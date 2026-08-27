package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.minecraft.entity.LivingEntity;
import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkingFetchMsgMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.network.codec.PacketCodecs;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public record SendChannelingSkillFinishedToServer(String SkillID, float ClientUserSkillIDDefaultChannelingTime, int IsChannelingAction, int IsChannelingInterrupted, int TimeSpentOnChanneling) implements CustomPayload {
    public static final CustomPayload.Id<SendChannelingSkillFinishedToServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "send_channeling_skill_finished_to_server") );

    public static final PacketCodec<RegistryByteBuf, SendChannelingSkillFinishedToServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            SendChannelingSkillFinishedToServer::SkillID,
            PacketCodecs.FLOAT,
            SendChannelingSkillFinishedToServer::ClientUserSkillIDDefaultChannelingTime,
            PacketCodecs.INTEGER,
            SendChannelingSkillFinishedToServer::IsChannelingAction,
            PacketCodecs.INTEGER,
            SendChannelingSkillFinishedToServer::IsChannelingInterrupted,
            PacketCodecs.INTEGER,
            SendChannelingSkillFinishedToServer::TimeSpentOnChanneling,
            SendChannelingSkillFinishedToServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final SendChannelingSkillFinishedToServer data, final ServerPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.server().execute(() -> {
                    //do on main thread
                    //if in cd, dont cast
                    ServerPlayerEntity sp1 = context.player();
                    SkillDataJson skill1 = getID2SkillData().get(data.SkillID());
                    int playerSkillLVL = 0;
                    if (getGlobalPlayerSkillID2lvlMap().get(sp1).containsKey(data.SkillID())) {
                        playerSkillLVL = getGlobalPlayerSkillID2lvlMap().get(sp1).get(data.SkillID());
                        if (playerSkillLVL <= 0) {
                            return;
                        }
                        playerSkillLVL = playerSkillLVL - 1;
                    }
                    float ServerSkillIDDefaultChannelingTime = skill1.getChannelingTime().get(playerSkillLVL).floatValue();

                    if (data.ClientUserSkillIDDefaultChannelingTime() >= ServerSkillIDDefaultChannelingTime - 1f) {
                        if (skill1.isActiveType() && skill1.getActiveSkillAction1() != null) {
                            skill1.getActiveSkillAction1().executeAction(sp1);
//                    printInGameMsg("channel ticks: "+getSPlayerState().get(sp1));
                        }
                    } else {
                        //kick?
                        NetworkingFetchMsgMethods.FetchPlayerMsgFromServer(sp1, "Channeling Time #2 doesn't match with the current server!");
                    }

                    HashMap<LivingEntity, HashMap<String, Integer>> ServerLivingEntityState = getSLivingEntityState();
                    if (ServerLivingEntityState.containsKey(sp1) && ServerLivingEntityState.get(sp1) != null) {
                        ServerLivingEntityState.get(sp1).put(Constants.IS_CHANNELING, data.IsChannelingAction());
                        if (data.IsChannelingInterrupted() == 1) {
                            ServerLivingEntityState.get(sp1).put(Constants.CHANNELING_INTERRUPTED, data.IsChannelingInterrupted());
                        }
                        ServerLivingEntityState.get(sp1).put(Constants.CHANNELING_TICKS, data.TimeSpentOnChanneling());
                    }
                });
    }
}

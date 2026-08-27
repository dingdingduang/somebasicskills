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
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerLastTriggeredChannelingActiveSkillID;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public record SendChannelingSkillToServer(String SkillID, float ClientUserSkillIDDefaultChannelingTime) implements CustomPayload {
    public static final CustomPayload.Id<SendChannelingSkillToServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "send_channeling_skill_to_server") );

    public static final PacketCodec<RegistryByteBuf, SendChannelingSkillToServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            SendChannelingSkillToServer::SkillID,
            PacketCodecs.FLOAT,
            SendChannelingSkillToServer::ClientUserSkillIDDefaultChannelingTime,
            SendChannelingSkillToServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final SendChannelingSkillToServer data, final ServerPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.server().execute(() -> {
                    //do on main thread
                    ServerPlayerEntity sp1 = context.player();
                    //if in cd, dont cast
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
                        if (skill1.isActiveType() && skill1.getChannelingSkillAction1() != null) {
                            skill1.getChannelingSkillAction1().executeAction(sp1);
                            getSPlayerLastTriggeredChannelingActiveSkillID().put(sp1, data.SkillID());
                        }
                    } else {
                        //kick?
                        NetworkingFetchMsgMethods.FetchPlayerMsgFromServer(sp1, "Channeling Time #1 doesn't match with the current server!");
                    }

                    HashMap<LivingEntity, HashMap<String, Integer>> ServerLivingEntityState = getSLivingEntityState();
                    if (ServerLivingEntityState.containsKey(sp1) && ServerLivingEntityState.get(sp1) != null) {
                        ServerLivingEntityState.get(sp1).put(Constants.IS_CHANNELING, Constants.ACTION_ON);
                        ServerLivingEntityState.get(sp1).put(Constants.CHANNELING_INTERRUPTED, Constants.ACTION_OFF);
                    }
                });
    }
}

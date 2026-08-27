package net.dingdingduang.somebasicskills.networking.packet.skill;

import io.netty.buffer.ByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkingFetchMsgMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;

import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public record SendChannelingSkillFinishedToServer(String SkillID, float ClientUserSkillIDDefaultChannelingTime, int IsChannelingAction, int IsChannelingInterrupted, int TimeSpentOnChanneling) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SendChannelingSkillFinishedToServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "send_channeling_skill_finished_to_server") );

    public static final StreamCodec<ByteBuf, SendChannelingSkillFinishedToServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            SendChannelingSkillFinishedToServer::SkillID,
            ByteBufCodecs.FLOAT,
            SendChannelingSkillFinishedToServer::ClientUserSkillIDDefaultChannelingTime,
            ByteBufCodecs.INT,
            SendChannelingSkillFinishedToServer::IsChannelingAction,
            ByteBufCodecs.INT,
            SendChannelingSkillFinishedToServer::IsChannelingInterrupted,
            ByteBufCodecs.INT,
            SendChannelingSkillFinishedToServer::TimeSpentOnChanneling,
            SendChannelingSkillFinishedToServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final SendChannelingSkillFinishedToServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                    //do on main thread
                    //if in cd, dont cast
                    ServerPlayer sp1 = (ServerPlayer) context.player();
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
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

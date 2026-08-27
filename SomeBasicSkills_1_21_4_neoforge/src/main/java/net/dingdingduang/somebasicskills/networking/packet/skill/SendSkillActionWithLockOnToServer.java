package net.dingdingduang.somebasicskills.networking.packet.skill;

import io.netty.buffer.ByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkingFetchMsgMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerLastTriggeredNotChannelingActiveSkillID;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public record SendSkillActionWithLockOnToServer(String SkillID, float ClientUserSkillIDDefaultSkillCDTime, float ServerPlayerFacingAngle) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SendSkillActionWithLockOnToServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "send_skill_action_with_lock_on_to_server") );

    public static final StreamCodec<ByteBuf, SendSkillActionWithLockOnToServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            SendSkillActionWithLockOnToServer::SkillID,
            ByteBufCodecs.FLOAT,
            SendSkillActionWithLockOnToServer::ClientUserSkillIDDefaultSkillCDTime,
            ByteBufCodecs.FLOAT,
            SendSkillActionWithLockOnToServer::ServerPlayerFacingAngle,
            SendSkillActionWithLockOnToServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final SendSkillActionWithLockOnToServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                    //do on main thread
                    ServerPlayer sp1 = (ServerPlayer) context.player();

                    //TODO server timer cooldown if in cd, dont cast
                    SkillDataJson skill1 = getID2SkillData().get(data.SkillID());
                    int playerSkillLVL = 0;
                    if (getGlobalPlayerSkillID2lvlMap().get(sp1).containsKey(data.SkillID())) {
                        playerSkillLVL = getGlobalPlayerSkillID2lvlMap().get(sp1).get(data.SkillID());
                        if (playerSkillLVL <= 0) { return; }
                        playerSkillLVL = playerSkillLVL - 1;
                    }
                    float ServerSkillIDDefaultCDTime = skill1.getCooldownTime().get(playerSkillLVL).floatValue();

                    if (data.ClientUserSkillIDDefaultSkillCDTime() >= ServerSkillIDDefaultCDTime - 1f) {
                        if (skill1.isActiveType() && skill1.getActiveSkillAction1() != null) {
                            sp1.setYRot(data.ServerPlayerFacingAngle());
                            skill1.getActiveSkillAction1().executeAction(sp1);
                            getSPlayerLastTriggeredNotChannelingActiveSkillID().put(sp1, data.SkillID());
                        }
                    }
                    else {
                        //kick
                        NetworkingFetchMsgMethods.FetchPlayerMsgFromServer(sp1, "Cooldown doesn't match with the current server!");
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

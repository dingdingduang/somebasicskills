package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkingFetchMsgMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.network.codec.PacketCodecs;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerLastTriggeredNotChannelingActiveSkillID;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public record SendSkillActionWithLockOnToServer(String SkillID, float ClientUserSkillIDDefaultSkillCDTime, float ServerPlayerFacingAngle) implements CustomPayload {
    public static final CustomPayload.Id<SendSkillActionWithLockOnToServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "send_skill_action_with_lock_on_to_server") );

    public static final PacketCodec<RegistryByteBuf, SendSkillActionWithLockOnToServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            SendSkillActionWithLockOnToServer::SkillID,
            PacketCodecs.FLOAT,
            SendSkillActionWithLockOnToServer::ClientUserSkillIDDefaultSkillCDTime,
            PacketCodecs.FLOAT,
            SendSkillActionWithLockOnToServer::ServerPlayerFacingAngle,
            SendSkillActionWithLockOnToServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final SendSkillActionWithLockOnToServer data, final ServerPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.server().execute(() -> {
                    //do on main thread
                    ServerPlayerEntity sp1 = context.player();

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
                            sp1.setYaw(data.ServerPlayerFacingAngle());
                            skill1.getActiveSkillAction1().executeAction(sp1);
                            getSPlayerLastTriggeredNotChannelingActiveSkillID().put(sp1, data.SkillID());
                        }
                    }
                    else {
                        //kick
                        NetworkingFetchMsgMethods.FetchPlayerMsgFromServer(sp1, "Cooldown doesn't match with the current server!");
                    }
                });
    }
}

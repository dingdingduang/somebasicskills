package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.network.codec.PacketCodecs;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public record SendSkillPassiveActionTwoToServer(String SkillID) implements CustomPayload {
    public static final CustomPayload.Id<SendSkillPassiveActionTwoToServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "send_skill_passive_action_two_to_server") );

    public static final PacketCodec<RegistryByteBuf, SendSkillPassiveActionTwoToServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            SendSkillPassiveActionTwoToServer::SkillID,
            SendSkillPassiveActionTwoToServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final SendSkillPassiveActionTwoToServer data, final ServerPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.server().execute(() -> {
                    //do on main thread
                    ServerPlayerEntity sp1 = context.player();

                    //TODO server timer cooldown if in cd, dont cast
                    SkillDataJson skill1 = getID2SkillData().get(data.SkillID());

                    if ((skill1.isPassiveType() || skill1.isBothType()) && skill1.getPassiveSkillAction2() != null) {
                        skill1.getPassiveSkillAction2().executeAction(sp1);
                    }
                });
    }
}

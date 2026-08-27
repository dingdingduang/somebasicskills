package net.dingdingduang.somebasicskills.networking.packet.skill;

import io.netty.buffer.ByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;

import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public record SendSkillPassiveActionToServer(String SkillID) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SendSkillPassiveActionToServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "send_skill_passive_action_to_server") );

    public static final StreamCodec<ByteBuf, SendSkillPassiveActionToServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            SendSkillPassiveActionToServer::SkillID,
            SendSkillPassiveActionToServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final SendSkillPassiveActionToServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                    //do on main thread
                    ServerPlayer sp1 = (ServerPlayer) context.player();

                    SkillDataJson skill1 = getID2SkillData().get(data.SkillID());

                    if ((skill1.isPassiveType() || skill1.isBothType()) && skill1.getPassiveSkillAction1() != null) {
                        skill1.getPassiveSkillAction1().executeAction(sp1);
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

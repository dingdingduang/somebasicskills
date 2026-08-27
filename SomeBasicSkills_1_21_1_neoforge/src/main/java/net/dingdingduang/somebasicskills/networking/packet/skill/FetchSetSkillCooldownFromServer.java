package net.dingdingduang.somebasicskills.networking.packet.skill;

import io.netty.buffer.ByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.gui.overlay.SkillsInCooldownClientTimerOverlay;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;

public record FetchSetSkillCooldownFromServer(boolean IsTimerOn, String SkillID, double CooldownAmount) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<FetchSetSkillCooldownFromServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "fetch_set_skill_cooldown_from_server") );

    public static final StreamCodec<ByteBuf, FetchSetSkillCooldownFromServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            FetchSetSkillCooldownFromServer::IsTimerOn,
            ByteBufCodecs.STRING_UTF8,
            FetchSetSkillCooldownFromServer::SkillID,
            ByteBufCodecs.DOUBLE,
            FetchSetSkillCooldownFromServer::CooldownAmount,
            FetchSetSkillCooldownFromServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final FetchSetSkillCooldownFromServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                    //do on main thread
                    SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance().setCooldownTimer(data.IsTimerOn(), data.SkillID(), data.CooldownAmount());
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

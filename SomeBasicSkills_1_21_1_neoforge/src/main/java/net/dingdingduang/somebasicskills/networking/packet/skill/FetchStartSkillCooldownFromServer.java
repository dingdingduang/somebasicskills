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

public record FetchStartSkillCooldownFromServer(boolean IsTimerOn, String SkillID) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<FetchStartSkillCooldownFromServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "fetch_start_skill_cooldown_from_server") );

    public static final StreamCodec<ByteBuf, FetchStartSkillCooldownFromServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            FetchStartSkillCooldownFromServer::IsTimerOn,
            ByteBufCodecs.STRING_UTF8,
            FetchStartSkillCooldownFromServer::SkillID,
            FetchStartSkillCooldownFromServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final FetchStartSkillCooldownFromServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                    //do on main thread
                    SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance().setCooldownTimer(data.IsTimerOn(), data.SkillID());
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

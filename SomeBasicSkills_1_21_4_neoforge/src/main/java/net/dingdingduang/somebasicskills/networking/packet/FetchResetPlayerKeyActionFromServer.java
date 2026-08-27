package net.dingdingduang.somebasicskills.networking.packet;

import io.netty.buffer.ByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.gui.overlay.SkillChannelingOverlay;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerLastKeyAction;

public record FetchResetPlayerKeyActionFromServer(String SkillID) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<FetchResetPlayerKeyActionFromServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "fetch_reset_player_key_action_from_server") );

    public static final StreamCodec<ByteBuf, FetchResetPlayerKeyActionFromServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            FetchResetPlayerKeyActionFromServer::SkillID,
            FetchResetPlayerKeyActionFromServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final FetchResetPlayerKeyActionFromServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                    //do on main thread

                    //do shiet on client side
                    SkillChannelingOverlay.resetSkillKeyCodeActionFromPlayerSkillID2KeyCode(data.SkillID());
                    setCPlayerLastKeyAction(0);

                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

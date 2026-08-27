package net.dingdingduang.somebasicskills.networking.packet;

import io.netty.buffer.ByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.event.SBPlayerConfigFileInitHelper;

import net.dingdingduang.somebasicskills.networking.StreamCodecMoreMethods;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;

public record SendResetPlayerStateRequestToServer() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SendResetPlayerStateRequestToServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "send_reset_player_state_request_to_server") );

    public static final StreamCodec<ByteBuf, SendResetPlayerStateRequestToServer> STREAM_CODEC = StreamCodecMoreMethods.composite(
            SendResetPlayerStateRequestToServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final SendResetPlayerStateRequestToServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                    //do on main thread
                    ServerPlayer sp1 = (ServerPlayer) context.player();

                    SBPlayerConfigFileInitHelper tempConfigStateHelper = new SBPlayerConfigFileInitHelper(sp1);
                    tempConfigStateHelper.SBPlayerUnstuckRequestOnServer();

                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

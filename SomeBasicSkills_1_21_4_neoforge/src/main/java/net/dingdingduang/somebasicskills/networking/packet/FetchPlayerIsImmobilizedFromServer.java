package net.dingdingduang.somebasicskills.networking.packet;

import io.netty.buffer.ByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerIsImmobilized;

public record FetchPlayerIsImmobilizedFromServer(boolean isImmobilized) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<FetchPlayerIsImmobilizedFromServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "fetch_player_is_immobilized_from_server") );

    public static final StreamCodec<ByteBuf, FetchPlayerIsImmobilizedFromServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            FetchPlayerIsImmobilizedFromServer::isImmobilized,
            FetchPlayerIsImmobilizedFromServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final FetchPlayerIsImmobilizedFromServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                    //do on main thread

                    setCPlayerIsImmobilized(data.isImmobilized());
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

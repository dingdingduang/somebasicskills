package net.dingdingduang.somebasicskills.networking.packet;

import io.netty.buffer.ByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerBaseMultiplierStatusMap;

public record FetchStatusKeyValueOnlyFromServer(String StateName, double AnyDouble) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<FetchStatusKeyValueOnlyFromServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "fetch_status_key_value_only_from_server") );

    public static final StreamCodec<ByteBuf, FetchStatusKeyValueOnlyFromServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            FetchStatusKeyValueOnlyFromServer::StateName,
            ByteBufCodecs.DOUBLE,
            FetchStatusKeyValueOnlyFromServer::AnyDouble,
            FetchStatusKeyValueOnlyFromServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final FetchStatusKeyValueOnlyFromServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                    //do on main thread
                    getCPlayerBaseMultiplierStatusMap().put(data.StateName(), data.AnyDouble());

                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

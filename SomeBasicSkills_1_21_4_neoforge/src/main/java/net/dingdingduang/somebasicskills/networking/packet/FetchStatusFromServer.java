package net.dingdingduang.somebasicskills.networking.packet;

import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerBaseMultiplierStatusMap;

public record FetchStatusFromServer(HashMap<String, Double> PlayerStatus) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<FetchStatusFromServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "fetch_status_from_server") );

    public static final StreamCodec<ByteBuf, FetchStatusFromServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.map(Maps::newHashMapWithExpectedSize, ByteBufCodecs.STRING_UTF8, ByteBufCodecs.DOUBLE),
            FetchStatusFromServer::PlayerStatus,
            FetchStatusFromServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final FetchStatusFromServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                    //do on main thread

                    setCPlayerBaseMultiplierStatusMap(data.PlayerStatus());
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

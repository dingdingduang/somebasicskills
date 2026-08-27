package net.dingdingduang.somebasicskills.networking.packet;

import com.google.common.collect.Maps;
import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.codec.PacketCodecs;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerBaseMultiplierStatusMap;

public record FetchStatusFromServer(HashMap<String, Double> PlayerStatus) implements CustomPayload {
    public static final CustomPayload.Id<FetchStatusFromServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "fetch_status_from_server") );

    public static final PacketCodec<RegistryByteBuf, FetchStatusFromServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.map(Maps::newHashMapWithExpectedSize, PacketCodecs.STRING, PacketCodecs.DOUBLE),
            FetchStatusFromServer::PlayerStatus,
            FetchStatusFromServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final FetchStatusFromServer data, final ClientPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.client().execute(() -> {
                    //do on main thread

                    setCPlayerBaseMultiplierStatusMap(data.PlayerStatus());
                });
    }
}

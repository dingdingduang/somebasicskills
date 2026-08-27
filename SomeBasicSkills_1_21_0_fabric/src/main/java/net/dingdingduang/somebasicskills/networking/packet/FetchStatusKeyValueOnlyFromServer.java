package net.dingdingduang.somebasicskills.networking.packet;

import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.codec.PacketCodecs;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerBaseMultiplierStatusMap;

public record FetchStatusKeyValueOnlyFromServer(String StateName, double AnyDouble) implements CustomPayload {
    public static final CustomPayload.Id<FetchStatusKeyValueOnlyFromServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "fetch_status_key_value_only_from_server") );

    public static final PacketCodec<RegistryByteBuf, FetchStatusKeyValueOnlyFromServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            FetchStatusKeyValueOnlyFromServer::StateName,
            PacketCodecs.DOUBLE,
            FetchStatusKeyValueOnlyFromServer::AnyDouble,
            FetchStatusKeyValueOnlyFromServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final FetchStatusKeyValueOnlyFromServer data, final ClientPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.client().execute(() -> {
                    //do on main thread
                    getCPlayerBaseMultiplierStatusMap().put(data.StateName(), data.AnyDouble());

                });
    }
}

package net.dingdingduang.somebasicskills.networking.packet;

import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.codec.PacketCodecs;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerIsImmobilized;

public record FetchPlayerIsImmobilizedFromServer(boolean isImmobilized) implements CustomPayload {
    public static final CustomPayload.Id<FetchPlayerIsImmobilizedFromServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "fetch_player_is_immobilized_from_server") );

    public static final PacketCodec<RegistryByteBuf, FetchPlayerIsImmobilizedFromServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.BOOLEAN,
            FetchPlayerIsImmobilizedFromServer::isImmobilized,
            FetchPlayerIsImmobilizedFromServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final FetchPlayerIsImmobilizedFromServer data, final ClientPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.client().execute(() -> {
                    //do on main thread

                    setCPlayerIsImmobilized(data.isImmobilized());
                });
    }
}

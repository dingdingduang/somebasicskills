package net.dingdingduang.somebasicskills.networking.packet.skill;

import com.google.common.collect.Maps;
import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.codec.PacketCodecs;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerState;

public record FetchPlayerStateMapFromServer(HashMap<String, Integer> PlayerState) implements CustomPayload {
    public static final CustomPayload.Id<FetchPlayerStateMapFromServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "fetch_player_state_map_from_server") );

    public static final PacketCodec<RegistryByteBuf, FetchPlayerStateMapFromServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.map(Maps::newHashMapWithExpectedSize, PacketCodecs.STRING, PacketCodecs.INTEGER),
            FetchPlayerStateMapFromServer::PlayerState,
            FetchPlayerStateMapFromServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final FetchPlayerStateMapFromServer data, final ClientPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.client().execute(() -> {
                    //do on main thread
                    setCPlayerState(data.PlayerState());
                });
    }
}

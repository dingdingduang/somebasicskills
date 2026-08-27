package net.dingdingduang.somebasicskills.networking.packet.skill;

import com.google.common.collect.Maps;
import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.network.codec.PacketCodecs;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;

public record SendPlayerStateMapToServer(HashMap<String, Integer> PlayerState) implements CustomPayload {
    public static final CustomPayload.Id<SendPlayerStateMapToServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "send_player_state_map_to_server") );

    public static final PacketCodec<RegistryByteBuf, SendPlayerStateMapToServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.map(Maps::newHashMapWithExpectedSize, PacketCodecs.STRING, PacketCodecs.INTEGER),
            SendPlayerStateMapToServer::PlayerState,
            SendPlayerStateMapToServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final SendPlayerStateMapToServer data, final ServerPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.server().execute(() -> {
                    //do on main thread
                    ServerPlayerEntity sp1 = context.player();

                    getSLivingEntityState().put(sp1, data.PlayerState());
                });
    }
}

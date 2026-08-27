package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.minecraft.entity.LivingEntity;
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

public record SendPlayerStateKeyValueOnlyToServer(String StateName, int AnyInteger) implements CustomPayload {
    public static final CustomPayload.Id<SendPlayerStateKeyValueOnlyToServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "send_player_state_key_value_only_to_server") );

    public static final PacketCodec<RegistryByteBuf, SendPlayerStateKeyValueOnlyToServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            SendPlayerStateKeyValueOnlyToServer::StateName,
            PacketCodecs.INTEGER,
            SendPlayerStateKeyValueOnlyToServer::AnyInteger,
            SendPlayerStateKeyValueOnlyToServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final SendPlayerStateKeyValueOnlyToServer data, final ServerPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.server().execute(() -> {
                    //do on main thread
                    ServerPlayerEntity sp1 = context.player();

                    HashMap<LivingEntity, HashMap<String, Integer>> ServerLivingEntityState = getSLivingEntityState();
                    if (ServerLivingEntityState.containsKey(sp1) && ServerLivingEntityState.get(sp1) != null) {
                        ServerLivingEntityState.get(sp1).put(data.StateName(), data.AnyInteger());
                    }
                });
    }
}

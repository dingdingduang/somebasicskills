package net.dingdingduang.somebasicskills.networking.packet.skill;

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

public record SendPlayerStateKeyIncrementValueOnlyToServer(String StateName, int AnyInteger) implements CustomPayload {
    public static final CustomPayload.Id<SendPlayerStateKeyIncrementValueOnlyToServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "send_player_state_key_increment_value_only_to_server") );

    public static final PacketCodec<RegistryByteBuf, SendPlayerStateKeyIncrementValueOnlyToServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            SendPlayerStateKeyIncrementValueOnlyToServer::StateName,
            PacketCodecs.INTEGER,
            SendPlayerStateKeyIncrementValueOnlyToServer::AnyInteger,
            SendPlayerStateKeyIncrementValueOnlyToServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final SendPlayerStateKeyIncrementValueOnlyToServer data, final ServerPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.server().execute(() -> {
                    //do on main thread
                    //do shiet on server side
                    ServerPlayerEntity sp1 = context.player();
                    HashMap<String, Integer> tempPlayerState = getSLivingEntityState().get(sp1);
                    int incrementInteger;
                    if (tempPlayerState.containsKey(data.StateName())) {
                        incrementInteger = tempPlayerState.get(data.StateName()) + data.AnyInteger();
                    } else {
                        incrementInteger = data.AnyInteger();
                    }
                    tempPlayerState.put(data.StateName(), incrementInteger);
                });
    }
}

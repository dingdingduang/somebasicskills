package net.dingdingduang.somebasicskills.networking.packet.skill;

import io.netty.buffer.ByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;

public record SendPlayerStateKeyIncrementValueOnlyToServer(String StateName, int AnyInteger) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SendPlayerStateKeyIncrementValueOnlyToServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "send_player_state_key_increment_value_only_to_server") );

    public static final StreamCodec<ByteBuf, SendPlayerStateKeyIncrementValueOnlyToServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            SendPlayerStateKeyIncrementValueOnlyToServer::StateName,
            ByteBufCodecs.INT,
            SendPlayerStateKeyIncrementValueOnlyToServer::AnyInteger,
            SendPlayerStateKeyIncrementValueOnlyToServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final SendPlayerStateKeyIncrementValueOnlyToServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                    //do on main thread
                    //do shiet on server side
                    ServerPlayer sp1 = (ServerPlayer) context.player();
                    HashMap<String, Integer> tempPlayerState = getSLivingEntityState().get(sp1);
                    int incrementInteger;
                    if (tempPlayerState.containsKey(data.StateName())) {
                        incrementInteger = tempPlayerState.get(data.StateName()) + data.AnyInteger();
                    } else {
                        incrementInteger = data.AnyInteger();
                    }
                    tempPlayerState.put(data.StateName(), incrementInteger);
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

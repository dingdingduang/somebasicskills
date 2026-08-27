package net.dingdingduang.somebasicskills.networking.packet.skill;

import io.netty.buffer.ByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;

public record SendPlayerStateKeyValueOnlyToServer(String StateName, int AnyInteger) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SendPlayerStateKeyValueOnlyToServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "send_player_state_key_value_only_to_server") );

    public static final StreamCodec<ByteBuf, SendPlayerStateKeyValueOnlyToServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            SendPlayerStateKeyValueOnlyToServer::StateName,
            ByteBufCodecs.INT,
            SendPlayerStateKeyValueOnlyToServer::AnyInteger,
            SendPlayerStateKeyValueOnlyToServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final SendPlayerStateKeyValueOnlyToServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                    //do on main thread
                    ServerPlayer sp1 = (ServerPlayer) context.player();

                    HashMap<LivingEntity, HashMap<String, Integer>> ServerLivingEntityState = getSLivingEntityState();
                    if (ServerLivingEntityState.containsKey(sp1) && ServerLivingEntityState.get(sp1) != null) {
                        ServerLivingEntityState.get(sp1).put(data.StateName(), data.AnyInteger());
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

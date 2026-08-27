package net.dingdingduang.somebasicskills.networking.packet;

import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;
import static net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods.StatusPlayerNameFileWriteTo;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerValue2BaseMultiplierMap;

public record SendStatusToServer(HashMap<String, Double> PlayerStatus) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SendStatusToServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "send_status_to_server") );

    public static final StreamCodec<ByteBuf, SendStatusToServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.map(Maps::newHashMapWithExpectedSize, ByteBufCodecs.STRING_UTF8, ByteBufCodecs.DOUBLE),
            SendStatusToServer::PlayerStatus,
            SendStatusToServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final SendStatusToServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                    //do on main thread
                    ServerPlayer sp1 = (ServerPlayer) context.player();

                    MinecraftServer mcServer = getMinecraftServerInstance();

                    getSPlayerValue2BaseMultiplierMap().put(sp1, data.PlayerStatus());
                    StatusPlayerNameFileWriteTo(mcServer, sp1);
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

package net.dingdingduang.somebasicskills.networking.packet;

import com.google.common.collect.Maps;
import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import net.minecraft.network.codec.PacketCodecs;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;
import static net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods.StatusPlayerNameFileWriteTo;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerValue2BaseMultiplierMap;

public record SendStatusToServer(HashMap<String, Double> PlayerStatus) implements CustomPayload {
    public static final CustomPayload.Id<SendStatusToServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "send_status_to_server") );

    public static final PacketCodec<RegistryByteBuf, SendStatusToServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.map(Maps::newHashMapWithExpectedSize, PacketCodecs.STRING, PacketCodecs.DOUBLE),
            SendStatusToServer::PlayerStatus,
            SendStatusToServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final SendStatusToServer data, final ServerPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.server().execute(() -> {
                    //do on main thread
                    ServerPlayerEntity sp1 = context.player();

                    MinecraftServer mcServer = getMinecraftServerInstance(sp1);

                    getSPlayerValue2BaseMultiplierMap().put(sp1, data.PlayerStatus());
                    StatusPlayerNameFileWriteTo(mcServer, sp1);
                });
    }
}

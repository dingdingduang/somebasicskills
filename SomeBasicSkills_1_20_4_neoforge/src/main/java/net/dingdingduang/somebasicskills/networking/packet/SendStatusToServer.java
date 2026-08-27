package net.dingdingduang.somebasicskills.networking.packet;

import com.google.common.collect.Maps;
import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;
import static net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods.StatusPlayerNameFileWriteTo;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerValue2BaseMultiplierMap;

public record SendStatusToServer(HashMap<String, Double> PlayerStatus) implements CustomPacketPayload {
    public static final ResourceLocation SEND_STATUS_TO_SERVER = getMCResourceLocation(Constants.MOD_ID, "send_status_to_server");

    @Override
    public ResourceLocation id() {
        return SEND_STATUS_TO_SERVER;
    }

    public SendStatusToServer(FriendlyByteBuf buf) {
        this((HashMap<String, Double>) buf.readMap(Maps::newHashMapWithExpectedSize, FriendlyByteBuf::readUtf, FriendlyByteBuf::readDouble));
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeMap(PlayerStatus(), FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeDouble);
    }

    public static void handle(final SendStatusToServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread
                    if (context.player().isPresent()) {
                        ServerPlayer sp1 = (ServerPlayer) context.player().get();

                        MinecraftServer mcServer = getMinecraftServerInstance();

                        getSPlayerValue2BaseMultiplierMap().put(sp1, data.PlayerStatus());
                        StatusPlayerNameFileWriteTo(mcServer, sp1);

                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

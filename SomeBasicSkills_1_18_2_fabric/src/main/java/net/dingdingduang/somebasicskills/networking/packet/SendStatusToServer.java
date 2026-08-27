package net.dingdingduang.somebasicskills.networking.packet;

import com.google.common.collect.Maps;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkFabricGeneralMethods;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods.StatusPlayerNameFileWriteTo;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftServerInstance;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerValue2BaseMultiplierMap;

public class SendStatusToServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "send_status_to_server");

    public static PacketByteBuf setupPacket(HashMap<String, Double> playerStatus) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeMap(playerStatus, PacketByteBuf::writeString, PacketByteBuf::writeDouble);
        return packetToBeSent;
    }

    public static void toServerHandle(MinecraftServer server, ServerPlayerEntity serverPlayer, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final HashMap<String, Double> PlayerStatus = buf.readMap(Maps::newHashMapWithExpectedSize, PacketByteBuf::readString, PacketByteBuf::readDouble);

        server.execute( () -> {
            //start action

            MinecraftServer mcServer = getMinecraftServerInstance(serverPlayer);
            if (mcServer == null) { return; }

            getSPlayerValue2BaseMultiplierMap().put(serverPlayer, PlayerStatus);
            StatusPlayerNameFileWriteTo(mcServer, serverPlayer);

        });
    }
}

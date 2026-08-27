package net.dingdingduang.somebasicskills.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class NetworkFabricGeneralMethods {
    public static PacketByteBuf createPackage() { return PacketByteBufs.create(); }

    public static void sendToServer(Identifier packetID, PacketByteBuf dataBuf) {
        ClientPlayNetworking.send(packetID, dataBuf);
    }

    public static void sendToPlayer(Identifier packetID, PacketByteBuf dataBuf, ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, packetID, dataBuf);
    }
}

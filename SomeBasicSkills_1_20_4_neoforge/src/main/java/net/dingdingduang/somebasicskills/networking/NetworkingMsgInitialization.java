package net.dingdingduang.somebasicskills.networking;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;

public class NetworkingMsgInitialization {
    public static void sendToServer(CustomPacketPayload payload) {
        PacketDistributor.SERVER.with(null).send(payload);
    }

    public static void sendToPlayer(CustomPacketPayload payload, ServerPlayer player) {
        PacketDistributor.PLAYER.with(player).send(payload);
    }
}
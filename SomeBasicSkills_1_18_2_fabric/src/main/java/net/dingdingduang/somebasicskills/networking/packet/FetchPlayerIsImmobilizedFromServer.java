package net.dingdingduang.somebasicskills.networking.packet;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkFabricGeneralMethods;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;


import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerIsImmobilized;

public class FetchPlayerIsImmobilizedFromServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "fetch_player_is_immobilized_from_server");

    public static PacketByteBuf setupPacket(boolean IsImmobilized) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeBoolean(IsImmobilized);
        return packetToBeSent;
    }

    public static void toClientHandle(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final boolean IsImmobilized = buf.readBoolean();

        client.execute( () -> {
            //start action
            setCPlayerIsImmobilized(IsImmobilized);
        });
    }
}

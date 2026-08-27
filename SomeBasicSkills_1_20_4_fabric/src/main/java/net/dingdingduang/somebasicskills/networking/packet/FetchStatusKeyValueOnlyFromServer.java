package net.dingdingduang.somebasicskills.networking.packet;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkFabricGeneralMethods;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerBaseMultiplierStatusMap;

public class FetchStatusKeyValueOnlyFromServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "fetch_status_key_value_only_from_server");

    public static PacketByteBuf setupPacket(String stateName, double anyDouble) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeString(stateName);
        packetToBeSent.writeDouble(anyDouble);
        return packetToBeSent;
    }

    public static void toClientHandle(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final String StateName = buf.readString();
        final double AnyDouble = buf.readDouble();

        client.execute( () -> {
            //start action
            getCPlayerBaseMultiplierStatusMap().put(StateName, AnyDouble);
        });
    }
}

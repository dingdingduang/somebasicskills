package net.dingdingduang.somebasicskills.networking.packet.skill;

import com.google.common.collect.Maps;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkFabricGeneralMethods;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerState;

public class FetchPlayerStateMapFromServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "fetch_player_state_map_from_server");

    public static PacketByteBuf setupPacket(HashMap<String, Integer> playerStatus) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeMap(playerStatus, PacketByteBuf::writeString, PacketByteBuf::writeInt);
        return packetToBeSent;
    }

    public static void toClientHandle(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final HashMap<String, Integer> PlayerState = buf.readMap(Maps::newHashMapWithExpectedSize, PacketByteBuf::readString, PacketByteBuf::readInt);

        client.execute( () -> {
            //start action
            setCPlayerState(PlayerState);
        });
    }
}

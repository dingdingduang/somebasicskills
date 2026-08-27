package net.dingdingduang.somebasicskills.networking.packet.skill;

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
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;

public class SendPlayerStateMapToServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "send_player_state_map_to_server");

    public static PacketByteBuf setupPacket(HashMap<String, Integer> playerStatus) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeMap(playerStatus, PacketByteBuf::writeString, PacketByteBuf::writeInt);
        return packetToBeSent;
    }

    public static void toServerHandle(MinecraftServer server, ServerPlayerEntity serverPlayer, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final HashMap<String, Integer> PlayerState = buf.readMap(Maps::newHashMapWithExpectedSize, PacketByteBuf::readString, PacketByteBuf::readInt);

        server.execute( () -> {
            //start action
            getSLivingEntityState().put(serverPlayer, PlayerState);
        });
    }
}

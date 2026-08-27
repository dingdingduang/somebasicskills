package net.dingdingduang.somebasicskills.networking.packet.skill;

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

public class SendPlayerStateKeyIncrementValueOnlyToServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "send_player_state_key_increment_value_only_to_server");

    public static PacketByteBuf setupPacket(String stateName, int anyInteger) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeString(stateName);
        packetToBeSent.writeInt(anyInteger);
        return packetToBeSent;
    }

    public static void toServerHandle(MinecraftServer server, ServerPlayerEntity serverPlayer, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final String StateName = buf.readString();
        final int AnyInteger = buf.readInt();

        server.execute( () -> {
            //start action

            //do shiet on server side
            HashMap<String, Integer> tempPlayerState = getSLivingEntityState().get(serverPlayer);
            int incrementInteger;
            if (tempPlayerState.containsKey(StateName)) {
                incrementInteger = tempPlayerState.get(StateName) + AnyInteger;
            }
            else {
                incrementInteger = AnyInteger;
            }
            tempPlayerState.put(StateName, incrementInteger);
        });
    }
}

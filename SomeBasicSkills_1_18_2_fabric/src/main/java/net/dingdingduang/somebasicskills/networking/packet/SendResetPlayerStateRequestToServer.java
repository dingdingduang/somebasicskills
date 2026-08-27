package net.dingdingduang.somebasicskills.networking.packet;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.event.SBPlayerConfigFileInitHelper;
import net.dingdingduang.somebasicskills.networking.NetworkFabricGeneralMethods;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;

public class SendResetPlayerStateRequestToServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "send_reset_player_state_request_to_server");

    public static PacketByteBuf setupPacket() {
        return NetworkFabricGeneralMethods.createPackage();
    }

    public static void toServerHandle(MinecraftServer server, ServerPlayerEntity serverPlayer, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here

        server.execute( () -> {
            //start action
            SBPlayerConfigFileInitHelper tempConfigStateHelper = new SBPlayerConfigFileInitHelper(serverPlayer);
            tempConfigStateHelper.SBPlayerUnstuckRequestOnServer();
        });
    }
}

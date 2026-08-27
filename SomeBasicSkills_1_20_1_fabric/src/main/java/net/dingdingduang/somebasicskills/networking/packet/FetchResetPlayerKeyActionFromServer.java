package net.dingdingduang.somebasicskills.networking.packet;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.gui.overlay.SkillChannelingOverlay;
import net.dingdingduang.somebasicskills.networking.NetworkFabricGeneralMethods;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerLastKeyAction;

public class FetchResetPlayerKeyActionFromServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "fetch_reset_player_key_action_from_server");

    public static PacketByteBuf setupPacket(String skillID) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeString(skillID);
        return packetToBeSent;
    }

    public static void toClientHandle(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final String SkillID = buf.readString();

        client.execute( () -> {
            //start action

            SkillChannelingOverlay.resetSkillKeyCodeActionFromPlayerSkillID2KeyCode(SkillID);
            setCPlayerLastKeyAction(0);
        });
    }
}

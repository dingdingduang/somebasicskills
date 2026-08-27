package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.gui.overlay.SkillsInCooldownClientTimerOverlay;
import net.dingdingduang.somebasicskills.networking.NetworkFabricGeneralMethods;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;

public class FetchIncreaseSkillCooldownFromServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "fetch_increase_skill_cooldown_from_server");

    public static PacketByteBuf setupPacket(boolean isTimerOn, String skillID, double cooldownAmount) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeBoolean(isTimerOn);
        packetToBeSent.writeString(skillID);
        packetToBeSent.writeDouble(cooldownAmount);
        return packetToBeSent;
    }

    public static void toClientHandle(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final boolean IsTimerOn = buf.readBoolean();
        final String SkillID = buf.readString();
        final double CooldownAmount = buf.readDouble();

        client.execute( () -> {
            //start action
            SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance().increaseSkillCooldownTimer(IsTimerOn, SkillID, CooldownAmount);
        });
    }
}

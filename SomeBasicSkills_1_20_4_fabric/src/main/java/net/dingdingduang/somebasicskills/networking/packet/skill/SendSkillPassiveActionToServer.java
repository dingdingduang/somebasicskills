package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkFabricGeneralMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;


import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class SendSkillPassiveActionToServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "send_skill_passive_action_to_server");

    public static PacketByteBuf setupPacket(String skillID) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeString(skillID);
//        packetToBeSent.writeFloat(clientUserSkillIDDefaultSkillCDTime);
        return packetToBeSent;
    }

    public static void toServerHandle(MinecraftServer server, ServerPlayerEntity serverPlayer, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final String SkillID = buf.readString();
//        final float ClientUserSkillIDDefaultSkillCDTime = buf.readFloat();

        server.execute( () -> {
            //start action
            //TODO server timer cooldown if in cd, dont cast
            SkillDataJson skill1 = getID2SkillData().get(SkillID);

            if ((skill1.isPassiveType() || skill1.isBothType()) && skill1.getPassiveSkillAction1() != null) {
                skill1.getPassiveSkillAction1().executeAction(serverPlayer);
            }

        });
    }
}

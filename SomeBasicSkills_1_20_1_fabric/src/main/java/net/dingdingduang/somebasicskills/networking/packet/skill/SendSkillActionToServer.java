package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkFabricGeneralMethods;
import net.dingdingduang.somebasicskills.networking.NetworkingFetchMsgMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;


import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerLastTriggeredNotChannelingActiveSkillID;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class SendSkillActionToServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "send_skill_action_to_server");

    public static PacketByteBuf setupPacket(String skillID, float clientUserSkillIDDefaultSkillCDTime) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeString(skillID);
        packetToBeSent.writeFloat(clientUserSkillIDDefaultSkillCDTime);
        return packetToBeSent;
    }

    public static void toServerHandle(MinecraftServer server, ServerPlayerEntity serverPlayer, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final String SkillID = buf.readString();
        final float ClientUserSkillIDDefaultSkillCDTime = buf.readFloat();

        server.execute( () -> {
            //start action

//            if (serverPlayer == null) { return; }
            //TODO server timer cooldown if in cd, dont cast
            SkillDataJson skill1 = getID2SkillData().get(SkillID);
            int playerSkillLVL = 0;
            if (getGlobalPlayerSkillID2lvlMap().get(serverPlayer).containsKey(SkillID)) {
                playerSkillLVL = getGlobalPlayerSkillID2lvlMap().get(serverPlayer).get(SkillID);
                if (playerSkillLVL <= 0) {
                    return;
                }
                playerSkillLVL = playerSkillLVL - 1;
            }
            float ServerSkillIDDefaultCDTime = skill1.getCooldownTime().get(playerSkillLVL).floatValue();

            if (ClientUserSkillIDDefaultSkillCDTime >= ServerSkillIDDefaultCDTime - 1f) {
                if (skill1.isActiveType() && skill1.getActiveSkillAction1() != null) {
                    skill1.getActiveSkillAction1().executeAction(serverPlayer);
                    getSPlayerLastTriggeredNotChannelingActiveSkillID().put(serverPlayer, SkillID);
                }
            }
            else {
                //kick
                NetworkingFetchMsgMethods.FetchPlayerMsgFromServer(serverPlayer, "Cooldown doesn't match with the current server!");
            }
        });
    }
}

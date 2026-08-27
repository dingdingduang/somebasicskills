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
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerLastTriggeredChannelingActiveSkillID;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class SendChannelingSkillToServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "send_channeling_skill_to_server");

    public static PacketByteBuf setupPacket(String skillID, float clientUserSkillIDDefaultChannelingTicks) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeString(skillID);
        packetToBeSent.writeFloat(clientUserSkillIDDefaultChannelingTicks);
        return packetToBeSent;
    }

    public static void toServerHandle(MinecraftServer server, ServerPlayerEntity serverPlayer, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final String SkillID = buf.readString();
        final float ClientUserSkillIDDefaultChannelingTime = buf.readFloat();

        server.execute( () -> {
            //start action

            //if in cd, dont cast
            SkillDataJson skill1 = getID2SkillData().get(SkillID);
            int playerSkillLVL = 0;
            if (getGlobalPlayerSkillID2lvlMap().get(serverPlayer).containsKey(SkillID)) {
                playerSkillLVL = getGlobalPlayerSkillID2lvlMap().get(serverPlayer).get(SkillID);
                if (playerSkillLVL <= 0) { return; }
                playerSkillLVL = playerSkillLVL - 1;
            }
            float ServerSkillIDDefaultChannelingTime = skill1.getChannelingTime().get(playerSkillLVL).floatValue();

            if (ClientUserSkillIDDefaultChannelingTime >= ServerSkillIDDefaultChannelingTime - 1f) {
                if (skill1.isActiveType() && skill1.getChannelingSkillAction1() != null) {
                    skill1.getChannelingSkillAction1().executeAction(serverPlayer);
                    getSPlayerLastTriggeredChannelingActiveSkillID().put(serverPlayer, SkillID);
                }
            }
            else {
                //kick?
                NetworkingFetchMsgMethods.FetchPlayerMsgFromServer(serverPlayer, "Channeling Time #1 doesn't match with the current server!");
            }

            HashMap<LivingEntity, HashMap<String, Integer>> ServerLivingEntityState = getSLivingEntityState();
            if (ServerLivingEntityState.containsKey(serverPlayer) && ServerLivingEntityState.get(serverPlayer) != null) {
                ServerLivingEntityState.get(serverPlayer).put(Constants.IS_CHANNELING, Constants.ACTION_ON);
                ServerLivingEntityState.get(serverPlayer).put(Constants.CHANNELING_INTERRUPTED, Constants.ACTION_OFF);
            }
        });
    }
}

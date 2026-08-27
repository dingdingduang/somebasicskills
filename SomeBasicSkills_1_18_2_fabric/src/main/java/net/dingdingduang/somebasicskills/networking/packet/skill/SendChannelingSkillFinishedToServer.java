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
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class SendChannelingSkillFinishedToServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "send_channeling_skill_finished_to_server");

    public static PacketByteBuf setupPacket(String skillID, float clientUserSkillIDDefaultChannelingTicks, int isChannelingAction, int isChannelingInterrupted, int timeSpentOnChanneling) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeString(skillID);
        packetToBeSent.writeFloat(clientUserSkillIDDefaultChannelingTicks);
        packetToBeSent.writeInt(isChannelingAction);
        packetToBeSent.writeInt(isChannelingInterrupted);
        packetToBeSent.writeInt(timeSpentOnChanneling);
        return packetToBeSent;
    }

    public static void toServerHandle(MinecraftServer server, ServerPlayerEntity serverPlayer, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final String SkillID = buf.readString();
        final float ClientUserSkillIDDefaultChannelingTime = buf.readFloat();
        final int IsChannelingAction = buf.readInt();
        final int IsChannelingInterrupted = buf.readInt();
        final int TimeSpentOnChanneling = buf.readInt();

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
                if (skill1.isActiveType() && skill1.getActiveSkillAction1() != null) {
                    skill1.getActiveSkillAction1().executeAction(serverPlayer);
//                    printInGameMsg("channel ticks: "+getSPlayerState().get(serverPlayer));
                }
            }
            else {
                //kick?
                NetworkingFetchMsgMethods.FetchPlayerMsgFromServer(serverPlayer, "Channeling Time #2 doesn't match with the current server!");
            }

            HashMap<LivingEntity, HashMap<String, Integer>> ServerLivingEntityState = getSLivingEntityState();
            if (ServerLivingEntityState.containsKey(serverPlayer) && ServerLivingEntityState.get(serverPlayer) != null) {
                ServerLivingEntityState.get(serverPlayer).put(Constants.IS_CHANNELING, IsChannelingAction);
                if (IsChannelingInterrupted == 1) {
                    ServerLivingEntityState.get(serverPlayer).put(Constants.CHANNELING_INTERRUPTED, IsChannelingInterrupted);
                }
                ServerLivingEntityState.get(serverPlayer).put(Constants.CHANNELING_TICKS, TimeSpentOnChanneling);
            }
        });
    }
}

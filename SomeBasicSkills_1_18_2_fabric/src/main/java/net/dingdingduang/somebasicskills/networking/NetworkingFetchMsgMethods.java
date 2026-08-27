package net.dingdingduang.somebasicskills.networking;

import net.dingdingduang.somebasicskills.networking.packet.*;
import net.dingdingduang.somebasicskills.networking.packet.skill.FetchPlayerStateKeyValueOnlyFromServer;
import net.minecraft.server.network.ServerPlayerEntity;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.*;

public class NetworkingFetchMsgMethods {
    //=====================================================
    //update player config file
    public static void FetchConfigKeyValToClientSide(ServerPlayerEntity sp1, String configName, String configDetailName, int configVal) {
        NetworkFabricGeneralMethods.sendToPlayer(FetchConfigKeyValOnlyFromServer.ID, FetchConfigKeyValOnlyFromServer.setupPacket(configName, configDetailName, configVal), sp1);
    }

    //update player entity status with corresponding value
    public static void FetchPlayerStatusToClientSide(ServerPlayerEntity sp1, String statusName, double amount) {
        NetworkFabricGeneralMethods.sendToPlayer(FetchStatusKeyValueOnlyFromServer.ID, FetchStatusKeyValueOnlyFromServer.setupPacket(statusName, amount), sp1);
    }


    //=====================================================
    //update player entity state IS_ACTION
    public static void FetchPlayerStateToClientSide(ServerPlayerEntity sp1, String stateName, int value) {
        NetworkFabricGeneralMethods.sendToPlayer(FetchPlayerStateKeyValueOnlyFromServer.ID, FetchPlayerStateKeyValueOnlyFromServer.setupPacket(stateName, value), sp1);
    }


    //=====================================================
    //transfer server data to client side when player login
    public static void FetchLoginDataToClientSide(ServerPlayerEntity sp1) {
        NetworkFabricGeneralMethods.sendToPlayer(FetchLoginDataFromServer.ID, FetchLoginDataFromServer.setupPacket(getGlobalPlayerSkillID2lvlMap().get(sp1)), sp1);
    }

    public static void FetchPlayerStatusMapToClientSide(ServerPlayerEntity sp1) {
        NetworkFabricGeneralMethods.sendToPlayer(FetchStatusFromServer.ID, FetchStatusFromServer.setupPacket(getSPlayerValue2BaseMultiplierMap().get(sp1)), sp1);
    }

    public static void FetchPlayerIsImmobilizedBooleanFromServer(ServerPlayerEntity sp1, boolean isImmobilized) {
        NetworkFabricGeneralMethods.sendToPlayer(FetchPlayerIsImmobilizedFromServer.ID, FetchPlayerIsImmobilizedFromServer.setupPacket(isImmobilized), sp1);
    }

    public static void FetchPlayerMsgFromServer(ServerPlayerEntity sp1, String msg) {
        NetworkFabricGeneralMethods.sendToPlayer(FetchStringMsgFromServer.ID, FetchStringMsgFromServer.setupPacket(msg), sp1);
    }

    public static void FetchPlayerResetLastKeyActionFromServer(ServerPlayerEntity sp1, String SkillID) {
        NetworkFabricGeneralMethods.sendToPlayer(FetchResetPlayerKeyActionFromServer.ID, FetchResetPlayerKeyActionFromServer.setupPacket(SkillID), sp1);
    }
}

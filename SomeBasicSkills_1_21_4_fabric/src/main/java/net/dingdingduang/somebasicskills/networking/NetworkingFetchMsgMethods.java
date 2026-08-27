package net.dingdingduang.somebasicskills.networking;

import net.dingdingduang.somebasicskills.networking.packet.*;
import net.dingdingduang.somebasicskills.networking.packet.skill.FetchPlayerStateKeyValueOnlyFromServer;
import net.minecraft.server.network.ServerPlayerEntity;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.*;

public class NetworkingFetchMsgMethods {
    //=====================================================
    //update player config file
    public static void FetchConfigKeyValToClientSide(ServerPlayerEntity sp1, String configName, String configDetailName, int configVal) {
        NetworkingGeneralMethods.sendToPlayer(new FetchConfigKeyValOnlyFromServer(configName, configDetailName, configVal), sp1);
    }

    //update player entity status with corresponding value
    public static void FetchPlayerStatusToClientSide(ServerPlayerEntity sp1, String statusName, double amount) {
        NetworkingGeneralMethods.sendToPlayer(new FetchStatusKeyValueOnlyFromServer(statusName, amount), sp1);
    }


    //=====================================================
    //update player entity state IS_ACTION
    public static void FetchPlayerStateToClientSide(ServerPlayerEntity sp1, String stateName, int value) {
        NetworkingGeneralMethods.sendToPlayer(new FetchPlayerStateKeyValueOnlyFromServer(stateName, value), sp1);
    }


    //=====================================================
    //transfer server data to client side when player login
    public static void FetchLoginDataToClientSide(ServerPlayerEntity sp1) {
        NetworkingGeneralMethods.sendToPlayer(new FetchLoginDataFromServer(getGlobalPlayerSkillID2lvlMap().get(sp1)), sp1);
    }

    public static void FetchPlayerStatusMapToClientSide(ServerPlayerEntity sp1) {
        NetworkingGeneralMethods.sendToPlayer(new FetchStatusFromServer(getSPlayerValue2BaseMultiplierMap().get(sp1)), sp1);
    }

    public static void FetchPlayerIsImmobilizedBooleanFromServer(ServerPlayerEntity sp1, boolean isImmobilized) {
        NetworkingGeneralMethods.sendToPlayer(new FetchPlayerIsImmobilizedFromServer(isImmobilized), sp1);
    }

    public static void FetchPlayerMsgFromServer(ServerPlayerEntity sp1, String msg) {
        NetworkingGeneralMethods.sendToPlayer(new FetchStringMsgFromServer(msg), sp1);
    }

    public static void FetchPlayerResetLastKeyActionFromServer(ServerPlayerEntity sp1, String SkillID) {
        NetworkingGeneralMethods.sendToPlayer(new FetchResetPlayerKeyActionFromServer(SkillID), sp1);
    }
}

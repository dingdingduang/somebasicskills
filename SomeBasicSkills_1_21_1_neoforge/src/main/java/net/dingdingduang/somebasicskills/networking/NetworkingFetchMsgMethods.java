package net.dingdingduang.somebasicskills.networking;

import net.dingdingduang.somebasicskills.networking.packet.*;
import net.dingdingduang.somebasicskills.networking.packet.skill.FetchPlayerStateKeyValueOnlyFromServer;
import net.minecraft.server.level.ServerPlayer;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.*;

public class NetworkingFetchMsgMethods {
    //=====================================================
    //update player config file
    public static void FetchConfigKeyValToClientSide(ServerPlayer sp1, String configName, String configDetailName, int configVal) {
        NetworkingMsgInitialization.sendToPlayer(new FetchConfigKeyValOnlyFromServer(configName, configDetailName, configVal), sp1);
    }

    //update player entity status with corresponding value
    public static void FetchPlayerStatusToClientSide(ServerPlayer sp1, String statusName, double amount) {
        NetworkingMsgInitialization.sendToPlayer(new FetchStatusKeyValueOnlyFromServer(statusName, amount), sp1);
    }


    //=====================================================
    //update player entity state IS_ACTION
    public static void FetchPlayerStateToClientSide(ServerPlayer sp1, String stateName, int value) {
        NetworkingMsgInitialization.sendToPlayer(new FetchPlayerStateKeyValueOnlyFromServer(stateName, value), sp1);
    }

//    public static void FetchPlayerStateIncrementAmountToClientSide(ServerPlayer sp1, String stateName, int value) {
//        NetworkingMsgInitialization.sendToPlayer(new FetchPlayerStateKeyIncrementValueOnlyFromServer(stateName, value), sp1);
//    }


    //=====================================================
    //transfer server data to client side when player login
    public static void FetchLoginDataToClientSide(ServerPlayer sp1) {
        NetworkingMsgInitialization.sendToPlayer(new FetchLoginDataFromServer(getGlobalPlayerSkillID2lvlMap().get(sp1)), sp1);
    }

//    public static void FetchLogoutDataToClientSide(ServerPlayer sp1) {
//        NetworkingMsgInitialization.sendToPlayer(new FetchLogoutDataFromServer(), sp1);
//    }

//    public static void FetchQuickSlotSettingToClientSide(ServerPlayer sp1) {
//        NetworkingMsgInitialization.sendToPlayer(new FetchQuickSlotSettingFromServer(getSPlayerKey2SkillID().get(sp1), getSPlayerSkillID2Key().get(sp1)), sp1);
//    }
//
//    public static void FetchKeyComboSettingToClientSide(ServerPlayer sp1) {
//        NetworkingMsgInitialization.sendToPlayer(new FetchKeyComboSettingFromServer(getSPlayerKeyCombo2SkillID().get(sp1), getSPlayerSkillID2KeyCombo().get(sp1)), sp1);
//    }

    public static void FetchPlayerStatusMapToClientSide(ServerPlayer sp1) {
        NetworkingMsgInitialization.sendToPlayer(new FetchStatusFromServer(getSPlayerValue2BaseMultiplierMap().get(sp1)), sp1);
    }

    public static void FetchPlayerIsImmobilizedBooleanFromServer(ServerPlayer sp1, boolean isImmobilized) {
        NetworkingMsgInitialization.sendToPlayer(new FetchPlayerIsImmobilizedFromServer(isImmobilized), sp1);
    }

    public static void FetchPlayerMsgFromServer(ServerPlayer sp1, String msg) {
        NetworkingMsgInitialization.sendToPlayer(new FetchStringMsgFromServer(msg), sp1);
    }

    public static void FetchPlayerResetLastKeyActionFromServer(ServerPlayer sp1, String SkillID) {
        NetworkingMsgInitialization.sendToPlayer(new FetchResetPlayerKeyActionFromServer(SkillID), sp1);
    }
}

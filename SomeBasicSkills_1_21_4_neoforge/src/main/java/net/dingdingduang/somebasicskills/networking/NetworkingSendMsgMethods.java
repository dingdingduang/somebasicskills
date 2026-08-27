package net.dingdingduang.somebasicskills.networking;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.packet.SendConfigKeyValOnlyToServer;
import net.dingdingduang.somebasicskills.networking.packet.SendResetPlayerStateRequestToServer;
import net.dingdingduang.somebasicskills.networking.packet.screen.SendAddBtnActionToServer;
import net.dingdingduang.somebasicskills.networking.packet.screen.SendMaxBtnActionToServer;
import net.dingdingduang.somebasicskills.networking.packet.screen.SendSubBtnActionToServer;
import net.dingdingduang.somebasicskills.networking.packet.skill.*;

public class NetworkingSendMsgMethods {
    //=====================================================
    //send config file content to server
    public static void SendConfigKeyValFromClientSideToServer(String ConfigOptionName, String ConfigDetailOptionName, int ConfigDetailOptionVal, boolean isConfigDetailOptionBoolean) {
        NetworkingMsgInitialization.sendToServer(new SendConfigKeyValOnlyToServer(ConfigOptionName, ConfigDetailOptionName, ConfigDetailOptionVal,isConfigDetailOptionBoolean));
    }

    public static void SendResetPlayerStateRequestToServerSide() {
        NetworkingMsgInitialization.sendToServer(new SendResetPlayerStateRequestToServer());
    }

    //Send Skill Info Screen Packet
    //Skill Level to Server
    public static void SendPlayerSkillLevelFromClientSideToServer(String SkillID, int SkillLevel) {
        NetworkingMsgInitialization.sendToServer(new SendSkillLevelKeyValueOnlyToServer(SkillID, SkillLevel));
    }


    //execute Add Btn Action on server side
    public static void SendAddBtnActionFromClientToServer(String SkillID) {
        NetworkingMsgInitialization.sendToServer(new SendAddBtnActionToServer(SkillID));
    }
    //execute Sub Btn Action on server side
    public static void SendSubBtnActionFromClientToServer(String SkillID) {
        NetworkingMsgInitialization.sendToServer(new SendSubBtnActionToServer(SkillID));
    }
    //execute Max Btn Action on server side
    public static void SendMaxBtnActionFromClientToServer(String SkillID, int loopTimes) {
        NetworkingMsgInitialization.sendToServer(new SendMaxBtnActionToServer(SkillID, loopTimes));
    }


    //=====================================================
    //Send Keyboard Setting Packet to Server
//    public static void SendQuickSlotSettingFromClientSideToServer(HashMap<Integer, String> Key2SkillIDMap) {
//        NetworkingMsgInitialization.sendToServer(new SendQuickSlotSettingToServer(Key2SkillIDMap));
//    }
//
//    public static void SendKeyComboSettingFromClientSideToServer(HashMap<ArrayList<Integer>, String> KeyCombo2SkillIDMap) {
//        NetworkingMsgInitialization.sendToServer(new SendKeyComboSettingToServer(KeyCombo2SkillIDMap));
//    }


    //=====================================================
    //Send Keyboard Listening Skill Activation Packet
    public static void SendSkillActionFromClientSideToServer(String SkillID, float clientUserSkillIDDefaultSkillCDTime) {
        NetworkingMsgInitialization.sendToServer(new SendSkillActionToServer(SkillID, clientUserSkillIDDefaultSkillCDTime));
    }

    public static void SendSkillActionWithLockOnFromClientSideToServer(String SkillID, float clientUserSkillIDDefaultSkillCDTime, float facingAngle) {
        NetworkingMsgInitialization.sendToServer(new SendSkillActionWithLockOnToServer(SkillID, clientUserSkillIDDefaultSkillCDTime, facingAngle));
    }

    public static void SendSkillPassiveActionFromClientSideToServer(String SkillID) {
        NetworkingMsgInitialization.sendToServer(new SendSkillPassiveActionToServer(SkillID));
    }

    public static void SendSkillPassiveActionTwoFromClientSideToServer(String SkillID) {
        NetworkingMsgInitialization.sendToServer(new SendSkillPassiveActionTwoToServer(SkillID));
    }



    //=====================================================
    //Send Channeling Action Packet to Server
    public static void SendPlayerStateDataFromClientSideToServer(String stateName, int value) {
        NetworkingMsgInitialization.sendToServer(new SendPlayerStateKeyValueOnlyToServer(stateName, value));
    }

    public static void SendPlayerStateKeyIncrementAmountFromClientSideToServer(String stateName, int value) {
        NetworkingMsgInitialization.sendToServer(new SendPlayerStateKeyIncrementValueOnlyToServer(stateName, value));
    }

    public static void SendChannelingActionWithSkillIDFromClientSideToServer(String SkillID, float clientUserSkillIDDefaultChannelingTicks) {
        NetworkingMsgInitialization.sendToServer(new SendChannelingSkillToServer(SkillID, clientUserSkillIDDefaultChannelingTicks));
    }

    public static void SendChannelingFinishedActionWithSkillIDFromClientSideToServer(String SkillID, float clientUserSkillIDDefaultChannelingTicks, int isChannelingAction, int isChannelingInterrupted, int timeSpentOnChanneling) {
        NetworkingMsgInitialization.sendToServer(new SendChannelingSkillFinishedToServer(SkillID, clientUserSkillIDDefaultChannelingTicks, isChannelingAction, isChannelingInterrupted, timeSpentOnChanneling));
    }
}

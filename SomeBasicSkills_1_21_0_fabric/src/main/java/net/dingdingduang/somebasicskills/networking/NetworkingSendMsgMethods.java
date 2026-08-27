package net.dingdingduang.somebasicskills.networking;

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
        NetworkingGeneralMethods.sendToServer(new SendConfigKeyValOnlyToServer(ConfigOptionName, ConfigDetailOptionName, ConfigDetailOptionVal,isConfigDetailOptionBoolean));
    }

    public static void SendResetPlayerStateRequestToServerSide() {
        NetworkingGeneralMethods.sendToServer(new SendResetPlayerStateRequestToServer());
    }

    //Send Skill Info Screen Packet
    //Skill World to Server
    public static void SendPlayerSkillLevelFromClientSideToServer(String SkillID, int SkillLevel) {
        NetworkingGeneralMethods.sendToServer(new SendSkillLevelKeyValueOnlyToServer(SkillID, SkillLevel));
    }


    //execute Add Btn Action on server side
    public static void SendAddBtnActionFromClientToServer(String SkillID) {
        NetworkingGeneralMethods.sendToServer(new SendAddBtnActionToServer(SkillID));
    }
    //execute Sub Btn Action on server side
    public static void SendSubBtnActionFromClientToServer(String SkillID) {
        NetworkingGeneralMethods.sendToServer(new SendSubBtnActionToServer(SkillID));
    }
    //execute Max Btn Action on server side
    public static void SendMaxBtnActionFromClientToServer(String SkillID, int loopTimes) {
        NetworkingGeneralMethods.sendToServer(new SendMaxBtnActionToServer(SkillID, loopTimes));
    }

    //=====================================================
    //Send Keyboard Listening Skill Activation Packet
    public static void SendSkillActionFromClientSideToServer(String SkillID, float clientUserSkillIDDefaultSkillCDTime) {
        NetworkingGeneralMethods.sendToServer(new SendSkillActionToServer(SkillID, clientUserSkillIDDefaultSkillCDTime));
    }

    public static void SendSkillActionWithLockOnFromClientSideToServer(String SkillID, float clientUserSkillIDDefaultSkillCDTime, float facingAngle) {
        NetworkingGeneralMethods.sendToServer(new SendSkillActionWithLockOnToServer(SkillID, clientUserSkillIDDefaultSkillCDTime, facingAngle));
    }

    public static void SendSkillPassiveActionFromClientSideToServer(String SkillID) {
        NetworkingGeneralMethods.sendToServer(new SendSkillPassiveActionToServer(SkillID));
    }

    public static void SendSkillPassiveActionTwoFromClientSideToServer(String SkillID) {
        NetworkingGeneralMethods.sendToServer(new SendSkillPassiveActionTwoToServer(SkillID));
    }



    //=====================================================
    //Send Channeling Action Packet to Server
    public static void SendPlayerStateDataFromClientSideToServer(String stateName, int value) {
        NetworkingGeneralMethods.sendToServer(new SendPlayerStateKeyValueOnlyToServer(stateName, value));
    }

    public static void SendPlayerStateKeyIncrementAmountFromClientSideToServer(String stateName, int value) {
        NetworkingGeneralMethods.sendToServer(new SendPlayerStateKeyIncrementValueOnlyToServer(stateName, value));
    }

    public static void SendChannelingActionWithSkillIDFromClientSideToServer(String SkillID, float clientUserSkillIDDefaultChannelingTicks) {
        NetworkingGeneralMethods.sendToServer(new SendChannelingSkillToServer(SkillID, clientUserSkillIDDefaultChannelingTicks));
    }

    public static void SendChannelingFinishedActionWithSkillIDFromClientSideToServer(String SkillID, float clientUserSkillIDDefaultChannelingTicks, int isChannelingAction, int isChannelingInterrupted, int timeSpentOnChanneling) {
        NetworkingGeneralMethods.sendToServer(new SendChannelingSkillFinishedToServer(SkillID, clientUserSkillIDDefaultChannelingTicks, isChannelingAction, isChannelingInterrupted, timeSpentOnChanneling));
    }
}

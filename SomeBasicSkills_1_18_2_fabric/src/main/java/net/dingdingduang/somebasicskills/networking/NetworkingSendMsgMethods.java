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
        NetworkFabricGeneralMethods.sendToServer(SendConfigKeyValOnlyToServer.ID, SendConfigKeyValOnlyToServer.setupPacket(ConfigOptionName, ConfigDetailOptionName, ConfigDetailOptionVal,isConfigDetailOptionBoolean));
    }

    public static void SendResetPlayerStateRequestToServerSide() {
        NetworkFabricGeneralMethods.sendToServer(SendResetPlayerStateRequestToServer.ID, SendResetPlayerStateRequestToServer.setupPacket());
    }

    //Send Skill Info Screen Packet
    //Skill World to Server
    public static void SendPlayerSkillLevelFromClientSideToServer(String SkillID, int SkillLevel) {
        NetworkFabricGeneralMethods.sendToServer(SendSkillLevelKeyValueOnlyToServer.ID, SendSkillLevelKeyValueOnlyToServer.setupPacket(SkillID, SkillLevel));
    }


    //execute Add Btn Action on server side
    public static void SendAddBtnActionFromClientToServer(String SkillID) {
        NetworkFabricGeneralMethods.sendToServer(SendAddBtnActionToServer.ID, SendAddBtnActionToServer.setupPacket(SkillID));
    }
    //execute Sub Btn Action on server side
    public static void SendSubBtnActionFromClientToServer(String SkillID) {
        NetworkFabricGeneralMethods.sendToServer(SendSubBtnActionToServer.ID, SendSubBtnActionToServer.setupPacket(SkillID));
    }
    //execute Max Btn Action on server side
    public static void SendMaxBtnActionFromClientToServer(String SkillID, int loopTimes) {
        NetworkFabricGeneralMethods.sendToServer(SendMaxBtnActionToServer.ID, SendMaxBtnActionToServer.setupPacket(SkillID, loopTimes));
    }

    //=====================================================
    //Send Keyboard Listening Skill Activation Packet
    public static void SendSkillActionFromClientSideToServer(String SkillID, float clientUserSkillIDDefaultSkillCDTime) {
        NetworkFabricGeneralMethods.sendToServer(SendSkillActionToServer.ID, SendSkillActionToServer.setupPacket(SkillID, clientUserSkillIDDefaultSkillCDTime));
    }

    public static void SendSkillActionWithLockOnFromClientSideToServer(String SkillID, float clientUserSkillIDDefaultSkillCDTime, float facingAngle) {
        NetworkFabricGeneralMethods.sendToServer(SendSkillActionWithLockOnToServer.ID, SendSkillActionWithLockOnToServer.setupPacket(SkillID, clientUserSkillIDDefaultSkillCDTime, facingAngle));
    }

    public static void SendSkillPassiveActionFromClientSideToServer(String SkillID) {
        NetworkFabricGeneralMethods.sendToServer(SendSkillPassiveActionToServer.ID, SendSkillPassiveActionToServer.setupPacket(SkillID));
    }

    public static void SendSkillPassiveActionTwoFromClientSideToServer(String SkillID) {
        NetworkFabricGeneralMethods.sendToServer(SendSkillPassiveActionTwoToServer.ID, SendSkillPassiveActionTwoToServer.setupPacket(SkillID));
    }



    //=====================================================
    //Send Channeling Action Packet to Server
    public static void SendPlayerStateDataFromClientSideToServer(String stateName, int value) {
        NetworkFabricGeneralMethods.sendToServer(SendPlayerStateKeyValueOnlyToServer.ID, SendPlayerStateKeyValueOnlyToServer.setupPacket(stateName, value));
    }

    public static void SendPlayerStateKeyIncrementAmountFromClientSideToServer(String stateName, int value) {
        NetworkFabricGeneralMethods.sendToServer(SendPlayerStateKeyIncrementValueOnlyToServer.ID, SendPlayerStateKeyIncrementValueOnlyToServer.setupPacket(stateName, value));
    }

    public static void SendChannelingActionWithSkillIDFromClientSideToServer(String SkillID, float clientUserSkillIDDefaultChannelingTicks) {
        NetworkFabricGeneralMethods.sendToServer(SendChannelingSkillToServer.ID, SendChannelingSkillToServer.setupPacket(SkillID, clientUserSkillIDDefaultChannelingTicks));
    }

    public static void SendChannelingFinishedActionWithSkillIDFromClientSideToServer(String SkillID, float clientUserSkillIDDefaultChannelingTicks, int isChannelingAction, int isChannelingInterrupted, int timeSpentOnChanneling) {
        NetworkFabricGeneralMethods.sendToServer(SendChannelingSkillFinishedToServer.ID, SendChannelingSkillFinishedToServer.setupPacket(SkillID, clientUserSkillIDDefaultChannelingTicks, isChannelingAction, isChannelingInterrupted, timeSpentOnChanneling));
    }
}

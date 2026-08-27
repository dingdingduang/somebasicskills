package net.dingdingduang.somebasicskills.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.dingdingduang.somebasicskills.networking.packet.*;
import net.dingdingduang.somebasicskills.networking.packet.screen.SendAddBtnActionToServer;
import net.dingdingduang.somebasicskills.networking.packet.screen.SendMaxBtnActionToServer;
import net.dingdingduang.somebasicskills.networking.packet.screen.SendSubBtnActionToServer;
import net.dingdingduang.somebasicskills.networking.packet.skill.*;

public class NetworkPacketRegister {
    public static void registerClientPacket() {
        ClientPlayNetworking.registerGlobalReceiver(FetchIncreaseSkillCooldownFromServer.ID, FetchIncreaseSkillCooldownFromServer::toClientHandle);
        ClientPlayNetworking.registerGlobalReceiver(FetchPlayerStateKeyValueOnlyFromServer.ID, FetchPlayerStateKeyValueOnlyFromServer::toClientHandle);
        ClientPlayNetworking.registerGlobalReceiver(FetchPlayerStateMapFromServer.ID, FetchPlayerStateMapFromServer::toClientHandle);
        ClientPlayNetworking.registerGlobalReceiver(FetchSetSkillCooldownFromServer.ID, FetchSetSkillCooldownFromServer::toClientHandle);
        ClientPlayNetworking.registerGlobalReceiver(FetchStartSkillCooldownFromServer.ID, FetchStartSkillCooldownFromServer::toClientHandle);
        ClientPlayNetworking.registerGlobalReceiver(FetchConfigKeyValOnlyFromServer.ID, FetchConfigKeyValOnlyFromServer::toClientHandle);
        ClientPlayNetworking.registerGlobalReceiver(FetchLoginDataFromServer.ID, FetchLoginDataFromServer::toClientHandle);
        ClientPlayNetworking.registerGlobalReceiver(FetchPlayerIsImmobilizedFromServer.ID, FetchPlayerIsImmobilizedFromServer::toClientHandle);
        ClientPlayNetworking.registerGlobalReceiver(FetchResetPlayerKeyActionFromServer.ID, FetchResetPlayerKeyActionFromServer::toClientHandle);
        ClientPlayNetworking.registerGlobalReceiver(FetchStatusFromServer.ID, FetchStatusFromServer::toClientHandle);
        ClientPlayNetworking.registerGlobalReceiver(FetchStatusKeyValueOnlyFromServer.ID, FetchStatusKeyValueOnlyFromServer::toClientHandle);
        ClientPlayNetworking.registerGlobalReceiver(FetchStringMsgFromServer.ID, FetchStringMsgFromServer::toClientHandle);
    }

    public static void registerServerPacket() {
        ServerPlayNetworking.registerGlobalReceiver(SendAddBtnActionToServer.ID, SendAddBtnActionToServer::toServerHandle);
        ServerPlayNetworking.registerGlobalReceiver(SendMaxBtnActionToServer.ID, SendMaxBtnActionToServer::toServerHandle);
        ServerPlayNetworking.registerGlobalReceiver(SendSubBtnActionToServer.ID, SendSubBtnActionToServer::toServerHandle);
        ServerPlayNetworking.registerGlobalReceiver(SendChannelingSkillFinishedToServer.ID, SendChannelingSkillFinishedToServer::toServerHandle);
        ServerPlayNetworking.registerGlobalReceiver(SendChannelingSkillToServer.ID, SendChannelingSkillToServer::toServerHandle);
        ServerPlayNetworking.registerGlobalReceiver(SendPlayerStateKeyIncrementValueOnlyToServer.ID, SendPlayerStateKeyIncrementValueOnlyToServer::toServerHandle);
        ServerPlayNetworking.registerGlobalReceiver(SendPlayerStateKeyValueOnlyToServer.ID, SendPlayerStateKeyValueOnlyToServer::toServerHandle);
        ServerPlayNetworking.registerGlobalReceiver(SendPlayerStateMapToServer.ID, SendPlayerStateMapToServer::toServerHandle);
        ServerPlayNetworking.registerGlobalReceiver(SendSkillActionToServer.ID, SendSkillActionToServer::toServerHandle);
        ServerPlayNetworking.registerGlobalReceiver(SendSkillActionWithLockOnToServer.ID, SendSkillActionWithLockOnToServer::toServerHandle);
        ServerPlayNetworking.registerGlobalReceiver(SendSkillLevelKeyValueOnlyToServer.ID, SendSkillLevelKeyValueOnlyToServer::toServerHandle);
        ServerPlayNetworking.registerGlobalReceiver(SendSkillLevelMapToServer.ID, SendSkillLevelMapToServer::toServerHandle);
        ServerPlayNetworking.registerGlobalReceiver(SendSkillPassiveActionToServer.ID, SendSkillPassiveActionToServer::toServerHandle);
        ServerPlayNetworking.registerGlobalReceiver(SendSkillPassiveActionTwoToServer.ID, SendSkillPassiveActionTwoToServer::toServerHandle);
        ServerPlayNetworking.registerGlobalReceiver(SendConfigKeyValOnlyToServer.ID, SendConfigKeyValOnlyToServer::toServerHandle);
        ServerPlayNetworking.registerGlobalReceiver(SendResetPlayerStateRequestToServer.ID, SendResetPlayerStateRequestToServer::toServerHandle);
        ServerPlayNetworking.registerGlobalReceiver(SendStatusToServer.ID, SendStatusToServer::toServerHandle);
    }
}

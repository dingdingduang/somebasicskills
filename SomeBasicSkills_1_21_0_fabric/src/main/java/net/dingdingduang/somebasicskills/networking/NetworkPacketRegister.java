package net.dingdingduang.somebasicskills.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.dingdingduang.somebasicskills.networking.packet.*;
import net.dingdingduang.somebasicskills.networking.packet.screen.SendAddBtnActionToServer;
import net.dingdingduang.somebasicskills.networking.packet.screen.SendMaxBtnActionToServer;
import net.dingdingduang.somebasicskills.networking.packet.screen.SendSubBtnActionToServer;
import net.dingdingduang.somebasicskills.networking.packet.skill.*;

public class NetworkPacketRegister {
    public static void registerClientPacket() {
        PayloadTypeRegistry.playS2C().register(FetchIncreaseSkillCooldownFromServer.TYPE, FetchIncreaseSkillCooldownFromServer.STREAM_CODEC);
        ClientPlayNetworking.registerGlobalReceiver(FetchIncreaseSkillCooldownFromServer.TYPE, FetchIncreaseSkillCooldownFromServer::handle);

        PayloadTypeRegistry.playS2C().register(FetchPlayerStateKeyValueOnlyFromServer.TYPE, FetchPlayerStateKeyValueOnlyFromServer.STREAM_CODEC);
        ClientPlayNetworking.registerGlobalReceiver(FetchPlayerStateKeyValueOnlyFromServer.TYPE, FetchPlayerStateKeyValueOnlyFromServer::handle);

        PayloadTypeRegistry.playS2C().register(FetchPlayerStateMapFromServer.TYPE, FetchPlayerStateMapFromServer.STREAM_CODEC);
        ClientPlayNetworking.registerGlobalReceiver(FetchPlayerStateMapFromServer.TYPE, FetchPlayerStateMapFromServer::handle);

        PayloadTypeRegistry.playS2C().register(FetchSetSkillCooldownFromServer.TYPE, FetchSetSkillCooldownFromServer.STREAM_CODEC);
        ClientPlayNetworking.registerGlobalReceiver(FetchSetSkillCooldownFromServer.TYPE, FetchSetSkillCooldownFromServer::handle);

        PayloadTypeRegistry.playS2C().register(FetchStartSkillCooldownFromServer.TYPE, FetchStartSkillCooldownFromServer.STREAM_CODEC);
        ClientPlayNetworking.registerGlobalReceiver(FetchStartSkillCooldownFromServer.TYPE, FetchStartSkillCooldownFromServer::handle);

        PayloadTypeRegistry.playS2C().register(FetchConfigKeyValOnlyFromServer.TYPE, FetchConfigKeyValOnlyFromServer.STREAM_CODEC);
        ClientPlayNetworking.registerGlobalReceiver(FetchConfigKeyValOnlyFromServer.TYPE, FetchConfigKeyValOnlyFromServer::handle);

        PayloadTypeRegistry.playS2C().register(FetchLoginDataFromServer.TYPE, FetchLoginDataFromServer.STREAM_CODEC);
        ClientPlayNetworking.registerGlobalReceiver(FetchLoginDataFromServer.TYPE, FetchLoginDataFromServer::handle);

        PayloadTypeRegistry.playS2C().register(FetchPlayerIsImmobilizedFromServer.TYPE, FetchPlayerIsImmobilizedFromServer.STREAM_CODEC);
        ClientPlayNetworking.registerGlobalReceiver(FetchPlayerIsImmobilizedFromServer.TYPE, FetchPlayerIsImmobilizedFromServer::handle);

        PayloadTypeRegistry.playS2C().register(FetchResetPlayerKeyActionFromServer.TYPE, FetchResetPlayerKeyActionFromServer.STREAM_CODEC);
        ClientPlayNetworking.registerGlobalReceiver(FetchResetPlayerKeyActionFromServer.TYPE, FetchResetPlayerKeyActionFromServer::handle);

        PayloadTypeRegistry.playS2C().register(FetchStatusFromServer.TYPE, FetchStatusFromServer.STREAM_CODEC);
        ClientPlayNetworking.registerGlobalReceiver(FetchStatusFromServer.TYPE, FetchStatusFromServer::handle);

        PayloadTypeRegistry.playS2C().register(FetchStatusKeyValueOnlyFromServer.TYPE, FetchStatusKeyValueOnlyFromServer.STREAM_CODEC);
        ClientPlayNetworking.registerGlobalReceiver(FetchStatusKeyValueOnlyFromServer.TYPE, FetchStatusKeyValueOnlyFromServer::handle);

        PayloadTypeRegistry.playS2C().register(FetchStringMsgFromServer.TYPE, FetchStringMsgFromServer.STREAM_CODEC);
        ClientPlayNetworking.registerGlobalReceiver(FetchStringMsgFromServer.TYPE, FetchStringMsgFromServer::handle);
    }

    public static void registerServerPacket() {
        PayloadTypeRegistry.playC2S().register(SendAddBtnActionToServer.TYPE, SendAddBtnActionToServer.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SendAddBtnActionToServer.TYPE, SendAddBtnActionToServer::handle);

        PayloadTypeRegistry.playC2S().register(SendMaxBtnActionToServer.TYPE, SendMaxBtnActionToServer.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SendMaxBtnActionToServer.TYPE, SendMaxBtnActionToServer::handle);

        PayloadTypeRegistry.playC2S().register(SendSubBtnActionToServer.TYPE, SendSubBtnActionToServer.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SendSubBtnActionToServer.TYPE, SendSubBtnActionToServer::handle);

        PayloadTypeRegistry.playC2S().register(SendChannelingSkillFinishedToServer.TYPE, SendChannelingSkillFinishedToServer.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SendChannelingSkillFinishedToServer.TYPE, SendChannelingSkillFinishedToServer::handle);

        PayloadTypeRegistry.playC2S().register(SendChannelingSkillToServer.TYPE, SendChannelingSkillToServer.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SendChannelingSkillToServer.TYPE, SendChannelingSkillToServer::handle);

        PayloadTypeRegistry.playC2S().register(SendPlayerStateKeyIncrementValueOnlyToServer.TYPE, SendPlayerStateKeyIncrementValueOnlyToServer.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SendPlayerStateKeyIncrementValueOnlyToServer.TYPE, SendPlayerStateKeyIncrementValueOnlyToServer::handle);

        PayloadTypeRegistry.playC2S().register(SendPlayerStateKeyValueOnlyToServer.TYPE, SendPlayerStateKeyValueOnlyToServer.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SendPlayerStateKeyValueOnlyToServer.TYPE, SendPlayerStateKeyValueOnlyToServer::handle);

        PayloadTypeRegistry.playC2S().register(SendPlayerStateMapToServer.TYPE, SendPlayerStateMapToServer.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SendPlayerStateMapToServer.TYPE, SendPlayerStateMapToServer::handle);

        PayloadTypeRegistry.playC2S().register(SendSkillActionToServer.TYPE, SendSkillActionToServer.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SendSkillActionToServer.TYPE, SendSkillActionToServer::handle);

        PayloadTypeRegistry.playC2S().register(SendSkillActionWithLockOnToServer.TYPE, SendSkillActionWithLockOnToServer.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SendSkillActionWithLockOnToServer.TYPE, SendSkillActionWithLockOnToServer::handle);

        PayloadTypeRegistry.playC2S().register(SendSkillLevelKeyValueOnlyToServer.TYPE, SendSkillLevelKeyValueOnlyToServer.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SendSkillLevelKeyValueOnlyToServer.TYPE, SendSkillLevelKeyValueOnlyToServer::handle);

        PayloadTypeRegistry.playC2S().register(SendSkillLevelMapToServer.TYPE, SendSkillLevelMapToServer.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SendSkillLevelMapToServer.TYPE, SendSkillLevelMapToServer::handle);

        PayloadTypeRegistry.playC2S().register(SendSkillPassiveActionToServer.TYPE, SendSkillPassiveActionToServer.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SendSkillPassiveActionToServer.TYPE, SendSkillPassiveActionToServer::handle);

        PayloadTypeRegistry.playC2S().register(SendSkillPassiveActionTwoToServer.TYPE, SendSkillPassiveActionTwoToServer.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SendSkillPassiveActionTwoToServer.TYPE, SendSkillPassiveActionTwoToServer::handle);

        PayloadTypeRegistry.playC2S().register(SendConfigKeyValOnlyToServer.TYPE, SendConfigKeyValOnlyToServer.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SendConfigKeyValOnlyToServer.TYPE, SendConfigKeyValOnlyToServer::handle);

        PayloadTypeRegistry.playC2S().register(SendResetPlayerStateRequestToServer.TYPE, SendResetPlayerStateRequestToServer.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SendResetPlayerStateRequestToServer.TYPE, SendResetPlayerStateRequestToServer::handle);

        PayloadTypeRegistry.playC2S().register(SendStatusToServer.TYPE, SendStatusToServer.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SendStatusToServer.TYPE, SendStatusToServer::handle);
    }
}

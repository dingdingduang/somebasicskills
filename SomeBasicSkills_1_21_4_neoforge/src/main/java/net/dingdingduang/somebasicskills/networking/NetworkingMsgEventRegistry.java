package net.dingdingduang.somebasicskills.networking;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.packet.*;
import net.dingdingduang.somebasicskills.networking.packet.screen.*;
import net.dingdingduang.somebasicskills.networking.packet.skill.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NetworkingMsgEventRegistry {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar myRegistrar = event.registrar(Constants.MOD_ID);
        // on network or main thread?

        registerToServerPacketConfiguration(myRegistrar);
        registerToClientPacketConfiguration(myRegistrar);
    }




    private static void registerToServerPacketConfiguration(PayloadRegistrar payloadRegistrar) {
        payloadRegistrar.playToServer(SendStatusToServer.TYPE, SendStatusToServer.STREAM_CODEC, SendStatusToServer::handle);
        payloadRegistrar.playToServer(SendAddBtnActionToServer.TYPE, SendAddBtnActionToServer.STREAM_CODEC, SendAddBtnActionToServer::handle);
        payloadRegistrar.playToServer(SendMaxBtnActionToServer.TYPE, SendMaxBtnActionToServer.STREAM_CODEC, SendMaxBtnActionToServer::handle);
        payloadRegistrar.playToServer(SendSubBtnActionToServer.TYPE, SendSubBtnActionToServer.STREAM_CODEC, SendSubBtnActionToServer::handle);
        payloadRegistrar.playToServer(SendChannelingSkillFinishedToServer.TYPE, SendChannelingSkillFinishedToServer.STREAM_CODEC, SendChannelingSkillFinishedToServer::handle);
        payloadRegistrar.playToServer(SendChannelingSkillToServer.TYPE, SendChannelingSkillToServer.STREAM_CODEC, SendChannelingSkillToServer::handle);
        payloadRegistrar.playToServer(SendPlayerStateKeyIncrementValueOnlyToServer.TYPE, SendPlayerStateKeyIncrementValueOnlyToServer.STREAM_CODEC, SendPlayerStateKeyIncrementValueOnlyToServer::handle);
        payloadRegistrar.playToServer(SendPlayerStateKeyValueOnlyToServer.TYPE, SendPlayerStateKeyValueOnlyToServer.STREAM_CODEC, SendPlayerStateKeyValueOnlyToServer::handle);
        payloadRegistrar.playToServer(SendPlayerStateMapToServer.TYPE, SendPlayerStateMapToServer.STREAM_CODEC, SendPlayerStateMapToServer::handle);
        payloadRegistrar.playToServer(SendSkillActionToServer.TYPE, SendSkillActionToServer.STREAM_CODEC, SendSkillActionToServer::handle);
        payloadRegistrar.playToServer(SendSkillActionWithLockOnToServer.TYPE, SendSkillActionWithLockOnToServer.STREAM_CODEC, SendSkillActionWithLockOnToServer::handle);
        payloadRegistrar.playToServer(SendSkillLevelKeyValueOnlyToServer.TYPE, SendSkillLevelKeyValueOnlyToServer.STREAM_CODEC, SendSkillLevelKeyValueOnlyToServer::handle);
        payloadRegistrar.playToServer(SendSkillLevelMapToServer.TYPE, SendSkillLevelMapToServer.STREAM_CODEC, SendSkillLevelMapToServer::handle);
        payloadRegistrar.playToServer(SendSkillPassiveActionToServer.TYPE, SendSkillPassiveActionToServer.STREAM_CODEC, SendSkillPassiveActionToServer::handle);
        payloadRegistrar.playToServer(SendSkillPassiveActionTwoToServer.TYPE, SendSkillPassiveActionTwoToServer.STREAM_CODEC, SendSkillPassiveActionTwoToServer::handle);
        payloadRegistrar.playToServer(SendConfigKeyValOnlyToServer.TYPE, SendConfigKeyValOnlyToServer.STREAM_CODEC, SendConfigKeyValOnlyToServer::handle);
        payloadRegistrar.playToServer(SendResetPlayerStateRequestToServer.TYPE, SendResetPlayerStateRequestToServer.STREAM_CODEC, SendResetPlayerStateRequestToServer::handle);
    }

    private static void registerToClientPacketConfiguration(PayloadRegistrar payloadRegistrar) {
        payloadRegistrar.playToClient(FetchIncreaseSkillCooldownFromServer.TYPE, FetchIncreaseSkillCooldownFromServer.STREAM_CODEC, FetchIncreaseSkillCooldownFromServer::handle);
        payloadRegistrar.playToClient(FetchPlayerStateKeyValueOnlyFromServer.TYPE, FetchPlayerStateKeyValueOnlyFromServer.STREAM_CODEC, FetchPlayerStateKeyValueOnlyFromServer::handle);
        payloadRegistrar.playToClient(FetchPlayerStateMapFromServer.TYPE, FetchPlayerStateMapFromServer.STREAM_CODEC, FetchPlayerStateMapFromServer::handle);
        payloadRegistrar.playToClient(FetchSetSkillCooldownFromServer.TYPE, FetchSetSkillCooldownFromServer.STREAM_CODEC, FetchSetSkillCooldownFromServer::handle);
        payloadRegistrar.playToClient(FetchStartSkillCooldownFromServer.TYPE, FetchStartSkillCooldownFromServer.STREAM_CODEC, FetchStartSkillCooldownFromServer::handle);
        payloadRegistrar.playToClient(FetchConfigKeyValOnlyFromServer.TYPE, FetchConfigKeyValOnlyFromServer.STREAM_CODEC, FetchConfigKeyValOnlyFromServer::handle);
        payloadRegistrar.playToClient(FetchLoginDataFromServer.TYPE, FetchLoginDataFromServer.STREAM_CODEC, FetchLoginDataFromServer::handle);
        payloadRegistrar.playToClient(FetchPlayerIsImmobilizedFromServer.TYPE, FetchPlayerIsImmobilizedFromServer.STREAM_CODEC, FetchPlayerIsImmobilizedFromServer::handle);
        payloadRegistrar.playToClient(FetchResetPlayerKeyActionFromServer.TYPE, FetchResetPlayerKeyActionFromServer.STREAM_CODEC, FetchResetPlayerKeyActionFromServer::handle);
        payloadRegistrar.playToClient(FetchStatusFromServer.TYPE, FetchStatusFromServer.STREAM_CODEC, FetchStatusFromServer::handle);
        payloadRegistrar.playToClient(FetchStatusKeyValueOnlyFromServer.TYPE, FetchStatusKeyValueOnlyFromServer.STREAM_CODEC, FetchStatusKeyValueOnlyFromServer::handle);
        payloadRegistrar.playToClient(FetchStringMsgFromServer.TYPE, FetchStringMsgFromServer.STREAM_CODEC, FetchStringMsgFromServer::handle);
    }
}

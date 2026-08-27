package net.dingdingduang.somebasicskills.networking;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.packet.*;
import net.dingdingduang.somebasicskills.networking.packet.screen.*;
import net.dingdingduang.somebasicskills.networking.packet.skill.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NetworkingMsgEventRegistry {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlerEvent event) {
        IPayloadRegistrar myRegistrar = event.registrar(Constants.MOD_ID);

        registerToServerPacketConfiguration(myRegistrar);
        registerToClientPacketConfiguration(myRegistrar);
    }




    private static void registerToServerPacketConfiguration(IPayloadRegistrar payloadRegistrar) {
        payloadRegistrar.play(SendStatusToServer.SEND_STATUS_TO_SERVER, SendStatusToServer::new, handler -> handler.server(SendStatusToServer::handle));
        payloadRegistrar.play(SendAddBtnActionToServer.SEND_ADD_BTN_ACTION_TO_SERVER, SendAddBtnActionToServer::new, handler -> handler.server(SendAddBtnActionToServer::handle));
        payloadRegistrar.play(SendMaxBtnActionToServer.SEND_MAX_BTN_ACTION_TO_SERVER, SendMaxBtnActionToServer::new, handler -> handler.server(SendMaxBtnActionToServer::handle));
        payloadRegistrar.play(SendSubBtnActionToServer.SEND_SUB_BTN_ACTION_TO_SERVER, SendSubBtnActionToServer::new, handler -> handler.server(SendSubBtnActionToServer::handle));
        payloadRegistrar.play(SendChannelingSkillFinishedToServer.SEND_CHANNELING_SKILL_FINISHED_TO_SERVER, SendChannelingSkillFinishedToServer::new, handler -> handler.server(SendChannelingSkillFinishedToServer::handle));
        payloadRegistrar.play(SendChannelingSkillToServer.SEND_CHANNELING_SKILL_TO_SERVER, SendChannelingSkillToServer::new, handler -> handler.server(SendChannelingSkillToServer::handle));
        payloadRegistrar.play(SendPlayerStateKeyIncrementValueOnlyToServer.SEND_PLAYER_STATE_KEY_INCREMENT_VALUE_ONLY_TO_SERVER, SendPlayerStateKeyIncrementValueOnlyToServer::new, handler -> handler.server(SendPlayerStateKeyIncrementValueOnlyToServer::handle));
        payloadRegistrar.play(SendPlayerStateKeyValueOnlyToServer.SEND_PLAYER_STATE_KEY_VALUE_ONLY_TO_SERVER, SendPlayerStateKeyValueOnlyToServer::new, handler -> handler.server(SendPlayerStateKeyValueOnlyToServer::handle));
        payloadRegistrar.play(SendPlayerStateMapToServer.SEND_PLAYER_STATE_MAP_TO_SERVER, SendPlayerStateMapToServer::new, handler -> handler.server(SendPlayerStateMapToServer::handle));
        payloadRegistrar.play(SendSkillActionToServer.SEND_SKILL_ACTION_TO_SERVER, SendSkillActionToServer::new, handler -> handler.server(SendSkillActionToServer::handle));
        payloadRegistrar.play(SendSkillActionWithLockOnToServer.SEND_SKILL_ACTION_WITH_LOCK_ON_TO_SERVER, SendSkillActionWithLockOnToServer::new, handler -> handler.server(SendSkillActionWithLockOnToServer::handle));
        payloadRegistrar.play(SendSkillLevelKeyValueOnlyToServer.SEND_SKILL_LEVEL_KEY_VALUE_ONLY_TO_SERVER, SendSkillLevelKeyValueOnlyToServer::new, handler -> handler.server(SendSkillLevelKeyValueOnlyToServer::handle));
        payloadRegistrar.play(SendSkillLevelMapToServer.SEND_SKILL_LEVEL_MAP_TO_SERVER, SendSkillLevelMapToServer::new, handler -> handler.server(SendSkillLevelMapToServer::handle));
        payloadRegistrar.play(SendSkillPassiveActionToServer.SEND_SKILL_PASSIVE_ACTION_TO_SERVER, SendSkillPassiveActionToServer::new, handler -> handler.server(SendSkillPassiveActionToServer::handle));
        payloadRegistrar.play(SendSkillPassiveActionTwoToServer.SEND_SKILL_PASSIVE_ACTION_TWO_TO_SERVER, SendSkillPassiveActionTwoToServer::new, handler -> handler.server(SendSkillPassiveActionTwoToServer::handle));
        payloadRegistrar.play(SendConfigKeyValOnlyToServer.SEND_CONFIG_KEY_VAL_ONLY_TO_SERVER, SendConfigKeyValOnlyToServer::new, handler -> handler.server(SendConfigKeyValOnlyToServer::handle));
        payloadRegistrar.play(SendResetPlayerStateRequestToServer.SEND_RESET_PLAYER_STATE_REQUEST_TO_SERVER, SendResetPlayerStateRequestToServer::new, handler -> handler.server(SendResetPlayerStateRequestToServer::handle));
    }

    private static void registerToClientPacketConfiguration(IPayloadRegistrar payloadRegistrar) {
        payloadRegistrar.play(FetchIncreaseSkillCooldownFromServer.FETCH_INCREASE_SKILL_COOLDOWN_FROM_SERVER, FetchIncreaseSkillCooldownFromServer::new, handler -> handler.client(FetchIncreaseSkillCooldownFromServer::handle));
        payloadRegistrar.play(FetchPlayerStateKeyValueOnlyFromServer.FETCH_PLAYER_STATE_KEY_VALUE_ONLY_FROM_SERVER, FetchPlayerStateKeyValueOnlyFromServer::new, handler -> handler.client(FetchPlayerStateKeyValueOnlyFromServer::handle));
        payloadRegistrar.play(FetchPlayerStateMapFromServer.FETCH_PLAYER_STATE_MAP_FROM_SERVER, FetchPlayerStateMapFromServer::new, handler -> handler.client(FetchPlayerStateMapFromServer::handle));
        payloadRegistrar.play(FetchSetSkillCooldownFromServer.FETCH_SET_SKILL_COOLDOWN_FROM_SERVER, FetchSetSkillCooldownFromServer::new, handler -> handler.client(FetchSetSkillCooldownFromServer::handle));
        payloadRegistrar.play(FetchStartSkillCooldownFromServer.FETCH_START_SKILL_COOLDOWN_FROM_SERVER, FetchStartSkillCooldownFromServer::new, handler -> handler.client(FetchStartSkillCooldownFromServer::handle));
        payloadRegistrar.play(FetchConfigKeyValOnlyFromServer.FETCH_CONFIG_KEY_VAL_ONLY_FROM_SERVER, FetchConfigKeyValOnlyFromServer::new, handler -> handler.client(FetchConfigKeyValOnlyFromServer::handle));
        payloadRegistrar.play(FetchLoginDataFromServer.FETCH_LOGIN_DATA_FROM_SERVER, FetchLoginDataFromServer::new, handler -> handler.client(FetchLoginDataFromServer::handle));
        payloadRegistrar.play(FetchPlayerIsImmobilizedFromServer.FETCH_PLAYER_IS_IMMOBILIZED_FROM_SERVER, FetchPlayerIsImmobilizedFromServer::new, handler -> handler.client(FetchPlayerIsImmobilizedFromServer::handle));
        payloadRegistrar.play(FetchResetPlayerKeyActionFromServer.FETCH_RESET_PLAYER_KEY_ACTION_FROM_SERVER, FetchResetPlayerKeyActionFromServer::new, handler -> handler.client(FetchResetPlayerKeyActionFromServer::handle));
        payloadRegistrar.play(FetchStatusFromServer.FETCH_STATUS_FROM_SERVER, FetchStatusFromServer::new, handler -> handler.client(FetchStatusFromServer::handle));
        payloadRegistrar.play(FetchStatusKeyValueOnlyFromServer.FETCH_STATUS_KEY_VALUE_ONLY_FROM_SERVER, FetchStatusKeyValueOnlyFromServer::new, handler -> handler.client(FetchStatusKeyValueOnlyFromServer::handle));
        payloadRegistrar.play(FetchStringMsgFromServer.FETCH_STRING_MSG_FROM_SERVER, FetchStringMsgFromServer::new, handler -> handler.client(FetchStringMsgFromServer::handle));
    }
}

package net.dingdingduang.somebasicskills.networking;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.packet.*;
import net.dingdingduang.somebasicskills.networking.packet.screen.SendAddBtnActionToServer;
import net.dingdingduang.somebasicskills.networking.packet.screen.SendMaxBtnActionToServer;
import net.dingdingduang.somebasicskills.networking.packet.screen.SendSubBtnActionToServer;
import net.dingdingduang.somebasicskills.networking.packet.skill.*;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;

public class NetworkingMsgInitialization {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
//        SimpleChannel net = NetworkRegistry.ChannelBuilder
//                .named(new ResourceLocation(Constants.MOD_ID, "messages"))
//                .networkProtocolVersion(() -> "1.0")
//                .clientAcceptedVersions(s -> true)
//                .serverAcceptedVersions(s -> true)
//                .simpleChannel();

        SimpleChannel net = NetworkRegistry.newSimpleChannel(getMCResourceLocation(Constants.MOD_ID, "network"),
                () -> "1.0",
                s -> true,
                s -> true);

        INSTANCE = net;


        //==================================
        //TEST
//        net.messageBuilder(SendTestToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
//                .decoder(SendTestToServer::new)
//                .encoder(SendTestToServer::toBytes)
//                .consumer(SendTestToServer::handle)
//                .add();
//
//        net.messageBuilder(SendTestToServerTwo.class, id(), NetworkDirection.PLAY_TO_SERVER)
//                .decoder(SendTestToServerTwo::new)
//                .encoder(SendTestToServerTwo::toBytes)
//                .consumer(SendTestToServerTwo::handle)
//                .add();

        //==================================

        //send shiet to server only without sending packet back to client
        //server <--- client side
        net.messageBuilder(SendAddBtnActionToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendAddBtnActionToServer::new)
                .encoder(SendAddBtnActionToServer::toBytes)
                .consumer(SendAddBtnActionToServer::handle)
                .add();

        net.messageBuilder(SendSubBtnActionToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendSubBtnActionToServer::new)
                .encoder(SendSubBtnActionToServer::toBytes)
                .consumer(SendSubBtnActionToServer::handle)
                .add();

        net.messageBuilder(SendMaxBtnActionToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendMaxBtnActionToServer::new)
                .encoder(SendMaxBtnActionToServer::toBytes)
                .consumer(SendMaxBtnActionToServer::handle)
                .add();

//        net.messageBuilder(SendPutKeyComboKeyValueOnlyToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
//                .decoder(SendPutKeyComboKeyValueOnlyToServer::new)
//                .encoder(SendPutKeyComboKeyValueOnlyToServer::toBytes)
//                .consumer(SendPutKeyComboKeyValueOnlyToServer::handle)
//                .add();
//
//        net.messageBuilder(SendRemoveKeyComboKeyOnlyToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
//                .decoder(SendRemoveKeyComboKeyOnlyToServer::new)
//                .encoder(SendRemoveKeyComboKeyOnlyToServer::toBytes)
//                .consumer(SendRemoveKeyComboKeyOnlyToServer::handle)
//                .add();

//        net.messageBuilder(SendQuickSlotMatchedSkillIDToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
//                .decoder(SendQuickSlotMatchedSkillIDToServer::new)
//                .encoder(SendQuickSlotMatchedSkillIDToServer::toBytes)
//                .consumer(SendQuickSlotMatchedSkillIDToServer::handle)
//                .add();

//        net.messageBuilder(SendKeyComboMatchedSkillIDToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
//                .decoder(SendKeyComboMatchedSkillIDToServer::new)
//                .encoder(SendKeyComboMatchedSkillIDToServer::toBytes)
//                .consumer(SendKeyComboMatchedSkillIDToServer::handle)
//                .add();

        net.messageBuilder(SendChannelingSkillToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendChannelingSkillToServer::new)
                .encoder(SendChannelingSkillToServer::toBytes)
                .consumer(SendChannelingSkillToServer::handle)
                .add();

        net.messageBuilder(SendChannelingSkillFinishedToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendChannelingSkillFinishedToServer::new)
                .encoder(SendChannelingSkillFinishedToServer::toBytes)
                .consumer(SendChannelingSkillFinishedToServer::handle)
                .add();

        net.messageBuilder(SendSkillLevelMapToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendSkillLevelMapToServer::new)
                .encoder(SendSkillLevelMapToServer::toBytes)
                .consumer(SendSkillLevelMapToServer::handle)
                .add();

        net.messageBuilder(SendSkillLevelKeyValueOnlyToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendSkillLevelKeyValueOnlyToServer::new)
                .encoder(SendSkillLevelKeyValueOnlyToServer::toBytes)
                .consumer(SendSkillLevelKeyValueOnlyToServer::handle)
                .add();

        net.messageBuilder(SendSkillActionToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendSkillActionToServer::new)
                .encoder(SendSkillActionToServer::toBytes)
                .consumer(SendSkillActionToServer::handle)
                .add();

        net.messageBuilder(SendSkillActionWithLockOnToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendSkillActionWithLockOnToServer::new)
                .encoder(SendSkillActionWithLockOnToServer::toBytes)
                .consumer(SendSkillActionWithLockOnToServer::handle)
                .add();

        net.messageBuilder(SendSkillPassiveActionToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendSkillPassiveActionToServer::new)
                .encoder(SendSkillPassiveActionToServer::toBytes)
                .consumer(SendSkillPassiveActionToServer::handle)
                .add();

        net.messageBuilder(SendSkillPassiveActionTwoToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendSkillPassiveActionTwoToServer::new)
                .encoder(SendSkillPassiveActionTwoToServer::toBytes)
                .consumer(SendSkillPassiveActionTwoToServer::handle)
                .add();

//        net.messageBuilder(SendUUIDPlayerStateToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
//                .decoder(SendUUIDPlayerStateToServer::new)
//                .encoder(SendUUIDPlayerStateToServer::toBytes)
//                .consumer(SendUUIDPlayerStateToServer::handle)
//                .add();
//
//        net.messageBuilder(SendUUIDWithCurrentChannelingSkillToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
//                .decoder(SendUUIDWithCurrentChannelingSkillToServer::new)
//                .encoder(SendUUIDWithCurrentChannelingSkillToServer::toBytes)
//                .consumer(SendUUIDWithCurrentChannelingSkillToServer::handle)
//                .add();

        net.messageBuilder(SendResetPlayerStateRequestToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendResetPlayerStateRequestToServer::new)
                .encoder(SendResetPlayerStateRequestToServer::toBytes)
                .consumer(SendResetPlayerStateRequestToServer::handle)
                .add();



        //=============================
        //server ---> client side
        net.messageBuilder(FetchStringMsgFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FetchStringMsgFromServer::new)
                .encoder(FetchStringMsgFromServer::toBytes)
                .consumer(FetchStringMsgFromServer::handle)
                .add();

//        net.messageBuilder(FetchStartChannelingFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
//                .decoder(FetchStartChannelingFromServer::new)
//                .encoder(FetchStartChannelingFromServer::toBytes)
//                .consumer(FetchStartChannelingFromServer::handle)
//                .add();

        net.messageBuilder(FetchLoginDataFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FetchLoginDataFromServer::new)
                .encoder(FetchLoginDataFromServer::toBytes)
                .consumer(FetchLoginDataFromServer::handle)
                .add();

//        net.messageBuilder(FetchLogoutDataFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
//                .decoder(FetchLogoutDataFromServer::new)
//                .encoder(FetchLogoutDataFromServer::toBytes)
//                .consumer(FetchLogoutDataFromServer::handle)
//                .add();

        net.messageBuilder(FetchStatusKeyValueOnlyFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FetchStatusKeyValueOnlyFromServer::new)
                .encoder(FetchStatusKeyValueOnlyFromServer::toBytes)
                .consumer(FetchStatusKeyValueOnlyFromServer::handle)
                .add();

        net.messageBuilder(FetchStartSkillCooldownFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FetchStartSkillCooldownFromServer::new)
                .encoder(FetchStartSkillCooldownFromServer::toBytes)
                .consumer(FetchStartSkillCooldownFromServer::handle)
                .add();

        net.messageBuilder(FetchSetSkillCooldownFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FetchSetSkillCooldownFromServer::new)
                .encoder(FetchSetSkillCooldownFromServer::toBytes)
                .consumer(FetchSetSkillCooldownFromServer::handle)
                .add();

        net.messageBuilder(FetchIncreaseSkillCooldownFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FetchIncreaseSkillCooldownFromServer::new)
                .encoder(FetchIncreaseSkillCooldownFromServer::toBytes)
                .consumer(FetchIncreaseSkillCooldownFromServer::handle)
                .add();

        net.messageBuilder(FetchPlayerIsImmobilizedFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FetchPlayerIsImmobilizedFromServer::new)
                .encoder(FetchPlayerIsImmobilizedFromServer::toBytes)
                .consumer(FetchPlayerIsImmobilizedFromServer::handle)
                .add();

        net.messageBuilder(FetchResetPlayerKeyActionFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FetchResetPlayerKeyActionFromServer::new)
                .encoder(FetchResetPlayerKeyActionFromServer::toBytes)
                .consumer(FetchResetPlayerKeyActionFromServer::handle)
                .add();



        //=============================
        //server <---> client side
//        net.messageBuilder(SendQuickSlotSettingToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
//                .decoder(SendQuickSlotSettingToServer::new)
//                .encoder(SendQuickSlotSettingToServer::toBytes)
//                .consumer(SendQuickSlotSettingToServer::handle)
//                .add();
//
//        net.messageBuilder(FetchQuickSlotSettingFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
//                .decoder(FetchQuickSlotSettingFromServer::new)
//                .encoder(FetchQuickSlotSettingFromServer::toBytes)
//                .consumer(FetchQuickSlotSettingFromServer::handle)
//                .add();
//
//        net.messageBuilder(SendKeyComboSettingToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
//                .decoder(SendKeyComboSettingToServer::new)
//                .encoder(SendKeyComboSettingToServer::toBytes)
//                .consumer(SendKeyComboSettingToServer::handle)
//                .add();
//
//        net.messageBuilder(FetchKeyComboSettingFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
//                .decoder(FetchKeyComboSettingFromServer::new)
//                .encoder(FetchKeyComboSettingFromServer::toBytes)
//                .consumer(FetchKeyComboSettingFromServer::handle)
//                .add();

        net.messageBuilder(SendStatusToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendStatusToServer::new)
                .encoder(SendStatusToServer::toBytes)
                .consumer(SendStatusToServer::handle)
                .add();

        net.messageBuilder(FetchStatusFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FetchStatusFromServer::new)
                .encoder(FetchStatusFromServer::toBytes)
                .consumer(FetchStatusFromServer::handle)
                .add();

        net.messageBuilder(SendPlayerStateMapToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendPlayerStateMapToServer::new)
                .encoder(SendPlayerStateMapToServer::toBytes)
                .consumer(SendPlayerStateMapToServer::handle)
                .add();

        net.messageBuilder(FetchPlayerStateMapFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FetchPlayerStateMapFromServer::new)
                .encoder(FetchPlayerStateMapFromServer::toBytes)
                .consumer(FetchPlayerStateMapFromServer::handle)
                .add();

//        net.messageBuilder(SendSkillLevelToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
//                .decoder(SendSkillLevelToServer::new)
//                .encoder(SendSkillLevelToServer::toBytes)
//                .consumer(SendSkillLevelToServer::handle)
//                .add();

//        net.messageBuilder(FetchSkillLevelFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
//                .decoder(FetchSkillLevelFromServer::new)
//                .encoder(FetchSkillLevelFromServer::toBytes)
//                .consumer(FetchSkillLevelFromServer::handle)
//                .add();

//        net.messageBuilder(SendLoginDataToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
//                .decoder(SendLoginDataToServer::new)
//                .encoder(SendLoginDataToServer::toBytes)
//                .consumer(SendLoginDataToServer::handle)
//                .add();

//        net.messageBuilder(FetchLoginDataFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
//                .decoder(FetchLoginDataFromServer::new)
//                .encoder(FetchLoginDataFromServer::toBytes)
//                .consumer(FetchLoginDataFromServer::handle)
//                .add();

        net.messageBuilder(SendPlayerStateKeyValueOnlyToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendPlayerStateKeyValueOnlyToServer::new)
                .encoder(SendPlayerStateKeyValueOnlyToServer::toBytes)
                .consumer(SendPlayerStateKeyValueOnlyToServer::handle)
                .add();

        net.messageBuilder(FetchPlayerStateKeyValueOnlyFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FetchPlayerStateKeyValueOnlyFromServer::new)
                .encoder(FetchPlayerStateKeyValueOnlyFromServer::toBytes)
                .consumer(FetchPlayerStateKeyValueOnlyFromServer::handle)
                .add();

        net.messageBuilder(SendPlayerStateKeyIncrementValueOnlyToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendPlayerStateKeyIncrementValueOnlyToServer::new)
                .encoder(SendPlayerStateKeyIncrementValueOnlyToServer::toBytes)
                .consumer(SendPlayerStateKeyIncrementValueOnlyToServer::handle)
                .add();

//        net.messageBuilder(FetchPlayerStateKeyIncrementValueOnlyFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
//                .decoder(FetchPlayerStateKeyIncrementValueOnlyFromServer::new)
//                .encoder(FetchPlayerStateKeyIncrementValueOnlyFromServer::toBytes)
//                .consumer(FetchPlayerStateKeyIncrementValueOnlyFromServer::handle)
//                .add();

        net.messageBuilder(SendConfigKeyValOnlyToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendConfigKeyValOnlyToServer::new)
                .encoder(SendConfigKeyValOnlyToServer::toBytes)
                .consumer(SendConfigKeyValOnlyToServer::handle)
                .add();

        net.messageBuilder(FetchConfigKeyValOnlyFromServer.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FetchConfigKeyValOnlyFromServer::new)
                .encoder(FetchConfigKeyValOnlyFromServer::toBytes)
                .consumer(FetchConfigKeyValOnlyFromServer::handle)
                .add();

    }

    public static <MSG> void sendToServer(MSG message) {
         INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClient(MSG message, ServerPlayer player) {
        INSTANCE.sendTo(message, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }

    public static <MSG> void sendToClients(PacketDistributor.PacketTarget target, MSG message) {
        INSTANCE.send(target, message);
    }
}

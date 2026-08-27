package net.dingdingduang.somebasicskills.event;

import net.dingdingduang.somebasicskills.networking.NetworkingFetchMsgMethods;
import net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.event.RemovePlayerDataWhenLogout.RemoveServerPlayerData;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftServerInstance;
import static net.dingdingduang.somebasicskills.globalmethods.ServerSkillMethods.initializePlayerPassiveStatusWhenLogin;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.initializeEntityStateHashMapsWhenLogin;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.initializeServerPlayerHashMapsWhenLogin;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerValuesInit.globalServerValuesInit;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerValuesInit.isGlobalServerValuesInitialized;
import static net.dingdingduang.somebasicskills.sbsattributes.statusquery.AttributeServerPlayerStatusQueryMethods.setupSkillDataPlayerInitialization;

public class SBServerPlayerFabricEvent {
    public static void register() {
//        ServerPlayConnectionEvents.INIT.register(
//                SBServerPlayerFabricEvent::FirstPlayerLoggedInInitializationEvent
//        );
        ServerPlayConnectionEvents.JOIN.register(SBServerPlayerFabricEvent::PlayerLoggedInEvent);
        ServerPlayConnectionEvents.DISCONNECT.register(SBServerPlayerFabricEvent::clearDataWhenPlayerLogout);

        ServerPlayerEvents.AFTER_RESPAWN.register(SBServerPlayerFabricEvent::ResetSBSStatusWhenPlayerRespawn);
    }

    private static void ResetSBSStatusWhenPlayerRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
//        initializePlayerPassiveStatusWhenLogin(newPlayer);

////            SendPacketIsInActionToClientSideFromServer(sp1, Constants.ACTION_OFF);
//            ServerUpdateLivingEntityState(sp1, Constants.IS_IN_ACTION, Constants.ACTION_OFF);
//            ServerUpdateLivingEntityState(sp1, Constants.IS_CHANNELING, Constants.ACTION_OFF);
//            ServerUpdateLivingEntityState(sp1, Constants.IS_BACKSTEPPING, Constants.ACTION_OFF);
////            NetworkingFetchMsgMethods.FetchPlayerStateToClientSide(sp1, Constants.IS_CHANNELING, Constants.ACTION_OFF);
//            NetworkingFetchMsgMethods.FetchPlayerIsImmobilizedBooleanFromServer(sp1, false);

        SBPlayerConfigFileInitHelper tempConfigHelper = new SBPlayerConfigFileInitHelper(oldPlayer);
        tempConfigHelper.SBPlayerUnstuckRequestOnServer();
        RemoveServerPlayerData(oldPlayer);
        InitializeServerPlayer(newPlayer);
    }

    private static void InitializeServerPlayer(ServerPlayerEntity sp1) {
        HashMap<ServerPlayerEntity, HashMap<String, Integer>> GlobalPlayerSkillID2lvlMap = getGlobalPlayerSkillID2lvlMap();
        GlobalPlayerSkillID2lvlMap.put(sp1, new HashMap<String, Integer>());

//            MinecraftServer mcServer = sp1.getServer();
        MinecraftServer mcServer = getMinecraftServerInstance(sp1);
        if (mcServer == null) { return; }
        //read player data based on playername
        FileReadWriteMethods.SkillPlayernameFileReadFrom(mcServer, sp1);

        //initialize player hashmap
        initializeServerPlayerHashMapsWhenLogin(sp1);
        initializeEntityStateHashMapsWhenLogin(sp1);
        //initialize player status
        setupSkillDataPlayerInitialization(sp1);

        //read player keyCombo and quickSlot setting
//            KeycomboPlayernameFileReadFrom(mcServer, sp1);
//            QuickslotPlayernameFileReadFrom(mcServer, sp1);

        //read player status
//            FileReadWriteMethods.StatusPlayerNameFileReadFrom(mcServer, sp1);
        //init config
        SBPlayerConfigFileInitHelper tempConfigHelper = new SBPlayerConfigFileInitHelper(sp1);
        tempConfigHelper.SBServerConfigInitMixinHelper();
        //read player config file
        FileReadWriteMethods.ConfigPlayernameFileReadFrom(mcServer, sp1);

        //check player passive skills
        initializePlayerPassiveStatusWhenLogin(sp1);

        //from server to client packet
        NetworkingFetchMsgMethods.FetchLoginDataToClientSide(sp1);
        NetworkingFetchMsgMethods.FetchPlayerStatusMapToClientSide(sp1);
//            NetworkingFetchMsgMethods.FetchQuickSlotSettingToClientSide(sp1);
//            NetworkingFetchMsgMethods.FetchKeyComboSettingToClientSide(sp1);
        //sync with client config
        tempConfigHelper.SBServerConfigAfterInitMixinHelper();
    }

    private static void PlayerLoggedInEvent(ServerPlayNetworkHandler serverPlayNetworkHandler, PacketSender packetSender, MinecraftServer minecraftServer) {
        ServerPlayerEntity sp1 = serverPlayNetworkHandler.player;
        //only execute once when server activate
        if (!isGlobalServerValuesInitialized()) {
            globalServerValuesInit();
        }

        InitializeServerPlayer(sp1);
    }

//    private static void FirstPlayerLoggedInInitializationEvent(ServerPlayNetworkHandler serverPlayNetworkHandler, MinecraftServer minecraftServer) {
//
//    }

    private static void clearDataWhenPlayerLogout(ServerPlayNetworkHandler serverPlayNetworkHandler, MinecraftServer minecraftServer) {
        RemoveServerPlayerData(serverPlayNetworkHandler.player);
    }

    private static void PlayerRespawnEvent() {

    }
}

package net.dingdingduang.somebasicskills.event;

import net.dingdingduang.somebasicskills.Constants;

import net.dingdingduang.somebasicskills.networking.NetworkingFetchMsgMethods;
import net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.event.RemovePlayerDataWhenLogout.RemoveServerPlayerData;
import static net.dingdingduang.somebasicskills.globalmethods.ServerSkillMethods.*;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.initializeEntityStateHashMapsWhenLogin;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;
import static net.dingdingduang.somebasicskills.sbsattributes.statusquery.AttributeServerPlayerStatusQueryMethods.setupSkillDataPlayerInitialization;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.*;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerValuesInit.globalServerValuesInit;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerValuesInit.isGlobalServerValuesInitialized;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class SBServerPlayerEvent {
    //load on server side when player login
    @SubscribeEvent
    public static void readSkillDataWhenPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
//        Player cp1 = e.getEntity();

//        if (cp1.getCommandSenderWorld().isClientSide) { return; }

        if(e.getEntity() instanceof ServerPlayer sp1) {
            //only execute once when server activate
            if (!isGlobalServerValuesInitialized()) {
                globalServerValuesInit();
            }

            HashMap<ServerPlayer, HashMap<String, Integer>> GlobalPlayerSkillID2lvlMap = getGlobalPlayerSkillID2lvlMap();
            GlobalPlayerSkillID2lvlMap.put(sp1, new HashMap<String, Integer>());

//            MinecraftServer mcServer = sp1.getServer();
            MinecraftServer mcServer = getMinecraftServerInstance();
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
    }

    //clear data when player logout
    @SubscribeEvent
    public static void clearDataWhenPlayerLogout(PlayerEvent.PlayerLoggedOutEvent e) {
//        if (e.getEntity().getCommandSenderWorld().isClientSide) { return; }

//        Player cp1 = e.getEntity();

        if (e.getEntity() instanceof ServerPlayer sp1) {
            RemoveServerPlayerData(sp1);
//            NetworkingFetchMsgMethods.FetchLogoutDataToClientSide(sp1);
        }
        //save Skill Data When Player Logout?
    }

    //reset stat when player respawn
    @SubscribeEvent
    public static void resetSBSStatusWhenPlayerRespawn(PlayerEvent.PlayerRespawnEvent e) {
//        if (e.getEntity().getCommandSenderWorld().isClientSide) { return; }

//        Player cp1 = e.getEntity();

        if (e.getEntity() instanceof ServerPlayer sp1) {
            //check player passive skills
            initializePlayerPassiveStatusWhenLogin(sp1);

////            SendPacketIsInActionToClientSideFromServer(sp1, Constants.ACTION_OFF);
//            ServerUpdateLivingEntityState(sp1, Constants.IS_IN_ACTION, Constants.ACTION_OFF);
//            ServerUpdateLivingEntityState(sp1, Constants.IS_CHANNELING, Constants.ACTION_OFF);
//            ServerUpdateLivingEntityState(sp1, Constants.IS_BACKSTEPPING, Constants.ACTION_OFF);
////            NetworkingFetchMsgMethods.FetchPlayerStateToClientSide(sp1, Constants.IS_CHANNELING, Constants.ACTION_OFF);
//            NetworkingFetchMsgMethods.FetchPlayerIsImmobilizedBooleanFromServer(sp1, false);

            SBPlayerConfigFileInitHelper tempConfigHelper = new SBPlayerConfigFileInitHelper(sp1);
            tempConfigHelper.SBPlayerUnstuckRequestOnServer();
        }
    }
}

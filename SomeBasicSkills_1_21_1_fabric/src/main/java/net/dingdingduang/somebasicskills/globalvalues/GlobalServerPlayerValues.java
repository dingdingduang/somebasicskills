package net.dingdingduang.somebasicskills.globalvalues;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.HashSet;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;

public class GlobalServerPlayerValues {
    //Basic
    private static HashMap<ServerPlayerEntity, HashMap<String, Integer>> GlobalPlayerSkillID2lvlMap;
    //player value multiplier, will be used to generate character info in future
    private static HashMap<ServerPlayerEntity, HashMap<String, Double>> SPlayerValue2BaseMultiplierMap;
//    private static HashMap<String, MethodAction> SPlayerValue2BaseMultiplierActionMap;

    private static HashMap<ServerPlayerEntity, HashMap<String, HashMap<String, MethodConfigHelper>>> SPlayerConfig;
    private static HashSet<String> SConfig;

    private static HashMap<ServerPlayerEntity, Integer> SPlayerLastTriggeredSkillServerTimeTicks;
    private static HashMap<ServerPlayerEntity, String> SPlayerLastTriggeredNotChannelingActiveSkillID;
    private static HashMap<ServerPlayerEntity, String> SPlayerLastTriggeredChannelingActiveSkillID;

    // for status bleed stack, channeling status, is running walking or something else
    // to help me build skills better
//    private static HashMap<ServerPlayerEntity, HashMap<String, Integer>> SPlayerState;

    //keyCode and KeyCombo data maps
//    private static HashMap<ServerPlayerEntity, Boolean> SPlayerAssigningKeyCombo;
////    private static HashMap<ServerPlayerEntity, ArrayList<Integer>> SPlayerCurrentKeyCombo;
//    //    private static HashMap<ServerPlayerEntity, ArrayList<Integer>> SPlayerLastKeyCombo;
//    private static HashMap<ServerPlayerEntity, HashMap<ArrayList<Integer>, String>> SPlayerKeyCombo2SkillID;
//    private static HashMap<ServerPlayerEntity, HashMap<String, ArrayList<Integer>>> SPlayerSkillID2KeyCombo;
//
//    private static HashMap<ServerPlayerEntity, Boolean> SPlayerAssigningQuickslot;
////    private static HashMap<ServerPlayerEntity, Integer> SPlayerLastPressedKeyCode;
//    private static HashMap<ServerPlayerEntity, HashMap<Integer, String>> SPlayerKey2SkillID;
//    private static HashMap<ServerPlayerEntity, HashMap<String, Integer>> SPlayerSkillID2Key;

//    private static HashMap<ServerPlayerEntity, Integer> SPlayerLastKeyAction;


    //================================================================================================
    //Initialization for server
    public static void GlobalServerPlayerValuesInit() {
        GlobalPlayerSkillID2lvlMap = new HashMap<ServerPlayerEntity, HashMap<String, Integer>>();
//        SPlayerState = new HashMap<ServerPlayerEntity, HashMap<String, Integer>>();
        SPlayerValue2BaseMultiplierMap = new HashMap<ServerPlayerEntity, HashMap<String, Double>>();

        //config
        SPlayerConfig = new HashMap<ServerPlayerEntity, HashMap<String, HashMap<String, MethodConfigHelper>>>();

        //init SConfig
        SConfig = new HashSet<String>();
        SConfig.add(Constants.SB_GENERAL_SETTING);
        SConfig.add(Constants.CONDITION_OPTION_INDEX);
        SConfig.add(Constants.PLAY_SKILL_ERR_SOUND);
        SConfig.add(Constants.SB_GENERAL_SETTING_LOCK_ON);
        SConfig.add(Constants.SB_GENERAL_SETTING_LOCK_ON_RANGE);

        //Server player status maps init
        SPlayerLastTriggeredSkillServerTimeTicks = new HashMap<ServerPlayerEntity, Integer>();
        SPlayerLastTriggeredNotChannelingActiveSkillID = new HashMap<ServerPlayerEntity, String>();
        SPlayerLastTriggeredChannelingActiveSkillID = new HashMap<ServerPlayerEntity, String>();


        //Keyboard keycode pressing and keycombo to release any skill
//        SPlayerAssigningQuickslot = new HashMap<ServerPlayerEntity, Boolean>();
////        SPlayerCurrentKeyCombo = new HashMap<ServerPlayerEntity, ArrayList<Integer>>();
//        SPlayerKeyCombo2SkillID = new HashMap<ServerPlayerEntity, HashMap<ArrayList<Integer>, String>>();
//        SPlayerSkillID2KeyCombo = new HashMap<ServerPlayerEntity, HashMap<String, ArrayList<Integer>>>();
//
//        SPlayerAssigningKeyCombo = new HashMap<ServerPlayerEntity, Boolean>();
////        SPlayerLastPressedKeyCode = new HashMap<ServerPlayerEntity, Integer>();
//        SPlayerKey2SkillID = new HashMap<ServerPlayerEntity, HashMap<Integer, String>>();
//        SPlayerSkillID2Key = new HashMap<ServerPlayerEntity, HashMap<String, Integer>>();

        //Key Action, 0 releasing from key, 1 pressed down, 2 holding
//        SPlayerLastKeyAction = new HashMap<ServerPlayerEntity, Integer>();
    }


    //====================
    //config
    public void setupMixinConfigFile() {

    }

    public GlobalServerPlayerValues() {

    }

    //================================================================================================
    //Initialization for each login player
    public static void initializeServerPlayerHashMapsWhenLogin(ServerPlayerEntity a) {
        //GlobalPlayerSkillID2lvlMap is already init for player
        //keyboard
//        getSPlayerAssigningQuickslot().put(a, false);
//        getSPlayerKey2SkillID().put(a, new HashMap<Integer, String>());
//        getSPlayerSkillID2Key().put(a, new HashMap<String, Integer>());
//
//        getSPlayerAssigningKeyCombo().put(a, false);
//        getSPlayerKeyCombo2SkillID().put(a, new HashMap<ArrayList<Integer>, String>());
//        getSPlayerSkillID2KeyCombo().put(a, new HashMap<String, ArrayList<Integer>>());

        //player state
//        SPlayerState.put(a, new HashMap<String, Integer>());
        SPlayerValue2BaseMultiplierMap.put(a, new HashMap<String, Double>());

        //Server player status maps init
        SPlayerLastTriggeredSkillServerTimeTicks.put(a, 0);
        SPlayerLastTriggeredNotChannelingActiveSkillID.put(a, Constants.NOTHING);
        SPlayerLastTriggeredChannelingActiveSkillID.put(a, Constants.NOTHING);

        //config
        HashMap<String, HashMap<String, MethodConfigHelper>> ServerPlayerEntityConfigMap = new HashMap<String, HashMap<String, MethodConfigHelper>>();
        HashMap<String, MethodConfigHelper> GeneralSetting = new HashMap<String, MethodConfigHelper>();
        GeneralSetting.put(Constants.CONDITION_OPTION_INDEX, new MethodConfigHelper(null, 0, true));
        GeneralSetting.put(Constants.PLAY_SKILL_ERR_SOUND, new MethodConfigHelper(null, 0, true));
        GeneralSetting.put(Constants.SB_GENERAL_SETTING_LOCK_ON, new MethodConfigHelper(null, 0, true));
        GeneralSetting.put(Constants.SB_GENERAL_SETTING_LOCK_ON_RANGE, new MethodConfigHelper(null, 2, false));
        ServerPlayerEntityConfigMap.put(Constants.SB_GENERAL_SETTING, GeneralSetting);
        SPlayerConfig.put(a, ServerPlayerEntityConfigMap);

        GlobalServerPlayerValues tempConfig = new GlobalServerPlayerValues();
        tempConfig.setupMixinConfigFile();
    }

    //================================================================================================
    //Basic
    public static HashMap<ServerPlayerEntity, HashMap<String, Integer>> getGlobalPlayerSkillID2lvlMap() {
        return GlobalPlayerSkillID2lvlMap;
    }

    public static void setGlobalPlayerSkillID2lvlMap(HashMap<ServerPlayerEntity, HashMap<String, Integer>> tempPlayerList) {
        GlobalPlayerSkillID2lvlMap = tempPlayerList;
    }

    public static HashMap<ServerPlayerEntity, HashMap<String, Double>> getSPlayerValue2BaseMultiplierMap() { return SPlayerValue2BaseMultiplierMap; }
    public static void setSPlayerValue2MultiplierMap(HashMap<ServerPlayerEntity, HashMap<String, Double>> playerValue2MultiplierMap) { SPlayerValue2BaseMultiplierMap = playerValue2MultiplierMap; }

    public static boolean isSPlayerOnline(ServerPlayerEntity sp1) { return getSLivingEntityState().get(sp1).containsKey("ONLINE"); }
    public static void setSPlayerOnline(ServerPlayerEntity sp1, boolean isOnline) {
        if (isOnline) {
            getSLivingEntityState().get(sp1).put("ONLINE", 0);
        }
        else {
            getSLivingEntityState().get(sp1).remove("ONLINE");
        }
    }

    public static HashMap<ServerPlayerEntity, HashMap<String, HashMap<String, MethodConfigHelper>>> getSPlayerConfig() { return SPlayerConfig; }
    public static void setSPlayerConfig(HashMap<ServerPlayerEntity, HashMap<String, HashMap<String, MethodConfigHelper>>> sPlayerConfig) { SPlayerConfig = sPlayerConfig; }

    public static HashSet<String> getSConfig() { return SConfig; }
//    public static void setSConfig(HashSet<String> sConfig) { SConfig = sConfig; }


    //Server player skill status
    public static HashMap<ServerPlayerEntity, Integer> getSPlayerLastTriggeredSkillServerTimeTicks() { return SPlayerLastTriggeredSkillServerTimeTicks; }
    public static void setSPlayerLastTriggeredSkillServerTimeTicks(HashMap<ServerPlayerEntity, Integer> sPlayerLastTriggeredSkillServerTimeTicks) { SPlayerLastTriggeredSkillServerTimeTicks = sPlayerLastTriggeredSkillServerTimeTicks; }
    public static HashMap<ServerPlayerEntity, String> getSPlayerLastTriggeredNotChannelingActiveSkillID() { return SPlayerLastTriggeredNotChannelingActiveSkillID; }
    public static void setSPlayerLastTriggeredNotChannelingActiveSkillID(HashMap<ServerPlayerEntity, String> sPlayerLastTriggeredNotChannelingActiveSkillID) { SPlayerLastTriggeredNotChannelingActiveSkillID = sPlayerLastTriggeredNotChannelingActiveSkillID; }
    public static HashMap<ServerPlayerEntity, String> getSPlayerLastTriggeredChannelingActiveSkillID() { return SPlayerLastTriggeredChannelingActiveSkillID; }
    public static void setSPlayerLastTriggeredChannelingActiveSkillID(HashMap<ServerPlayerEntity, String> sPlayerLastTriggeredChannelingActiveSkillID) { SPlayerLastTriggeredChannelingActiveSkillID = sPlayerLastTriggeredChannelingActiveSkillID; }

    //================================================================================================
    //KeyCombo
//    public static HashMap<ServerPlayerEntity, Boolean> getSPlayerAssigningKeyCombo() { return SPlayerAssigningKeyCombo; }
//    public static void setSPlayerAssigningKeyCombo(HashMap<ServerPlayerEntity, Boolean> sPlayerAssigningKeyCombo) { SPlayerAssigningKeyCombo = sPlayerAssigningKeyCombo; }
////    public static HashMap<ServerPlayerEntity, ArrayList<Integer>> getSPlayerCurrentKeyCombo() { return SPlayerCurrentKeyCombo; }
////    public static void setSPlayerCurrentKeyCombo(HashMap<ServerPlayerEntity, ArrayList<Integer>> sPlayerKeyCombo) { SPlayerCurrentKeyCombo = sPlayerKeyCombo; }
//    public static HashMap<ServerPlayerEntity, HashMap<ArrayList<Integer>, String>> getSPlayerKeyCombo2SkillID() {
//        return SPlayerKeyCombo2SkillID;
//    }
//    public static void setSPlayerKeyCombo2SkillID(HashMap<ServerPlayerEntity, HashMap<ArrayList<Integer>, String>> sPlayerKeyCombo2SkillID) {
//        SPlayerKeyCombo2SkillID = sPlayerKeyCombo2SkillID;
//    }
//    public static HashMap<ServerPlayerEntity, HashMap<String, ArrayList<Integer>>> getSPlayerSkillID2KeyCombo() {
//        return SPlayerSkillID2KeyCombo;
//    }
//    public static void setSPlayerSkillID2KeyCombo(HashMap<ServerPlayerEntity, HashMap<String, ArrayList<Integer>>> sPlayerSkillID2KeyCombo) {
//        SPlayerSkillID2KeyCombo = sPlayerSkillID2KeyCombo;
//    }
//    //QuickSlot
//    public static HashMap<ServerPlayerEntity, Boolean> getSPlayerAssigningQuickslot() { return SPlayerAssigningQuickslot; }
//    public static void setSPlayerAssigningQuickslot(HashMap<ServerPlayerEntity, Boolean> sPlayerAssigningQuickslot) { SPlayerAssigningQuickslot = sPlayerAssigningQuickslot; }
////    public static HashMap<ServerPlayerEntity, Integer> getSPlayerLastPressedKeyCode() { return SPlayerLastPressedKeyCode; }
////    public static void setSPlayerLastPressedKeyCode(HashMap<ServerPlayerEntity, Integer> sPlayerLastPressedKeyCode) { SPlayerLastPressedKeyCode = sPlayerLastPressedKeyCode; }
//    public static HashMap<ServerPlayerEntity, HashMap<Integer, String>> getSPlayerKey2SkillID() { return SPlayerKey2SkillID; }
//    public static void setSPlayerKey2SkillID(HashMap<ServerPlayerEntity, HashMap<Integer, String>> sPlayerKey2SkillID) { SPlayerKey2SkillID = sPlayerKey2SkillID; }
//    public static HashMap<ServerPlayerEntity, HashMap<String, Integer>> getSPlayerSkillID2Key() { return SPlayerSkillID2Key; }
//    public static void setSPlayerSkillID2Key(HashMap<ServerPlayerEntity, HashMap<String, Integer>> sPlayerSkillID2Key) { SPlayerSkillID2Key = sPlayerSkillID2Key; }
//
//    public static HashMap<ServerPlayerEntity, Integer> getSPlayerLastKeyAction() { return SPlayerLastKeyAction; }
//    public static void setSPlayerLastKeyAction(HashMap<ServerPlayerEntity, Integer> sPlayerLastKeyAction) { SPlayerLastKeyAction = sPlayerLastKeyAction; }
}

package net.dingdingduang.somebasicskills.globalvalues;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.HashSet;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;

public class GlobalServerPlayerValues {
    //Basic
    private static HashMap<ServerPlayer, HashMap<String, Integer>> GlobalPlayerSkillID2lvlMap;
    //player value multiplier, will be used to generate character info in future
    private static HashMap<ServerPlayer, HashMap<String, Double>> SPlayerValue2BaseMultiplierMap;
//    private static HashMap<String, MethodAction> SPlayerValue2BaseMultiplierActionMap;

    private static HashMap<ServerPlayer, HashMap<String, HashMap<String, MethodConfigHelper>>> SPlayerConfig;
    private static HashSet<String> SConfig;

    private static HashMap<ServerPlayer, Integer> SPlayerLastTriggeredSkillServerTimeTicks;
    private static HashMap<ServerPlayer, String> SPlayerLastTriggeredNotChannelingActiveSkillID;
    private static HashMap<ServerPlayer, String> SPlayerLastTriggeredChannelingActiveSkillID;

    // for status bleed stack, channeling status, is running walking or something else
    // to help me build skills better
//    private static HashMap<ServerPlayer, HashMap<String, Integer>> SPlayerState;

    //keyCode and KeyCombo data maps
//    private static HashMap<ServerPlayer, Boolean> SPlayerAssigningKeyCombo;
////    private static HashMap<ServerPlayer, ArrayList<Integer>> SPlayerCurrentKeyCombo;
//    //    private static HashMap<ServerPlayer, ArrayList<Integer>> SPlayerLastKeyCombo;
//    private static HashMap<ServerPlayer, HashMap<ArrayList<Integer>, String>> SPlayerKeyCombo2SkillID;
//    private static HashMap<ServerPlayer, HashMap<String, ArrayList<Integer>>> SPlayerSkillID2KeyCombo;
//
//    private static HashMap<ServerPlayer, Boolean> SPlayerAssigningQuickslot;
////    private static HashMap<ServerPlayer, Integer> SPlayerLastPressedKeyCode;
//    private static HashMap<ServerPlayer, HashMap<Integer, String>> SPlayerKey2SkillID;
//    private static HashMap<ServerPlayer, HashMap<String, Integer>> SPlayerSkillID2Key;

//    private static HashMap<ServerPlayer, Integer> SPlayerLastKeyAction;


    //================================================================================================
    //Initialization for server
    public static void GlobalServerPlayerValuesInit() {
        GlobalPlayerSkillID2lvlMap = new HashMap<ServerPlayer, HashMap<String, Integer>>();
//        SPlayerState = new HashMap<ServerPlayer, HashMap<String, Integer>>();
        SPlayerValue2BaseMultiplierMap = new HashMap<ServerPlayer, HashMap<String, Double>>();

        //config
        SPlayerConfig = new HashMap<ServerPlayer, HashMap<String, HashMap<String, MethodConfigHelper>>>();

        //init SConfig
        SConfig = new HashSet<String>();
        SConfig.add(Constants.SB_GENERAL_SETTING);
        SConfig.add(Constants.CONDITION_OPTION_INDEX);
        SConfig.add(Constants.PLAY_SKILL_ERR_SOUND);
        SConfig.add(Constants.SB_GENERAL_SETTING_LOCK_ON);
        SConfig.add(Constants.SB_GENERAL_SETTING_LOCK_ON_RANGE);

        //Server player status maps init
        SPlayerLastTriggeredSkillServerTimeTicks = new HashMap<ServerPlayer, Integer>();
        SPlayerLastTriggeredNotChannelingActiveSkillID = new HashMap<ServerPlayer, String>();
        SPlayerLastTriggeredChannelingActiveSkillID = new HashMap<ServerPlayer, String>();


        //Keyboard keycode pressing and keycombo to release any skill
//        SPlayerAssigningQuickslot = new HashMap<ServerPlayer, Boolean>();
////        SPlayerCurrentKeyCombo = new HashMap<ServerPlayer, ArrayList<Integer>>();
//        SPlayerKeyCombo2SkillID = new HashMap<ServerPlayer, HashMap<ArrayList<Integer>, String>>();
//        SPlayerSkillID2KeyCombo = new HashMap<ServerPlayer, HashMap<String, ArrayList<Integer>>>();
//
//        SPlayerAssigningKeyCombo = new HashMap<ServerPlayer, Boolean>();
////        SPlayerLastPressedKeyCode = new HashMap<ServerPlayer, Integer>();
//        SPlayerKey2SkillID = new HashMap<ServerPlayer, HashMap<Integer, String>>();
//        SPlayerSkillID2Key = new HashMap<ServerPlayer, HashMap<String, Integer>>();

        //Key Action, 0 releasing from key, 1 pressed down, 2 holding
//        SPlayerLastKeyAction = new HashMap<ServerPlayer, Integer>();
    }


    //====================
    //config
    public void setupMixinConfigFile() {

    }

    public GlobalServerPlayerValues() {

    }

    //================================================================================================
    //Initialization for each login player
    public static void initializeServerPlayerHashMapsWhenLogin(ServerPlayer a) {
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
        HashMap<String, HashMap<String, MethodConfigHelper>> ServerPlayerConfigMap = new HashMap<String, HashMap<String, MethodConfigHelper>>();
        HashMap<String, MethodConfigHelper> GeneralSetting = new HashMap<String, MethodConfigHelper>();
        GeneralSetting.put(Constants.CONDITION_OPTION_INDEX, new MethodConfigHelper(null, 0, true));
        GeneralSetting.put(Constants.PLAY_SKILL_ERR_SOUND, new MethodConfigHelper(null, 0, true));
        GeneralSetting.put(Constants.SB_GENERAL_SETTING_LOCK_ON, new MethodConfigHelper(null, 0, true));
        GeneralSetting.put(Constants.SB_GENERAL_SETTING_LOCK_ON_RANGE, new MethodConfigHelper(null, 2, false));
        ServerPlayerConfigMap.put(Constants.SB_GENERAL_SETTING, GeneralSetting);
        SPlayerConfig.put(a, ServerPlayerConfigMap);

        GlobalServerPlayerValues tempConfig = new GlobalServerPlayerValues();
        tempConfig.setupMixinConfigFile();
    }

    //================================================================================================
    //Basic
    public static HashMap<ServerPlayer, HashMap<String, Integer>> getGlobalPlayerSkillID2lvlMap() {
        return GlobalPlayerSkillID2lvlMap;
    }

    public static void setGlobalPlayerSkillID2lvlMap(HashMap<ServerPlayer, HashMap<String, Integer>> tempPlayerList) {
        GlobalPlayerSkillID2lvlMap = tempPlayerList;
    }

    public static HashMap<ServerPlayer, HashMap<String, Double>> getSPlayerValue2BaseMultiplierMap() { return SPlayerValue2BaseMultiplierMap; }
    public static void setSPlayerValue2MultiplierMap(HashMap<ServerPlayer, HashMap<String, Double>> playerValue2MultiplierMap) { SPlayerValue2BaseMultiplierMap = playerValue2MultiplierMap; }

    public static boolean isSPlayerOnline(ServerPlayer sp1) { return getSLivingEntityState().get(sp1).containsKey("ONLINE"); }
    public static void setSPlayerOnline(ServerPlayer sp1, boolean isOnline) {
        if (isOnline) {
            getSLivingEntityState().get(sp1).put("ONLINE", 0);
        }
        else {
            getSLivingEntityState().get(sp1).remove("ONLINE");
        }
    }

    public static HashMap<ServerPlayer, HashMap<String, HashMap<String, MethodConfigHelper>>> getSPlayerConfig() { return SPlayerConfig; }
    public static void setSPlayerConfig(HashMap<ServerPlayer, HashMap<String, HashMap<String, MethodConfigHelper>>> sPlayerConfig) { SPlayerConfig = sPlayerConfig; }

    public static HashSet<String> getSConfig() { return SConfig; }
//    public static void setSConfig(HashSet<String> sConfig) { SConfig = sConfig; }


    //Server player skill status
    public static HashMap<ServerPlayer, Integer> getSPlayerLastTriggeredSkillServerTimeTicks() { return SPlayerLastTriggeredSkillServerTimeTicks; }
    public static void setSPlayerLastTriggeredSkillServerTimeTicks(HashMap<ServerPlayer, Integer> sPlayerLastTriggeredSkillServerTimeTicks) { SPlayerLastTriggeredSkillServerTimeTicks = sPlayerLastTriggeredSkillServerTimeTicks; }
    public static HashMap<ServerPlayer, String> getSPlayerLastTriggeredNotChannelingActiveSkillID() { return SPlayerLastTriggeredNotChannelingActiveSkillID; }
    public static void setSPlayerLastTriggeredNotChannelingActiveSkillID(HashMap<ServerPlayer, String> sPlayerLastTriggeredNotChannelingActiveSkillID) { SPlayerLastTriggeredNotChannelingActiveSkillID = sPlayerLastTriggeredNotChannelingActiveSkillID; }
    public static HashMap<ServerPlayer, String> getSPlayerLastTriggeredChannelingActiveSkillID() { return SPlayerLastTriggeredChannelingActiveSkillID; }
    public static void setSPlayerLastTriggeredChannelingActiveSkillID(HashMap<ServerPlayer, String> sPlayerLastTriggeredChannelingActiveSkillID) { SPlayerLastTriggeredChannelingActiveSkillID = sPlayerLastTriggeredChannelingActiveSkillID; }

    //================================================================================================
    //KeyCombo
//    public static HashMap<ServerPlayer, Boolean> getSPlayerAssigningKeyCombo() { return SPlayerAssigningKeyCombo; }
//    public static void setSPlayerAssigningKeyCombo(HashMap<ServerPlayer, Boolean> sPlayerAssigningKeyCombo) { SPlayerAssigningKeyCombo = sPlayerAssigningKeyCombo; }
////    public static HashMap<ServerPlayer, ArrayList<Integer>> getSPlayerCurrentKeyCombo() { return SPlayerCurrentKeyCombo; }
////    public static void setSPlayerCurrentKeyCombo(HashMap<ServerPlayer, ArrayList<Integer>> sPlayerKeyCombo) { SPlayerCurrentKeyCombo = sPlayerKeyCombo; }
//    public static HashMap<ServerPlayer, HashMap<ArrayList<Integer>, String>> getSPlayerKeyCombo2SkillID() {
//        return SPlayerKeyCombo2SkillID;
//    }
//    public static void setSPlayerKeyCombo2SkillID(HashMap<ServerPlayer, HashMap<ArrayList<Integer>, String>> sPlayerKeyCombo2SkillID) {
//        SPlayerKeyCombo2SkillID = sPlayerKeyCombo2SkillID;
//    }
//    public static HashMap<ServerPlayer, HashMap<String, ArrayList<Integer>>> getSPlayerSkillID2KeyCombo() {
//        return SPlayerSkillID2KeyCombo;
//    }
//    public static void setSPlayerSkillID2KeyCombo(HashMap<ServerPlayer, HashMap<String, ArrayList<Integer>>> sPlayerSkillID2KeyCombo) {
//        SPlayerSkillID2KeyCombo = sPlayerSkillID2KeyCombo;
//    }
//    //QuickSlot
//    public static HashMap<ServerPlayer, Boolean> getSPlayerAssigningQuickslot() { return SPlayerAssigningQuickslot; }
//    public static void setSPlayerAssigningQuickslot(HashMap<ServerPlayer, Boolean> sPlayerAssigningQuickslot) { SPlayerAssigningQuickslot = sPlayerAssigningQuickslot; }
////    public static HashMap<ServerPlayer, Integer> getSPlayerLastPressedKeyCode() { return SPlayerLastPressedKeyCode; }
////    public static void setSPlayerLastPressedKeyCode(HashMap<ServerPlayer, Integer> sPlayerLastPressedKeyCode) { SPlayerLastPressedKeyCode = sPlayerLastPressedKeyCode; }
//    public static HashMap<ServerPlayer, HashMap<Integer, String>> getSPlayerKey2SkillID() { return SPlayerKey2SkillID; }
//    public static void setSPlayerKey2SkillID(HashMap<ServerPlayer, HashMap<Integer, String>> sPlayerKey2SkillID) { SPlayerKey2SkillID = sPlayerKey2SkillID; }
//    public static HashMap<ServerPlayer, HashMap<String, Integer>> getSPlayerSkillID2Key() { return SPlayerSkillID2Key; }
//    public static void setSPlayerSkillID2Key(HashMap<ServerPlayer, HashMap<String, Integer>> sPlayerSkillID2Key) { SPlayerSkillID2Key = sPlayerSkillID2Key; }
//
//    public static HashMap<ServerPlayer, Integer> getSPlayerLastKeyAction() { return SPlayerLastKeyAction; }
//    public static void setSPlayerLastKeyAction(HashMap<ServerPlayer, Integer> sPlayerLastKeyAction) { SPlayerLastKeyAction = sPlayerLastKeyAction; }
}

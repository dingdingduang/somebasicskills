package net.dingdingduang.somebasicskills.globalvalues;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.event.SBPlayerConfigFileInitHelper;
import net.dingdingduang.somebasicskills.networking.NetworkingSendMsgMethods;
import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods;

import java.util.ArrayList;
import java.util.HashMap;

public class GlobalClientPlayerValues {
    //Basic
    private static HashMap<String, Integer> ClientPlayerSkillID2lvlMap = new HashMap<>();
    private static HashMap<String, Double> CPlayerBaseMultiplierStatusMap = new HashMap<>();
    private static HashMap<String, Integer> CPlayerState = new HashMap<>();

    private static boolean CPlayerIsImmobilized = false;

    private static String CPlayerLastTriggeredNotChannelingActiveSkillID = "noaction";
    private static String CPlayerLastTriggeredChannelingActiveSkillID = "noaction";
    private static String CPlayerCurrentActiveSkillID = "noaction";

    private static HashMap<String, HashMap<String, MethodConfigHelper>> CPlayerConfig2Settings = new HashMap<>();

    //Priority
    private static boolean CPlayerAssigningSkillPriority = false;
    private static HashMap<String, Integer> CPlayerSkillID2Priority = new HashMap<>();
    //Cooldown Delay
    private static boolean CPlayerAssigningSkillCooldownDelay = false;
    private static boolean CPlayerAppendedPeriodForSkillCooldownDelay = false;
    private static HashMap<String, Float> CPlayerSkillID2CooldownDelay = new HashMap<>();

    //keyCode and KeyCombo data maps
    private static boolean CPlayerAssigningKeyCombo = false;
    private static ArrayList<Integer> CPlayerCurrentKeyCombo = new ArrayList<>();
    private static HashMap<ArrayList<Integer>, HashMap<String, Byte>> CPlayerKeyCombo2SkillID = new HashMap<>();
    private static HashMap<String, ArrayList<Integer>> CPlayerSkillID2KeyCombo = new HashMap<>();

    private static boolean CPlayerAssigningQuickslot = false;
    private static int CPlayerLastPressedKeyCode = -1;
    private static HashMap<Integer, HashMap<String, Byte>> CPlayerKey2SkillID = new HashMap<>();
    private static HashMap<String, Integer> CPlayerSkillID2Key = new HashMap<>();

    private static int CPlayerLastKeyAction = -1;

    public static void DefaultSettingOnOff(String configName, String configDetailName, MethodConfigHelper configHelper) {
        //true false
        if (configHelper.getIntValue() != 0) {
            configHelper.setIntValue(Constants.ACTION_OFF);
            NetworkingSendMsgMethods.SendConfigKeyValFromClientSideToServer(configName, configDetailName, Constants.ACTION_OFF, true);
        } else {
            configHelper.setIntValue(Constants.ACTION_ON);
            NetworkingSendMsgMethods.SendConfigKeyValFromClientSideToServer(configName, configDetailName, Constants.ACTION_ON, true);
        }
    }

    public static void DefaultSettingLoopIntFrom1to32(String configName, String configDetailName, MethodConfigHelper configHelper) {
        //true false
        int currentInt = (configHelper.getIntValue() % 32) + 1;
        configHelper.setIntValue(currentInt);
        NetworkingSendMsgMethods.SendConfigKeyValFromClientSideToServer(configName, configDetailName, currentInt, false);
    }

    //================================================================================================
    //Initialization for client
    public static void GlobalClientPlayerValuesInit() {
        ClientPlayerSkillID2lvlMap = new HashMap<String, Integer>();
        CPlayerBaseMultiplierStatusMap = new HashMap<String, Double>();
        CPlayerState = new HashMap<String, Integer>();

        CPlayerAssigningSkillPriority = false;
        CPlayerAppendedPeriodForSkillCooldownDelay = false;
        CPlayerAssigningSkillCooldownDelay = false;

        //Keyboard keycode pressing and keycombo to release any skill
        CPlayerAssigningQuickslot = false;
        CPlayerCurrentKeyCombo = new ArrayList<Integer>();
        CPlayerKeyCombo2SkillID = new HashMap<ArrayList<Integer>, HashMap<String, Byte>>();
        CPlayerSkillID2KeyCombo = new HashMap<String, ArrayList<Integer>>();

        CPlayerAssigningKeyCombo = false;
        CPlayerLastPressedKeyCode = -1;
        CPlayerKey2SkillID = new HashMap<Integer, HashMap<String, Byte>>();
        CPlayerSkillID2Key = new HashMap<String, Integer>();

        //Key Action, 0 releasing from key, 1 pressed down, 2 holding
        CPlayerLastKeyAction = -1;

        CPlayerIsImmobilized = false;

        CPlayerLastTriggeredChannelingActiveSkillID = Constants.NOTHING;
        CPlayerLastTriggeredNotChannelingActiveSkillID = Constants.NOTHING;
        CPlayerCurrentActiveSkillID = Constants.NOTHING;

        CPlayerConfig2Settings = new HashMap<String, HashMap<String, MethodConfigHelper>>();
        HashMap<String, MethodConfigHelper> GeneralSetting = new HashMap<String, MethodConfigHelper>();
        GeneralSetting.put(Constants.CONDITION_OPTION_INDEX, new MethodConfigHelper(GlobalClientPlayerValues::DefaultSettingOnOff, 0, true));
        GeneralSetting.put(Constants.PLAY_SKILL_ERR_SOUND, new MethodConfigHelper(GlobalClientPlayerValues::DefaultSettingOnOff, 0, true));
        GeneralSetting.put(Constants.SB_GENERAL_SETTING_LOCK_ON, new MethodConfigHelper(GlobalClientPlayerValues::DefaultSettingOnOff, 0, true));
        GeneralSetting.put(Constants.SB_GENERAL_SETTING_LOCK_ON_RANGE, new MethodConfigHelper(GlobalClientPlayerValues::DefaultSettingLoopIntFrom1to32, 2, false));
        CPlayerConfig2Settings.put(Constants.SB_GENERAL_SETTING, GeneralSetting);

        CPlayerSkillID2Priority = new HashMap<String, Integer>();
        CPlayerSkillID2CooldownDelay = new HashMap<String, Float>();
    }

//    public static void setAllowToPressOtherKeysWhileHolding(boolean isAllow) {
//        if (isAllow) {
//            int tempInt = CPlayerState.getOrDefault(Constants.IS_CHANNELING_ALLOWED_PRESS_OTHER_KEYS, 0) + 1;
//            if (tempInt > 0) {
//                CPlayerState.put(Constants.IS_CHANNELING_ALLOWED_PRESS_OTHER_KEYS, tempInt);
//            }
//            else {
//                CPlayerState.put(Constants.IS_CHANNELING_ALLOWED_PRESS_OTHER_KEYS, 1);
//            }
//        }
//        else {
//            int tempInt = CPlayerState.getOrDefault(Constants.IS_CHANNELING_ALLOWED_PRESS_OTHER_KEYS, 0) - 1;
//            if (tempInt <= 0) {
//                CPlayerState.remove(Constants.IS_CHANNELING_ALLOWED_PRESS_OTHER_KEYS);
//            }
//            else {
//                CPlayerState.put(Constants.IS_CHANNELING_ALLOWED_PRESS_OTHER_KEYS, tempInt);
//            }
//        }
//    }
//
//    public static boolean isAllowedToPressOtherKeysWhileHolding() {
//        if (CPlayerState.containsKey(Constants.IS_CHANNELING_ALLOWED_PRESS_OTHER_KEYS)) {
//            return CPlayerState.get(Constants.IS_CHANNELING_ALLOWED_PRESS_OTHER_KEYS) > 0;
//        }
//        return false;
//    }

    //basic
    public static HashMap<String, Integer> getClientPlayerSkillID2lvlMap() {
        return ClientPlayerSkillID2lvlMap;
    }

    public static void setClientPlayerSkillID2lvlMap(HashMap<String, Integer> clientPlayerSkillID2lvlMap) {
        ClientPlayerSkillID2lvlMap = clientPlayerSkillID2lvlMap;
    }

    public static HashMap<String, Double> getCPlayerBaseMultiplierStatusMap() {
        return CPlayerBaseMultiplierStatusMap;
    }

    public static void setCPlayerBaseMultiplierStatusMap(HashMap<String, Double> playerBaseMultiplierStatusMap) {
        CPlayerBaseMultiplierStatusMap = playerBaseMultiplierStatusMap;
    }

    public static HashMap<String, Integer> getCPlayerState() { return CPlayerState; }
    public static void setCPlayerState(HashMap<String, Integer> cPlayerState) { CPlayerState = cPlayerState; }

    public static boolean getCPlayerIsImmobilized() { return CPlayerIsImmobilized; }
    public static void setCPlayerIsImmobilized(boolean cPlayerIsImmobilized) { CPlayerIsImmobilized = cPlayerIsImmobilized; }

    //Config
    public static HashMap<String, HashMap<String, MethodConfigHelper>> getCPlayerConfig2Settings() { return CPlayerConfig2Settings; }
    public static void setCPlayerConfig2Settings(HashMap<String, HashMap<String, MethodConfigHelper>> cPlayerConfig) { CPlayerConfig2Settings = cPlayerConfig; }

    //Skill tree gui skill priority
    public static boolean isCPlayerAssigningSkillPriority() { return CPlayerAssigningSkillPriority; }
    public static void setCPlayerAssigningSkillPriority(boolean cPlayerAssigningSkillPriority) { CPlayerAssigningSkillPriority = cPlayerAssigningSkillPriority; }
    public static HashMap<String, Integer> getCPlayerSkillID2Priority() { return CPlayerSkillID2Priority; }
    public static void setCPlayerSkillID2Priority(HashMap<String, Integer> cPlayerSkillID2Priority) { CPlayerSkillID2Priority = cPlayerSkillID2Priority; }

    //skill cooldown delay
    public static boolean isCPlayerAssigningSkillCooldownDelay() { return CPlayerAssigningSkillCooldownDelay; }
    public static void setCPlayerAssigningSkillCooldownDelay(boolean cPlayerAssigningSkillCooldownDelay) { CPlayerAssigningSkillCooldownDelay = cPlayerAssigningSkillCooldownDelay; }
    public static HashMap<String, Float> getCPlayerSkillID2CooldownDelay() { return CPlayerSkillID2CooldownDelay; }
    public static void setCPlayerSkillID2CooldownDelay(HashMap<String, Float> cPlayerSkillID2CooldownDelay) { CPlayerSkillID2CooldownDelay = cPlayerSkillID2CooldownDelay; }
    public static boolean isCPlayerAppendedPeriodForSkillCooldownDelay() { return CPlayerAppendedPeriodForSkillCooldownDelay; }
    public static void setCPlayerAppendedPeriodForSkillCooldownDelay(boolean cPlayerAppendedPeriodForSkillCooldownDelay) { CPlayerAppendedPeriodForSkillCooldownDelay = cPlayerAppendedPeriodForSkillCooldownDelay; }

    //skill last Skill triggered
    public static String getCPlayerLastTriggeredNotChannelingActiveSkillID() { return CPlayerLastTriggeredNotChannelingActiveSkillID; }
    public static void setCPlayerLastTriggeredNotChannelingActiveSkillID(String cPlayerLastTriggeredNotChannelingActiveSkillID) { CPlayerLastTriggeredNotChannelingActiveSkillID = cPlayerLastTriggeredNotChannelingActiveSkillID; }
    public static String getCPlayerLastTriggeredChannelingActiveSkillID() { return CPlayerLastTriggeredChannelingActiveSkillID; }
    public static void setCPlayerLastTriggeredChannelingActiveSkillID(String cPlayerLastTriggeredChannelingActiveSkillID) { CPlayerLastTriggeredChannelingActiveSkillID = cPlayerLastTriggeredChannelingActiveSkillID; }

    public static String getCPlayerCurrentActiveSkillID() { return CPlayerCurrentActiveSkillID; }
    public static void setCPlayerCurrentActiveSkillID(String cPlayerCurrentActiveSkillID) { CPlayerCurrentActiveSkillID = cPlayerCurrentActiveSkillID; }

    //================================================================================================
    //Initialization for each login player
    public static void initializeClientPlayerHashMapsWhenLogin() {
        if (ClientPlayerSkillID2lvlMap == null) { ClientPlayerSkillID2lvlMap = new HashMap<String, Integer>(); }
        else { ClientPlayerSkillID2lvlMap.clear(); }
        if (CPlayerBaseMultiplierStatusMap == null) { CPlayerBaseMultiplierStatusMap = new HashMap<String, Double>(); }
        else { CPlayerBaseMultiplierStatusMap.clear(); }
        if (CPlayerState == null) { CPlayerState = new HashMap<String, Integer>(); }
        else { CPlayerState.clear(); }

        CPlayerAssigningSkillPriority = false;
        CPlayerAppendedPeriodForSkillCooldownDelay = false;
        CPlayerAssigningSkillCooldownDelay = false;

        CPlayerLastTriggeredChannelingActiveSkillID = Constants.NOTHING;
        CPlayerLastTriggeredNotChannelingActiveSkillID = Constants.NOTHING;
        CPlayerCurrentActiveSkillID = Constants.NOTHING;

        //keyboard
        CPlayerAssigningQuickslot = false;
        if (CPlayerKey2SkillID == null) { CPlayerKey2SkillID = new HashMap<Integer, HashMap<String, Byte>>(); }
        else { clearCPlayerKey2SkillID(); }
        if (CPlayerSkillID2Key == null) { CPlayerSkillID2Key = new HashMap<String, Integer>(); }
        else { clearCPlayerSkillID2KeyCombo(); }

        CPlayerAssigningKeyCombo = false;
        if (CPlayerKeyCombo2SkillID == null) { CPlayerKeyCombo2SkillID = new HashMap<ArrayList<Integer>, HashMap<String, Byte>>(); }
        else { clearCPlayerKeyCombo2SkillID(); }
        if (CPlayerSkillID2KeyCombo == null) { CPlayerSkillID2KeyCombo = new HashMap<String, ArrayList<Integer>>(); }
        else { clearCPlayerSkillID2KeyCombo(); }

        if (CPlayerConfig2Settings == null) { CPlayerConfig2Settings = new HashMap<String, HashMap<String, MethodConfigHelper>>(); }
        else { CPlayerConfig2Settings.clear(); }
        HashMap<String, MethodConfigHelper> GeneralSetting = new HashMap<String, MethodConfigHelper>();
        GeneralSetting.put(Constants.CONDITION_OPTION_INDEX, new MethodConfigHelper(GlobalClientPlayerValues::DefaultSettingOnOff, 0, true));
        GeneralSetting.put(Constants.PLAY_SKILL_ERR_SOUND, new MethodConfigHelper(GlobalClientPlayerValues::DefaultSettingOnOff, 0, true));
        GeneralSetting.put(Constants.SB_GENERAL_SETTING_LOCK_ON, new MethodConfigHelper(GlobalClientPlayerValues::DefaultSettingOnOff, 0, true));
        GeneralSetting.put(Constants.SB_GENERAL_SETTING_LOCK_ON_RANGE, new MethodConfigHelper(GlobalClientPlayerValues::DefaultSettingLoopIntFrom1to32, 2, false));
        CPlayerConfig2Settings.put(Constants.SB_GENERAL_SETTING, GeneralSetting);

        SBPlayerConfigFileInitHelper configFileInitHelper = new SBPlayerConfigFileInitHelper(null);
        configFileInitHelper.SBClientConfigInitMixinHelper();

        if (CPlayerSkillID2Priority == null) { CPlayerSkillID2Priority = new HashMap<>(); }
        else { CPlayerSkillID2Priority.clear(); }

        if (CPlayerSkillID2CooldownDelay == null) { CPlayerSkillID2CooldownDelay = new HashMap<String, Float>(); }
        else { CPlayerSkillID2CooldownDelay.clear(); }
    }

    //================================================================================================
    //KeyCombo
    public static boolean getCPlayerAssigningKeyCombo() { return CPlayerAssigningKeyCombo; }
    public static void setCPlayerAssigningKeyCombo(boolean cPlayerAssigningKeyCombo) { CPlayerAssigningKeyCombo = cPlayerAssigningKeyCombo; }
    public static ArrayList<Integer> getCPlayerCurrentKeyCombo() { return CPlayerCurrentKeyCombo; }
    public static void setCPlayerCurrentKeyCombo(ArrayList<Integer> cPlayerKeyCombo) { CPlayerCurrentKeyCombo = cPlayerKeyCombo; }
    public static void clearCPlayerCurrentKeyCombo() {
        //[!] use clear will sweep reference to target skill, just make a new one
//        if (CPlayerCurrentKeyCombo != null) {
//            CPlayerCurrentKeyCombo.clear();
//        }
        CPlayerCurrentKeyCombo = new ArrayList<Integer>();
    }
    public static HashMap<ArrayList<Integer>, HashMap<String, Byte>> getCPlayerKeyCombo2SkillID() {
        return CPlayerKeyCombo2SkillID;
    }
    public static void setCPlayerKeyCombo2SkillID(HashMap<ArrayList<Integer>, HashMap<String, Byte>> cPlayerKeyCombo2SkillID) {
        CPlayerKeyCombo2SkillID = cPlayerKeyCombo2SkillID;
    }
    public static void clearCPlayerKeyCombo2SkillID() {
        if (CPlayerKeyCombo2SkillID != null) {
            CPlayerKeyCombo2SkillID.clear();
        }
    }
    public static HashMap<String, ArrayList<Integer>> getCPlayerSkillID2KeyCombo() {
        return CPlayerSkillID2KeyCombo;
    }
    public static void setCPlayerSkillID2KeyCombo(HashMap<String, ArrayList<Integer>> cPlayerSkillID2KeyCombo) {
        CPlayerSkillID2KeyCombo = cPlayerSkillID2KeyCombo;
    }
    public static void clearCPlayerSkillID2KeyCombo() {
        if (CPlayerSkillID2KeyCombo != null) {
            CPlayerSkillID2KeyCombo.clear();
        }
    }
    //QuickSlot
    public static boolean getCPlayerAssigningQuickslot() { return CPlayerAssigningQuickslot; }
    public static void setCPlayerAssigningQuickslot(Boolean cPlayerAssigningQuickslot) { CPlayerAssigningQuickslot = cPlayerAssigningQuickslot; }
    public static int getCPlayerLastPressedKeyCode() { return CPlayerLastPressedKeyCode; }
    public static void setCPlayerLastPressedKeyCode(Integer cPlayerLastPressedKeyCode) { CPlayerLastPressedKeyCode = cPlayerLastPressedKeyCode; }
    public static HashMap<Integer, HashMap<String, Byte>> getCPlayerKey2SkillID() { return CPlayerKey2SkillID; }
    public static void setCPlayerKey2SkillID(HashMap<Integer, HashMap<String, Byte>> cPlayerKey2SkillID) { CPlayerKey2SkillID = cPlayerKey2SkillID; }
    public static void clearCPlayerKey2SkillID() {
        if (CPlayerKey2SkillID != null) {
            CPlayerKey2SkillID.clear();
        }
    }
    public static HashMap<String, Integer> getCPlayerSkillID2Key() {
        return CPlayerSkillID2Key;
    }
    public static void setCPlayerSkillID2Key(HashMap<String, Integer> cPlayerSkillID2Key) {
        CPlayerSkillID2Key = cPlayerSkillID2Key;
    }
    public static void clearCPlayerSkillID2Key() {
        if (CPlayerSkillID2Key != null) {
            CPlayerSkillID2Key.clear();
        }
    }

    public static void resetSkillPriority() {
        CPlayerSkillID2Priority.clear();
        FileReadWriteMethods.SkillPriorityPlayernameFileWriteTo();
    }

    public static void resetSkillCooldownDelay() {
        CPlayerSkillID2CooldownDelay.clear();
        FileReadWriteMethods.SkillCooldownDelayPlayernameFileWriteTo();
    }

    public static void resetQuickSlotSetting() {
//        setCPlayerKey2SkillID(new HashMap<Integer, String>());
//        setCPlayerSkillID2Key(new HashMap<String, Integer>());
        clearCPlayerKey2SkillID();
        clearCPlayerSkillID2Key();
        FileReadWriteMethods.ClientQuickslotPlayernameFileWriteTo();
    }

    public static void resetKeyComboSetting() {
//        setCPlayerKeyCombo2SkillID(new HashMap<ArrayList<Integer>, String>());
//        setCPlayerSkillID2KeyCombo(new HashMap<String, ArrayList<Integer>>());
        clearCPlayerKeyCombo2SkillID();
        clearCPlayerSkillID2KeyCombo();
        FileReadWriteMethods.ClientKeycomboPlayernameFileWriteTo();
    }

    public static int getCPlayerLastKeyAction() { return CPlayerLastKeyAction; }
    public static void setCPlayerLastKeyAction(int cPlayerLastKeyAction) { CPlayerLastKeyAction = cPlayerLastKeyAction; }
}

package net.dingdingduang.somebasicskills.keyboard;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.event.SBPlayerConfigFileInitHelper;
import net.dingdingduang.somebasicskills.gui.overlay.SkillsInCooldownClientTimerOverlay;
import net.dingdingduang.somebasicskills.networking.NetworkingSendMsgMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;

import java.util.ArrayList;
import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftInstance;
import static net.dingdingduang.somebasicskills.globalmethods.SoundMethods.PlayLocalInCooldownSound;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.*;
import static net.dingdingduang.somebasicskills.keyboard.keyboardoverlaytimer.PlayerKeyComboListenerOverlayTimer.getPlayerKerboardOverlayTimerInstance;
import static net.dingdingduang.somebasicskills.gui.overlay.SkillChannelingOverlay.getSkillChannelingOverlayInstance;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class KeyMappingInit {
    private static HashMap<Integer, Integer> Key2Action = new HashMap<>();
    private static boolean previousKeyActionIsHolding = false;

//    public static void KeyMappingInitialization() {
//        Key2Action = new HashMap<>();
//        previousKeyActionIsHolding = false;
//    }

    public static HashMap<Integer, Integer> getKey2Action() { return Key2Action; }
    public static void setKey2Action(HashMap<Integer, Integer> key2Action) { Key2Action = key2Action; }

    public static int getKeyAction(int keyCode) { return (Key2Action.containsKey(keyCode) ? Key2Action.get(keyCode): -1); }
    public static void setKeyAction(int keyCode, int keyAction) { Key2Action.put(keyCode, keyAction); }

    //releasing a pressed key
    private static boolean isKeyActionReleased(int keyAction) { return keyAction == 0; }
    //pressing down on a key
    private static boolean isKeyActionDown(int keyAction) { return keyAction == 1; }
    //holding a key
    private static boolean isKeyActionHolding(int keyAction) { return keyAction == 2; }

    @EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
    private static class SBSInputEvents {
        @SubscribeEvent
        public static void SBSInputEvent(InputEvent.Key event) {
            Minecraft minecraft = getMinecraftInstance();

            if (minecraft.player == null) { return; }

            int tempKeyCode = event.getKey();
            int tempKeyAction = event.getAction();
            if (!isKeyActionHolding(tempKeyAction) && previousKeyActionIsHolding) {
                previousKeyActionIsHolding = false;
            }
            if (!previousKeyActionIsHolding) {
                Key2Action.put(tempKeyCode, tempKeyAction);
            }

            setCPlayerLastKeyAction(tempKeyAction);

            if (isKeyDown(event)) {
                setCPlayerLastPressedKeyCode(tempKeyCode);

                if (getCPlayerAssigningKeyCombo()) {
                    ArrayList<Integer> tempKeyComboMap = getCPlayerCurrentKeyCombo();

                    tempKeyComboMap.add(tempKeyCode);
                }
                if (isCPlayerAssigningSkillPriority() && tempKeyCode >= 48 && tempKeyCode < 58) {
                    ArrayList<Integer> tempKeyComboMap = getCPlayerCurrentKeyCombo();

                    if (tempKeyComboMap.size() < 8) {
                        tempKeyComboMap.add(tempKeyCode);
                    }
                }
                if (isCPlayerAssigningSkillCooldownDelay()) {
                    if (tempKeyCode >= 48 && tempKeyCode < 58) {
                        ArrayList<Integer> tempKeyComboMap = getCPlayerCurrentKeyCombo();
                        if (tempKeyComboMap.size() < 8) {
                            tempKeyComboMap.add(tempKeyCode);
                        }
                    }
                    else if (!isCPlayerAppendedPeriodForSkillCooldownDelay() && tempKeyCode == 46) {
                        setCPlayerAppendedPeriodForSkillCooldownDelay(true);
                        ArrayList<Integer> tempKeyComboMap = getCPlayerCurrentKeyCombo();
                        if (tempKeyComboMap.isEmpty()) {
                            tempKeyComboMap.add(48);
                            tempKeyComboMap.add(tempKeyCode);
                        }
                        else if (tempKeyComboMap.size() < 8) {
                            tempKeyComboMap.add(tempKeyCode);
                        }
                    }
                }

                //activate skill if no screen
                if (minecraft.screen != null) { return; }
                //update current key combo and activate skill if matched
                boolean triggeredPreviousSkillByKeyCombo = getPlayerKerboardOverlayTimerInstance().setStartKeyComboListeningTimer(true, tempKeyCode);

//                String SkillID;
                HashMap<Integer, HashMap<String, Byte>> key2Skill = getCPlayerKey2SkillID();
                if (!triggeredPreviousSkillByKeyCombo && key2Skill.containsKey(tempKeyCode)) {
                    boolean isExecuted = false;
                    HashMap<String, Byte> SkillIDList = key2Skill.get(tempKeyCode);
                    String SkillID = null;
                    HashMap<String, Integer> SkillID2PriorityMap = getCPlayerSkillID2Priority();
                    int SkillPriority = 0;
                    SkillDataJson skill1;
                    for (String tempSkillID: SkillIDList.keySet()) {
                        //start client cooldown timer
                        if (getClientPlayerSkillID2lvlMap().containsKey(tempSkillID) && getClientPlayerSkillID2lvlMap().get(tempSkillID) > 0 && getID2SkillData().containsKey(tempSkillID)) {
                            skill1 = getID2SkillData().get(tempSkillID);
                            boolean passedConditionRequirement = true;
                            if (skill1.getClientConditionRequirement() != null) {
                                passedConditionRequirement = skill1.getClientConditionRequirement().executeAction(tempSkillID);
                            }
                            if (passedConditionRequirement) {
                                int currentTempSkillPriority = 0;
                                //if matched condition, then check priority condition
                                if (SkillID2PriorityMap.containsKey(tempSkillID) && (currentTempSkillPriority = SkillID2PriorityMap.get(tempSkillID)) >= SkillPriority) {
                                    SkillPriority = currentTempSkillPriority;
                                    SkillID = tempSkillID;
                                }
                                else if (currentTempSkillPriority >= SkillPriority) {
                                    SkillID = tempSkillID;
                                }
                            }
//                            else {
//                                printInGameMsg("tempID1 failed: " + tempSkillID);
//                            }
                        }
                        // if player doesnt have skill, dont play failed sound
                        else { isExecuted = true; }
                    }

                    if (SkillID != null) {
                        skill1 = getID2SkillData().get(SkillID);
                        if (skill1.isActiveType() && skill1.getActiveSkillAction1() != null) {
                            if (skill1.isChannelingCast()) {
                                setCPlayerLastTriggeredChannelingActiveSkillID(SkillID);
                                getSkillChannelingOverlayInstance().setChannelingTimer(true, SkillID, tempKeyCode);
                            }
                            else {
                                int clientSkillLVL = getClientPlayerSkillID2lvlMap().get(SkillID);
                                setCPlayerLastTriggeredNotChannelingActiveSkillID(SkillID);
                                setCPlayerCurrentActiveSkillID(SkillID);

                                boolean isLockOnActionSent = false;
                                try {
                                    if (skill1.canLockOnEntity() && getCPlayerConfig2Settings().get(Constants.SB_GENERAL_SETTING).get(Constants.SB_GENERAL_SETTING_LOCK_ON).getIntValue() != 0) {
                                        SBPlayerConfigFileInitHelper tempConfigHelper = new SBPlayerConfigFileInitHelper(null);
                                        float facingAngle = tempConfigHelper.setLockOnFacingAngle();
                                        isLockOnActionSent = true;
                                        NetworkingSendMsgMethods.SendSkillActionWithLockOnFromClientSideToServer(SkillID, skill1.getCooldownTime().get(clientSkillLVL - 1).floatValue(), facingAngle);
                                    }
                                }
                                catch (Exception ignored) {

                                }

                                if (!isLockOnActionSent) {
                                    NetworkingSendMsgMethods.SendSkillActionFromClientSideToServer(SkillID, skill1.getCooldownTime().get(clientSkillLVL - 1).floatValue());
                                }
                            }
                            SkillsInCooldownClientTimerOverlay ClientCDTimer = SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance();
                            ClientCDTimer.setCooldownTimer(true, SkillID);
                        }
                        if (skill1.getClientCondReqPassedAction() != null) {
                            skill1.getClientCondReqPassedAction().executeAction(SkillID);
                        }
                        isExecuted = true;
                    }

                    if (!isExecuted) {
                        PlayLocalInCooldownSound(1.0f, 0.7f);
                    }
                }
            }

        }

        //releasing a pressed key
        private static boolean isKeyReleased(InputEvent.Key e) {
            return e.getAction() == 0;
        }

        //pressing down on a key
        private static boolean isKeyDown(InputEvent.Key e) {
            return e.getAction() == 1;
        }

        //holding a key
        private static boolean isKeyHolding(InputEvent.Key e) {
            return e.getAction() == 2;
        }
    }
}

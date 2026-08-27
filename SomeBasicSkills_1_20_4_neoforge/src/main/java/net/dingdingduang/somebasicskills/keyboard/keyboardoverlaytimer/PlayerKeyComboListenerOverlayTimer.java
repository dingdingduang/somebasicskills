package net.dingdingduang.somebasicskills.keyboard.keyboardoverlaytimer;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.event.SBPlayerConfigFileInitHelper;
import net.dingdingduang.somebasicskills.gui.overlay.SkillsInCooldownClientTimerOverlay;
import net.dingdingduang.somebasicskills.networking.NetworkingSendMsgMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.gui.overlay.ExtendedGui;
import net.neoforged.neoforge.client.gui.overlay.IGuiOverlay;

import java.util.ArrayList;
import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.SoundMethods.PlayLocalInCooldownSound;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.*;
import static net.dingdingduang.somebasicskills.gui.overlay.SkillChannelingOverlay.getSkillChannelingOverlayInstance;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

@OnlyIn(value = Dist.CLIENT)
public class PlayerKeyComboListenerOverlayTimer implements IGuiOverlay {
    private boolean StartTimer = false;
    private static final PlayerKeyComboListenerOverlayTimer PLAYER_KEY_COMBO_LISTENER_OVERLAY_TIMER_INSTANCE = new PlayerKeyComboListenerOverlayTimer();

    //20 ticks = 1 sec
//    private int keyboardStartTick = 0;
//    private int keyboardTotalTick = 30;

    private int Player2Ticks;

    private boolean TriggeredPreviousSkillID = false;
    private int PreviousSkillIDLastKeyCode = -1;
    private String PreviousSkillID = Constants.NOTHING;

    @Override
    public void render(ExtendedGui forgeGui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        if (this.StartTimer) {
            if (this.TriggeredPreviousSkillID) {
                this.TriggeredPreviousSkillID = false;
                return;
            }
            int i, arrSize, comboArrSize;
            boolean matched;
            HashMap<String, Byte> matchedSkillIDList;
            ArrayList<Integer> matchedKeyCodeList;
            HashMap<ArrayList<Integer>, HashMap<String, Byte>> tempMap;

            i = this.Player2Ticks + 1;
            //1.3 sec for keycombo
            if (i > 26) {
                this.Player2Ticks = -1;
//                printInGameMsg("current keycombo: "+getCPlayerCurrentKeyCombo().get(cp1));
//                setCPlayerCurrentKeyCombo(new ArrayList<Integer>());
                clearCPlayerCurrentKeyCombo();
                this.StartTimer = false;
//                return;
                this.TriggeredPreviousSkillID = false;
                this.PreviousSkillIDLastKeyCode = -1;
                this.PreviousSkillID = Constants.NOTHING;
//                this.FailToTriggerCount = 0;
            }
            else {
                this.Player2Ticks = i;
                matchedKeyCodeList = getCPlayerCurrentKeyCombo();
                arrSize = matchedKeyCodeList.size();

                tempMap = getCPlayerKeyCombo2SkillID();
                for (ArrayList<Integer> keyCodeList: tempMap.keySet()) {
                    matched = false;
                    comboArrSize = keyCodeList.size();

                    //use equals for int compare?
                    if (arrSize >= comboArrSize) {
                        for (int j = 0; j < comboArrSize; j++) {
//                            if (matchedKeyCodeList.get(arrSize - comboArrSize + j) != (keyCodeList.get(j))) {
                            if (!matchedKeyCodeList.get(arrSize - comboArrSize + j).equals(keyCodeList.get(j))) {
                                break;
                            }
                            else {
                                if (j >= comboArrSize-1) {
                                    matched = true;
                                }
                            }
                        }
                    }


                    if (matched) {
//                            printInGameMsg("matched!!!");
                        matchedSkillIDList = tempMap.get(keyCodeList);
                        String matchedSkillIDWithHighestPriority = null;
                        HashMap<String, Integer> SkillID2PriorityMap = getCPlayerSkillID2Priority();
                        int SkillPriority = 0;
                        SkillDataJson skill1;
                        boolean isExecuted = false;
                        for (String matchedSkillID: matchedSkillIDList.keySet()) {
                            //start client cooldown timer
                            if (getClientPlayerSkillID2lvlMap().containsKey(matchedSkillID) && getClientPlayerSkillID2lvlMap().get(matchedSkillID) > 0 && getID2SkillData().containsKey(matchedSkillID)) {
                                skill1 = getID2SkillData().get(matchedSkillID);
                                boolean passedConditionRequirement = true;
                                if (skill1.getClientConditionRequirement() != null) {
                                    passedConditionRequirement = skill1.getClientConditionRequirement().executeAction(matchedSkillID);
                                }
                                if (passedConditionRequirement) {
                                    int currentTempSkillPriority = 0;
                                    //if matched condition, then check priority condition
                                    if (SkillID2PriorityMap.containsKey(matchedSkillID) && (currentTempSkillPriority = SkillID2PriorityMap.get(matchedSkillID)) >= SkillPriority) {
                                        SkillPriority = currentTempSkillPriority;
                                        matchedSkillIDWithHighestPriority = matchedSkillID;
                                    }
                                    else if (currentTempSkillPriority >= SkillPriority) {
                                        matchedSkillIDWithHighestPriority = matchedSkillID;
                                    }
                                }
//                            else {
//                                printInGameMsg("tempID1 failed: " + matchedSkillID);
//                            }
                            }
                            // if player doesnt have skill, dont play failed sound
                            else { isExecuted = true; }
                        }

                        if (matchedSkillIDWithHighestPriority != null) {
                            skill1 = getID2SkillData().get(matchedSkillIDWithHighestPriority);
                            if (skill1.isActiveType() && skill1.getActiveSkillAction1() != null) {
                                if (skill1.isChannelingCast()) {
                                    setCPlayerLastTriggeredChannelingActiveSkillID(matchedSkillIDWithHighestPriority);
                                    setCPlayerCurrentActiveSkillID(matchedSkillIDWithHighestPriority);
                                    getSkillChannelingOverlayInstance().setChannelingTimer(true, matchedSkillIDWithHighestPriority, keyCodeList.get(keyCodeList.size() - 1));
                                }
                                else {
//                                    int clientSkillLVL = getClientPlayerSkillID2lvlMap().get(matchedSkillIDWithHighestPriority);
//                                    NetworkingSendMsgMethods.SendSkillActionFromClientSideToServer(matchedSkillIDWithHighestPriority, skill1.getCooldownTime().get(clientSkillLVL - 1).floatValue());

                                    setCPlayerLastTriggeredNotChannelingActiveSkillID(matchedSkillIDWithHighestPriority);
                                    setCPlayerCurrentActiveSkillID(matchedSkillIDWithHighestPriority);
                                    int clientSkillLVL = getClientPlayerSkillID2lvlMap().get(matchedSkillIDWithHighestPriority);

                                    boolean isLockOnActionSent = false;
                                    try {
                                        if (skill1.canLockOnEntity() && getCPlayerConfig2Settings().get(Constants.SB_GENERAL_SETTING).get(Constants.SB_GENERAL_SETTING_LOCK_ON).getIntValue() != 0) {
                                            SBPlayerConfigFileInitHelper tempConfigHelper = new SBPlayerConfigFileInitHelper(null);
                                            float facingAngle = tempConfigHelper.setLockOnFacingAngle();
                                            isLockOnActionSent = true;
                                            NetworkingSendMsgMethods.SendSkillActionWithLockOnFromClientSideToServer(matchedSkillIDWithHighestPriority, skill1.getCooldownTime().get(clientSkillLVL - 1).floatValue(), facingAngle);
                                        }
                                    }
                                    catch (Exception ignored) {

                                    }

                                    if (!isLockOnActionSent) {
                                        NetworkingSendMsgMethods.SendSkillActionFromClientSideToServer(matchedSkillIDWithHighestPriority, skill1.getCooldownTime().get(clientSkillLVL - 1).floatValue());
                                    }
                                }
                                SkillsInCooldownClientTimerOverlay ClientCDTimer = SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance();
                                ClientCDTimer.setCooldownTimer(true, matchedSkillIDWithHighestPriority);
                            }
                            if (skill1.getClientCondReqPassedAction() != null) {
                                skill1.getClientCondReqPassedAction().executeAction(matchedSkillIDWithHighestPriority);
                            }

                            isExecuted = true;
                            this.PreviousSkillIDLastKeyCode = keyCodeList.get(keyCodeList.size()-1);
                            this.PreviousSkillID = matchedSkillIDWithHighestPriority;
                        }

                        if (!isExecuted) { PlayLocalInCooldownSound(1.0f, 0.7f); }

                        this.Player2Ticks = -1;
                        clearCPlayerCurrentKeyCombo();
                        this.StartTimer = false;

                        break;
                    }
                }
            }
        }
    }

    public boolean isStartTimerActive() { return this.StartTimer; }
    public boolean setStartKeyComboListeningTimer(boolean a, int keyCode) {
        this.StartTimer = a;

        boolean triggeredPreviousSkillByKeyCombo = false;
        if (this.PreviousSkillIDLastKeyCode == keyCode) {
//            this.FailToTriggerCount++;
            SkillDataJson skill1 = getID2SkillData().get(this.PreviousSkillID);
            boolean passedConditionRequirement = true;
            if (skill1.getClientConditionRequirement() != null) {
                passedConditionRequirement = skill1.getClientConditionRequirement().executeAction(this.PreviousSkillID);
            }
            if (passedConditionRequirement) {
                if (skill1.isActiveType() && skill1.getActiveSkillAction1() != null) {
                    if (skill1.isChannelingCast()) {
                        setCPlayerLastTriggeredChannelingActiveSkillID(this.PreviousSkillID);
                        getSkillChannelingOverlayInstance().setChannelingTimer(true, this.PreviousSkillID, this.PreviousSkillIDLastKeyCode);
                    } else {
                        setCPlayerLastTriggeredNotChannelingActiveSkillID(this.PreviousSkillID);
                        int clientSkillLVL = getClientPlayerSkillID2lvlMap().get(this.PreviousSkillID);

                        boolean isLockOnActionSent = false;
                        try {
                            if (skill1.canLockOnEntity() && getCPlayerConfig2Settings().get(Constants.SB_GENERAL_SETTING).get(Constants.SB_GENERAL_SETTING_LOCK_ON).getIntValue() != 0) {
                                SBPlayerConfigFileInitHelper tempConfigHelper = new SBPlayerConfigFileInitHelper(null);
                                float facingAngle = tempConfigHelper.setLockOnFacingAngle();
                                isLockOnActionSent = true;
                                NetworkingSendMsgMethods.SendSkillActionWithLockOnFromClientSideToServer(this.PreviousSkillID, skill1.getCooldownTime().get(clientSkillLVL - 1).floatValue(), facingAngle);
                            }
                        } catch (Exception ignored) {

                        }

                        if (!isLockOnActionSent) {
                            NetworkingSendMsgMethods.SendSkillActionFromClientSideToServer(this.PreviousSkillID, skill1.getCooldownTime().get(clientSkillLVL - 1).floatValue());
                        }
                    }
                    SkillsInCooldownClientTimerOverlay ClientCDTimer = SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance();
                    ClientCDTimer.setCooldownTimer(true, this.PreviousSkillID);
                }
                if (skill1.getClientCondReqPassedAction() != null) {
                    skill1.getClientCondReqPassedAction().executeAction(this.PreviousSkillID);
                }
                triggeredPreviousSkillByKeyCombo = true;
//                this.FailToTriggerCount = 0;
            }
        }

//        printInGameMsg("is nothing: "+(getCPlayerCurrentActiveSkillID().hashCode() == Constants.NOTHING.hashCode()));
        if (!triggeredPreviousSkillByKeyCombo && getCPlayerCurrentActiveSkillID().hashCode() == Constants.NOTHING.hashCode()) {
//            ArrayList<Integer> tempKeyComboMap = getCPlayerCurrentKeyCombo();
            getCPlayerCurrentKeyCombo().add(keyCode);
            this.PreviousSkillIDLastKeyCode = -1;
            this.PreviousSkillID = Constants.NOTHING;
        }

        this.Player2Ticks = 0;
        return triggeredPreviousSkillByKeyCombo;
    }

    public static PlayerKeyComboListenerOverlayTimer getPlayerKerboardOverlayTimerInstance() {
        return PLAYER_KEY_COMBO_LISTENER_OVERLAY_TIMER_INSTANCE;
    }
}

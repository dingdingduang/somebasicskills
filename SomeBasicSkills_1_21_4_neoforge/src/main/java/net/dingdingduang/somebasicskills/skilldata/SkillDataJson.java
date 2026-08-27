package net.dingdingduang.somebasicskills.skilldata;

import net.dingdingduang.somebasicskills.util.*;

import java.util.ArrayList;

public class SkillDataJson {
    private String RoleCategoryName;
    private String SkillID;
    private String translatableTextSkillTreeTitle;
    private String translatableTextTitle;
    private String translatableTextDescription;
    private MethodTooltipGetter translatableTooltipGetter;

    private String resLocBtn;
    private String resLocDisBtn;
    private String resLocHoveredBtn;

    private int btnPosX;
    private int btnPosY;

    private int SkillType; //0 Active, 1 Passive, 2 Active and Passive
    private int SkillCastType; //0 instant, 1 channeling
    private boolean ChannelingShouldHoldKey;
    private boolean ChannelingShouldShowWindow;
    private boolean ShouldChannelingTimeBeFixed;
    private int SkillTarget; //0 no target, 1 target entity, 2 point target
    private int SkillDamageType; //0 general attack damage, 1 magic damage

    private int requiredLevel;
    private int lvlUpgInterval;
    private int xpCostPerLeveling;
    private int totalLevel;

    private boolean CanLockOnEntity;

    private ArrayList<String> preRequisiteSkillID;
    private ArrayList<Integer> preRequisiteSkillRequiredLVL;
    private ArrayList<String> advancedSkillID;

    private ArrayList<Double> duration;
    private ArrayList<Double> distance;
    private ArrayList<Double> effectArea;

    private ArrayList<Double> stationaryTime;
    private ArrayList<Double> cooldownTime;
    private ArrayList<Double> channelingTime;

    private ArrayList<Double> valueUsage_01;
    private ArrayList<Double> valueUsage_02;
    private ArrayList<Double> ValueMultiplierList_01;
    private ArrayList<Double> ValueMultiplierList_02;
    private ArrayList<Double> DamageList_01;
    private ArrayList<Double> DamageList_02;
    private ArrayList<Double> DamageList_03;
    private ArrayList<Double> DamageMultiplierList_01;
    private ArrayList<Double> DamageMultiplierList_02;
    private ArrayList<Double> DamageMultiplierList_03;

    private ArrayList<Boolean> customBooleanList;
    private ArrayList<Integer> customIntegerList;
    private ArrayList<Double> customDoubleList;
    private ArrayList<String> customStringList;

    private SkillClientConditionRequirement ClientConditionRequirement;
    private SkillClientCondReqPassedAction ClientCondReqPassedAction;
    private SkillServerConditionRequirement ServerConditionRequirement;

    private MethodAction ActiveSkillAction1;
    private MethodAction ActiveSkillAction2;
    private MethodAction ActiveSkillAction3;
    private MethodAction PassiveSkillAction1;
    private MethodAction PassiveSkillAction2;
    private MethodAction PassiveSkillAction3;
    private MethodAction ChannelingSkillAction1;
    private MethodAction ClearSkillAction;

    private MethodAction SetupPassiveStatusWhenLoginAction;
    private MethodAction PressAddBtnAction;
    private MethodAction PressSubBtnAction;

    public String getRoleCategoryName() { return this.RoleCategoryName; }
    public void setRoleCategoryName(String roleCategoryName) { this.RoleCategoryName = roleCategoryName; }
    public String getSkillID() { return this.SkillID;}
    public void setSkillID(String skillID) { this.SkillID = skillID; }

    public String getTranslatableTextSkillTreeTitle() { return this.translatableTextSkillTreeTitle; }
    public void setTranslatableTextSkillTreeTitle(String translatableTextSkillTreeTitle) { this.translatableTextSkillTreeTitle = translatableTextSkillTreeTitle; }
    public String getTranslatableTextTitle() { return this.translatableTextTitle; }
    public void setTranslatableTextTitle(String translatableText) { this.translatableTextTitle = translatableText; }
    public String getTranslatableTextDescription() { return this.translatableTextDescription; }
    public void setTranslatableTextDescription(String translatableText) { this.translatableTextDescription = translatableText; }
    public MethodTooltipGetter getTranslatableTooltipGetter() { return this.translatableTooltipGetter; }
    public void setTranslatableTooltipGetter(MethodTooltipGetter translatableTooltipGetter) { this.translatableTooltipGetter = translatableTooltipGetter; }

    public String getResLocBtn() { return resLocBtn; }
    public void setResLocBtn(String resLocBtn) { this.resLocBtn = resLocBtn; }
    public String getResLocDisBtn() { return this.resLocDisBtn; }
    public void setResLocDisBtn(String resLocDisBtn) { this.resLocDisBtn = resLocDisBtn; }
    public String getResLocHoveredBtn() { return this.resLocHoveredBtn; }
    public void setResLocHoveredBtn(String resLocHoveredBtn) { this.resLocHoveredBtn = resLocHoveredBtn; }

    public int getBtnPosX() { return this.btnPosX; }
    public void setBtnPosX(int btnPosX) { this.btnPosX = btnPosX; }
    public int getBtnPosY() { return this.btnPosY; }
    public void setBtnPosY(int btnPosY) { this.btnPosY = btnPosY; }

    public int getSkillType() { return this.SkillType; }
    public void setSkillType(int skillType) { this.SkillType = skillType; }
    public boolean isActiveType() { return this.SkillType == 0; }
    public boolean isPassiveType() { return this.SkillType == 1; }
    public boolean isBothType() { return this.SkillType == 2; }

    public int getSkillCastType() { return this.SkillCastType; }
    public void setSkillCastType(int skillCastType) { this.SkillCastType = skillCastType; }
    public boolean isInstantCast() { return this.SkillCastType == 0; }
    public boolean isChannelingCast() { return this.SkillCastType == 1; }
    public boolean isChannelingShouldHoldKey() { return this.ChannelingShouldHoldKey; }
    public void setChannelingShouldHoldKey(boolean ChannelingShouldHoldKey) { this.ChannelingShouldHoldKey = ChannelingShouldHoldKey; }
    public boolean isChannelingShouldShowWindow() { return this.ChannelingShouldShowWindow; }
    public void setChannelingShouldShowWindow(boolean ChannelingShouldShowWindow) { this.ChannelingShouldShowWindow = ChannelingShouldShowWindow; }
    public boolean shouldChannelingTimeBeFixed() { return this.ShouldChannelingTimeBeFixed; }
    public void setChannelingTimeBeFixed(boolean shouldChannelingTimeBeFixed) { this.ShouldChannelingTimeBeFixed = shouldChannelingTimeBeFixed;}

    public int getSkillTarget() { return this.SkillTarget; }
    public void setSkillTarget(int skillTarget) { this.SkillTarget = skillTarget; }
    public boolean hasNoTarget() { return this.SkillTarget == 0; }
    public boolean hasTarget() { return this.SkillTarget == 1; }
    public boolean hasPointTarget() { return this.SkillTarget == 2; }

    public int getSkillDamageType() { return this.SkillDamageType; }
    public void setSkillDamageType(int skillDamageType) { this.SkillDamageType = skillDamageType; }
    public boolean isPhysicalDamage() { return this.SkillDamageType == 0; }
    public boolean isMagicDamage() { return this.SkillDamageType == 1; }

    public int getRequiredLevel() { return this.requiredLevel; }
    public void setRequiredLevel(int requiredLevel) { this.requiredLevel = requiredLevel; }
    public int getLvlUpgInterval() { return this.lvlUpgInterval; }
    public void setLvlUpgInterval(int lvlUpgInterval) { this.lvlUpgInterval = lvlUpgInterval; }
    public int getXpCostPerLeveling() { return this.xpCostPerLeveling; }
    public void setXpCostPerLeveling(int xpCostPerLeveling) { this.xpCostPerLeveling = xpCostPerLeveling; }
    public int getTotalLevel() { return this.totalLevel; }
    public void setTotalLevel(int totalLevel) { this.totalLevel = totalLevel; }

    public boolean canLockOnEntity() { return this.CanLockOnEntity; }
    public void setLockOnEntity(boolean canLockOnEntity) { this.CanLockOnEntity = canLockOnEntity; }

    public ArrayList<String> getPreRequisiteSkillID() { return this.preRequisiteSkillID; }
    public void setPreRequisiteSkillID(ArrayList<String> preRequisiteSkillID) { this.preRequisiteSkillID = preRequisiteSkillID; }
    public ArrayList<Integer> getPreRequisiteSkillRequiredLVL() { return this.preRequisiteSkillRequiredLVL; }
    public void setPreRequisiteSkillRequiredLVL(ArrayList<Integer> preRequisiteSkillRequiredLVL) { this.preRequisiteSkillRequiredLVL = preRequisiteSkillRequiredLVL; }
    public ArrayList<String> getAdvancedSkillID() { return this.advancedSkillID; }
    public void setAdvancedSkillID(ArrayList<String> advancedSkillID) { this.advancedSkillID = advancedSkillID; }

    public ArrayList<Double> getDuration() { return this.duration; }
    public void setDuration(ArrayList<Double> duration) { this.duration = duration; }
    public ArrayList<Double> getDistance() { return this.distance; }
    public void setDistance(ArrayList<Double> distance) { this.distance = distance; }
    public ArrayList<Double> getEffectArea() { return this.effectArea; }
    public void setEffectArea(ArrayList<Double> effectArea) { this.effectArea = effectArea; }

    public ArrayList<Double> getStationaryTime() { return this.stationaryTime; }
    public void setStationaryTime(ArrayList<Double> StationaryTime) { this.stationaryTime = StationaryTime; }
    public ArrayList<Double> getCooldownTime() { return this.cooldownTime; }
    public void setCooldownTime(ArrayList<Double> cooldownTime) { this.cooldownTime = cooldownTime; }
    public ArrayList<Double> getChannelingTime() { return this.channelingTime; }
    public void setChannelingTime(ArrayList<Double> channelingTime) { this.channelingTime = channelingTime; }

    public ArrayList<Double> getValueUsage_01() { return this.valueUsage_01; }
    public void setValueUsage_01(ArrayList<Double> valueUsage_01) { this.valueUsage_01 = valueUsage_01; }
    public ArrayList<Double> getValueUsage_02() { return this.valueUsage_02; }
    public void setValueUsage_02(ArrayList<Double> valueUsage_02) { this.valueUsage_02 = valueUsage_02; }
    public ArrayList<Double> getValueMultiplierList_01() { return this.ValueMultiplierList_01; }
    public void setValueMultiplierList_01(ArrayList<Double> valueMultiplierList_01) { this.ValueMultiplierList_01 = valueMultiplierList_01; }
    public ArrayList<Double> getValueMultiplierList_02() { return this.ValueMultiplierList_02; }
    public void setValueMultiplierList_02(ArrayList<Double> valueMultiplierList_02) { this.ValueMultiplierList_02 = valueMultiplierList_02; }

    public ArrayList<Double> getDamageList_01() { return this.DamageList_01; }
    public void setDamageList_01(ArrayList<Double> damageList_01) { this.DamageList_01 = damageList_01; }
    public ArrayList<Double> getDamageList_02() { return this.DamageList_02; }
    public void setDamageList_02(ArrayList<Double> damageList_02) { this.DamageList_02 = damageList_02; }
    public ArrayList<Double> getDamageList_03() { return this.DamageList_03; }
    public void setDamageList_03(ArrayList<Double> damageList_03) { this.DamageList_03 = damageList_03; }
    public ArrayList<Double> getDamageMultiplierList_01() { return this.DamageMultiplierList_01; }
    public void setDamageMultiplierList_01(ArrayList<Double> damageMultiplierList_01) { this.DamageMultiplierList_01 = damageMultiplierList_01; }
    public ArrayList<Double> getDamageMultiplierList_02() { return this.DamageMultiplierList_02; }
    public void setDamageMultiplierList_02(ArrayList<Double> damageMultiplierList_02) { this.DamageMultiplierList_02 = damageMultiplierList_02; }
    public ArrayList<Double> getDamageMultiplierList_03() { return this.DamageMultiplierList_03; }
    public void setDamageMultiplierList_03(ArrayList<Double> damageMultiplierList_03) { this.DamageMultiplierList_03 = damageMultiplierList_03; }

    public ArrayList<Boolean> getCustomBooleanList() { return this.customBooleanList; }
    public void setCustomBooleanList(ArrayList<Boolean> customBooleanList) { this.customBooleanList = customBooleanList; }
    public ArrayList<Integer> getCustomIntegerList() { return this.customIntegerList; }
    public void setCustomIntegerList(ArrayList<Integer> customIntegerList) { this.customIntegerList = customIntegerList; }
    public ArrayList<Double> getCustomDoubleList() { return this.customDoubleList; }
    public void setCustomDoubleList(ArrayList<Double> customDoubleList) { this.customDoubleList = customDoubleList; }
    public ArrayList<String> getCustomStringList() { return this.customStringList; }
    public void setCustomStringList(ArrayList<String> customStringList) { this.customStringList = customStringList; }

    public SkillClientConditionRequirement getClientConditionRequirement() { return this.ClientConditionRequirement; }
    public void setClientConditionRequirement(SkillClientConditionRequirement conditionRequirement) { this.ClientConditionRequirement = conditionRequirement; }
    public SkillClientCondReqPassedAction getClientCondReqPassedAction() { return this.ClientCondReqPassedAction; }
    public void setClientCondReqPassedAction(SkillClientCondReqPassedAction clientCondReqPassedAction) { this.ClientCondReqPassedAction = clientCondReqPassedAction; }
    public SkillServerConditionRequirement getServerConditionRequirement() { return this.ServerConditionRequirement; }
    public void setServerConditionRequirement(SkillServerConditionRequirement serverConditionRequirement) { this.ServerConditionRequirement = serverConditionRequirement; }

    public MethodAction getActiveSkillAction1() { return this.ActiveSkillAction1; }
    public void setActiveSkillAction1(MethodAction activeSkillAction1) { this.ActiveSkillAction1 = activeSkillAction1; }
    public MethodAction getActiveSkillAction2() { return this.ActiveSkillAction2; }
    public void setActiveSkillAction2(MethodAction activeSkillAction2) { this.ActiveSkillAction2 = activeSkillAction2; }
    public MethodAction getActiveSkillAction3() { return this.ActiveSkillAction3; }
    public void setActiveSkillAction3(MethodAction activeSkillAction3) { this.ActiveSkillAction3 = activeSkillAction3; }

    public MethodAction getPassiveSkillAction1() { return this.PassiveSkillAction1; }
    public void setPassiveSkillAction1(MethodAction passiveSkillAction1) { this.PassiveSkillAction1 = passiveSkillAction1; }
    public MethodAction getPassiveSkillAction2() { return this.PassiveSkillAction2; }
    public void setPassiveSkillAction2(MethodAction passiveSkillAction2) { this.PassiveSkillAction2 = passiveSkillAction2; }
    public MethodAction getPassiveSkillAction3() { return this.PassiveSkillAction3; }
    public void setPassiveSkillAction3(MethodAction passiveSkillAction3) { this.PassiveSkillAction3 = passiveSkillAction3; }

    public MethodAction getChannelingSkillAction1() { return ChannelingSkillAction1; }
    public void setChannelingSkillAction1(MethodAction channelingSkillAction1) { ChannelingSkillAction1 = channelingSkillAction1; }

    public MethodAction getClearSkillAction() { return this.ClearSkillAction; }
    public void setClearSkillAction(MethodAction clearSkillAction) { this.ClearSkillAction = clearSkillAction; }

    public MethodAction getPassiveStatusWhenLoginAction() { return this.SetupPassiveStatusWhenLoginAction; }
    public void setPassiveStatusWhenLoginAction(MethodAction setupPassiveStatusWhenLoginAction) { this.SetupPassiveStatusWhenLoginAction = setupPassiveStatusWhenLoginAction; }
    public MethodAction getPressAddBtnAction() { return this.PressAddBtnAction; }
    public void setPressAddBtnAction(MethodAction pressAddBtnAction) { this.PressAddBtnAction = pressAddBtnAction; }
    public MethodAction getPressSubBtnAction() { return this.PressSubBtnAction; }
    public void setPressSubBtnAction(MethodAction pressSubBtnAction) { this.PressSubBtnAction = pressSubBtnAction; }


//    @Override
//    public int hashCode() {
//        return this.SkillID.hashCode();
//    }

//    @Override
//    public boolean equals(Object c) {
//        if (c instanceof SkillDataJson skill1) {
//            return this.SkillID.equals(c.getSkillID());
//        }
//        return false
//    }
}

package net.dingdingduang.somebasicskills.anexampleskilltree.skills.passive;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree.CommonSkillInitialization;
import net.dingdingduang.somebasicskills.globalmethods.LocaleLanguageMethods;
import net.dingdingduang.somebasicskills.sbsattributes.PermAttributesDistributor;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.dingdingduang.somebasicskills.util.MethodAction;

import net.minecraft.server.network.ServerPlayerEntity;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class COMMON104 {
    private static String getSkillDescription(SkillDataJson skillData, int currentSkillLevel) {
        boolean isSkillLevelZero = true;
        int correctSkillLevel = 0;
        if (currentSkillLevel > 0) {
            correctSkillLevel = currentSkillLevel - 1;
            isSkillLevelZero = false;
        }
        Double AttackDamage = skillData.getValueUsage_01().get(correctSkillLevel);

        String translatedString;
        if (isSkillLevelZero) {
            translatedString = LocaleLanguageMethods.getTranslatableDescriptionWithArgs(skillData.getTranslatableTextDescription(), String.format("%s * %s", LocaleLanguageMethods.getLocalizationText(Constants.GUI_SKILLINFO_LEVEL), AttackDamage));
        }
        else {
            translatedString = LocaleLanguageMethods.getTranslatableDescriptionWithArgs(skillData.getTranslatableTextDescription(), String.format("%s", AttackDamage));
        }
        return translatedString;
    }

    public static void init() {
        SkillDataJson tempSkillData = getID2SkillData().get(CommonSkillInitialization.COMMON_104_LEATHER_ARMOR_MASTERY);
        tempSkillData.setTranslatableTooltipGetter(COMMON104::getSkillDescription);

        MethodAction tempAct =
                (entity1) -> {
                    double amount = 0.0;
                    int playerSkillLVL = 0;
                    SkillDataJson skill1 = getID2SkillData().get(CommonSkillInitialization.COMMON_104_LEATHER_ARMOR_MASTERY);
                    if (entity1 instanceof ServerPlayerEntity sp1) {
                        if (getGlobalPlayerSkillID2lvlMap().get(sp1).containsKey(CommonSkillInitialization.COMMON_104_LEATHER_ARMOR_MASTERY)) {
                            playerSkillLVL = getGlobalPlayerSkillID2lvlMap().get(sp1).get(CommonSkillInitialization.COMMON_104_LEATHER_ARMOR_MASTERY);
                            if (playerSkillLVL <= 0) { return; }
                            amount = skill1.getValueUsage_01().get(playerSkillLVL - 1);
                        }
                    }
                    else {
                        amount = skill1.getValueUsage_01().get(playerSkillLVL);
                    }
                    PermAttributesDistributor.applyPermArmorToEntity(entity1, amount, Constants.OP_ADDITION, CommonSkillInitialization.COMMON_104_LEATHER_ARMOR_MASTERY, null);
                };


        tempSkillData.setPassiveStatusWhenLoginAction(tempAct);
        tempSkillData.setPressAddBtnAction(tempAct);
        tempSkillData.setPressSubBtnAction(tempAct);
    }
}

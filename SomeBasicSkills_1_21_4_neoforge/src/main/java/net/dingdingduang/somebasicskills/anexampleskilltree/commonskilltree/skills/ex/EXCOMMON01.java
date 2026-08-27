package net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree.skills.ex;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree.CommonSkillInitialization;
import net.dingdingduang.somebasicskills.globalmethods.LocaleLanguageMethods;
import net.dingdingduang.somebasicskills.sbsattributes.PermAttributesDistributor;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.dingdingduang.somebasicskills.util.MethodAction;

import net.minecraft.server.level.ServerPlayer;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class EXCOMMON01 {
    private static String getSkillDescription(SkillDataJson skillData, int currentSkillLevel) {
        int correctSkillLevel = 0;
        if (currentSkillLevel > 0) {
            correctSkillLevel = currentSkillLevel - 1;
        }
        int AttackDamageMultiplier = (int) (skillData.getValueMultiplierList_01().get(correctSkillLevel).floatValue() * 100);

        return LocaleLanguageMethods.getTranslatableDescriptionWithArgs(skillData.getTranslatableTextDescription(),
                String.format("%d%%", AttackDamageMultiplier)
        );
    }

    public static void init() {
        SkillDataJson tempSkillData = getID2SkillData().get(CommonSkillInitialization.EX_COMMON_001_BASIC_TRAINING_UPGRADE_MASTERY);
        tempSkillData.setTranslatableTooltipGetter(EXCOMMON01::getSkillDescription);

        MethodAction tempAct =
                (entity1) -> {
                    double amount = 0.0;
                    int playerSkillLVL = 0;
                    SkillDataJson skill1 = getID2SkillData().get(CommonSkillInitialization.EX_COMMON_001_BASIC_TRAINING_UPGRADE_MASTERY);
                    if (entity1 instanceof ServerPlayer sp1) {
                        if (getGlobalPlayerSkillID2lvlMap().get(sp1).containsKey(CommonSkillInitialization.EX_COMMON_001_BASIC_TRAINING_UPGRADE_MASTERY)) {
                            playerSkillLVL = getGlobalPlayerSkillID2lvlMap().get(sp1).get(CommonSkillInitialization.EX_COMMON_001_BASIC_TRAINING_UPGRADE_MASTERY);
                            if (playerSkillLVL <= 0) {
                                return;
                            }
                            amount = skill1.getValueMultiplierList_01().get(playerSkillLVL - 1);
                        }
                    }
                    else {
                        amount = skill1.getValueMultiplierList_01().get(playerSkillLVL);
                    }
                    PermAttributesDistributor.applyPermAttackDMGToEntity(entity1, amount, Constants.OP_MULTIPLY_BASE, CommonSkillInitialization.EX_COMMON_001_BASIC_TRAINING_UPGRADE_MASTERY, null);
                };


        tempSkillData.setPassiveStatusWhenLoginAction(tempAct);
        tempSkillData.setPressAddBtnAction(tempAct);
        tempSkillData.setPressSubBtnAction(tempAct);
    }
}

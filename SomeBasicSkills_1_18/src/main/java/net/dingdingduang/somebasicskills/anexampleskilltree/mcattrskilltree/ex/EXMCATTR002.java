package net.dingdingduang.somebasicskills.anexampleskilltree.mcattrskilltree.ex;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.anexampleskilltree.mcattrskilltree.MCAttributeSkillInitialization;
import net.dingdingduang.somebasicskills.globalmethods.LocaleLanguageMethods;
import net.dingdingduang.somebasicskills.sbsattributes.PermAttributesDistributor;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class EXMCATTR002 {
    private static String getSkillDescription(SkillDataJson skillData, int currentSkillLevel) {
        int correctSkillLevel = 0;
        if (currentSkillLevel > 0) {
            correctSkillLevel = currentSkillLevel - 1;
        }
        float ValueMultiplier = skillData.getValueMultiplierList_01().get(correctSkillLevel).floatValue() * 100;

        return LocaleLanguageMethods.getTranslatableDescriptionWithArgs(skillData.getTranslatableTextDescription(),
                String.format("%.2f%%", ValueMultiplier)
        );
    }

    public static void passiveAction(LivingEntity entity1) {
        double amount = 0.0;
        int playerSkillLVL = 0;
        SkillDataJson skill1 = getID2SkillData().get(MCAttributeSkillInitialization.EXMC_ATTR_MOVEMENT_SPEED_MULTIPLIER_0_2);
        //future plan, character panel info
        if (entity1 instanceof ServerPlayer sp1) {
            //Assume getGlobalPlayerSkillID2lvlMap().get(sp1) is not null because it is initialized when player login
            //and is null when player logout
            if (getGlobalPlayerSkillID2lvlMap().get(sp1).containsKey(MCAttributeSkillInitialization.EXMC_ATTR_MOVEMENT_SPEED_MULTIPLIER_0_2)) {
                playerSkillLVL = getGlobalPlayerSkillID2lvlMap().get(sp1).get(MCAttributeSkillInitialization.EXMC_ATTR_MOVEMENT_SPEED_MULTIPLIER_0_2);
                if (playerSkillLVL <= 0) { return; }
                amount = skill1.getValueMultiplierList_01().get(playerSkillLVL - 1);
            }
        }
        else {
            amount = skill1.getValueMultiplierList_01().get(playerSkillLVL);
        }
        PermAttributesDistributor.applyPermMovementSpeedToEntity(entity1, amount, Constants.OP_MULTIPLY_BASE, MCAttributeSkillInitialization.EXMC_ATTR_MOVEMENT_SPEED_MULTIPLIER_0_2, null);
    }

    public static void init() {
        SkillDataJson tempSkillData = getID2SkillData().get(MCAttributeSkillInitialization.EXMC_ATTR_MOVEMENT_SPEED_MULTIPLIER_0_2);
        tempSkillData.setTranslatableTooltipGetter(EXMCATTR002::getSkillDescription);

        tempSkillData.setPassiveStatusWhenLoginAction(EXMCATTR002::passiveAction);
        tempSkillData.setPressAddBtnAction(EXMCATTR002::passiveAction);
        tempSkillData.setPressSubBtnAction(EXMCATTR002::passiveAction);
    }
}

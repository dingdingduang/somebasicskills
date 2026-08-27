package net.dingdingduang.somebasicskills.anexampleskilltree.mcattrskilltree.passive;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.anexampleskilltree.mcattrskilltree.MCAttributeSkillInitialization;
import net.dingdingduang.somebasicskills.globalmethods.LocaleLanguageMethods;
import net.dingdingduang.somebasicskills.sbsattributes.PermAttributesDistributor;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class MCATTR007 {
    private static String getSkillDescription(SkillDataJson skillData, int currentSkillLevel) {
        int correctSkillLevel = 0;
        if (currentSkillLevel > 0) {
            correctSkillLevel = currentSkillLevel - 1;
        }
        Double Value = skillData.getValueUsage_01().get(correctSkillLevel);

        return LocaleLanguageMethods.getTranslatableDescriptionWithArgs(skillData.getTranslatableTextDescription(),
                String.format("%s", Value)
        );
    }

    public static void passiveAction(LivingEntity entity1) {
        double amount = 0.0;
        int playerSkillLVL = 0;
        SkillDataJson skill1 = getID2SkillData().get(MCAttributeSkillInitialization.MC_ATTR_JUMP_STRENGTH_0_7);
        //future plan, character panel info
        if (entity1 instanceof ServerPlayerEntity sp1) {
            //Assume getGlobalPlayerSkillID2lvlMap().get(sp1) is not null because it is initialized when player login
            //and is null when player logout
            if (getGlobalPlayerSkillID2lvlMap().get(sp1).containsKey(MCAttributeSkillInitialization.MC_ATTR_JUMP_STRENGTH_0_7)) {
                playerSkillLVL = getGlobalPlayerSkillID2lvlMap().get(sp1).get(MCAttributeSkillInitialization.MC_ATTR_JUMP_STRENGTH_0_7);
                if (playerSkillLVL <= 0) { return; }
                amount = skill1.getValueUsage_01().get(playerSkillLVL - 1);
            }
        }
        else {
            amount = skill1.getValueUsage_01().get(playerSkillLVL);
        }
        PermAttributesDistributor.applyPermJumpStrengthToEntity(entity1, amount, Constants.OP_ADDITION, MCAttributeSkillInitialization.MC_ATTR_JUMP_STRENGTH_0_7, null);
    }

    public static void init() {
        SkillDataJson tempSkillData = getID2SkillData().get(MCAttributeSkillInitialization.MC_ATTR_JUMP_STRENGTH_0_7);
        tempSkillData.setTranslatableTooltipGetter(MCATTR007::getSkillDescription);

        tempSkillData.setPassiveStatusWhenLoginAction(MCATTR007::passiveAction);
        tempSkillData.setPressAddBtnAction(MCATTR007::passiveAction);
        tempSkillData.setPressSubBtnAction(MCATTR007::passiveAction);
    }
}

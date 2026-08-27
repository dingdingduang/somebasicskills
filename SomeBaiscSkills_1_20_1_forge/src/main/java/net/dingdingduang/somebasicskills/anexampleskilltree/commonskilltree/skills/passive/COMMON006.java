package net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree.skills.passive;

import net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree.CommonSkillInitialization;
import net.dingdingduang.somebasicskills.globalmethods.LocaleLanguageMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.dingdingduang.somebasicskills.util.MethodAction;

import net.minecraft.server.level.ServerPlayer;

import static net.dingdingduang.somebasicskills.globalmethods.SBSAttributeMethods.*;
import static net.dingdingduang.somebasicskills.sbsattributes.statusquery.AttributeServerPlayerStatusQueryMethods.*;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class COMMON006 {
    public final static String playerStatusName = PHYSIC_BACKSTAB_CRIT_CHANCE_BASE+ALL_SKILLS;

    private static String getSkillDescription(SkillDataJson skillData, int currentSkillLevel) {
        int correctSkillLevel = 0;
        if (currentSkillLevel > 0) {
            correctSkillLevel = currentSkillLevel - 1;
        }
        int RearPhysicalCritChance = (int) (skillData.getValueUsage_01().get(correctSkillLevel) * 100);

        return LocaleLanguageMethods.getTranslatableDescriptionWithArgs(skillData.getTranslatableTextDescription(), String.format("%d%%", RearPhysicalCritChance));
    }

    public static void init() {
        SkillDataJson tempSkillData = getID2SkillData().get(CommonSkillInitialization.COMMON_006_PHYSICAL_BACKSTAB_CRIT);
        tempSkillData.setTranslatableTooltipGetter(COMMON006::getSkillDescription);

        MethodAction tempActLoginStatus =
                (entity1) -> {
                    if (entity1 instanceof ServerPlayer sp1) {
                        setPlayerBaseStatusBySkillIDWhenLogin(sp1, playerStatusName, CommonSkillInitialization.COMMON_006_PHYSICAL_BACKSTAB_CRIT);
                    }
                };

        MethodAction tempActAdd =
                (entity1) -> {
                    if (entity1 instanceof ServerPlayer sp1) {
                        increasePlayerBaseStatusBySkillIDWhenUpgrading(sp1, playerStatusName, CommonSkillInitialization.COMMON_006_PHYSICAL_BACKSTAB_CRIT);
                    }
                };

        MethodAction tempActSub =
                (entity1) -> {
                    if (entity1 instanceof ServerPlayer sp1) {
                        decreasePlayerBaseStatusBySkillIDWhenDowngrading(sp1, playerStatusName, CommonSkillInitialization.COMMON_006_PHYSICAL_BACKSTAB_CRIT);
                    }
                };


        tempSkillData.setPassiveStatusWhenLoginAction(tempActLoginStatus);
        tempSkillData.setPressAddBtnAction(tempActAdd);
        tempSkillData.setPressSubBtnAction(tempActSub);
    }
}

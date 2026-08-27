package net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree.skills.active;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree.CommonSkillHelperMethods;
import net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree.CommonSkillInitialization;
import net.dingdingduang.somebasicskills.event.SBSTickEventMethods;
import net.dingdingduang.somebasicskills.globalmethods.LocaleLanguageMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.dingdingduang.somebasicskills.util.MethodAction;
import net.dingdingduang.somebasicskills.registries.SoundRegistry;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.ServerSkillMethods.isEntityChannellingInterrupted;
import static net.dingdingduang.somebasicskills.globalmethods.SoundMethods.*;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.*;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.gui.overlay.SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance;
import static net.dingdingduang.somebasicskills.sbsattributes.TimedAttributesDistributor.*;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class COMMON004 {
    private static String getSkillDescription(SkillDataJson skillData, int currentSkillLevel) {
        int correctSkillLevel = 0;
        if (currentSkillLevel > 0) {
            correctSkillLevel = currentSkillLevel - 1;
        }
        Double duration = skillData.getDuration().get(correctSkillLevel);
        int MagicDamageBonus = (int) (skillData.getValueMultiplierList_01().get(correctSkillLevel) * 100);

        return LocaleLanguageMethods.getTranslatableDescriptionWithArgs(skillData.getTranslatableTextDescription(),
                String.format("%ss", duration),
                String.format("%d%%", MagicDamageBonus)
        );
    }

    public static void init() {
        SkillDataJson tempSkillData = getID2SkillData().get(CommonSkillInitialization.COMMON_004_ANCIENT_MEMORY);
        tempSkillData.setTranslatableTooltipGetter(COMMON004::getSkillDescription);

        //condition: check to see if qualify before activate skill
        tempSkillData.setClientConditionRequirement(
                (SkillID2) -> {
                    boolean isInCD = getSkillsInCooldownClientTimerOverlayInstance().isClientPlayerSkillInCD(SkillID2);
                    boolean isInAction = false;
                    if (getCPlayerState().containsKey(Constants.IS_IN_ACTION)) {
                        isInAction = getCPlayerState().get(Constants.IS_IN_ACTION) >= Constants.ACTION_ON;
                    }
                    boolean isChanneling = false;
                    if (getCPlayerState().containsKey(Constants.IS_CHANNELING)) {
                        isChanneling = getCPlayerState().get(Constants.IS_CHANNELING) >= Constants.ACTION_ON;
                    }
                    boolean isOnGround = true;
                    if (CommonSkillHelperMethods.helperGetClientPlayer() != null) {
                        isOnGround = CommonSkillHelperMethods.helperGetEntityOnGround(CommonSkillHelperMethods.helperGetClientPlayer());
                    }
                    boolean isImmobilized = getCPlayerIsImmobilized();

                    return !isInCD && !isInAction && !isChanneling && isOnGround && !isImmobilized;
                }
        );

        //action while channeling on server side
        MethodAction tempChannelingAction = (entity1) -> {
            int actionPeriod = 3;
            MethodAction action = (entity2) -> {
                boolean contActioning = false;
                HashMap<LivingEntity, HashMap<String, Integer>> ServerLivingEntityState = getSLivingEntityState();
                if (ServerLivingEntityState.containsKey(entity2) && ServerLivingEntityState.get(entity2) != null) {
                    contActioning = ServerLivingEntityState.get(entity2).get(Constants.IS_CHANNELING) == 1;
                }
                if (contActioning) {
                    //play sound at target location while channeling
                    PlaySoundAtLocation(SoundRegistry.CHANNELING_SOUND, entity2.getWorld(), entity2.getX(), entity2.getY(), entity2.getZ(), 0.25f, 1.0f);
                }
                else {
                    //stop loop action
                    SBSTickEventMethods.setSkillActionDone(entity2, CommonSkillInitialization.COMMON_004_ANCIENT_MEMORY);
                }
            };

            //start loop action
            SBSTickEventMethods.setMethodActionTimer(entity1, CommonSkillInitialization.COMMON_004_ANCIENT_MEMORY, actionPeriod, action);

            //play entity animation
            //...
        };

        //channeling final action
        MethodAction tempChannelingFinalAction = (entity1) -> {
                    //condition, if skill is interrupted, do not execute final action
                    if (!isEntityChannellingInterrupted(entity1)) {
                        double duration = 0.0;
                        double amount = 0.0;
                        int playerSkillLVL = 0;
                        SkillDataJson skill1 = getID2SkillData().get(CommonSkillInitialization.COMMON_004_ANCIENT_MEMORY);
                        if (entity1 instanceof ServerPlayerEntity sp1) {
                            if (getGlobalPlayerSkillID2lvlMap().get(sp1).containsKey(CommonSkillInitialization.COMMON_004_ANCIENT_MEMORY)) {
                                playerSkillLVL = getGlobalPlayerSkillID2lvlMap().get(sp1).get(CommonSkillInitialization.COMMON_004_ANCIENT_MEMORY);
                                if (playerSkillLVL <= 0) {
                                    return;
                                }
                                duration = skill1.getDuration().get(playerSkillLVL - 1);
                                amount = skill1.getValueMultiplierList_01().get(playerSkillLVL - 1);
                            }
                        } else {
                            duration = skill1.getDuration().get(playerSkillLVL);
                            amount = skill1.getValueMultiplierList_01().get(playerSkillLVL);
                        }
                        applyTimedMagicDmgToEntity(entity1, (int) Math.round(duration * 20), amount, Constants.OP_MULTIPLY_BASE, false, CommonSkillInitialization.COMMON_004_ANCIENT_MEMORY, null, null);

                        //play buff on sound
                        PlaySoundAtLivingEntityLocationEX(entity1, SoundRegistry.CHANNELING_FINISHED_SOUND, 0.6f, 1.0f);
                    }
                    else {
                        PlaySoundAtLivingEntityLocationEX(entity1, SoundRegistry.CHANNELING_FAILED_SOUND, 0.4f, 1.0f);
                    }
                };

        //while channeling
        tempSkillData.setChannelingSkillAction1(tempChannelingAction);
        //final action
        tempSkillData.setActiveSkillAction1(tempChannelingFinalAction);

    }
}

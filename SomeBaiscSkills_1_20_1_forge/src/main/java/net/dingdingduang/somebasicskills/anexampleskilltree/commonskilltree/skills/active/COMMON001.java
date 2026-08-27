package net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree.skills.active;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree.CommonSkillHelperMethods;
import net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree.CommonSkillInitialization;
import net.dingdingduang.somebasicskills.event.SBSTickEventMethods;
import net.dingdingduang.somebasicskills.globalmethods.LocaleLanguageMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.dingdingduang.somebasicskills.util.DistanceHasTravledHolder;
import net.dingdingduang.somebasicskills.util.ElapsedTicksHolder;
import net.dingdingduang.somebasicskills.util.MethodAction;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.state.BlockState;

import static net.dingdingduang.somebasicskills.globalmethods.ServerSkillMethods.*;
import static net.dingdingduang.somebasicskills.globalmethods.SoundMethods.*;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerState;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getClientPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.gui.overlay.SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance;
import static net.dingdingduang.somebasicskills.registries.SoundRegistry.COMMON_0_1_LEAP_SOUND;
import static net.dingdingduang.somebasicskills.sbsattributes.TimedAttributesDistributor.*;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class COMMON001 {
    private static String getSkillDescription(SkillDataJson skillData, int currentSkillLevel) {
        int correctSkillLevel = 0;
        if (currentSkillLevel > 0) {
            correctSkillLevel = currentSkillLevel - 1;
        }
        Double duration = skillData.getDuration().get(correctSkillLevel);

        return LocaleLanguageMethods.getTranslatableDescriptionWithArgs(skillData.getTranslatableTextDescription(),
                String.format("%ss", duration)
        );
    }

    public static void init() {
        SkillDataJson tempSkillData = getID2SkillData().get(CommonSkillInitialization.COMMON_001_BACKSTEP);

        tempSkillData.setTranslatableTooltipGetter(COMMON001::getSkillDescription);



        //====================================
        //set skill condition
//        default condition: getID2SkillData().get(SkillID).setClientConditionRequirement(SkillMethods::DefaultSkillCondition);
        //setup customize condition requirement
        //check on ground, on fire, on air, being hit, or whatever
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

                    return !isInCD && !isInAction && !isChanneling && isOnGround;
                }
        );
        tempSkillData.setClientCondReqPassedAction((SkillID) -> {
//            getCPlayerState().put(Constants.IS_BACKSTEPPING, Constants.ACTION_ON);
            //client moving
            if (CommonSkillHelperMethods.helperGetClientPlayer() == null) { return; }
            getCPlayerState().put(Constants.IS_BACKSTEPPING, 1);
            float triggeredEntityYRot = CommonSkillHelperMethods.helperGetEntityYRot(CommonSkillHelperMethods.helperGetClientPlayer());

            int playerSkillLVL = 0;
            if (getClientPlayerSkillID2lvlMap().containsKey(CommonSkillInitialization.COMMON_001_BACKSTEP)) {
                playerSkillLVL = getClientPlayerSkillID2lvlMap().get(CommonSkillInitialization.COMMON_001_BACKSTEP);
                if (playerSkillLVL <= 0) { return; }
                playerSkillLVL = playerSkillLVL - 1;
            }
            SkillDataJson skill1 = getID2SkillData().get(CommonSkillInitialization.COMMON_001_BACKSTEP);
            float dist = skill1.getValueUsage_01().get(playerSkillLVL).floatValue();

            double dx = dist*Math.sin((-triggeredEntityYRot+180) * Math.PI / 180.0);
            double dy = 0.4;
            double dz = dist*Math.cos((-triggeredEntityYRot+180) * Math.PI / 180.0);

            CommonSkillHelperMethods.helperSetDelta(CommonSkillHelperMethods.helperGetClientPlayer(), dx, dy, dz);
        });



        //====================================
        //setup skill action
        MethodAction tempSkillAction =
                (entity1) -> {
                    int duration;
                    int playerSkillLVL = 0;
                    float dist;

                    incrementEntityStateByAmountXNoPacket(entity1, Constants.IS_BACKSTEPPING, 1);

                    //being performed
                    SendPacketIsInActionToClientSideFromServer(entity1, true);

                    SkillDataJson skill1 = getID2SkillData().get(CommonSkillInitialization.COMMON_001_BACKSTEP);
                    //future plan, character panel info
                    if (entity1 instanceof ServerPlayer sp1) {
                        if (getGlobalPlayerSkillID2lvlMap().get(sp1).containsKey(CommonSkillInitialization.COMMON_001_BACKSTEP)) {
                            playerSkillLVL = getGlobalPlayerSkillID2lvlMap().get(sp1).get(CommonSkillInitialization.COMMON_001_BACKSTEP);
                            if (playerSkillLVL <= 0) { return; }
                            playerSkillLVL = playerSkillLVL - 1;
                        }
                    }
                    duration = (int) (skill1.getDuration().get(playerSkillLVL) * 20);
                    dist = skill1.getValueUsage_01().get(playerSkillLVL).floatValue();

                    applyTimedBeneficialStatusInvincibilityToEntity(entity1, duration, skill1.getDuration().get(playerSkillLVL - 1), Constants.OP_ADDITION, false, CommonSkillInitialization.COMMON_001_BACKSTEP, null, null);
                    applyTimedKnockbackResistToEntity(entity1, duration, 10.0, Constants.OP_ADDITION, false, CommonSkillInitialization.COMMON_001_BACKSTEP, null, null);

                    //play skill sound at target location
                    PlaySoundAtLocation(COMMON_0_1_LEAP_SOUND.get(), entity1.level(), entity1.getX(), entity1.getY(), entity1.getZ(), 0.5f, 1.0f);

                    //VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV
                    //start Actioning
                    float finalDist = dist;
                    int actionPeriod = 1;
                    int finalTicks = (int) (skill1.getDuration().get(playerSkillLVL) * 20);
                    float triggeredEntityYRot = CommonSkillHelperMethods.helperGetEntityYRot(entity1);
                    double dx = finalDist*Math.sin((-triggeredEntityYRot+180) * Math.PI / 180.0);
                    double dy = 0.4f;
                    double dz = finalDist*Math.cos((-triggeredEntityYRot+180) * Math.PI / 180.0);

                    CommonSkillHelperMethods.helperSetDelta(entity1, dx, dy, dz);

                    ElapsedTicksHolder ElapsedTicksHolder = new ElapsedTicksHolder(0);
                    DistanceHasTravledHolder distanceHasTravledHolder = new DistanceHasTravledHolder(0);
                    MethodAction action =
                            (entity2) ->  {
                                float distHasTraveled = distanceHasTravledHolder.getDistHasTraveled();

                                int updatedTick = ElapsedTicksHolder.getIntCounter() + actionPeriod;
                                ElapsedTicksHolder.setIntCounter(updatedTick);

                                if (distHasTraveled < finalDist || updatedTick > 60) {
                                    float currentDistShouldTravel = (finalDist / finalTicks / actionPeriod);
                                    distHasTraveled = distHasTraveled + currentDistShouldTravel;
                                    distanceHasTravledHolder.setDistHasTraveled(distHasTraveled);

                                    //check collision
                                    int x = (int) Math.floor(entity2.getX() + currentDistShouldTravel*Math.sin((-entity2.getYRot()+180) * Math.PI / 180.0));
                                    int y = (int) Math.floor(entity2.getY());
                                    int z = (int) Math.floor(entity2.getZ() + currentDistShouldTravel*Math.cos((-entity2.getYRot()+180) * Math.PI / 180.0));
                                    BlockState state = entity2.level().getBlockState(new BlockPos(x, y, z));

                                    if (!entity2.onGround() && !state.isAir()) {
                                        //IMPORTANT! set action off to allow entity to be able to cast skills
                                        SendPacketIsInActionToClientSideFromServer(entity2, false);
                                        incrementEntityStateByAmountX(entity2, Constants.IS_BACKSTEPPING, -1);

                                        SBSTickEventMethods.setSkillActionDone(entity2, CommonSkillInitialization.COMMON_001_BACKSTEP);
                                    }

                                    //play animation
                                    //...
                                }
                                else {
                                    //IMPORTANT! set action off to allow entity to be able to cast skills
                                    SendPacketIsInActionToClientSideFromServer(entity2, false);
                                    incrementEntityStateByAmountX(entity2, Constants.IS_BACKSTEPPING, -1);
//                                    ServerUpdateLivingEntityState(entity2, Constants.IS_BACKSTEPPING, Constants.ACTION_OFF);

                                    SBSTickEventMethods.setSkillActionDone(entity2, CommonSkillInitialization.COMMON_001_BACKSTEP);
                                }
                            };
                    //Actioning ends
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

                    //start loop actioning with fixed period ticks
                    SBSTickEventMethods.setMethodActionTimer(entity1, CommonSkillInitialization.COMMON_001_BACKSTEP, actionPeriod, action);
                };

        tempSkillData.setActiveSkillAction1(tempSkillAction);
    }
}

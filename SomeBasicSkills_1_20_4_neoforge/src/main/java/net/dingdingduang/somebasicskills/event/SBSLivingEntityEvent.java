package net.dingdingduang.somebasicskills.event;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues;
import net.dingdingduang.somebasicskills.sbsattributes.PermAttributesDistributor;
import net.dingdingduang.somebasicskills.sbsattributes.SBSAttributes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.SBSAttributeMethods.getTimedAttributeValueFromLivingEntity;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class SBSLivingEntityEvent {
//    @SubscribeEvent
//    public static void RemoveEntityFromStoredMapOnDeath(LivingDeathEvent event) {
//        LivingEntity entity = event.getEntity();

//        if (!(entity instanceof ServerPlayer)) {
//            PermAttributesDistributor.removeEntityFromMap(entity);
////            AttributeServerTimerOverlay.getAttributeServerTimerOverlayInstance().removeLivingEntityFromStatusMap(entity);
//            HashMap<LivingEntity, HashMap<String, Integer>> tempServerEntityStateMap = GlobalServerLivingEntityValues.getSLivingEntityState();
//            if (tempServerEntityStateMap != null) {
//                tempServerEntityStateMap.remove(entity);
//            }
//        }
//    }
    @SubscribeEvent
    public static void RemoveEntityFromStoredMapOnDeath(EntityLeaveLevelEvent event) {
        if (event.getLevel() instanceof ServerLevel && event.getEntity() instanceof LivingEntity entity && !(entity instanceof ServerPlayer)) {
            PermAttributesDistributor.removeEntityFromMap(entity);
//            AttributeServerTimerOverlay.getAttributeServerTimerOverlayInstance().removeLivingEntityFromStatusMap(entity);
            HashMap<LivingEntity, HashMap<String, Integer>> tempServerEntityStateMap = GlobalServerLivingEntityValues.getSLivingEntityState();
            if (tempServerEntityStateMap != null) {
                tempServerEntityStateMap.remove(entity);
            }
        }
    }

//    //Entity: the one before being hit
//    @SubscribeEvent
//    public static void EntityBeingAttackedSBEvent(LivingAttackEvent event) {
//        LivingEntity entity = event.getEntity();
//
//    }

//    //Entity: player
//    @SubscribeEvent
//    public static void PlayerAttackSBEvent(AttackEntityEvent event) {
//        event.setCanceled(true);
//    }

//    //Entity: the one before getting hit
    @SubscribeEvent
    public static void EntityBeforeDamageSBEvent(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        //retrieve entity timed status map
        if (getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_BENEFICIAL_STATUS_INVINCIBILITY, Constants.OP_ADDITION) > 0) {
            event.setAmount(0);
            event.setCanceled(true);
        }
    }

//    @SubscribeEvent
//    public static void SBImmobilizationEvent(LivingEvent.LivingJumpEvent event) {
//        if (event.getEntity() != null && event.getEntity().hasEffect(EffectRegistry.IMMOBILIZATION.get())) {
//            event.getEntity().setDeltaMovement(0, 0, 0);
////            return;
//        }
//    }
}

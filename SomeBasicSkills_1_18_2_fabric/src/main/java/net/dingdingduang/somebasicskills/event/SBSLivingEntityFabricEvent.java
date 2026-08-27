package net.dingdingduang.somebasicskills.event;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues;
import net.dingdingduang.somebasicskills.sbsattributes.PermAttributesDistributor;
import net.dingdingduang.somebasicskills.sbsattributes.SBSAttributes;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.SBSAttributeMethods.getTimedAttributeValueFromLivingEntity;

public class SBSLivingEntityFabricEvent {
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
    public static float EntityBeforeDamageSBEvent(LivingEntity beingAttack, float amount) {
        //retrieve entity timed status map
        if (getTimedAttributeValueFromLivingEntity(beingAttack, SBSAttributes.SBS_ATTRIBUTE_BENEFICIAL_STATUS_INVINCIBILITY, Constants.OP_ADDITION) > 0) {
//            event.setAmount(0);
//            return true;
            return 0;
        }
        return amount;
    }

//    @SubscribeEvent
//    public static void SBImmobilizationEvent(LivingEvent.LivingJumpEvent event) {
//        if (event.getEntity() != null && event.getEntity().hasEffect(EffectRegistry.IMMOBILIZATION.get())) {
//            event.getEntity().setDeltaMovement(0, 0, 0);
////            return;
//        }
//    }

    public static void ServerEntityLeaveWorldAction(Entity entity, ServerWorld serverWorld) {
        if (entity instanceof LivingEntity livingEntity && !(livingEntity instanceof ServerPlayerEntity)) {
            PermAttributesDistributor.removeEntityFromMap(livingEntity);
//            AttributeServerTimerOverlay.getAttributeServerTimerOverlayInstance().removeLivingEntityFromStatusMap(entity);
            HashMap<LivingEntity, HashMap<String, Integer>> tempServerEntityStateMap = GlobalServerLivingEntityValues.getSLivingEntityState();
            if (tempServerEntityStateMap != null) {
                tempServerEntityStateMap.remove(livingEntity);
            }
        }
    }

    public static void registerEvent() {
        ServerEntityEvents.ENTITY_UNLOAD.register(SBSLivingEntityFabricEvent::ServerEntityLeaveWorldAction);
    }


//    public static void registerEvent() {
//        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
//            BlockState state = world.getBlockState(pos);
//                /* Manual spectator check is necessary because AttackBlockCallbacks
//                   fire before the spectator check */
//            if (state.isToolRequired() && !player.isSpectator() &&
//                    player.getMainHandStack().isEmpty()) {
//                player.damage(DamageSource.field_5869, 1.0F);
//            }
//            return ActionResult.PASS;
//        });
//    }
}

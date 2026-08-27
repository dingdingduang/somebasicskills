package net.dingdingduang.somebasicskills.event;

import net.dingdingduang.somebasicskills.networking.NetworkingFetchMsgMethods;
import net.dingdingduang.somebasicskills.sbsattributes.AttributeHelper;
import net.dingdingduang.somebasicskills.sbsattributes.AttributePrimitiveOnly;
import net.dingdingduang.somebasicskills.sbsattributes.UniqueAttributePrimitiveOnly;
import net.dingdingduang.somebasicskills.sbsattributes.statusquery.AttributeServerPlayerStatusQueryMethods;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerValue2BaseMultiplierMap;

public class SBSTickEventAttributeMethods {
    private static boolean isAttributeServerTimerActive = false;
    //set period to 10 because of terrible nested loop
    private static final int period = 10;
//    private int previousTriggeredPeriod = -1;

    //remove timed attr by ticks
    private static ConcurrentHashMap<LivingEntity, ConcurrentHashMap<String, AttributeHelper>> ServerEntityAttrName2AttrHelper;
    //attrname+opInt as key matched final value for base/multiplier
    private static HashMap<LivingEntity, HashMap<String, Double>> ServerEntityAttrNameWithOP2FinalValue;
    private static HashMap<LivingEntity, HashMap<String, Integer>> ServerEntityAttrName2MaxTickTime;
    private static HashSet<LivingEntity> LivingEntityToBeRemoved = new HashSet<>();

    private static int timer = -1;

    public static void SBSAttributesRunAction() {
        if (isAttributeServerTimerActive) {

            timer++;

//                int currentTriggeredPeriod = timer / period;
            if (timer % period == 0) {
//                if (currentTriggeredPeriod != previousTriggeredPeriod) {
//                    previousTriggeredPeriod = currentTriggeredPeriod;
                timer = 0;
                HashSet<LivingEntity> entityToBeRemovedSet = new HashSet<LivingEntity>();
                HashSet<String> attributeToBeRemovedSet = new HashSet<String>();
                HashSet<AttributePrimitiveOnly> attributePrimToBeRemovedSet = new HashSet<AttributePrimitiveOnly>();
                ConcurrentHashMap<String, AttributeHelper> tempAttr2AttrHelper;
                AttributeHelper tempAttrHelper;
                EntityAttributeModifier tempAttrModifier;
                int remaingTime;
                for (LivingEntity entity : ServerEntityAttrName2AttrHelper.keySet()) {
                    if (LivingEntityToBeRemoved.contains(entity)) { continue; }

                    tempAttr2AttrHelper = ServerEntityAttrName2AttrHelper.get(entity);
                    HashSet<String> isTrueRemainingTicksModified = new HashSet<>();

                    for (String attrNameWithSkillIDPostfix : tempAttr2AttrHelper.keySet()) {
                        tempAttrHelper = tempAttr2AttrHelper.get(attrNameWithSkillIDPostfix);

                        HashMap<AttributePrimitiveOnly, Integer> tempAttrPrimMap = tempAttrHelper.getAttributePrimitive2Index();

                        //ULTIMATE UGLY nested loop, OMG how do I improve this shiet
                        for (AttributePrimitiveOnly tempAttrPrimitive : tempAttrPrimMap.keySet()) {
                            tempAttrModifier = tempAttrHelper.getAttrModifier();
                            boolean isAttributeOwnerAlive = entity.isAlive();
                            remaingTime = (isAttributeOwnerAlive) ? tempAttrPrimitive.getTimeTicks() - period : 0;
                            tempAttrPrimitive.setTimeTicks(remaingTime);
//                            printInGameMsg("remaingingTime: "+remaingTime);

                            //do something if AttributePrimitiveOnly has something special like emit particle effect
//                                if (isAttributeOwnerAlive && tempAttrPrimitive.getAttributeMethodAction() != null) {
                            if (tempAttrPrimitive.getAttributeMethodAction() != null) {
                                tempAttrPrimitive.getAttributeMethodAction().executeAction(entity);
                            }

                            EntityAttribute tempAttrType = tempAttrHelper.getAttrType();
                            String tempAttrTypeOpString = tempAttrType.getTranslationKey() + tempAttrModifier.getOperation().getId();

                            if (!isTrueRemainingTicksModified.contains(tempAttrTypeOpString)) {
                                HashMap<String, Integer> tempAttrName2MaxTickTimeMap = ServerEntityAttrName2MaxTickTime.get(entity);
                                if (tempAttrName2MaxTickTimeMap.containsKey(tempAttrTypeOpString)) {
                                    tempAttrName2MaxTickTimeMap.put(tempAttrTypeOpString, tempAttrName2MaxTickTimeMap.get(tempAttrTypeOpString) - period);
                                }
                                isTrueRemainingTicksModified.add(tempAttrTypeOpString);
                            }

                            if (remaingTime <= 0) {
//                                    if (isAttributeOwnerAlive && tempAttrPrimitive.getAttributeFinalMethodAction() != null) {
                                if (tempAttrPrimitive.getAttributeFinalMethodAction() != null) {
                                    tempAttrPrimitive.getAttributeFinalMethodAction().executeAction(entity);
                                }

//                                    Attribute tempAttrType = tempAttrHelper.getAttrType();
//                                    String tempAttrTypeOpString = tempAttrType.getDescriptionId() + tempAttrModifier.getOperation().toValue();

                                attributePrimToBeRemovedSet.add(tempAttrPrimitive);
                                double updateAmount = ServerEntityAttrNameWithOP2FinalValue.get(entity).get(tempAttrTypeOpString) - tempAttrPrimitive.getAmount();
                                //update
                                EntityAttributeModifier updateAttr = new EntityAttributeModifier(tempAttrModifier.getId(),
                                        tempAttrModifier.getName(),
                                        updateAmount,
                                        tempAttrModifier.getOperation());

                                //test find problem
//                                if (tempAttrModifier.getOperation().toValue() == 1) {
//                                    printInGameMsg("final: " + updateAmount+", tempAmount: "+tempAttrPrimitive.getAmount()+", previous amount: "+ServerEntityAttrNameWithOP2FinalValue.get(entity).get(tempAttrTypeOpString));
//                                    printInGameMsg("op: " + tempAttrTypeOpString);
//                                }
                                ServerEntityAttrNameWithOP2FinalValue.get(entity).put(tempAttrTypeOpString, updateAmount);

                                if (entity instanceof ServerPlayerEntity sp1) {
                                    String playerStatusName = tempAttrPrimitive.getAttrName();
                                    //ASSUME getSPlayerValue2BaseMultiplierMap() is not null simpy because it is initialized and should never be null unless server is offline
                                    HashMap<String, Double> tempPlayerStatusMap = getSPlayerValue2BaseMultiplierMap().get(sp1);
                                    //add status value based on given attr i.e. HP ATK SPEED
                                    if (tempPlayerStatusMap.containsKey(playerStatusName)) {
//                                        tempPlayerStatusMap.put(playerStatusName, amount+tempPlayerStatusMap.get(playerStatusName));
                                        tempPlayerStatusMap.put(playerStatusName, updateAmount);
                                        NetworkingFetchMsgMethods.FetchPlayerStatusToClientSide(sp1, playerStatusName, updateAmount);
                                    }
                                }

                                EntityAttributeInstance tempAttrInstance = entity.getAttributeInstance(tempAttrType);
                                if (tempAttrInstance != null) {
                                    boolean isAttrTypeHealth = false;
                                    float EntityHP_Percent = 0.01f;

                                    //prevent abusing health
                                    if (isAttributeOwnerAlive && tempAttrHelper.getAttrType() == EntityAttributes.GENERIC_MAX_HEALTH) {
                                        isAttrTypeHealth = true;
                                        EntityHP_Percent = entity.getHealth() / entity.getMaxHealth();
                                    }
                                    //vvvvvvvvvvvvvvvvvvv
                                    if (tempAttrInstance.hasModifier(updateAttr)) {
                                        tempAttrInstance.removeModifier(updateAttr);
                                    }
                                    double absUpdateAmount = Math.abs(updateAmount);
                                    if (absUpdateAmount > 0.001 || Math.round(absUpdateAmount) > 0) {
                                        tempAttrInstance.addTemporaryModifier(updateAttr);
                                    }
//                                    if (updateAmount > 0) {
//                                        tempAttrInstance.addTransientModifier(updateAttr);
//                                    }
                                    //^^^^^^^^^^^^^^^^^^^
                                    if (isAttrTypeHealth) {
                                        entity.setHealth(EntityHP_Percent * entity.getMaxHealth());
                                    }

                                    //put return previous key
//                                    tempAttrModifier = ServerEntityAttrName2FinalAttrValue.get(entity).put(attrNameWithSkillIDPostfix, updateAttr);

                                    tempAttrHelper.setAttrModifier(updateAttr);
                                }
                            }
                        }

                        for (AttributePrimitiveOnly attrPrim : attributePrimToBeRemovedSet) {
                            tempAttrPrimMap.remove(attrPrim);
                        }

                        if (tempAttrPrimMap.isEmpty()) {
                            attributeToBeRemovedSet.add(attrNameWithSkillIDPostfix);
                        }
                    }

                    for (String attrNameWithSkillIDPostfixTBR : attributeToBeRemovedSet) {
                        tempAttr2AttrHelper.remove(attrNameWithSkillIDPostfixTBR);
                    }
                    if (tempAttr2AttrHelper.isEmpty()) {
                        entityToBeRemovedSet.add(entity);
                    }
                }
                for (LivingEntity entityTBR: LivingEntityToBeRemoved) {
                    entityToBeRemovedSet.add(entityTBR);
                }
                for (LivingEntity entityTBR : entityToBeRemovedSet) {
                    LivingEntityToBeRemoved.remove(entityTBR);
                    ServerEntityAttrName2AttrHelper.remove(entityTBR);
                    ServerEntityAttrNameWithOP2FinalValue.remove(entityTBR);
                    ServerEntityAttrName2MaxTickTime.remove(entityTBR);
                }
                if (ServerEntityAttrName2AttrHelper.isEmpty()) {
//                    printInGameMsg("expired timer");
                    isAttributeServerTimerActive = false;
                    ServerEntityAttrName2AttrHelper = null;
                    ServerEntityAttrNameWithOP2FinalValue = null;
                    ServerEntityAttrName2MaxTickTime = null;
                    timer = -1;
                }
            }
        }
    }


    public static boolean isAttributeServerTimerActive() { return isAttributeServerTimerActive; }

    public static double setUniqueAttributeServerTimer(LivingEntity entity, UUID attrUUID, String attrName, EntityAttribute attrType, AttributePrimitiveOnly attrPrimitive, EntityAttributeModifier.Operation op, String SkillID) {
        isAttributeServerTimerActive = true;
        if (timer == -1) { timer = 0; }
        if (ServerEntityAttrName2AttrHelper == null) {
            ServerEntityAttrName2AttrHelper = new ConcurrentHashMap<LivingEntity, ConcurrentHashMap<String, AttributeHelper>>();
            ServerEntityAttrNameWithOP2FinalValue = new HashMap<LivingEntity, HashMap<String, Double>>();
            ServerEntityAttrName2MaxTickTime = new HashMap<LivingEntity, HashMap<String, Integer>>();
        }
        if (!ServerEntityAttrName2AttrHelper.containsKey(entity)) {
            ServerEntityAttrName2AttrHelper.put(entity, new ConcurrentHashMap<String, AttributeHelper>());
            ServerEntityAttrNameWithOP2FinalValue.put(entity, new HashMap<String, Double>());
            ServerEntityAttrName2MaxTickTime.put(entity, new HashMap<String, Integer>());
        }
        String attrNameWithSkillIDPostfix = attrName + SkillID;
        ConcurrentHashMap<String, AttributeHelper> PlayerAttr = ServerEntityAttrName2AttrHelper.get(entity);

//        String attrNameWithSkillIDPostfix = attrType.getDescriptionId();
        if (!PlayerAttr.containsKey(attrNameWithSkillIDPostfix)) {
            PlayerAttr.put(attrNameWithSkillIDPostfix, new AttributeHelper(attrType));
        }

        AttributeHelper tempAttributeHelper = PlayerAttr.get(attrNameWithSkillIDPostfix), tempAttributeHelper2;
        HashMap<AttributePrimitiveOnly, Integer> tempAttrModifierMap = tempAttributeHelper.getAttributePrimitive2Index();
//        tempAttrModifierMap.put(attrPrimitive, tempAttrModifierMap.size());
        UniqueAttributePrimitiveOnly tempUnique = new UniqueAttributePrimitiveOnly(attrPrimitive.getAmount(), attrPrimitive.getTimeTicks(), op.getId(),
                attrPrimitive.getAttrUUID(), attrPrimitive.getAttrName(), attrPrimitive.getSkillID(), SkillID);
        tempAttrModifierMap.remove(tempUnique);
        tempAttrModifierMap.put(tempUnique, tempAttrModifierMap.size());
        tempUnique.setAttributeMethodAction(attrPrimitive.getAttributeMethodAction());
        tempUnique.setAttributeFinalMethodAction(attrPrimitive.getAttributeFinalMethodAction());

        //calculate final Attr Value
        double FinalValue = 0.0;
        int operationInt = op.getId();
        for (String PlayerAttrAttrName: PlayerAttr.keySet()) {
            tempAttributeHelper2 = PlayerAttr.get(PlayerAttrAttrName);
            tempAttrModifierMap = tempAttributeHelper2.getAttributePrimitive2Index();
            for (AttributePrimitiveOnly tempAttrPrimitive : tempAttrModifierMap.keySet()) {
                if (tempAttrPrimitive.getAttrUUID().equals(attrUUID)) {
                    FinalValue += tempAttrPrimitive.getAmount();
                }
            }
        }
//        printInGameMsg("new final: "+FinalValue);
        String AttrNameWithOp = attrType.getTranslationKey()+operationInt;
        ServerEntityAttrNameWithOP2FinalValue.get(entity).put(AttrNameWithOp, FinalValue);
        HashMap<String, Integer> AttrName2MaxTickTimeMap = ServerEntityAttrName2MaxTickTime.get(entity);
        if (AttrName2MaxTickTimeMap.containsKey(AttrNameWithOp)) {
            if (AttrName2MaxTickTimeMap.get(AttrNameWithOp) < attrPrimitive.getTimeTicks()) {
                AttrName2MaxTickTimeMap.put(AttrNameWithOp, attrPrimitive.getTimeTicks());
            }
        }
        else {
            AttrName2MaxTickTimeMap.put(AttrNameWithOp, attrPrimitive.getTimeTicks());
        }


        EntityAttributeModifier tempAttr = new EntityAttributeModifier(attrUUID, attrNameWithSkillIDPostfix, FinalValue, op);
        tempAttributeHelper.setAttrModifier(tempAttr);

        EntityAttributeInstance tempAttrInstance = entity.getAttributeInstance(attrType);
        boolean isAttrTypeHealth = false;
        float EntityHP_Percent = 0.01f;
        if (tempAttrInstance != null) {
            //prevent abusing health
            if (attrType == EntityAttributes.GENERIC_MAX_HEALTH) {
                isAttrTypeHealth = true;
                EntityHP_Percent = entity.getHealth() / entity.getMaxHealth();
            }
            //vvvvvvvvvvvvvvvvvvv
            if (tempAttrInstance.hasModifier(tempAttr)) {
                tempAttrInstance.removeModifier(tempAttr);
            }
            tempAttrInstance.addTemporaryModifier(tempAttr);
            //^^^^^^^^^^^^^^^^^^
            if (isAttrTypeHealth) {
                entity.setHealth(EntityHP_Percent*entity.getMaxHealth());
            }
        }
        return FinalValue;
    }

    public static void setAttributeDupServerTimer(LivingEntity entity, UUID attrUUID, String attrName, EntityAttribute attrType, AttributePrimitiveOnly attrPrimitive, EntityAttributeModifier.Operation op, String SkillID) {
        isAttributeServerTimerActive = true;
        if (timer == -1) { timer = 0; }
        if (ServerEntityAttrName2AttrHelper == null) {
            ServerEntityAttrName2AttrHelper = new ConcurrentHashMap<LivingEntity, ConcurrentHashMap<String, AttributeHelper>>();
            ServerEntityAttrNameWithOP2FinalValue = new HashMap<LivingEntity, HashMap<String, Double>>();
            ServerEntityAttrName2MaxTickTime = new HashMap<LivingEntity, HashMap<String, Integer>>();
        }
        if (!ServerEntityAttrName2AttrHelper.containsKey(entity)) {
            ServerEntityAttrName2AttrHelper.put(entity, new ConcurrentHashMap<String, AttributeHelper>());
            ServerEntityAttrNameWithOP2FinalValue.put(entity, new HashMap<String, Double>());
            ServerEntityAttrName2MaxTickTime.put(entity, new HashMap<String, Integer>());
        }
        String attrNameWithSkillIDPostfix = attrName + SkillID;
        ConcurrentHashMap<String, AttributeHelper> PlayerAttr = ServerEntityAttrName2AttrHelper.get(entity);

        if (!PlayerAttr.containsKey(attrNameWithSkillIDPostfix)) {
            PlayerAttr.put(attrNameWithSkillIDPostfix, new AttributeHelper(attrType));
        }

        AttributeHelper tempAttributeHelper = PlayerAttr.get(attrNameWithSkillIDPostfix), tempAttributeHelper2;
        HashMap<AttributePrimitiveOnly, Integer> tempAttrModifierMap = tempAttributeHelper.getAttributePrimitive2Index();
        tempAttrModifierMap.put(attrPrimitive, tempAttrModifierMap.size());

        //calculate final Attr Value
        double FinalValue = 0.0;
        int operationInt = op.getId();
        for (String PlayerAttrAttrName: PlayerAttr.keySet()) {
            tempAttributeHelper2 = PlayerAttr.get(PlayerAttrAttrName);
            tempAttrModifierMap = tempAttributeHelper2.getAttributePrimitive2Index();
            for (AttributePrimitiveOnly tempAttrPrimitive : tempAttrModifierMap.keySet()) {
                if (tempAttrPrimitive.getAttrUUID().equals(attrUUID)) {
                    FinalValue += tempAttrPrimitive.getAmount();
                }
            }
        }
//        printInGameMsg("new final: "+FinalValue);
        String AttrNameWithOp = attrType.getTranslationKey()+operationInt;
        ServerEntityAttrNameWithOP2FinalValue.get(entity).put(AttrNameWithOp, FinalValue);
        HashMap<String, Integer> AttrName2MaxTickTimeMap = ServerEntityAttrName2MaxTickTime.get(entity);
        if (AttrName2MaxTickTimeMap.containsKey(AttrNameWithOp)) {
            if (AttrName2MaxTickTimeMap.get(AttrNameWithOp) < attrPrimitive.getTimeTicks()) {
                AttrName2MaxTickTimeMap.put(AttrNameWithOp, attrPrimitive.getTimeTicks());
            }
        }
        else {
            AttrName2MaxTickTimeMap.put(AttrNameWithOp, attrPrimitive.getTimeTicks());
        }

        EntityAttributeModifier tempAttr = new EntityAttributeModifier(attrUUID, attrNameWithSkillIDPostfix, FinalValue, op);
        tempAttributeHelper.setAttrModifier(tempAttr);

        EntityAttributeInstance tempAttrInstance = entity.getAttributeInstance(attrType);
        boolean isAttrTypeHealth = false;
        float EntityHP_Percent = 0.01f;
        if (tempAttrInstance != null) {
            if (attrType == EntityAttributes.GENERIC_MAX_HEALTH) {
                isAttrTypeHealth = true;
                EntityHP_Percent = entity.getHealth() / entity.getMaxHealth();
            }
            //vvvvvvvvvvvvvvvvvvv
            if (tempAttrInstance.hasModifier(tempAttr)) {
                tempAttrInstance.removeModifier(tempAttr);
            }
            tempAttrInstance.addTemporaryModifier(tempAttr);
            //^^^^^^^^^^^^^^^^^^
            if (isAttrTypeHealth) {
                entity.setHealth(EntityHP_Percent*entity.getMaxHealth());
            }
        }
    }

    public static HashMap<String, Double> getServerEntityAttrNameWithOP2FinalValue(LivingEntity entity) {
        if (ServerEntityAttrNameWithOP2FinalValue != null && ServerEntityAttrNameWithOP2FinalValue.containsKey(entity)) {
            return ServerEntityAttrNameWithOP2FinalValue.get(entity);
        }
        return null;
    }

    public static HashMap<String, Integer> getServerEntityAttrNameRemainingTickCountMapFromLivingEntity(LivingEntity entity) {
        if (ServerEntityAttrName2MaxTickTime != null && ServerEntityAttrName2MaxTickTime.containsKey(entity)) {
            return ServerEntityAttrName2MaxTickTime.get(entity);
        }
        return null;
    }

    //SkillID could be anything
    public static void setAttrWithIDDone(LivingEntity entity, UUID attrUUID, String attrName, EntityAttribute attrType, EntityAttributeModifier.Operation op, String SkillID, boolean isUnique) {
        AttributePrimitiveOnly tempAttrPrimOnly = new AttributePrimitiveOnly(0, 0, op.getId(), attrUUID, attrName + AttributeServerPlayerStatusQueryMethods.ALL_SKILLS, SkillID);
        if (isUnique) {
            setUniqueAttributeServerTimer(entity, attrUUID, attrName, attrType, tempAttrPrimOnly, op, SkillID);
        }
        else {
            setAttributeDupServerTimer(entity, attrUUID, attrName, attrType, tempAttrPrimOnly, op, SkillID);
        }
    }

    public static void removeLivingEntityFromStatusMap(LivingEntity entity) {
        if (ServerEntityAttrName2AttrHelper != null && ServerEntityAttrName2AttrHelper.containsKey(entity)) {
            LivingEntityToBeRemoved.add(entity);
        }
    }
}

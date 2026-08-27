package net.dingdingduang.somebasicskills.globalmethods;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.event.SBSTickEventAttributeMethods;
import net.dingdingduang.somebasicskills.sbsattributes.SBSAttributes;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerValue2BaseMultiplierMap;
import static net.dingdingduang.somebasicskills.sbsattributes.PermAttributesDistributor.getServerEntityPermAttrNameWithOP2FinalValueMap;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class SBSAttributeMethods {
    //ASSUME every skill properly has setup, for example, getID2SkillData() is not null and empty, skill1.getValueUsage_01() will not be empty list
    public static void increasePlayerBaseStatusBySkillIDWhenUpgrading(ServerPlayerEntity sp1, String playerStatusName, String SkillID) {
        double amount = 0.0, previousAmount = 0.0;
        if (getGlobalPlayerSkillID2lvlMap().get(sp1).containsKey(SkillID)) {
            int playerSkillLVL = getGlobalPlayerSkillID2lvlMap().get(sp1).get(SkillID);
            SkillDataJson skill1 = getID2SkillData().get(SkillID);
            amount = skill1.getValueUsage_01().get(playerSkillLVL - 1);
            if (playerSkillLVL - 1 > 0) {
                previousAmount = skill1.getValueUsage_01().get(playerSkillLVL - 2);
            }
        }

        //ASSUME getSPlayerValue2BaseMultiplierMap() is not null simpy because it is initialized and should never be null unless server is offline
        HashMap<String, Double> tempPlayerStatusMap = getSPlayerValue2BaseMultiplierMap().get(sp1);
        //add status value based on given attr i.e. HP ATK SPEED
        if (tempPlayerStatusMap.containsKey(playerStatusName)) {
            tempPlayerStatusMap.put(playerStatusName, amount - previousAmount + tempPlayerStatusMap.get(playerStatusName));
        }
    }

    public static void decreasePlayerBaseStatusBySkillIDWhenDowngrading(ServerPlayerEntity sp1, String playerStatusName, String SkillID) {
        SkillDataJson skill1 = getID2SkillData().get(SkillID);
        double amount = 0.0, previousAmount = skill1.getValueUsage_01().get(0);
        if (getGlobalPlayerSkillID2lvlMap().get(sp1).containsKey(SkillID)) {
            int playerSkillLVL = getGlobalPlayerSkillID2lvlMap().get(sp1).get(SkillID);
            amount = skill1.getValueUsage_01().get(playerSkillLVL - 1);
            if (playerSkillLVL < skill1.getValueUsage_01().size()) {
                previousAmount = skill1.getValueUsage_01().get(playerSkillLVL);
            }
        }

        //ASSUME getSPlayerValue2BaseMultiplierMap() is not null simpy because it is initialized and should never be null unless server is offline
        HashMap<String, Double> tempPlayerStatusMap = getSPlayerValue2BaseMultiplierMap().get(sp1);
        //add status value based on given attr i.e. HP ATK SPEED
        if (tempPlayerStatusMap.containsKey(playerStatusName)) {
            tempPlayerStatusMap.put(playerStatusName, amount - previousAmount + tempPlayerStatusMap.get(playerStatusName));
        }
    }

    public static void setPlayerBaseStatusBySkillIDWhenLogin(ServerPlayerEntity sp1, String playerStatusName, String SkillID) {
        double amount = 0.0;
        if (getGlobalPlayerSkillID2lvlMap().get(sp1).containsKey(SkillID)) {
            SkillDataJson skill1 = getID2SkillData().get(SkillID);
            int playerSkillLVL = getGlobalPlayerSkillID2lvlMap().get(sp1).get(SkillID);
            amount = skill1.getValueUsage_01().get(playerSkillLVL - 1);
        }

        //ASSUME getSPlayerValue2BaseMultiplierMap() is not null simpy because it is initialized and should never be null unless server is offline
        HashMap<String, Double> tempPlayerStatusMap = getSPlayerValue2BaseMultiplierMap().get(sp1);
        if (tempPlayerStatusMap.containsKey(playerStatusName)) {
            tempPlayerStatusMap.put(playerStatusName, amount + tempPlayerStatusMap.get(playerStatusName));
        }
    }

    public static double getTimedAttributeValueFromLivingEntity(LivingEntity entity, EntityAttribute sbsAttribute, int operation) {
        //retrieve entity timed status map
        HashMap<String, Double> entityStatusMap = SBSTickEventAttributeMethods.getServerEntityAttrNameWithOP2FinalValue(entity);
        if (entityStatusMap != null) {
            String attrNameWithOpPostfix = sbsAttribute.getTranslationKey() + operation;
            if (entityStatusMap.containsKey(attrNameWithOpPostfix)) {
                return entityStatusMap.get(attrNameWithOpPostfix);
            }
        }
        return 0.0;
    }

    public static double getPermAttributeValueFromLivingEntity(LivingEntity entity, EntityAttribute sbsAttribute, int operation) {
        //retrieve entity perm status map
        HashMap<String, Double> entityStatusMap = getServerEntityPermAttrNameWithOP2FinalValueMap(entity);
        if (entityStatusMap != null) {
            String attrNameWithOpPostfix = sbsAttribute.getTranslationKey()+ operation;
            if (entityStatusMap.containsKey(attrNameWithOpPostfix)) {
                return entityStatusMap.get(attrNameWithOpPostfix);
            }
        }
        return 0.0;
    }

    public static int getTimedAttributeRemainingMaxTotalTickCountFromLivingEntity(LivingEntity entity, EntityAttribute sbsAttribute, int operation) {
        //retrieve entity timed status map
        HashMap<String, Integer> entityRemainingMaxTotalTickCountFromEntityMap = SBSTickEventAttributeMethods.getServerEntityAttrNameRemainingTickCountMapFromLivingEntity(entity);
        if (entityRemainingMaxTotalTickCountFromEntityMap != null) {
            String attrNameWithOpPostfix = sbsAttribute.getTranslationKey()+ operation;
            if (entityRemainingMaxTotalTickCountFromEntityMap.containsKey(attrNameWithOpPostfix)) {
                return entityRemainingMaxTotalTickCountFromEntityMap.get(attrNameWithOpPostfix);
            }
        }
        return 0;
    }

    public static double getTotalAttributeValueFromServerPlayer(ServerPlayerEntity sp1, String attrName) {
        return getSPlayerValue2BaseMultiplierMap().get(sp1).get(attrName);
    }



    //===========================
    public static float getSuperArmorAttrVal(LivingEntity entity) {
        return (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_BENEFICIAL_STATUS_SUPER_ARMOR, Constants.OP_ADDITION)
                + (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_BENEFICIAL_STATUS_SUPER_ARMOR, Constants.OP_ADDITION);
    }

    public static float getInvincibilityAttrVal(LivingEntity entity) {
        return (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_BENEFICIAL_STATUS_INVINCIBILITY, Constants.OP_ADDITION)
                + (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_BENEFICIAL_STATUS_INVINCIBILITY, Constants.OP_ADDITION);
    }

    public static float getBeingAttackedAttrVal(LivingEntity entity) {
        return (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_BEING_ATTACKED, Constants.OP_ADDITION);
    }

    public static float getStationaryAttrVal(LivingEntity entity) {
        return (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_STATIONARY, Constants.OP_ADDITION);
    }


    public static float getUninterruptibleAttrVal(LivingEntity entity) {
        float tempAttrAdd = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_UNINTERRUPTIBLE_CHANCE, Constants.OP_ADDITION);
        float tempAttrMul = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_UNINTERRUPTIBLE_CHANCE, Constants.OP_MULTIPLY_BASE);
        float permAttrAdd = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_UNINTERRUPTIBLE_CHANCE, Constants.OP_ADDITION);
        float permAttrMul = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_UNINTERRUPTIBLE_CHANCE, Constants.OP_MULTIPLY_BASE);
        return (tempAttrAdd + permAttrAdd) * (1f + tempAttrMul + permAttrMul);
    }

    public static float getTotalMPAttrVal(LivingEntity entity) {
        float tempAttrAdd = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_MAX_MP, Constants.OP_ADDITION);
        float tempAttrMul = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_MAX_MP, Constants.OP_MULTIPLY_BASE);
        float permAttrAdd = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_MAX_MP, Constants.OP_ADDITION);
        float permAttrMul = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_MAX_MP, Constants.OP_MULTIPLY_BASE);
        return (tempAttrAdd + permAttrAdd) * (1f + tempAttrMul + permAttrMul);
    }

    public static float getCooldownAttrVal(LivingEntity entity, float currentSkillIDCooldown) {
        float tempAttrAdd = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_COOLDOWN, Constants.OP_ADDITION);
        float tempAttrMul = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_COOLDOWN, Constants.OP_MULTIPLY_BASE);
        float permAttrAdd = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_COOLDOWN, Constants.OP_ADDITION);
        float permAttrMul = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_COOLDOWN, Constants.OP_MULTIPLY_BASE);
        return (currentSkillIDCooldown + tempAttrAdd + permAttrAdd) * (1f + tempAttrMul + permAttrMul);
    }

    public static float getChannelingAttrVal(LivingEntity entity, float currentSkillIDChannelingTime) {
        float tempAttrAdd = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_CHANNELING, Constants.OP_ADDITION);
        float tempAttrMul = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_CHANNELING, Constants.OP_MULTIPLY_BASE);
        float permAttrAdd = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_CHANNELING, Constants.OP_ADDITION);
        float permAttrMul = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_CHANNELING, Constants.OP_MULTIPLY_BASE);
        return (currentSkillIDChannelingTime + tempAttrAdd + permAttrAdd) * (1f + tempAttrMul + permAttrMul);
    }

    public static float getPhysicalCritAttrVal(LivingEntity entity) {
        float tempAttrAdd = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_CRIT_CHANCE, Constants.OP_ADDITION);
        float tempAttrMul = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_CRIT_CHANCE, Constants.OP_MULTIPLY_BASE);
        float permAttrAdd = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_CRIT_CHANCE, Constants.OP_ADDITION);
        float permAttrMul = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_CRIT_CHANCE, Constants.OP_MULTIPLY_BASE);
        return (tempAttrAdd + permAttrAdd) * (1f + tempAttrMul + permAttrMul);
    }

    public static float getMagicalCritAttrVal(LivingEntity entity) {
        float tempAttrAdd = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_MAGIC_CRIT_CHANCE, Constants.OP_ADDITION);
        float tempAttrMul = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_MAGIC_CRIT_CHANCE, Constants.OP_MULTIPLY_BASE);
        float permAttrAdd = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_MAGIC_CRIT_CHANCE, Constants.OP_ADDITION);
        float permAttrMul = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_MAGIC_CRIT_CHANCE, Constants.OP_MULTIPLY_BASE);
        return (tempAttrAdd + permAttrAdd) * (1f + tempAttrMul + permAttrMul);
    }

    public static float getPhysicalCritDamageAttrVal(LivingEntity entity) {
        float tempAttrAdd = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_CRIT_DAMAGE, Constants.OP_ADDITION);
        float tempAttrMul = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_CRIT_DAMAGE, Constants.OP_MULTIPLY_BASE);
        float permAttrAdd = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_CRIT_DAMAGE, Constants.OP_ADDITION);
        float permAttrMul = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_CRIT_DAMAGE, Constants.OP_MULTIPLY_BASE);
        return (tempAttrAdd + permAttrAdd) * (1f + tempAttrMul + permAttrMul);
    }

    public static float getMagicalCritDamageAttrVal(LivingEntity entity) {
        float tempAttrAdd = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_MAGIC_CRIT_DAMAGE, Constants.OP_ADDITION);
        float tempAttrMul = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_MAGIC_CRIT_DAMAGE, Constants.OP_MULTIPLY_BASE);
        float permAttrAdd = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_MAGIC_CRIT_DAMAGE, Constants.OP_ADDITION);
        float permAttrMul = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_MAGIC_CRIT_DAMAGE, Constants.OP_MULTIPLY_BASE);
        return (tempAttrAdd + permAttrAdd) * (1f + tempAttrMul + permAttrMul);
    }

    public static float getPhysicalBackstabCritAttrVal(LivingEntity entity) {
        float tempAttrAdd = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_BACKSTAB_CRIT_CHANCE, Constants.OP_ADDITION);
        float tempAttrMul = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_BACKSTAB_CRIT_CHANCE, Constants.OP_MULTIPLY_BASE);
        float permAttrAdd = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_BACKSTAB_CRIT_CHANCE, Constants.OP_ADDITION);
        float permAttrMul = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_BACKSTAB_CRIT_CHANCE, Constants.OP_MULTIPLY_BASE);
        return (tempAttrAdd + permAttrAdd) * (1f + tempAttrMul + permAttrMul);
    }

    public static float getMagicalBackstabCritAttrVal(LivingEntity entity) {
        float tempAttrAdd = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_MAGIC_BACKSTAB_CRIT_CHANCE, Constants.OP_ADDITION);
        float tempAttrMul = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_MAGIC_BACKSTAB_CRIT_CHANCE, Constants.OP_MULTIPLY_BASE);
        float permAttrAdd = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_MAGIC_BACKSTAB_CRIT_CHANCE, Constants.OP_ADDITION);
        float permAttrMul = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_MAGIC_BACKSTAB_CRIT_CHANCE, Constants.OP_MULTIPLY_BASE);
        return (tempAttrAdd + permAttrAdd) * (1f + tempAttrMul + permAttrMul);
    }

    public static float getIndependenceDamageAttrVal(LivingEntity entity) {
        float tempAttrAdd = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_INDEPENDENCE_DMG, Constants.OP_ADDITION);
        float tempAttrMul = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_INDEPENDENCE_DMG, Constants.OP_MULTIPLY_BASE);
        float permAttrAdd = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_INDEPENDENCE_DMG, Constants.OP_ADDITION);
        float permAttrMul = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_INDEPENDENCE_DMG, Constants.OP_MULTIPLY_BASE);
        return (tempAttrAdd + permAttrAdd) * (1f + tempAttrMul + permAttrMul);
    }

    public static float getExtraReceivedDamageAttrVal(LivingEntity entity) {
        float tempAttrAdd = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_EXTRA_DAMAGE_RECEIVED, Constants.OP_ADDITION);
        float tempAttrMul = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_EXTRA_DAMAGE_RECEIVED, Constants.OP_MULTIPLY_BASE);
        float permAttrAdd = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_EXTRA_DAMAGE_RECEIVED, Constants.OP_ADDITION);
        float permAttrMul = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_EXTRA_DAMAGE_RECEIVED, Constants.OP_MULTIPLY_BASE);
        return (tempAttrAdd + permAttrAdd) * (1f + tempAttrMul + permAttrMul);
    }

    public static float getSkillDamageAttrVal(LivingEntity entity) {
        float tempAttrAdd = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_SKILL_DMG, Constants.OP_ADDITION);
        float tempAttrMul = (float) getTimedAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_SKILL_DMG, Constants.OP_MULTIPLY_BASE);
        float permAttrAdd = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_SKILL_DMG, Constants.OP_ADDITION);
        float permAttrMul = (float) getPermAttributeValueFromLivingEntity(entity, SBSAttributes.SBS_ATTRIBUTE_SKILL_DMG, Constants.OP_MULTIPLY_BASE);
        return (tempAttrAdd + permAttrAdd) * (1f + tempAttrMul + permAttrMul);
    }
}

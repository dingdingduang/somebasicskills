package net.dingdingduang.somebasicskills.sbsattributes.statusquery;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerBaseMultiplierStatusMap;
import static net.dingdingduang.somebasicskills.sbsattributes.statusquery.AttributeServerPlayerStatusQueryMethods.*;

public class AttributeClientPlayerStatusQueryMethods {
    public static void setupSkillDataClientPlayerInitialization() {
        HashMap<String, Double> clientPlayerStatus = getCPlayerBaseMultiplierStatusMap();

        //minecraft attribute
        clientPlayerStatus.put(ATTACK_DAMAGE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ATTACK_DAMAGE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ATTACK_KNOCKBACK_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ATTACK_KNOCKBACK_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(KNOCKBACK_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(KNOCKBACK_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ASPD_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ASPD_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(MOVS_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(MOVS_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(MAX_HP_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(MAX_HP_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ARMOR_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ARMOR_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ARMOR_TOUGHNESS_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ARMOR_TOUGHNESS_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(LUCK_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(LUCK_MULTIPLIER + ALL_SKILLS, 0.0);


        //SBS Attribute
        clientPlayerStatus.put(MAX_MP_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(MAX_MP_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(ARMOR_PIERCE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ARMOR_PIERCE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(PROTECTION_PIERCE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(PROTECTION_PIERCE_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(COOLDOWN_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(COOLDOWN_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(CHANNELING_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(CHANNELING_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(UNINTERRUPTIBLE_CHANCE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(UNINTERRUPTIBLE_CHANCE_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(INDEPENDENCE_DMG_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(INDEPENDENCE_DMG_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(PHYSIC_CRIT_CHANCE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(PHYSIC_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(PHYSIC_CRIT_DAMAGE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(PHYSIC_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(MAGIC_DMG_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(MAGIC_DMG_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(MAGIC_CRIT_CHANCE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(MAGIC_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(MAGIC_CRIT_DAMAGE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(MAGIC_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(BACKSTAB_DMG_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(BACKSTAB_DMG_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(PHYSIC_BACKSTAB_CRIT_CHANCE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(PHYSIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(PHYSIC_BACKSTAB_CRIT_DAMAGE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(PHYSIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(MAGIC_BACKSTAB_CRIT_CHANCE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(MAGIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(MAGIC_BACKSTAB_CRIT_DAMAGE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(MAGIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(SKILL_DMG_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(SKILL_DMG_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(LIFE_STEAL_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(LIFE_STEAL_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(EXTRA_DAMAGE_RECEIVED_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(EXTRA_DAMAGE_RECEIVED_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(EFFECT_AREA_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(EFFECT_AREA_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(DURATION_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(DURATION_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(DISTANCE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(DISTANCE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(STATIONARY_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(STATIONARY_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(BENEFICIAL_STATUS_INVINCIBILITY_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(BENEFICIAL_STATUS_INVINCIBILITY_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(BENEFICIAL_STATUS_SUPER_ARMOR_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(BENEFICIAL_STATUS_SUPER_ARMOR_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(ABNORMAL_STATUS_BLEEDING_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_BLEEDING_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_SHOCK_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_SHOCK_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_FREEZE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_FREEZE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_BURN_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_BURN_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_CURSED_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_CURSED_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_DISARM_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_DISARM_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_IMMOBILIZATION_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_IMMOBILIZATION_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_SLEEP_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_SLEEP_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_CONFUSION_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_CONFUSION_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_STUN_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_STUN_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(ABNORMAL_STATUS_ALL_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_ALL_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_BLEEDING_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_BLEEDING_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_SHOCK_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_SHOCK_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_FREEZE_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_FREEZE_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_BURN_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_BURN_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_CURSED_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_CURSED_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_DISARM_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_DISARM_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_STUN_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ABNORMAL_STATUS_STUN_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(ALL_DMG_REDUCTION_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ALL_DMG_REDUCTION_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(PHYSIC_DMG_REDUCTION_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(PHYSIC_DMG_REDUCTION_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(MAGIC_DMG_REDUCTION_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(MAGIC_DMG_REDUCTION_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(ELEMENT_LIGHT_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_LIGHT_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_DARKNESS_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_DARKNESS_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_METAL_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_METAL_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_WOOD_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_WOOD_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_EARTH_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_EARTH_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_FIRE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_FIRE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_AIR_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_AIR_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_WATER_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_WATER_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(ELEMENT_LIGHT_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_LIGHT_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_DARKNESS_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_DARKNESS_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_METAL_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_METAL_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_WOOD_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_WOOD_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_EARTH_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_EARTH_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_FIRE_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_FIRE_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_AIR_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_AIR_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_WATER_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(ELEMENT_WATER_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(BEING_ATTACKED_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(BEING_ATTACKED_MULTIPLIER + ALL_SKILLS, 0.0);

        //========================================================
        //minecraft attribute temporary
        clientPlayerStatus.put(TEMPORARY_PREFIX + ATTACK_DAMAGE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ATTACK_DAMAGE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ATTACK_KNOCKBACK_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ATTACK_KNOCKBACK_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + KNOCKBACK_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + KNOCKBACK_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ASPD_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ASPD_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MOVS_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MOVS_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MAX_HP_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MAX_HP_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ARMOR_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ARMOR_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ARMOR_TOUGHNESS_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ARMOR_TOUGHNESS_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + LUCK_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + LUCK_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + JUMP_STRENGTH_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + JUMP_STRENGTH_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MINING_EFFICIENCY_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MINING_EFFICIENCY_MULTIPLIER + ALL_SKILLS, 0.0);

        //SBS attribute temporary
        clientPlayerStatus.put(TEMPORARY_PREFIX + MAX_MP_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MAX_MP_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(TEMPORARY_PREFIX + ARMOR_PIERCE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ARMOR_PIERCE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + PROTECTION_PIERCE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + PROTECTION_PIERCE_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(TEMPORARY_PREFIX + COOLDOWN_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + COOLDOWN_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(TEMPORARY_PREFIX + CHANNELING_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + CHANNELING_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + UNINTERRUPTIBLE_CHANCE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + UNINTERRUPTIBLE_CHANCE_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(TEMPORARY_PREFIX + INDEPENDENCE_DMG_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + INDEPENDENCE_DMG_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(TEMPORARY_PREFIX + LIFE_STEAL_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + LIFE_STEAL_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(TEMPORARY_PREFIX + EXTRA_DAMAGE_RECEIVED_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + EXTRA_DAMAGE_RECEIVED_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(TEMPORARY_PREFIX + PHYSIC_CRIT_CHANCE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + PHYSIC_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + PHYSIC_CRIT_DAMAGE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + PHYSIC_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MAGIC_DMG_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MAGIC_DMG_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MAGIC_CRIT_CHANCE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MAGIC_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MAGIC_CRIT_DAMAGE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MAGIC_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(TEMPORARY_PREFIX + BACKSTAB_DMG_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + BACKSTAB_DMG_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + PHYSIC_BACKSTAB_CRIT_CHANCE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + PHYSIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + PHYSIC_BACKSTAB_CRIT_DAMAGE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + PHYSIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MAGIC_BACKSTAB_CRIT_CHANCE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MAGIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MAGIC_BACKSTAB_CRIT_DAMAGE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MAGIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(TEMPORARY_PREFIX + SKILL_DMG_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + SKILL_DMG_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + OVERALL_DMG_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + OVERALL_DMG_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(TEMPORARY_PREFIX + EFFECT_AREA_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + EFFECT_AREA_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + DURATION_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + DURATION_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + DISTANCE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + DISTANCE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + STATIONARY_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + STATIONARY_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(TEMPORARY_PREFIX + BENEFICIAL_STATUS_INVINCIBILITY_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + BENEFICIAL_STATUS_INVINCIBILITY_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + BENEFICIAL_STATUS_SUPER_ARMOR_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + BENEFICIAL_STATUS_SUPER_ARMOR_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_BLEEDING_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_BLEEDING_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_SHOCK_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_SHOCK_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_FREEZE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_FREEZE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_BURN_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_BURN_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_CURSED_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_CURSED_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_DISARM_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_DISARM_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_IMMOBILIZATION_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_IMMOBILIZATION_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_SLEEP_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_SLEEP_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_CONFUSION_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_CONFUSION_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_STUN_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_STUN_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_SILENCE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_SILENCE_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_ALL_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_ALL_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_BLEEDING_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_BLEEDING_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_SHOCK_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_SHOCK_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_FREEZE_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_FREEZE_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_BURN_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_BURN_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_CURSED_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_CURSED_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_DISARM_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_DISARM_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_STUN_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_STUN_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_SILENCE_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ABNORMAL_STATUS_SILENCE_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(TEMPORARY_PREFIX + ALL_DMG_REDUCTION_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ALL_DMG_REDUCTION_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + PHYSIC_DMG_REDUCTION_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + PHYSIC_DMG_REDUCTION_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MAGIC_DMG_REDUCTION_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + MAGIC_DMG_REDUCTION_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_LIGHT_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_LIGHT_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_DARKNESS_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_DARKNESS_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_METAL_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_METAL_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_WOOD_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_WOOD_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_EARTH_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_EARTH_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_FIRE_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_FIRE_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_AIR_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_AIR_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_WATER_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_WATER_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_LIGHT_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_LIGHT_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_DARKNESS_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_DARKNESS_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_METAL_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_METAL_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_WOOD_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_WOOD_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_EARTH_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_EARTH_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_FIRE_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_FIRE_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_AIR_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_AIR_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_WATER_RESIST_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + ELEMENT_WATER_RESIST_MULTIPLIER + ALL_SKILLS, 0.0);

        clientPlayerStatus.put(TEMPORARY_PREFIX + BEING_ATTACKED_BASE + ALL_SKILLS, 0.0);
        clientPlayerStatus.put(TEMPORARY_PREFIX + BEING_ATTACKED_MULTIPLIER + ALL_SKILLS, 0.0);
    }

    public static double getCPlayerAttribute(String AttrName) {
        HashMap<String, Double> tempClientPlayerAttributeMap = getCPlayerBaseMultiplierStatusMap();
        Double tempAttributeVal = tempClientPlayerAttributeMap.get(AttrName);
        if (tempAttributeVal != null) {
            return tempAttributeVal;
        }
        return 0;
    }

    public static void increaseCPlayerAttribute(String AttrName, double amount) {
        HashMap<String, Double> tempClientPlayerAttributeMap = getCPlayerBaseMultiplierStatusMap();
        Double tempAttributeVal = tempClientPlayerAttributeMap.get(AttrName);
        if (tempAttributeVal != null) {
            tempClientPlayerAttributeMap.put(AttrName, tempAttributeVal + amount);
        }
        else {
            tempClientPlayerAttributeMap.put(AttrName, amount);
        }
    }



    //====================================================================
    public static double getCPlayerAttackDamageBase() {
        return getCPlayerAttribute(ATTACK_DAMAGE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAttackDamageBase(double amount) {
        increaseCPlayerAttribute(ATTACK_DAMAGE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAttackDamageBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ATTACK_DAMAGE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAttackDamageBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ATTACK_DAMAGE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAttackDamageMultiplier() {
        return getCPlayerAttribute(ATTACK_DAMAGE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAttackDamageMultiplier(double amount) {
        increaseCPlayerAttribute(ATTACK_DAMAGE_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAttackDamageMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ATTACK_DAMAGE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAttackDamageMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ATTACK_DAMAGE_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAttackKnockbackBase() {
        return getCPlayerAttribute(ATTACK_KNOCKBACK_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAttackKnockbackBase(double amount) {
        increaseCPlayerAttribute(ATTACK_KNOCKBACK_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAttackKnockbackBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ATTACK_KNOCKBACK_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAttackKnockbackBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ATTACK_KNOCKBACK_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAttackKnockbackMultiplier() {
        return getCPlayerAttribute(ATTACK_KNOCKBACK_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAttackKnockbackMultiplier(double amount) {
        increaseCPlayerAttribute(ATTACK_KNOCKBACK_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAttackKnockbackMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ATTACK_KNOCKBACK_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAttackKnockbackMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ATTACK_KNOCKBACK_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerKnockbackResistBase() {
        return getCPlayerAttribute(KNOCKBACK_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerKnockbackResistBase(double amount) {
        increaseCPlayerAttribute(KNOCKBACK_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryKnockbackResistBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + KNOCKBACK_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryKnockbackResistBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + KNOCKBACK_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerKnockbackResistMultiplier() {
        return getCPlayerAttribute(KNOCKBACK_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerKnockbackResistMultiplier(double amount) {
        increaseCPlayerAttribute(KNOCKBACK_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryKnockbackResistMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + KNOCKBACK_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryKnockbackResistMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + KNOCKBACK_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAspdBase() {
        return getCPlayerAttribute(ASPD_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAspdBase(double amount) {
        increaseCPlayerAttribute(ASPD_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAspdBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ASPD_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAspdBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ASPD_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAspdMultiplier() {
        return getCPlayerAttribute(ASPD_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAspdMultiplier(double amount) {
        increaseCPlayerAttribute(ASPD_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAspdMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ASPD_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAspdMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ASPD_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerMovsBase() {
        return getCPlayerAttribute(MOVS_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerMovsBase(double amount) {
        increaseCPlayerAttribute(MOVS_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMovsBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MOVS_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMovsBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MOVS_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerMovsMultiplier() {
        return getCPlayerAttribute(MOVS_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerMovsMultiplier(double amount) {
        increaseCPlayerAttribute(MOVS_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMovsMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MOVS_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMovsMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MOVS_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerMaxHpBase() {
        return getCPlayerAttribute(MAX_HP_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerMaxHpBase(double amount) {
        increaseCPlayerAttribute(MAX_HP_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMaxHpBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MAX_HP_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMaxHpBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MAX_HP_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerMaxHpMultiplier() {
        return getCPlayerAttribute(MAX_HP_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerMaxHpMultiplier(double amount) {
        increaseCPlayerAttribute(MAX_HP_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMaxHpMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MAX_HP_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMaxHpMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MAX_HP_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerArmorBase() {
        return getCPlayerAttribute(ARMOR_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerArmorBase(double amount) {
        increaseCPlayerAttribute(ARMOR_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryArmorBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ARMOR_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryArmorBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ARMOR_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerArmorMultiplier() {
        return getCPlayerAttribute(ARMOR_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerArmorMultiplier(double amount) {
        increaseCPlayerAttribute(ARMOR_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryArmorMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ARMOR_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryArmorMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ARMOR_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerArmorToughnessBase() {
        return getCPlayerAttribute(ARMOR_TOUGHNESS_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerArmorToughnessBase(double amount) {
        increaseCPlayerAttribute(ARMOR_TOUGHNESS_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryArmorToughnessBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ARMOR_TOUGHNESS_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryArmorToughnessBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ARMOR_TOUGHNESS_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerArmorToughnessMultiplier() {
        return getCPlayerAttribute(ARMOR_TOUGHNESS_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerArmorToughnessMultiplier(double amount) {
        increaseCPlayerAttribute(ARMOR_TOUGHNESS_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryArmorToughnessMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ARMOR_TOUGHNESS_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryArmorToughnessMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ARMOR_TOUGHNESS_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerLuckBase() {
        return getCPlayerAttribute(LUCK_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerLuckBase(double amount) {
        increaseCPlayerAttribute(LUCK_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryLuckBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + LUCK_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryLuckBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + LUCK_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerLuckMultiplier() {
        return getCPlayerAttribute(LUCK_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerLuckMultiplier(double amount) {
        increaseCPlayerAttribute(LUCK_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryLuckMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + LUCK_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryLuckMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + LUCK_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerJumpStrengthBase() {
        return getCPlayerAttribute(JUMP_STRENGTH_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerJumpStrengthBase(double amount) {
        increaseCPlayerAttribute(JUMP_STRENGTH_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryJumpStrengthBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + JUMP_STRENGTH_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryJumpStrengthBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + JUMP_STRENGTH_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerJumpStrengthMultiplier() {
        return getCPlayerAttribute(JUMP_STRENGTH_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerJumpStrengthMultiplier(double amount) {
        increaseCPlayerAttribute(JUMP_STRENGTH_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryJumpStrengthMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + JUMP_STRENGTH_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryJumpStrengthMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + JUMP_STRENGTH_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerMiningEfficiencyBase() {
        return getCPlayerAttribute(MINING_EFFICIENCY_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerMiningEfficiencyBase(double amount) {
        increaseCPlayerAttribute(MINING_EFFICIENCY_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMiningEfficiencyBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MINING_EFFICIENCY_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMiningEfficiencyBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MINING_EFFICIENCY_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerMiningEfficiencyMultiplier() {
        return getCPlayerAttribute(MINING_EFFICIENCY_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerMiningEfficiencyMultiplier(double amount) {
        increaseCPlayerAttribute(MINING_EFFICIENCY_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMiningEfficiencyMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MINING_EFFICIENCY_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMiningEfficiencyMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MINING_EFFICIENCY_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerMaxMpBase() {
        return getCPlayerAttribute(MAX_MP_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerMaxMpBase(double amount) {
        increaseCPlayerAttribute(MAX_MP_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMaxMpBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MAX_MP_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMaxMpBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MAX_MP_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerMaxMpMultiplier() {
        return getCPlayerAttribute(MAX_MP_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerMaxMpMultiplier(double amount) {
        increaseCPlayerAttribute(MAX_MP_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMaxMpMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MAX_MP_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMaxMpMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MAX_MP_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerArmorPierceBase() {
        return getCPlayerAttribute(ARMOR_PIERCE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerArmorPierceBase(double amount) {
        increaseCPlayerAttribute(ARMOR_PIERCE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryArmorPierceBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ARMOR_PIERCE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryArmorPierceBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ARMOR_PIERCE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerArmorPierceMultiplier() {
        return getCPlayerAttribute(ARMOR_PIERCE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerArmorPierceMultiplier(double amount) {
        increaseCPlayerAttribute(ARMOR_PIERCE_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryArmorPierceMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ARMOR_PIERCE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryArmorPierceMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ARMOR_PIERCE_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerProtectionPierceBase() {
        return getCPlayerAttribute(PROTECTION_PIERCE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerProtectionPierceBase(double amount) {
        increaseCPlayerAttribute(PROTECTION_PIERCE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryProtectionPierceBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + PROTECTION_PIERCE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryProtectionPierceBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + PROTECTION_PIERCE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerProtectionPierceMultiplier() {
        return getCPlayerAttribute(PROTECTION_PIERCE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerProtectionPierceMultiplier(double amount) {
        increaseCPlayerAttribute(PROTECTION_PIERCE_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryProtectionPierceMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + PROTECTION_PIERCE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryProtectionPierceMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + PROTECTION_PIERCE_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerCooldownBase() {
        return getCPlayerAttribute(COOLDOWN_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerCooldownBase(double amount) {
        increaseCPlayerAttribute(COOLDOWN_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryCooldownBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + COOLDOWN_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryCooldownBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + COOLDOWN_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerCooldownMultiplier() {
        return getCPlayerAttribute(COOLDOWN_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerCooldownMultiplier(double amount) {
        increaseCPlayerAttribute(COOLDOWN_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryCooldownMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + COOLDOWN_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryCooldownMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + COOLDOWN_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerChannelingBase() {
        return getCPlayerAttribute(CHANNELING_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerChannelingBase(double amount) {
        increaseCPlayerAttribute(CHANNELING_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryChannelingBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + CHANNELING_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryChannelingBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + CHANNELING_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerChannelingMultiplier() {
        return getCPlayerAttribute(CHANNELING_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerChannelingMultiplier(double amount) {
        increaseCPlayerAttribute(CHANNELING_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryChannelingMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + CHANNELING_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryChannelingMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + CHANNELING_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerUninterruptibleChanceBase() {
        return getCPlayerAttribute(UNINTERRUPTIBLE_CHANCE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerUninterruptibleChanceBase(double amount) {
        increaseCPlayerAttribute(UNINTERRUPTIBLE_CHANCE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryUninterruptibleChanceBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + UNINTERRUPTIBLE_CHANCE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryUninterruptibleChanceBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + UNINTERRUPTIBLE_CHANCE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerUninterruptibleChanceMultiplier() {
        return getCPlayerAttribute(UNINTERRUPTIBLE_CHANCE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerUninterruptibleChanceMultiplier(double amount) {
        increaseCPlayerAttribute(UNINTERRUPTIBLE_CHANCE_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryUninterruptibleChanceMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + UNINTERRUPTIBLE_CHANCE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryUninterruptibleChanceMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + UNINTERRUPTIBLE_CHANCE_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerIndependenceDmgBase() {
        return getCPlayerAttribute(INDEPENDENCE_DMG_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerIndependenceDmgBase(double amount) {
        increaseCPlayerAttribute(INDEPENDENCE_DMG_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryIndependenceDmgBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + INDEPENDENCE_DMG_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryIndependenceDmgBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + INDEPENDENCE_DMG_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerIndependenceDmgMultiplier() {
        return getCPlayerAttribute(INDEPENDENCE_DMG_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerIndependenceDmgMultiplier(double amount) {
        increaseCPlayerAttribute(INDEPENDENCE_DMG_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryIndependenceDmgMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + INDEPENDENCE_DMG_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryIndependenceDmgMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + INDEPENDENCE_DMG_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerPhysicCritChanceBase() {
        return getCPlayerAttribute(PHYSIC_CRIT_CHANCE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerPhysicCritChanceBase(double amount) {
        increaseCPlayerAttribute(PHYSIC_CRIT_CHANCE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryPhysicCritChanceBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_CRIT_CHANCE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryPhysicCritChanceBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_CRIT_CHANCE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerPhysicCritChanceMultiplier() {
        return getCPlayerAttribute(PHYSIC_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerPhysicCritChanceMultiplier(double amount) {
        increaseCPlayerAttribute(PHYSIC_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryPhysicCritChanceMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryPhysicCritChanceMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerPhysicCritDamageBase() {
        return getCPlayerAttribute(PHYSIC_CRIT_DAMAGE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerPhysicCritDamageBase(double amount) {
        increaseCPlayerAttribute(PHYSIC_CRIT_DAMAGE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryPhysicCritDamageBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_CRIT_DAMAGE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryPhysicCritDamageBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_CRIT_DAMAGE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerPhysicCritDamageMultiplier() {
        return getCPlayerAttribute(PHYSIC_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerPhysicCritDamageMultiplier(double amount) {
        increaseCPlayerAttribute(PHYSIC_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryPhysicCritDamageMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryPhysicCritDamageMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerMagicDmgBase() {
        return getCPlayerAttribute(MAGIC_DMG_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerMagicDmgBase(double amount) {
        increaseCPlayerAttribute(MAGIC_DMG_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMagicDmgBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_DMG_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMagicDmgBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_DMG_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerMagicDmgMultiplier() {
        return getCPlayerAttribute(MAGIC_DMG_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerMagicDmgMultiplier(double amount) {
        increaseCPlayerAttribute(MAGIC_DMG_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMagicDmgMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_DMG_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMagicDmgMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_DMG_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerMagicCritChanceBase() {
        return getCPlayerAttribute(MAGIC_CRIT_CHANCE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerMagicCritChanceBase(double amount) {
        increaseCPlayerAttribute(MAGIC_CRIT_CHANCE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMagicCritChanceBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_CRIT_CHANCE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMagicCritChanceBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_CRIT_CHANCE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerMagicCritChanceMultiplier() {
        return getCPlayerAttribute(MAGIC_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerMagicCritChanceMultiplier(double amount) {
        increaseCPlayerAttribute(MAGIC_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMagicCritChanceMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMagicCritChanceMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerMagicCritDamageBase() {
        return getCPlayerAttribute(MAGIC_CRIT_DAMAGE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerMagicCritDamageBase(double amount) {
        increaseCPlayerAttribute(MAGIC_CRIT_DAMAGE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMagicCritDamageBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_CRIT_DAMAGE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMagicCritDamageBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_CRIT_DAMAGE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerMagicCritDamageMultiplier() {
        return getCPlayerAttribute(MAGIC_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerMagicCritDamageMultiplier(double amount) {
        increaseCPlayerAttribute(MAGIC_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMagicCritDamageMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMagicCritDamageMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerBackstabDmgBase() {
        return getCPlayerAttribute(BACKSTAB_DMG_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerBackstabDmgBase(double amount) {
        increaseCPlayerAttribute(BACKSTAB_DMG_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryBackstabDmgBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + BACKSTAB_DMG_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryBackstabDmgBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + BACKSTAB_DMG_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerBackstabDmgMultiplier() {
        return getCPlayerAttribute(BACKSTAB_DMG_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerBackstabDmgMultiplier(double amount) {
        increaseCPlayerAttribute(BACKSTAB_DMG_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryBackstabDmgMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + BACKSTAB_DMG_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryBackstabDmgMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + BACKSTAB_DMG_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerPhysicBackstabCritChanceBase() {
        return getCPlayerAttribute(PHYSIC_BACKSTAB_CRIT_CHANCE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerPhysicBackstabCritChanceBase(double amount) {
        increaseCPlayerAttribute(PHYSIC_BACKSTAB_CRIT_CHANCE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryPhysicBackstabCritChanceBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_BACKSTAB_CRIT_CHANCE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryPhysicBackstabCritChanceBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_BACKSTAB_CRIT_CHANCE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerPhysicBackstabCritChanceMultiplier() {
        return getCPlayerAttribute(PHYSIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerPhysicBackstabCritChanceMultiplier(double amount) {
        increaseCPlayerAttribute(PHYSIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryPhysicBackstabCritChanceMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryPhysicBackstabCritChanceMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerPhysicBackstabCritDamageBase() {
        return getCPlayerAttribute(PHYSIC_BACKSTAB_CRIT_DAMAGE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerPhysicBackstabCritDamageBase(double amount) {
        increaseCPlayerAttribute(PHYSIC_BACKSTAB_CRIT_DAMAGE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryPhysicBackstabCritDamageBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_BACKSTAB_CRIT_DAMAGE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryPhysicBackstabCritDamageBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_BACKSTAB_CRIT_DAMAGE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerPhysicBackstabCritDamageMultiplier() {
        return getCPlayerAttribute(PHYSIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerPhysicBackstabCritDamageMultiplier(double amount) {
        increaseCPlayerAttribute(PHYSIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryPhysicBackstabCritDamageMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryPhysicBackstabCritDamageMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerMagicBackstabCritChanceBase() {
        return getCPlayerAttribute(MAGIC_BACKSTAB_CRIT_CHANCE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerMagicBackstabCritChanceBase(double amount) {
        increaseCPlayerAttribute(MAGIC_BACKSTAB_CRIT_CHANCE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMagicBackstabCritChanceBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_BACKSTAB_CRIT_CHANCE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMagicBackstabCritChanceBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_BACKSTAB_CRIT_CHANCE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerMagicBackstabCritChanceMultiplier() {
        return getCPlayerAttribute(MAGIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerMagicBackstabCritChanceMultiplier(double amount) {
        increaseCPlayerAttribute(MAGIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMagicBackstabCritChanceMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMagicBackstabCritChanceMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerMagicBackstabCritDamageBase() {
        return getCPlayerAttribute(MAGIC_BACKSTAB_CRIT_DAMAGE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerMagicBackstabCritDamageBase(double amount) {
        increaseCPlayerAttribute(MAGIC_BACKSTAB_CRIT_DAMAGE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMagicBackstabCritDamageBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_BACKSTAB_CRIT_DAMAGE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMagicBackstabCritDamageBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_BACKSTAB_CRIT_DAMAGE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerMagicBackstabCritDamageMultiplier() {
        return getCPlayerAttribute(MAGIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerMagicBackstabCritDamageMultiplier(double amount) {
        increaseCPlayerAttribute(MAGIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMagicBackstabCritDamageMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMagicBackstabCritDamageMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerSkillDmgBase() {
        return getCPlayerAttribute(SKILL_DMG_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerSkillDmgBase(double amount) {
        increaseCPlayerAttribute(SKILL_DMG_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporarySkillDmgBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + SKILL_DMG_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporarySkillDmgBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + SKILL_DMG_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerSkillDmgMultiplier() {
        return getCPlayerAttribute(SKILL_DMG_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerSkillDmgMultiplier(double amount) {
        increaseCPlayerAttribute(SKILL_DMG_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporarySkillDmgMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + SKILL_DMG_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporarySkillDmgMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + SKILL_DMG_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerOverallDmgBase() {
        return getCPlayerAttribute(OVERALL_DMG_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerOverallDmgBase(double amount) {
        increaseCPlayerAttribute(OVERALL_DMG_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryOverallDmgBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + OVERALL_DMG_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryOverallDmgBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + OVERALL_DMG_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerOverallDmgMultiplier() {
        return getCPlayerAttribute(OVERALL_DMG_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerOverallDmgMultiplier(double amount) {
        increaseCPlayerAttribute(OVERALL_DMG_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryOverallDmgMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + OVERALL_DMG_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryOverallDmgMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + OVERALL_DMG_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerLifeStealBase() {
        return getCPlayerAttribute(LIFE_STEAL_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerLifeStealBase(double amount) {
        increaseCPlayerAttribute(LIFE_STEAL_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryLifeStealBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + LIFE_STEAL_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryLifeStealBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + LIFE_STEAL_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerLifeStealMultiplier() {
        return getCPlayerAttribute(LIFE_STEAL_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerLifeStealMultiplier(double amount) {
        increaseCPlayerAttribute(LIFE_STEAL_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryLifeStealMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + LIFE_STEAL_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryLifeStealMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + LIFE_STEAL_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerExtraDamageReceivedBase() {
        return getCPlayerAttribute(EXTRA_DAMAGE_RECEIVED_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerExtraDamageReceivedBase(double amount) {
        increaseCPlayerAttribute(EXTRA_DAMAGE_RECEIVED_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryExtraDamageReceivedBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + EXTRA_DAMAGE_RECEIVED_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryExtraDamageReceivedBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + EXTRA_DAMAGE_RECEIVED_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerExtraDamageReceivedMultiplier() {
        return getCPlayerAttribute(EXTRA_DAMAGE_RECEIVED_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerExtraDamageReceivedMultiplier(double amount) {
        increaseCPlayerAttribute(EXTRA_DAMAGE_RECEIVED_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryExtraDamageReceivedMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + EXTRA_DAMAGE_RECEIVED_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryExtraDamageReceivedMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + EXTRA_DAMAGE_RECEIVED_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerEffectAreaBase() {
        return getCPlayerAttribute(EFFECT_AREA_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerEffectAreaBase(double amount) {
        increaseCPlayerAttribute(EFFECT_AREA_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryEffectAreaBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + EFFECT_AREA_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryEffectAreaBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + EFFECT_AREA_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerEffectAreaMultiplier() {
        return getCPlayerAttribute(EFFECT_AREA_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerEffectAreaMultiplier(double amount) {
        increaseCPlayerAttribute(EFFECT_AREA_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryEffectAreaMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + EFFECT_AREA_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryEffectAreaMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + EFFECT_AREA_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerDurationBase() {
        return getCPlayerAttribute(DURATION_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerDurationBase(double amount) {
        increaseCPlayerAttribute(DURATION_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryDurationBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + DURATION_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryDurationBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + DURATION_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerDurationMultiplier() {
        return getCPlayerAttribute(DURATION_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerDurationMultiplier(double amount) {
        increaseCPlayerAttribute(DURATION_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryDurationMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + DURATION_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryDurationMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + DURATION_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerDistanceBase() {
        return getCPlayerAttribute(DISTANCE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerDistanceBase(double amount) {
        increaseCPlayerAttribute(DISTANCE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryDistanceBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + DISTANCE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryDistanceBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + DISTANCE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerDistanceMultiplier() {
        return getCPlayerAttribute(DISTANCE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerDistanceMultiplier(double amount) {
        increaseCPlayerAttribute(DISTANCE_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryDistanceMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + DISTANCE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryDistanceMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + DISTANCE_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerStationaryBase() {
        return getCPlayerAttribute(STATIONARY_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerStationaryBase(double amount) {
        increaseCPlayerAttribute(STATIONARY_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryStationaryBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + STATIONARY_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryStationaryBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + STATIONARY_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerStationaryMultiplier() {
        return getCPlayerAttribute(STATIONARY_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerStationaryMultiplier(double amount) {
        increaseCPlayerAttribute(STATIONARY_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryStationaryMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + STATIONARY_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryStationaryMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + STATIONARY_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerBeneficialStatusInvincibilityBase() {
        return getCPlayerAttribute(BENEFICIAL_STATUS_INVINCIBILITY_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerBeneficialStatusInvincibilityBase(double amount) {
        increaseCPlayerAttribute(BENEFICIAL_STATUS_INVINCIBILITY_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryBeneficialStatusInvincibilityBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + BENEFICIAL_STATUS_INVINCIBILITY_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryBeneficialStatusInvincibilityBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + BENEFICIAL_STATUS_INVINCIBILITY_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerBeneficialStatusInvincibilityMultiplier() {
        return getCPlayerAttribute(BENEFICIAL_STATUS_INVINCIBILITY_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerBeneficialStatusInvincibilityMultiplier(double amount) {
        increaseCPlayerAttribute(BENEFICIAL_STATUS_INVINCIBILITY_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryBeneficialStatusInvincibilityMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + BENEFICIAL_STATUS_INVINCIBILITY_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryBeneficialStatusInvincibilityMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + BENEFICIAL_STATUS_INVINCIBILITY_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerBeneficialStatusSuperArmorBase() {
        return getCPlayerAttribute(BENEFICIAL_STATUS_SUPER_ARMOR_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerBeneficialStatusSuperArmorBase(double amount) {
        increaseCPlayerAttribute(BENEFICIAL_STATUS_SUPER_ARMOR_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryBeneficialStatusSuperArmorBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + BENEFICIAL_STATUS_SUPER_ARMOR_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryBeneficialStatusSuperArmorBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + BENEFICIAL_STATUS_SUPER_ARMOR_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerBeneficialStatusSuperArmorMultiplier() {
        return getCPlayerAttribute(BENEFICIAL_STATUS_SUPER_ARMOR_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerBeneficialStatusSuperArmorMultiplier(double amount) {
        increaseCPlayerAttribute(BENEFICIAL_STATUS_SUPER_ARMOR_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryBeneficialStatusSuperArmorMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + BENEFICIAL_STATUS_SUPER_ARMOR_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryBeneficialStatusSuperArmorMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + BENEFICIAL_STATUS_SUPER_ARMOR_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusBleedingBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_BLEEDING_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusBleedingBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_BLEEDING_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusBleedingBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_BLEEDING_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusBleedingBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_BLEEDING_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusBleedingMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_BLEEDING_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusBleedingMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_BLEEDING_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusBleedingMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_BLEEDING_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusBleedingMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_BLEEDING_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusShockBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_SHOCK_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusShockBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_SHOCK_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusShockBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SHOCK_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusShockBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SHOCK_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusShockMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_SHOCK_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusShockMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_SHOCK_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusShockMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SHOCK_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusShockMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SHOCK_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusFreezeBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_FREEZE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusFreezeBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_FREEZE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusFreezeBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_FREEZE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusFreezeBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_FREEZE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusFreezeMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_FREEZE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusFreezeMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_FREEZE_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusFreezeMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_FREEZE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusFreezeMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_FREEZE_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusBurnBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_BURN_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusBurnBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_BURN_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusBurnBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_BURN_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusBurnBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_BURN_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusBurnMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_BURN_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusBurnMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_BURN_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusBurnMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_BURN_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusBurnMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_BURN_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusCursedBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_CURSED_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusCursedBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_CURSED_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusCursedBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_CURSED_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusCursedBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_CURSED_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusCursedMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_CURSED_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusCursedMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_CURSED_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusCursedMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_CURSED_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusCursedMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_CURSED_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusDisarmBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_DISARM_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusDisarmBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_DISARM_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusDisarmBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_DISARM_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusDisarmBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_DISARM_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusDisarmMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_DISARM_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusDisarmMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_DISARM_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusDisarmMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_DISARM_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusDisarmMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_DISARM_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusImmobilizationBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_IMMOBILIZATION_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusImmobilizationBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_IMMOBILIZATION_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusImmobilizationBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_IMMOBILIZATION_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusImmobilizationBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_IMMOBILIZATION_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusImmobilizationMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_IMMOBILIZATION_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusImmobilizationMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_IMMOBILIZATION_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusImmobilizationMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_IMMOBILIZATION_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusImmobilizationMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_IMMOBILIZATION_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusSleepBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_SLEEP_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusSleepBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_SLEEP_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusSleepBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SLEEP_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusSleepBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SLEEP_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusSleepMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_SLEEP_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusSleepMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_SLEEP_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusSleepMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SLEEP_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusSleepMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SLEEP_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusConfusionBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_CONFUSION_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusConfusionBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_CONFUSION_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusConfusionBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_CONFUSION_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusConfusionBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_CONFUSION_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusConfusionMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_CONFUSION_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusConfusionMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_CONFUSION_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusConfusionMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_CONFUSION_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusConfusionMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_CONFUSION_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusStunBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_STUN_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusStunBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_STUN_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusStunBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_STUN_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusStunBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_STUN_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusStunMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_STUN_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusStunMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_STUN_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusStunMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_STUN_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusStunMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_STUN_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusSilenceBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_SILENCE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusSilenceBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_SILENCE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusSilenceBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SILENCE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusSilenceBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SILENCE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusSilenceMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_SILENCE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusSilenceMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_SILENCE_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusSilenceMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SILENCE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusSilenceMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SILENCE_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusAllResistBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_ALL_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusAllResistBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_ALL_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusAllResistBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_ALL_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusAllResistBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_ALL_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusAllResistMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_ALL_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusAllResistMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_ALL_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusAllResistMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_ALL_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusAllResistMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_ALL_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusBleedingResistBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_BLEEDING_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusBleedingResistBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_BLEEDING_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusBleedingResistBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_BLEEDING_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusBleedingResistBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_BLEEDING_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusBleedingResistMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_BLEEDING_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusBleedingResistMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_BLEEDING_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusBleedingResistMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_BLEEDING_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusBleedingResistMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_BLEEDING_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusShockResistBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_SHOCK_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusShockResistBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_SHOCK_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusShockResistBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SHOCK_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusShockResistBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SHOCK_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusShockResistMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_SHOCK_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusShockResistMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_SHOCK_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusShockResistMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SHOCK_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusShockResistMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SHOCK_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusFreezeResistBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_FREEZE_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusFreezeResistBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_FREEZE_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusFreezeResistBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_FREEZE_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusFreezeResistBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_FREEZE_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusFreezeResistMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_FREEZE_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusFreezeResistMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_FREEZE_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusFreezeResistMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_FREEZE_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusFreezeResistMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_FREEZE_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusBurnResistBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_BURN_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusBurnResistBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_BURN_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusBurnResistBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_BURN_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusBurnResistBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_BURN_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusBurnResistMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_BURN_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusBurnResistMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_BURN_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusBurnResistMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_BURN_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusBurnResistMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_BURN_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusCursedResistBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_CURSED_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusCursedResistBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_CURSED_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusCursedResistBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_CURSED_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusCursedResistBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_CURSED_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusCursedResistMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_CURSED_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusCursedResistMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_CURSED_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusCursedResistMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_CURSED_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusCursedResistMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_CURSED_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusDisarmResistBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_DISARM_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusDisarmResistBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_DISARM_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusDisarmResistBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_DISARM_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusDisarmResistBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_DISARM_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusDisarmResistMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_DISARM_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusDisarmResistMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_DISARM_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusDisarmResistMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_DISARM_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusDisarmResistMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_DISARM_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusStunResistBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_STUN_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusStunResistBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_STUN_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusStunResistBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_STUN_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusStunResistBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_STUN_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusStunResistMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_STUN_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusStunResistMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_STUN_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusStunResistMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_STUN_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusStunResistMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_STUN_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAbnormalStatusSilenceResistBase() {
        return getCPlayerAttribute(ABNORMAL_STATUS_SILENCE_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusSilenceResistBase(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_SILENCE_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusSilenceResistBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SILENCE_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusSilenceResistBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SILENCE_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAbnormalStatusSilenceResistMultiplier() {
        return getCPlayerAttribute(ABNORMAL_STATUS_SILENCE_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAbnormalStatusSilenceResistMultiplier(double amount) {
        increaseCPlayerAttribute(ABNORMAL_STATUS_SILENCE_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAbnormalStatusSilenceResistMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SILENCE_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAbnormalStatusSilenceResistMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ABNORMAL_STATUS_SILENCE_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerAllDmgReductionBase() {
        return getCPlayerAttribute(ALL_DMG_REDUCTION_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerAllDmgReductionBase(double amount) {
        increaseCPlayerAttribute(ALL_DMG_REDUCTION_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAllDmgReductionBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ALL_DMG_REDUCTION_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAllDmgReductionBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ALL_DMG_REDUCTION_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerAllDmgReductionMultiplier() {
        return getCPlayerAttribute(ALL_DMG_REDUCTION_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerAllDmgReductionMultiplier(double amount) {
        increaseCPlayerAttribute(ALL_DMG_REDUCTION_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryAllDmgReductionMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ALL_DMG_REDUCTION_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryAllDmgReductionMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ALL_DMG_REDUCTION_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerPhysicDmgReductionBase() {
        return getCPlayerAttribute(PHYSIC_DMG_REDUCTION_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerPhysicDmgReductionBase(double amount) {
        increaseCPlayerAttribute(PHYSIC_DMG_REDUCTION_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryPhysicDmgReductionBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_DMG_REDUCTION_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryPhysicDmgReductionBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_DMG_REDUCTION_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerPhysicDmgReductionMultiplier() {
        return getCPlayerAttribute(PHYSIC_DMG_REDUCTION_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerPhysicDmgReductionMultiplier(double amount) {
        increaseCPlayerAttribute(PHYSIC_DMG_REDUCTION_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryPhysicDmgReductionMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_DMG_REDUCTION_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryPhysicDmgReductionMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + PHYSIC_DMG_REDUCTION_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerMagicDmgReductionBase() {
        return getCPlayerAttribute(MAGIC_DMG_REDUCTION_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerMagicDmgReductionBase(double amount) {
        increaseCPlayerAttribute(MAGIC_DMG_REDUCTION_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMagicDmgReductionBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_DMG_REDUCTION_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMagicDmgReductionBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_DMG_REDUCTION_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerMagicDmgReductionMultiplier() {
        return getCPlayerAttribute(MAGIC_DMG_REDUCTION_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerMagicDmgReductionMultiplier(double amount) {
        increaseCPlayerAttribute(MAGIC_DMG_REDUCTION_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryMagicDmgReductionMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_DMG_REDUCTION_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryMagicDmgReductionMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + MAGIC_DMG_REDUCTION_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerElementLightBase() {
        return getCPlayerAttribute(ELEMENT_LIGHT_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerElementLightBase(double amount) {
        increaseCPlayerAttribute(ELEMENT_LIGHT_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementLightBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_LIGHT_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementLightBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_LIGHT_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerElementLightMultiplier() {
        return getCPlayerAttribute(ELEMENT_LIGHT_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerElementLightMultiplier(double amount) {
        increaseCPlayerAttribute(ELEMENT_LIGHT_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementLightMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_LIGHT_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementLightMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_LIGHT_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerElementDarknessBase() {
        return getCPlayerAttribute(ELEMENT_DARKNESS_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerElementDarknessBase(double amount) {
        increaseCPlayerAttribute(ELEMENT_DARKNESS_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementDarknessBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_DARKNESS_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementDarknessBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_DARKNESS_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerElementDarknessMultiplier() {
        return getCPlayerAttribute(ELEMENT_DARKNESS_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerElementDarknessMultiplier(double amount) {
        increaseCPlayerAttribute(ELEMENT_DARKNESS_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementDarknessMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_DARKNESS_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementDarknessMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_DARKNESS_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerElementMetalBase() {
        return getCPlayerAttribute(ELEMENT_METAL_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerElementMetalBase(double amount) {
        increaseCPlayerAttribute(ELEMENT_METAL_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementMetalBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_METAL_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementMetalBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_METAL_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerElementMetalMultiplier() {
        return getCPlayerAttribute(ELEMENT_METAL_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerElementMetalMultiplier(double amount) {
        increaseCPlayerAttribute(ELEMENT_METAL_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementMetalMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_METAL_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementMetalMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_METAL_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerElementWoodBase() {
        return getCPlayerAttribute(ELEMENT_WOOD_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerElementWoodBase(double amount) {
        increaseCPlayerAttribute(ELEMENT_WOOD_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementWoodBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_WOOD_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementWoodBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_WOOD_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerElementWoodMultiplier() {
        return getCPlayerAttribute(ELEMENT_WOOD_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerElementWoodMultiplier(double amount) {
        increaseCPlayerAttribute(ELEMENT_WOOD_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementWoodMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_WOOD_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementWoodMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_WOOD_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerElementEarthBase() {
        return getCPlayerAttribute(ELEMENT_EARTH_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerElementEarthBase(double amount) {
        increaseCPlayerAttribute(ELEMENT_EARTH_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementEarthBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_EARTH_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementEarthBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_EARTH_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerElementEarthMultiplier() {
        return getCPlayerAttribute(ELEMENT_EARTH_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerElementEarthMultiplier(double amount) {
        increaseCPlayerAttribute(ELEMENT_EARTH_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementEarthMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_EARTH_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementEarthMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_EARTH_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerElementFireBase() {
        return getCPlayerAttribute(ELEMENT_FIRE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerElementFireBase(double amount) {
        increaseCPlayerAttribute(ELEMENT_FIRE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementFireBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_FIRE_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementFireBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_FIRE_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerElementFireMultiplier() {
        return getCPlayerAttribute(ELEMENT_FIRE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerElementFireMultiplier(double amount) {
        increaseCPlayerAttribute(ELEMENT_FIRE_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementFireMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_FIRE_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementFireMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_FIRE_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerElementAirBase() {
        return getCPlayerAttribute(ELEMENT_AIR_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerElementAirBase(double amount) {
        increaseCPlayerAttribute(ELEMENT_AIR_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementAirBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_AIR_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementAirBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_AIR_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerElementAirMultiplier() {
        return getCPlayerAttribute(ELEMENT_AIR_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerElementAirMultiplier(double amount) {
        increaseCPlayerAttribute(ELEMENT_AIR_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementAirMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_AIR_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementAirMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_AIR_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerElementWaterBase() {
        return getCPlayerAttribute(ELEMENT_WATER_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerElementWaterBase(double amount) {
        increaseCPlayerAttribute(ELEMENT_WATER_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementWaterBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_WATER_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementWaterBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_WATER_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerElementWaterMultiplier() {
        return getCPlayerAttribute(ELEMENT_WATER_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerElementWaterMultiplier(double amount) {
        increaseCPlayerAttribute(ELEMENT_WATER_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementWaterMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_WATER_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementWaterMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_WATER_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerElementLightResistBase() {
        return getCPlayerAttribute(ELEMENT_LIGHT_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerElementLightResistBase(double amount) {
        increaseCPlayerAttribute(ELEMENT_LIGHT_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementLightResistBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_LIGHT_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementLightResistBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_LIGHT_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerElementLightResistMultiplier() {
        return getCPlayerAttribute(ELEMENT_LIGHT_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerElementLightResistMultiplier(double amount) {
        increaseCPlayerAttribute(ELEMENT_LIGHT_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementLightResistMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_LIGHT_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementLightResistMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_LIGHT_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerElementDarknessResistBase() {
        return getCPlayerAttribute(ELEMENT_DARKNESS_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerElementDarknessResistBase(double amount) {
        increaseCPlayerAttribute(ELEMENT_DARKNESS_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementDarknessResistBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_DARKNESS_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementDarknessResistBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_DARKNESS_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerElementDarknessResistMultiplier() {
        return getCPlayerAttribute(ELEMENT_DARKNESS_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerElementDarknessResistMultiplier(double amount) {
        increaseCPlayerAttribute(ELEMENT_DARKNESS_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementDarknessResistMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_DARKNESS_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementDarknessResistMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_DARKNESS_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerElementMetalResistBase() {
        return getCPlayerAttribute(ELEMENT_METAL_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerElementMetalResistBase(double amount) {
        increaseCPlayerAttribute(ELEMENT_METAL_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementMetalResistBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_METAL_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementMetalResistBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_METAL_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerElementMetalResistMultiplier() {
        return getCPlayerAttribute(ELEMENT_METAL_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerElementMetalResistMultiplier(double amount) {
        increaseCPlayerAttribute(ELEMENT_METAL_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementMetalResistMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_METAL_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementMetalResistMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_METAL_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerElementWoodResistBase() {
        return getCPlayerAttribute(ELEMENT_WOOD_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerElementWoodResistBase(double amount) {
        increaseCPlayerAttribute(ELEMENT_WOOD_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementWoodResistBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_WOOD_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementWoodResistBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_WOOD_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerElementWoodResistMultiplier() {
        return getCPlayerAttribute(ELEMENT_WOOD_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerElementWoodResistMultiplier(double amount) {
        increaseCPlayerAttribute(ELEMENT_WOOD_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementWoodResistMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_WOOD_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementWoodResistMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_WOOD_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerElementEarthResistBase() {
        return getCPlayerAttribute(ELEMENT_EARTH_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerElementEarthResistBase(double amount) {
        increaseCPlayerAttribute(ELEMENT_EARTH_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementEarthResistBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_EARTH_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementEarthResistBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_EARTH_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerElementEarthResistMultiplier() {
        return getCPlayerAttribute(ELEMENT_EARTH_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerElementEarthResistMultiplier(double amount) {
        increaseCPlayerAttribute(ELEMENT_EARTH_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementEarthResistMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_EARTH_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementEarthResistMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_EARTH_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerElementFireResistBase() {
        return getCPlayerAttribute(ELEMENT_FIRE_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerElementFireResistBase(double amount) {
        increaseCPlayerAttribute(ELEMENT_FIRE_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementFireResistBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_FIRE_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementFireResistBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_FIRE_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerElementFireResistMultiplier() {
        return getCPlayerAttribute(ELEMENT_FIRE_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerElementFireResistMultiplier(double amount) {
        increaseCPlayerAttribute(ELEMENT_FIRE_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementFireResistMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_FIRE_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementFireResistMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_FIRE_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerElementAirResistBase() {
        return getCPlayerAttribute(ELEMENT_AIR_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerElementAirResistBase(double amount) {
        increaseCPlayerAttribute(ELEMENT_AIR_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementAirResistBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_AIR_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementAirResistBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_AIR_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerElementAirResistMultiplier() {
        return getCPlayerAttribute(ELEMENT_AIR_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerElementAirResistMultiplier(double amount) {
        increaseCPlayerAttribute(ELEMENT_AIR_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementAirResistMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_AIR_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementAirResistMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_AIR_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerElementWaterResistBase() {
        return getCPlayerAttribute(ELEMENT_WATER_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerElementWaterResistBase(double amount) {
        increaseCPlayerAttribute(ELEMENT_WATER_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementWaterResistBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_WATER_RESIST_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementWaterResistBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_WATER_RESIST_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerElementWaterResistMultiplier() {
        return getCPlayerAttribute(ELEMENT_WATER_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerElementWaterResistMultiplier(double amount) {
        increaseCPlayerAttribute(ELEMENT_WATER_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryElementWaterResistMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_WATER_RESIST_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryElementWaterResistMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + ELEMENT_WATER_RESIST_MULTIPLIER + ALL_SKILLS, amount);
    }

    //====================================================================
    public static double getCPlayerBeingAttackedBase() {
        return getCPlayerAttribute(BEING_ATTACKED_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerBeingAttackedBase(double amount) {
        increaseCPlayerAttribute(BEING_ATTACKED_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryBeingAttackedBase() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + BEING_ATTACKED_BASE + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryBeingAttackedBase(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + BEING_ATTACKED_BASE + ALL_SKILLS, amount);
    }
    public static double getCPlayerBeingAttackedMultiplier() {
        return getCPlayerAttribute(BEING_ATTACKED_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerBeingAttackedMultiplier(double amount) {
        increaseCPlayerAttribute(BEING_ATTACKED_MULTIPLIER + ALL_SKILLS, amount);
    }
    public static double getCPlayerTemporaryBeingAttackedMultiplier() {
        return getCPlayerAttribute(TEMPORARY_PREFIX + BEING_ATTACKED_MULTIPLIER + ALL_SKILLS);
    }
    public static void increaseCPlayerTemporaryBeingAttackedMultiplier(double amount) {
        increaseCPlayerAttribute(TEMPORARY_PREFIX + BEING_ATTACKED_MULTIPLIER + ALL_SKILLS, amount);
    }
}

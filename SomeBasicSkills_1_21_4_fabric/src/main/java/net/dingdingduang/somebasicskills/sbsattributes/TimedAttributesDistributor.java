package net.dingdingduang.somebasicskills.sbsattributes;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.event.SBSTickEventAttributeMethods;
import net.dingdingduang.somebasicskills.networking.NetworkingFetchMsgMethods;
import net.dingdingduang.somebasicskills.sbsattributes.statusquery.AttributeServerPlayerStatusQueryMethods;
import net.dingdingduang.somebasicskills.util.MethodAction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.entity.attribute.EntityAttributeModifier;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerValue2BaseMultiplierMap;

//TODO: check duplicate UUID, or use mc default registry then take their UUID?
//ONLY USE THIS ON SERVER SIDE
public class TimedAttributesDistributor {

    public static final Identifier SBS_UUID_TIMED_ATTACK_DAMAGE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_attack_damage_base");
    public static final Identifier SBS_UUID_TIMED_ATTACK_DAMAGE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_attack_damage_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ATTACK_DAMAGE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_attack_damage_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ATTACK_KNOCKBACK_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_attack_knockback_base");
    public static final Identifier SBS_UUID_TIMED_ATTACK_KNOCKBACK_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_attack_knockback_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ATTACK_KNOCKBACK_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_attack_knockback_multiply_total");

    public static final Identifier SBS_UUID_TIMED_KNOCKBACK_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_knockback_resist_base");
    public static final Identifier SBS_UUID_TIMED_KNOCKBACK_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_knockback_resist_multiply_base");
    public static final Identifier SBS_UUID_TIMED_KNOCKBACK_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_knockback_resist_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ASPD_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_aspd_base");
    public static final Identifier SBS_UUID_TIMED_ASPD_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_aspd_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ASPD_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_aspd_multiply_total");

    public static final Identifier SBS_UUID_TIMED_MOVS_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_movs_base");
    public static final Identifier SBS_UUID_TIMED_MOVS_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_movs_multiply_base");
    public static final Identifier SBS_UUID_TIMED_MOVS_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_movs_multiply_total");

    public static final Identifier SBS_UUID_TIMED_MAX_HP_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_max_hp_base");
    public static final Identifier SBS_UUID_TIMED_MAX_HP_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_max_hp_multiply_base");
    public static final Identifier SBS_UUID_TIMED_MAX_HP_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_max_hp_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ARMOR_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_armor_base");
    public static final Identifier SBS_UUID_TIMED_ARMOR_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_armor_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ARMOR_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_armor_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ARMOR_TOUGHNESS_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_armor_toughness_base");
    public static final Identifier SBS_UUID_TIMED_ARMOR_TOUGHNESS_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_armor_toughness_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ARMOR_TOUGHNESS_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_armor_toughness_multiply_total");

    public static final Identifier SBS_UUID_TIMED_LUCK_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_luck_base");
    public static final Identifier SBS_UUID_TIMED_LUCK_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_luck_multiply_base");
    public static final Identifier SBS_UUID_TIMED_LUCK_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_luck_multiply_total");

    public static final Identifier SBS_UUID_TIMED_JUMP_STRENGTH_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_jump_strength_base");
    public static final Identifier SBS_UUID_TIMED_JUMP_STRENGTH_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_jump_strength_multiply_base");
    public static final Identifier SBS_UUID_TIMED_JUMP_STRENGTH_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_jump_strength_multiply_total");

    public static final Identifier SBS_UUID_TIMED_MINING_EFFICIENCY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_mining_efficiency_base");
    public static final Identifier SBS_UUID_TIMED_MINING_EFFICIENCY_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_mining_efficiency_multiply_base");
    public static final Identifier SBS_UUID_TIMED_MINING_EFFICIENCY_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_mining_efficiency_multiply_total");

    public static final Identifier SBS_UUID_TIMED_MAX_MP_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_max_mp_base");
    public static final Identifier SBS_UUID_TIMED_MAX_MP_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_max_mp_multiply_base");
    public static final Identifier SBS_UUID_TIMED_MAX_MP_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_max_mp_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ARMOR_PIERCE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_armor_pierce_base");
    public static final Identifier SBS_UUID_TIMED_ARMOR_PIERCE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_armor_pierce_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ARMOR_PIERCE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_armor_pierce_multiply_total");

    public static final Identifier SBS_UUID_TIMED_PROTECTION_PIERCE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_protection_pierce_base");
    public static final Identifier SBS_UUID_TIMED_PROTECTION_PIERCE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_protection_pierce_multiply_base");
    public static final Identifier SBS_UUID_TIMED_PROTECTION_PIERCE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_protection_pierce_multiply_total");

    public static final Identifier SBS_UUID_TIMED_COOLDOWN_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_cooldown_base");
    public static final Identifier SBS_UUID_TIMED_COOLDOWN_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_cooldown_multiply_base");
    public static final Identifier SBS_UUID_TIMED_COOLDOWN_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_cooldown_multiply_total");

    public static final Identifier SBS_UUID_TIMED_CHANNELING_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_channeling_base");
    public static final Identifier SBS_UUID_TIMED_CHANNELING_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_channeling_multiply_base");
    public static final Identifier SBS_UUID_TIMED_CHANNELING_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_channeling_multiply_total");

    public static final Identifier SBS_UUID_TIMED_UNINTERRUPTIBLE_CHANCE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_uninterruptible_chance_base");
    public static final Identifier SBS_UUID_TIMED_UNINTERRUPTIBLE_CHANCE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_uninterruptible_chance_multiply_base");
    public static final Identifier SBS_UUID_TIMED_UNINTERRUPTIBLE_CHANCE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_uninterruptible_chance_multiply_total");

    public static final Identifier SBS_UUID_TIMED_INDEPENDENCE_DMG_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_independence_dmg_base");
    public static final Identifier SBS_UUID_TIMED_INDEPENDENCE_DMG_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_independence_dmg_multiply_base");
    public static final Identifier SBS_UUID_TIMED_INDEPENDENCE_DMG_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_independence_dmg_multiply_total");

    public static final Identifier SBS_UUID_TIMED_PHYSIC_CRIT_CHANCE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_physic_crit_chance_base");
    public static final Identifier SBS_UUID_TIMED_PHYSIC_CRIT_CHANCE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_physic_crit_chance_multiply_base");
    public static final Identifier SBS_UUID_TIMED_PHYSIC_CRIT_CHANCE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_physic_crit_chance_multiply_total");

    public static final Identifier SBS_UUID_TIMED_PHYSIC_CRIT_DAMAGE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_physic_crit_damage_base");
    public static final Identifier SBS_UUID_TIMED_PHYSIC_CRIT_DAMAGE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_physic_crit_damage_multiply_base");
    public static final Identifier SBS_UUID_TIMED_PHYSIC_CRIT_DAMAGE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_physic_crit_damage_multiply_total");

    public static final Identifier SBS_UUID_TIMED_MAGIC_DMG_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_magic_dmg_base");
    public static final Identifier SBS_UUID_TIMED_MAGIC_DMG_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_magic_dmg_multiply_base");
    public static final Identifier SBS_UUID_TIMED_MAGIC_DMG_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_magic_dmg_multiply_total");

    public static final Identifier SBS_UUID_TIMED_MAGIC_CRIT_CHANCE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_magic_crit_chance_base");
    public static final Identifier SBS_UUID_TIMED_MAGIC_CRIT_CHANCE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_magic_crit_chance_multiply_base");
    public static final Identifier SBS_UUID_TIMED_MAGIC_CRIT_CHANCE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_magic_crit_chance_multiply_total");

    public static final Identifier SBS_UUID_TIMED_MAGIC_CRIT_DAMAGE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_magic_crit_damage_base");
    public static final Identifier SBS_UUID_TIMED_MAGIC_CRIT_DAMAGE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_magic_crit_damage_multiply_base");
    public static final Identifier SBS_UUID_TIMED_MAGIC_CRIT_DAMAGE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_magic_crit_damage_multiply_total");

    public static final Identifier SBS_UUID_TIMED_BACKSTAB_DMG_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_backstab_dmg_base");
    public static final Identifier SBS_UUID_TIMED_BACKSTAB_DMG_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_backstab_dmg_multiply_base");
    public static final Identifier SBS_UUID_TIMED_BACKSTAB_DMG_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_backstab_dmg_multiply_total");

    public static final Identifier SBS_UUID_TIMED_PHYSIC_BACKSTAB_CRIT_CHANCE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_physic_backstab_crit_chance_base");
    public static final Identifier SBS_UUID_TIMED_PHYSIC_BACKSTAB_CRIT_CHANCE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_physic_backstab_crit_chance_multiply_base");
    public static final Identifier SBS_UUID_TIMED_PHYSIC_BACKSTAB_CRIT_CHANCE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_physic_backstab_crit_chance_multiply_total");

    public static final Identifier SBS_UUID_TIMED_PHYSIC_BACKSTAB_CRIT_DAMAGE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_physic_backstab_crit_damage_base");
    public static final Identifier SBS_UUID_TIMED_PHYSIC_BACKSTAB_CRIT_DAMAGE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_physic_backstab_crit_damage_multiply_base");
    public static final Identifier SBS_UUID_TIMED_PHYSIC_BACKSTAB_CRIT_DAMAGE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_physic_backstab_crit_damage_multiply_total");

    public static final Identifier SBS_UUID_TIMED_MAGIC_BACKSTAB_CRIT_CHANCE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_magic_backstab_crit_chance_base");
    public static final Identifier SBS_UUID_TIMED_MAGIC_BACKSTAB_CRIT_CHANCE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_magic_backstab_crit_chance_multiply_base");
    public static final Identifier SBS_UUID_TIMED_MAGIC_BACKSTAB_CRIT_CHANCE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_magic_backstab_crit_chance_multiply_total");

    public static final Identifier SBS_UUID_TIMED_MAGIC_BACKSTAB_CRIT_DAMAGE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_magic_backstab_crit_damage_base");
    public static final Identifier SBS_UUID_TIMED_MAGIC_BACKSTAB_CRIT_DAMAGE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_magic_backstab_crit_damage_multiply_base");
    public static final Identifier SBS_UUID_TIMED_MAGIC_BACKSTAB_CRIT_DAMAGE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_magic_backstab_crit_damage_multiply_total");

    public static final Identifier SBS_UUID_TIMED_SKILL_DMG_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_skill_dmg_base");
    public static final Identifier SBS_UUID_TIMED_SKILL_DMG_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_skill_dmg_multiply_base");
    public static final Identifier SBS_UUID_TIMED_SKILL_DMG_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_skill_dmg_multiply_total");

    public static final Identifier SBS_UUID_TIMED_OVERALL_DMG_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_overall_dmg_base");
    public static final Identifier SBS_UUID_TIMED_OVERALL_DMG_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_overall_dmg_multiply_base");
    public static final Identifier SBS_UUID_TIMED_OVERALL_DMG_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_overall_dmg_multiply_total");

    public static final Identifier SBS_UUID_TIMED_EXTRA_DAMAGE_RECEIVED_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_extra_damage_received_base");
    public static final Identifier SBS_UUID_TIMED_EXTRA_DAMAGE_RECEIVED_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_extra_damage_received_multiply_base");
    public static final Identifier SBS_UUID_TIMED_EXTRA_DAMAGE_RECEIVED_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_extra_damage_received_multiply_total");

    public static final Identifier SBS_UUID_TIMED_EFFECT_AREA_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_effect_area_base");
    public static final Identifier SBS_UUID_TIMED_EFFECT_AREA_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_effect_area_multiply_base");
    public static final Identifier SBS_UUID_TIMED_EFFECT_AREA_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_effect_area_multiply_total");

    public static final Identifier SBS_UUID_TIMED_DURATION_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_duration_base");
    public static final Identifier SBS_UUID_TIMED_DURATION_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_duration_multiply_base");
    public static final Identifier SBS_UUID_TIMED_DURATION_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_duration_multiply_total");

    public static final Identifier SBS_UUID_TIMED_DISTANCE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_distance_base");
    public static final Identifier SBS_UUID_TIMED_DISTANCE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_distance_multiply_base");
    public static final Identifier SBS_UUID_TIMED_DISTANCE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_distance_multiply_total");

    public static final Identifier SBS_UUID_TIMED_STATIONARY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_stationary_base");
    public static final Identifier SBS_UUID_TIMED_STATIONARY_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_stationary_multiply_base");
    public static final Identifier SBS_UUID_TIMED_STATIONARY_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_stationary_multiply_total");

    public static final Identifier SBS_UUID_TIMED_BENEFICIAL_STATUS_INVINCIBILITY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_beneficial_status_invincibility_base");
    public static final Identifier SBS_UUID_TIMED_BENEFICIAL_STATUS_INVINCIBILITY_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_beneficial_status_invincibility_multiply_base");
    public static final Identifier SBS_UUID_TIMED_BENEFICIAL_STATUS_INVINCIBILITY_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_beneficial_status_invincibility_multiply_total");

    public static final Identifier SBS_UUID_TIMED_BENEFICIAL_STATUS_SUPER_ARMOR_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_beneficial_status_super_armor_base");
    public static final Identifier SBS_UUID_TIMED_BENEFICIAL_STATUS_SUPER_ARMOR_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_beneficial_status_super_armor_multiply_base");
    public static final Identifier SBS_UUID_TIMED_BENEFICIAL_STATUS_SUPER_ARMOR_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_beneficial_status_super_armor_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_BLEEDING_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_bleeding_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_BLEEDING_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_bleeding_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_BLEEDING_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_bleeding_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_SHOCK_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_shock_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_SHOCK_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_shock_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_SHOCK_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_shock_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_FREEZE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_freeze_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_FREEZE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_freeze_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_FREEZE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_freeze_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_BURN_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_burn_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_BURN_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_burn_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_BURN_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_burn_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_CURSED_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_cursed_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_CURSED_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_cursed_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_CURSED_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_cursed_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_DISARM_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_disarm_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_DISARM_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_disarm_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_DISARM_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_disarm_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_IMMOBILIZATION_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_immobilization_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_IMMOBILIZATION_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_immobilization_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_IMMOBILIZATION_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_immobilization_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_SLEEP_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_sleep_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_SLEEP_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_sleep_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_SLEEP_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_sleep_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_CONFUSION_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_confusion_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_CONFUSION_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_confusion_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_CONFUSION_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_confusion_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_STUN_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_stun_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_STUN_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_stun_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_STUN_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_stun_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_SILENCE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_silence_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_SILENCE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_silence_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_SILENCE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_silence_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_ALL_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_all_resist_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_ALL_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_all_resist_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_ALL_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_all_resist_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_BLEEDING_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_bleeding_resist_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_BLEEDING_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_bleeding_resist_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_BLEEDING_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_bleeding_resist_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_SHOCK_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_shock_resist_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_SHOCK_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_shock_resist_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_SHOCK_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_shock_resist_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_FREEZE_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_freeze_resist_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_FREEZE_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_freeze_resist_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_FREEZE_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_freeze_resist_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_BURN_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_burn_resist_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_BURN_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_burn_resist_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_BURN_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_burn_resist_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_CURSED_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_cursed_resist_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_CURSED_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_cursed_resist_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_CURSED_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_cursed_resist_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_DISARM_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_disarm_resist_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_DISARM_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_disarm_resist_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_DISARM_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_disarm_resist_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_STUN_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_stun_resist_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_STUN_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_stun_resist_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_STUN_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_stun_resist_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_SILENCE_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_silence_resist_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_SILENCE_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_silence_resist_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ABNORMAL_STATUS_SILENCE_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_abnormal_status_silence_resist_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ALL_DMG_REDUCTION_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_all_dmg_reduction_base");
    public static final Identifier SBS_UUID_TIMED_ALL_DMG_REDUCTION_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_all_dmg_reduction_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ALL_DMG_REDUCTION_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_all_dmg_reduction_multiply_total");

    public static final Identifier SBS_UUID_TIMED_PHYSIC_DMG_REDUCTION_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_physic_dmg_reduction_base");
    public static final Identifier SBS_UUID_TIMED_PHYSIC_DMG_REDUCTION_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_physic_dmg_reduction_multiply_base");
    public static final Identifier SBS_UUID_TIMED_PHYSIC_DMG_REDUCTION_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_physic_dmg_reduction_multiply_total");

    public static final Identifier SBS_UUID_TIMED_MAGIC_DMG_REDUCTION_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_magic_dmg_reduction_base");
    public static final Identifier SBS_UUID_TIMED_MAGIC_DMG_REDUCTION_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_magic_dmg_reduction_multiply_base");
    public static final Identifier SBS_UUID_TIMED_MAGIC_DMG_REDUCTION_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_magic_dmg_reduction_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ELEMENT_LIGHT_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_light_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_LIGHT_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_light_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_LIGHT_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_light_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ELEMENT_DARKNESS_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_darkness_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_DARKNESS_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_darkness_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_DARKNESS_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_darkness_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ELEMENT_METAL_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_metal_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_METAL_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_metal_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_METAL_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_metal_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ELEMENT_WOOD_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_wood_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_WOOD_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_wood_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_WOOD_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_wood_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ELEMENT_EARTH_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_earth_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_EARTH_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_earth_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_EARTH_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_earth_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ELEMENT_FIRE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_fire_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_FIRE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_fire_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_FIRE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_fire_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ELEMENT_AIR_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_air_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_AIR_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_air_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_AIR_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_air_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ELEMENT_WATER_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_water_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_WATER_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_water_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_WATER_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_water_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ELEMENT_LIGHT_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_light_resist_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_LIGHT_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_light_resist_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_LIGHT_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_light_resist_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ELEMENT_DARKNESS_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_darkness_resist_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_DARKNESS_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_darkness_resist_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_DARKNESS_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_darkness_resist_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ELEMENT_METAL_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_metal_resist_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_METAL_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_metal_resist_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_METAL_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_metal_resist_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ELEMENT_WOOD_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_wood_resist_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_WOOD_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_wood_resist_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_WOOD_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_wood_resist_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ELEMENT_EARTH_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_earth_resist_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_EARTH_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_earth_resist_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_EARTH_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_earth_resist_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ELEMENT_FIRE_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_fire_resist_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_FIRE_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_fire_resist_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_FIRE_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_fire_resist_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ELEMENT_AIR_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_air_resist_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_AIR_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_air_resist_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_AIR_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_air_resist_multiply_total");

    public static final Identifier SBS_UUID_TIMED_ELEMENT_WATER_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_water_resist_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_WATER_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_water_resist_multiply_base");
    public static final Identifier SBS_UUID_TIMED_ELEMENT_WATER_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_element_water_resist_multiply_total");

    public static final Identifier SBS_UUID_TIMED_BEING_ATTACKED_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_being_attacked_base");
    public static final Identifier SBS_UUID_TIMED_BEING_ATTACKED_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_being_attacked_multiply_base");
    public static final Identifier SBS_UUID_TIMED_BEING_ATTACKED_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_timed_being_attacked_multiply_total");

    //TODO: check if living entity is removed from world, if so remove them from attr map
//    public static void removeAttrFromEntity(LivingEntity entity, Attribute attrType, EntityAttributeModifier modifier) {
//        AttributeInstance tempAttrInstance = entity.getAttribute(attrType);
//        if (tempAttrInstance != null) {
//            if (tempAttrInstance.hasModifier(modifier)) {
//                tempAttrInstance.removeModifier(modifier);
//            }
//        }
//    }



    public static EntityAttributeModifier.Operation ConvertIntOp2AttrOp(int operator) {
        EntityAttributeModifier.Operation op;
        switch (operator) {
            case 0:
                op = EntityAttributeModifier.Operation.ADD_VALUE;
                break;
            case 1:
                op = EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE;
                break;
            case 2:
                op = EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL;
                break;
            default:
                op = EntityAttributeModifier.Operation.ADD_VALUE;
                break;
        }
        return op;
    }

    public static void applyTimedAttrToEntity(LivingEntity entity, Identifier attrUUID, String attrName, RegistryEntry<EntityAttribute> attrType, int totalTimeTicks, double amount, EntityAttributeModifier.Operation op, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        if (totalTimeTicks <= 0) { return; }

        //effect icon missing, do i have to register?
//        new MobEffectInstance(..., 100, 0, false, false);

        double totalAmount;
        String playerStatusName = attrName + AttributeServerPlayerStatusQueryMethods.ALL_SKILLS;

//        AttributePrimitiveOnly attrPrimitive = new AttributePrimitiveOnly(amount, totalTimeTicks, op.id()(), attrUUID, SkillID, action, finalAction);
        AttributePrimitiveOnly attrPrimitive = new AttributePrimitiveOnly(amount, totalTimeTicks, op.getId(), attrUUID, playerStatusName, SkillID);
        attrPrimitive.setAttributeMethodAction(action);
        attrPrimitive.setAttributeFinalMethodAction(finalAction);
        if (allowDuplicate) {
            if (entity instanceof ServerPlayerEntity sp1) {
                //ASSUME getSPlayerValue2BaseMultiplierMap() is not null simpy because it is initialized and should never be null unless server is offline
                HashMap<String, Double> tempPlayerStatusMap = getSPlayerValue2BaseMultiplierMap().get(sp1);
                //add status value based on given attr i.e. HP ATK SPEED
                if (tempPlayerStatusMap.containsKey(playerStatusName)) {
                    totalAmount = amount+tempPlayerStatusMap.get(playerStatusName);
                    tempPlayerStatusMap.put(playerStatusName, totalAmount);
                    NetworkingFetchMsgMethods.FetchPlayerStatusToClientSide(sp1, playerStatusName, totalAmount);
                }
            }
            SBSTickEventAttributeMethods.setAttributeDupServerTimer(entity, attrUUID, playerStatusName, attrType, attrPrimitive, op, SkillID);
        }
        else {
            double previousTotalValue = 0.0;
            if (entity instanceof ServerPlayerEntity sp1) {
                //ASSUME getSPlayerValue2BaseMultiplierMap() is not null simpy because it is initialized and should never be null unless server is offline
                HashMap<String, Double> tempPlayerStatusMap = getSPlayerValue2BaseMultiplierMap().get(sp1);
                //add status value based on given attr i.e. HP ATK SPEED
                if (tempPlayerStatusMap.containsKey(playerStatusName)) {
                    previousTotalValue = tempPlayerStatusMap.get(playerStatusName);
                }
            }
            double totalBonus = SBSTickEventAttributeMethods.setUniqueAttributeServerTimer(entity, attrUUID, playerStatusName, attrType, attrPrimitive, op, SkillID);
            if (entity instanceof ServerPlayerEntity sp1) {
                HashMap<String, Double> tempPlayerStatusMap = getSPlayerValue2BaseMultiplierMap().get(entity);
                totalAmount = totalBonus-previousTotalValue+tempPlayerStatusMap.get(playerStatusName);
                tempPlayerStatusMap.put(playerStatusName, totalAmount);
                NetworkingFetchMsgMethods.FetchPlayerStatusToClientSide(sp1, playerStatusName, totalAmount);
            }
        }
    }


    //not recommend using multiply total base+multiply is game breaking
    public static void applyTimedAttackDMGToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ATTACK_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ATTACK_DAMAGE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ATTACK_DAMAGE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ATTACK_DAMAGE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ATTACK_DAMAGE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ATTACK_DAMAGE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ATTACK_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ATTACK_DAMAGE_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, EntityAttributes.ATTACK_DAMAGE, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    public static void applyTimedAttackKnockbackToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ATTACK_KNOCKBACK_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.KNOCKBACK_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ATTACK_KNOCKBACK_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.KNOCKBACK_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ATTACK_KNOCKBACK_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.KNOCKBACK_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ATTACK_KNOCKBACK_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.KNOCKBACK_RESIST_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, EntityAttributes.ATTACK_KNOCKBACK, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }


    public static void applyTimedKnockbackResistToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_KNOCKBACK_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ATTACK_KNOCKBACK_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_KNOCKBACK_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ATTACK_KNOCKBACK_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_KNOCKBACK_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ATTACK_KNOCKBACK_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_KNOCKBACK_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ATTACK_KNOCKBACK_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, EntityAttributes.KNOCKBACK_RESISTANCE, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    public static void applyTimedAttackSpeedToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ASPD_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ASPD_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ASPD_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ASPD_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ASPD_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ASPD_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ASPD_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ASPD_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, EntityAttributes.ATTACK_SPEED, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    public static void applyTimedMovementSpeedToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_MOVS_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MOVS_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_MOVS_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MOVS_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_MOVS_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MOVS_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_MOVS_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MOVS_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, EntityAttributes.MOVEMENT_SPEED, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    public static void applyTimedMaxHealthToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_MAX_HP_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAX_HP_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_MAX_HP_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAX_HP_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_MAX_HP_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAX_HP_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_MAX_HP_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAX_HP_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, EntityAttributes.MAX_HEALTH, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    public static void applyTimedArmorToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ARMOR_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ARMOR_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ARMOR_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ARMOR_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ARMOR_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ARMOR_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ARMOR_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ARMOR_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, EntityAttributes.ARMOR, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    public static void applyTimedArmorToughnessToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ARMOR_TOUGHNESS_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ARMOR_TOUGHNESS_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ARMOR_TOUGHNESS_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ARMOR_TOUGHNESS_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ARMOR_TOUGHNESS_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ARMOR_TOUGHNESS_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ARMOR_TOUGHNESS_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ARMOR_TOUGHNESS_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, EntityAttributes.ARMOR_TOUGHNESS, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    public static void applyTimedLuckToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_LUCK_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.LUCK_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_LUCK_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.LUCK_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_LUCK_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.LUCK_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_LUCK_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.LUCK_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, EntityAttributes.LUCK, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedJumpStrengthToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_JUMP_STRENGTH_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.JUMP_STRENGTH_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_JUMP_STRENGTH_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.JUMP_STRENGTH_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_JUMP_STRENGTH_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.JUMP_STRENGTH_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_JUMP_STRENGTH_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.JUMP_STRENGTH_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, EntityAttributes.JUMP_STRENGTH, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedMiningEfficiencyToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_MINING_EFFICIENCY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MINING_EFFICIENCY_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_MINING_EFFICIENCY_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MINING_EFFICIENCY_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_MINING_EFFICIENCY_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MINING_EFFICIENCY_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_MINING_EFFICIENCY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MINING_EFFICIENCY_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, EntityAttributes.MINING_EFFICIENCY, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedMaxMpToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_MAX_MP_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAX_MP_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_MAX_MP_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAX_MP_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_MAX_MP_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAX_MP_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_MAX_MP_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAX_MP_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_MAX_MP, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedArmorPierceToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ARMOR_PIERCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ARMOR_PIERCE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ARMOR_PIERCE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ARMOR_PIERCE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ARMOR_PIERCE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ARMOR_PIERCE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ARMOR_PIERCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ARMOR_PIERCE_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ARMOR_PIERCE, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedProtectionPierceToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_PROTECTION_PIERCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PROTECTION_PIERCE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_PROTECTION_PIERCE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PROTECTION_PIERCE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_PROTECTION_PIERCE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PROTECTION_PIERCE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_PROTECTION_PIERCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PROTECTION_PIERCE_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_PROTECTION_PIERCE, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedCooldownToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_COOLDOWN_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.COOLDOWN_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_COOLDOWN_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.COOLDOWN_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_COOLDOWN_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.COOLDOWN_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_COOLDOWN_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.COOLDOWN_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_COOLDOWN, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedChannelingToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_CHANNELING_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.CHANNELING_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_CHANNELING_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.CHANNELING_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_CHANNELING_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.CHANNELING_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_CHANNELING_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.CHANNELING_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_CHANNELING, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedUninterruptibleChanceToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_UNINTERRUPTIBLE_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.UNINTERRUPTIBLE_CHANCE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_UNINTERRUPTIBLE_CHANCE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.UNINTERRUPTIBLE_CHANCE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_UNINTERRUPTIBLE_CHANCE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.UNINTERRUPTIBLE_CHANCE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_UNINTERRUPTIBLE_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.UNINTERRUPTIBLE_CHANCE_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_UNINTERRUPTIBLE_CHANCE, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedIndependenceDmgToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_INDEPENDENCE_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.INDEPENDENCE_DMG_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_INDEPENDENCE_DMG_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.INDEPENDENCE_DMG_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_INDEPENDENCE_DMG_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.INDEPENDENCE_DMG_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_INDEPENDENCE_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.INDEPENDENCE_DMG_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_INDEPENDENCE_DMG, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedPhysicCritChanceToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_PHYSIC_CRIT_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_CRIT_CHANCE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_PHYSIC_CRIT_CHANCE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_CRIT_CHANCE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_PHYSIC_CRIT_CHANCE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_CRIT_CHANCE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_PHYSIC_CRIT_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_CRIT_CHANCE_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_CRIT_CHANCE, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedPhysicCritDamageToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_PHYSIC_CRIT_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_CRIT_DAMAGE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_PHYSIC_CRIT_DAMAGE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_CRIT_DAMAGE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_PHYSIC_CRIT_DAMAGE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_CRIT_DAMAGE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_PHYSIC_CRIT_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_CRIT_DAMAGE_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_CRIT_DAMAGE, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedMagicDmgToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_MAGIC_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_DMG_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_MAGIC_DMG_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_DMG_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_MAGIC_DMG_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_DMG_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_MAGIC_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_DMG_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_MAGIC_DMG, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedMagicCritChanceToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_MAGIC_CRIT_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_CRIT_CHANCE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_MAGIC_CRIT_CHANCE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_CRIT_CHANCE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_MAGIC_CRIT_CHANCE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_CRIT_CHANCE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_MAGIC_CRIT_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_CRIT_CHANCE_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_MAGIC_CRIT_CHANCE, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedMagicCritDamageToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_MAGIC_CRIT_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_CRIT_DAMAGE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_MAGIC_CRIT_DAMAGE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_CRIT_DAMAGE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_MAGIC_CRIT_DAMAGE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_CRIT_DAMAGE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_MAGIC_CRIT_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_CRIT_DAMAGE_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_MAGIC_CRIT_DAMAGE, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedBackstabDmgToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_BACKSTAB_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BACKSTAB_DMG_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_BACKSTAB_DMG_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BACKSTAB_DMG_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_BACKSTAB_DMG_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BACKSTAB_DMG_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_BACKSTAB_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BACKSTAB_DMG_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_BACKSTAB_DMG, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedPhysicBackstabCritChanceToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_PHYSIC_BACKSTAB_CRIT_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_BACKSTAB_CRIT_CHANCE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_PHYSIC_BACKSTAB_CRIT_CHANCE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_PHYSIC_BACKSTAB_CRIT_CHANCE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_PHYSIC_BACKSTAB_CRIT_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_BACKSTAB_CRIT_CHANCE_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_BACKSTAB_CRIT_CHANCE, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedPhysicBackstabCritDamageToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_PHYSIC_BACKSTAB_CRIT_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_BACKSTAB_CRIT_DAMAGE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_PHYSIC_BACKSTAB_CRIT_DAMAGE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_PHYSIC_BACKSTAB_CRIT_DAMAGE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_PHYSIC_BACKSTAB_CRIT_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_BACKSTAB_CRIT_DAMAGE_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_BACKSTAB_CRIT_DAMAGE, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedMagicBackstabCritChanceToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_MAGIC_BACKSTAB_CRIT_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_BACKSTAB_CRIT_CHANCE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_MAGIC_BACKSTAB_CRIT_CHANCE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_MAGIC_BACKSTAB_CRIT_CHANCE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_MAGIC_BACKSTAB_CRIT_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_BACKSTAB_CRIT_CHANCE_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_MAGIC_BACKSTAB_CRIT_CHANCE, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedMagicBackstabCritDamageToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_MAGIC_BACKSTAB_CRIT_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_BACKSTAB_CRIT_DAMAGE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_MAGIC_BACKSTAB_CRIT_DAMAGE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_MAGIC_BACKSTAB_CRIT_DAMAGE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_MAGIC_BACKSTAB_CRIT_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_BACKSTAB_CRIT_DAMAGE_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_MAGIC_BACKSTAB_CRIT_DAMAGE, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedSkillDmgToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_SKILL_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.SKILL_DMG_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_SKILL_DMG_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.SKILL_DMG_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_SKILL_DMG_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.SKILL_DMG_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_SKILL_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.SKILL_DMG_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_SKILL_DMG, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedOverallDmgToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_OVERALL_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.OVERALL_DMG_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_OVERALL_DMG_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.OVERALL_DMG_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_OVERALL_DMG_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.OVERALL_DMG_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_OVERALL_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.OVERALL_DMG_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_OVERALL_DMG, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedExtraDamageReceivedToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_EXTRA_DAMAGE_RECEIVED_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.EXTRA_DAMAGE_RECEIVED_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_EXTRA_DAMAGE_RECEIVED_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.EXTRA_DAMAGE_RECEIVED_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_EXTRA_DAMAGE_RECEIVED_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.EXTRA_DAMAGE_RECEIVED_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_EXTRA_DAMAGE_RECEIVED_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.EXTRA_DAMAGE_RECEIVED_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_EXTRA_DAMAGE_RECEIVED, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedEffectAreaToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_EFFECT_AREA_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.EFFECT_AREA_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_EFFECT_AREA_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.EFFECT_AREA_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_EFFECT_AREA_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.EFFECT_AREA_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_EFFECT_AREA_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.EFFECT_AREA_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_EFFECT_AREA, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedDurationToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_DURATION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.DURATION_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_DURATION_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.DURATION_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_DURATION_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.DURATION_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_DURATION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.DURATION_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_DURATION, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedDistanceToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_DISTANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.DISTANCE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_DISTANCE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.DISTANCE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_DISTANCE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.DISTANCE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_DISTANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.DISTANCE_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_DISTANCE, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedStationaryToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_STATIONARY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.STATIONARY_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_STATIONARY_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.STATIONARY_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_STATIONARY_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.STATIONARY_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_STATIONARY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.STATIONARY_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_STATIONARY, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedBeneficialStatusInvincibilityToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_BENEFICIAL_STATUS_INVINCIBILITY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BENEFICIAL_STATUS_INVINCIBILITY_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_BENEFICIAL_STATUS_INVINCIBILITY_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BENEFICIAL_STATUS_INVINCIBILITY_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_BENEFICIAL_STATUS_INVINCIBILITY_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BENEFICIAL_STATUS_INVINCIBILITY_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_BENEFICIAL_STATUS_INVINCIBILITY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BENEFICIAL_STATUS_INVINCIBILITY_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_BENEFICIAL_STATUS_INVINCIBILITY, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedBeneficialStatusSuperArmorToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_BENEFICIAL_STATUS_SUPER_ARMOR_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BENEFICIAL_STATUS_SUPER_ARMOR_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_BENEFICIAL_STATUS_SUPER_ARMOR_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BENEFICIAL_STATUS_SUPER_ARMOR_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_BENEFICIAL_STATUS_SUPER_ARMOR_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BENEFICIAL_STATUS_SUPER_ARMOR_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_BENEFICIAL_STATUS_SUPER_ARMOR_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BENEFICIAL_STATUS_SUPER_ARMOR_BASE;
        }

        applyTimedAttrToEntity(entity, SBS_UUID_TIMED_KNOCKBACK_RESIST_BASE, AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.KNOCKBACK_RESIST_BASE, EntityAttributes.KNOCKBACK_RESISTANCE, totalTimeTicks, 2, ConvertIntOp2AttrOp(Constants.OP_ADDITION), true, SkillID, null, null);
        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_BENEFICIAL_STATUS_SUPER_ARMOR, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusBleedingToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_BLEEDING_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BLEEDING_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_BLEEDING_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BLEEDING_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_BLEEDING_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BLEEDING_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_BLEEDING_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BLEEDING_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_BLEEDING, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusShockToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SHOCK_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SHOCK_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SHOCK_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SHOCK_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SHOCK_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SHOCK_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SHOCK_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SHOCK_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_SHOCK, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusFreezeToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_FREEZE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_FREEZE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_FREEZE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_FREEZE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_FREEZE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_FREEZE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_FREEZE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_FREEZE_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_FREEZE, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusBurnToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_BURN_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BURN_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_BURN_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BURN_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_BURN_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BURN_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_BURN_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BURN_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_BURN, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusCursedToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_CURSED_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CURSED_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_CURSED_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CURSED_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_CURSED_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CURSED_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_CURSED_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CURSED_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_CURSED, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusDisarmToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_DISARM_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_DISARM_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_DISARM_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_DISARM_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_DISARM_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_DISARM_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_DISARM_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_DISARM_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_DISARM, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusImmobilizationToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_IMMOBILIZATION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_IMMOBILIZATION_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_IMMOBILIZATION_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_IMMOBILIZATION_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_IMMOBILIZATION_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_IMMOBILIZATION_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_IMMOBILIZATION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_IMMOBILIZATION_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_IMMOBILIZATION, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusSleepToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SLEEP_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SLEEP_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SLEEP_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SLEEP_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SLEEP_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SLEEP_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SLEEP_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SLEEP_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_SLEEP, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusConfusionToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_CONFUSION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CONFUSION_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_CONFUSION_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CONFUSION_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_CONFUSION_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CONFUSION_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_CONFUSION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CONFUSION_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_CONFUSION, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusStunToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_STUN_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_STUN_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_STUN_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_STUN_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_STUN_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_STUN_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_STUN_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_STUN_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_STUN, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusSilenceToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SILENCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SILENCE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SILENCE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SILENCE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SILENCE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SILENCE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SILENCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SILENCE_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_SILENCE, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusAllResistToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_ALL_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_ALL_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_ALL_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_ALL_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_ALL_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_ALL_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_ALL_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_ALL_RESIST_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_ALL_RESIST, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusBleedingResistToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_BLEEDING_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BLEEDING_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_BLEEDING_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BLEEDING_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_BLEEDING_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BLEEDING_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_BLEEDING_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BLEEDING_RESIST_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_BLEEDING_RESIST, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusShockResistToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SHOCK_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SHOCK_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SHOCK_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SHOCK_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SHOCK_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SHOCK_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SHOCK_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SHOCK_RESIST_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_SHOCK_RESIST, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusFreezeResistToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_FREEZE_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_FREEZE_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_FREEZE_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_FREEZE_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_FREEZE_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_FREEZE_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_FREEZE_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_FREEZE_RESIST_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_FREEZE_RESIST, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusBurnResistToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_BURN_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BURN_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_BURN_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BURN_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_BURN_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BURN_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_BURN_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BURN_RESIST_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_BURN_RESIST, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusCursedResistToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_CURSED_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CURSED_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_CURSED_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CURSED_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_CURSED_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CURSED_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_CURSED_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CURSED_RESIST_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_CURSED_RESIST, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusDisarmResistToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_DISARM_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_DISARM_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_DISARM_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_DISARM_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_DISARM_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_DISARM_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_DISARM_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_DISARM_RESIST_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_DISARM_RESIST, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusStunResistToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_STUN_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_STUN_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_STUN_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_STUN_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_STUN_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_STUN_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_STUN_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_STUN_RESIST_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_STUN_RESIST, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAbnormalStatusSilenceResistToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SILENCE_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SILENCE_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SILENCE_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SILENCE_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SILENCE_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SILENCE_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ABNORMAL_STATUS_SILENCE_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SILENCE_RESIST_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_SILENCE_RESIST, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedAllDmgReductionToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ALL_DMG_REDUCTION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ALL_DMG_REDUCTION_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ALL_DMG_REDUCTION_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ALL_DMG_REDUCTION_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ALL_DMG_REDUCTION_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ALL_DMG_REDUCTION_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ALL_DMG_REDUCTION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ALL_DMG_REDUCTION_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ALL_DMG_REDUCTION, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedPhysicDmgReductionToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_PHYSIC_DMG_REDUCTION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_DMG_REDUCTION_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_PHYSIC_DMG_REDUCTION_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_DMG_REDUCTION_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_PHYSIC_DMG_REDUCTION_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_DMG_REDUCTION_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_PHYSIC_DMG_REDUCTION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_DMG_REDUCTION_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_DMG_REDUCTION, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedMagicDmgReductionToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_MAGIC_DMG_REDUCTION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_DMG_REDUCTION_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_MAGIC_DMG_REDUCTION_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_DMG_REDUCTION_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_MAGIC_DMG_REDUCTION_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_DMG_REDUCTION_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_MAGIC_DMG_REDUCTION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_DMG_REDUCTION_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_MAGIC_DMG_REDUCTION, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedElementLightToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_LIGHT_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_LIGHT_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_LIGHT_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_LIGHT_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ELEMENT_LIGHT_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_LIGHT_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ELEMENT_LIGHT_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_LIGHT_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_LIGHT, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedElementDarknessToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_DARKNESS_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_DARKNESS_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_DARKNESS_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_DARKNESS_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ELEMENT_DARKNESS_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_DARKNESS_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ELEMENT_DARKNESS_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_DARKNESS_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_DARKNESS, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedElementMetalToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_METAL_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_METAL_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_METAL_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_METAL_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ELEMENT_METAL_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_METAL_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ELEMENT_METAL_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_METAL_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_METAL, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedElementWoodToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_WOOD_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WOOD_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_WOOD_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WOOD_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ELEMENT_WOOD_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WOOD_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ELEMENT_WOOD_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WOOD_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_WOOD, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedElementEarthToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_EARTH_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_EARTH_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_EARTH_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_EARTH_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ELEMENT_EARTH_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_EARTH_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ELEMENT_EARTH_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_EARTH_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_EARTH, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedElementFireToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_FIRE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_FIRE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_FIRE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_FIRE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ELEMENT_FIRE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_FIRE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ELEMENT_FIRE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_FIRE_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_FIRE, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedElementAirToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_AIR_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_AIR_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_AIR_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_AIR_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ELEMENT_AIR_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_AIR_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ELEMENT_AIR_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_AIR_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_AIR, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedElementWaterToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_WATER_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WATER_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_WATER_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WATER_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ELEMENT_WATER_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WATER_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ELEMENT_WATER_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WATER_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_WATER, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedElementLightResistToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_LIGHT_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_LIGHT_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_LIGHT_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_LIGHT_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ELEMENT_LIGHT_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_LIGHT_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ELEMENT_LIGHT_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_LIGHT_RESIST_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_LIGHT_RESIST, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedElementDarknessResistToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_DARKNESS_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_DARKNESS_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_DARKNESS_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_DARKNESS_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ELEMENT_DARKNESS_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_DARKNESS_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ELEMENT_DARKNESS_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_DARKNESS_RESIST_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_DARKNESS_RESIST, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedElementMetalResistToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_METAL_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_METAL_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_METAL_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_METAL_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ELEMENT_METAL_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_METAL_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ELEMENT_METAL_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_METAL_RESIST_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_METAL_RESIST, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedElementWoodResistToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_WOOD_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WOOD_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_WOOD_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WOOD_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ELEMENT_WOOD_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WOOD_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ELEMENT_WOOD_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WOOD_RESIST_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_WOOD_RESIST, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedElementEarthResistToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_EARTH_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_EARTH_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_EARTH_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_EARTH_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ELEMENT_EARTH_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_EARTH_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ELEMENT_EARTH_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_EARTH_RESIST_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_EARTH_RESIST, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedElementFireResistToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_FIRE_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_FIRE_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_FIRE_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_FIRE_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ELEMENT_FIRE_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_FIRE_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ELEMENT_FIRE_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_FIRE_RESIST_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_FIRE_RESIST, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedElementAirResistToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_AIR_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_AIR_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_AIR_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_AIR_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ELEMENT_AIR_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_AIR_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ELEMENT_AIR_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_AIR_RESIST_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_AIR_RESIST, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedElementWaterResistToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_WATER_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WATER_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_ELEMENT_WATER_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WATER_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_ELEMENT_WATER_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WATER_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_ELEMENT_WATER_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WATER_RESIST_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_WATER_RESIST, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }

    //==========================================================
    public static void applyTimedBeingAttackedToEntity(LivingEntity entity, int totalTimeTicks, double amount, int operator, boolean allowDuplicate, String SkillID, MethodAction action, MethodAction finalAction) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_TIMED_BEING_ATTACKED_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BEING_ATTACKED_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_TIMED_BEING_ATTACKED_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BEING_ATTACKED_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_TIMED_BEING_ATTACKED_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BEING_ATTACKED_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_TIMED_BEING_ATTACKED_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BEING_ATTACKED_BASE;
        }

        applyTimedAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_BEING_ATTACKED, totalTimeTicks, amount, op, allowDuplicate, SkillID, action, finalAction);
    }
}

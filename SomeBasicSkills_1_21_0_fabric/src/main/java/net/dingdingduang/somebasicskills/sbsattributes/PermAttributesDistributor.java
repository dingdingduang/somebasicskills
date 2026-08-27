package net.dingdingduang.somebasicskills.sbsattributes;

import net.dingdingduang.somebasicskills.Constants;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import net.dingdingduang.somebasicskills.networking.NetworkingFetchMsgMethods;
import net.dingdingduang.somebasicskills.sbsattributes.statusquery.AttributeServerPlayerStatusQueryMethods;
import net.dingdingduang.somebasicskills.util.MethodAction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerValue2BaseMultiplierMap;
import static net.dingdingduang.somebasicskills.sbsattributes.TimedAttributesDistributor.ConvertIntOp2AttrOp;

public class PermAttributesDistributor {
    //TODO: remove living entity from hashtable once they die
    private static ConcurrentHashMap<LivingEntity, HashMap<String, AttributeHelper>> ServerEntityPermAttrName2AttrHelper = new ConcurrentHashMap<>();
    //attrname+opInt as key matched final value for base/multiplier
    private static HashMap<LivingEntity, HashMap<String, Double>> ServerEntityPermAttrNameWithOP2FinalValue = new HashMap<>();

    public static final Identifier SBS_UUID_PERM_ATTACK_DAMAGE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_attack_damage_base");
    public static final Identifier SBS_UUID_PERM_ATTACK_DAMAGE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_attack_damage_multiply_base");
    public static final Identifier SBS_UUID_PERM_ATTACK_DAMAGE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_attack_damage_multiply_total");

    public static final Identifier SBS_UUID_PERM_ATTACK_KNOCKBACK_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_attack_knockback_base");
    public static final Identifier SBS_UUID_PERM_ATTACK_KNOCKBACK_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_attack_knockback_multiply_base");
    public static final Identifier SBS_UUID_PERM_ATTACK_KNOCKBACK_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_attack_knockback_multiply_total");

    public static final Identifier SBS_UUID_PERM_KNOCKBACK_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_knockback_resist_base");
    public static final Identifier SBS_UUID_PERM_KNOCKBACK_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_knockback_resist_multiply_base");
    public static final Identifier SBS_UUID_PERM_KNOCKBACK_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_knockback_resist_multiply_total");

    public static final Identifier SBS_UUID_PERM_ASPD_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_aspd_base");
    public static final Identifier SBS_UUID_PERM_ASPD_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_aspd_multiply_base");
    public static final Identifier SBS_UUID_PERM_ASPD_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_aspd_multiply_total");

    public static final Identifier SBS_UUID_PERM_MOVS_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_movs_base");
    public static final Identifier SBS_UUID_PERM_MOVS_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_movs_multiply_base");
    public static final Identifier SBS_UUID_PERM_MOVS_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_movs_multiply_total");

    public static final Identifier SBS_UUID_PERM_MAX_HP_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_max_hp_base");
    public static final Identifier SBS_UUID_PERM_MAX_HP_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_max_hp_multiply_base");
    public static final Identifier SBS_UUID_PERM_MAX_HP_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_max_hp_multiply_total");

    public static final Identifier SBS_UUID_PERM_ARMOR_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_armor_base");
    public static final Identifier SBS_UUID_PERM_ARMOR_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_armor_multiply_base");
    public static final Identifier SBS_UUID_PERM_ARMOR_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_armor_multiply_total");

    public static final Identifier SBS_UUID_PERM_ARMOR_TOUGHNESS_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_armor_toughness_base");
    public static final Identifier SBS_UUID_PERM_ARMOR_TOUGHNESS_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_armor_toughness_multiply_base");
    public static final Identifier SBS_UUID_PERM_ARMOR_TOUGHNESS_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_armor_toughness_multiply_total");

    public static final Identifier SBS_UUID_PERM_LUCK_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_luck_base");
    public static final Identifier SBS_UUID_PERM_LUCK_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_luck_multiply_base");
    public static final Identifier SBS_UUID_PERM_LUCK_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_luck_multiply_total");

    public static final Identifier SBS_UUID_PERM_JUMP_STRENGTH_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_jump_strength_base");
    public static final Identifier SBS_UUID_PERM_JUMP_STRENGTH_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_jump_strength_multiply_base");
    public static final Identifier SBS_UUID_PERM_JUMP_STRENGTH_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_jump_strength_multiply_total");

    public static final Identifier SBS_UUID_PERM_MINING_EFFICIENCY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_mining_efficiency_base");
    public static final Identifier SBS_UUID_PERM_MINING_EFFICIENCY_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_mining_efficiency_multiply_base");
    public static final Identifier SBS_UUID_PERM_MINING_EFFICIENCY_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_mining_efficiency_multiply_total");

    public static final Identifier SBS_UUID_PERM_MAX_MP_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_max_mp_base");
    public static final Identifier SBS_UUID_PERM_MAX_MP_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_max_mp_multiply_base");
    public static final Identifier SBS_UUID_PERM_MAX_MP_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_max_mp_multiply_total");

    public static final Identifier SBS_UUID_PERM_ARMOR_PIERCE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_armor_pierce_base");
    public static final Identifier SBS_UUID_PERM_ARMOR_PIERCE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_armor_pierce_multiply_base");
    public static final Identifier SBS_UUID_PERM_ARMOR_PIERCE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_armor_pierce_multiply_total");

    public static final Identifier SBS_UUID_PERM_PROTECTION_PIERCE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_protection_pierce_base");
    public static final Identifier SBS_UUID_PERM_PROTECTION_PIERCE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_protection_pierce_multiply_base");
    public static final Identifier SBS_UUID_PERM_PROTECTION_PIERCE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_protection_pierce_multiply_total");

    public static final Identifier SBS_UUID_PERM_COOLDOWN_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_cooldown_base");
    public static final Identifier SBS_UUID_PERM_COOLDOWN_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_cooldown_multiply_base");
    public static final Identifier SBS_UUID_PERM_COOLDOWN_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_cooldown_multiply_total");

    public static final Identifier SBS_UUID_PERM_CHANNELING_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_channeling_base");
    public static final Identifier SBS_UUID_PERM_CHANNELING_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_channeling_multiply_base");
    public static final Identifier SBS_UUID_PERM_CHANNELING_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_channeling_multiply_total");

    public static final Identifier SBS_UUID_PERM_UNINTERRUPTIBLE_CHANCE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_uninterruptible_chance_base");
    public static final Identifier SBS_UUID_PERM_UNINTERRUPTIBLE_CHANCE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_uninterruptible_chance_multiply_base");
    public static final Identifier SBS_UUID_PERM_UNINTERRUPTIBLE_CHANCE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_uninterruptible_chance_multiply_total");

    public static final Identifier SBS_UUID_PERM_INDEPENDENCE_DMG_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_independence_dmg_base");
    public static final Identifier SBS_UUID_PERM_INDEPENDENCE_DMG_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_independence_dmg_multiply_base");
    public static final Identifier SBS_UUID_PERM_INDEPENDENCE_DMG_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_independence_dmg_multiply_total");

    public static final Identifier SBS_UUID_PERM_PHYSIC_CRIT_CHANCE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_physic_crit_chance_base");
    public static final Identifier SBS_UUID_PERM_PHYSIC_CRIT_CHANCE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_physic_crit_chance_multiply_base");
    public static final Identifier SBS_UUID_PERM_PHYSIC_CRIT_CHANCE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_physic_crit_chance_multiply_total");

    public static final Identifier SBS_UUID_PERM_PHYSIC_CRIT_DAMAGE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_physic_crit_damage_base");
    public static final Identifier SBS_UUID_PERM_PHYSIC_CRIT_DAMAGE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_physic_crit_damage_multiply_base");
    public static final Identifier SBS_UUID_PERM_PHYSIC_CRIT_DAMAGE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_physic_crit_damage_multiply_total");

    public static final Identifier SBS_UUID_PERM_MAGIC_DMG_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_magic_dmg_base");
    public static final Identifier SBS_UUID_PERM_MAGIC_DMG_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_magic_dmg_multiply_base");
    public static final Identifier SBS_UUID_PERM_MAGIC_DMG_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_magic_dmg_multiply_total");

    public static final Identifier SBS_UUID_PERM_MAGIC_CRIT_CHANCE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_magic_crit_chance_base");
    public static final Identifier SBS_UUID_PERM_MAGIC_CRIT_CHANCE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_magic_crit_chance_multiply_base");
    public static final Identifier SBS_UUID_PERM_MAGIC_CRIT_CHANCE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_magic_crit_chance_multiply_total");

    public static final Identifier SBS_UUID_PERM_MAGIC_CRIT_DAMAGE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_magic_crit_damage_base");
    public static final Identifier SBS_UUID_PERM_MAGIC_CRIT_DAMAGE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_magic_crit_damage_multiply_base");
    public static final Identifier SBS_UUID_PERM_MAGIC_CRIT_DAMAGE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_magic_crit_damage_multiply_total");

    public static final Identifier SBS_UUID_PERM_BACKSTAB_DMG_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_backstab_dmg_base");
    public static final Identifier SBS_UUID_PERM_BACKSTAB_DMG_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_backstab_dmg_multiply_base");
    public static final Identifier SBS_UUID_PERM_BACKSTAB_DMG_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_backstab_dmg_multiply_total");

    public static final Identifier SBS_UUID_PERM_PHYSIC_BACKSTAB_CRIT_CHANCE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_physic_backstab_crit_chance_base");
    public static final Identifier SBS_UUID_PERM_PHYSIC_BACKSTAB_CRIT_CHANCE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_physic_backstab_crit_chance_multiply_base");
    public static final Identifier SBS_UUID_PERM_PHYSIC_BACKSTAB_CRIT_CHANCE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_physic_backstab_crit_chance_multiply_total");

    public static final Identifier SBS_UUID_PERM_PHYSIC_BACKSTAB_CRIT_DAMAGE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_physic_backstab_crit_damage_base");
    public static final Identifier SBS_UUID_PERM_PHYSIC_BACKSTAB_CRIT_DAMAGE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_physic_backstab_crit_damage_multiply_base");
    public static final Identifier SBS_UUID_PERM_PHYSIC_BACKSTAB_CRIT_DAMAGE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_physic_backstab_crit_damage_multiply_total");

    public static final Identifier SBS_UUID_PERM_MAGIC_BACKSTAB_CRIT_CHANCE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_magic_backstab_crit_chance_base");
    public static final Identifier SBS_UUID_PERM_MAGIC_BACKSTAB_CRIT_CHANCE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_magic_backstab_crit_chance_multiply_base");
    public static final Identifier SBS_UUID_PERM_MAGIC_BACKSTAB_CRIT_CHANCE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_magic_backstab_crit_chance_multiply_total");

    public static final Identifier SBS_UUID_PERM_MAGIC_BACKSTAB_CRIT_DAMAGE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_magic_backstab_crit_damage_base");
    public static final Identifier SBS_UUID_PERM_MAGIC_BACKSTAB_CRIT_DAMAGE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_magic_backstab_crit_damage_multiply_base");
    public static final Identifier SBS_UUID_PERM_MAGIC_BACKSTAB_CRIT_DAMAGE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_magic_backstab_crit_damage_multiply_total");

    public static final Identifier SBS_UUID_PERM_SKILL_DMG_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_skill_dmg_base");
    public static final Identifier SBS_UUID_PERM_SKILL_DMG_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_skill_dmg_multiply_base");
    public static final Identifier SBS_UUID_PERM_SKILL_DMG_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_skill_dmg_multiply_total");

    public static final Identifier SBS_UUID_PERM_OVERALL_DMG_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_overall_dmg_base");
    public static final Identifier SBS_UUID_PERM_OVERALL_DMG_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_overall_dmg_multiply_base");
    public static final Identifier SBS_UUID_PERM_OVERALL_DMG_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_overall_dmg_multiply_total");

    public static final Identifier SBS_UUID_PERM_EXTRA_DAMAGE_RECEIVED_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_extra_damage_received_base");
    public static final Identifier SBS_UUID_PERM_EXTRA_DAMAGE_RECEIVED_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_extra_damage_received_multiply_base");
    public static final Identifier SBS_UUID_PERM_EXTRA_DAMAGE_RECEIVED_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_extra_damage_received_multiply_total");

    public static final Identifier SBS_UUID_PERM_EFFECT_AREA_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_effect_area_base");
    public static final Identifier SBS_UUID_PERM_EFFECT_AREA_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_effect_area_multiply_base");
    public static final Identifier SBS_UUID_PERM_EFFECT_AREA_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_effect_area_multiply_total");

    public static final Identifier SBS_UUID_PERM_DURATION_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_duration_base");
    public static final Identifier SBS_UUID_PERM_DURATION_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_duration_multiply_base");
    public static final Identifier SBS_UUID_PERM_DURATION_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_duration_multiply_total");

    public static final Identifier SBS_UUID_PERM_DISTANCE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_distance_base");
    public static final Identifier SBS_UUID_PERM_DISTANCE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_distance_multiply_base");
    public static final Identifier SBS_UUID_PERM_DISTANCE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_distance_multiply_total");

    public static final Identifier SBS_UUID_PERM_STATIONARY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_stationary_base");
    public static final Identifier SBS_UUID_PERM_STATIONARY_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_stationary_multiply_base");
    public static final Identifier SBS_UUID_PERM_STATIONARY_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_stationary_multiply_total");

    public static final Identifier SBS_UUID_PERM_BENEFICIAL_STATUS_INVINCIBILITY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_beneficial_status_invincibility_base");
    public static final Identifier SBS_UUID_PERM_BENEFICIAL_STATUS_INVINCIBILITY_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_beneficial_status_invincibility_multiply_base");
    public static final Identifier SBS_UUID_PERM_BENEFICIAL_STATUS_INVINCIBILITY_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_beneficial_status_invincibility_multiply_total");

    public static final Identifier SBS_UUID_PERM_BENEFICIAL_STATUS_SUPER_ARMOR_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_beneficial_status_super_armor_base");
    public static final Identifier SBS_UUID_PERM_BENEFICIAL_STATUS_SUPER_ARMOR_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_beneficial_status_super_armor_multiply_base");
    public static final Identifier SBS_UUID_PERM_BENEFICIAL_STATUS_SUPER_ARMOR_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_beneficial_status_super_armor_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_BLEEDING_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_bleeding_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_BLEEDING_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_bleeding_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_BLEEDING_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_bleeding_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_SHOCK_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_shock_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_SHOCK_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_shock_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_SHOCK_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_shock_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_FREEZE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_freeze_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_FREEZE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_freeze_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_FREEZE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_freeze_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_BURN_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_burn_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_BURN_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_burn_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_BURN_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_burn_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_CURSED_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_cursed_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_CURSED_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_cursed_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_CURSED_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_cursed_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_DISARM_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_disarm_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_DISARM_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_disarm_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_DISARM_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_disarm_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_IMMOBILIZATION_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_immobilization_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_IMMOBILIZATION_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_immobilization_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_IMMOBILIZATION_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_immobilization_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_SLEEP_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_sleep_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_SLEEP_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_sleep_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_SLEEP_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_sleep_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_CONFUSION_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_confusion_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_CONFUSION_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_confusion_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_CONFUSION_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_confusion_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_STUN_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_stun_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_STUN_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_stun_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_STUN_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_stun_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_SILENCE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_silence_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_SILENCE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_silence_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_SILENCE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_silence_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_ALL_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_all_resist_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_ALL_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_all_resist_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_ALL_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_all_resist_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_BLEEDING_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_bleeding_resist_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_BLEEDING_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_bleeding_resist_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_BLEEDING_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_bleeding_resist_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_SHOCK_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_shock_resist_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_SHOCK_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_shock_resist_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_SHOCK_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_shock_resist_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_FREEZE_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_freeze_resist_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_FREEZE_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_freeze_resist_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_FREEZE_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_freeze_resist_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_BURN_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_burn_resist_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_BURN_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_burn_resist_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_BURN_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_burn_resist_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_CURSED_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_cursed_resist_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_CURSED_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_cursed_resist_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_CURSED_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_cursed_resist_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_DISARM_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_disarm_resist_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_DISARM_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_disarm_resist_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_DISARM_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_disarm_resist_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_STUN_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_stun_resist_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_STUN_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_stun_resist_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_STUN_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_stun_resist_multiply_total");

    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_SILENCE_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_silence_resist_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_SILENCE_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_silence_resist_multiply_base");
    public static final Identifier SBS_UUID_PERM_ABNORMAL_STATUS_SILENCE_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_abnormal_status_silence_resist_multiply_total");

    public static final Identifier SBS_UUID_PERM_ALL_DMG_REDUCTION_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_all_dmg_reduction_base");
    public static final Identifier SBS_UUID_PERM_ALL_DMG_REDUCTION_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_all_dmg_reduction_multiply_base");
    public static final Identifier SBS_UUID_PERM_ALL_DMG_REDUCTION_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_all_dmg_reduction_multiply_total");

    public static final Identifier SBS_UUID_PERM_PHYSIC_DMG_REDUCTION_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_physic_dmg_reduction_base");
    public static final Identifier SBS_UUID_PERM_PHYSIC_DMG_REDUCTION_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_physic_dmg_reduction_multiply_base");
    public static final Identifier SBS_UUID_PERM_PHYSIC_DMG_REDUCTION_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_physic_dmg_reduction_multiply_total");

    public static final Identifier SBS_UUID_PERM_MAGIC_DMG_REDUCTION_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_magic_dmg_reduction_base");
    public static final Identifier SBS_UUID_PERM_MAGIC_DMG_REDUCTION_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_magic_dmg_reduction_multiply_base");
    public static final Identifier SBS_UUID_PERM_MAGIC_DMG_REDUCTION_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_magic_dmg_reduction_multiply_total");

    public static final Identifier SBS_UUID_PERM_ELEMENT_LIGHT_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_light_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_LIGHT_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_light_multiply_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_LIGHT_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_light_multiply_total");

    public static final Identifier SBS_UUID_PERM_ELEMENT_DARKNESS_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_darkness_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_DARKNESS_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_darkness_multiply_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_DARKNESS_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_darkness_multiply_total");

    public static final Identifier SBS_UUID_PERM_ELEMENT_METAL_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_metal_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_METAL_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_metal_multiply_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_METAL_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_metal_multiply_total");

    public static final Identifier SBS_UUID_PERM_ELEMENT_WOOD_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_wood_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_WOOD_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_wood_multiply_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_WOOD_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_wood_multiply_total");

    public static final Identifier SBS_UUID_PERM_ELEMENT_EARTH_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_earth_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_EARTH_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_earth_multiply_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_EARTH_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_earth_multiply_total");

    public static final Identifier SBS_UUID_PERM_ELEMENT_FIRE_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_fire_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_FIRE_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_fire_multiply_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_FIRE_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_fire_multiply_total");

    public static final Identifier SBS_UUID_PERM_ELEMENT_AIR_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_air_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_AIR_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_air_multiply_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_AIR_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_air_multiply_total");

    public static final Identifier SBS_UUID_PERM_ELEMENT_WATER_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_water_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_WATER_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_water_multiply_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_WATER_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_water_multiply_total");

    public static final Identifier SBS_UUID_PERM_ELEMENT_LIGHT_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_light_resist_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_LIGHT_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_light_resist_multiply_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_LIGHT_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_light_resist_multiply_total");

    public static final Identifier SBS_UUID_PERM_ELEMENT_DARKNESS_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_darkness_resist_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_DARKNESS_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_darkness_resist_multiply_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_DARKNESS_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_darkness_resist_multiply_total");

    public static final Identifier SBS_UUID_PERM_ELEMENT_METAL_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_metal_resist_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_METAL_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_metal_resist_multiply_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_METAL_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_metal_resist_multiply_total");

    public static final Identifier SBS_UUID_PERM_ELEMENT_WOOD_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_wood_resist_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_WOOD_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_wood_resist_multiply_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_WOOD_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_wood_resist_multiply_total");

    public static final Identifier SBS_UUID_PERM_ELEMENT_EARTH_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_earth_resist_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_EARTH_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_earth_resist_multiply_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_EARTH_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_earth_resist_multiply_total");

    public static final Identifier SBS_UUID_PERM_ELEMENT_FIRE_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_fire_resist_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_FIRE_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_fire_resist_multiply_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_FIRE_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_fire_resist_multiply_total");

    public static final Identifier SBS_UUID_PERM_ELEMENT_AIR_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_air_resist_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_AIR_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_air_resist_multiply_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_AIR_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_air_resist_multiply_total");

    public static final Identifier SBS_UUID_PERM_ELEMENT_WATER_RESIST_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_water_resist_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_WATER_RESIST_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_water_resist_multiply_base");
    public static final Identifier SBS_UUID_PERM_ELEMENT_WATER_RESIST_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_element_water_resist_multiply_total");

//    public static final Identifier SBS_UUID_PERM_BEING_ATTACKED_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_being_attacked_base");
//    public static final Identifier SBS_UUID_PERM_BEING_ATTACKED_MULTIPLY_BASE = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_being_attacked_multiply_base");
//    public static final Identifier SBS_UUID_PERM_BEING_ATTACKED_MULTIPLY_TOTAL = getMCResourceLocation(Constants.MOD_ID, "sbs_uuid_perm_being_attacked_multiply_total");

    public static HashMap<String, Double> getServerEntityPermAttrNameWithOP2FinalValueMap(LivingEntity entity) {
        if (ServerEntityPermAttrNameWithOP2FinalValue.containsKey(entity)) {
            return ServerEntityPermAttrNameWithOP2FinalValue.get(entity);
        }
        return new HashMap<String, Double>();
    }

    public static void removeEntityFromMap(LivingEntity entity) {
        if (ServerEntityPermAttrName2AttrHelper != null) {
            ServerEntityPermAttrName2AttrHelper.remove(entity);
            ServerEntityPermAttrNameWithOP2FinalValue.remove(entity);
        }
    }


    public static void applyPermAttrToEntity(LivingEntity entity, Identifier attrUUID, String attrName, RegistryEntry<EntityAttribute> attrType, double amount, EntityAttributeModifier.Operation op, String SkillID, MethodAction action) {
//        UniqueAttributePrimitiveOnly attrPrimitive = new UniqueAttributePrimitiveOnly(amount, -1, attrUUID, attrName);
        String playerStatusName = attrName + AttributeServerPlayerStatusQueryMethods.ALL_SKILLS;
//        UniqueAttributePrimitiveOnly attrPrimitive = new UniqueAttributePrimitiveOnly(amount, -1, op.getId()(), attrUUID, attrName, SkillID);
        UniqueAttributePrimitiveOnly attrPrimitive = new UniqueAttributePrimitiveOnly(amount, -1, op.getId(), attrUUID, playerStatusName, SkillID, SkillID);
        attrPrimitive.setAttributeMethodAction(action);
        double previousAmount = applyPermAttr(entity, attrUUID, playerStatusName, attrType, attrPrimitive, op, SkillID), totalAmount;

        //update playerStatusMap
        if (entity instanceof ServerPlayerEntity sp1) {
            //ASSUME getSPlayerValue2BaseMultiplierMap() is not null simpy because it is initialized and should never be null unless server is offline
            HashMap<String, Double> tempPlayerStatusMap = getSPlayerValue2BaseMultiplierMap().get(sp1);
            //add status value based on given attr i.e. HP ATK SPEED
            if (tempPlayerStatusMap.containsKey(playerStatusName)) {
                totalAmount = amount-previousAmount+tempPlayerStatusMap.get(playerStatusName);
                tempPlayerStatusMap.put(playerStatusName, totalAmount);
                NetworkingFetchMsgMethods.FetchPlayerStatusToClientSide(sp1, playerStatusName, totalAmount);
//                printInGameMsg("previous: "+previousAmount+", current: "+amount);
            }
        }
    }

    public static double applyPermAttr(LivingEntity entity, Identifier attrUUID, String attrName, RegistryEntry<EntityAttribute> attrType, AttributePrimitiveOnly attrPrimitive, EntityAttributeModifier.Operation op, String SkillID) {
        if (!ServerEntityPermAttrName2AttrHelper.containsKey(entity)) {
            ServerEntityPermAttrName2AttrHelper.put(entity, new HashMap<String, AttributeHelper>());
            ServerEntityPermAttrNameWithOP2FinalValue.put(entity, new HashMap<String, Double>());
        }
        String attrNameWithSkillIDPostfix = attrName+SkillID;
        HashMap<String, AttributeHelper> PlayerAttr = ServerEntityPermAttrName2AttrHelper.get(entity);

        if (!PlayerAttr.containsKey(attrNameWithSkillIDPostfix)) {
            PlayerAttr.put(attrNameWithSkillIDPostfix, new AttributeHelper(attrType));
        }

        AttributeHelper tempAttributeHelper = PlayerAttr.get(attrNameWithSkillIDPostfix), tempAttributeHelper2;
        HashMap<AttributePrimitiveOnly, Integer> tempAttrModifierMap = tempAttributeHelper.getAttributePrimitive2Index();
        UniqueAttributePrimitiveOnly tempUnique = new UniqueAttributePrimitiveOnly(attrPrimitive.getAmount()
                , attrPrimitive.getTimeTicks(), op.getId(), attrPrimitive.getAttrUUID(), attrPrimitive.getAttrName()
                , attrPrimitive.getSkillID(), SkillID);
        tempUnique.setAttributeMethodAction(attrPrimitive.getAttributeMethodAction());

        double previousAmount = 0.0;
        if (tempAttrModifierMap.containsKey(tempUnique)) {
            for (AttributePrimitiveOnly tempAttrPrim: tempAttrModifierMap.keySet()) {
                if (tempUnique.equals(tempAttrPrim)) {
                    previousAmount = tempAttrPrim.getAmount();
                    break;
                }
            }
        }
        tempAttrModifierMap.remove(tempUnique);

        ////do something if AttributePrimitiveOnly has something special like emit particle effect
        if (tempUnique.getAttributeMethodAction() != null) {
            tempUnique.getAttributeMethodAction().executeAction(entity);
        }

        double FinalValue = 0.0;
        int operationInt = op.getId();
//        if (tempUnique.getAmount() != FinalValue) {
//        }
        tempAttrModifierMap.put(tempUnique, tempAttrModifierMap.size());

        //calculate final Attr Value
        for (String PlayerAttrAttrName : PlayerAttr.keySet()) {
            tempAttributeHelper2 = PlayerAttr.get(PlayerAttrAttrName);
            tempAttrModifierMap = tempAttributeHelper2.getAttributePrimitive2Index();
            for (AttributePrimitiveOnly tempAttrPrimitive : tempAttrModifierMap.keySet()) {
                if (tempAttrPrimitive.getAttrUUID().equals(attrUUID)) {
                    FinalValue += tempAttrPrimitive.getAmount();
                }
            }
        }

        ServerEntityPermAttrNameWithOP2FinalValue.get(entity).put(attrType.getIdAsString()+operationInt, FinalValue);

        EntityAttributeModifier tempAttr = new EntityAttributeModifier(attrUUID, FinalValue, op);
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
            if (tempAttrInstance.hasModifier(attrUUID)) {
                tempAttrInstance.removeModifier(attrUUID);
            }
            double absFinalValue = Math.abs(FinalValue);
            if (absFinalValue > 0.001 || Math.round(absFinalValue) > 0) {
                tempAttrInstance.addTemporaryModifier(tempAttr);
            }
//            tempAttrInstance.addTemporaryModifier(tempAttr);
            //^^^^^^^^^^^^^^^^^^
            if (isAttrTypeHealth) {
                entity.setHealth(EntityHP_Percent*entity.getMaxHealth());
            }
        }
        return previousAmount;
    }


    //not recommend using multiply total base+multiply is game breaking
    public static void applyPermAttackDMGToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ATTACK_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.ATTACK_DAMAGE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ATTACK_DAMAGE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.ATTACK_DAMAGE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ATTACK_DAMAGE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.ATTACK_DAMAGE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ATTACK_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.ATTACK_DAMAGE_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, EntityAttributes.GENERIC_ATTACK_DAMAGE, amount, op, SkillID, action);
    }

    public static void applyPermAttackKnockbackToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ATTACK_KNOCKBACK_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.ATTACK_KNOCKBACK_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ATTACK_KNOCKBACK_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.ATTACK_KNOCKBACK_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ATTACK_KNOCKBACK_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.ATTACK_KNOCKBACK_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ATTACK_KNOCKBACK_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.ATTACK_KNOCKBACK_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, EntityAttributes.GENERIC_ATTACK_KNOCKBACK, amount, op, SkillID, action);
    }

    public static void applyPermKnockbackResistToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_KNOCKBACK_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.KNOCKBACK_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_KNOCKBACK_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.KNOCKBACK_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_KNOCKBACK_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.KNOCKBACK_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_KNOCKBACK_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.KNOCKBACK_RESIST_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, amount, op, SkillID, action);
    }

    public static void applyPermAttackSpeedToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ASPD_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.ASPD_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ASPD_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.ASPD_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ASPD_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.ASPD_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ASPD_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.ASPD_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, EntityAttributes.GENERIC_ATTACK_SPEED, amount, op, SkillID, action);
    }

    public static void applyPermMovementSpeedToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_MOVS_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.MOVS_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_MOVS_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.MOVS_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_MOVS_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.MOVS_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_MOVS_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.MOVS_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, EntityAttributes.GENERIC_MOVEMENT_SPEED, amount, op, SkillID, action);
    }

    public static void applyPermMaxHealthToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_MAX_HP_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.MAX_HP_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_MAX_HP_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.MAX_HP_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_MAX_HP_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.MAX_HP_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_MAX_HP_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.MAX_HP_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, EntityAttributes.GENERIC_MAX_HEALTH, amount, op, SkillID, action);
    }

    public static void applyPermArmorToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ARMOR_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.ARMOR_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ARMOR_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.ARMOR_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ARMOR_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.ARMOR_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ARMOR_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.ARMOR_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, EntityAttributes.GENERIC_ARMOR, amount, op, SkillID, action);
    }

    public static void applyPermArmorToughnessToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ARMOR_TOUGHNESS_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.ARMOR_TOUGHNESS_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ARMOR_TOUGHNESS_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.ARMOR_TOUGHNESS_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ARMOR_TOUGHNESS_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.ARMOR_TOUGHNESS_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ARMOR_TOUGHNESS_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.ARMOR_TOUGHNESS_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, EntityAttributes.GENERIC_ARMOR_TOUGHNESS, amount, op, SkillID, action);
    }

    public static void applyPermLuckToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_LUCK_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.LUCK_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_LUCK_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.LUCK_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_LUCK_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.LUCK_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_LUCK_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.LUCK_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, EntityAttributes.GENERIC_LUCK, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermJumpStrengthToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_JUMP_STRENGTH_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.JUMP_STRENGTH_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_JUMP_STRENGTH_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.JUMP_STRENGTH_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_JUMP_STRENGTH_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.JUMP_STRENGTH_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_JUMP_STRENGTH_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.JUMP_STRENGTH_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, EntityAttributes.GENERIC_JUMP_STRENGTH, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermMiningEfficiencyToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_MINING_EFFICIENCY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MINING_EFFICIENCY_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_MINING_EFFICIENCY_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MINING_EFFICIENCY_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_MINING_EFFICIENCY_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MINING_EFFICIENCY_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_MINING_EFFICIENCY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MINING_EFFICIENCY_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, EntityAttributes.PLAYER_MINING_EFFICIENCY, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermMaxMpToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_MAX_MP_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAX_MP_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_MAX_MP_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAX_MP_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_MAX_MP_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAX_MP_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_MAX_MP_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAX_MP_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_MAX_MP, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermArmorPierceToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ARMOR_PIERCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ARMOR_PIERCE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ARMOR_PIERCE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ARMOR_PIERCE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ARMOR_PIERCE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ARMOR_PIERCE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ARMOR_PIERCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ARMOR_PIERCE_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ARMOR_PIERCE, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermProtectionPierceToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_PROTECTION_PIERCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PROTECTION_PIERCE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_PROTECTION_PIERCE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PROTECTION_PIERCE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_PROTECTION_PIERCE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PROTECTION_PIERCE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_PROTECTION_PIERCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PROTECTION_PIERCE_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_PROTECTION_PIERCE, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermCooldownToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_COOLDOWN_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.COOLDOWN_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_COOLDOWN_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.COOLDOWN_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_COOLDOWN_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.COOLDOWN_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_COOLDOWN_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.COOLDOWN_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_COOLDOWN, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermChannelingToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_CHANNELING_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.CHANNELING_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_CHANNELING_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.CHANNELING_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_CHANNELING_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.CHANNELING_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_CHANNELING_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.CHANNELING_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_CHANNELING, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermUninterruptibleChanceToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_UNINTERRUPTIBLE_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.UNINTERRUPTIBLE_CHANCE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_UNINTERRUPTIBLE_CHANCE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.UNINTERRUPTIBLE_CHANCE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_UNINTERRUPTIBLE_CHANCE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.UNINTERRUPTIBLE_CHANCE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_UNINTERRUPTIBLE_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.UNINTERRUPTIBLE_CHANCE_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_UNINTERRUPTIBLE_CHANCE, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermIndependenceDmgToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_INDEPENDENCE_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.INDEPENDENCE_DMG_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_INDEPENDENCE_DMG_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.INDEPENDENCE_DMG_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_INDEPENDENCE_DMG_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.INDEPENDENCE_DMG_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_INDEPENDENCE_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.INDEPENDENCE_DMG_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_INDEPENDENCE_DMG, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermPhysicCritChanceToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_PHYSIC_CRIT_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_CRIT_CHANCE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_PHYSIC_CRIT_CHANCE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_CRIT_CHANCE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_PHYSIC_CRIT_CHANCE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_CRIT_CHANCE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_PHYSIC_CRIT_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_CRIT_CHANCE_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_CRIT_CHANCE, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermPhysicCritDamageToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_PHYSIC_CRIT_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_CRIT_DAMAGE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_PHYSIC_CRIT_DAMAGE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_CRIT_DAMAGE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_PHYSIC_CRIT_DAMAGE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_CRIT_DAMAGE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_PHYSIC_CRIT_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_CRIT_DAMAGE_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_CRIT_DAMAGE, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermMagicDmgToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_MAGIC_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_DMG_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_MAGIC_DMG_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_DMG_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_MAGIC_DMG_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_DMG_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_MAGIC_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_DMG_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_MAGIC_DMG, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermMagicCritChanceToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_MAGIC_CRIT_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_CRIT_CHANCE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_MAGIC_CRIT_CHANCE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_CRIT_CHANCE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_MAGIC_CRIT_CHANCE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_CRIT_CHANCE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_MAGIC_CRIT_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_CRIT_CHANCE_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_MAGIC_CRIT_CHANCE, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermMagicCritDamageToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_MAGIC_CRIT_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_CRIT_DAMAGE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_MAGIC_CRIT_DAMAGE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_CRIT_DAMAGE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_MAGIC_CRIT_DAMAGE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_CRIT_DAMAGE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_MAGIC_CRIT_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_CRIT_DAMAGE_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_MAGIC_CRIT_DAMAGE, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermBackstabDmgToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_BACKSTAB_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BACKSTAB_DMG_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_BACKSTAB_DMG_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BACKSTAB_DMG_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_BACKSTAB_DMG_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BACKSTAB_DMG_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_BACKSTAB_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BACKSTAB_DMG_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_BACKSTAB_DMG, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermPhysicBackstabCritChanceToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_PHYSIC_BACKSTAB_CRIT_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_BACKSTAB_CRIT_CHANCE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_PHYSIC_BACKSTAB_CRIT_CHANCE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_PHYSIC_BACKSTAB_CRIT_CHANCE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_PHYSIC_BACKSTAB_CRIT_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_BACKSTAB_CRIT_CHANCE_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_BACKSTAB_CRIT_CHANCE, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermPhysicBackstabCritDamageToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_PHYSIC_BACKSTAB_CRIT_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_BACKSTAB_CRIT_DAMAGE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_PHYSIC_BACKSTAB_CRIT_DAMAGE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_PHYSIC_BACKSTAB_CRIT_DAMAGE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_PHYSIC_BACKSTAB_CRIT_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_BACKSTAB_CRIT_DAMAGE_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_BACKSTAB_CRIT_DAMAGE, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermMagicBackstabCritChanceToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_MAGIC_BACKSTAB_CRIT_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_BACKSTAB_CRIT_CHANCE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_MAGIC_BACKSTAB_CRIT_CHANCE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_MAGIC_BACKSTAB_CRIT_CHANCE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_BACKSTAB_CRIT_CHANCE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_MAGIC_BACKSTAB_CRIT_CHANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_BACKSTAB_CRIT_CHANCE_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_MAGIC_BACKSTAB_CRIT_CHANCE, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermMagicBackstabCritDamageToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_MAGIC_BACKSTAB_CRIT_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_BACKSTAB_CRIT_DAMAGE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_MAGIC_BACKSTAB_CRIT_DAMAGE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_MAGIC_BACKSTAB_CRIT_DAMAGE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_BACKSTAB_CRIT_DAMAGE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_MAGIC_BACKSTAB_CRIT_DAMAGE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_BACKSTAB_CRIT_DAMAGE_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_MAGIC_BACKSTAB_CRIT_DAMAGE, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermSkillDmgToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_SKILL_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.SKILL_DMG_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_SKILL_DMG_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.SKILL_DMG_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_SKILL_DMG_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.SKILL_DMG_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_SKILL_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.SKILL_DMG_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_SKILL_DMG, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermOverallDmgToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_OVERALL_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.OVERALL_DMG_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_OVERALL_DMG_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.OVERALL_DMG_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_OVERALL_DMG_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.OVERALL_DMG_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_OVERALL_DMG_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.OVERALL_DMG_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_OVERALL_DMG, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermExtraDamageReceivedToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_EXTRA_DAMAGE_RECEIVED_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.EXTRA_DAMAGE_RECEIVED_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_EXTRA_DAMAGE_RECEIVED_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.EXTRA_DAMAGE_RECEIVED_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_EXTRA_DAMAGE_RECEIVED_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.EXTRA_DAMAGE_RECEIVED_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_EXTRA_DAMAGE_RECEIVED_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.EXTRA_DAMAGE_RECEIVED_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_EXTRA_DAMAGE_RECEIVED, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermEffectAreaToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_EFFECT_AREA_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.EFFECT_AREA_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_EFFECT_AREA_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.EFFECT_AREA_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_EFFECT_AREA_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.EFFECT_AREA_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_EFFECT_AREA_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.EFFECT_AREA_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_EFFECT_AREA, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermDurationToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_DURATION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.DURATION_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_DURATION_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.DURATION_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_DURATION_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.DURATION_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_DURATION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.DURATION_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_DURATION, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermDistanceToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_DISTANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.DISTANCE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_DISTANCE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.DISTANCE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_DISTANCE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.DISTANCE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_DISTANCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.DISTANCE_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_DISTANCE, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermStationaryToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_STATIONARY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.STATIONARY_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_STATIONARY_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.STATIONARY_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_STATIONARY_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.STATIONARY_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_STATIONARY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.STATIONARY_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_STATIONARY, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermBeneficialStatusInvincibilityToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_BENEFICIAL_STATUS_INVINCIBILITY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BENEFICIAL_STATUS_INVINCIBILITY_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_BENEFICIAL_STATUS_INVINCIBILITY_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BENEFICIAL_STATUS_INVINCIBILITY_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_BENEFICIAL_STATUS_INVINCIBILITY_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BENEFICIAL_STATUS_INVINCIBILITY_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_BENEFICIAL_STATUS_INVINCIBILITY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BENEFICIAL_STATUS_INVINCIBILITY_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_BENEFICIAL_STATUS_INVINCIBILITY, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermBeneficialStatusSuperArmorToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_BENEFICIAL_STATUS_SUPER_ARMOR_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BENEFICIAL_STATUS_SUPER_ARMOR_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_BENEFICIAL_STATUS_SUPER_ARMOR_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BENEFICIAL_STATUS_SUPER_ARMOR_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_BENEFICIAL_STATUS_SUPER_ARMOR_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BENEFICIAL_STATUS_SUPER_ARMOR_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_BENEFICIAL_STATUS_SUPER_ARMOR_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BENEFICIAL_STATUS_SUPER_ARMOR_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_BENEFICIAL_STATUS_SUPER_ARMOR, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusBleedingToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_BLEEDING_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BLEEDING_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_BLEEDING_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BLEEDING_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_BLEEDING_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BLEEDING_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_BLEEDING_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BLEEDING_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_BLEEDING, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusShockToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SHOCK_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SHOCK_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SHOCK_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SHOCK_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SHOCK_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SHOCK_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SHOCK_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SHOCK_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_SHOCK, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusFreezeToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_FREEZE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_FREEZE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_FREEZE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_FREEZE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_FREEZE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_FREEZE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_FREEZE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_FREEZE_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_FREEZE, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusBurnToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_BURN_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BURN_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_BURN_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BURN_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_BURN_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BURN_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_BURN_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BURN_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_BURN, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusCursedToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_CURSED_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CURSED_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_CURSED_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CURSED_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_CURSED_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CURSED_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_CURSED_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CURSED_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_CURSED, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusDisarmToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_DISARM_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_DISARM_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_DISARM_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_DISARM_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_DISARM_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_DISARM_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_DISARM_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_DISARM_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_DISARM, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusImmobilizationToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_IMMOBILIZATION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_IMMOBILIZATION_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_IMMOBILIZATION_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_IMMOBILIZATION_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_IMMOBILIZATION_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_IMMOBILIZATION_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_IMMOBILIZATION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_IMMOBILIZATION_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_IMMOBILIZATION, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusSleepToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SLEEP_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SLEEP_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SLEEP_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SLEEP_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SLEEP_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SLEEP_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SLEEP_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SLEEP_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_SLEEP, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusConfusionToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_CONFUSION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CONFUSION_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_CONFUSION_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CONFUSION_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_CONFUSION_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CONFUSION_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_CONFUSION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CONFUSION_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_CONFUSION, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusStunToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_STUN_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_STUN_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_STUN_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_STUN_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_STUN_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_STUN_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_STUN_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_STUN_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_STUN, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusSilenceToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch (op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SILENCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SILENCE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SILENCE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SILENCE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SILENCE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SILENCE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SILENCE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SILENCE_BASE;
        }
    }

    //==================================================
    public static void applyPermAbnormalStatusAllResistToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_ALL_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_ALL_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_ALL_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_ALL_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_ALL_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_ALL_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_ALL_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_ALL_RESIST_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_ALL_RESIST, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusBleedingResistToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_BLEEDING_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BLEEDING_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_BLEEDING_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BLEEDING_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_BLEEDING_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BLEEDING_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_BLEEDING_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BLEEDING_RESIST_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_BLEEDING_RESIST, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusShockResistToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SHOCK_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SHOCK_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SHOCK_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SHOCK_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SHOCK_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SHOCK_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SHOCK_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SHOCK_RESIST_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_SHOCK_RESIST, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusFreezeResistToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_FREEZE_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_FREEZE_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_FREEZE_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_FREEZE_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_FREEZE_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_FREEZE_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_FREEZE_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_FREEZE_RESIST_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_FREEZE_RESIST, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusBurnResistToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_BURN_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BURN_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_BURN_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BURN_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_BURN_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BURN_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_BURN_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_BURN_RESIST_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_BURN_RESIST, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusCursedResistToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_CURSED_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CURSED_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_CURSED_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CURSED_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_CURSED_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CURSED_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_CURSED_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_CURSED_RESIST_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_CURSED_RESIST, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusDisarmResistToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_DISARM_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_DISARM_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_DISARM_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_DISARM_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_DISARM_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_DISARM_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_DISARM_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_DISARM_RESIST_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_DISARM_RESIST, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusStunResistToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_STUN_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_STUN_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_STUN_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_STUN_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_STUN_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_STUN_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_STUN_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_STUN_RESIST_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_STUN_RESIST, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAbnormalStatusSilenceResistToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SILENCE_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SILENCE_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SILENCE_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SILENCE_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SILENCE_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SILENCE_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ABNORMAL_STATUS_SILENCE_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ABNORMAL_STATUS_SILENCE_RESIST_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ABNORMAL_STATUS_SILENCE_RESIST, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermAllDmgReductionToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ALL_DMG_REDUCTION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ALL_DMG_REDUCTION_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ALL_DMG_REDUCTION_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ALL_DMG_REDUCTION_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ALL_DMG_REDUCTION_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ALL_DMG_REDUCTION_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ALL_DMG_REDUCTION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ALL_DMG_REDUCTION_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ALL_DMG_REDUCTION, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermPhysicDmgReductionToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_PHYSIC_DMG_REDUCTION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_DMG_REDUCTION_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_PHYSIC_DMG_REDUCTION_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_DMG_REDUCTION_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_PHYSIC_DMG_REDUCTION_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_DMG_REDUCTION_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_PHYSIC_DMG_REDUCTION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.PHYSIC_DMG_REDUCTION_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_PHYSIC_DMG_REDUCTION, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermMagicDmgReductionToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_MAGIC_DMG_REDUCTION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_DMG_REDUCTION_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_MAGIC_DMG_REDUCTION_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_DMG_REDUCTION_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_MAGIC_DMG_REDUCTION_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_DMG_REDUCTION_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_MAGIC_DMG_REDUCTION_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.MAGIC_DMG_REDUCTION_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_MAGIC_DMG_REDUCTION, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermElementLightToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ELEMENT_LIGHT_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_LIGHT_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ELEMENT_LIGHT_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_LIGHT_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ELEMENT_LIGHT_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_LIGHT_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ELEMENT_LIGHT_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_LIGHT_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_LIGHT, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermElementDarknessToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ELEMENT_DARKNESS_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_DARKNESS_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ELEMENT_DARKNESS_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_DARKNESS_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ELEMENT_DARKNESS_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_DARKNESS_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ELEMENT_DARKNESS_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_DARKNESS_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_DARKNESS, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermElementMetalToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ELEMENT_METAL_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_METAL_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ELEMENT_METAL_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_METAL_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ELEMENT_METAL_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_METAL_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ELEMENT_METAL_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_METAL_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_METAL, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermElementWoodToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ELEMENT_WOOD_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WOOD_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ELEMENT_WOOD_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WOOD_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ELEMENT_WOOD_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WOOD_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ELEMENT_WOOD_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WOOD_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_WOOD, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermElementEarthToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ELEMENT_EARTH_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_EARTH_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ELEMENT_EARTH_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_EARTH_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ELEMENT_EARTH_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_EARTH_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ELEMENT_EARTH_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_EARTH_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_EARTH, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermElementFireToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ELEMENT_FIRE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_FIRE_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ELEMENT_FIRE_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_FIRE_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ELEMENT_FIRE_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_FIRE_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ELEMENT_FIRE_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_FIRE_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_FIRE, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermElementAirToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ELEMENT_AIR_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_AIR_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ELEMENT_AIR_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_AIR_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ELEMENT_AIR_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_AIR_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ELEMENT_AIR_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_AIR_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_AIR, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermElementWaterToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ELEMENT_WATER_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WATER_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ELEMENT_WATER_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WATER_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ELEMENT_WATER_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WATER_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ELEMENT_WATER_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WATER_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_WATER, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermElementLightResistToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ELEMENT_LIGHT_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_LIGHT_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ELEMENT_LIGHT_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_LIGHT_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ELEMENT_LIGHT_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_LIGHT_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ELEMENT_LIGHT_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_LIGHT_RESIST_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_LIGHT_RESIST, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermElementDarknessResistToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ELEMENT_DARKNESS_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_DARKNESS_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ELEMENT_DARKNESS_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_DARKNESS_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ELEMENT_DARKNESS_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_DARKNESS_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ELEMENT_DARKNESS_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_DARKNESS_RESIST_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_DARKNESS_RESIST, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermElementMetalResistToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ELEMENT_METAL_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_METAL_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ELEMENT_METAL_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_METAL_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ELEMENT_METAL_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_METAL_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ELEMENT_METAL_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_METAL_RESIST_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_METAL_RESIST, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermElementWoodResistToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ELEMENT_WOOD_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WOOD_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ELEMENT_WOOD_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WOOD_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ELEMENT_WOOD_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WOOD_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ELEMENT_WOOD_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WOOD_RESIST_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_WOOD_RESIST, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermElementEarthResistToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ELEMENT_EARTH_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_EARTH_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ELEMENT_EARTH_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_EARTH_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ELEMENT_EARTH_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_EARTH_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ELEMENT_EARTH_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_EARTH_RESIST_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_EARTH_RESIST, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermElementFireResistToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ELEMENT_FIRE_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_FIRE_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ELEMENT_FIRE_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_FIRE_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ELEMENT_FIRE_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_FIRE_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ELEMENT_FIRE_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_FIRE_RESIST_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_FIRE_RESIST, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermElementAirResistToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ELEMENT_AIR_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_AIR_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ELEMENT_AIR_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_AIR_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ELEMENT_AIR_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_AIR_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ELEMENT_AIR_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_AIR_RESIST_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_AIR_RESIST, amount, op, SkillID, action);
    }

    //==================================================
    public static void applyPermElementWaterResistToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
        Identifier switchUUID;
        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
        String attrName;
        switch(op) {
            case EntityAttributeModifier.Operation.ADD_VALUE:
                switchUUID = SBS_UUID_PERM_ELEMENT_WATER_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WATER_RESIST_BASE;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
                switchUUID = SBS_UUID_PERM_ELEMENT_WATER_RESIST_MULTIPLY_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WATER_RESIST_MULTIPLIER;
                break;
            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
                switchUUID = SBS_UUID_PERM_ELEMENT_WATER_RESIST_MULTIPLY_TOTAL;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WATER_RESIST_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
                break;
            default:
                switchUUID = SBS_UUID_PERM_ELEMENT_WATER_RESIST_BASE;
                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.ELEMENT_WATER_RESIST_BASE;
        }

        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_ELEMENT_WATER_RESIST, amount, op, SkillID, action);
    }

    //==================================================
//    public static void applyPermBeingAttackedToEntity(LivingEntity entity, double amount, int operator, String SkillID, MethodAction action) {
//        Identifier switchUUID;
//        EntityAttributeModifier.Operation op = ConvertIntOp2AttrOp(operator);
//        String attrName;
//        switch(op) {
//            case EntityAttributeModifier.Operation.ADD_VALUE:
//                switchUUID = SBS_UUID_PERM_BEING_ATTACKED_BASE;
//                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BEING_ATTACKED_BASE;
//                break;
//            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE:
//                switchUUID = SBS_UUID_PERM_BEING_ATTACKED_MULTIPLY_BASE;
//                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BEING_ATTACKED_MULTIPLIER;
//                break;
//            case EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL:
//                switchUUID = SBS_UUID_PERM_BEING_ATTACKED_MULTIPLY_TOTAL;
//                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BEING_ATTACKED_MULTIPLIER + AttributeServerPlayerStatusQueryMethods.MULTIPLIER_TOTAL_POSTFIX;
//                break;
//            default:
//                switchUUID = SBS_UUID_PERM_BEING_ATTACKED_BASE;
//                attrName = AttributeServerPlayerStatusQueryMethods.TEMPORARY_PREFIX + AttributeServerPlayerStatusQueryMethods.BEING_ATTACKED_BASE;
//        }
//
//        applyPermAttrToEntity(entity, switchUUID, attrName, SBSAttributes.SBS_ATTRIBUTE_BEING_ATTACKED, amount, op, SkillID, action);
//    }
}

package net.dingdingduang.somebasicskills.sbsattributes;

import net.minecraft.entity.attribute.EntityAttribute;

public class SBSAttributes {
//    public static final EntityAttribute SBS_ATTRIBUTE_COOLDOWN = register("sbsgeneric.cooldown", new SBSAttribute("sbsattribute.cooldown", 0.0));
//
//    private static EntityAttribute register(String pId, EntityAttribute pAttribute) {
//        return (Attribute) Registry.register(BuiltInRegistries.ATTRIBUTE, pId, pAttribute);
//    }

    public static final EntityAttribute SBS_ATTRIBUTE_MAX_MP = new SBSAttribute("sbsattribute.max_mp", 0.0);

    public static final EntityAttribute SBS_ATTRIBUTE_ARMOR_PIERCE = new SBSAttribute("sbsattribute.armor_pierce", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_PROTECTION_PIERCE = new SBSAttribute("sbsattribute.protection_pierce", 0.0);

    public static final EntityAttribute SBS_ATTRIBUTE_COOLDOWN = new SBSAttribute("sbsattribute.cooldown", 0.0);

    public static final EntityAttribute SBS_ATTRIBUTE_CHANNELING = new SBSAttribute("sbsattribute.channeling", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_UNINTERRUPTIBLE_CHANCE = new SBSAttribute("sbsattribute.uninterruptible_chance", 0.0);

    public static final EntityAttribute SBS_ATTRIBUTE_INDEPENDENCE_DMG = new SBSAttribute("sbsattribute.independence_dmg", 0.0);

    public static final EntityAttribute SBS_ATTRIBUTE_PHYSIC_CRIT_CHANCE = new SBSAttribute("sbsattribute.physic_crit_chance", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_PHYSIC_CRIT_DAMAGE = new SBSAttribute("sbsattribute.physic_crit_damage", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_MAGIC_DMG = new SBSAttribute("sbsattribute.magic_dmg", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_MAGIC_CRIT_CHANCE = new SBSAttribute("sbsattribute.magic_crit_chance", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_MAGIC_CRIT_DAMAGE = new SBSAttribute("sbsattribute.magic_crit_damage", 0.0);

    public static final EntityAttribute SBS_ATTRIBUTE_BACKSTAB_DMG = new SBSAttribute("sbsattribute.backstab_dmg", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_PHYSIC_BACKSTAB_CRIT_CHANCE = new SBSAttribute("sbsattribute.physic_backstab_crit_chance", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_PHYSIC_BACKSTAB_CRIT_DAMAGE = new SBSAttribute("sbsattribute.physic_backstab_crit_damage", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_MAGIC_BACKSTAB_CRIT_CHANCE = new SBSAttribute("sbsattribute.magic_backstab_crit_chance", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_MAGIC_BACKSTAB_CRIT_DAMAGE = new SBSAttribute("sbsattribute.magic_backstab_crit_damage", 0.0);

    public static final EntityAttribute SBS_ATTRIBUTE_SKILL_DMG = new SBSAttribute("sbsattribute.skill_dmg", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_OVERALL_DMG = new SBSAttribute("sbsattribute.overall_dmg", 0.0);

    public static final EntityAttribute SBS_ATTRIBUTE_EXTRA_DAMAGE_RECEIVED = new SBSAttribute("sbsattribute.extra_damage_received", 0.0);

    public static final EntityAttribute SBS_ATTRIBUTE_EFFECT_AREA = new SBSAttribute("sbsattribute.effect_area", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_DURATION = new SBSAttribute("sbsattribute.duration", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_DISTANCE = new SBSAttribute("sbsattribute.distance", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_STATIONARY = new SBSAttribute("sbsattribute.stationary", 0.0);

    public static final EntityAttribute SBS_ATTRIBUTE_BENEFICIAL_STATUS_INVINCIBILITY = new SBSAttribute("sbsattribute.beneficial_status_invincibility", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_BENEFICIAL_STATUS_SUPER_ARMOR = new SBSAttribute("sbsattribute.beneficial_status_super_armor", 0.0);

    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_BLEEDING = new SBSAttribute("sbsattribute.abnormal_status_bleeding", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_SHOCK = new SBSAttribute("sbsattribute.abnormal_status_shock", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_FREEZE = new SBSAttribute("sbsattribute.abnormal_status_freeze", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_BURN = new SBSAttribute("sbsattribute.abnormal_status_burn", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_CURSED = new SBSAttribute("sbsattribute.abnormal_status_cursed", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_DISARM = new SBSAttribute("sbsattribute.abnormal_status_disarm", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_IMMOBILIZATION = new SBSAttribute("sbsattribute.abnormal_status_immobilization", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_SLEEP = new SBSAttribute("sbsattribute.abnormal_status_sleep", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_CONFUSION = new SBSAttribute("sbsattribute.abnormal_status_confusion", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_STUN = new SBSAttribute("sbsattribute.abnormal_status_stun", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_SILENCE = new SBSAttribute("sbsattribute.abnormal_status_silence", 0.0);

    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_ALL_RESIST = new SBSAttribute("sbsattribute.abnormal_status_all_resist", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_BLEEDING_RESIST = new SBSAttribute("sbsattribute.abnormal_status_bleeding_resist", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_SHOCK_RESIST = new SBSAttribute("sbsattribute.abnormal_status_shock_resist", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_FREEZE_RESIST = new SBSAttribute("sbsattribute.abnormal_status_freeze_resist", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_BURN_RESIST = new SBSAttribute("sbsattribute.abnormal_status_burn_resist", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_CURSED_RESIST = new SBSAttribute("sbsattribute.abnormal_status_cursed_resist", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_DISARM_RESIST = new SBSAttribute("sbsattribute.abnormal_status_disarm_resist", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_STUN_RESIST = new SBSAttribute("sbsattribute.abnormal_status_stun_resist", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ABNORMAL_STATUS_SILENCE_RESIST = new SBSAttribute("sbsattribute.abnormal_status_silence_resist", 0.0);

    public static final EntityAttribute SBS_ATTRIBUTE_ALL_DMG_REDUCTION = new SBSAttribute("sbsattribute.all_dmg_reduction", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_PHYSIC_DMG_REDUCTION = new SBSAttribute("sbsattribute.physic_dmg_reduction", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_MAGIC_DMG_REDUCTION = new SBSAttribute("sbsattribute.magic_dmg_reduction", 0.0);

    public static final EntityAttribute SBS_ATTRIBUTE_ELEMENT_LIGHT = new SBSAttribute("sbsattribute.element_light", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ELEMENT_DARKNESS = new SBSAttribute("sbsattribute.element_darkness", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ELEMENT_METAL = new SBSAttribute("sbsattribute.element_metal", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ELEMENT_WOOD = new SBSAttribute("sbsattribute.element_wood", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ELEMENT_EARTH = new SBSAttribute("sbsattribute.element_earth", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ELEMENT_FIRE = new SBSAttribute("sbsattribute.element_fire", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ELEMENT_AIR = new SBSAttribute("sbsattribute.element_air", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ELEMENT_WATER = new SBSAttribute("sbsattribute.element_water", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ELEMENT_LIGHT_RESIST = new SBSAttribute("sbsattribute.element_light_resist", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ELEMENT_DARKNESS_RESIST = new SBSAttribute("sbsattribute.element_darkness_resist", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ELEMENT_METAL_RESIST = new SBSAttribute("sbsattribute.element_metal_resist", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ELEMENT_WOOD_RESIST = new SBSAttribute("sbsattribute.element_wood_resist", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ELEMENT_EARTH_RESIST = new SBSAttribute("sbsattribute.element_earth_resist", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ELEMENT_FIRE_RESIST = new SBSAttribute("sbsattribute.element_fire_resist", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ELEMENT_AIR_RESIST = new SBSAttribute("sbsattribute.element_air_resist", 0.0);
    public static final EntityAttribute SBS_ATTRIBUTE_ELEMENT_WATER_RESIST = new SBSAttribute("sbsattribute.element_water_resist", 0.0);

    public static final EntityAttribute SBS_ATTRIBUTE_BEING_ATTACKED = new SBSAttribute("sbsattribute.being_attacked", 0.0);
}

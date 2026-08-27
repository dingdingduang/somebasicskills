package net.dingdingduang.somebasicskills.sbsattributes;

import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;

public class SBSAttributes {
//    public static final DeferredRegister<EntityAttribute> ATTRIBUTE_REGISTRY = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, Constants.MOD_ID);

    //TODO: fix attribute null?
    private static RegistryEntry<EntityAttribute> register(String pId, EntityAttribute pAttribute) {

//        RegistryAttributeHolder.get(Registries.ATTRIBUTE).addAttribute(RegistryAttribute.MODDED).addAttribute(RegistryAttribute.SYNCED);

//        RegistryKeys.ATTRIBUTE;
//        return RegistryEntry.of(Registry.register(Registries.ATTRIBUTE, getMCResourceLocation(Constants.MOD_ID, pId), pAttribute));
        return Registry.registerReference(Registries.ATTRIBUTE, getMCResourceLocation(Constants.MOD_ID,pId), pAttribute);
    }

    private static EntityAttribute getMCAttribute(String pDescriptionID, double pDefaultVal) {
//        return new RangedAttribute(pDescriptionID, pDefaultVal, 0, 65535);
        return new SBSAttribute(pDescriptionID, pDefaultVal);
    }

//    public static final RegistryEntry<EntityAttribute SBS_ATTRIBUTE_ATTACK_DAMAGE = register("attack.damage", getMCAttribute("attack.damage", 0) );
//    public static final RegistryEntry<EntityAttribute SBS_ATTRIBUTE_ATTACK_KNOCKBACK = register("attack.knockback", getMCAttribute("attack.knockback", 0) );
//    public static final RegistryEntry<EntityAttribute SBS_ATTRIBUTE_KNOCKBACK_RESIST = register("knockback.resist", getMCAttribute("knockback.resist", 0) );
//    public static final RegistryEntry<EntityAttribute SBS_ATTRIBUTE_ASPD = register("aspd", getMCAttribute("aspd", 0) );
//    public static final RegistryEntry<EntityAttribute SBS_ATTRIBUTE_MOVS = register("movs", getMCAttribute("movs", 0) );
//    public static final RegistryEntry<EntityAttribute SBS_ATTRIBUTE_MAX_HP = register("max.hp", getMCAttribute("max.hp", 0) );
//    public static final RegistryEntry<EntityAttribute SBS_ATTRIBUTE_ARMOR = register("armor", getMCAttribute("armor", 0) );
//    public static final RegistryEntry<EntityAttribute SBS_ATTRIBUTE_ARMOR_TOUGHNESS = register("armor.toughness", getMCAttribute("armor.toughness", 0) );
//    public static final RegistryEntry<EntityAttribute SBS_ATTRIBUTE_LUCK = register("luck", getMCAttribute("luck", 0) );

    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_MAX_MP = register("max.mp", getMCAttribute("max.mp", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ARMOR_PIERCE = register("armor.pierce", getMCAttribute("armor.pierce", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_PROTECTION_PIERCE = register("protection.pierce", getMCAttribute("protection.pierce", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_COOLDOWN = register("cooldown", getMCAttribute("cooldown", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_CHANNELING = register("channeling", getMCAttribute("channeling", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_UNINTERRUPTIBLE_CHANCE = register("uninterruptible.chance", getMCAttribute("uninterruptible.chance", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_INDEPENDENCE_DMG = register("independence.dmg", getMCAttribute("independence.dmg", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_PHYSIC_CRIT_CHANCE = register("physic.crit.chance", getMCAttribute("physic.crit.chance", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_PHYSIC_CRIT_DAMAGE = register("physic.crit.damage", getMCAttribute("physic.crit.damage", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_MAGIC_DMG = register("magic.dmg", getMCAttribute("magic.dmg", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_MAGIC_CRIT_CHANCE = register("magic.crit.chance", getMCAttribute("magic.crit.chance", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_MAGIC_CRIT_DAMAGE = register("magic.crit.damage", getMCAttribute("magic.crit.damage", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_BACKSTAB_DMG = register("backstab.dmg", getMCAttribute("backstab.dmg", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_PHYSIC_BACKSTAB_CRIT_CHANCE = register("physic.backstab.crit.chance", getMCAttribute("physic.backstab.crit.chance", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_PHYSIC_BACKSTAB_CRIT_DAMAGE = register("physic.backstab.crit.damage", getMCAttribute("physic.backstab.crit.damage", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_MAGIC_BACKSTAB_CRIT_CHANCE = register("magic.backstab.crit.chance", getMCAttribute("magic.backstab.crit.chance", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_MAGIC_BACKSTAB_CRIT_DAMAGE = register("magic.backstab.crit.damage", getMCAttribute("magic.backstab.crit.damage", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_SKILL_DMG = register("skill.dmg", getMCAttribute("skill.dmg", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_OVERALL_DMG = register("overall.dmg", getMCAttribute("overall.dmg", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_EXTRA_DAMAGE_RECEIVED = register("extra.damage.received", getMCAttribute("extra.damage.received", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_EFFECT_AREA = register("effect.area", getMCAttribute("effect.area", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_DURATION = register("duration", getMCAttribute("duration", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_DISTANCE = register("distance", getMCAttribute("distance", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_STATIONARY = register("stationary", getMCAttribute("stationary", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_BENEFICIAL_STATUS_INVINCIBILITY = register("beneficial.status.invincibility", getMCAttribute("beneficial.status.invincibility", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_BENEFICIAL_STATUS_SUPER_ARMOR = register("beneficial.status.super.armor", getMCAttribute("beneficial.status.super.armor", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_BLEEDING = register("abnormal.status.bleeding", getMCAttribute("abnormal.status.bleeding", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_SHOCK = register("abnormal.status.shock", getMCAttribute("abnormal.status.shock", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_FREEZE = register("abnormal.status.freeze", getMCAttribute("abnormal.status.freeze", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_BURN = register("abnormal.status.burn", getMCAttribute("abnormal.status.burn", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_CURSED = register("abnormal.status.cursed", getMCAttribute("abnormal.status.cursed", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_DISARM = register("abnormal.status.disarm", getMCAttribute("abnormal.status.disarm", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_IMMOBILIZATION = register("abnormal.status.immobilization", getMCAttribute("abnormal.status.immobilization", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_SLEEP = register("abnormal.status.sleep", getMCAttribute("abnormal.status.sleep", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_CONFUSION = register("abnormal.status.confusion", getMCAttribute("abnormal.status.confusion", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_STUN = register("abnormal.status.stun", getMCAttribute("abnormal.status.stun", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_SILENCE = register("abnormal.status.silence", getMCAttribute("abnormal.status.silence", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_ALL_RESIST = register("abnormal.status.all.resist", getMCAttribute("abnormal.status.all.resist", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_BLEEDING_RESIST = register("abnormal.status.bleeding.resist", getMCAttribute("abnormal.status.bleeding.resist", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_SHOCK_RESIST = register("abnormal.status.shock.resist", getMCAttribute("abnormal.status.shock.resist", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_FREEZE_RESIST = register("abnormal.status.freeze.resist", getMCAttribute("abnormal.status.freeze.resist", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_BURN_RESIST = register("abnormal.status.burn.resist", getMCAttribute("abnormal.status.burn.resist", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_CURSED_RESIST = register("abnormal.status.cursed.resist", getMCAttribute("abnormal.status.cursed.resist", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_DISARM_RESIST = register("abnormal.status.disarm.resist", getMCAttribute("abnormal.status.disarm.resist", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_STUN_RESIST = register("abnormal.status.stun.resist", getMCAttribute("abnormal.status.stun.resist", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_SILENCE_RESIST = register("abnormal.status.silence.resist", getMCAttribute("abnormal.status.silence.resist", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ALL_DMG_REDUCTION = register("all.dmg.reduction", getMCAttribute("all.dmg.reduction", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_PHYSIC_DMG_REDUCTION = register("physic.dmg.reduction", getMCAttribute("physic.dmg.reduction", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_MAGIC_DMG_REDUCTION = register("magic.dmg.reduction", getMCAttribute("magic.dmg.reduction", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ELEMENT_LIGHT = register("element.light", getMCAttribute("element.light", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ELEMENT_DARKNESS = register("element.darkness", getMCAttribute("element.darkness", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ELEMENT_METAL = register("element.metal", getMCAttribute("element.metal", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ELEMENT_WOOD = register("element.wood", getMCAttribute("element.wood", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ELEMENT_EARTH = register("element.earth", getMCAttribute("element.earth", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ELEMENT_FIRE = register("element.fire", getMCAttribute("element.fire", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ELEMENT_AIR = register("element.air", getMCAttribute("element.air", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ELEMENT_WATER = register("element.water", getMCAttribute("element.water", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ELEMENT_LIGHT_RESIST = register("element.light.resist", getMCAttribute("element.light.resist", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ELEMENT_DARKNESS_RESIST = register("element.darkness.resist", getMCAttribute("element.darkness.resist", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ELEMENT_METAL_RESIST = register("element.metal.resist", getMCAttribute("element.metal.resist", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ELEMENT_WOOD_RESIST = register("element.wood.resist", getMCAttribute("element.wood.resist", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ELEMENT_EARTH_RESIST = register("element.earth.resist", getMCAttribute("element.earth.resist", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ELEMENT_FIRE_RESIST = register("element.fire.resist", getMCAttribute("element.fire.resist", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ELEMENT_AIR_RESIST = register("element.air.resist", getMCAttribute("element.air.resist", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_ELEMENT_WATER_RESIST = register("element.water.resist", getMCAttribute("element.water.resist", 0) );
    public static final RegistryEntry<EntityAttribute> SBS_ATTRIBUTE_BEING_ATTACKED = register("being.attacked", getMCAttribute("being.attacked", 0) );
}

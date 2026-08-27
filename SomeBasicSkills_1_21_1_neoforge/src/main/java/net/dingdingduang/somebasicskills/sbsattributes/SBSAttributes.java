package net.dingdingduang.somebasicskills.sbsattributes;

import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;

public class SBSAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTE_REGISTRY = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, Constants.MOD_ID);

    public static void eventBusRegister(IEventBus eventBus) {
        ATTRIBUTE_REGISTRY.register(eventBus);
    }

    private static Holder<Attribute> register(String pId, Attribute pAttribute) {
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, getMCResourceLocation(Constants.MOD_ID, pId), pAttribute);
//        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, ResourceLocation.withDefaultNamespace(pId), pAttribute);
    }

    private static Attribute getMCAttribute(String pDescriptionID, double pDefaultVal) {
//        return new RangedAttribute(pDescriptionID, pDefaultVal, 0, 65535);
        return new SBSAttribute(pDescriptionID, pDefaultVal);
    }

//    public static final Holder<Attribute> SBS_ATTRIBUTE_ATTACK_DAMAGE = ATTRIBUTE_REGISTRY.register("attack.damage", () -> getMCAttribute("attack.damage", 0) );
//    public static final Holder<Attribute> SBS_ATTRIBUTE_ATTACK_KNOCKBACK = ATTRIBUTE_REGISTRY.register("attack.knockback", () -> getMCAttribute("attack.knockback", 0) );
//    public static final Holder<Attribute> SBS_ATTRIBUTE_KNOCKBACK_RESIST = ATTRIBUTE_REGISTRY.register("knockback.resist", () -> getMCAttribute("knockback.resist", 0) );
//    public static final Holder<Attribute> SBS_ATTRIBUTE_ASPD = ATTRIBUTE_REGISTRY.register("aspd", () -> getMCAttribute("aspd", 0) );
//    public static final Holder<Attribute> SBS_ATTRIBUTE_MOVS = ATTRIBUTE_REGISTRY.register("movs", () -> getMCAttribute("movs", 0) );
//    public static final Holder<Attribute> SBS_ATTRIBUTE_MAX_HP = ATTRIBUTE_REGISTRY.register("max.hp", () -> getMCAttribute("max.hp", 0) );
//    public static final Holder<Attribute> SBS_ATTRIBUTE_ARMOR = ATTRIBUTE_REGISTRY.register("armor", () -> getMCAttribute("armor", 0) );
//    public static final Holder<Attribute> SBS_ATTRIBUTE_ARMOR_TOUGHNESS = ATTRIBUTE_REGISTRY.register("armor.toughness", () -> getMCAttribute("armor.toughness", 0) );
//    public static final Holder<Attribute> SBS_ATTRIBUTE_LUCK = ATTRIBUTE_REGISTRY.register("luck", () -> getMCAttribute("luck", 0) );

    public static final Holder<Attribute> SBS_ATTRIBUTE_MAX_MP = ATTRIBUTE_REGISTRY.register("max.mp", () -> getMCAttribute("max.mp", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ARMOR_PIERCE = ATTRIBUTE_REGISTRY.register("armor.pierce", () -> getMCAttribute("armor.pierce", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_PROTECTION_PIERCE = ATTRIBUTE_REGISTRY.register("protection.pierce", () -> getMCAttribute("protection.pierce", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_COOLDOWN = ATTRIBUTE_REGISTRY.register("cooldown", () -> getMCAttribute("cooldown", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_CHANNELING = ATTRIBUTE_REGISTRY.register("channeling", () -> getMCAttribute("channeling", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_UNINTERRUPTIBLE_CHANCE = ATTRIBUTE_REGISTRY.register("uninterruptible.chance", () -> getMCAttribute("uninterruptible.chance", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_INDEPENDENCE_DMG = ATTRIBUTE_REGISTRY.register("independence.dmg", () -> getMCAttribute("independence.dmg", 0) );

    public static final Holder<Attribute> SBS_ATTRIBUTE_PHYSIC_CRIT_CHANCE = ATTRIBUTE_REGISTRY.register("physic.crit.chance", () -> getMCAttribute("physic.crit.chance", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_PHYSIC_CRIT_DAMAGE = ATTRIBUTE_REGISTRY.register("physic.crit.damage", () -> getMCAttribute("physic.crit.damage", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_MAGIC_DMG = ATTRIBUTE_REGISTRY.register("magic.dmg", () -> getMCAttribute("magic.dmg", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_MAGIC_CRIT_CHANCE = ATTRIBUTE_REGISTRY.register("magic.crit.chance", () -> getMCAttribute("magic.crit.chance", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_MAGIC_CRIT_DAMAGE = ATTRIBUTE_REGISTRY.register("magic.crit.damage", () -> getMCAttribute("magic.crit.damage", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_BACKSTAB_DMG = ATTRIBUTE_REGISTRY.register("backstab.dmg", () -> getMCAttribute("backstab.dmg", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_PHYSIC_BACKSTAB_CRIT_CHANCE = ATTRIBUTE_REGISTRY.register("physic.backstab.crit.chance", () -> getMCAttribute("physic.backstab.crit.chance", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_PHYSIC_BACKSTAB_CRIT_DAMAGE = ATTRIBUTE_REGISTRY.register("physic.backstab.crit.damage", () -> getMCAttribute("physic.backstab.crit.damage", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_MAGIC_BACKSTAB_CRIT_CHANCE = ATTRIBUTE_REGISTRY.register("magic.backstab.crit.chance", () -> getMCAttribute("magic.backstab.crit.chance", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_MAGIC_BACKSTAB_CRIT_DAMAGE = ATTRIBUTE_REGISTRY.register("magic.backstab.crit.damage", () -> getMCAttribute("magic.backstab.crit.damage", 0) );

    public static final Holder<Attribute> SBS_ATTRIBUTE_SKILL_DMG = ATTRIBUTE_REGISTRY.register("skill.dmg", () -> getMCAttribute("skill.dmg", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_OVERALL_DMG = ATTRIBUTE_REGISTRY.register("overall.dmg", () -> getMCAttribute("overall.dmg", 0) );

    public static final Holder<Attribute> SBS_ATTRIBUTE_EXTRA_DAMAGE_RECEIVED = ATTRIBUTE_REGISTRY.register("extra.damage.received", () -> getMCAttribute("extra.damage.received", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_EFFECT_AREA = ATTRIBUTE_REGISTRY.register("effect.area", () -> getMCAttribute("effect.area", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_DURATION = ATTRIBUTE_REGISTRY.register("duration", () -> getMCAttribute("duration", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_DISTANCE = ATTRIBUTE_REGISTRY.register("distance", () -> getMCAttribute("distance", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_STATIONARY = ATTRIBUTE_REGISTRY.register("stationary", () -> getMCAttribute("stationary", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_BENEFICIAL_STATUS_INVINCIBILITY = ATTRIBUTE_REGISTRY.register("beneficial.status.invincibility", () -> getMCAttribute("beneficial.status.invincibility", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_BENEFICIAL_STATUS_SUPER_ARMOR = ATTRIBUTE_REGISTRY.register("beneficial.status.super.armor", () -> getMCAttribute("beneficial.status.super.armor", 0) );

    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_BLEEDING = ATTRIBUTE_REGISTRY.register("abnormal.status.bleeding", () -> getMCAttribute("abnormal.status.bleeding", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_SHOCK = ATTRIBUTE_REGISTRY.register("abnormal.status.shock", () -> getMCAttribute("abnormal.status.shock", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_FREEZE = ATTRIBUTE_REGISTRY.register("abnormal.status.freeze", () -> getMCAttribute("abnormal.status.freeze", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_BURN = ATTRIBUTE_REGISTRY.register("abnormal.status.burn", () -> getMCAttribute("abnormal.status.burn", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_CURSED = ATTRIBUTE_REGISTRY.register("abnormal.status.cursed", () -> getMCAttribute("abnormal.status.cursed", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_DISARM = ATTRIBUTE_REGISTRY.register("abnormal.status.disarm", () -> getMCAttribute("abnormal.status.disarm", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_IMMOBILIZATION = ATTRIBUTE_REGISTRY.register("abnormal.status.immobilization", () -> getMCAttribute("abnormal.status.immobilization", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_SLEEP = ATTRIBUTE_REGISTRY.register("abnormal.status.sleep", () -> getMCAttribute("abnormal.status.sleep", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_CONFUSION = ATTRIBUTE_REGISTRY.register("abnormal.status.confusion", () -> getMCAttribute("abnormal.status.confusion", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_STUN = ATTRIBUTE_REGISTRY.register("abnormal.status.stun", () -> getMCAttribute("abnormal.status.stun", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_SILENCE = ATTRIBUTE_REGISTRY.register("abnormal.status.silence", () -> getMCAttribute("abnormal.status.silence", 0) );

    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_ALL_RESIST = ATTRIBUTE_REGISTRY.register("abnormal.status.all.resist", () -> getMCAttribute("abnormal.status.all.resist", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_BLEEDING_RESIST = ATTRIBUTE_REGISTRY.register("abnormal.status.bleeding.resist", () -> getMCAttribute("abnormal.status.bleeding.resist", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_SHOCK_RESIST = ATTRIBUTE_REGISTRY.register("abnormal.status.shock.resist", () -> getMCAttribute("abnormal.status.shock.resist", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_FREEZE_RESIST = ATTRIBUTE_REGISTRY.register("abnormal.status.freeze.resist", () -> getMCAttribute("abnormal.status.freeze.resist", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_BURN_RESIST = ATTRIBUTE_REGISTRY.register("abnormal.status.burn.resist", () -> getMCAttribute("abnormal.status.burn.resist", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_CURSED_RESIST = ATTRIBUTE_REGISTRY.register("abnormal.status.cursed.resist", () -> getMCAttribute("abnormal.status.cursed.resist", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_DISARM_RESIST = ATTRIBUTE_REGISTRY.register("abnormal.status.disarm.resist", () -> getMCAttribute("abnormal.status.disarm.resist", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_STUN_RESIST = ATTRIBUTE_REGISTRY.register("abnormal.status.stun.resist", () -> getMCAttribute("abnormal.status.stun.resist", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ABNORMAL_STATUS_SILENCE_RESIST = ATTRIBUTE_REGISTRY.register("abnormal.status.silence.resist", () -> getMCAttribute("abnormal.status.silence.resist", 0) );

    public static final Holder<Attribute> SBS_ATTRIBUTE_ALL_DMG_REDUCTION = ATTRIBUTE_REGISTRY.register("all.dmg.reduction", () -> getMCAttribute("all.dmg.reduction", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_PHYSIC_DMG_REDUCTION = ATTRIBUTE_REGISTRY.register("physic.dmg.reduction", () -> getMCAttribute("physic.dmg.reduction", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_MAGIC_DMG_REDUCTION = ATTRIBUTE_REGISTRY.register("magic.dmg.reduction", () -> getMCAttribute("magic.dmg.reduction", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ELEMENT_LIGHT = ATTRIBUTE_REGISTRY.register("element.light", () -> getMCAttribute("element.light", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ELEMENT_DARKNESS = ATTRIBUTE_REGISTRY.register("element.darkness", () -> getMCAttribute("element.darkness", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ELEMENT_METAL = ATTRIBUTE_REGISTRY.register("element.metal", () -> getMCAttribute("element.metal", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ELEMENT_WOOD = ATTRIBUTE_REGISTRY.register("element.wood", () -> getMCAttribute("element.wood", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ELEMENT_EARTH = ATTRIBUTE_REGISTRY.register("element.earth", () -> getMCAttribute("element.earth", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ELEMENT_FIRE = ATTRIBUTE_REGISTRY.register("element.fire", () -> getMCAttribute("element.fire", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ELEMENT_AIR = ATTRIBUTE_REGISTRY.register("element.air", () -> getMCAttribute("element.air", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ELEMENT_WATER = ATTRIBUTE_REGISTRY.register("element.water", () -> getMCAttribute("element.water", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ELEMENT_LIGHT_RESIST = ATTRIBUTE_REGISTRY.register("element.light.resist", () -> getMCAttribute("element.light.resist", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ELEMENT_DARKNESS_RESIST = ATTRIBUTE_REGISTRY.register("element.darkness.resist", () -> getMCAttribute("element.darkness.resist", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ELEMENT_METAL_RESIST = ATTRIBUTE_REGISTRY.register("element.metal.resist", () -> getMCAttribute("element.metal.resist", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ELEMENT_WOOD_RESIST = ATTRIBUTE_REGISTRY.register("element.wood.resist", () -> getMCAttribute("element.wood.resist", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ELEMENT_EARTH_RESIST = ATTRIBUTE_REGISTRY.register("element.earth.resist", () -> getMCAttribute("element.earth.resist", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ELEMENT_FIRE_RESIST = ATTRIBUTE_REGISTRY.register("element.fire.resist", () -> getMCAttribute("element.fire.resist", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ELEMENT_AIR_RESIST = ATTRIBUTE_REGISTRY.register("element.air.resist", () -> getMCAttribute("element.air.resist", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_ELEMENT_WATER_RESIST = ATTRIBUTE_REGISTRY.register("element.water.resist", () -> getMCAttribute("element.water.resist", 0) );
    public static final Holder<Attribute> SBS_ATTRIBUTE_BEING_ATTACKED = ATTRIBUTE_REGISTRY.register("being.attacked", () -> getMCAttribute("being.attacked", 0) );
}

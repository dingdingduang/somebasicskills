package net.dingdingduang.somebasicskills.globalmethods;


import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.LivingEntity;

public class EffectMethods {
    //amplifier I II III IV...
    public static StatusEffectInstance CreateHiddenParticleEffectInstance(StatusEffect a, int duration) {
        return new StatusEffectInstance(a, duration, 0, false, false);
    }

    public static boolean applyEffectToLivingEntity(LivingEntity a, StatusEffect b, int duration, int amplifier) {
//        a.addStatusEffect(new StatusEffectInstance(EffectInitialization.IMMOBILIZATION.get(), duration, amplifier));
        return a.addStatusEffect(new StatusEffectInstance(b, duration, amplifier));
    }

    public static boolean applyEffectToLivingEntity(LivingEntity a, StatusEffect b) {
        return a.addStatusEffect(new StatusEffectInstance(b));
    }

    public static boolean applyEffectToLivingEntity(LivingEntity a, StatusEffect b, int duration) {
        return a.addStatusEffect(new StatusEffectInstance(b, duration));
    }

    public static boolean applyEffectToLivingEntity(LivingEntity a, StatusEffectInstance b) {
        return a.addStatusEffect(b);
    }

    public static boolean hasEffect(LivingEntity a, StatusEffect b) {
        return a.hasStatusEffect(b);
    }

    public static boolean removeEffectFromLivingEntity(LivingEntity a, StatusEffect b) {
        return a.removeStatusEffect(b);
    }
}

package net.dingdingduang.somebasicskills.mixin;

import net.dingdingduang.somebasicskills.globalmethods.MixinMethodCallHelper;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.printInGameMsg;

@Mixin(LivingEntity.class)
public abstract class SBSLivingEntityOnHurtEventMixin extends Entity implements Attackable {
    public SBSLivingEntityOnHurtEventMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    //    @ModifyVariable(method = "applyDamage", at = @At(value = "STORE", ordinal = 1), ordinal = 0, argsOnly = true)
//    @Inject(method = "applyDamage", at = @At("HEAD"))
//    private void SBSonArmorToDamage(DamageSource source, float amount, CallbackInfo ci) {
//        LivingEntity target = (LivingEntity) this.world.getEntityById(this.hashCode());
//
//        amount = 0;
//
//
//        MixinMethodCallHelper.helper.LivingEntityOnHurtExtraAction(target, source, amount);
//    }

    //    @ModifyVariable(method = "applyDamage", at = @At(value = "STORE", ordinal = 1), ordinal = 0, argsOnly = true)
//    @Inject(method = "applyDamage", at = @At("HEAD"))
//    private void SBSonArmorToDamage(DamageSource source, float amount, CallbackInfo ci) {
//        LivingEntity target = (LivingEntity) this.world.getEntityById(this.hashCode());
//
//        amount = 0;
//
//
//        MixinMethodCallHelper.helper.LivingEntityOnHurtExtraAction(target, source, amount);
//    }

//    @ModifyArg(method = "applyDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyArmorToDamage(Lnet/minecraft/entity/damage/DamageSource;F)F"), index = 1, allow = 1)
//    @ModifyVariable(method = "applyDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyArmorToDamage(Lnet/minecraft/entity/damage/DamageSource;F)F"), ordinal = 0, argsOnly = true)
    @ModifyArgs(method = "applyDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyArmorToDamage(Lnet/minecraft/entity/damage/DamageSource;F)F"))
    private void SBSonArmorToDamage(Args args) {
        DamageSource damageSource = args.get(0);
        float incomingDMG = args.get(1);
        LivingEntity target = (LivingEntity) this.getWorld().getEntityById(this.hashCode());

//        var damageEntity = damageSource.getSource();
//        var damageEntityOwner = damageSource.getAttacker();
//        var targetName = target.getName().getString();
//        if (targetName != null) {
//            printInGameMsg("targetName: "+targetName);
//        }
//        if (damageEntity == null) {
////            printInGameMsg("null");
//        }
//        else {
//            printInGameMsg("damageEntity name: " +damageEntity.getNameForScoreboard());
//        }
//        if (damageEntityOwner == null) {
////            printInGameMsg("null");
//        }
//        else {
//            printInGameMsg("damageEntityOwner name: " +damageEntity.getNameForScoreboard());
//        }
//        incomingDMG = 0;


        incomingDMG = MixinMethodCallHelper.helper.LivingEntityOnHurtAction(target, damageSource, incomingDMG);

        args.set(1, incomingDMG);
    }

    @Inject(method = "takeKnockback", at = @At(value = "HEAD"), cancellable = true)
    private void SBSonLivingEntityTakeKnockBack(double strength, double x, double z, CallbackInfo ci) {
        LivingEntity target = (LivingEntity) this.getWorld().getEntityById(this.hashCode());

        if (target != null) {
            boolean shouldCancelMethodCall = false;
            shouldCancelMethodCall = MixinMethodCallHelper.helper.LivingEntityTakeKnockBackAction(target, shouldCancelMethodCall);
            if (shouldCancelMethodCall) { ci.cancel(); }
        }
    }

//    @ModifyArg(method = "applyDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyArmorToDamage(Lnet/minecraft/entity/damage/DamageSource;F)F"), index = 1, allow = 1)
//    private float SBSonArmorToDamage(float amount) {
//        return 0;
//    }
}

package net.dingdingduang.somebasicskills.globalmethods;

import net.dingdingduang.somebasicskills.event.SBSLivingEntityFabricEvent;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class MixinMethodCallHelper {
    public static MixinMethodCallHelper helper = new MixinMethodCallHelper();

    public MixinMethodCallHelper() {

    }

    public float LivingEntityOnHurtExtraAction(LivingEntity beingAttacked, DamageSource source, float IncomingDamage) {
        float finalIncomingDamage = IncomingDamage;



        return finalIncomingDamage;
    }

    public float LivingEntityOnHurtAction(LivingEntity beingAttacked, DamageSource source, float amount) {
//        float finalIncomingDamage = amount;
        float finalIncomingDamage = SBSLivingEntityFabricEvent.EntityBeforeDamageSBEvent(beingAttacked, amount);
        if (amount <= 0) { return 0; }
        finalIncomingDamage = LivingEntityOnHurtExtraAction(beingAttacked, source, finalIncomingDamage);

        return finalIncomingDamage;
    }

    public boolean LivingEntityTakeKnockBackExtraAction(LivingEntity beingAttacked, boolean shouldCancel) {
        return shouldCancel;
    }

    public boolean LivingEntityTakeKnockBackAction(LivingEntity beingAttacked, boolean shouldCancel) {
        boolean finalShouldCancel = LivingEntityTakeKnockBackExtraAction(beingAttacked, shouldCancel);
        return finalShouldCancel;
    }

    public void KeyEventExtraAction(int key, int scancode, int action, int modifiers) {

    }

    public void ExtraGuiOverlayAction(MatrixStack pPoseStack, float tickDelta) {

    }
}

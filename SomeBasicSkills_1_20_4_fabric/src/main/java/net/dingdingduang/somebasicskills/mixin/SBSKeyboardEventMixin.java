package net.dingdingduang.somebasicskills.mixin;

import net.dingdingduang.somebasicskills.globalmethods.MixinMethodCallHelper;
import net.dingdingduang.somebasicskills.keyboard.SomeBasicSkillTreeScreenKeySetting;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftInstance;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.printInGameMsg;
import static net.dingdingduang.somebasicskills.keyboard.KeyMappingInit.SBSInputEvent;

@Mixin(Keyboard.class)
public abstract class SBSKeyboardEventMixin {
//	@Mutable
//	@Final
//	@Shadow
//	private final MinecraftClient client;
//
//	public SBSKeyboardEventMixin(MinecraftClient client) {
//		this.client = client;
//	}

	//at head just in case if returns nothing
    //	@Inject(method = "Lnet/minecraft/client/Keyboard;onKey", at = @At("TAIL"))
	@Inject(method = "onKey", at = @At("HEAD"))
	private void SBSonKeyMixin(long window, int key, int scancode, int action, int modifiers, CallbackInfo info) {
		if (window == getMinecraftInstance().getWindow().getHandle()) {
//			SBSKeyboardEvent SBSonKeyEvent = new SBSKeyboardEvent(key, scancode, action, modifiers);
			SBSInputEvent(key, action);
//			SomeBasicSkillTreeScreenKeySetting.sbsSkillTreeScreenKeySetting(action);

//			printInGameMsg("key: "+key+", action: "+action);
			MixinMethodCallHelper.helper.KeyEventExtraAction(key, scancode, action, modifiers);
		}
	}

//	@ModifyArgs(method = "method_22678", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Keyboard;onKey(JIIII)V"))
//	private void SBSonArmorToDamage(Args args) {
//		long window = args.get(0);
//		int key = args.get(1);
//		int scancode = args.get(2);
//		int action = args.get(3);
//		int modifiers = args.get(4);
//		String abc = String.format("before: window: %d, key: %d, action: %d", window, key, action);
//		printInGameMsg(abc);
//	}
}

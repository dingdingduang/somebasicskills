package net.dingdingduang.somebasicskills.mixin;

import net.dingdingduang.somebasicskills.globalmethods.MixinMethodCallHelper;
import net.dingdingduang.somebasicskills.keyboard.SomeBasicSkillTreeScreenKeySetting;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftInstance;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.printInGameMsg;
import static net.dingdingduang.somebasicskills.keyboard.KeyMappingInit.SBSInputEvent;

@Mixin(Keyboard.class)
public abstract class SBSKeyboardEventMixin {
//	@Inject(method = "Lnet/minecraft/client/Keyboard;onKey", at = @At("TAIL"))
	@Inject(method = "onKey", at = @At("TAIL"))
	private void SBSonKeyMixin(long window, int key, int scancode, int action, int modifiers, CallbackInfo info) {
		if (window == getMinecraftInstance().getWindow().getHandle()) {
//			SBSKeyboardEvent SBSonKeyEvent = new SBSKeyboardEvent(key, scancode, action, modifiers);
			SBSInputEvent(key, action);
//			SomeBasicSkillTreeScreenKeySetting.sbsSkillTreeScreenKeySetting(action);

			MixinMethodCallHelper.helper.KeyEventExtraAction(key, scancode, action, modifiers);
		}
	}
}

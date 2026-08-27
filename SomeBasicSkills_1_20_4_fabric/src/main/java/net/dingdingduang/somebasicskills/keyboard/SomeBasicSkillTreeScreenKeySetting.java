package net.dingdingduang.somebasicskills.keyboard;

import net.dingdingduang.somebasicskills.gui.screen.RoleSelectionScreen;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;
import static net.dingdingduang.somebasicskills.keyboard.KeyMappingInit.isKeyActionDown;
import static net.dingdingduang.somebasicskills.keyboard.KeyMappingInit.setupKeyMapping;

public class SomeBasicSkillTreeScreenKeySetting {
//    public static final KeyBinding SOME_BASIC_SKILL_OPEN_SKILL_TREE_SCREEN =
//            setupKeyMapping("key.display_SomeBasicSkills", GLFW.GLFW_KEY_P, "key.categories." + Constants.MOD_ID);

//    public static void registerKeyMappingsEvent(FMLClientSetupEvent event) {
//        event.enqueueWork(
//                () -> ClientRegistry.registerKeyBinding(SOME_BASIC_SKILL_OPEN_SKILL_TREE_SCREEN)
//        );
//    }

//    public static void sbsSkillTreeScreenKeySetting(int keyAction) {
//        if (SOME_BASIC_SKILL_OPEN_SKILL_TREE_SCREEN.isPressed() && isKeyActionDown(keyAction)) {
//            MinecraftClient minecraft = getMinecraftInstance();
//
//            if (minecraft.player == null || minecraft.currentScreen != null) {
//                return;
//            }
//            minecraft.setScreen(new RoleSelectionScreen());
//        }
//    }

    public static void register() {
        final KeyBinding SOME_BASIC_SKILL_OPEN_SKILL_TREE_SCREEN = KeyBindingHelper.registerKeyBinding(setupKeyMapping("key.display_SomeBasicSkillsKeyMapping", GLFW.GLFW_KEY_P, "key.categories.SomeBasicSkillsTitle"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            MinecraftClient minecraft = getMinecraftInstance();
            if (minecraft.player == null || minecraft.currentScreen != null) { return; }
            while (SOME_BASIC_SKILL_OPEN_SKILL_TREE_SCREEN.isPressed()) { minecraft.setScreen(new RoleSelectionScreen()); }
        });
    }
}

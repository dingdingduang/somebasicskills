package net.dingdingduang.somebasicskills.keyboard;

import net.dingdingduang.somebasicskills.gui.screen.RoleSelectionScreen;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

import net.dingdingduang.somebasicskills.Constants;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;

public class SomeBasicSkillTreeScreenKeySetting {
    public static final KeyMapping SOME_BASIC_SKILL_OPEN_SKILL_TREE_SCREEN = new KeyMapping(Constants.SBS_KEY_SETTING_NAME, GLFW.GLFW_KEY_P, Constants.SBS_KEY_SETTING_CATEGORY);

    public static void registerKeyMappingsEvent(FMLClientSetupEvent event) {
        event.enqueueWork(
                () -> ClientRegistry.registerKeyBinding(SOME_BASIC_SKILL_OPEN_SKILL_TREE_SCREEN)
        );
    }

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
    private static class SBSSkillTreeScreenKeySetting {
        @SubscribeEvent
        public static void sbsSkillTreeScreenKeySetting(InputEvent.KeyInputEvent event) {
            if (SOME_BASIC_SKILL_OPEN_SKILL_TREE_SCREEN.isDown() && isKeyDown(event)) {
                Minecraft minecraft = getMinecraftInstance();

                if (minecraft.player == null || minecraft.screen != null) {
                    return;
                }

                minecraft.setScreen(new RoleSelectionScreen());
            }
        }
    }

    //releasing a pressed key
    private static boolean isKeyReleased(InputEvent.KeyInputEvent e) {
        return e.getAction() == 0;
    }

    //pressing down on a key
    private static boolean isKeyDown(InputEvent.KeyInputEvent e) {
        return e.getAction() == 1;
    }

    //holding a key
    private static boolean isKeyHolding(InputEvent.KeyInputEvent e) {
        return e.getAction() == 2;
    }
}

package net.dingdingduang.somebasicskills.keyboard;

import net.dingdingduang.somebasicskills.gui.screen.RoleSelectionScreen;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

import net.dingdingduang.somebasicskills.Constants;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SomeBasicSkillTreeScreenKeySetting {
    public static final KeyMapping SOME_BASIC_SKILL_OPEN_SKILL_TREE_SCREEN = new KeyMapping(Constants.SBS_KEY_SETTING_NAME, GLFW.GLFW_KEY_P, Constants.SBS_KEY_SETTING_CATEGORY);

    @SubscribeEvent
    public static void registerKeyMappingsEvent(RegisterKeyMappingsEvent event) {
        event.register(SOME_BASIC_SKILL_OPEN_SKILL_TREE_SCREEN);
    }

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
    private static class SBSSkillTreeScreenKeySetting {
        @SubscribeEvent
        public static void sbsSkillTreeScreenKeySetting(InputEvent.Key event) {
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
    private static boolean isKeyReleased(InputEvent.Key e) {
        return e.getAction() == 0;
    }

    //pressing down on a key
    private static boolean isKeyDown(InputEvent.Key e) {
        return e.getAction() == 1;
    }

    //holding a key
    private static boolean isKeyHolding(InputEvent.Key e) {
        return e.getAction() == 2;
    }
}

package net.dingdingduang.somebasicskills.registries;

//import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.OverlayRegistry;
//import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.dingdingduang.somebasicskills.keyboard.keyboardoverlaytimer.PlayerKeyComboListenerOverlayTimer.getPlayerKerboardOverlayTimerInstance;
import static net.dingdingduang.somebasicskills.gui.overlay.SkillChannelingOverlay.getSkillChannelingOverlayInstance;
import static net.dingdingduang.somebasicskills.gui.overlay.SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance;

//@Mod.EventBusSubscriber(Dist.CLIENT)
public class GuiOverlayClientRegistry {
    public static void registerOverlays(FMLClientSetupEvent event) {
        event.enqueueWork(
                () -> {
                    OverlayRegistry.registerOverlayAbove(ForgeIngameGui.EXPERIENCE_BAR_ELEMENT, "skillsincooldownclienttimeroverlay000", getSkillsInCooldownClientTimerOverlayInstance());
                    OverlayRegistry.registerOverlayAbove(ForgeIngameGui.EXPERIENCE_BAR_ELEMENT, "skillchannelingoverlay001", getSkillChannelingOverlayInstance());
                    OverlayRegistry.registerOverlayAbove(ForgeIngameGui.EXPERIENCE_BAR_ELEMENT, "playerkeyboardlisteneroverlay002", getPlayerKerboardOverlayTimerInstance());
                }
        );
    }
}

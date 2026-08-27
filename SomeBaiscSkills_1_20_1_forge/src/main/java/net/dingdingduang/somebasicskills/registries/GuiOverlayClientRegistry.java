package net.dingdingduang.somebasicskills.registries;

import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;

import static net.dingdingduang.somebasicskills.keyboard.keyboardoverlaytimer.PlayerKeyComboListenerOverlayTimer.getPlayerKerboardOverlayTimerInstance;
import static net.dingdingduang.somebasicskills.gui.overlay.SkillChannelingOverlay.getSkillChannelingOverlayInstance;
import static net.dingdingduang.somebasicskills.gui.overlay.SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance;

public class GuiOverlayClientRegistry {
    public static void onRegisterOverlays(RegisterGuiOverlaysEvent event) {
        //id lowercase only
//        event.registerAbove(VanillaGuiOverlay.EXPERIENCE_BAR.id(), "testoverlay000", testoverlay.instance);
//        event.registerAbove(VanillaGuiOverlay.EXPERIENCE_BAR.id(), "skillsincooldownservertimeroverlay000", getSkillsInCooldownServerTimerOverlayInstance());
        event.registerAbove(VanillaGuiOverlay.EXPERIENCE_BAR.id(), "skillsincooldownclienttimeroverlay000", getSkillsInCooldownClientTimerOverlayInstance());
        event.registerAbove(VanillaGuiOverlay.EXPERIENCE_BAR.id(), "skillchannelingoverlay001", getSkillChannelingOverlayInstance());
        event.registerAbove(VanillaGuiOverlay.EXPERIENCE_BAR.id(), "playerkeyboardlisteneroverlay002", getPlayerKerboardOverlayTimerInstance());
//        event.registerAbove(VanillaGuiOverlay.EXPERIENCE_BAR.id(), "attributeservertimeroverlay003", getAttributeServerTimerOverlayInstance());
//        event.registerAbove(VanillaGuiOverlay.EXPERIENCE_BAR.id(), "methodactiontimeroverlay004", getMethodActionTimerOverlayInstance());

//        event.registerBelow(VanillaGuiOverlay.HOTBAR.id(), "testoverlay000", testoverlay.instance);
//        event.registerAbove(VanillaGuiOverlay.EXPERIENCE_BAR.id(), "testoverlay000", testoverlay.instance);
//        event.registerAbove(VanillaGuiOverlay.PLAYER_LIST.id(), "testoverlay000", testoverlay.instance);
    }
}

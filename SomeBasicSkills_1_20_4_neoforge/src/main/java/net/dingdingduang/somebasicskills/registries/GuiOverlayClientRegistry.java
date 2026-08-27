package net.dingdingduang.somebasicskills.registries;

import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RegisterGuiOverlaysEvent;
import net.neoforged.neoforge.client.gui.overlay.VanillaGuiOverlay;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.keyboard.keyboardoverlaytimer.PlayerKeyComboListenerOverlayTimer.getPlayerKerboardOverlayTimerInstance;
import static net.dingdingduang.somebasicskills.gui.overlay.SkillChannelingOverlay.getSkillChannelingOverlayInstance;
import static net.dingdingduang.somebasicskills.gui.overlay.SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance;

public class GuiOverlayClientRegistry {
    private static final ResourceLocation SKILLS_IN_COOLDOWN_CLIENT_TIMMER_OVERLAY_000 = getMCResourceLocation(Constants.MOD_ID, "skillsincooldownclienttimeroverlay000");
    private static final ResourceLocation SKILL_CHANNELING_OVERLAY_001 = getMCResourceLocation(Constants.MOD_ID, "skillchannelingoverlay001");
    private static final ResourceLocation PLAYER_KEYBOARD_LISTENER_OVERLAY_002 = getMCResourceLocation(Constants.MOD_ID, "playerkeyboardlisteneroverlay002");

    public static void onRegisterOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAbove(VanillaGuiOverlay.EXPERIENCE_BAR.id(), SKILLS_IN_COOLDOWN_CLIENT_TIMMER_OVERLAY_000, getSkillsInCooldownClientTimerOverlayInstance());
        event.registerAbove(VanillaGuiOverlay.EXPERIENCE_BAR.id(), SKILL_CHANNELING_OVERLAY_001, getSkillChannelingOverlayInstance());
        event.registerAbove(VanillaGuiOverlay.EXPERIENCE_BAR.id(), PLAYER_KEYBOARD_LISTENER_OVERLAY_002, getPlayerKerboardOverlayTimerInstance());
    }
}

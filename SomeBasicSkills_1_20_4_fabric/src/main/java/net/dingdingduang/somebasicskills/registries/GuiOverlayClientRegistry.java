package net.dingdingduang.somebasicskills.registries;

import net.dingdingduang.somebasicskills.globalmethods.MixinMethodCallHelper;
import net.dingdingduang.somebasicskills.gui.overlay.SkillChannelingOverlay;
import net.dingdingduang.somebasicskills.gui.overlay.SkillsInCooldownClientTimerOverlay;
import net.dingdingduang.somebasicskills.keyboard.keyboardoverlaytimer.PlayerKeyComboListenerOverlayTimer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftInstance;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.printInGameMsg;

public class GuiOverlayClientRegistry {
    private static final SkillChannelingOverlay ChannelingOverlay = SkillChannelingOverlay.getSkillChannelingOverlayInstance();
    private static final SkillsInCooldownClientTimerOverlay CooldownOverlay = SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance();
    private static final PlayerKeyComboListenerOverlayTimer KeyComboOverlay = PlayerKeyComboListenerOverlayTimer.getPlayerKerboardOverlayTimerInstance();

    private static void registerFabricClientOverlaysFinal(DrawContext drawContext, float tickDelta) {
        PlayerEntity player = getMinecraftInstance().player;
        if (player == null) { return; }
        MatrixStack pPoseStack = drawContext.getMatrices();
        int gameWorldTick = getMinecraftInstance().player.age;
//        float partialTick = getMinecraftInstance().getTickDelta();
//        float partialTick = tickDelta;
        int screenWidth = getMinecraftInstance().getWindow().getScaledWidth();
        int screenHeight = getMinecraftInstance().getWindow().getScaledHeight();

        CooldownOverlay.render(gameWorldTick, drawContext, pPoseStack, tickDelta, screenWidth, screenHeight);
        ChannelingOverlay.render(gameWorldTick, pPoseStack, tickDelta, screenWidth, screenHeight);
        KeyComboOverlay.render();

        MixinMethodCallHelper.helper.ExtraGuiOverlayAction(pPoseStack, tickDelta);

//        int color = 0xFFFF0000; // Red
//        int targetColor = 0xFF00FF00; // Green
//
//        // Total tick delta is stored in a field, so we can use it later.
//        totalTickDelta += tickDelta;
//
//        // "lerp" simply means "linear interpolation", which is a fancy way of saying "blend".
//        float lerpedAmount = MathHelper.abs(MathHelper.sin(totalTickDelta / 50F));
//        int lerpedColor = ColorHelper.Argb.lerp(lerpedAmount, color, targetColor);
//
//        // Draw a square with the lerped color.
//        // x1, x2, y1, y2, z, color
//        context.fill(0, 0, 100, 100, 0, lerpedColor);
    }

    public static void registerFabricClientOverlays() {
        HudRenderCallback.EVENT.register(GuiOverlayClientRegistry::registerFabricClientOverlaysFinal);
    }
}

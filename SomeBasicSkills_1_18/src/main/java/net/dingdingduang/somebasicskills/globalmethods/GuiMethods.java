package net.dingdingduang.somebasicskills.globalmethods;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.dingdingduang.somebasicskills.util.SBSImageButton;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.GuiUtils;

public class GuiMethods {
    //0, 0 is topleft
    public static SBSImageButton CreateImageButton(int x, int y, int pxWidth, int pxHeight, ResourceLocation IMGloc, boolean hasHover, ResourceLocation hoverIMGloc, int IMGpxWidth, int IMGpxHeight, int translatedZ) {
        SBSImageButton tempIMGbutton =
                new SBSImageButton(x, y, pxWidth, pxHeight, TextComponent.EMPTY, IMGloc, hasHover, hoverIMGloc, 0, 0, pxWidth, pxHeight,
                        IMGpxWidth, IMGpxHeight, translatedZ);

        return tempIMGbutton;
    }

    public static SBSImageButton CreateImageButton(int x, int y, int pxWidth, int pxHeight, ResourceLocation IMGloc, boolean hasHover, ResourceLocation hoverIMGloc, int translatedZ) {
        return CreateImageButton(x, y, pxWidth, pxHeight, IMGloc, hasHover, hoverIMGloc, pxWidth, pxHeight, translatedZ);
    }

    public static SBSImageButton CreateImageButton(int x, int y, int pxWidth, int pxHeight, ResourceLocation IMGloc, int translatedZ) {
        return CreateImageButton(x, y, pxWidth, pxHeight, IMGloc, false, null, pxWidth, pxHeight, translatedZ);
    }

    public static void drawImageOnly(PoseStack pPoseStack, ResourceLocation res, int posX, int poxY, int pxImageWidth, int pxImageHeight) {
//        int border = (pxImageWidth > pxImageHeight) ? pxImageWidth: pxImageHeight;
//        GuiUtils.drawContinuousTexturedBox(pPoseStack, res, posX, poxY, pxImageWidth, pxImageHeight, pxImageWidth, pxImageHeight, pxImageWidth, pxImageHeight, border, zLevel);

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, res);
        GuiComponent.blit(pPoseStack, posX, poxY, 0, pxImageWidth, pxImageHeight, pxImageWidth, pxImageHeight, pxImageWidth, pxImageHeight);
    }

    public static void guiUtilsFillGradient(PoseStack pPoseStack, int x1, int y1, int x2, int y2, int fromColor, int toColor, int translateZ) {
        GuiUtils.drawGradientRect(pPoseStack.last().pose(), translateZ, x1, y1, x2, y2, fromColor, toColor);
    }

    public static void drawImageOnly(PoseStack pPoseStack, ResourceLocation res, int posX, int poxY, int blitPosX, int blitPosY, int blitImageWidth, int blitImageHeight, int pxImageWidth, int pxImageHeight) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, res);

//        int border = (pxImageWidth > pxImageHeight) ? pxImageWidth: pxImageHeight;
//        GuiUtils.drawContinuousTexturedBox(pPoseStack, res, posX, poxY, blitPosX, blitPosY, btnWidth, btnHeight, pxImageWidth, pxImageHeight, border, zLevel);
//        GuiComponent.blit(pPoseStack, posX, poxY, 0, blitPosX, blitPosY, blitImageWidth, blitImageHeight, pxImageWidth, pxImageHeight);
        GuiComponent.blit(pPoseStack, posX, poxY, 0, blitPosX, blitPosY, blitImageWidth, blitImageHeight, pxImageWidth, pxImageHeight);
    }

//    public static SBSImageButton CreateImageButtonImageOnly(int x, int y, int pxWidth, int pxHeight, ResourceLocation IMGloc, ResourceLocation hoverIMGloc) {
//        return new SBSImageButton(x, y, 0, 0, Component.empty(),
//                false, IMGloc, hoverIMGloc, 0, 0, pxWidth, pxHeight,
//                pxWidth, pxHeight);
//    }

    public static void drawString(PoseStack pPoseStack, Font font, String text, int posX, int posY, int color) {
        GuiComponent.drawString(pPoseStack, font, text, posX, posY, color);
    }

    public static void drawCenteredString(PoseStack pPoseStack, Font font, String text, int posX, int posY, int color) {
        GuiComponent.drawCenteredString(pPoseStack, font, text, posX, posY, color);
    }

    public static void abc() {
    }
}

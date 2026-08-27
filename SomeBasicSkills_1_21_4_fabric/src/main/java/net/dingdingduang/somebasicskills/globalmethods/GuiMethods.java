package net.dingdingduang.somebasicskills.globalmethods;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dingdingduang.somebasicskills.util.SBSImageButton;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.*;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import net.minecraft.client.gl.ShaderProgramKeys;

public class GuiMethods {
    public static void oldBlit(MatrixStack pPoseStack, Identifier resLoc, int pX, int pY, float pUOffset, float pVOffset, int pWidth, int pHeight, int pTextureWidth, int pTextureHeight) {
        oldBlit(pPoseStack, resLoc, pX, pY, pWidth, pHeight, pUOffset, pVOffset, pWidth, pHeight, pTextureWidth, pTextureHeight);
    }

    public static void oldBlit(MatrixStack pPoseStack, Identifier resLoc, int pX, int pY, int pWidth, int pHeight, float pUOffset, float pVOffset, int pUWidth, int pVHeight, int pTextureWidth, int pTextureHeight) {
        oldBlit(pPoseStack, resLoc, pX, pX + pWidth, pY, pY + pHeight, 0, pUWidth, pVHeight, pUOffset, pVOffset, pTextureWidth, pTextureHeight);
    }

    public static void oldBlit(MatrixStack pPoseStack, Identifier resLoc, int pX1, int pX2, int pY1, int pY2, int pBlitOffset, int pUWidth, int pVHeight, float pUOffset, float pVOffset, int pTextureWidth, int pTextureHeight) {
        oldFinalBlitMC(pPoseStack, resLoc, pX1, pX2, pY1, pY2, pBlitOffset, (pUOffset + 0.0F) / (float)pTextureWidth, (pUOffset + (float)pUWidth) / (float)pTextureWidth, (pVOffset + 0.0F) / (float)pTextureHeight, (pVOffset + (float)pVHeight) / (float)pTextureHeight);
    }

    public static void oldFinalBlitMC(MatrixStack pPoseStack, Identifier resLoc, int pX1, int pX2, int pY1, int pY2, int pBlitOffset, float pMinU, float pMaxU, float pMinV, float pMaxV) {
        RenderSystem.setShaderTexture(0, resLoc);
        RenderSystem.setShader(ShaderProgramKeys.POSITION_TEX);
        Matrix4f matrix4f = pPoseStack.peek().getPositionMatrix();
        RenderSystem.enableBlend();
        BufferBuilder bufferBuilder = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
//        bufferBuilder.vertex(matrix4f, (float)pX1, (float)pY1, (float)pBlitOffset).texture(pMinU, pMinV).color(255, 255, 255, 255);
//        bufferBuilder.vertex(matrix4f, (float)pX1, (float)pY2, (float)pBlitOffset).texture(pMinU, pMaxV).color(255, 255, 255, 255);
//        bufferBuilder.vertex(matrix4f, (float)pX2, (float)pY2, (float)pBlitOffset).texture(pMaxU, pMaxV).color(255, 255, 255, 255);
//        bufferBuilder.vertex(matrix4f, (float)pX2, (float)pY1, (float)pBlitOffset).texture(pMaxU, pMinV).color(255, 255, 255, 255);
        bufferBuilder.vertex(matrix4f, (float)pX1, (float)pY1, (float)pBlitOffset).texture(pMinU, pMinV);
        bufferBuilder.vertex(matrix4f, (float)pX1, (float)pY2, (float)pBlitOffset).texture(pMinU, pMaxV);
        bufferBuilder.vertex(matrix4f, (float)pX2, (float)pY2, (float)pBlitOffset).texture(pMaxU, pMaxV);
        bufferBuilder.vertex(matrix4f, (float)pX2, (float)pY1, (float)pBlitOffset).texture(pMaxU, pMinV);
//        BufferRenderer.draw(bufferBuilder.end());
        BufferRenderer.drawWithGlobalProgram(bufferBuilder.end());
        RenderSystem.disableBlend();
    }

    //0, 0 is topleft
    public static SBSImageButton CreateImageButton(int x, int y, int pxWidth, int pxHeight, Identifier IMGloc, boolean hasHover, Identifier hoverIMGloc, int IMGpxWidth, int IMGpxHeight, int translatedZ) {
        SBSImageButton tempIMGbutton =
                new SBSImageButton(x, y, pxWidth, pxHeight, Text.empty(), IMGloc, hasHover, hoverIMGloc, 0, 0, pxWidth, pxHeight,
                        IMGpxWidth, IMGpxHeight, translatedZ);

        return tempIMGbutton;
    }

    public static SBSImageButton CreateImageButton(int x, int y, int pxWidth, int pxHeight, Identifier IMGloc, boolean hasHover, Identifier hoverIMGloc, int translatedZ) {
        return CreateImageButton(x, y, pxWidth, pxHeight, IMGloc, hasHover, hoverIMGloc, pxWidth, pxHeight, translatedZ);
    }

    public static SBSImageButton CreateImageButton(int x, int y, int pxWidth, int pxHeight, Identifier IMGloc, int translatedZ) {
        return CreateImageButton(x, y, pxWidth, pxHeight, IMGloc, false, null, pxWidth, pxHeight, translatedZ);
    }

    public static void drawImageOnly(MatrixStack pPoseStack, Identifier resLoc, int posX, int poxY, int pxImageWidth, int pxImageHeight) {
        oldBlit(pPoseStack, resLoc, posX, poxY, pxImageWidth, pxImageHeight, pxImageWidth, pxImageHeight, pxImageWidth, pxImageHeight);
    }

//    public static void guiUtilsFillGradient(MatrixStack pPoseStack, int x1, int y1, int x2, int y2, int fromColor, int toColor, int translateZ) {
//        Matrix4f matrix4f = pPoseStack.peek().getPositionMatrix();
//
//        float startAlpha = (float) (fromColor >> 24 & 255) / 255F;
//        float startRed = (float) (fromColor >> 16 & 255) / 255F;
//        float startGreen = (float) (fromColor >> 8 & 255) / 255F;
//        float startBlue = (float) (fromColor & 255) / 255F;
//        float endAlpha = (float) (toColor >> 24 & 255) / 255F;
//        float endRed = (float) (toColor >> 16 & 255) / 255F;
//        float endGreen = (float) (toColor >> 8 & 255) / 255F;
//        float endBlue = (float) (toColor & 255) / 255F;
//        RenderSystem.enableDepthTest();
//        RenderSystem.disableTexture();
//        RenderSystem.enableBlend();
//        RenderSystem.defaultBlendFunc();
//        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder bufferBuilder = tessellator.getBuffer();
//        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
//        bufferBuilder.vertex(matrix4f, (float) x2, (float) y1, (float) translateZ).color(startRed, startGreen, startBlue, startAlpha).next();
//        bufferBuilder.vertex(matrix4f, (float) x1, (float) y1, (float) translateZ).color(startRed, startGreen, startBlue, startAlpha).next();
//        bufferBuilder.vertex(matrix4f, (float) x1, (float) y2, (float) translateZ).color(endRed, endGreen, endBlue, endAlpha).next();
//        bufferBuilder.vertex(matrix4f, (float) x2, (float) y2, (float) translateZ).color(endRed, endGreen, endBlue, endAlpha).next();
//        tessellator.draw();
//        RenderSystem.disableBlend();
//        RenderSystem.enableTexture();
//    }

    public static void guiUtilsFillGradient(DrawContext drawContext, int x1, int y1, int x2, int y2, int fromColor, int toColor, int translateZ) {
        drawContext.fillGradient(x1, y1, x2, y2, translateZ, toColor, fromColor);
    }

    public static void drawImageOnly(MatrixStack pPoseStack, Identifier res, int posX, int poxY, int blitPosX, int blitPosY, int blitImageWidth, int blitImageHeight, int pxImageWidth, int pxImageHeight) {
        RenderSystem.setShader(ShaderProgramKeys.POSITION_TEX);
        RenderSystem.setShaderTexture(0, res);

//        int border = (pxImageWidth > pxImageHeight) ? pxImageWidth: pxImageHeight;
//        GuiUtils.drawContinuousTexturedBox(pPoseStack, res, posX, poxY, blitPosX, blitPosY, btnWidth, btnHeight, pxImageWidth, pxImageHeight, border, zLevel);
//        GuiComponent.blit(pPoseStack, posX, poxY, 0, blitPosX, blitPosY, blitImageWidth, blitImageHeight, pxImageWidth, pxImageHeight);
        oldBlit(pPoseStack, res, posX, poxY, blitPosX, blitPosY, blitImageWidth, blitImageHeight, pxImageWidth, pxImageHeight);
    }

//    public static SBSImageButton CreateImageButtonImageOnly(int x, int y, int pxWidth, int pxHeight, Identifier IMGloc, Identifier hoverIMGloc) {
//        return new SBSImageButton(x, y, 0, 0, Component.empty(),
//                false, IMGloc, hoverIMGloc, 0, 0, pxWidth, pxHeight,
//                pxWidth, pxHeight);
//    }

    public static BufferAllocator getCutOutBuffer() { return new BufferAllocator(786432); }

    public static void drawOrderText(TextRenderer font, MatrixStack matrices, OrderedText text, float x, float y, int color) {
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(getCutOutBuffer());
        font.draw(text, x, y, color, false, matrix4f, immediate, TextRenderer.TextLayerType.NORMAL, 0, 15728880);
//        drawTextWithDetailSettings(font, text, x, y, color, false, matrix4f, immediate, TextRenderer.TextLayerType.NORMAL, 0, 15728880);
        immediate.draw();
    }

    public static void drawTextWithDetailSettings(TextRenderer font, String text, float x, float y, int color, boolean shadow, Matrix4f matrix, VertexConsumerProvider vertexConsumers, TextRenderer.TextLayerType layerType, int backgroundColor, int light) {
        font.draw(text, x, y, color, shadow, matrix, vertexConsumers, layerType, backgroundColor, light);
    }

    public static void drawWithShadow(TextRenderer font, MatrixStack matrices, String text, float x, float y, int color) {
        VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(getCutOutBuffer());
        drawTextWithDetailSettings(font, text, x, y, color, true, matrices.peek().getPositionMatrix(), immediate, TextRenderer.TextLayerType.NORMAL, 0, 15728880);
        immediate.draw();
    }

    public static void drawString(MatrixStack pPoseStack, TextRenderer font, String text, int posX, int posY, int color) {
//        font.drawWithShadow(pPoseStack, text, posX, posY, color);
        drawWithShadow(font, pPoseStack, text, posX, posY, color);
    }

    public static void drawCenteredString(MatrixStack pPoseStack, TextRenderer font, String text, int posX, int posY, int color) {
//        font.drawWithShadow(pPoseStack, text, posX - font.getWidth(text) * 0.5f, posY, color);
        drawWithShadow(font, pPoseStack, text, posX - font.getWidth(text) * 0.5f, posY, color);
    }

    public static int getAlphaFromColor255IntBase(int color) { return color >> 24 & 255; }
    public static int getRedFromColor255IntBase(int color) { return color >> 16 & 255; }
    public static int getGreenFromColor255IntBase(int color) { return color >> 8 & 255; }
    public static int getBlueFromColor255IntBase(int color) { return color & 255; }
}

package net.dingdingduang.somebasicskills.globalmethods;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.dingdingduang.somebasicskills.util.SBSImageButton;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.CoreShaders;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

public class GuiMethods {
    //0, 0 is topleft
    public static SBSImageButton CreateImageButton(int x, int y, int pxWidth, int pxHeight, ResourceLocation IMGloc, boolean hasHover, ResourceLocation hoverIMGloc, int IMGpxWidth, int IMGpxHeight, int translatedZ) {
        SBSImageButton tempIMGbutton =
                new SBSImageButton(x, y, pxWidth, pxHeight, Component.empty(), IMGloc, hasHover, hoverIMGloc, 0, 0, pxWidth, pxHeight,
                        IMGpxWidth, IMGpxHeight, translatedZ);

        return tempIMGbutton;
    }

    public static SBSImageButton CreateImageButton(int x, int y, int pxWidth, int pxHeight, ResourceLocation IMGloc, boolean hasHover, ResourceLocation hoverIMGloc, int translatedZ) {
        return CreateImageButton(x, y, pxWidth, pxHeight, IMGloc, hasHover, hoverIMGloc, pxWidth, pxHeight, translatedZ);
    }

    public static SBSImageButton CreateImageButton(int x, int y, int pxWidth, int pxHeight, ResourceLocation IMGloc, int translatedZ) {
        return CreateImageButton(x, y, pxWidth, pxHeight, IMGloc, false, null, pxWidth, pxHeight, translatedZ);
    }


    public static void oldBlit(GuiGraphics guiGraphics, ResourceLocation pAtlasLocation, int pX, int pY, float pUOffset, float pVOffset, int pWidth, int pHeight, int pTextureWidth, int pTextureHeight) {
        oldBlit(guiGraphics, pAtlasLocation, pX, pY, pWidth, pHeight, pUOffset, pVOffset, pWidth, pHeight, pTextureWidth, pTextureHeight);
    }

    public static void oldBlit(GuiGraphics guiGraphics, ResourceLocation pAtlasLocation, int pX, int pY, int pWidth, int pHeight, float pUOffset, float pVOffset, int pUWidth, int pVHeight, int pTextureWidth, int pTextureHeight) {
        oldBlit(guiGraphics, pAtlasLocation, pX, pX + pWidth, pY, pY + pHeight, 0, pUWidth, pVHeight, pUOffset, pVOffset, pTextureWidth, pTextureHeight);
    }

    public static void oldBlit(GuiGraphics guiGraphics, ResourceLocation pAtlasLocation, int pX1, int pX2, int pY1, int pY2, int pBlitOffset, int pUWidth, int pVHeight, float pUOffset, float pVOffset, int pTextureWidth, int pTextureHeight) {
        oldFinalBlitMC(guiGraphics, pAtlasLocation, pX1, pX2, pY1, pY2, pBlitOffset, (pUOffset + 0.0F) / (float)pTextureWidth, (pUOffset + (float)pUWidth) / (float)pTextureWidth, (pVOffset + 0.0F) / (float)pTextureHeight, (pVOffset + (float)pVHeight) / (float)pTextureHeight);
    }

    public static void oldFinalBlitMC(GuiGraphics guiGraphics, ResourceLocation resLoc, int pX1, int pX2, int pY1, int pY2, int pBlitOffset, float pMinU, float pMaxU, float pMinV, float pMaxV) {
        RenderSystem.setShaderTexture(0, resLoc);
        RenderSystem.setShader(CoreShaders.POSITION_TEX);
        Matrix4f matrix4f = guiGraphics.pose().last().pose();
        BufferBuilder bufferbuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.addVertex(matrix4f, (float)pX1, (float)pY1, (float)pBlitOffset).setUv(pMinU, pMinV).setColor(0xFFFFFFFF);
        bufferbuilder.addVertex(matrix4f, (float)pX1, (float)pY2, (float)pBlitOffset).setUv(pMinU, pMaxV).setColor(0xFFFFFFFF);
        bufferbuilder.addVertex(matrix4f, (float)pX2, (float)pY2, (float)pBlitOffset).setUv(pMaxU, pMaxV).setColor(0xFFFFFFFF);
        bufferbuilder.addVertex(matrix4f, (float)pX2, (float)pY1, (float)pBlitOffset).setUv(pMaxU, pMinV).setColor(0xFFFFFFFF);
        BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());
    }

    public static void drawImageOnly(GuiGraphics guiGraphics, ResourceLocation res, int posX, int posY, int pxImageWidth, int pxImageHeight) {
//        guiGraphics.blit(RenderType::guiTextured, res, posX, posY, pxImageWidth, pxImageHeight, pxImageWidth, pxImageHeight, pxImageWidth, pxImageHeight);
        oldBlit(guiGraphics, res, posX, posY, pxImageWidth, pxImageHeight, pxImageWidth, pxImageHeight, pxImageWidth, pxImageHeight);
    }

    public static void drawImageOnly(GuiGraphics guiGraphics, ResourceLocation res, int posX, int posY, int BlitButtonFromX, int BlitButtonFromY, int BlitButtonToX, int BlitButtonToY, int ResourceLocImageWidth, int ResourceLocImageHeight) {
//        guiGraphics.blit(RenderType::guiTextured, res, posX, posY, BlitButtonFromX, BlitButtonFromY, BlitButtonToX, BlitButtonToY, ResourceLocImageWidth, ResourceLocImageHeight);
        oldBlit(guiGraphics, res, posX, posY, BlitButtonFromX, BlitButtonFromY, BlitButtonToX, BlitButtonToY, ResourceLocImageWidth, ResourceLocImageHeight);
    }

//    public static SBSImageButton CreateImageButtonImageOnly(int x, int y, int pxWidth, int pxHeight, ResourceLocation IMGloc, ResourceLocation hoverIMGloc) {
//        return new SBSImageButton(x, y, 0, 0, Component.empty(),
//                false, IMGloc, hoverIMGloc, 0, 0, pxWidth, pxHeight,
//                pxWidth, pxHeight);
//    }
}

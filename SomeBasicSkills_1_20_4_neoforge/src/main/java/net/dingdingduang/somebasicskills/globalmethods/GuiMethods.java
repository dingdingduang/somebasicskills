package net.dingdingduang.somebasicskills.globalmethods;

import net.dingdingduang.somebasicskills.util.SBSImageButton;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

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

    public static void drawImageOnly(GuiGraphics guiGraphics, ResourceLocation res, int posX, int poxY, int pxImageWidth, int pxImageHeight) {
        guiGraphics.blit(res, posX, poxY, pxImageWidth, pxImageHeight, pxImageWidth, pxImageHeight, pxImageWidth, pxImageHeight);
    }

//    public static SBSImageButton CreateImageButtonImageOnly(int x, int y, int pxWidth, int pxHeight, ResourceLocation IMGloc, ResourceLocation hoverIMGloc) {
//        return new SBSImageButton(x, y, 0, 0, Component.empty(),
//                false, IMGloc, hoverIMGloc, 0, 0, pxWidth, pxHeight,
//                pxWidth, pxHeight);
//    }
}

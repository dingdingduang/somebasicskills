package net.dingdingduang.somebasicskills.util;

import java.util.function.Supplier;

import net.dingdingduang.somebasicskills.globalmethods.GeneralMethods;
import net.dingdingduang.somebasicskills.globalmethods.GuiMethods;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftInstanceFont;
import static net.dingdingduang.somebasicskills.globalmethods.GuiMethods.drawImageOnly;

public class SBSImageButton extends Button {
    private ResourceLocation BGImageLoc;
    private ResourceLocation BGHoveredImageLoc;
    private int BlitButtonWidth;
    private int BlitButtonHeight;
    private int ButtonWidth;
    private int ButtonHeight;
    private int ResourceLocImageWidth;
    private int ResourceLocImageHeight;
    protected OnPress Function;

    //Btn original pos
    private int ButtonOriginalPosX = 0;
    private int ButtonOriginalPosY = 0;

    //hover
    private boolean hasHoverBool = false;

    //translateZ
    private int TranslatedZ = 0;

    //pressedAnimation
    private boolean pressedBool = false;
    private ResourceLocation pressedBoolResLoc;
    private boolean hasPressedBool = false;
    private int tempPressesButtonTicks;
    private int pressedBtnTicks;

    //text setup
    private boolean hasText = false;
    private String ButtonText;
    private int ButtonTextRelativePosX;
    private int ButtonTextRelativePosY;
    private int ButtonTextColor;

    public SBSImageButton(int x, int y, int width, int height, Component message, ResourceLocation BGImageLoc, boolean hasHover, ResourceLocation BGHoveredImageLoc,
                          int blitButtonWidth, int blitButtonHeight, int ButtonWidth, int ButtonHeight, int ResourceLocImageWidth, int ResourceLocImageHeight, int translateZ) {
        super(x, y, width, height, message, b -> {}, Supplier::get);
        this.BGImageLoc = BGImageLoc;
        this.BGHoveredImageLoc = BGHoveredImageLoc;
        this.BlitButtonWidth = blitButtonWidth;
        this.BlitButtonHeight = blitButtonHeight;
        this.ButtonWidth = ButtonWidth;
        this.ButtonHeight = ButtonHeight;
        this.ResourceLocImageWidth = ResourceLocImageWidth;
        this.ResourceLocImageHeight = ResourceLocImageHeight;
        this.Function = b -> {};
        this.hasHoverBool = hasHover;
        this.TranslatedZ = translateZ;
    }

    public int getSBSBtnPosX() {
        return this.getX();
    }

    public void setSBSBtnPosX(int newX) {
        this.setX(newX);
    }

    public int getSBSBtnPosY() {
        return this.getY();
    }

    public void setSBSBtnPosY(int newY) {
        this.setY(newY);
    }

    public void setPressedFuncAction(OnPress Function) {
        this.Function = Function;
    }

    @Override
    public void onPress() {
        if (this.hasPressedBool) {
            this.pressedBool = true;
            this.tempPressesButtonTicks = this.pressedBtnTicks;
        }
        this.Function.onPress(this);
    }

    @Override
    public void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate(0, 0, this.TranslatedZ + 1);
        if (this.pressedBool) {
            if (this.tempPressesButtonTicks > 0) { this.tempPressesButtonTicks--; }
            else { this.pressedBool = false; }
//            pGuiGraphics.blit(RenderType::guiTextured, pressedBoolResLoc, getSBSBtnPosX(), getSBSBtnPosY(), this.BlitButtonWidth, this.BlitButtonHeight, this.ButtonWidth, this.ButtonHeight, this.ResourceLocImageWidth, this.ResourceLocImageHeight);
            drawImageOnly(pGuiGraphics, pressedBoolResLoc, getSBSBtnPosX(), getSBSBtnPosY(), this.BlitButtonWidth, this.BlitButtonHeight, this.ButtonWidth, this.ButtonHeight, this.ResourceLocImageWidth, this.ResourceLocImageHeight);
        }
        else {
//            pGuiGraphics.blit(RenderType::guiTextured, this.BGImageLoc, getSBSBtnPosX(), getSBSBtnPosY(), this.BlitButtonWidth, this.BlitButtonHeight, this.ButtonWidth, this.ButtonHeight, this.ResourceLocImageWidth, this.ResourceLocImageHeight);
//            drawImageOnly(pGuiGraphics,  this.BGImageLoc, getSBSBtnPosX(), getSBSBtnPosY(), this.BlitButtonWidth, this.BlitButtonHeight, this.ButtonWidth, this.ButtonHeight, this.ResourceLocImageWidth, this.ResourceLocImageHeight);


            if (this.hasHoverBool && isHovered()) {
//                pGuiGraphics.blit(RenderType::guiTextured, this.BGHoveredImageLoc, getSBSBtnPosX(), getSBSBtnPosY(), this.BlitButtonWidth, this.BlitButtonHeight, this.ButtonWidth, this.ButtonHeight, this.ResourceLocImageWidth, this.ResourceLocImageHeight);
//                drawImageOnly(pGuiGraphics, this.BGHoveredImageLoc, getSBSBtnPosX(), getSBSBtnPosY(), this.BlitButtonWidth, this.BlitButtonHeight, this.ButtonWidth, this.ButtonHeight, this.ResourceLocImageWidth, this.ResourceLocImageHeight);
                renderHoveredBtn(pGuiGraphics);
            }
            else {
                renderNormalBtn(pGuiGraphics);
            }
//            else {
//                drawImageOnly(pGuiGraphics,  this.BGImageLoc, getSBSBtnPosX(), getSBSBtnPosY(), this.BlitButtonWidth, this.BlitButtonHeight, this.ButtonWidth, this.ButtonHeight, this.ResourceLocImageWidth, this.ResourceLocImageHeight);
//            }
            if (isHovered()) {
                if (this.hasText) {
                    renderTextMsg(pGuiGraphics);
                }
            }
        }
        pGuiGraphics.pose().popPose();
    }

    public void renderNormalBtn(GuiGraphics pGuiGraphics) {
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate(0, 0, this.TranslatedZ + 3);

        drawImageOnly(pGuiGraphics, this.BGImageLoc, getSBSBtnPosX(), getSBSBtnPosY(), this.BlitButtonWidth, this.BlitButtonHeight, this.ButtonWidth, this.ButtonHeight, this.ResourceLocImageWidth, this.ResourceLocImageHeight);
//        GuiMethods.oldBlit(pGuiGraphics, this.BGImageLoc, getSBSBtnPosX(), getSBSBtnPosY(), this.BlitButtonWidth, this.BlitButtonHeight, this.ButtonWidth, this.ButtonHeight, this.ResourceLocImageWidth, this.ResourceLocImageHeight);


        pGuiGraphics.pose().popPose();
    }

    public void renderHoveredBtn(GuiGraphics pGuiGraphics) {
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate(0, 0, this.TranslatedZ + 2);

        drawImageOnly(pGuiGraphics, this.BGHoveredImageLoc, getSBSBtnPosX(), getSBSBtnPosY(), this.BlitButtonWidth, this.BlitButtonHeight, this.ButtonWidth, this.ButtonHeight, this.ResourceLocImageWidth, this.ResourceLocImageHeight);
//        GuiMethods.oldBlit(pGuiGraphics, this.BGHoveredImageLoc, getSBSBtnPosX(), getSBSBtnPosY(), this.BlitButtonWidth, this.BlitButtonHeight, this.ButtonWidth, this.ButtonHeight, this.ResourceLocImageWidth, this.ResourceLocImageHeight);

        pGuiGraphics.pose().popPose();
    }

    //play pressed btn sound
//    @Override
//    public void playDownSound(SoundManager soundHandlerIn) {
//        soundHandlerIn.play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK.get(), 1.0F, 1.0F));
//    }

    public boolean hasPressedBool() {
        return this.hasPressedBool;
    }
    public void setIsBtnPressedAction(boolean a) {
        this.pressedBool = a;
    }

    public void setPressedBool(boolean hasPressedBool, ResourceLocation PressedBoolResLoc, int ticks) {
        this.hasPressedBool = hasPressedBool;
        this.pressedBoolResLoc = PressedBoolResLoc;
        this.pressedBtnTicks = ticks;
    }

    private void renderTextMsg(GuiGraphics pGuiGraphics) {
        pGuiGraphics.drawCenteredString(getMinecraftInstanceFont(), this.ButtonText,
                getSBSBtnPosX() + this.ButtonTextRelativePosX + width / 2,
                getSBSBtnPosY() + this.ButtonTextRelativePosY + height / 5,
                this.ButtonTextColor);
    }

    public void setText(String text, int buttonTextRelativePosX, int buttonTextRelativePosY, int buttonTextColor) {
        this.hasText = true;
        this.ButtonText = text;
//        this.ButtonTextPosX = getSBSBtnPosX() + buttonTextRelativePosX + width / 2;
//        this.ButtonTextPosY = getSBSBtnPosY() + buttonTextRelativePosY + height / 5;
        this.ButtonTextRelativePosX = buttonTextRelativePosX;
        this.ButtonTextRelativePosY = buttonTextRelativePosY;
        this.ButtonTextColor = buttonTextColor;
    }

    public int getButtonOriginalPosX() { return this.ButtonOriginalPosX; }
    public void setButtonOriginalPosX(int buttonOriginalPosX) { this.ButtonOriginalPosX = buttonOriginalPosX; }
    public int getButtonOriginalPosY() { return this.ButtonOriginalPosY; }
    public void setButtonOriginalPosY(int buttonOriginalPosY) { this.ButtonOriginalPosY = buttonOriginalPosY; }

    public ResourceLocation getBGImageLoc() { return this.BGImageLoc; }
    public void setBGImageLoc(ResourceLocation imageLoc) { this.BGImageLoc = imageLoc; }
}

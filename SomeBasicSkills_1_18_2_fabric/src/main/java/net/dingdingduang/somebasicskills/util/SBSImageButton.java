package net.dingdingduang.somebasicskills.util;

import net.minecraft.client.util.math.MatrixStack;
import net.dingdingduang.somebasicskills.globalmethods.GuiMethods;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftInstanceFont;

public class SBSImageButton extends ButtonWidget {
    private Identifier BGImageLoc;
    private Identifier BGHoveredImageLoc;
    private int BlitButtonWidth;
    private int BlitButtonHeight;
    private int ButtonWidth;
    private int ButtonHeight;
    private int ResourceLocImageWidth;
    private int ResourceLocImageHeight;
    protected PressAction Function;

    //Btn original pos
    private int ButtonOriginalPosX = 0;
    private int ButtonOriginalPosY = 0;

    //hover
    private boolean hasHoverBool = false;

    //translateZ
    private int TranslatedZ = 0;

    //pressedAnimation
    private boolean pressedBool = false;
    private Identifier pressedBoolResLoc;
    private boolean hasPressedBool = false;
    private int tempPressesButtonTicks;
    private int pressedBtnTicks;

    //text setup
    private boolean hasText = false;
    private String ButtonText;
    private int ButtonTextRelativePosX;
    private int ButtonTextRelativePosY;
    private int ButtonTextColor;

    public SBSImageButton(int x, int y, int width, int height, Text message, Identifier BGImageLoc, boolean hasHover, Identifier BGHoveredImageLoc,
                          int blitButtonWidth, int blitButtonHeight, int ButtonWidth, int ButtonHeight, int ResourceLocImageWidth, int ResourceLocImageHeight, int translateZ) {
        super(x, y, width, height, message, b -> {});
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

    public int getSBSBtnPosX() { return this.x; }
    public void setSBSBtnPosX(int newX) { this.x = newX; }
    public int getSBSBtnPosY() { return this.y; }
    public void setSBSBtnPosY(int newY) { this.y = newY; }

    public boolean getIsBtnHovered() { return this.hovered; }

    public void setPressedFuncAction(PressAction Function) {
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
    public void renderButton(MatrixStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        pPoseStack.push();
        pPoseStack.translate(0, 0, this.TranslatedZ);
        if (this.pressedBool) {
            if (this.tempPressesButtonTicks > 0) { this.tempPressesButtonTicks--; }
            else { this.pressedBool = false; }
            GuiMethods.drawImageOnly(pPoseStack, pressedBoolResLoc, getSBSBtnPosX(), getSBSBtnPosY(), this.BlitButtonWidth, this.BlitButtonHeight, this.ButtonWidth, this.ButtonHeight, this.ResourceLocImageWidth, this.ResourceLocImageHeight);
        }
        else {
            GuiMethods.drawImageOnly(pPoseStack, this.BGImageLoc, getSBSBtnPosX(), getSBSBtnPosY(), this.BlitButtonWidth, this.BlitButtonHeight, this.ButtonWidth, this.ButtonHeight, this.ResourceLocImageWidth, this.ResourceLocImageHeight);

            if (this.hasHoverBool && getIsBtnHovered()) {
                GuiMethods.drawImageOnly(pPoseStack, this.BGHoveredImageLoc, getSBSBtnPosX(), getSBSBtnPosY(), this.BlitButtonWidth, this.BlitButtonHeight, this.ButtonWidth, this.ButtonHeight, this.ResourceLocImageWidth, this.ResourceLocImageHeight);
            }
            if (getIsBtnHovered()) {
                if (this.hasText) {
                    renderTextMsg(pPoseStack);
                }
            }
        }
        pPoseStack.pop();
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

    public void setPressedBool(boolean hasPressedBool, Identifier PressedBoolResLoc, int ticks) {
        this.hasPressedBool = hasPressedBool;
        this.pressedBoolResLoc = PressedBoolResLoc;
        this.pressedBtnTicks = ticks;
    }

    private void renderTextMsg(MatrixStack pPoseStack) {
        GuiMethods.drawCenteredString(pPoseStack, getMinecraftInstanceFont(), this.ButtonText,
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

    public Identifier getBGImageLoc() { return this.BGImageLoc; }
    public void setBGImageLoc(Identifier imageLoc) { this.BGImageLoc = imageLoc; }
}

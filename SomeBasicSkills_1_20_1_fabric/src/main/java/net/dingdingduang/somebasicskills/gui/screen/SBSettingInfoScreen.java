package net.dingdingduang.somebasicskills.gui.screen;

import net.dingdingduang.somebasicskills.globalmethods.GuiMethods;
import net.dingdingduang.somebasicskills.resourcelocation.icon.IconBasicResourceLocation;
import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.dingdingduang.somebasicskills.util.SBSImageButton;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getComponentWithSpecifiedString;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftInstance;
import static net.dingdingduang.somebasicskills.globalmethods.GuiMethods.CreateImageButton;
import static net.dingdingduang.somebasicskills.globalmethods.LocaleLanguageMethods.getLocalizationText;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.*;

public class SBSettingInfoScreen extends Screen {
    private double scrollDist = 0.0D;

    private int MaxTextLength;
    private int IncrementTextLengthVal;

    private boolean isAllSBSettingBtnsRendered = false;
    private HashMap<String, SBSImageButton> ConfigName2SettingBtn;
    private HashMap<String, Integer> ConfigName2SettingBtnCounter;

    private boolean showSBSettingDetailsScreen = false;
    private SBSettingDetailsScreen LocalSBSettingDetailsScreen;

    public SBSettingInfoScreen(String title) {
        this(getComponentWithSpecifiedString(getLocalizationText(title)));
    }

    protected SBSettingInfoScreen(Text component) { super(component); }

    private TextRenderer getFont() { return this.textRenderer; }
    private void removeWidget(Element btn) { this.remove(btn); }

    @Override
    public void init() {
        super.init();
        this.MaxTextLength = width - 32;
//        this.IncrementTextLengthVal = (int) (this.MaxTextLength * 0.1666667f);
        this.IncrementTextLengthVal = (int) (this.MaxTextLength * 0.143f);
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float partialTick) {
        MatrixStack pPoseStack = drawContext.getMatrices();
        // Make sure we use a higher z-index.
        pPoseStack.push();
        pPoseStack.translate(0, 0, 201);


        if (this.showSBSettingDetailsScreen()) {
            this.LocalSBSettingDetailsScreen.render(drawContext, mouseX, mouseY, partialTick);
        }
        else {
            // Background and frame.
            this.renderBackground(drawContext);

            // Render screen.
            super.render(drawContext, mouseX, mouseY, partialTick);

            if (!this.isAllSBSettingBtnsRendered) {
                renderAllSettingBtns(pPoseStack);
            }
            renderAllSettingBtnsDescription(pPoseStack);
        }

        pPoseStack.pop();
    }

    public void renderAllSettingBtns(MatrixStack pPoseStack) {
        // Make sure we use a higher z-index.
        pPoseStack.push();
        pPoseStack.translate(0, 0, 202);

        this.isAllSBSettingBtnsRendered = true;
        this.ConfigName2SettingBtn = new HashMap<>();
        this.ConfigName2SettingBtnCounter = new HashMap<>();
        SBSImageButton tempBtn;

        int counter = 0;
        HashMap<String, HashMap<String, MethodConfigHelper>> tempClientConfigSetting = getCPlayerConfig2Settings();
        for (String configName : tempClientConfigSetting.keySet()) {
            int yPos = getYPos(counter);
            tempBtn = CreateImageButton(0, yPos, 16, 16, IconBasicResourceLocation.GUI_SB_GENERAL_SETTING_ICON, true, IconBasicResourceLocation.GUI_SB_GENERAL_SETTING_ICON_GLOW, 202);
            HashMap<String, MethodConfigHelper> tempConfigDetailSetting = tempClientConfigSetting.get(configName);
            tempBtn.setPressedFuncAction((btn) -> {
                //get new screen
                pressedSBDetailsSettingBtnAction(new SBSettingDetailsScreen(configName, tempConfigDetailSetting));
            });
            if (!this.ConfigName2SettingBtn.containsKey(configName)) {
                addDrawableChild(tempBtn);
            }
            tempBtn.setButtonOriginalPosY(yPos);
            this.ConfigName2SettingBtnCounter.put(configName, counter);
            this.ConfigName2SettingBtn.put(configName, tempBtn);
            counter++;
        }

        pPoseStack.pop();
    }

    private int getYPos(int counter) { return 18 * counter + 8; }
    private int getYTextPos(int exCounter) { return 4 + 8 * exCounter; }

    public void renderAllSettingBtnsDescription(MatrixStack pPoseStack) {
        pPoseStack.push();
        pPoseStack.translate(0, 0, 203);
        HashMap<String, HashMap<String, MethodConfigHelper>> tempClientConfigSetting = getCPlayerConfig2Settings();

        int intRoundedScrollDist =  (int) Math.round(this.scrollDist);

        StringBuilder tempStrBuilder1;
        int totalExtraYCounter = 0;
        for (String configName : tempClientConfigSetting.keySet()) {
            SBSImageButton tempImgBtn = this.ConfigName2SettingBtn.get(configName);
            int currentUpdatedBtnPos = getYPos(this.ConfigName2SettingBtnCounter.get(configName)) + getYTextPos(totalExtraYCounter) + intRoundedScrollDist;
            ArrayList<String> tempSeparatedStrArrList = separateString(getLocalizationText(configName));
            if (tempSeparatedStrArrList.size() > 1) {
                int extraYCounter = 0;
                for (String tempSeparatedStr : tempSeparatedStrArrList) {
                    tempStrBuilder1 = new StringBuilder();
                    tempStrBuilder1.append(tempSeparatedStr);
                    GuiMethods.drawString(pPoseStack, getFont(), tempStrBuilder1.toString(), 32, currentUpdatedBtnPos + 4 + 8 * extraYCounter, 0xFFFFFF);
                    extraYCounter = extraYCounter + 1;
                }
                tempImgBtn.setButtonOriginalPosY( currentUpdatedBtnPos );
                tempImgBtn.setSBSBtnPosY(tempImgBtn.getButtonOriginalPosY());
                totalExtraYCounter = totalExtraYCounter + extraYCounter;

            }
            else {
                tempStrBuilder1 = new StringBuilder();
                tempStrBuilder1.append(tempSeparatedStrArrList.get(0));

                GuiMethods.drawString(pPoseStack, getFont(), tempStrBuilder1.toString(), 32, tempImgBtn.getSBSBtnPosY() + 4, 0xFFFFFF);
                tempImgBtn.setButtonOriginalPosY( currentUpdatedBtnPos );
                tempImgBtn.setSBSBtnPosY(tempImgBtn.getButtonOriginalPosY());
            }
        }
        pPoseStack.pop();
    }

    public ArrayList<String> separateString(String str1) {
        ArrayList<String> separatedStringList = new ArrayList<>();

        int beginIndex = 0;
        int endIndex = this.IncrementTextLengthVal;
        int StringLength = str1.length();

        if (StringLength <= this.IncrementTextLengthVal) {
            separatedStringList.add(str1.substring(beginIndex));
        }
        else {
            while (StringLength > this.IncrementTextLengthVal) {
                separatedStringList.add(str1.substring(beginIndex, endIndex));
                StringLength -= this.IncrementTextLengthVal;
                beginIndex += this.IncrementTextLengthVal;
                endIndex += this.IncrementTextLengthVal;
            }
            separatedStringList.add(str1.substring(beginIndex));
        }

        return separatedStringList;
    }

    public void hideBtns() {
        for (SBSImageButton tempBtn: this.ConfigName2SettingBtn.values()) {
            removeWidget(tempBtn);
        }
    }
    public void showBtns() {
        for (SBSImageButton tempBtn: this.ConfigName2SettingBtn.values()) {
            addDrawableChild(tempBtn);
        }
    }

    public boolean showSBSettingDetailsScreen() {
        return this.showSBSettingDetailsScreen && this.LocalSBSettingDetailsScreen != null;
    }

    public void pressedSBDetailsSettingBtnAction(SBSettingDetailsScreen infoScreen) {
        hideBtns();
        this.showSBSettingDetailsScreen = true;
        this.LocalSBSettingDetailsScreen = infoScreen;
        this.LocalSBSettingDetailsScreen.init(getMinecraftInstance(), width, height);
    }

    @Override
    public void renderBackground(DrawContext drawContext) {
//        this.fillGradient(pPoseStack, 0, 0, this.width, this.height, -0x50FFEFF0, -0x50FFEFF0);
        drawContext.fillGradient(0, 0, this.width, this.height, -0x50FFEFF0, -0x50FFEFF0);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) {
            super.mouseClicked(mouseX, mouseY, button);
        }
        if (this.showSBSettingDetailsScreen()) {
            if (this.LocalSBSettingDetailsScreen.isMouseOver(mouseX, mouseY)) {
                this.LocalSBSettingDetailsScreen.mouseClicked(mouseX, mouseY, button);
            }
            else {
                this.showSBSettingDetailsScreen = false;
            }
            return false;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scroll) {
        if (this.showSBSettingDetailsScreen()) {
            this.LocalSBSettingDetailsScreen.mouseScrolled(mouseX, mouseY, scroll);
        }
        else {
            if (this.isAllSBSettingBtnsRendered) {
                this.scrollDist = this.scrollDist + scroll;

                if (this.scrollDist <= -512) {
                    this.scrollDist = -511;
                } else if (this.scrollDist >= 1) {
                    this.scrollDist = 0;
                }
            }
        }

        return super.mouseScrolled(mouseX, mouseY, scroll);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (pKeyCode == GLFW.GLFW_KEY_SPACE || pKeyCode == GLFW.GLFW_KEY_ENTER) { return false; }

        if (pKeyCode == GLFW.GLFW_KEY_ESCAPE) {
            if (this.showSBSettingDetailsScreen()) {
                showBtns();
                this.showSBSettingDetailsScreen = false;
                return false;
            }
            else {
                return super.keyPressed(pKeyCode, pScanCode, pModifiers);
            }
        }
        else {
            return super.keyPressed(pKeyCode, pScanCode, pModifiers);
        }
    }
}

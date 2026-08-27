package net.dingdingduang.somebasicskills.gui.screen;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.resourcelocation.icon.IconBasicResourceLocation;
import net.dingdingduang.somebasicskills.util.MethodConfigAction;
import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.dingdingduang.somebasicskills.util.SBSImageButton;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getComponentWithSpecifiedString;
import static net.dingdingduang.somebasicskills.globalmethods.GuiMethods.CreateImageButton;
import static net.dingdingduang.somebasicskills.globalmethods.LocaleLanguageMethods.getLocalizationText;

@OnlyIn(Dist.CLIENT)
public class SBSettingDetailsScreen extends Screen {
    private double scrollDist = 0.0D;

    private int MaxTextLength;
    private int IncrementTextLengthVal;

    private boolean isAllSBSettingBtnsRendered = false;
    private HashMap<String, SBSImageButton> ConfigName2SettingBtn;
    private HashMap<String, Integer> ConfigName2SettingBtnCounter;
    private HashMap<String, MethodConfigHelper> ConfigActionMap;
    private String ParentOptionName;

    public SBSettingDetailsScreen(String title, HashMap<String, MethodConfigHelper> configActionMap) {
        this(getComponentWithSpecifiedString(getLocalizationText(title)));
        this.ParentOptionName = title;
        this.ConfigActionMap = configActionMap;
    }

    protected SBSettingDetailsScreen(Component component) { super(component); }

    @Override
    public void init() {
        super.init();
        this.MaxTextLength = width - 32;
//        this.IncrementTextLengthVal = (int) (this.MaxTextLength * 0.1666667f);
        this.IncrementTextLengthVal = (int) (this.MaxTextLength * 0.143f);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Make sure we use a higher z-index.
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0, 0, 210);

        // Background and frame.
        this.renderBackground(guiGraphics);

        // Render screen.
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        if (!this.isAllSBSettingBtnsRendered) {
            renderAllSettingBtns(guiGraphics);
        }
        renderAllSettingBtnsDescription(guiGraphics);

        guiGraphics.pose().popPose();
    }

    public void renderAllSettingBtns(GuiGraphics guiGraphics) {
        // Make sure we use a higher z-index.
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0, 0, 211);

        this.isAllSBSettingBtnsRendered = true;
        this.ConfigName2SettingBtn = new HashMap<>();
        this.ConfigName2SettingBtnCounter = new HashMap<>();
        SBSImageButton tempBtn;

        int counter = 0;
        for (String configName : this.ConfigActionMap.keySet()) {
            int yPos = getYPos(counter);
            tempBtn = CreateImageButton(0, yPos, 16, 16, IconBasicResourceLocation.GUI_SB_GENERAL_SETTING_ICON, true, IconBasicResourceLocation.GUI_SB_GENERAL_SETTING_ICON_GLOW, 202);
            tempBtn.setPressedFuncAction((btn) -> {
                MethodConfigAction tempAction = this.ConfigActionMap.get(configName).getMethodAction();
                if (tempAction != null) {
                    tempAction.ConfigSetting(this.ParentOptionName, configName, this.ConfigActionMap.get(configName));
                }
            });
            if (!this.ConfigName2SettingBtn.containsKey(configName)) {
                addRenderableWidget(tempBtn);
            }
            tempBtn.setButtonOriginalPosY(yPos);
            this.ConfigName2SettingBtnCounter.put(configName, counter);
            this.ConfigName2SettingBtn.put(configName, tempBtn);
            counter++;
        }

        guiGraphics.pose().popPose();
    }

    private int getYPos(int counter) { return 18 * counter + 8; }
    private int getYTextPos(int exCounter) { return 4 + 8 * exCounter; }

    public void renderAllSettingBtnsDescription(GuiGraphics guiGraphics) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0, 0, 212);
        HashMap<String, MethodConfigHelper> tempBtn2Setting = this.ConfigActionMap;

        int intRoundedScrollDist =  (int) Math.round(this.scrollDist);

        StringBuilder tempStrBuilder1;
        int totalExtraYCounter = 0;
        for (String configName : tempBtn2Setting.keySet()) {
            SBSImageButton tempImgBtn = this.ConfigName2SettingBtn.get(configName);
            int currentUpdatedBtnPos = getYPos(this.ConfigName2SettingBtnCounter.get(configName)) + getYTextPos(totalExtraYCounter) + intRoundedScrollDist;
            ArrayList<String> tempSeparatedStrArrList = separateString(getLocalizationText(configName));
            if (tempSeparatedStrArrList.size() > 1) {
                int extraYCounter = 0;
                for (String tempSeparatedStr : tempSeparatedStrArrList) {
                    tempStrBuilder1 = new StringBuilder();
                    tempStrBuilder1.append(tempSeparatedStr);
                    guiGraphics.drawString(this.font, tempStrBuilder1.toString(), 32, currentUpdatedBtnPos + 4 + 8 * extraYCounter, 0xFFFFFF);
                    extraYCounter = extraYCounter + 1;
                }
                tempStrBuilder1 = new StringBuilder();
                MethodConfigHelper configHelper = this.ConfigActionMap.get(configName);
                if (configHelper.isBooleanConfig()) {
                    tempStrBuilder1.append( (configHelper.getIntValue() == 0) ? getLocalizationText(Constants.SB_GENERAL_SETTING_BOOLEAN_NO) : getLocalizationText(Constants.SB_GENERAL_SETTING_BOOLEAN_YES));
                    guiGraphics.drawString(this.font, tempStrBuilder1.toString(), this.IncrementTextLengthVal * 6 + 38, currentUpdatedBtnPos + 4 + 4 * extraYCounter, 0xFFFFFF);
                }
                else {
                    tempStrBuilder1.append(configHelper.getIntValue());
                    guiGraphics.drawString(this.font, tempStrBuilder1.toString(), this.IncrementTextLengthVal * 6 + 38, currentUpdatedBtnPos + 4 + 4 * extraYCounter, 0xFFFFFF);
                }

//                    tempImgBtn.setButtonOriginalPosY( currentUpdatedBtnPos + 8 * extraYCounter );
                tempImgBtn.setButtonOriginalPosY( currentUpdatedBtnPos );
                tempImgBtn.setSBSBtnPosY(tempImgBtn.getButtonOriginalPosY());
                totalExtraYCounter = totalExtraYCounter + extraYCounter;
//                    tempStrBuilder1 = new StringBuilder();
//                    tempStrBuilder1.append(tempSeparatedStrArrList.get(0));
//                    tempStrBuilder1.append((tempBtn2Boolean.get(configName) == 0) ? getLocalizationText(Constants.SB_GENERAL_SETTING_BOOLEAN_NO) : getLocalizationText(Constants.SB_GENERAL_SETTING_BOOLEAN_YES));
//                    guiGraphics.drawString(this.font, tempStrBuilder1.toString(), 32, this.ConfigName2SettingBtn.get(configName).getY() + 4, 0xFFFFFF);
            }
            else {
                tempStrBuilder1 = new StringBuilder();
                tempStrBuilder1.append(tempSeparatedStrArrList.get(0));
                MethodConfigHelper configHelper = this.ConfigActionMap.get(configName);
                if (configHelper.isBooleanConfig()) {
                    tempStrBuilder1.append( (configHelper.getIntValue() == 0) ? getLocalizationText(Constants.SB_GENERAL_SETTING_BOOLEAN_NO) : getLocalizationText(Constants.SB_GENERAL_SETTING_BOOLEAN_YES));
                }
                else {
                    tempStrBuilder1.append(configHelper.getIntValue());
                }
                guiGraphics.drawString(this.font, tempStrBuilder1.toString(), 32, tempImgBtn.getY() + 4, 0xFFFFFF);
                tempImgBtn.setButtonOriginalPosY( currentUpdatedBtnPos );
                tempImgBtn.setSBSBtnPosY(tempImgBtn.getButtonOriginalPosY());
            }
        }
        guiGraphics.pose().popPose();
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

    @Override
    public void renderBackground(GuiGraphics guiGraphics) {
        guiGraphics.fillGradient(0, 0, this.width, this.height, -0x50FFEFF0, -0x50FFEFF0);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scroll) {
        if (this.isAllSBSettingBtnsRendered) {
//            this.scrollDist = (Math.abs(this.scrollDist) > 132) ? ((scroll >= 0) ? 128 : -128) : this.scrollDist + scroll;
            this.scrollDist = this.scrollDist + scroll;

            if (this.scrollDist <= -512) {
                this.scrollDist = -511;
            }
            else if (this.scrollDist >= 1) {
                this.scrollDist = 0;
            }

//            for (SBSImageButton tempBTN : this.ConfigName2SettingBtn.values()) {
//                tempBTN.setSBSBtnPosY(tempBTN.getButtonOriginalPosY() + (int) Math.round(this.scrollDist));
//            }
        }

        return super.mouseScrolled(mouseX, mouseY, scroll);
    }
}

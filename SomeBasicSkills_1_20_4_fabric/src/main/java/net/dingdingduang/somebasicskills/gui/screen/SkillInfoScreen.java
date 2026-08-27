package net.dingdingduang.somebasicskills.gui.screen;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.globalmethods.GuiMethods;
import net.dingdingduang.somebasicskills.networking.NetworkingSendMsgMethods;
import net.dingdingduang.somebasicskills.resourcelocation.icon.IconBasicResourceLocation;
import net.dingdingduang.somebasicskills.gui.screen.widget.SkillInfoWidget;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.dingdingduang.somebasicskills.util.SBSImageButton;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static net.dingdingduang.somebasicskills.globalmethods.ClientPlayerMethods.getClientPlayerXpLevel;
import static net.dingdingduang.somebasicskills.globalmethods.ClientPlayerMethods.getClientPlayerTotalExpPoints;
import static net.dingdingduang.somebasicskills.globalmethods.ClientPlayerMethods.giveClientPlayerExpPoints;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getComponentWithSpecifiedString;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GuiMethods.CreateImageButton;
import static net.dingdingduang.somebasicskills.globalmethods.GuiMethods.drawImageOnly;
import static net.dingdingduang.somebasicskills.globalmethods.LocaleLanguageMethods.getLocalizationText;
import static net.dingdingduang.somebasicskills.globalmethods.SoundMethods.PlayLocalKeyboardErrSound;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.*;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getRole2IconLocMap;

public class SkillInfoScreen extends Screen {
    private static final Identifier MC_DEFAULT_WINDOW_BAKCGROUND =  getMCResourceLocation(Constants.MOD_ID, "textures/gui/window.png");

    private SkillInfoWidget skillInfoPanel;
    private SBSImageButton IMGbuttonAdd;
    private SBSImageButton IMGbuttonSub;
    private SBSImageButton IMGbuttonMax;
    private boolean isAddBtnGray = false;
    private boolean isSubBtnGray = false;
    private boolean isMaxBtnGray = false;
    private boolean isBtnMeetRequirements = false;
    private boolean isBtnGray = false;

    private boolean isntAddBtnRendered = true;
    private boolean isntSubBtnRendered = true;
    private boolean isntMaxBtnRendered = true;

    private int maxWidth;
    private int maxHeight;
    private int top;
    private int left;

    private String description;
    private String RoleCategory;
    private String SkillID;

    private PlayerEntity triggeredLocalPlayer;
    private HashMap<String, Integer> triggeredLocalPlayerSkillLVLMap;
    private SkillDataJson SkillData;

    //Skill Priority
    private SBSImageButton SkillPrioritySettingBtn;
    private boolean isntSkillPrioritySettingBtnRendered = true;
    private boolean isntSkillPrioritySettingAssignedYet = true;
    private String SkillPrioritySettingText;

    //Skill Cooldown Delay
    private SBSImageButton SkillCooldownDelaySettingBtn;
    private boolean isntSkillCooldownDelaySettingBtnRendered = true;
    private boolean isntSkillCooldownDelaySettingAssignedYet = true;
    private String SkillCooldownDelaySettingText;

    //keyboard
    private SBSImageButton KeyboardQuickSlotBtn;
    private boolean isntKeyboardQuickSlotBtnRendered = true;
    private boolean isntKeyboardQuickSlotAssignedYet = true;
    private String SkillQuickslot;

    private SBSImageButton KeyComboSettingBtn;
    private boolean isntKeyComboSettingBtnRendered = true;
    private boolean isntKeyComboSettingAssignedYet = true;
    private String SkillKeyComb;

//    public SkillInfoScreen(String title, String skillDescription, String roleCategory, String skillID, PlayerEntity player) {
    public SkillInfoScreen(String title, String roleCategory, String skillID, PlayerEntity player) {
        this(getComponentWithSpecifiedString(getLocalizationText(title)));
//        this.description = getLocalizationText(skillDescription);
        this.RoleCategory = roleCategory;
        this.SkillID = skillID;
        this.triggeredLocalPlayer = player;
        this.SkillData = getID2SkillData().get(this.SkillID);
        this.triggeredLocalPlayerSkillLVLMap = getClientPlayerSkillID2lvlMap();

        this.description = getUpdatedDescription();
    }

    protected SkillInfoScreen(Text component) {
        super(component);
    }

    private TextRenderer getFont() { return this.textRenderer; }
    private void removeWidget(Element btn) { this.remove(btn); }

    private String getUpdatedDescription() {
        int currentSkillLevel = 0;
        if (this.triggeredLocalPlayerSkillLVLMap.containsKey(this.SkillID)) {
            currentSkillLevel = this.triggeredLocalPlayerSkillLVLMap.get(this.SkillID);
        }
        return this.SkillData.getTranslatableTooltipGetter().getTooltipFromSkillData(this.SkillData, currentSkillLevel);
    }

    private List<String> prepareInfoContent() {
        List<String> info = new ArrayList<>();
        String tempHexColorString;
        int tempCurrentLVL;
        if (this.SkillData.getRequiredLevel() > 0) {
            tempCurrentLVL = getClientPlayerXpLevel(this.triggeredLocalPlayer);
            int updateRequiredLevel;

            if (this.triggeredLocalPlayerSkillLVLMap.containsKey(this.SkillID)) {
                updateRequiredLevel = this.SkillData.getRequiredLevel() + this.triggeredLocalPlayerSkillLVLMap.get(this.SkillID) * this.SkillData.getLvlUpgInterval();
            }
            else {
                updateRequiredLevel = this.SkillData.getRequiredLevel();
            }

//            info.add("Required PlayerEntity XP Level:");
            info.add(getLocalizationText("somebasicskills.gui.skillinfo.RequiredXP"));
            tempHexColorString = (isntSatisfiedLevelRequirement()) ? "|cFFFF0303" : "|cFF20C000";
            info.add(String.format("%s (LV%d/LV%d)", tempHexColorString,
                    tempCurrentLVL,
                    updateRequiredLevel));
            info.add(" ");

        }

        //check pre requisites
        if (!this.SkillData.getPreRequisiteSkillID().isEmpty()) {
            ArrayList<String> tempPreRequisiteSkillIDList = this.SkillData.getPreRequisiteSkillID();
            ArrayList<Integer> tempPreRequisiteSkillRequiredLVLList = this.SkillData.getPreRequisiteSkillRequiredLVL();
            String preRequisiteSkillID;
            int preRequisiteSkillRequiredLVL;
//            info.add("Pre-Requisites:");
            info.add(getLocalizationText("somebasicskills.gui.skillinfo.PreRequisites"));
            for (int i = 0; i < tempPreRequisiteSkillIDList.size(); i++) {
                preRequisiteSkillID = tempPreRequisiteSkillIDList.get(i);
                preRequisiteSkillRequiredLVL = tempPreRequisiteSkillRequiredLVLList.get(i);
                tempCurrentLVL = (this.triggeredLocalPlayerSkillLVLMap.containsKey(preRequisiteSkillID)) ? this.triggeredLocalPlayerSkillLVLMap.get(preRequisiteSkillID) : 0;

                tempHexColorString = (tempCurrentLVL >= preRequisiteSkillRequiredLVL) ? "|cFF20C000": "|cFFFF0303";

//                info.add(String.format("%s%s (LV%d/LV%d)", tempHexColorString, getLocalizationText(Constants.SKILL_TITLE+preRequisiteSkillID),
                info.add(String.format("%s%s (LV%d/LV%d)", tempHexColorString, getLocalizationText(getID2SkillData().get(preRequisiteSkillID).getTranslatableTextTitle()),
                        tempCurrentLVL,
                        preRequisiteSkillRequiredLVL));
            }
            info.add(" ");
        }

        // Display description.
//        info.add(this.description);
        String[] splitArr = this.description.split("\n");
        for (int i = 0; i < splitArr.length; i++) {
//            info.add((i < splitArr.length-1) ? splitArr[i] : splitArr[i] + "\n");
//            printInGameMsg("l: "+splitArr.length+", "+splitArr[i]);
//            printInGameMsg("length: "+splitArr[i].length());

            info.add((splitArr[i].length() <= 1) ? splitArr[i]+" " : splitArr[i]);
        }

//        printInGameMsg(info.toString());
        return info;
    }

    private void refreshSkillInfoDescription() {
        this.description = getUpdatedDescription();
        this.skillInfoPanel.setInfo(prepareInfoContent());
    }

    @Override
    public void init() {
        maxHeight = Math.min(height - 30, 260);
        //    maxHeight = Math.min(height - 78, 260);
        maxWidth = 250;
        left = (width - maxWidth) / 2;
        top = (height - maxHeight) / 2;

        //    this.skillInfoPanel = new SkillInfoWidget(minecraft, maxWidth - 18, maxHeight - 38, top + 18, left + 3);
        this.skillInfoPanel = new SkillInfoWidget(client, maxWidth - 18, maxHeight - 38 - 48, top + 18 + 48, left + 3);
        this.skillInfoPanel.setInfo(prepareInfoContent());

        this.addDrawableChild(this.skillInfoPanel);

        SBSImageButton tempIMGbutton;
        tempIMGbutton = CreateImageButton(left+34+12, top+26+18, 18, 18, IconBasicResourceLocation.ICON_ADD, true, IconBasicResourceLocation.ICON_ADD_HOVERGLOW, 203);
        tempIMGbutton.setPressedFuncAction(b -> triggerAddBtnAction());
        tempIMGbutton.setPressedBool(true, IconBasicResourceLocation.ICON_ADD_PRESSED, 5);
        this.IMGbuttonAdd = tempIMGbutton;

        tempIMGbutton = CreateImageButton(left+34+18+12, top+26+18, 18, 18, IconBasicResourceLocation.ICON_SUB, true, IconBasicResourceLocation.ICON_SUB_HOVERGLOW, 203);
        tempIMGbutton.setPressedFuncAction(b -> triggerSubBtnAction());
        tempIMGbutton.setPressedBool(true, IconBasicResourceLocation.ICON_SUB_PRESSED, 5);
        this.IMGbuttonSub = tempIMGbutton;

        tempIMGbutton = CreateImageButton(left+34+18*2+12, top+26+18, 18, 18, IconBasicResourceLocation.ICON_MAX, true, IconBasicResourceLocation.ICON_MAX_HOVERGLOW, 203);
        tempIMGbutton.setPressedFuncAction(b -> triggerMaxBtnAction());
        tempIMGbutton.setPressedBool(true, IconBasicResourceLocation.ICON_MAX_PRESSED, 5);
        this.IMGbuttonMax = tempIMGbutton;

        //Skill order
        tempIMGbutton = CreateImageButton(left+152, top+18, 16, 16, IconBasicResourceLocation.GUI_PRIORITY_SETTING_ICON, true, IconBasicResourceLocation.GUI_PRIORITY_SETTING_ICON_GLOW, 206);
        tempIMGbutton.setPressedFuncAction(b -> triggerAssignSkillPriorityBtnAction());
        this.SkillPrioritySettingBtn = tempIMGbutton;

        tempIMGbutton = CreateImageButton(left+188, top+18, 16, 16, IconBasicResourceLocation.GUI_SKILL_COOLDOWN_DELAY_ICON, true, IconBasicResourceLocation.GUI_SKILL_COOLDOWN_DELAY_ICON_GLOW, 206);
        tempIMGbutton.setPressedFuncAction(b -> triggerAssignSkillCooldownDelayBtnAction());
        this.SkillCooldownDelaySettingBtn = tempIMGbutton;

        //Keyboard
        tempIMGbutton = CreateImageButton(left+152, top+35, 16, 16, IconBasicResourceLocation.KEYBOARD_QUCIKSLOT_ASSIGN, true, IconBasicResourceLocation.KEYBOARD_QUCIKSLOT_ASSIGN_GLOW, 206);
        tempIMGbutton.setPressedFuncAction(b -> triggerAssignQuickSlotBtnAction());
        this.KeyboardQuickSlotBtn = tempIMGbutton;

        tempIMGbutton = CreateImageButton(left+152, top+52, 16, 16, IconBasicResourceLocation.KEY_COMBO_SETTING, true, IconBasicResourceLocation.KEY_COMBO_SETTING_GLOW, 206);
        tempIMGbutton.setPressedFuncAction(b -> triggerAssignKeyComboSettingBtnAction());
        this.KeyComboSettingBtn = tempIMGbutton;
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float partialTick) {
        MatrixStack pPoseStack = drawContext.getMatrices();
        // Make sure we use a higher z-index.
        pPoseStack.push();
        pPoseStack.translate(0, 0, 201);

        // Background and frame.
        this.renderBackground(drawContext, mouseX, mouseY, partialTick);

        // Render screen.
        super.render(drawContext, mouseX, mouseY, partialTick);

        // Render skill info panel.
        if (this.skillInfoPanel != null) {
            this.skillInfoPanel.render(drawContext, mouseX, mouseY, partialTick);

            renderSkillButtonBG(pPoseStack);
            renderSkillButtons(pPoseStack);

            //render text
            //      renderSkillLevelText(guiGraphics, "LV"+Math.round(Math.random()*100), left+4+14+12, top+35+18, 0xFFFFFF);
            if (this.triggeredLocalPlayerSkillLVLMap.containsKey(this.SkillID)) {
                renderSkillLevelText(pPoseStack, "LV"+this.triggeredLocalPlayerSkillLVLMap.get(this.SkillID), left + 4 + 14 + 12, top + 35 + 18, 0xFFFFFF);
            }
            else {
                renderSkillLevelText(pPoseStack, "LV0", left + 4 + 14 + 12, top + 35 + 18, 0xFFFFFF);
            }
            //show xp
            renderSkillLevelText(pPoseStack, String.valueOf(SkillData.getXpCostPerLeveling()), left + 4 + 14 + 12+96, top + 35 + 15, 0xFFFFFF);

            if (!this.SkillData.isPassiveType()) {
                //skill priority
                renderSkillPriorityBtn(pPoseStack);
                //skill cooldown delay
                renderSkillCooldownDelayBtn(pPoseStack);

                //keyboard
                renderKeyboardBtns(pPoseStack);
                renderKeyboardText(pPoseStack);
            }
        }

        pPoseStack.pop();
    }

    @Override
    public void renderBackground(DrawContext drawContext, int mouseX, int mouseY, float partialTick) {
        MatrixStack pPoseStack = drawContext.getMatrices();
        // Make sure we use a higher z-index.
        pPoseStack.push();
        pPoseStack.translate(0, 0, 201);

        // Background and frame.
        int heightPerPart = maxHeight / 2;
        RenderSystem.enableBlend();

        // Frame will be constructed in two parts, top and bottom.
        GuiMethods.drawImageOnly(pPoseStack, MC_DEFAULT_WINDOW_BAKCGROUND, left, top, 0, 0, maxWidth, heightPerPart, 256, 256);
        GuiMethods.drawImageOnly(pPoseStack,
                MC_DEFAULT_WINDOW_BAKCGROUND,
                left,
                top + heightPerPart,
                0,
                150 - heightPerPart,
                maxWidth,
                heightPerPart + 10,
                256, 256);

        // Background with gradient.
        //0x80 FF EF F0, alpha, red, green, blue
        //    guiGraphics.fillGradient(
        //            left + 9, top + 18, left + maxWidth - 9, top + maxHeight - 20, -0xFFEFF0, -0xFFEFF0);
//        guiGraphics.fillGradient(
//                left + 9, top + 18, left + maxWidth - 9, top + maxHeight - 20, -0x80FFEFF0, -0x80FFEFF0);
//        this.fillGradient(pPoseStack,
//                left + 9, top + 17, left + maxWidth - 7, top + maxHeight - 19, -0x00FFEFF0, -0x00FFEFF0);
        drawContext.fillGradient(left + 9, top + 17, left + maxWidth - 7, top + maxHeight - 19, -0x00FFEFF0, -0x00FFEFF0);
        RenderSystem.disableBlend();

        // Title
        GuiMethods.drawString(pPoseStack, getFont(), this.title.getString(), left + 6, top + 6, 0xFFFFFF);

        pPoseStack.pop();
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseY >= this.top
                && mouseY <= this.top + this.maxHeight
                && mouseX >= this.left
                && mouseX <= this.left + this.maxWidth;
    }

    public void renderSkillPriorityBtn(MatrixStack pPoseStack) {
        if (this.isntSkillPrioritySettingBtnRendered) {
            addDrawableChild(this.SkillPrioritySettingBtn);
            this.isntSkillPrioritySettingBtnRendered = false;
        }
    }

    public void renderSkillCooldownDelayBtn(MatrixStack pPoseStack) {
        if (this.isntSkillCooldownDelaySettingBtnRendered) {
            addDrawableChild(this.SkillCooldownDelaySettingBtn);
            this.isntSkillCooldownDelaySettingBtnRendered = false;
        }
    }

    public void renderKeyboardBtns(MatrixStack pPoseStack) {
        if (this.isntKeyboardQuickSlotBtnRendered) {
            addDrawableChild(this.KeyboardQuickSlotBtn);
            this.isntKeyboardQuickSlotBtnRendered = false;
        }
        if (this.isntKeyComboSettingBtnRendered) {
            addDrawableChild(this.KeyComboSettingBtn);
            this.isntKeyComboSettingBtnRendered = false;
        }
    }

    public void renderSkillButtonBG(MatrixStack pPoseStack) {
        pPoseStack.push();
        pPoseStack.translate(0, 0, 202);

        drawImageOnly(pPoseStack, IconBasicResourceLocation.ICON_BG_EXPAND, left+12, top+18, 140, 48);

        pPoseStack.pop();
    }

    public void renderSkillButtons(MatrixStack pPoseStack) {
        pPoseStack.push();
        pPoseStack.translate(0, 0, 203);

//        if (this.isBtnGray) { drawImageOnly(guiGraphics, getRole2IconLocMap().get(this.RoleCategory).get(Constants.GRAY_BTN_ICON_PREFIX + this.SkillID), left+4+12, top+3+18, 28, 28);}
        if (getClientPlayerXpLevel(this.triggeredLocalPlayer) < this.SkillData.getRequiredLevel()) { drawImageOnly(pPoseStack, getRole2IconLocMap().get(this.RoleCategory).get(Constants.GRAY_BTN_ICON_PREFIX + this.SkillID), left+4+12, top+3+18, 28, 28);}
        else { drawImageOnly(pPoseStack, getRole2IconLocMap().get(this.RoleCategory).get(this.SkillID), left+4+12, top+3+18, 28, 28); }

        //check conditions
//        if (!this.isBtnMeetRequirements) { renderSkillButtonOverlay(guiGraphics); }
        if (isntSatisfiedLevelRequirement()) {
            renderSkillButtonOverlay(pPoseStack);
            this.isAddBtnGray = true;
            this.isMaxBtnGray = true;
        }
        else if (isMaxLVL() || isntMeetWithPreRequisites() || isntSatifiedXpRequirement()) {
            this.isAddBtnGray = true;
            this.isMaxBtnGray = true;
        }
        if (!this.triggeredLocalPlayerSkillLVLMap.containsKey(this.SkillID) || couldntBeDowngraded()) {
            this.isSubBtnGray = true;
        }

        if (this.isAddBtnGray) {
            drawImageOnly(pPoseStack, IconBasicResourceLocation.DISICON_ADD, left+34+12, top+26+18, 18, 18);
            if (!this.isntAddBtnRendered) {
                this.IMGbuttonAdd.setIsBtnPressedAction(false);
                removeWidget(this.IMGbuttonAdd);
                this.isntAddBtnRendered = true;
            }
        }
        else {
            if (this.isntAddBtnRendered) {
                addDrawableChild(this.IMGbuttonAdd);
                this.isntAddBtnRendered = false;
            }
        }
        if (this.isSubBtnGray) {
            drawImageOnly(pPoseStack, IconBasicResourceLocation.DISICON_SUB, left+34+18+12, top+26+18, 18, 18);
            if (!this.isntSubBtnRendered) {
                this.IMGbuttonSub.setIsBtnPressedAction(false);
                removeWidget(this.IMGbuttonSub);
                this.isntSubBtnRendered = true;
            }
        }
        else {
            if (this.isntSubBtnRendered) {
                addDrawableChild(this.IMGbuttonSub);
                this.isntSubBtnRendered = false;
            }
        }
        if (this.isMaxBtnGray) {
            drawImageOnly(pPoseStack, IconBasicResourceLocation.DISICON_MAX, left+34+18*2+12, top+26+18, 18, 18);
            if (!this.isntMaxBtnRendered) {
                this.IMGbuttonMax.setIsBtnPressedAction(false);
                removeWidget(this.IMGbuttonMax);
                this.isntMaxBtnRendered = true;
            }
        }
        else {
            if (this.isntMaxBtnRendered) {
                addDrawableChild(this.IMGbuttonMax);
                this.isntMaxBtnRendered = false;
            }
        }

        //reset btn status to normal
        this.isAddBtnGray = false;
        this.isSubBtnGray = false;
        this.isMaxBtnGray = false;

        pPoseStack.pop();
    }

    public void renderSkillButtonOverlay(MatrixStack pPoseStack) {
        pPoseStack.push();
        pPoseStack.translate(0, 0, 204);

        RenderSystem.enableBlend();
        drawImageOnly(pPoseStack, IconBasicResourceLocation.ICON_OVERLAY, left+4+12, top+3+18, 28, 28);
        RenderSystem.disableBlend();

        pPoseStack.pop();
    }

    public void renderSkillLevelText(MatrixStack pPoseStack, String text, int x, int y, int pColor) {
        pPoseStack.push();
        pPoseStack.translate(0, 0, 205);

//        GuiMethods.drawString(pPoseStack, getFont(), text, x, y, pColor);
        GuiMethods.drawCenteredString(pPoseStack, getFont(), text, x, y, pColor);

        pPoseStack.pop();
    }

    //condition checking
    public boolean isMaxLVL() {
        if (this.triggeredLocalPlayerSkillLVLMap.containsKey(this.SkillID)) {
            return this.triggeredLocalPlayerSkillLVLMap.get(this.SkillID) >= this.SkillData.getTotalLevel();
        }
        else { return false; }
    }
    public boolean isntSatisfiedLevelRequirement() {
        if (this.triggeredLocalPlayerSkillLVLMap.containsKey(this.SkillID)) {
            return ( getClientPlayerXpLevel(this.triggeredLocalPlayer) < this.SkillData.getRequiredLevel() + this.triggeredLocalPlayerSkillLVLMap.get(this.SkillID) * this.SkillData.getLvlUpgInterval() );
        }
        else {
            return ( getClientPlayerXpLevel(this.triggeredLocalPlayer) < this.SkillData.getRequiredLevel() );
        }
    }
    public boolean isntSatifiedXpRequirement() {
        //if level got set to XX, totalXp won't be counted
        return ( getClientPlayerTotalExpPoints(this.triggeredLocalPlayer) < this.SkillData.getXpCostPerLeveling() );
    }
    public boolean isntMeetWithPreRequisites() {
        boolean isntMeetWithPreRequisites = false;
        if (!this.SkillData.getPreRequisiteSkillID().isEmpty()) {
            String preSkillID;
            int requiredLeveling;
            ArrayList<String> tempPreSkillIDList = this.SkillData.getPreRequisiteSkillID();
            ArrayList<Integer> tempPreSkillLVLRequirementList = this.SkillData.getPreRequisiteSkillRequiredLVL();

            for (int i = 0; i < tempPreSkillIDList.size(); i++) {
                preSkillID = tempPreSkillIDList.get(i);
                requiredLeveling = tempPreSkillLVLRequirementList.get(i);
                if (this.triggeredLocalPlayerSkillLVLMap.containsKey(preSkillID)) {
                    if (this.triggeredLocalPlayerSkillLVLMap.get(preSkillID) < requiredLeveling) {
                        isntMeetWithPreRequisites = true;
                        break;
                    }
                }
//                if (this.triggeredLocalPlayerSkillLVLMap.containsKey(preSkillID) && this.triggeredLocalPlayerSkillLVLMap.get(preSkillID) < requiredLeveling) {
//                    isntMeetWithPreRequisites = true;
//                    break;
//                }
                else {
                    isntMeetWithPreRequisites = true;
                    break;
                }
            }
        }
        return isntMeetWithPreRequisites;
    }
    public boolean couldntBeDowngraded() {
        if (!this.SkillData.getAdvancedSkillID().isEmpty()) {
            ArrayList<String> AdvancedSkillPreRequisiteSkillID;
            ArrayList<Integer> AdvancedSkillPreRequisiteSkillRequiredLVL;
            for (String AdvancedSkillID: this.SkillData.getAdvancedSkillID()) {
                AdvancedSkillPreRequisiteSkillID = getID2SkillData().get(AdvancedSkillID).getPreRequisiteSkillID();
                AdvancedSkillPreRequisiteSkillRequiredLVL = getID2SkillData().get(AdvancedSkillID).getPreRequisiteSkillRequiredLVL();
                for (int j = 0; j < AdvancedSkillPreRequisiteSkillID.size(); j++) {
//                    if (AdvancedSkillPreRequisiteSkillID.get(j).equals(this.SkillID)) {
//                        if (this.triggeredLocalPlayerSkillLVLMap.containsKey(AdvancedSkillID)) {
//                            if (this.triggeredLocalPlayerSkillLVLMap.get(this.SkillID) <= AdvancedSkillPreRequisiteSkillRequiredLVL.get(j)) {
////                                this.isSubBtnGray = true;
//                                return true;
//                            }
//                        }
//                    }

                    if (AdvancedSkillPreRequisiteSkillID.get(j).equals(this.SkillID) && this.triggeredLocalPlayerSkillLVLMap.containsKey(AdvancedSkillID) && this.triggeredLocalPlayerSkillLVLMap.get(this.SkillID) <= AdvancedSkillPreRequisiteSkillRequiredLVL.get(j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isAddBtnGray() { return this.isAddBtnGray; }
    public void setAddBtnGray(boolean addBtnGray) { this.isAddBtnGray = addBtnGray; }
    public void triggerAddBtnAction() {
        this.isSubBtnGray = false;

        int tempTriggeredLocalPlayerSkillLVL = 1;
        if (this.triggeredLocalPlayerSkillLVLMap.containsKey(this.SkillID)) {
            tempTriggeredLocalPlayerSkillLVL = this.triggeredLocalPlayerSkillLVLMap.get(this.SkillID)+1;
            this.triggeredLocalPlayerSkillLVLMap.put(this.SkillID, tempTriggeredLocalPlayerSkillLVL);
        }
        else {
            this.triggeredLocalPlayerSkillLVLMap.put(this.SkillID, tempTriggeredLocalPlayerSkillLVL);
        }

        NetworkingSendMsgMethods.SendPlayerSkillLevelFromClientSideToServer(this.SkillID, tempTriggeredLocalPlayerSkillLVL);

        giveClientPlayerExpPoints(this.triggeredLocalPlayer, -this.SkillData.getXpCostPerLeveling());
//        giveServerPlayerExpPoints(this.triggeredLocalPlayer, -this.SkillData.getXpCostPerLeveling());


        if (isMaxLVL() || isntSatisfiedLevelRequirement() || isntMeetWithPreRequisites()) {
            this.isAddBtnGray = true;
            this.isMaxBtnGray = true;
        }

        refreshSkillInfoDescription();

        //Action
//        if (this.SkillData.getPressAddBtnAction() != null) {
//            this.SkillData.getPressAddBtnAction().executeAction(this.triggeredServerPlayer);
//        }

        NetworkingSendMsgMethods.SendAddBtnActionFromClientToServer(this.SkillID);
    }

    public boolean isSubBtnGray() { return this.isSubBtnGray; }
    public void setSubBtnGray(boolean subBtnGray) { this.isSubBtnGray = subBtnGray; }
    public void triggerSubBtnAction() {
        this.isAddBtnGray = false;
        this.isMaxBtnGray = false;

        int i;
        if (this.triggeredLocalPlayerSkillLVLMap.containsKey(this.SkillID)) {

            i = this.triggeredLocalPlayerSkillLVLMap.get(this.SkillID)-1;
            if (i <= 0) {
                this.isSubBtnGray = true;
                this.triggeredLocalPlayerSkillLVLMap.remove(this.SkillID);
            }
            else {
                this.triggeredLocalPlayerSkillLVLMap.put(this.SkillID, i);
            }


            giveClientPlayerExpPoints(this.triggeredLocalPlayer, this.SkillData.getXpCostPerLeveling());
//            giveServerPlayerExpPoints(this.triggeredLocalPlayer, this.SkillData.getXpCostPerLeveling());

            //Action
//            if (this.SkillData.getPressSubBtnAction() != null) {
//                this.SkillData.getPressSubBtnAction().executeAction(this.triggeredServerPlayer);
//            }

            NetworkingSendMsgMethods.SendPlayerSkillLevelFromClientSideToServer(this.SkillID, i);
            NetworkingSendMsgMethods.SendSubBtnActionFromClientToServer(this.SkillID);
        }

        if (couldntBeDowngraded()) {
            this.isSubBtnGray = true;
        }

        refreshSkillInfoDescription();
    }

    public boolean isMaxBtnGray() { return this.isMaxBtnGray; }
    public void setMaxBtnGray(boolean maxBtnGray) { this.isMaxBtnGray = maxBtnGray; }
    public void triggerMaxBtnAction() {
        this.isSubBtnGray = false;

        //calculate max lvl the player could reach
        int currentLVL = 0;
        if (this.triggeredLocalPlayerSkillLVLMap.containsKey(this.SkillID)) {
            currentLVL = this.triggeredLocalPlayerSkillLVLMap.get(this.SkillID);
        }
        int totalLVL = this.SkillData.getTotalLevel();

        int i = 0;
        while (i < totalLVL-currentLVL) {
            if (getClientPlayerXpLevel(this.triggeredLocalPlayer) >= this.SkillData.getRequiredLevel() + (currentLVL + i) * this.SkillData.getLvlUpgInterval()) {
                i++;
                giveClientPlayerExpPoints(this.triggeredLocalPlayer, -this.SkillData.getXpCostPerLeveling());
//                giveServerPlayerExpPoints(this.triggeredLocalPlayer, -this.SkillData.getXpCostPerLeveling());

                //Action
//                if (this.SkillData.getPressAddBtnAction() != null) {
//                    this.SkillData.getPressAddBtnAction().executeAction(this.triggeredServerPlayer);
//                }
            }
            else {
                break;
            }
        }

        this.triggeredLocalPlayerSkillLVLMap.put(this.SkillID, currentLVL+i);

        if (isMaxLVL() || isntSatisfiedLevelRequirement() || isntMeetWithPreRequisites()) {
            this.isAddBtnGray = true;
            this.isMaxBtnGray = true;
        }

        refreshSkillInfoDescription();

        NetworkingSendMsgMethods.SendPlayerSkillLevelFromClientSideToServer(this.SkillID, currentLVL+i);
        NetworkingSendMsgMethods.SendMaxBtnActionFromClientToServer(this.SkillID, i);
    }

    public boolean isBtnMeetRequirements() { return this.isBtnMeetRequirements; }
    public void setIsBtnMeetRequirements(boolean btnLimited) { this.isBtnMeetRequirements = btnLimited; }
    public boolean isBtnGray() { return this.isBtnGray; }
    public void setIsBtnGray(boolean btnGray) { this.isBtnGray = btnGray; }

    public void refreshAddBtn() { this.isntAddBtnRendered = true; }
    public void refreshSubBtn() { this.isntSubBtnRendered = true; }
    public void refreshMaxBtn() { this.isntMaxBtnRendered = true; }

    //Skill Priority Setting
    public void triggerAssignSkillPriorityBtnAction() {
        if (this.isntKeyboardQuickSlotAssignedYet && this.isntKeyComboSettingAssignedYet && this.isntSkillCooldownDelaySettingAssignedYet && !SkillData.isPassiveType()) {
            if (this.isntSkillPrioritySettingAssignedYet) {
//                printInGameMsg("setting skill priority...");
                removeWidget(this.SkillCooldownDelaySettingBtn);
                setCPlayerAssigningSkillPriority(true);

                this.isntSkillPrioritySettingAssignedYet = false;
            }
            else {
                addDrawableChild(this.SkillCooldownDelaySettingBtn);
                completeAssignSkillPriority();
                setCPlayerAssigningSkillPriority(false);
                this.isntSkillPrioritySettingAssignedYet = true;

                if (getCPlayerCurrentKeyCombo().isEmpty()) {
                    getCPlayerSkillID2Priority().remove(this.SkillID);
                    this.SkillPrioritySettingText = "0";
                }
            }

            //prevent keycombo match to null SkillID
            clearCPlayerCurrentKeyCombo();
        }
        else {
            PlayLocalKeyboardErrSound(1.0f, 0.6f);
        }
    }
    public void completeAssignSkillPriority() {
        ArrayList<Integer> keyCodeCombo = getCPlayerCurrentKeyCombo();

        StringBuilder sb1 = new StringBuilder();
        for (int i: keyCodeCombo) {
            sb1.append((char) i);
        }

        String tempSkillPriorityString = sb1.toString();
        if (!tempSkillPriorityString.isEmpty()) {
            Integer tempSkillPriorityInt = (Integer) Math.min(Integer.parseInt(tempSkillPriorityString), 255);
            HashMap<String, Integer> SkillID2Priority = getCPlayerSkillID2Priority();
            if (tempSkillPriorityInt > 0) {
                SkillID2Priority.put(this.SkillID, tempSkillPriorityInt);
//                this.SkillPrioritySettingText = tempSkillPriorityString;
                this.SkillPrioritySettingText = tempSkillPriorityInt.toString();
            }
            else {
                SkillID2Priority.remove(this.SkillID);
                this.SkillPrioritySettingText = "0";
            }
        }
    }



    //Skill Cooldown Delay Setting
    public void triggerAssignSkillCooldownDelayBtnAction() {
        if (this.isntKeyboardQuickSlotAssignedYet && this.isntKeyComboSettingAssignedYet && this.isntSkillPrioritySettingAssignedYet && !SkillData.isPassiveType()) {
            if (this.isntSkillCooldownDelaySettingAssignedYet) {
                setCPlayerAssigningSkillCooldownDelay(true);
                setCPlayerAppendedPeriodForSkillCooldownDelay(false);

                this.isntSkillCooldownDelaySettingAssignedYet = false;
            }
            else {
                completeAssignSkillCooldownDelay();
                setCPlayerAssigningSkillCooldownDelay(false);
                setCPlayerAppendedPeriodForSkillCooldownDelay(false);
                this.isntSkillCooldownDelaySettingAssignedYet = true;

                if (getCPlayerCurrentKeyCombo().isEmpty()) {
                    getCPlayerSkillID2CooldownDelay().remove(this.SkillID);
                    this.SkillCooldownDelaySettingText = "0.0";
                }
            }

            //prevent keycombo match to null SkillID
            clearCPlayerCurrentKeyCombo();
        }
        else {
            PlayLocalKeyboardErrSound(1.0f, 0.6f);
        }
    }
    public void completeAssignSkillCooldownDelay() {
        ArrayList<Integer> keyCodeCombo = getCPlayerCurrentKeyCombo();

        StringBuilder sb1 = new StringBuilder();
        for (int i: keyCodeCombo) {
            sb1.append((char) i);
        }

        String tempSkillCooldownFloatString = sb1.toString();
        if (!tempSkillCooldownFloatString.isEmpty()) {
            float tempSkillCooldownFloat = Float.parseFloat(tempSkillCooldownFloatString);
            if (tempSkillCooldownFloat > 60) { tempSkillCooldownFloat = 60; }
            HashMap<String, Float> SkillID2CooldownDelay = getCPlayerSkillID2CooldownDelay();
            if (tempSkillCooldownFloat > 0) {
                SkillID2CooldownDelay.put(this.SkillID, tempSkillCooldownFloat);
                this.SkillCooldownDelaySettingText = ((Float) tempSkillCooldownFloat).toString();
            }
            else {
                SkillID2CooldownDelay.remove(this.SkillID);
                this.SkillCooldownDelaySettingText = "0.0";
            }
        }
    }



    //keyboard
    public boolean getIsntKeyboardQuickSlotAssignedYet() { return this.isntKeyboardQuickSlotAssignedYet; }
    public boolean getIsntKeyComboSettingAssignedYet() { return this.isntKeyComboSettingAssignedYet; }
    public void triggerAssignQuickSlotBtnAction() {
        if (this.isntKeyComboSettingAssignedYet && this.isntSkillPrioritySettingAssignedYet && this.isntSkillCooldownDelaySettingAssignedYet && !SkillData.isPassiveType()) {
            if (this.isntKeyboardQuickSlotAssignedYet) {
                setCPlayerAssigningQuickslot(true);
                setCPlayerLastPressedKeyCode(-1);
                this.isntKeyboardQuickSlotAssignedYet = false;
            } else {
                completeAssignQuickSlot();
                setCPlayerAssigningQuickslot(false);
                this.isntKeyboardQuickSlotAssignedYet = true;

//                HashMap<Integer, String> Key2SkillID = getCPlayerKey2SkillID();

                if (getCPlayerLastPressedKeyCode() == -1) {
                    getCPlayerKey2SkillID().remove(-1);
                    getCPlayerSkillID2Key().remove(this.SkillID);
                    this.SkillQuickslot = "N/A";
                }
//                NetworkingSendMsgMethods.SendQuickSlotSettingFromClientSideToServer(Key2SkillID);
            }
        }
        else {
            PlayLocalKeyboardErrSound(1.0f, 0.6f);
        }
    }
    public void completeAssignQuickSlot() {
        HashMap<Integer, HashMap<String, Byte>> Key2SkillIDList = getCPlayerKey2SkillID();
        HashMap<String, Integer> SkillID2Key = getCPlayerSkillID2Key();

        int keyCode = getCPlayerLastPressedKeyCode();

        HashMap<String, Byte> tempSkillIDList;
        int previousKeyCode;
        //clear previous assigned key
        if (SkillID2Key.containsKey(this.SkillID)) {
            previousKeyCode = SkillID2Key.get(this.SkillID);
            if (Key2SkillIDList.containsKey(previousKeyCode)) {
                tempSkillIDList = Key2SkillIDList.get(previousKeyCode);
                tempSkillIDList.remove(this.SkillID);
                if (tempSkillIDList.isEmpty()) {
                    Key2SkillIDList.remove(previousKeyCode);
                }
            }
        }

        if (!Key2SkillIDList.containsKey(keyCode)) { Key2SkillIDList.put(keyCode, new HashMap<>()); }
        tempSkillIDList = Key2SkillIDList.get(keyCode);
        byte tempByte = 0;
        tempSkillIDList.put(this.SkillID, tempByte);

        SkillID2Key.put(this.SkillID, keyCode);
        StringBuilder sb1 = new StringBuilder();
        sb1.append((char) keyCode);
        this.SkillQuickslot = sb1.toString();

//            printInGameMsg("Last keyCode: "+keyCode);
//            printInGameMsg("Assigned to Key: " + Character.toString((char) keyCode));
    }

    public void triggerAssignKeyComboSettingBtnAction() {
        if (this.isntKeyboardQuickSlotAssignedYet && this.isntSkillPrioritySettingAssignedYet && this.isntSkillCooldownDelaySettingAssignedYet && !SkillData.isPassiveType()) {
            if (this.isntKeyComboSettingAssignedYet) {
//                printInGameMsg("setting combo...");
                setCPlayerAssigningKeyCombo(true);
//                setCPlayerCurrentKeyCombo(new ArrayList<Integer>());
                clearCPlayerCurrentKeyCombo();
                this.isntKeyComboSettingAssignedYet = false;
            } else {
//                printInGameMsg("setting combo complete!");
                completeAssignKeyCombo();
                setCPlayerAssigningKeyCombo(false);
                this.isntKeyComboSettingAssignedYet = true;

                if (getCPlayerCurrentKeyCombo().isEmpty()) {
                    getCPlayerKeyCombo2SkillID().remove(getCPlayerCurrentKeyCombo());
                    getCPlayerSkillID2KeyCombo().remove(this.SkillID);
                    this.SkillKeyComb = "N/A";
                }

                //prevent keycombo match to null SkillID
//                setCPlayerCurrentKeyCombo(new ArrayList<Integer>());
                clearCPlayerCurrentKeyCombo();

//                NetworkingSendMsgMethods.SendKeyComboSettingFromClientSideToServer(getCPlayerKeyCombo2SkillID());
            }
        }
        else {
            PlayLocalKeyboardErrSound(1.0f, 0.6f);
        }
    }
    public void completeAssignKeyCombo() {
        ArrayList<Integer> keyCodeCombo = getCPlayerCurrentKeyCombo(), previousKeyCodeCombo;
        HashMap<String, ArrayList<Integer>> tempId2Keycombo = getCPlayerSkillID2KeyCombo();
        HashMap<ArrayList<Integer>, HashMap<String, Byte>> tempKeyCombo2IdList = getCPlayerKeyCombo2SkillID();

//        printInGameMsg("current keycombo: "+tempCurrentKeyCombo.get(this.triggeredLocalPlayer));

        //clear previous keyCombo
        if (tempId2Keycombo.containsKey(this.SkillID)) {
            previousKeyCodeCombo = tempId2Keycombo.get(this.SkillID);
            if (tempKeyCombo2IdList.containsKey(previousKeyCodeCombo)) {
                HashMap<String, Byte> tempKeyCombo2Id = tempKeyCombo2IdList.get(previousKeyCodeCombo);
                tempKeyCombo2Id.remove(this.SkillID);
                if (tempKeyCombo2Id.isEmpty()) {
                    tempKeyCombo2IdList.remove(previousKeyCodeCombo);
                }
            }
        }

        tempId2Keycombo.put(this.SkillID, keyCodeCombo);
        if (!tempKeyCombo2IdList.containsKey(keyCodeCombo)) { tempKeyCombo2IdList.put(keyCodeCombo, new HashMap<>()); }
        HashMap<String, Byte> tempKeyCombo2Id = tempKeyCombo2IdList.get(keyCodeCombo);
        byte tempByte = 0;
        tempKeyCombo2Id.put(this.SkillID, tempByte);

        StringBuilder sb1 = new StringBuilder();
        for (int i: keyCodeCombo) {
            sb1.append((char) i);
        }

        this.SkillKeyComb = sb1.toString();

//            printInGameMsg("keycombo list: "+tempPlayerKeyCombo2SkillID.get(this.triggeredLocalPlayer));
    }

    public void renderKeyboardText(MatrixStack pPoseStack) {
        pPoseStack.push();
        pPoseStack.translate(0, 0, 201);

        if (this.SkillPrioritySettingText == null) {
            int tempCurrentSkillPriority = 0;
            if (getCPlayerSkillID2Priority().containsKey(this.SkillID)) {
                tempCurrentSkillPriority = getCPlayerSkillID2Priority().get(this.SkillID);
            }

            this.SkillPrioritySettingText = String.valueOf(tempCurrentSkillPriority);
        }

        if (this.SkillCooldownDelaySettingText == null) {
            Float tempCurrentSkillCooldownDelay = 0f;
            if (getCPlayerSkillID2CooldownDelay().containsKey(this.SkillID)) {
                tempCurrentSkillCooldownDelay = getCPlayerSkillID2CooldownDelay().get(this.SkillID);
            }

            this.SkillCooldownDelaySettingText = tempCurrentSkillCooldownDelay.toString();
        }

        if (this.SkillQuickslot == null) {
            if (getCPlayerSkillID2Key().containsKey(this.SkillID)) {
                int i = getCPlayerSkillID2Key().get(this.SkillID);
                StringBuilder sb1 = new StringBuilder();
                sb1.append((char) i);
                this.SkillQuickslot = sb1.toString();
            }
        }

        if (this.SkillKeyComb == null) {
            if (getCPlayerSkillID2KeyCombo().containsKey(this.SkillID)) {
                StringBuilder sb1 = new StringBuilder();
                ArrayList<Integer> tempKeyCombo = getCPlayerSkillID2KeyCombo().get(this.SkillID);
                for (int i: tempKeyCombo) {
                    sb1.append((char) i);
                }
                this.SkillKeyComb = sb1.toString();
            }
        }

        if (!this.isntKeyboardQuickSlotAssignedYet) {
            this.SkillQuickslot = "-> [" + (char) getCPlayerLastPressedKeyCode() + "]?";
        }

        if (!this.isntKeyComboSettingAssignedYet) {
            ArrayList<Integer> tempCurrentKeyCombo = getCPlayerCurrentKeyCombo();

            StringBuilder sb1 = new StringBuilder();
            for (int i : tempCurrentKeyCombo) {
                sb1.append((char) i);
            }
            this.SkillKeyComb = "-> ["+sb1.toString()+"]?";
        }

        if (!this.isntSkillPrioritySettingAssignedYet) {
            ArrayList<Integer> tempCurrentKeyCombo = getCPlayerCurrentKeyCombo();

            StringBuilder sb1 = new StringBuilder();
            for (int i : tempCurrentKeyCombo) {
                sb1.append((char) i);
            }
            String tempSkillPrioritySettingText = sb1.toString();
            if (tempSkillPrioritySettingText.length() <= 0) { tempSkillPrioritySettingText = "0"; }

            this.SkillPrioritySettingText = "-> ["+tempSkillPrioritySettingText+"]?";
        }

        if (!this.isntSkillCooldownDelaySettingAssignedYet) {
            ArrayList<Integer> tempCurrentKeyCombo = getCPlayerCurrentKeyCombo();

            StringBuilder sb1 = new StringBuilder();
            for (int i : tempCurrentKeyCombo) {
                sb1.append((char) i);
            }
            String tempSkillCooldownDelaySettingText = sb1.toString();
            if (tempSkillCooldownDelaySettingText.length() <= 0) { tempSkillCooldownDelaySettingText = "0.0"; }

            this.SkillCooldownDelaySettingText = "["+tempSkillCooldownDelaySettingText+"]?";
        }

        GuiMethods.drawString(pPoseStack, getFont(), (this.SkillPrioritySettingText == null) ? "0": this.SkillPrioritySettingText, left + 172, top + 24, 0xFFFFFF);
        if (this.isntSkillPrioritySettingAssignedYet) {
            GuiMethods.drawString(pPoseStack, getFont(), (this.SkillCooldownDelaySettingText == null) ? "0.0" : this.SkillCooldownDelaySettingText, left + 208, top + 24, 0xFFFFFF);
        }
        GuiMethods.drawString(pPoseStack, getFont(), (this.SkillQuickslot == null) ? "N/A": this.SkillQuickslot, left + 172, top + 41, 0xFFFFFF);
        GuiMethods.drawString(pPoseStack, getFont(), (this.SkillKeyComb == null) ? "N/A": this.SkillKeyComb, left + 172, top + 58, 0xFFFFFF);

        pPoseStack.pop();
    }
}

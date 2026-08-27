package net.dingdingduang.somebasicskills.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.event.SBPlayerConfigFileInitHelper;
import net.dingdingduang.somebasicskills.globalmethods.GuiMethods;
import net.dingdingduang.somebasicskills.networking.NetworkingSendMsgMethods;
import net.dingdingduang.somebasicskills.resourcelocation.icon.IconBasicResourceLocation;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.dingdingduang.somebasicskills.util.SBSImageButton;
import net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;
import static net.dingdingduang.somebasicskills.globalmethods.GuiMethods.CreateImageButton;
import static net.dingdingduang.somebasicskills.globalmethods.LocaleLanguageMethods.getLocalizationText;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.*;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.*;

public class RoleSelectionScreen extends Screen {
    private boolean isntAllBtnRendered = true;

    private int listWidth;

    private boolean showSkillTree = false;
    private SkillTreeScreen showSkillTreeScreen;

    private HashMap<String, SBSImageButton> CategoryName2Btn;
    private double scrollDist = 0.0D;

    private SBSImageButton ResetPlayerStateBtn;

    private SBSImageButton ClearSkillPrioritySettingBtn;
    private SBSImageButton ClearSkillCooldownSettingBtn;

    private SBSImageButton ClearQuickSlotSettingBtn;
    private SBSImageButton ClearKeyComboSettingBtn;

    private SBSImageButton LocalSBSettingInfoScreenTrigBtn;
    private boolean showSBSettingInfoScreen = false;
    private SBSettingInfoScreen LocalSBSettingInfoScreen;

    public RoleSelectionScreen() {
        this(getComponentWithSpecifiedString(getLocalizationText(Constants.GUI_ROLE_SELECTION_TITLE)));
    }

    public RoleSelectionScreen(Text component) {
        super(component);
    }

    private TextRenderer getFont() { return this.textRenderer; }
    private void removeWidget(Element btn) { this.remove(btn); }

    public boolean showSkillTree() {
        return this.showSkillTree && this.showSkillTreeScreen != null;
    }

    public boolean showSBSettingInfoScreen() {
        return this.showSBSettingInfoScreen && this.LocalSBSettingInfoScreen != null;
    }

    public void pressedSBSettingBtnAction(SBSettingInfoScreen infoScreen) {
        hideBtns();
        this.showSBSettingInfoScreen = true;
        this.LocalSBSettingInfoScreen = infoScreen;
        this.LocalSBSettingInfoScreen.init(getMinecraftInstance(), width, height);
    }

    @Override
    protected void init() {
        super.init();

        // Calculate viewport and general design
        this.listWidth = Math.max(width / 3, 100);
//        int topPosition = PADDING + 10;
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float partialTick) {
        MatrixStack pPoseStack = drawContext.getMatrices();
//        this.renderBackground(pPoseStack);
        this.renderBackground(drawContext, mouseX, mouseY, partialTick);

        // Render panels for category and overview
//        super.render(pPoseStack, mouseX, mouseY, partialTick);
        super.render(drawContext, mouseX, mouseY, partialTick);

        // Title
        GuiMethods.drawString(pPoseStack, getFont(), this.title.getString(), this.listWidth + 10 + 10, 8, 0xFFFFFF);

        if (this.isntAllBtnRendered) {
            HashMap<String, ArrayList<String>> Category2SkillID = getCategory2SkillID();
            HashMap<String, SkillDataJson> Category2Title = getRole2TitleMap();
            SkillDataJson tempSkillDataJson1;

            this.CategoryName2Btn = new HashMap<String, SBSImageButton>();

            SBSImageButton tempIMGbutton;

            int tempIntI, tempIntJ;

            HashMap<Integer, Integer> BtnYPos2ExtraYPos = new HashMap<Integer, Integer>();

            for (String tempCateogryName : Category2SkillID.keySet()) {
                if (Category2Title.containsKey(tempCateogryName)) {
                    tempSkillDataJson1 = Category2Title.get(tempCateogryName);
                }
                else {
                    printInGameMsg("Unknown CategoryName: "+tempCateogryName);
                    continue;
                }
//                tempIMGbutton = CreateImageButton(0, 0, 28, 28, new ResourceLocation(Constants.MOD_ID, Category2Title.get(tempCateogryName).getResLocBtn()));
                tempIMGbutton = CreateImageButton(0, 0, 28, 28, getRole2IconLocMap().get(tempCateogryName).get(tempCateogryName+Constants.SKILL_TITLE), 0);

                this.CategoryName2Btn.put(tempCateogryName, tempIMGbutton);
//                tempIntI = i*32+36;
//                tempIntJ = j*48+8;
                tempIntI = tempSkillDataJson1.getBtnPosX()*32+36;
                tempIntJ = tempSkillDataJson1.getBtnPosY()*48+32;

                if (tempIntI + 90 > width) {
                    int tempBTNPOSY = tempSkillDataJson1.getBtnPosY();
                    if (!BtnYPos2ExtraYPos.containsKey(tempBTNPOSY)) {
                        BtnYPos2ExtraYPos.put(tempBTNPOSY, (tempIntI + 90) / width);
                    }
                    else {
                        BtnYPos2ExtraYPos.put(tempBTNPOSY, Math.max(BtnYPos2ExtraYPos.get(tempBTNPOSY), tempIntI + 90) / width);
                    }
                }
                tempIMGbutton.setSBSBtnPosX(tempIntI);
                tempIMGbutton.setSBSBtnPosY(tempIntJ);

                tempIMGbutton.setButtonOriginalPosX(tempIntI);
                tempIMGbutton.setButtonOriginalPosY(tempIntJ);

//                tempIMGbutton.setPressedFuncAction(b -> getMinecraft().setScreen(new SkillTreeScreen(tempCateogryName)) );
                tempIMGbutton.setPressedFuncAction(b -> pressedRoleBtnAction(new SkillTreeScreen(tempCateogryName)) );
//                tempIMGbutton.setText(getLocalizationText(Constants.SKILLTREE_TITLE+tempCateogryName), 0, 24, 0xFFFFFF);
                tempIMGbutton.setText(getLocalizationText(tempSkillDataJson1.getTranslatableTextSkillTreeTitle()), 0, 24, 0xFFFFFF);
                addDrawableChild(tempIMGbutton);

            }
            //sort btn pos
            int k, n = 0;
            boolean shouldNincremented;
            for (int targetBtnPosY: BtnYPos2ExtraYPos.keySet()) {
                shouldNincremented = false;
                for (String BtnSkillID : this.CategoryName2Btn.keySet()) {
                    tempIMGbutton = this.CategoryName2Btn.get(BtnSkillID);
                    tempIntI = tempIMGbutton.getButtonOriginalPosX();
                    tempIntJ = tempIMGbutton.getButtonOriginalPosY();
                    if (getID2SkillData().get(BtnSkillID).getBtnPosY()+n == targetBtnPosY+n) {
                        if (tempIntI + 90 > width) {
                            //x
//                            k = (tempIntI + 90) % width;
                            k = (getID2SkillData().get(BtnSkillID).getBtnPosX() - ( (width-90) / 32) )*32+36;
                            tempIMGbutton.setButtonOriginalPosX(k);
                            tempIMGbutton.setSBSBtnPosX(k);
                            //y
                            k = tempIntJ + ( (tempIntI + 90) / width ) * 48;
                            tempIMGbutton.setButtonOriginalPosY(k);
                            tempIMGbutton.setSBSBtnPosY(k);

                            shouldNincremented = true;
                        }
                    }
                    else if (getID2SkillData().get(BtnSkillID).getBtnPosY()+n > targetBtnPosY+n) {
                        //y
                        k = tempIntJ + (n + 1) * 48;
                        tempIMGbutton.setButtonOriginalPosY(k);
                        tempIMGbutton.setSBSBtnPosY(k);
                    }
                }

                if (shouldNincremented) {
                    n++;
                }
            }

            this.ResetPlayerStateBtn = CreateImageButton(width-16, height-16, 16, 16, IconBasicResourceLocation.GUI_SB_RESET_STATE_ICON, true, IconBasicResourceLocation.GUI_SB_RESET_STATE_ICON_GLOW, 0);
            this.ResetPlayerStateBtn.setPressedFuncAction( (btn) -> {
                removeWidget(this.ResetPlayerStateBtn);
                NetworkingSendMsgMethods.SendResetPlayerStateRequestToServerSide();
                SBPlayerConfigFileInitHelper tempConfigStateHelper = new SBPlayerConfigFileInitHelper(null);
                tempConfigStateHelper.SBPlayerUnstuckRequestOnClient();
            });

            this.LocalSBSettingInfoScreenTrigBtn = CreateImageButton(width-88, 0, 16, 16, IconBasicResourceLocation.GUI_SB_GENERAL_SETTING_ICON, true, IconBasicResourceLocation.GUI_SB_GENERAL_SETTING_ICON_GLOW, 0);
            this.LocalSBSettingInfoScreenTrigBtn.setPressedFuncAction( (btn) -> pressedSBSettingBtnAction(new SBSettingInfoScreen(getLocalizationText(Constants.SB_GENERAL_SETTING))) );
            this.ClearSkillPrioritySettingBtn = CreateImageButton(width-70, 0, 16, 16, IconBasicResourceLocation.GUI_PRIORITY_RESET_SETTING_ICON, true, IconBasicResourceLocation.GUI_PRIORITY_RESET_SETTING_ICON_GLOW, 0);
            this.ClearSkillPrioritySettingBtn.setPressedFuncAction( (btn) -> resetSkillPriority());
            this.ClearSkillCooldownSettingBtn = CreateImageButton(width-52, 0, 16, 16, IconBasicResourceLocation.GUI_SKILL_RESET_COOLDOWN_DELAY_ICON, true, IconBasicResourceLocation.GUI_SKILL_RESET_COOLDOWN_DELAY_ICON_GLOW, 0);
            this.ClearSkillCooldownSettingBtn.setPressedFuncAction( (btn) -> resetSkillCooldownDelay());
            this.ClearQuickSlotSettingBtn = CreateImageButton(width-34, 0, 16, 16, IconBasicResourceLocation.KEYBOARD_RESET_QUCIKSLOT_ASSIGN, true, IconBasicResourceLocation.KEYBOARD_RESET_QUCIKSLOT_ASSIGN_GLOW, 0);
            this.ClearQuickSlotSettingBtn.setPressedFuncAction( (btn) -> resetQuickSlotSetting());
            this.ClearKeyComboSettingBtn = CreateImageButton(width-16, 0, 16, 16, IconBasicResourceLocation.KEY_COMBO_RESET_SETTING, true, IconBasicResourceLocation.KEY_COMBO_RESET_SETTING_GLOW, 0);
            this.ClearKeyComboSettingBtn.setPressedFuncAction( (btn) -> resetKeyComboSetting());

            addDrawableChild(this.ResetPlayerStateBtn);
            addDrawableChild(this.LocalSBSettingInfoScreenTrigBtn);
            addDrawableChild(this.ClearSkillPrioritySettingBtn);
            addDrawableChild(this.ClearSkillCooldownSettingBtn);
            addDrawableChild(this.ClearQuickSlotSettingBtn);
            addDrawableChild(this.ClearKeyComboSettingBtn);

            this.isntAllBtnRendered = false;
        }

        if (this.showSkillTree()) {
          this.showSkillTreeScreen.render(drawContext, mouseX, mouseY, partialTick);
        }

        if (this.showSBSettingInfoScreen()) {
            this.LocalSBSettingInfoScreen.render(drawContext, mouseX, mouseY, partialTick);
        }
    }

    public void pressedRoleBtnAction(SkillTreeScreen tempScreen) {
        hideBtns();

        this.showSkillTree = true;
        this.showSkillTreeScreen = tempScreen;
        this.showSkillTreeScreen.init(getMinecraftInstance(), width, height);
    }

    public void hideBtns() {
        for (SBSImageButton tempBtn: this.CategoryName2Btn.values()) {
            removeWidget(tempBtn);
        }
        removeWidget(this.ResetPlayerStateBtn);
        removeWidget(this.LocalSBSettingInfoScreenTrigBtn);
        removeWidget(this.ClearSkillPrioritySettingBtn);
        removeWidget(this.ClearSkillCooldownSettingBtn);
        removeWidget(this.ClearQuickSlotSettingBtn);
        removeWidget(this.ClearKeyComboSettingBtn);
    }
    public void showBtns() {
        for (SBSImageButton tempBtn: this.CategoryName2Btn.values()) {
            addDrawableChild(tempBtn);
        }
        if (this.ResetPlayerStateBtn != null) {
            addDrawableChild(this.ResetPlayerStateBtn);
        }
        if (this.LocalSBSettingInfoScreenTrigBtn != null) {
            addDrawableChild(this.LocalSBSettingInfoScreenTrigBtn);
        }
        if (this.ClearSkillPrioritySettingBtn != null) {
            addDrawableChild(this.ClearSkillPrioritySettingBtn);
        }
        if (this.ClearSkillCooldownSettingBtn != null) {
            addDrawableChild(this.ClearSkillCooldownSettingBtn);
        }
        if (this.ClearQuickSlotSettingBtn != null) {
            addDrawableChild(this.ClearQuickSlotSettingBtn);
        }
        if (this.ClearKeyComboSettingBtn != null) {
            addDrawableChild(this.ClearKeyComboSettingBtn);
        }
    }
    public void regenerateBtns() {
        this.isntAllBtnRendered = true;
    }

    @Override
    public void renderBackground(DrawContext drawContext, int mouseX, int mouseY, float partialTick) {
        // Background
    //    guiGraphics.fillGradient(0, 0, this.width, this.height, -0x80FFEFF0, -0x80FFEFF0);
    //    guiGraphics.fillGradient(0, this.height - 12, this.width, this.height, -0x80FFEFF0, -0x80FFEFF0);

//        this.fillGradient(pPoseStack, 0, 0, this.width, this.height, -0x50FFEFF0, -0x50FFEFF0);

//        drawContext.fillGradient(0, 0, this.width, this.height, -0x50FFEFF0, -0x50FFEFF0);
        RenderSystem.enableBlend();
        GuiMethods.drawImageOnly(drawContext.getMatrices(), IconBasicResourceLocation.SKILL_BACKGROUND, 0, 0, this.width, this.height);
        RenderSystem.disableBlend();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) {
            super.mouseClicked(mouseX, mouseY, button);
        }
        if (this.showSkillTree()) {
            if (this.showSkillTreeScreen.isMouseOver(mouseX, mouseY)) {
                this.showSkillTreeScreen.mouseClicked(mouseX, mouseY, button);
            }
            else {
                //close window
                this.showSkillTree = false;
            }
          return false;
        }
        if (this.showSBSettingInfoScreen()) {
            if (this.LocalSBSettingInfoScreen.isMouseOver(mouseX, mouseY)) {
                this.LocalSBSettingInfoScreen.mouseClicked(mouseX, mouseY, button);
            }
            else {
                this.showSBSettingInfoScreen = false;
            }
            return false;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (this.showSkillTree()) {
            this.showSkillTreeScreen.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
        }

        if (this.showSBSettingInfoScreen()) {
            this.LocalSBSettingInfoScreen.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
        }

        if (!this.showSkillTree() && !this.showSBSettingInfoScreen()) {
//            this.scrollDist = (Math.abs(this.scrollDist) > 132) ? ((scroll >= 0) ? 128 : -128) : this.scrollDist + scroll;
            this.scrollDist = this.scrollDist + scrollY;

            if (this.scrollDist <= -512) {
                this.scrollDist = -511;
            }
            else if (this.scrollDist >= 1) {
                this.scrollDist = 0;
            }

            for (SBSImageButton tempBTN : this.CategoryName2Btn.values()) {
                tempBTN.setSBSBtnPosY(tempBTN.getButtonOriginalPosY() + (int) Math.round(this.scrollDist));
            }
//            printInGameMsg("btn pos: " + tempposY);
//            printInGameMsg("Scroll dist: " + this.scrollDist + ", rounded int:" + (int) Math.round(this.scrollDist));
        }

        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public void tick() {
        //o.O
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (pKeyCode == GLFW.GLFW_KEY_SPACE || pKeyCode == GLFW.GLFW_KEY_ENTER) { return false; }

        if (pKeyCode == GLFW.GLFW_KEY_ESCAPE) {
            if (this.showSkillTree()) {
                this.showSkillTree = false;
                showBtns();
                //when closing current skill tree, saving keyboard setting
//            NetworkingSendMsgMethods.SendQuickSlotSettingFromClientSideToServer(getCPlayerKey2SkillID());
//            NetworkingSendMsgMethods.SendKeyComboSettingFromClientSideToServer(getCPlayerKeyCombo2SkillID());
                FileReadWriteMethods.SkillPriorityPlayernameFileWriteTo();
                FileReadWriteMethods.ClientQuickslotPlayernameFileWriteTo();
                FileReadWriteMethods.ClientKeycomboPlayernameFileWriteTo();
                return false;
            }
            else if (this.showSBSettingInfoScreen()) {
                showBtns();
                this.showSBSettingInfoScreen = false;
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

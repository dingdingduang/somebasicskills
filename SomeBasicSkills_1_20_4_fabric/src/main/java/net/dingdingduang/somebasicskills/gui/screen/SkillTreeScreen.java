package net.dingdingduang.somebasicskills.gui.screen;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.globalmethods.GuiMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.entity.player.PlayerEntity;

import net.dingdingduang.somebasicskills.util.SBSImageButton;

import static net.dingdingduang.somebasicskills.globalmethods.ClientPlayerMethods.getClientPlayerXpLevel;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getComponentWithSpecifiedString;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftInstance;
import static net.dingdingduang.somebasicskills.globalmethods.GuiMethods.CreateImageButton;
import static net.dingdingduang.somebasicskills.globalmethods.LocaleLanguageMethods.getLocalizationText;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.*;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getCategory2SkillID;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getRole2IconLocMap;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.HashMap;

public class SkillTreeScreen extends Screen {
    private boolean isntAllBtnRendered = true;

    private int listWidth;

    private boolean showSkillInfo = false;
    private SkillInfoScreen showSkillInfoScreen;

    private HashMap<String, SBSImageButton> ID2Btn;
    private double scrollDist = 0.0D;

    private String SkillCategory;

    public SkillTreeScreen() {
//        this(Component.literal("BlademasterSkill"));
//        this.SkillCategory = "BlademasterSkill";
        this(getComponentWithSpecifiedString("CommonSkill"));
        this.SkillCategory = "CommonSkill";
    }

    public SkillTreeScreen(Text component) {
        super(component);
    }

    public SkillTreeScreen(String category) {
        super(getComponentWithSpecifiedString(category));
        this.SkillCategory = category;
    }

    private TextRenderer getFont() { return this.textRenderer; }
    private void removeWidget(Element btn) { this.remove(btn); }

    public PlayerEntity getTriggeredLocalPlayer() {
        return getMinecraftInstance().player;
    }

//    public Minecraft getMinecraftInstance() {
////        return minecraft;
//        return getMinecraft();
//    }

//    public Font getFontRenderer() {
//        return font;
//    }

    public boolean showSkillInfo() {
        return this.showSkillInfo && this.showSkillInfoScreen != null;
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
        this.renderBackground(drawContext, mouseX, mouseY, partialTick);

        // Render panels for category and overview
        super.render(drawContext, mouseX, mouseY, partialTick);

        // Title
//        GuiMethods.drawString(pPoseStack, getFont(), this.title.getString(), this.listWidth + 10 + 10, 8, 0xFFFFFF);

        if (this.isntAllBtnRendered) {
            HashMap<String, ArrayList<String>> Category2SkillID = getCategory2SkillID();
            HashMap<String, Identifier> tempIconMap = getRole2IconLocMap().get(this.SkillCategory);
            SkillDataJson tempSkillDataJson1;
            Identifier tempBtnIcon;
            this.ID2Btn = new HashMap<String, SBSImageButton>();

            SBSImageButton tempIMGbutton;

            int tempIntI, tempIntJ;

            HashMap<Integer, Integer> BtnYPos2ExtraYPos = new HashMap<Integer, Integer>();

            for (String SKILL_ID : Category2SkillID.get(this.SkillCategory)) {
                tempSkillDataJson1 = getID2SkillData().get(SKILL_ID);
//                String skillTranslateTitle, skillTranslateDescription;
                String skillTranslateTitle;
                if (getClientPlayerXpLevel(getTriggeredLocalPlayer()) < tempSkillDataJson1.getRequiredLevel()) { tempBtnIcon = tempIconMap.get(Constants.GRAY_BTN_ICON_PREFIX + SKILL_ID); }
                else { tempBtnIcon = tempIconMap.get(SKILL_ID); }
//                tempIMGbutton = CreateImageButton(0, 0, 28, 28, tempIconMap.get(SKILL_ID), 0);
                tempIMGbutton = CreateImageButton(0, 0, 28, 28, tempBtnIcon, 0);

                this.ID2Btn.put(SKILL_ID, tempIMGbutton);
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

//                tempIMGbutton.setPressedFuncAction(b -> pressedSkillIDBtnAction(new SkillInfoScreen(Constants.SKILL_TITLE+SKILL_ID, Constants.SKILL_DESCRIPTION+SKILL_ID, this.SkillCategory, SKILL_ID, getTriggeredLocalPlayer()) ) );
                skillTranslateTitle = tempSkillDataJson1.getTranslatableTextTitle();
//                skillTranslateDescription = tempSkillDataJson1.getTranslatableTextDescription();
                tempIMGbutton.setPressedFuncAction(b -> pressedSkillIDBtnAction(new SkillInfoScreen(skillTranslateTitle, this.SkillCategory, SKILL_ID, getTriggeredLocalPlayer()) ) );
//                tempIMGbutton.setText(getLocalizationText(Constants.SKILL_TITLE+SKILL_ID), 0, 24, 0xFFFFFF);
                tempIMGbutton.setText(getLocalizationText(skillTranslateTitle), 0, 24, 0xFFFFFF);
                addDrawableChild(tempIMGbutton);

            }
            //sort btn pos
            int k, n = 0;
            boolean shouldNincremented;
            for (int targetBtnPosY: BtnYPos2ExtraYPos.keySet()) {
                shouldNincremented = false;
                for (String BtnSkillID : this.ID2Btn.keySet()) {
                    tempIMGbutton = this.ID2Btn.get(BtnSkillID);
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

            this.isntAllBtnRendered = false;
        }

        if (this.showSkillInfo()) {
          this.showSkillInfoScreen.render(drawContext, mouseX, mouseY, partialTick);
        }
    }

    public void pressedSkillIDBtnAction(SkillInfoScreen tempScreen) {
        this.showSkillInfo = true;
        this.showSkillInfoScreen = tempScreen;
        this.showSkillInfoScreen.init(getMinecraftInstance(), width, height);
    }

    public void hideBtns() {
        for (SBSImageButton tempBtn: this.ID2Btn.values()) {
            removeWidget(tempBtn);
        }
    }
    public void showBtns() {
        for (SBSImageButton tempBtn: this.ID2Btn.values()) {
            addDrawableChild(tempBtn);
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
//        this.fillGradient(poseStack, 0, 0, this.width, this.height, -0x50FFEFF0, -0x50FFEFF0);
        drawContext.fillGradient(0, 0, this.width, this.height, -0x50FFEFF0, -0x50FFEFF0);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) {
          super.mouseClicked(mouseX, mouseY, button);
        }
        if (this.showSkillInfo()) {
          if (this.showSkillInfoScreen.isMouseOver(mouseX, mouseY)) {
            this.showSkillInfoScreen.mouseClicked(mouseX, mouseY, button);
          } else {
            //close window
            this.showSkillInfo = false;
          }
          return false;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (this.showSkillInfo()) {
          this.showSkillInfoScreen.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
        }

        if (!this.showSkillInfo()) {
//            this.scrollDist = (Math.abs(this.scrollDist) > 132) ? ((scroll >= 0) ? 128 : -128) : this.scrollDist + scroll;
            this.scrollDist = this.scrollDist + scrollY;

            if (this.scrollDist <= -512) {
                this.scrollDist = -511;
            }
            else if (this.scrollDist >= 1) {
                this.scrollDist = 0;
            }

            for (SBSImageButton tempBTN : this.ID2Btn.values()) {
                tempBTN.setSBSBtnPosY(tempBTN.getButtonOriginalPosY() + (int) Math.round(this.scrollDist));
            }
//            printInGameMsg("btn pos: " + tempposY);
//            printInGameMsg("Scroll dist: " + this.scrollDist + ", rounded int:" + (int) Math.round(this.scrollDist));
        }

        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.showSkillInfo()) {
          this.showSkillInfoScreen.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }

        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public void tick() {
        //o.O
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (pKeyCode == GLFW.GLFW_KEY_SPACE || pKeyCode == GLFW.GLFW_KEY_ENTER) { return false; }

        if (pKeyCode == GLFW.GLFW_KEY_ESCAPE && this.showSkillInfo()) {
            this.showSkillInfo = false;
//            getSPlayerAssigningQuickslot().put(ConvertClientPlayerToServerPlayer(getMinecraft().player), false);
//            getSPlayerAssigningKeyCombo().put(ConvertClientPlayerToServerPlayer(getMinecraft().player), false);
            setCPlayerAssigningQuickslot(false);
            setCPlayerAssigningKeyCombo(false);
            return false;
        } else {
            return super.keyPressed(pKeyCode, pScanCode, pModifiers);
        }
    }
}

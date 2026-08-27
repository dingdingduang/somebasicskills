package net.dingdingduang.somebasicskills.gui.overlay;

import net.dingdingduang.somebasicskills.globalmethods.GuiMethods;
import net.dingdingduang.somebasicskills.resourcelocation.icon.IconBasicResourceLocation;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerSkillID2CooldownDelay;
import static net.dingdingduang.somebasicskills.sbsattributes.statusquery.AttributeClientPlayerStatusQueryMethods.*;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getClientPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getRole2IconLocMap;

public class SkillsInCooldownClientTimerOverlay {
    private boolean isCooldownTimerActive = false;
    private static final SkillsInCooldownClientTimerOverlay SKILLS_IN_COOLDOWN_CLIENT_TIMER_OVERLAY_INSTANCE = new SkillsInCooldownClientTimerOverlay();

    //20 ticks = 1 sec
//    private final int Ticks2Sec = 20;

//    private int unknownTick = 2;

    private int previousForgeGuiTick = -1;
    private int currentForgeGuiTick = -1;

    private HashMap<String, Integer> PlayerSkill2CooldownTicks;
    private HashMap<String, Double> PlayerSkillID2TotalCooldownTicks;
    private HashMap<String, Integer> PlayerSkillIconIndex;

    //render skill icons
    //TODO customize whether to show or not on client side
    private boolean isIconVisible = true;
    private boolean showCooldown = true;

    //TODO customize icon size and (questionable) font size?
//    private final int SkillIconSize = 14;
//    private final int SkillIconFrameSize = 16;

    public void drawSquareImage(MatrixStack pPoseStack, int x, int y, int translateZ, Identifier iconResLoc, int size) {
        pPoseStack.push();
        pPoseStack.translate(0, 0, translateZ);
        GuiMethods.drawImageOnly(pPoseStack, iconResLoc, x, y, size, size, size, size, size, size);
        pPoseStack.pop();
    }

    public void fillGradient(DrawContext drawContext, MatrixStack pPoseStack, int x1, int y1, int x2, int y2, int translateZ, int pColor) {
        pPoseStack.push();
        pPoseStack.translate(0, 0, translateZ);

        GuiMethods.guiUtilsFillGradient(drawContext, x1, y1, x2, y2, pColor, pColor, translateZ);

        pPoseStack.pop();
    }

    public void drawCenterString(MatrixStack pPoseStack, int x1, int y1, int translateZ, String text, int pColor) {
        pPoseStack.push();
        pPoseStack.translate(0, 0, translateZ);

        GuiMethods.drawCenteredString(pPoseStack, getMinecraftInstanceFont(), text, x1, y1, pColor);

        pPoseStack.pop();
    }

    public void renderSkillIcons(DrawContext drawContext, MatrixStack pPoseStack, int screenWidth, int screenHeight, SkillDataJson skill1, int skillTickElapsed, double skillCooldownTime, int tempSkillIndex) {
        if (getRole2IconLocMap().containsKey(skill1.getRoleCategoryName())) {
            int SkillIconSize = 14, SkillIconFrameSize = 16;

            int skillIconFramePaddingWidth = (SkillIconFrameSize - SkillIconSize) / 2;
            int tempInterval = (SkillIconFrameSize*tempSkillIndex)+((tempSkillIndex+1)*10);

            int posX = screenWidth - SkillIconFrameSize - skillIconFramePaddingWidth;
            int posY = screenHeight - SkillIconFrameSize - skillIconFramePaddingWidth;
            double CooldownPercent = 1-skillTickElapsed/skillCooldownTime;
            CooldownPercent = CooldownPercent > 1 ? 1 : CooldownPercent;

            drawSquareImage(pPoseStack, posX, posY- tempInterval, 189, IconBasicResourceLocation.ICON_FRAME_BACKGROUND, SkillIconFrameSize);
            if (CooldownPercent < 0.05 && skillTickElapsed % 2 == 0) {
                drawSquareImage(pPoseStack, posX+1, posY-tempInterval +1, 190, getRole2IconLocMap().get(skill1.getRoleCategoryName()).get(skill1.getSkillID()), SkillIconSize);
            }
            else {
                drawSquareImage(pPoseStack, posX+1, posY-tempInterval +1, 190, getRole2IconLocMap().get(skill1.getRoleCategoryName()).get("DISBTN"+skill1.getSkillID()), SkillIconSize);
            }

            fillGradient(drawContext,pPoseStack, posX+1, posY-tempInterval+1, posX+ SkillIconFrameSize -1, posY-tempInterval-1 + ((int) Math.round( SkillIconFrameSize * CooldownPercent ) ), 191, -0x50FFEFF0);

            if (showCooldown) {
                int posX_center = (posX + (posX + SkillIconFrameSize)) / 2;
                int posY_center = ((posY - tempInterval) + (posY - tempInterval + SkillIconFrameSize)) / 2;
                drawCenterString(pPoseStack, posX_center, posY_center + 9, 192, "" + (Math.round((skillCooldownTime - skillTickElapsed) / 20 * 10) * 1.0 / 10), 0xFFFFFF);
//                drawCenterString(guiGraphics, posX_center, posY_center + 9, 192, "" + (Math.round((skillCooldownTime - skillTickElapsed) / 20 * 100) * 1.0 / 100), 0xFFFFFF);
            }
        }
    }

    public void render(int gameWorldTick, DrawContext drawContext, MatrixStack pPoseStack, float partialTick, int screenWidth, int screenHeight) {
        if (this.isCooldownTimerActive) {
            this.currentForgeGuiTick = gameWorldTick;

            int skillTickElapsed, tempSkillIndex;
            double skillCooldownTime;
            SkillDataJson skill1;
            ArrayList<String> skillsToBeRemoved = new ArrayList<String>();

            for (String skillID: this.PlayerSkill2CooldownTicks.keySet()) {
                skill1 = getID2SkillData().get(skillID);
                skillTickElapsed = this.PlayerSkill2CooldownTicks.get(skillID);
                skillCooldownTime = this.PlayerSkillID2TotalCooldownTicks.get(skillID);

                if (skillTickElapsed > skillCooldownTime) {
                    skillsToBeRemoved.add(skillID);
                }
                else {
                    if (isIconVisible) {
                        tempSkillIndex = this.PlayerSkillIconIndex.get(skillID);
                        renderSkillIcons(drawContext, pPoseStack, screenWidth, screenHeight, skill1, skillTickElapsed, skillCooldownTime, tempSkillIndex);
                    }
//                    this.PlayerSkill2CooldownTicks.put(skillID, skillTickElapsed + 1);
                    if (this.previousForgeGuiTick > -1) {
                        this.PlayerSkill2CooldownTicks.put(skillID, skillTickElapsed + this.currentForgeGuiTick - this.previousForgeGuiTick);
                    }
                }
            }

            //remove skill no longer in CD
            for (String skillIDTBR: skillsToBeRemoved) {
                this.PlayerSkill2CooldownTicks.remove(skillIDTBR);

                //update icon index
                tempSkillIndex = this.PlayerSkillIconIndex.get(skillIDTBR);
                this.PlayerSkillIconIndex.remove(skillIDTBR);
                if (isIconVisible) {
                    for (String skillID : this.PlayerSkill2CooldownTicks.keySet()) {
                        if (tempSkillIndex < this.PlayerSkillIconIndex.get(skillID)) {
                            this.PlayerSkillIconIndex.put(skillID, this.PlayerSkillIconIndex.get(skillID) - 1);
                        }
                    }
                }
            }
            this.previousForgeGuiTick = this.currentForgeGuiTick;

            //close timer if no skill in used
            if (this.PlayerSkill2CooldownTicks.size() <= 0) {
                this.previousForgeGuiTick = -1;
                this.currentForgeGuiTick = -1;
                this.isCooldownTimerActive = false;
            }
        }
    }

    public boolean isCooldownTimerActive() { return this.isCooldownTimerActive; }
    public void setCooldownTimer(boolean a, String skillID) {
        SkillDataJson skill1 = getID2SkillData().get(skillID);
        //if lvl 0, dont trigger
        HashMap<String, Integer> PlayerSkillID2lvlMap = getClientPlayerSkillID2lvlMap();
        if (!PlayerSkillID2lvlMap.containsKey(skillID)) { return; }
        //if current lvl is 0 and default CD isn't 0, return
        int skillCurrentLVL = PlayerSkillID2lvlMap.get(skillID);
        if (skillCurrentLVL <= 0 || (skill1.getCooldownTime().get(skillCurrentLVL-1) <= 0)) {
            return;
        }

        //setup cooldown timer for current player
        if (this.PlayerSkill2CooldownTicks == null) {
            this.PlayerSkill2CooldownTicks = new HashMap<String, Integer>();
            this.PlayerSkillID2TotalCooldownTicks = new HashMap<String, Double>();
            this.PlayerSkillIconIndex = new HashMap<String, Integer>();
        }
        this.isCooldownTimerActive = a;

        if (this.PlayerSkill2CooldownTicks.containsKey(skillID)) {
//            printInGameMsg(skilldata.getSkillID()+" already in cooldown!");
//            printInGameMsg(getLocalizationText("somebasicskills.skill.title."+skillID)+" already in cooldown!");

//            TODO: boolean for skilldatajson check if should overwrite?
//            skillCurrentLVL - 1, arraylist index
            float delayTime = getCPlayerSkillID2CooldownDelay().containsKey(skillID) ? getCPlayerSkillID2CooldownDelay().get(skillID): 0;
            double skillTotalCD =
                    ( (skill1.getCooldownTime().get(skillCurrentLVL-1)
                            +getCPlayerCooldownBase()
                            +getCPlayerTemporaryCooldownBase()) * (1.0
                            +getCPlayerCooldownMultiplier()
                            +getCPlayerTemporaryCooldownMultiplier())
                            +delayTime
                    ) * 20;

            this.PlayerSkill2CooldownTicks.put(skillID, 0);

            this.PlayerSkillID2TotalCooldownTicks.put(skillID, skillTotalCD);
        }
        else {
            //skillCurrentLVL - 1, arraylist index
            float delayTime = getCPlayerSkillID2CooldownDelay().containsKey(skillID) ? getCPlayerSkillID2CooldownDelay().get(skillID): 0;
            double skillTotalCD =
                    ( (skill1.getCooldownTime().get(skillCurrentLVL-1)
                            +getCPlayerCooldownBase()
                            +getCPlayerTemporaryCooldownBase()) * (1.0
                            +getCPlayerCooldownMultiplier()
                            +getCPlayerTemporaryCooldownMultiplier())
                            +delayTime
                    ) * 20;

            this.PlayerSkill2CooldownTicks.put(skillID, 0);

            this.PlayerSkillID2TotalCooldownTicks.put(skillID, skillTotalCD);

            this.PlayerSkillIconIndex.put(skillID, this.PlayerSkill2CooldownTicks.size()-1);
        }
    }
    public void setCooldownTimer(boolean a, String skillID, double cooldownAmount) {
        SkillDataJson skill1 = getID2SkillData().get(skillID);
        //if lvl 0, dont trigger
        HashMap<String, Integer> PlayerSkillID2lvlMap = getClientPlayerSkillID2lvlMap();
        if (!PlayerSkillID2lvlMap.containsKey(skillID)) { return; }
        //if current lvl is 0 and default CD isn't 0, return
        int skillCurrentLVL = PlayerSkillID2lvlMap.get(skillID);
        if (skillCurrentLVL <= 0 || (skill1.getCooldownTime().get(skillCurrentLVL-1) <= 0)) {
            return;
        }

        //setup cooldown timer for current player
        if (this.PlayerSkill2CooldownTicks == null) {
            this.PlayerSkill2CooldownTicks = new HashMap<String, Integer>();
            this.PlayerSkillID2TotalCooldownTicks = new HashMap<String, Double>();
            this.PlayerSkillIconIndex = new HashMap<String, Integer>();
        }
        this.isCooldownTimerActive = a;

        if (this.PlayerSkill2CooldownTicks.containsKey(skillID)) {
            this.PlayerSkill2CooldownTicks.put(skillID, 0);

            this.PlayerSkillID2TotalCooldownTicks.put(skillID, cooldownAmount);
        }
        else {
            this.PlayerSkill2CooldownTicks.put(skillID, 0);

            this.PlayerSkillID2TotalCooldownTicks.put(skillID, cooldownAmount);

            this.PlayerSkillIconIndex.put(skillID, this.PlayerSkill2CooldownTicks.size()-1);
        }
    }
    public void increaseSkillCooldownTimer(boolean a, String skillID, double cooldownAmount) {
        SkillDataJson skill1 = getID2SkillData().get(skillID);
        //if lvl 0, dont trigger
        HashMap<String, Integer> PlayerSkillID2lvlMap = getClientPlayerSkillID2lvlMap();
        if (!PlayerSkillID2lvlMap.containsKey(skillID)) { return; }
        //if current lvl is 0 and default CD isn't 0, return
        int skillCurrentLVL = PlayerSkillID2lvlMap.get(skillID);
        if (skillCurrentLVL <= 0 || (skill1.getCooldownTime().get(skillCurrentLVL-1) <= 0)) {
            return;
        }

        //setup cooldown timer for current player
        if (this.PlayerSkill2CooldownTicks == null) {
            this.PlayerSkill2CooldownTicks = new HashMap<String, Integer>();
            this.PlayerSkillID2TotalCooldownTicks = new HashMap<String, Double>();
            this.PlayerSkillIconIndex = new HashMap<String, Integer>();
        }
        this.isCooldownTimerActive = a;

        if (this.PlayerSkill2CooldownTicks.containsKey(skillID)) {
            this.PlayerSkillID2TotalCooldownTicks.put(skillID, this.PlayerSkillID2TotalCooldownTicks.get(skillID)+cooldownAmount);
        }
    }

    public boolean isClientPlayerSkillInCD(String skillID) {
//        boolean isInCD = false;
//        if (this.PlayerSkill2CooldownTicks != null) {
//            isInCD = this.PlayerSkill2CooldownTicks.containsKey(skillID);
//        }
//        if (isInCD) {
////            PlayLocalInCooldownSound(1.0f, 1.0f);
//            PlayLocalInCooldownSound(1.0f, 0.7f);
////            PlayLocalDefaultBtnPressedSound(1.0f, 1.0f);
//        }
//        return isInCD;

        boolean isInCD = false;
        if (this.PlayerSkill2CooldownTicks != null) {
            isInCD = this.PlayerSkill2CooldownTicks.containsKey(skillID);
        }
        return isInCD;
    }

    public int getClientPlayerSkillCurrentCooldownTicks(String skillID) {
        if (this.PlayerSkill2CooldownTicks != null) {
            return this.PlayerSkill2CooldownTicks.get(skillID);
        }
        return 0;
    }

    public HashMap<String, Integer> getPlayerSkill2CooldownTicks() {
        return this.PlayerSkill2CooldownTicks;
    }

    public HashMap<String, Integer> getPlayerSkillIconIndex() {
        return this.PlayerSkillIconIndex;
    }

    public static int getCPlayerSkillCurrentCooldownTicks(String skillID) {
        return getSkillsInCooldownClientTimerOverlayInstance().getClientPlayerSkillCurrentCooldownTicks(skillID);
    }

    public static boolean isCPlayerSkillInCD(String skillID) {
        return getSkillsInCooldownClientTimerOverlayInstance().isClientPlayerSkillInCD(skillID);
    }

    public static SkillsInCooldownClientTimerOverlay getSkillsInCooldownClientTimerOverlayInstance() {
        return SKILLS_IN_COOLDOWN_CLIENT_TIMER_OVERLAY_INSTANCE;
    }
}

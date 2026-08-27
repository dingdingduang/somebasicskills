package net.dingdingduang.somebasicskills.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dingdingduang.somebasicskills.globalmethods.GeneralMethods;
import net.dingdingduang.somebasicskills.globalmethods.GuiMethods;
import net.dingdingduang.somebasicskills.keyboard.KeyMappingInit;
import net.dingdingduang.somebasicskills.networking.NetworkingSendMsgMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.dingdingduang.somebasicskills.resourcelocation.icon.IconBasicResourceLocation;
import net.dingdingduang.somebasicskills.Constants;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;

import static net.dingdingduang.somebasicskills.sbsattributes.statusquery.AttributeClientPlayerStatusQueryMethods.*;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.*;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class SkillChannelingOverlay {
    private boolean isChannelingTimerActive = false;
    private static final SkillChannelingOverlay SKILL_CHANNELING_OVERLAY_INSTANCE = new SkillChannelingOverlay();

    //20 ticks = 1 sec
//    private final int Ticks2Sec = 20;

    private int previousForgeGuiTick = -1;
    private int currentForgeGuiTick = -1;

    private HashMap<String, Integer> PlayerSkillID2ChannelingTicks;
    private HashMap<String, Integer> PlayerSkillID2KeyCode;
    private int SkillChannelingPreviousKeyCode;

    //render channeling bar
    private boolean isChannelingBarActive = true;
    private boolean showChannelingTime = true;

//    public void drawSquareImage(PoseStack pPoseStack, int x, int y, int translateZ, ResourceLocation res1, int size) {
//        pPoseStack.pushPose();
//        pPoseStack.translate(0, 0, translateZ);
//        guiGraphics.blit(res1, x, y, size, size, size, size, size, size);
//        pPoseStack.popPose();
//    }

    public void drawRectImage(MatrixStack pPoseStack, int x, int y, int imgWidth, int imdHeight, int translateZ, Identifier resLoc) {
        pPoseStack.push();
        pPoseStack.translate(0, 0, translateZ);
        RenderSystem.setShaderColor(0.5f, 0.5f, 0.5f, 0.8f);
        GuiMethods.drawImageOnly(pPoseStack, resLoc, x, y, imgWidth, imdHeight, imgWidth, imdHeight, imgWidth, imdHeight);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        pPoseStack.pop();
    }

//    public void fillGradient(PoseStack pPoseStack, int x1, int y1, int x2, int y2, int translateZ, int pColor) {
//        pPoseStack.pushPose();
//        pPoseStack.translate(0, 0, translateZ);
//
//        guiGraphics.fillGradient(x1, y1, x2, y2, pColor, pColor);
//
//        pPoseStack.popPose();
//    }

    public void drawCenterString(MatrixStack pPoseStack, int x1, int y1, int translateZ, String text, int pColor) {
        pPoseStack.push();
        pPoseStack.translate(0, 0, translateZ);

        RenderSystem.setShaderColor(0.5f, 0.5f, 0.5f, 0.8f);
        GuiMethods.drawCenteredString(pPoseStack, GeneralMethods.getMinecraftInstanceFont(), text, x1, y1, pColor);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

        pPoseStack.pop();
    }

    public void renderChannelingBar(MatrixStack pPoseStack, float partialTicks, int screenWidth, int screenHeight, int skillTickElapsed, double skillMaxChannelingTime) {
        //if on same time
        double ChannelingPercent;
        if (this.currentForgeGuiTick - this.previousForgeGuiTick == 0) {
            ChannelingPercent = (skillTickElapsed-1f+partialTicks)/skillMaxChannelingTime;
            if (ChannelingPercent < 0) {
                ChannelingPercent = 0;
            }
            else if (ChannelingPercent > 1) {
                ChannelingPercent = 1;
            }
//            ChannelingPercent = skillTickElapsed/skillMaxChannelingTime;
        }
        else {
            ChannelingPercent = skillTickElapsed/skillMaxChannelingTime;
//            ChannelingPercent = (skillTickElapsed+partialTicks)/skillMaxChannelingTime;
        }
        double deadline = 1-1.0*16/64;

        int centerX = screenWidth/2, centerY = screenHeight/2, relativeX = (int) Math.round(64 * ChannelingPercent);

        drawRectImage(pPoseStack, centerX, centerY, 64, 16, 190, IconBasicResourceLocation.PROGRESSBAR_BACKGROUND);
        drawRectImage(pPoseStack, centerX + 1, centerY, relativeX, 16, 191, IconBasicResourceLocation.PROGRESSBAR);
        if (ChannelingPercent > deadline) {
            drawRectImage(pPoseStack, centerX + relativeX, centerY, (int) Math.round(1.0 * 8 * 64 / 16*(1-ChannelingPercent)), 16, 192, IconBasicResourceLocation.PROGRESSBAR_FORWARD);
        }
        else {
            drawRectImage(pPoseStack, centerX + relativeX, centerY, 8, 16, 192, IconBasicResourceLocation.PROGRESSBAR_FORWARD);
        }

        if (showChannelingTime) {
//            drawCenterString(guiGraphics, centerX, centerY, 193, (Math.round((1.0*skillTickElapsed / 20 * 10) * 1.0 / 10))+"%", 0xFFFFFF);
            drawCenterString(pPoseStack, centerX + relativeX, centerY-6, 193, (Math.round(ChannelingPercent * 100))+"%", 0xFFFFFF);
        }
    }

    public void render(int gameWorldTick, MatrixStack pPoseStack, float partialTick, int screenWidth, int screenHeight) {
        if (this.isChannelingTimerActive) {
            this.currentForgeGuiTick = gameWorldTick;

            HashMap<String, Integer> PlayerSkillID2lvlMap = getClientPlayerSkillID2lvlMap();
            HashMap<String, Integer> currentSkillID2lvlMap;
            int PlayerLastPressedKeyCode = getCPlayerLastPressedKeyCode();
            // 0 key released from keyboard, 1 pressed down, 2 holding
            int PlayerLastKeyAction = getCPlayerLastKeyAction();
            int skillTickElapsed, skillCurrentLVL, currentPlayerLastPressedKeyCode, previousPlayerLastPressedKeyCode, currentPlayerLastKeyAction;
            double skillMaxChannelingTime;
            ArrayList<String> skillIDToBeRemoved = new ArrayList<String>();

            HashMap<String, Integer> ClientPlayerStateMap;

            ClientPlayerStateMap = getCPlayerState();
            currentSkillID2lvlMap = PlayerSkillID2lvlMap;
            currentPlayerLastPressedKeyCode = PlayerLastPressedKeyCode;
            currentPlayerLastKeyAction = PlayerLastKeyAction;
            previousPlayerLastPressedKeyCode = this.SkillChannelingPreviousKeyCode;

            this.SkillChannelingPreviousKeyCode = PlayerLastPressedKeyCode;

            for (String SkillID: this.PlayerSkillID2ChannelingTicks.keySet()) {
                SkillDataJson skill1 = getID2SkillData().get(SkillID);
                skillTickElapsed = this.PlayerSkillID2ChannelingTicks.get(SkillID);
                skillCurrentLVL = currentSkillID2lvlMap.get(SkillID);
                //skillCurrentLVL - 1, arraylist index
//                    skillMaxChannelingTime = skill1.getChannelingTime().get(skillCurrentLVL-1)*20;
                if (skill1.shouldChannelingTimeBeFixed()) {
                    skillMaxChannelingTime = (skill1.getChannelingTime().get(skillCurrentLVL - 1)) * 20;
                }
                else {
                    skillMaxChannelingTime = (skill1.getChannelingTime().get(skillCurrentLVL - 1)
                            + getCPlayerChannelingBase()
                            + getCPlayerTemporaryChannelingBase()) * (1.0
                            + getCPlayerChannelingMultiplier()
                            + getCPlayerTemporaryChannelingMultiplier())
                            * 20;
                }

//                printInGameMsg(String.format("currentkey: %s, previouskey: %s", String.valueOf((char) currentPlayerLastPressedKeyCode), String.valueOf((char) previousPlayerLastPressedKeyCode)));

                Integer isChanneling;
                if ( ( !skill1.isChannelingShouldHoldKey() || // should hold key while channeling
                        (KeyMappingInit.getKeyAction(this.PlayerSkillID2KeyCode.get(SkillID)) > 0) ||
                        (currentPlayerLastPressedKeyCode == previousPlayerLastPressedKeyCode && currentPlayerLastKeyAction > 0) ) // pressed other key or stopped holding the key
                        && ((isChanneling = ClientPlayerStateMap.get(Constants.IS_CHANNELING)) != null && isChanneling != Constants.ACTION_OFF) //if 0 means interrupted by something
                        ) {
//                    if (currentPlayerLastPressedKeyCode == previousPlayerLastPressedKeyCode // pressed other key
//                            && true //if 0 means interrupted by something
//                            && currentPlayerLastKeyAction > 0) { // stop holding the key
//                    if (skillTickElapsed > skillMaxChannelingTime) {
                    if (skillTickElapsed > skillMaxChannelingTime) {
                        skillIDToBeRemoved.add(SkillID);
                        //full charge action
//                        int tempInt = Math.max(ClientPlayerStateMap.get(Constants.IS_CHANNELING) - 1, 0);
//                        int tempInt = ClientPlayerStateMap.get(Constants.IS_CHANNELING) - 1;
//                        ClientPlayerStateMap.put(Constants.IS_CHANNELING, tempInt);

                        ClientPlayerStateMap.put(Constants.IS_CHANNELING, Constants.ACTION_OFF);
                        ClientPlayerStateMap.put(Constants.CHANNELING_TICKS, skillTickElapsed);

//                        NetworkingSendMsgMethods.SendPlayerStateDataFromClientSideToServer(Constants.IS_CHANNELING, tempInt);
//                        NetworkingSendMsgMethods.SendPlayerStateDataFromClientSideToServer(Constants.CHANNELING_TICKS, skillTickElapsed);
//                        NetworkingSendMsgMethods.SendChannelingFinishedActionWithSkillIDFromClientSideToServer(SkillID, skill1.getChannelingTime().get(skillCurrentLVL-1).floatValue(), tempInt, Constants.ACTION_OFF, skillTickElapsed);
                        NetworkingSendMsgMethods.SendChannelingFinishedActionWithSkillIDFromClientSideToServer(SkillID, skill1.getChannelingTime().get(skillCurrentLVL-1).floatValue(), Constants.ACTION_OFF, Constants.ACTION_OFF, skillTickElapsed);
                    }
                    else {
                        if (GeneralMethods.isFirstPersonPerspective() && isChannelingBarActive && skill1.isChannelingShouldShowWindow()) {
//                            renderChannelingBar(guiGraphics, screenWidth, screenHeight, skill1, skillTickElapsed, skillMaxChannelingTime);
                            renderChannelingBar(pPoseStack, partialTick, screenWidth, screenHeight, skillTickElapsed, skillMaxChannelingTime);
                        }

                        if (this.previousForgeGuiTick > -1) {
                            this.PlayerSkillID2ChannelingTicks.put(SkillID, skillTickElapsed + this.currentForgeGuiTick - this.previousForgeGuiTick);
                        }
                    }
                }
                else {
                    //interrupted action?
                    skillIDToBeRemoved.add(SkillID);
//                    int tempInt = Math.max(ClientPlayerStateMap.get(Constants.IS_CHANNELING) - 1, 0);
//                    int tempInt = ClientPlayerStateMap.get(Constants.IS_CHANNELING) - 1;
//                    ClientPlayerStateMap.put(Constants.IS_CHANNELING, tempInt);

                    ClientPlayerStateMap.put(Constants.IS_CHANNELING, Constants.ACTION_OFF);
                    ClientPlayerStateMap.put(Constants.CHANNELING_INTERRUPTED, 1);
                    ClientPlayerStateMap.put(Constants.CHANNELING_TICKS, skillTickElapsed);

//                    NetworkingSendMsgMethods.SendPlayerStateDataFromClientSideToServer(Constants.IS_CHANNELING, tempInt);
//                    NetworkingSendMsgMethods.SendPlayerStateDataFromClientSideToServer(Constants.CHANNELING_INTERRUPTED, Constants.ACTION_ON);
//                    NetworkingSendMsgMethods.SendPlayerStateDataFromClientSideToServer(Constants.CHANNELING_TICKS, skillTickElapsed);
//                    NetworkingSendMsgMethods.SendChannelingFinishedActionWithSkillIDFromClientSideToServer(SkillID, skill1.getChannelingTime().get(skillCurrentLVL-1).floatValue(), tempInt, Constants.ACTION_ON, skillTickElapsed);
                    NetworkingSendMsgMethods.SendChannelingFinishedActionWithSkillIDFromClientSideToServer(SkillID, skill1.getChannelingTime().get(skillCurrentLVL-1).floatValue(), Constants.ACTION_OFF, Constants.ACTION_ON, skillTickElapsed);
                }
            }

            //remove skill no longer in channeling state
            for (String SkillID: skillIDToBeRemoved) {
                this.PlayerSkillID2ChannelingTicks.remove(SkillID);
                this.PlayerSkillID2KeyCode.remove(SkillID);
            }

            this.previousForgeGuiTick = this.currentForgeGuiTick;
            //close timer if no skill is channeling
            if (this.PlayerSkillID2ChannelingTicks.size() <= 0) {
//                this.period = 0;
                this.previousForgeGuiTick = -1;
                this.currentForgeGuiTick = -1;
                this.isChannelingTimerActive = false;
            }
        }
    }

    public boolean isChannelingTimerActive() { return this.isChannelingTimerActive; }
    public void setChannelingTimer(boolean a, String SkillID, int lastKeyCode) {
//        SkillDataJson skilldata = getID2SkillData().get(SkillID);
        //if lvl 0, return
        HashMap<String, Integer> PlayerSkillID2lvlMap = getClientPlayerSkillID2lvlMap();
        int playerSkillLVL;
        if (!PlayerSkillID2lvlMap.containsKey(SkillID) || (playerSkillLVL = PlayerSkillID2lvlMap.get(SkillID)) <= 0) { return; }

        //setup channeling timer for client player
        if (this.PlayerSkillID2ChannelingTicks == null) {
            this.PlayerSkillID2ChannelingTicks = new HashMap<String, Integer>();
            this.PlayerSkillID2KeyCode = new HashMap<String, Integer>();
        }

        this.SkillChannelingPreviousKeyCode = getCPlayerLastPressedKeyCode();
        this.isChannelingTimerActive = a;

        if (this.PlayerSkillID2ChannelingTicks.isEmpty()) {
            //Only one same channeling spell is allowed
            this.PlayerSkillID2ChannelingTicks.put(SkillID, 0);
            this.PlayerSkillID2KeyCode.put(SkillID, lastKeyCode);

            HashMap<String, Integer> ClientPlayerStateMap = getCPlayerState();
//            int temp_is_channeling;
//            if (ClientPlayerStateMap.containsKey(Constants.IS_CHANNELING) && (temp_is_channeling = ClientPlayerStateMap.get(Constants.IS_CHANNELING)) > 0) {
//                ClientPlayerStateMap.put(Constants.IS_CHANNELING, temp_is_channeling + 1);
//            }
//            else {
//                ClientPlayerStateMap.put(Constants.IS_CHANNELING, 1);
//            }
            ClientPlayerStateMap.put(Constants.IS_CHANNELING, Constants.ACTION_ON);
            ClientPlayerStateMap.put(Constants.CHANNELING_INTERRUPTED, 0);
            SkillDataJson skill1 = getID2SkillData().get(SkillID);

//            NetworkingSendMsgMethods.SendPlayerStateDataFromClientSideToServer(Constants.IS_CHANNELING, Constants.ACTION_ON);
//            NetworkingSendMsgMethods.SendPlayerStateDataFromClientSideToServer(Constants.CHANNELING_INTERRUPTED, Constants.ACTION_OFF);
            NetworkingSendMsgMethods.SendChannelingActionWithSkillIDFromClientSideToServer(SkillID, skill1.getChannelingTime().get(playerSkillLVL - 1).floatValue());
        }
    }

    public static HashMap<String, Integer> getPlayerSkillID2KeyCode() {
        return getSkillChannelingOverlayInstance().PlayerSkillID2KeyCode;
    }

    public static void resetSkillKeyCodeActionFromPlayerSkillID2KeyCode(String SkillID) {
        SkillChannelingOverlay skillChannelingOverlay = getSkillChannelingOverlayInstance();
        Integer SkillID2KeyCode = skillChannelingOverlay.PlayerSkillID2KeyCode.get(SkillID);
        if (SkillID2KeyCode != null) {
            KeyMappingInit.setKeyAction(skillChannelingOverlay.PlayerSkillID2KeyCode.get(SkillID), 0);
        }
    }

    public static SkillChannelingOverlay getSkillChannelingOverlayInstance() {
        return SKILL_CHANNELING_OVERLAY_INSTANCE;
    }
}

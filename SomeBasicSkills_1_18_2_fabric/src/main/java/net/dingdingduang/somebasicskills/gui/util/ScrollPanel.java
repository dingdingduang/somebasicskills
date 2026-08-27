package net.dingdingduang.somebasicskills.gui.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dingdingduang.somebasicskills.globalmethods.GuiMethods;
import net.dingdingduang.somebasicskills.resourcelocation.icon.IconBasicResourceLocation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.render.*;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.gui.AbstractParentElement;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;

public abstract class ScrollPanel extends AbstractParentElement implements Drawable, Selectable {
    private final MinecraftClient client;
    protected final int width;
    protected final int height;
    protected final int top;
    protected final int bottom;
    protected final int right;
    protected final int left;
    private boolean scrolling;
    protected float scrollDistance;
    protected boolean captureMouse;
    protected final int border;
    private final int barWidth;
    private final int barLeft;
    private final int bgColorFrom;
    private final int bgColorTo;
    private final int barBgColor;
    private final int barColor;
    private final int barBorderColor;

    public ScrollPanel(MinecraftClient client, int width, int height, int top, int left) {
        this(client, width, height, top, left, 4);
    }

    public ScrollPanel(MinecraftClient client, int width, int height, int top, int left, int border) {
        this(client, width, height, top, left, border, 6);
    }

    public ScrollPanel(MinecraftClient client, int width, int height, int top, int left, int border, int barWidth) {
        this(client, width, height, top, left, border, barWidth, -1072689136, -804253680);
    }

    public ScrollPanel(MinecraftClient client, int width, int height, int top, int left, int border, int barWidth, int bgColor) {
        this(client, width, height, top, left, border, barWidth, bgColor, bgColor);
    }

    public ScrollPanel(MinecraftClient client, int width, int height, int top, int left, int border, int barWidth, int bgColorFrom, int bgColorTo) {
        this(client, width, height, top, left, border, barWidth, bgColorFrom, bgColorTo, -16777216, -8355712, -4144960);
    }

    public ScrollPanel(MinecraftClient client, int width, int height, int top, int left, int border, int barWidth, int bgColorFrom, int bgColorTo, int barBgColor, int barColor, int barBorderColor) {
        this.captureMouse = true;
        this.client = client;
        this.width = width;
        this.height = height;
        this.top = top;
        this.left = left;
        this.bottom = height + this.top;
        this.right = width + this.left;
        this.barLeft = this.left + this.width - barWidth;
        this.border = border;
        this.barWidth = barWidth;
        this.bgColorFrom = bgColorFrom;
        this.bgColorTo = bgColorTo;
        this.barBgColor = barBgColor;
        this.barColor = barColor;
        this.barBorderColor = barBorderColor;
    }

    protected abstract int getContentHeight();

    private ClientWorld getClientWorld() { return this.client.world; }

    protected void drawBackground(MatrixStack matrix, Tessellator tessellatorellator, float partialTick) {
        BufferBuilder bufferBuilder = tessellatorellator.getBuffer();
        if (getClientWorld() != null) {
            GuiMethods.guiUtilsFillGradient(matrix, this.left, this.top, this.right, this.bottom, this.bgColorFrom, this.bgColorTo, 0);
        }
        else {
            RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
            RenderSystem.setShaderTexture(0, IconBasicResourceLocation.SKILL_BACKGROUND);

            bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
            bufferBuilder.vertex(this.left, this.bottom, 0).texture(this.left / 32F, (this.bottom + (int) this.scrollDistance) / 32F).color(32, 32, 32, 255).next();
            bufferBuilder.vertex(this.right, this.bottom, 0).texture(this.right / 32F, (this.bottom + (int) this.scrollDistance) / 32F).color(32, 32, 32, 255).next();
            bufferBuilder.vertex(this.right, this.top, 0).texture(this.right / 32F, (this.top + (int) this.scrollDistance) / 32F).color(32, 32, 32, 255).next();
            bufferBuilder.vertex(this.left, this.top, 0).texture(this.left / 32F, (this.top + (int) this.scrollDistance) / 32F).color(32, 32, 32, 255).next();
            tessellatorellator.draw();
        }

    }

    protected abstract void drawPanel(MatrixStack pPoseStack, int entryRight, int relativeY, Tessellator tessellatorellator, int mouseX, int mouseY);

    protected boolean clickPanel(double mouseX, double mouseY, int button) { return false; }

    private int getMaxScroll() { return this.getContentHeight() - (this.height - this.border); }

    private void applyScrollLimits() {
        int max = this.getMaxScroll();
        if (max < 0) {
            max /= 2;
        }

        if (this.scrollDistance < 0.0F) {
            this.scrollDistance = 0.0F;
        }

        if (this.scrollDistance > max) {
            this.scrollDistance = max;
        }

    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scroll) {
        if (scroll != 0) {
            this.scrollDistance = (float) (this.scrollDistance + -scroll * this.getScrollAmount());
            this.applyScrollLimits();
            return true;
        } else {
            return false;
        }
    }

    protected int getScrollAmount() { return 20; }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= this.left && mouseX <= (this.left + this.width) && mouseY >= this.top && mouseY <= this.bottom;
    }


    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (super.mouseClicked(mouseX, mouseY, button)) {
            return true;
        } else {
            this.scrolling = button == 0 && mouseX >= this.barLeft && mouseX < (this.barLeft + this.barWidth);
            if (this.scrolling) {
                return true;
            } else {
                int mouseListY = (int)mouseY - this.top - this.getContentHeight() + (int)this.scrollDistance - this.border;
                return mouseX >= this.left && mouseX <= this.right && mouseListY < 0 ? this.clickPanel(mouseX - this.left, mouseY - this.top + ((int)this.scrollDistance) - this.border, button) : false;
            }
        }
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (super.mouseReleased(mouseX, mouseY, button)) {
            return true;
        } else {
            boolean ret = this.scrolling;
            this.scrolling = false;
            return ret;
        }
    }

    private int getBarHeight() {
        int barHeight = this.height * this.height / this.getContentHeight();
        if (barHeight < 32) {
            barHeight = 32;
        }

        if (barHeight > this.height - this.border * 2) {
            barHeight = this.height - this.border * 2;
        }

        return barHeight;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.scrolling) {
            int maxScroll = this.height - this.getBarHeight();
            double moved = deltaY / maxScroll;
            this.scrollDistance = (float) (this.scrollDistance + this.getMaxScroll() * moved);
            this.applyScrollLimits();
            return true;
        } else {
            return false;
        }
    }

    private double getWindowScaleFactor() { return this.client.getWindow().getScaleFactor(); }
    private double getWindowBufferHeight() { return this.client.getWindow().getFramebufferHeight(); }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTick) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        double scale = getWindowScaleFactor();
        RenderSystem.enableScissor((int)(this.left * scale), (int)(getWindowBufferHeight() - this.bottom * scale), (int)(this.width * scale), (int)(this.height * scale));
        this.drawBackground(matrix, tessellator, partialTick);
        int baseY = this.top + this.border - (int)this.scrollDistance;
        this.drawPanel(matrix, this.right, baseY, tessellator, mouseX, mouseY);
        RenderSystem.disableDepthTest();
        int extraHeight = this.getContentHeight() + this.border - this.height;
        if (extraHeight > 0) {
            int barHeight = this.getBarHeight();
            int barTop = (int)this.scrollDistance * (this.height - barHeight) / extraHeight + this.top;
            if (barTop < this.top) {
                barTop = this.top;
            }

            int barBgAlpha = GuiMethods.getAlphaFromColor255IntBase(this.barBgColor);
            int barBgRed = GuiMethods.getRedFromColor255IntBase(this.barBgColor);
            int barBgGreen = GuiMethods.getGreenFromColor255IntBase(this.barBgColor);
            int barBgBlue = GuiMethods.getBlueFromColor255IntBase(this.barBgColor);
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            RenderSystem.disableTexture();
            bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
            bufferBuilder.vertex(this.barLeft, this.bottom, 0.0F).color(barBgRed, barBgGreen, barBgBlue, barBgAlpha).next();
            bufferBuilder.vertex((this.barLeft + this.barWidth), this.bottom, 0.0F).color(barBgRed, barBgGreen, barBgBlue, barBgAlpha).next();
            bufferBuilder.vertex((this.barLeft + this.barWidth), this.top, 0.0F).color(barBgRed, barBgGreen, barBgBlue, barBgAlpha).next();
            bufferBuilder.vertex(this.barLeft, this.top, 0.0F).color(barBgRed, barBgGreen, barBgBlue, barBgAlpha).next();
            tessellator.draw();
            int barAlpha = GuiMethods.getAlphaFromColor255IntBase(this.barColor);
            int barRed = GuiMethods.getRedFromColor255IntBase(this.barColor);
            int barGreen = GuiMethods.getGreenFromColor255IntBase(this.barColor);
            int barBlue = GuiMethods.getBlueFromColor255IntBase(this.barColor);
            bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
            bufferBuilder.vertex(this.barLeft, (barTop + barHeight), 0.0F).color(barRed, barGreen, barBlue, barAlpha).next();
            bufferBuilder.vertex((this.barLeft + this.barWidth), (barTop + barHeight), 0.0F).color(barRed, barGreen, barBlue, barAlpha).next();
            bufferBuilder.vertex((this.barLeft + this.barWidth), barTop, 0.0F).color(barRed, barGreen, barBlue, barAlpha).next();
            bufferBuilder.vertex(this.barLeft, barTop, 0.0F).color(barRed, barGreen, barBlue, barAlpha).next();
            tessellator.draw();
            int barBorderAlpha = GuiMethods.getAlphaFromColor255IntBase(this.barBorderColor);
            int barBorderRed = GuiMethods.getRedFromColor255IntBase(this.barBorderColor);
            int barBorderGreen = GuiMethods.getGreenFromColor255IntBase(this.barBorderColor);
            int barBorderBlue = GuiMethods.getBlueFromColor255IntBase(this.barBorderColor);
            bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
            bufferBuilder.vertex(this.barLeft, (barTop + barHeight - 1), 0.0F).color(barBorderRed, barBorderGreen, barBorderBlue, barBorderAlpha).next();
            bufferBuilder.vertex((this.barLeft + this.barWidth - 1), (barTop + barHeight - 1), 0.0F).color(barBorderRed, barBorderGreen, barBorderBlue, barBorderAlpha).next();
            bufferBuilder.vertex((this.barLeft + this.barWidth - 1), barTop, 0.0F).color(barBorderRed, barBorderGreen, barBorderBlue, barBorderAlpha).next();
            bufferBuilder.vertex(this.barLeft, barTop, 0.0F).color(barBorderRed, barBorderGreen, barBorderBlue, barBorderAlpha).next();
            tessellator.draw();
        }

        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.disableScissor();
    }

    @Override
    public List<? extends Element> children() {
        return Collections.emptyList();
    }
}
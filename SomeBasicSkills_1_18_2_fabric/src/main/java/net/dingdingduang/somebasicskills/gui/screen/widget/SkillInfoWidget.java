/*
 * Copyright 2022 Markus Bordihn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.dingdingduang.somebasicskills.gui.screen.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dingdingduang.somebasicskills.gui.util.ScrollPanel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getComponentWithSpecifiedString;
import static net.dingdingduang.somebasicskills.globalmethods.LocaleLanguageMethods.getLanguageInstance;

public class SkillInfoWidget extends ScrollPanel {
    private static final int PADDING = 6;

    private List<OrderedText> lines = Collections.emptyList();
    private TextRenderer font;

    public SkillInfoWidget(MinecraftClient minecraft, int width, int height, int top, int left) {
        super(minecraft, width, height, top, left + PADDING);
        this.font = minecraft.textRenderer;
    }

    public void setInfo(List<String> lines) {
        this.lines = resizeContent(lines);
    }

    public void clearInfo() { this.lines = Collections.emptyList(); }

    private List<OrderedText> resizeContent(List<String> lines) {
        List<OrderedText> result = new ArrayList<>();
        String tempSubstring;
        for (String line : lines) {
            tempSubstring = line;
            if (line == null) {
                result.add(null);
                continue;
            }

            Style textStyle = Style.EMPTY;
            if (line.startsWith("|cFF")) {
                tempSubstring = line.substring(10);
                textStyle = textStyle.withColor(Integer.parseInt( line.substring(4, 10), 16 ));
            }

//            Text chat = ForgeHooks.newChatWithLinks(tempSubstring, false);
            Text chat = getComponentWithSpecifiedString(tempSubstring);
            int maxTextLength = this.width - 12;
            if (maxTextLength >= 0) {
                result.addAll(getLanguageInstance()
                        .reorder(font.getTextHandler().wrapLines(chat, maxTextLength, textStyle)));
            }
        }
        return result;
    }

    @Override
    public SelectionType getType() { return SelectionType.NONE; }

    @Override
    public void appendNarrations(NarrationMessageBuilder builder) {
        // Not needed.
    }

    @Override
    protected int getContentHeight() {
        int height = 5;
        height += (lines.size() * font.fontHeight);
        if (height < this.bottom - this.top - 8) height = this.bottom - this.top - 8;
        return height;
    }

    @Override
    protected int getScrollAmount() { return font.fontHeight * 3; }

    @Override
    protected void drawPanel(MatrixStack pPoseStack, int entryRight, int relativeY, Tessellator tessellator, int mouseX, int mouseY) {
        pPoseStack.push();
        pPoseStack.translate(0, 0, 201);
        for (OrderedText line : lines) {
            if (line != null) {
              RenderSystem.enableBlend();
              this.font.draw(pPoseStack, line, left + PADDING, relativeY, 0xFFFFFF);
              RenderSystem.disableBlend();
            }
            relativeY += font.fontHeight;
        }
        pPoseStack.pop();
    }
}

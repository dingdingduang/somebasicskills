package net.dingdingduang.somebasicskills.globalmethods;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class GeneralMethods {
    public static MinecraftClient getMinecraftInstance() { return MinecraftClient.getInstance(); }
    public static TextRenderer getMinecraftInstanceFont() { return getMinecraftInstance().textRenderer; }
    public static ClientWorld getClientLevel() { return getMinecraftInstance().world; }
    public static boolean isFirstPersonPerspective() { return getMinecraftInstance().options.getPerspective().isFirstPerson(); }
    public static boolean isCamMirror() { return getMinecraftInstance().options.getPerspective().isFrontView(); }

    //=======================
    //use inside network only or get crash
    public static MinecraftServer getMinecraftServerInstance(Entity entity) {
        return entity.getServer();
    }
    //END
    //========================

    public static void printInGameMsg(String a) {
        getMinecraftInstance().inGameHud.getChatHud().addMessage(getComponentWithSpecifiedString(a));
    }

    public static Text getComponentWithSpecifiedString(String text) { return Text.literal(text); }

    //TODO: prevent spam by checking world time tick 24000 total, abs(getTime()-x) > 20 ticks
//    public static long getDayTime() {
//        if (getClientLevel() != null) {
//            return getClientLevel().getDayTime();
//        }
//        return -1;
//    }

    public static long getMCGameTime() {
        if (getClientLevel() != null) {
            return getClientLevel().getTime();
        }
        return -1;
    }

    public static Identifier getMCResourceLocation(String namespace, String path) {
        return new Identifier(namespace, path);
    }
}

package net.dingdingduang.somebasicskills.globalmethods;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

public class GeneralMethods {
    public static Minecraft getMinecraftInstance() { return Minecraft.getInstance(); }
    public static Font getMinecraftInstanceFont() { return getMinecraftInstance().font; }
    public static ClientLevel getClientLevel() { return getMinecraftInstance().level; }
    public static boolean isFirstPersonPerspective() { return getMinecraftInstance().options.getCameraType().isFirstPerson(); }
    public static boolean isCamMirror() { return getMinecraftInstance().options.getCameraType().isMirrored(); }

    //=======================
    //use inside network only or get crash
    public static MinecraftServer getMinecraftServerInstance() {
        return ServerLifecycleHooks.getCurrentServer();
    }
    //END
    //========================

    public static void printInGameMsg(String a) {
        getMinecraftInstance().gui.getChat().addMessage(getComponentWithSpecifiedString(a));
    }

    public static Component getComponentWithSpecifiedString(String text) { return Component.literal(text); }

    //TODO: prevent spam by checking world time tick 24000 total, abs(getTime()-x) > 20 ticks
//    public static long getDayTime() {
//        if (getClientLevel() != null) {
//            return getClientLevel().getDayTime();
//        }
//        return -1;
//    }

    public static long getMCGameTime() {
        if (getClientLevel() != null) {
            return getClientLevel().getGameTime();
        }
        return -1;
    }

    public static Component getPacketFailedTranslatableComponent(String msg) { return Component.translatable("my_mod.networking.failed", msg); }

    public static ResourceLocation getMCResourceLocation(String namespace, String path) { return new ResourceLocation(namespace, path); }
}

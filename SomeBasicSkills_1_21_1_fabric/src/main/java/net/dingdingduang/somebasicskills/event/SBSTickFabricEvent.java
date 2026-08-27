package net.dingdingduang.somebasicskills.event;


import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

public class SBSTickFabricEvent {
    private static int previousServerTickCount = -1;

    public static void registerEvent() {
        ServerTickEvents.END_SERVER_TICK.register(SBSTickFabricEvent::SBSonServerTickEndAction);
    }

    private static void SBSonServerTickEndAction(MinecraftServer mcserver) {
        int currentServerTickCount;
        if (mcserver != null && previousServerTickCount != (currentServerTickCount = mcserver.getTicks())) {
            previousServerTickCount = currentServerTickCount;

            SBSTickEventMethods.SBSRunAction();

            SBSTickEventAttributeMethods.SBSAttributesRunAction();
        }
    }
}

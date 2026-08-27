package net.dingdingduang.somebasicskills.event;


import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class SBSTickEvent {
    private static int previousServerTickCount = -1;

    @SubscribeEvent
    public static void SBSonServerTick(TickEvent.ServerTickEvent event) {
//        try {
//            printInGameMsg("=====Phase======" +event.phase.name());
//            printInGameMsg("time: "+getMinecraftInstance().level.getDayTime());
//        }
//        catch (Exception ignored) {}
        int currentServerTickCount;
        MinecraftServer mcserver;
        if (event.phase.equals(TickEvent.Phase.END) && (mcserver = getMinecraftServerInstance()) != null && previousServerTickCount != (currentServerTickCount = mcserver.getTickCount())) {
            previousServerTickCount = currentServerTickCount;

            SBSTickEventMethods.SBSRunAction();

            SBSTickEventAttributeMethods.SBSAttributesRunAction();
        }
    }
}

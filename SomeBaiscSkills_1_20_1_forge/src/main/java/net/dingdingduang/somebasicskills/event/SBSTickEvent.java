package net.dingdingduang.somebasicskills.event;


import net.dingdingduang.somebasicskills.Constants;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftInstance;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.printInGameMsg;

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
        if (event.phase.equals(TickEvent.Phase.END) && previousServerTickCount != (currentServerTickCount = event.getServer().getTickCount())) {
            previousServerTickCount = currentServerTickCount;

            SBSTickEventMethods.SBSRunAction();

            SBSTickEventAttributeMethods.SBSAttributesRunAction();
        }
    }
}

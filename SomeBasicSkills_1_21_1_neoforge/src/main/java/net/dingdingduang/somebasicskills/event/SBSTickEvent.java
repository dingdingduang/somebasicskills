package net.dingdingduang.somebasicskills.event;


import net.dingdingduang.somebasicskills.Constants;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@EventBusSubscriber(modid = Constants.MOD_ID)
public class SBSTickEvent {
    private static int previousServerTickCount = -1;

    @SubscribeEvent
    public static void SBSonServerTick(ServerTickEvent.Post event) {
//        try {
//            printInGameMsg("=====Phase======" +event.phase.name());
//            printInGameMsg("time: "+getMinecraftInstance().level.getDayTime());
//        }
//        catch (Exception ignored) {}
        int currentServerTickCount;
        if (previousServerTickCount != (currentServerTickCount = event.getServer().getTickCount())) {
            previousServerTickCount = currentServerTickCount;

            SBSTickEventMethods.SBSRunAction();

            SBSTickEventAttributeMethods.SBSAttributesRunAction();
        }
    }
}

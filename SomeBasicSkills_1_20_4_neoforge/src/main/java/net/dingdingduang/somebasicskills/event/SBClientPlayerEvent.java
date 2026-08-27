package net.dingdingduang.somebasicskills.event;

import net.dingdingduang.somebasicskills.Constants;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;

import static net.dingdingduang.somebasicskills.event.RemovePlayerDataWhenLogout.RemoveClientPlayerData;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class SBClientPlayerEvent {
//    @SubscribeEvent
//    public static void DfoSwdStopWhenClientPlayerLogin(ClientPlayerNetworkEvent.LoggingIn event) {
//        KeyMappingInit.KeyMappingInitialization();
//    }

    @SubscribeEvent
    public static void DfoSwdStopWhenClientPlayerLogout(ClientPlayerNetworkEvent.LoggingOut event) {
//        getCPlayerState().put(Constants.IS_IN_ACTION, Constants.ACTION_OFF);
//        getCPlayerState().put(Constants.IS_CHANNELING, Constants.ACTION_OFF);
//        setCPlayerIsImmobilized(false);
        RemoveClientPlayerData();
    }
}

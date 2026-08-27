package net.dingdingduang.somebasicskills;

import net.dingdingduang.somebasicskills.networking.NetworkingMsgEventRegistry;
import net.dingdingduang.somebasicskills.networking.NetworkingMsgInitialization;
import net.dingdingduang.somebasicskills.registries.GuiOverlayClientRegistry;
import net.dingdingduang.somebasicskills.registries.SoundRegistry;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.IExtensionPoint;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class SomeBasicSkills {
    public SomeBasicSkills(IEventBus modEventBus) {
        // Make sure the mod being absent on the other network side does not cause the client to display
        // the server as incompatible
        ModLoadingContext.get()
            .registerExtensionPoint(
                IExtensionPoint.DisplayTest.class,
                () ->
                    new IExtensionPoint.DisplayTest(
                        () -> IExtensionPoint.DisplayTest.IGNORESERVERONLY, (a, b) -> true));

        //initialization
        modEventBus.addListener(GuiOverlayClientRegistry::onRegisterOverlays);
//        modEventBus.addListener(NetworkingMsgEventRegistry::register);

    //    DistExecutor.unsafeRunWhenOn(
    //        Dist.CLIENT,
    //        () ->
    //            () -> {
    //              modEventBus.addListener(ClientGui::registerClientGui);
    //            });
        //sound
        SoundRegistry.register(modEventBus);

    }
}

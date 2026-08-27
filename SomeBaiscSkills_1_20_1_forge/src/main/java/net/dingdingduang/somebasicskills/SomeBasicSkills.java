package net.dingdingduang.somebasicskills;

import net.dingdingduang.somebasicskills.networking.NetworkingMsgInitialization;
import net.dingdingduang.somebasicskills.registries.GuiOverlayClientRegistry;
import net.dingdingduang.somebasicskills.registries.SoundRegistry;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkConstants;

@Mod(Constants.MOD_ID)
public class SomeBasicSkills {
    public SomeBasicSkills() {
        // Make sure the mod being absent on the other network side does not cause the client to display
        // the server as incompatible
        ModLoadingContext.get()
            .registerExtensionPoint(
                IExtensionPoint.DisplayTest.class,
                () ->
                    new IExtensionPoint.DisplayTest(
                        () -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //initialization
        modEventBus.addListener(GuiOverlayClientRegistry::onRegisterOverlays);
        NetworkingMsgInitialization.register();

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

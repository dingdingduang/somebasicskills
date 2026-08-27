package net.dingdingduang.somebasicskills;

import net.dingdingduang.somebasicskills.keyboard.SomeBasicSkillTreeScreenKeySetting;
import net.dingdingduang.somebasicskills.networking.NetworkPacketRegister;
import net.dingdingduang.somebasicskills.registries.GuiOverlayClientRegistry;
import net.fabricmc.api.ClientModInitializer;

public class SomeBasicSkillsClientInitialization implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
//        NetworkPacketRegister.registerClientPacket();
        SomeBasicSkillTreeScreenKeySetting.register();
        GuiOverlayClientRegistry.registerFabricClientOverlays();
    }
}

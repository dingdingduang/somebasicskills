package net.dingdingduang.somebasicskills;

import net.dingdingduang.somebasicskills.event.SBSLivingEntityFabricEvent;
import net.dingdingduang.somebasicskills.event.SBSTickFabricEvent;
import net.dingdingduang.somebasicskills.event.SBServerPlayerFabricEvent;
import net.dingdingduang.somebasicskills.networking.NetworkPacketRegister;

import net.dingdingduang.somebasicskills.registries.SoundRegistry;
import net.dingdingduang.somebasicskills.sbsattributes.SBSAttributes;
import net.fabricmc.api.ModInitializer;

//import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class SomeBasicSkills implements ModInitializer {
    public static final String MOD_ID = Constants.MOD_ID;

    public void onInitialize() {
//        new EntityRegistry();
//        FabricDefaultAttributeRegistry.register(EntityRegistry.GEO_EXAMPLE_ENTITY,
//                EntityUtils.createGenericEntityAttributes());
//        FabricDefaultAttributeRegistry.register(EntityRegistry.EXTENDED_RENDERER_EXAMPLE,
//                EntityUtils.createGenericEntityAttributes());
//        FabricDefaultAttributeRegistry.register(EntityRegistry.BIKE_ENTITY,
//                EntityUtils.createGenericEntityAttributes());
//        FabricDefaultAttributeRegistry.register(EntityRegistry.CAR_ENTITY,
//                EntityUtils.createGenericEntityAttributes());
//        FabricDefaultAttributeRegistry.register(EntityRegistry.GEOLAYERENTITY,
//                EntityUtils.createGenericEntityAttributes());
//        FabricDefaultAttributeRegistry.register(EntityRegistry.TEXTURE_PER_BONE_EXAMPLE,
//                EntityUtils.createGenericEntityAttributes());
//        new ItemRegistry();
//        new TileRegistry();
//        new BlockRegistry();
        new SoundRegistry();
        new SBSAttributes();
        NetworkPacketRegister.registerClientPacket();
        NetworkPacketRegister.registerServerPacket();
        SBServerPlayerFabricEvent.register();
        SBSLivingEntityFabricEvent.registerEvent();
        SBSTickFabricEvent.registerEvent();

        //sound
//        modEventBus.addListener(SomeBasicSkillTreeScreenKeySetting::registerKeyMappingsEvent);
//        modEventBus.addListener(GuiOverlayClientRegistry::registerOverlays);
//        SoundRegistry.register(modEventBus);
    }
}

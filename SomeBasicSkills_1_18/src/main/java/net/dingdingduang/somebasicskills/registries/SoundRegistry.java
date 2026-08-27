package net.dingdingduang.somebasicskills.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.dingdingduang.somebasicskills.Constants;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUND_REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Constants.MOD_ID);

    //Particles
    public static final RegistryObject<SoundEvent> COOLDOWN_ERR_SOUND = SOUND_REGISTRY.register("btn_activate_err", () -> new SoundEvent(getMCResourceLocation(Constants.MOD_ID, "btn_activate_err")));
    public static final RegistryObject<SoundEvent> KEYBOARD_ERR_SOUND = SOUND_REGISTRY.register("btn_keyboard_err", () -> new SoundEvent(getMCResourceLocation(Constants.MOD_ID, "btn_keyboard_err")));
    public static final RegistryObject<SoundEvent> CHANNELING_SOUND = SOUND_REGISTRY.register("channelling_sound", () -> new SoundEvent(getMCResourceLocation(Constants.MOD_ID, "channelling_sound")));
    public static final RegistryObject<SoundEvent> CHANNELING_FINISHED_SOUND = SOUND_REGISTRY.register("channelling_finished_sound", () -> new SoundEvent(getMCResourceLocation(Constants.MOD_ID, "channelling_finished_sound")));
    public static final RegistryObject<SoundEvent> CHANNELING_FAILED_SOUND = SOUND_REGISTRY.register("channelling_failed_sound", () -> new SoundEvent(getMCResourceLocation(Constants.MOD_ID, "channelling_failed_sound")));
    public static final RegistryObject<SoundEvent> COMMON_0_1_LEAP_SOUND = SOUND_REGISTRY.register("common_0_1_leap", () -> new SoundEvent(getMCResourceLocation(Constants.MOD_ID, "common_0_1_leap")));

//    public static final RegistryObject<SoundEvent> COOLDOWN_ERR = createSoundEvent("btn_activate_err");
//    private static RegistryObject<SoundEvent> createSoundEvent(final String soundName) {
//        return SOUND_REGISTRY.register(soundName, () -> new SoundEvent(getMCResourceLocation(Constants.MOD_ID, soundName)));
//    }

    public static void register(IEventBus eventBus) {
        SOUND_REGISTRY.register(eventBus);
    }
}

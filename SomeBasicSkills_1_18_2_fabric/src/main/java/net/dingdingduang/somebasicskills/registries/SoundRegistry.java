package net.dingdingduang.somebasicskills.registries;

import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;

public class SoundRegistry {
//    public static final DeferredRegister<SoundEvent> SOUND_REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Constants.MOD_ID);

    //Particles
    public static final SoundEvent COOLDOWN_ERR_SOUND = register(Constants.MOD_ID, "btn_activate_err");
    public static final SoundEvent KEYBOARD_ERR_SOUND = register(Constants.MOD_ID, "btn_keyboard_err");
    public static final SoundEvent CHANNELING_SOUND = register(Constants.MOD_ID, "channelling_sound");
    public static final SoundEvent CHANNELING_FINISHED_SOUND = register(Constants.MOD_ID, "channelling_finished_sound");
    public static final SoundEvent CHANNELING_FAILED_SOUND = register(Constants.MOD_ID, "channelling_failed_sound");
    public static final SoundEvent COMMON_0_1_LEAP_SOUND = register(Constants.MOD_ID, "common_0_1_leap");

//    public static final SoundEvent COOLDOWN_ERR = createSoundEvent("btn_activate_err");
//    private static SoundEvent createSoundEvent(final String soundName) {
//        return SOUND_REGISTRY.register(soundName, () -> new SoundEvent(getMCResourceLocation(Constants.MOD_ID, soundName)));
//    }

    public static SoundEvent register(String namespace, String path) {
        return Registry.register(Registry.SOUND_EVENT, path, new SoundEvent(getMCResourceLocation(namespace, path)));
    }

//    public static void register(IEventBus eventBus) {
//        SOUND_REGISTRY.register(eventBus);
//    }
}

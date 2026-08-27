package net.dingdingduang.somebasicskills.registries;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

import net.dingdingduang.somebasicskills.Constants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUND_REGISTRY = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Constants.MOD_ID);

    //Particles
    public static final Holder<SoundEvent> COOLDOWN_ERR_SOUND = SOUND_REGISTRY.register("btn_activate_err", () -> SoundEvent.createVariableRangeEvent(getMCResourceLocation(Constants.MOD_ID, "btn_activate_err")));
    public static final Holder<SoundEvent> KEYBOARD_ERR_SOUND = SOUND_REGISTRY.register("btn_keyboard_err", () -> SoundEvent.createVariableRangeEvent(getMCResourceLocation(Constants.MOD_ID, "btn_keyboard_err")));
    public static final Holder<SoundEvent> CHANNELING_SOUND = SOUND_REGISTRY.register("channelling_sound", () -> SoundEvent.createVariableRangeEvent(getMCResourceLocation(Constants.MOD_ID, "channelling_sound")));
    public static final Holder<SoundEvent> CHANNELING_FINISHED_SOUND = SOUND_REGISTRY.register("channelling_finished_sound", () -> SoundEvent.createVariableRangeEvent(getMCResourceLocation(Constants.MOD_ID, "channelling_finished_sound")));
    public static final Holder<SoundEvent> CHANNELING_FAILED_SOUND = SOUND_REGISTRY.register("channelling_failed_sound", () -> SoundEvent.createVariableRangeEvent(getMCResourceLocation(Constants.MOD_ID, "channelling_failed_sound")));
    public static final Holder<SoundEvent> COMMON_0_1_LEAP_SOUND = SOUND_REGISTRY.register("common_0_1_leap", () -> SoundEvent.createVariableRangeEvent(getMCResourceLocation(Constants.MOD_ID, "common_0_1_leap")));

    public static void register(IEventBus eventBus) {
        SOUND_REGISTRY.register(eventBus);
    }
}

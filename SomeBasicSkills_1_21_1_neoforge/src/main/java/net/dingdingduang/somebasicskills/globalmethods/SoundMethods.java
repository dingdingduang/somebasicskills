package net.dingdingduang.somebasicskills.globalmethods;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.registries.SoundRegistry;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getClientLevel;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftInstance;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerConfig2Settings;

public class SoundMethods {
    public static SoundInstance setupSimpleSoundInstance(SoundEvent sound1, float pitch, float volume) {
        return SimpleSoundInstance.forUI(sound1, pitch, volume);
    }

    public static void PlayLocalSound(SoundInstance soundinstance1) {
        getMinecraftInstance().getSoundManager().play(soundinstance1);
    }

    public static void PlayLocalDefaultBtnPressedSound(float pitch, float volume) {
        PlayLocalSound(setupSimpleSoundInstance(SoundEvents.UI_BUTTON_CLICK.value(), pitch, volume));
    }

    public static void PlayLocalInCooldownSound(float pitch, float volume) {
        if (getCPlayerConfig2Settings().containsKey(Constants.SB_GENERAL_SETTING)
                && getCPlayerConfig2Settings().get(Constants.SB_GENERAL_SETTING).containsKey(Constants.PLAY_SKILL_ERR_SOUND)
        ) {
            if (getCPlayerConfig2Settings().get(Constants.SB_GENERAL_SETTING).get(Constants.PLAY_SKILL_ERR_SOUND).getIntValue() == 0) {
                PlayLocalSound(setupSimpleSoundInstance(SoundRegistry.COOLDOWN_ERR_SOUND.value(), pitch, volume));
            }
        }
    }

    public static void PlayLocalKeyboardErrSound(float pitch, float volume) {
        PlayLocalSound(setupSimpleSoundInstance(SoundRegistry.KEYBOARD_ERR_SOUND.value(), pitch, volume));
    }

//    public static void PlaySoundAtEntityLocation(Entity entity, SoundEvent sound, float volume, float pitch) {
//        entity.playSound(sound, volume, pitch);
//    }

    public static void PlaySoundAtLocation(SoundEvent sound, Level worldLevel, double x1, double y1, double z1, float volume, float pitch) {
        worldLevel.playSound((Player) null, x1, y1, z1, sound, SoundSource.NEUTRAL, volume, pitch);
    }

    public static void PlaySoundAtLivingEntityLocationEX(LivingEntity entity1, SoundEvent sound, float volume, float pitch) {
        //play sound at target location after entity finishes the action
        PlaySoundAtLocation(sound, entity1.level(), entity1.getX(), entity1.getY(), entity1.getZ(), volume, pitch);
//        if (!entity1.level().isClientSide()) {
//            PlaySoundAtEntityLocation(entity1, sound, volume, pitch);
//        }
//        //play sound on client if in single player mode
//        if (getMinecraftInstance().isSingleplayer()){
//            PlaySoundAtLocation(sound, entity1.level(), entity1.getX(), entity1.getY(), entity1.getZ(), volume, pitch);
//        }
    }

    public static void PlayClientSound(Vector3f vec, SoundEvent sound, float volume, float pitch) {
        getClientLevel().playLocalSound(vec.x(), vec.y(), vec.z(), sound, SoundSource.NEUTRAL, volume, pitch, false);
    }

    public static void PlaySBSLeapSoundAtLocation(Level worldLevel, double x1, double y1, double z1, float volume, float pitch) {
        PlaySoundAtLocation(SoundRegistry.COMMON_0_1_LEAP_SOUND.value(), worldLevel, x1, y1, z1, volume, pitch);
    }

    public static void PlaySBSChannelingSoundAtLocation(Level worldLevel, double x1, double y1, double z1, float volume, float pitch) {
        PlaySoundAtLocation(SoundRegistry.CHANNELING_SOUND.value(), worldLevel, x1, y1, z1, volume, pitch);
    }

    public static void PlaySBSChannelingFinishedSoundAtLocation(Level worldLevel, double x1, double y1, double z1, float volume, float pitch) {
        PlaySoundAtLocation(SoundRegistry.CHANNELING_FINISHED_SOUND.value(), worldLevel, x1, y1, z1, volume, pitch);
    }

    public static void PlaySBSChannelingFailedSoundAtLocation(Level worldLevel, double x1, double y1, double z1, float volume, float pitch) {
        PlaySoundAtLocation(SoundRegistry.CHANNELING_FAILED_SOUND.value(), worldLevel, x1, y1, z1, volume, pitch);
    }
}

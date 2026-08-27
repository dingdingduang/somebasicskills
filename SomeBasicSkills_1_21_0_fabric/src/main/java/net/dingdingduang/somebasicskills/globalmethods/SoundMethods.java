package net.dingdingduang.somebasicskills.globalmethods;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.registries.SoundRegistry;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.joml.Vector3f;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getClientLevel;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftInstance;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerConfig2Settings;

public class SoundMethods {
    public static SoundInstance setupSimpleSoundInstance(SoundEvent sound1, float pitch, float volume) {
        return PositionedSoundInstance.master(sound1, pitch, volume);
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
                PlayLocalSound(setupSimpleSoundInstance(SoundRegistry.COOLDOWN_ERR_SOUND, pitch, volume));
            }
        }
    }

    public static void PlayLocalKeyboardErrSound(float pitch, float volume) {
        PlayLocalSound(setupSimpleSoundInstance(SoundRegistry.KEYBOARD_ERR_SOUND, pitch, volume));
    }

//    public static void PlaySoundAtEntityLocation(Entity entity, SoundEvent sound, float volume, float pitch) {
//        entity.playSound(sound, volume, pitch);
//    }

    public static void PlaySoundAtLocation(SoundEvent sound, World worldLevel, double x1, double y1, double z1, float volume, float pitch) {
        worldLevel.playSound((PlayerEntity) null, x1, y1, z1, sound, SoundCategory.NEUTRAL, volume, pitch);
    }

    public static void PlaySoundAtLivingEntityLocationEX(LivingEntity entity1, SoundEvent sound, float volume, float pitch) {
        //play sound at target location after entity finishes the action
        PlaySoundAtLocation(sound, entity1.getWorld(), entity1.getX(), entity1.getY(), entity1.getZ(), volume, pitch);
//        if (!entity1.getWorld().isClientSide()) {
//            PlaySoundAtEntityLocation(entity1, sound, volume, pitch);
//        }
//        //play sound on client if in single player mode
//        if (getMinecraftInstance().isSingleplayer()){
//            PlaySoundAtLocation(sound, entity1.getWorld(), entity1.getX(), entity1.getY(), entity1.getZ(), volume, pitch);
//        }
    }

    public static void PlayClientSound(Vector3f vec, SoundEvent sound, float volume, float pitch) {
        getClientLevel().playSound(vec.x(), vec.y(), vec.z(), sound, SoundCategory.NEUTRAL, volume, pitch, false);
    }
}

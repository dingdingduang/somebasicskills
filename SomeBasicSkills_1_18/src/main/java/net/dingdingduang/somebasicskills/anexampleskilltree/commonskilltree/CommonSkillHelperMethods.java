package net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftInstance;

public class CommonSkillHelperMethods {
    public static int helperFloor(double pValue) {
        int roundVal = (int) pValue;
        return pValue < (double) roundVal ? roundVal - 1 : roundVal;
    }

    public static Player helperGetClientPlayer() {
        return getMinecraftInstance().player;
    }

    public static boolean helperGetEntityOnGround(Entity tempEntity) {
        return tempEntity.isOnGround();
    }

    public static Level helperGetEntityWorldLevel(Entity tempEntity) {
        return tempEntity.getLevel();
    }

    public static void helperSetDelta(Entity tempEntity, double dx, double dy, double dz) {
        tempEntity.setDeltaMovement(dx, dy, dz);
    }

    public static double helperGetEntityX(Entity tempEntity) {
        return tempEntity.getX();
    }

    public static double helperGetEntityY(Entity tempEntity) {
        return tempEntity.getY();
    }

    public static double helperGetEntityZ(Entity tempEntity) {
        return tempEntity.getZ();
    }

    public static float helperGetEntityYRot(Entity tempEntity) {
        return tempEntity.getYRot();
    }
}

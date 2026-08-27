package net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftInstance;

public class CommonSkillHelperMethods {
    public static int helperFloor(double pValue) {
        int roundVal = (int) pValue;
        return pValue < (double) roundVal ? roundVal - 1 : roundVal;
    }

    public static PlayerEntity helperGetClientPlayer() {
        return getMinecraftInstance().player;
    }

    public static boolean helperGetEntityOnGround(Entity tempEntity) {
        return tempEntity.isOnGround();
    }

    public static World helperGetEntityWorldLevel(Entity tempEntity) {
        return tempEntity.getWorld();
    }

    public static void helperSetDelta(Entity tempEntity, double dx, double dy, double dz) {
        tempEntity.setVelocity(dx, dy, dz);
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
        return tempEntity.getYaw();
    }
}

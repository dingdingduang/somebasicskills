package net.dingdingduang.somebasicskills.sbsattributes;

import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import net.minecraft.entity.attribute.EntityAttributeModifier;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;

public class MCDefaultAttributesMethods {
    public static final Identifier MC_DEFAULT_ATTR_MAX_HEALTH_BASE_CONSTANT_RES_LOC = getMCResourceLocation(Constants.MOD_ID, "mc_default_attr_max_health_base_constant_res_loc");
    public static final Identifier MC_DEFAULT_ATTR_MAX_HEALTH_MULTIPLIER_RES_LOC = getMCResourceLocation(Constants.MOD_ID, "mc_default_attr_max_health_multiplier_res_loc");
    public static final Identifier MC_DEFAULT_ATTR_ARMOR_BASE_CONSTANT_RES_LOC = getMCResourceLocation(Constants.MOD_ID, "mc_default_attr_armor_base_constant_res_loc");
    public static final Identifier MC_DEFAULT_ATTR_ARMOR_MULTIPLIER_RES_LOC = getMCResourceLocation(Constants.MOD_ID, "mc_default_attr_armor_multiplier_res_loc");
    public static final Identifier MC_DEFAULT_ATTR_ARMOR_BASE_CONSTANT_TOUGHNESS_RES_LOC = getMCResourceLocation(Constants.MOD_ID, "mc_default_attr_armor_toughness_base_constant_res_loc");
    public static final Identifier MC_DEFAULT_ATTR_ARMOR_TOUGHNESS_MULTIPLIER_RES_LOC = getMCResourceLocation(Constants.MOD_ID, "mc_default_attr_armor_toughness_multiplier_res_loc");
    public static final Identifier MC_DEFAULT_ATTR_ATTACK_DAAMAGE_BASE_CONSTANT_DAMAGE_RES_LOC = getMCResourceLocation(Constants.MOD_ID, "mc_default_attr_attack_damage_base_constant_res_loc");
    public static final Identifier MC_DEFAULT_ATTR_ATTACK_DAMAGE_MULTIPLIER_RES_LOC = getMCResourceLocation(Constants.MOD_ID, "mc_default_attr_attack_damage_multiplier_res_loc");
    public static final Identifier MC_DEFAULT_ATTR_ATTACK_BASE_CONSTANT_KNOCKBACK_RES_LOC = getMCResourceLocation(Constants.MOD_ID, "mc_default_attr_attack_knockback_base_constant_res_loc");
    public static final Identifier MC_DEFAULT_ATTR_ATTACK_KNOCKBACK_MULTIPLIER_RES_LOC = getMCResourceLocation(Constants.MOD_ID, "mc_default_attr_attack_knockback_multiplier_res_loc");
    public static final Identifier MC_DEFAULT_ATTR_ATTACK_BASE_CONSTANT_SPEED_MULTIPLIER_RES_LOC = getMCResourceLocation(Constants.MOD_ID, "mc_default_attr_attack_speed_base_constant_res_loc");
    public static final Identifier MC_DEFAULT_ATTR_ATTACK_SPEED_MULTIPLIER_RES_LOC = getMCResourceLocation(Constants.MOD_ID, "mc_default_attr_attack_speed_multiplier_res_loc");
    public static final Identifier MC_DEFAULT_ATTR_KNOCK_BASE_CONSTANT_BACK_RESISTANCE_RES_LOC = getMCResourceLocation(Constants.MOD_ID, "mc_default_attr_knockback_resistance_base_constant_res_loc");
    public static final Identifier MC_DEFAULT_ATTR_KNOCK_BACK_MULTIPLIER_RESISTANCE_RES_LOC = getMCResourceLocation(Constants.MOD_ID, "mc_default_attr_knockback_resistance_multiplier_res_loc");
    public static final Identifier MC_DEFAULT_ATTR_LUCK_BASE_CONSTANT_RES_LOC = getMCResourceLocation(Constants.MOD_ID, "mc_default_attr_luck_base_constant_res_loc");
    public static final Identifier MC_DEFAULT_ATTR_LUCK_MULTIPLIER_RES_LOC = getMCResourceLocation(Constants.MOD_ID, "mc_default_attr_luck_multiplier_res_loc");
    public static final Identifier MC_DEFAULT_ATTR_MOVEMENT_SPEED_BASE_CONSTANT_RES_LOC = getMCResourceLocation(Constants.MOD_ID, "mc_default_attr_movement_speed_base_constant_res_loc");
    public static final Identifier MC_DEFAULT_ATTR_MOVEMENT_SPEED_MULTIPLIER_RES_LOC = getMCResourceLocation(Constants.MOD_ID, "mc_default_attr_movement_speed_multiplier_res_loc");

    public static EntityAttributeModifier.Operation getOperation(int operation) {
        if (operation == Constants.OP_ADDITION) {
            return EntityAttributeModifier.Operation.ADD_VALUE;
        }
        else if (operation == Constants.OP_MULTIPLY_BASE) {
            return EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE;
        }
        else {
            return null;
        }
    }

    public static void AddMCMaxHealthAttribute(LivingEntity target, double amount, int operation) {
        EntityAttributeModifier.Operation mcop = getOperation(operation);
        if (mcop == null) { return; }
        Identifier MC_DEFAULT_ATTR_MAX_HEALTH_RES_LOC = (operation == Constants.OP_ADDITION) ? MC_DEFAULT_ATTR_MAX_HEALTH_BASE_CONSTANT_RES_LOC : MC_DEFAULT_ATTR_MAX_HEALTH_MULTIPLIER_RES_LOC;
        EntityAttributeModifier tempAttr = new EntityAttributeModifier(MC_DEFAULT_ATTR_MAX_HEALTH_RES_LOC, amount, mcop);
        EntityAttributeInstance tempAttrInstance = target.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (tempAttrInstance != null) {
            float EntityHP_Percent = target.getHealth() / target.getMaxHealth();
            //vvvvvvvvvvvvvvvvvvv
            if (tempAttrInstance.hasModifier(MC_DEFAULT_ATTR_MAX_HEALTH_RES_LOC)) {
                tempAttrInstance.removeModifier(MC_DEFAULT_ATTR_MAX_HEALTH_RES_LOC);
            }
            tempAttrInstance.addTemporaryModifier(tempAttr);
            //^^^^^^^^^^^^^^^^^^
            target.setHealth(EntityHP_Percent * target.getMaxHealth());
        }
    }
}

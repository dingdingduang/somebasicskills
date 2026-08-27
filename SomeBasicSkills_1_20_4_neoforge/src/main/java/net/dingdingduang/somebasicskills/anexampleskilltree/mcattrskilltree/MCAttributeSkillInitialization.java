package net.dingdingduang.somebasicskills.anexampleskilltree.mcattrskilltree;

import net.dingdingduang.somebasicskills.anexampleskilltree.mcattrskilltree.ex.*;
import net.dingdingduang.somebasicskills.anexampleskilltree.mcattrskilltree.passive.*;

public class MCAttributeSkillInitialization {
    public static final String MC_ATTR_ATTACK_DMG_0_0 = "MC_ATTR_0_0";
    public static final String MC_ATTR_ATTACK_KNOCKBACK_0_1 = "MC_ATTR_0_1";
    public static final String MC_ATTR_MOVEMENT_SPEED_0_2 = "MC_ATTR_0_2";
    public static final String MC_ATTR_ARMOR_0_3 = "MC_ATTR_0_3";
    public static final String MC_ATTR_ARMOR_TOUGHNESS_0_4 = "MC_ATTR_0_4";
    public static final String MC_ATTR_KNOCKBACK_RESISTANCE_0_5 = "MC_ATTR_0_5";
    public static final String MC_ATTR_MAX_HEALTH_0_6 = "MC_ATTR_0_6";
//    public static final String MC_ATTR_JUMP_STRENGTH_0_7 = "MC_ATTR_0_7";
//    public static final String MC_ATTR_MINING_EFFICIENCY_0_8 = "MC_ATTR_0_8";
    public static final String MC_ATTR_LUCK_0_9 = "MC_ATTR_0_9";

    public static final String EXMC_ATTR_ATTACK_DMG_MULTIPLIER_0_0 = "EXMC_ATTR_0_0";
    public static final String EXMC_ATTR_ATTACK_KNOCKBACK_MULTIPLIER_0_1 = "EXMC_ATTR_0_1";
    public static final String EXMC_ATTR_MOVEMENT_SPEED_MULTIPLIER_0_2 = "EXMC_ATTR_0_2";
    public static final String EXMC_ATTR_ARMOR_MULTIPLIER_0_3 = "EXMC_ATTR_0_3";
    public static final String EXMC_ATTR_ARMOR_TOUGHNESS_MULTIPLIER_0_4 = "EXMC_ATTR_0_4";
    public static final String EXMC_ATTR_KNOCKBACK_RESISTANCE_MULTIPLIER_0_5 = "EXMC_ATTR_0_5";
    public static final String EXMC_ATTR_MAX_HEALTH_MULTIPLIER_0_6 = "EXMC_ATTR_0_6";
//    public static final String EXMC_ATTR_JUMP_STRENGTH_MULTIPLIER_0_7 = "EXMC_ATTR_0_7";
//    public static final String EXMC_ATTR_MINING_EFFICIENCY_MULTIPLIER_0_8 = "EXMC_ATTR_0_8";
    public static final String EXMC_ATTR_LUCK_MULTIPLIER_0_9 = "EXMC_ATTR_0_9";

    public static void MCAttributeSkillInit() {
        //Passive
        MCATTR000.init();
        MCATTR001.init();
        MCATTR002.init();
        MCATTR003.init();
        MCATTR004.init();
        MCATTR005.init();
        MCATTR006.init();
//        MCATTR007.init();
//        MCATTR008.init();
        MCATTR009.init();

        //ex
        EXMCATTR000.init();
        EXMCATTR001.init();
        EXMCATTR002.init();
        EXMCATTR003.init();
        EXMCATTR004.init();
        EXMCATTR005.init();
        EXMCATTR006.init();
//        EXMCATTR007.init();
//        EXMCATTR008.init();
        EXMCATTR009.init();
    }
}

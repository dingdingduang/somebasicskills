package net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree;

import net.dingdingduang.somebasicskills.anexampleskilltree.skills.active.*;
import net.dingdingduang.somebasicskills.anexampleskilltree.skills.ex.EXCOMMON01;
import net.dingdingduang.somebasicskills.anexampleskilltree.skills.passive.*;

public class CommonSkillInitialization {
    public static final String COMMON_001_BACKSTEP = "COMMON_0_1";
    public static final String COMMON_002_BASIC_TRAINING = "COMMON_0_2";
    public static final String COMMON_003_LEAP = "COMMON_0_3";
    public static final String COMMON_004_ANCIENT_MEMORY = "COMMON_0_4";
//    public static final String COMMON_005_THROW = "COMMON_0_5";
    public static final String COMMON_006_PHYSICAL_BACKSTAB_CRIT = "COMMON_0_6";
    public static final String COMMON_007_INDOMITABLE_SPIRIT = "COMMON_0_7";
    public static final String COMMON_008_PHYSICAL_CRIT = "COMMON_0_8";
    public static final String COMMON_009_MAGICAL_CRIT = "COMMON_0_9";
    public static final String COMMON_010_MAGICAL_BACKSTAB_CRIT = "COMMON_0_10";
//    public static final String COMMON_011_QUICK_REBOUND = "COMMON_0_11";

    public static final String COMMON_101_GENERAL_ARMOR_MASTERY = "COMMON_1_1";
    public static final String COMMON_102_CLOTH_ARMOR_MASTERY = "COMMON_1_2";
    public static final String COMMON_103_HEAVY_ARMOR_MASTERY = "COMMON_1_3";
    public static final String COMMON_104_LEATHER_ARMOR_MASTERY = "COMMON_1_4";
    public static final String COMMON_105_LIGHT_ARMOR_MASTERY = "COMMON_1_5";
    public static final String COMMON_106_PLATE_ARMOR_MASTERY = "COMMON_1_6";

    public static final String EX_COMMON_001_BASIC_TRAINING_UPGRADE_MASTERY = "EXCOMMON_0_1";

    public static void CommonSkillInit() {
        //Active
        COMMON001.init();
        COMMON003.init();
        COMMON004.init();
        COMMON007.init();
        COMMON011.init();


        //Passive
        COMMON002.init();
        COMMON005.init();
        COMMON006.init();
        COMMON008.init();
        COMMON009.init();
        COMMON010.init();
        COMMON101.init();
        COMMON102.init();
        COMMON103.init();
        COMMON104.init();
        COMMON105.init();
        COMMON106.init();
        EXCOMMON01.init();
    }

//    public static void CommonSkillActiveInit() {
//
//    }
//
//    public static void CommonSkillPassiveInit() {
//
//    }
}

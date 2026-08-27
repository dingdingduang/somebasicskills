package net.dingdingduang.somebasicskills.globalvalues;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.GlobalClientPlayerValuesInit;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.SkillDataInit;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataJsonReader.SkillDataJsonInit;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataSkillTreeInitialization.initializeSkillDataMethodAction;

public class GlobalClientValuesInit {
    private static boolean isGlobalInitialized = false;

    public static void globalClientValuesInit() {
        //initialize map for data to be read from file
        GlobalClientPlayerValuesInit();
        //initialize all hashmap gonna be used by SkillDataJson
        SkillDataInit();
        //read all vals and put them to corresponding maps from json files
        SkillDataJsonInit();
        //initialize all SkillDataJson MethodAction
        initializeSkillDataMethodAction();

        isGlobalInitialized = true;
    }

    public static boolean isGlobalClientValuesInitialized() {
        return isGlobalInitialized;
    }
    public static void setGlobalClientValuesInitialized(boolean a) {
        isGlobalInitialized = a;
    }
}

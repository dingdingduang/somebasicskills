package net.dingdingduang.somebasicskills.globalvalues;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.GlobalServerLivingEntityValuesInit;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.GlobalServerPlayerValuesInit;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.SkillDataInit;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataJsonReader.SkillDataJsonInit;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataSkillTreeInitialization.initializeSkillDataMethodAction;

public class GlobalServerValuesInit {
    private static boolean isGlobalInitialized = false;

    public static void globalServerValuesInit() {
        //initialize map for data to be read from file
        GlobalServerPlayerValuesInit();
        GlobalServerLivingEntityValuesInit();
        //initialize all hashmap gonna be used by SkillDataJson
        SkillDataInit();
        //read all vals and put them to corresponding maps from json files
        SkillDataJsonInit();
        //initialize all SkillDataJson MethodAction
        initializeSkillDataMethodAction();

        isGlobalInitialized = true;
    }

    public static boolean isGlobalServerValuesInitialized() {
        return isGlobalInitialized;
    }
    public static void setGlobalServerValuesInitialized(boolean a) {
        isGlobalInitialized = a;
    }
}

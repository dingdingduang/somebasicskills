package net.dingdingduang.somebasicskills.skilldata;

import static net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree.CommonSkillInitialization.CommonSkillInit;
import static net.dingdingduang.somebasicskills.anexampleskilltree.mcattrskilltree.MCAttributeSkillInitialization.MCAttributeSkillInit;

public class SkillDataSkillTreeInitialization {
    public SkillDataSkillTreeInitialization() { }

    public void SBSkillActionMixinHelper() {

    }

    public static void initializeSkillDataMethodAction() {
        //add defined method action here based on skill id
        CommonSkillInit();
        MCAttributeSkillInit();

        //Mixin
        SkillDataSkillTreeInitialization a = new SkillDataSkillTreeInitialization();
        a.SBSkillActionMixinHelper();
    }
}

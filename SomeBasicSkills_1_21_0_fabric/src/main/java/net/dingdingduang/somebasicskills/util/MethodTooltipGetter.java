package net.dingdingduang.somebasicskills.util;

import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;

public interface MethodTooltipGetter {
    String getTooltipFromSkillData(SkillDataJson skillData, int currentSkillLevel);
}

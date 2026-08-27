package net.dingdingduang.somebasicskills.util;

import net.minecraft.entity.LivingEntity;

public interface SkillServerConditionRequirement {
    boolean executeAction(LivingEntity entity1, String SkillID);
}

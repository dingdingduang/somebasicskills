package net.dingdingduang.somebasicskills.util;

import net.minecraft.world.entity.LivingEntity;

public interface SkillServerConditionRequirement {
    boolean executeAction(LivingEntity entity1, String SkillID);
}

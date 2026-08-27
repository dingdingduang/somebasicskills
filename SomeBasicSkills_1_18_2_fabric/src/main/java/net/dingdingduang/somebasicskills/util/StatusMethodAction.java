package net.dingdingduang.somebasicskills.util;

import net.minecraft.entity.LivingEntity;

public interface StatusMethodAction {
    void executeAction(LivingEntity entity, double amount, String SkillID);
}

package net.dingdingduang.somebasicskills.util;

import net.minecraft.world.entity.LivingEntity;

public interface StatusMethodAction {
    void executeAction(LivingEntity entity, double amount, String SkillID);
}

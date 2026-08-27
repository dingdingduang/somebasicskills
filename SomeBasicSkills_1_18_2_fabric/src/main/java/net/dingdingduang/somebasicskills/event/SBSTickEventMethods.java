package net.dingdingduang.somebasicskills.event;

import net.dingdingduang.somebasicskills.util.MethodAction;
import net.minecraft.entity.LivingEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class SBSTickEventMethods {
    private static boolean isMethodActionTimerActive = false;

    private static ConcurrentHashMap<LivingEntity, ConcurrentHashMap<String, Integer>> ServerEntity2SkillIDWithPeriod = new ConcurrentHashMap<>();
    private static HashMap<LivingEntity, HashMap<String, MethodAction>> ServerEntity2SkillIDWithAction = new HashMap<>();
    private static HashMap<LivingEntity, HashMap<String, Boolean>> ServerEntity2SkillIDActionDone = new HashMap<>();
    private static HashMap<LivingEntity, HashMap<String, Integer>> ServerEntity2SkillIDActionTimer = new HashMap<>();
    private static HashMap<LivingEntity, HashMap<String, Integer>> ServerEntity2SkillIDActionPreviousTriggeredTimer = new HashMap<>();
    private static HashMap<LivingEntity, HashMap<String, MethodAction>> ServerEntity2SkillIDWithCleanAction = new HashMap<>();

    public static void SBSRunAction() {
        if (isMethodActionTimerActive) {
            HashSet<LivingEntity> entityToBeRemovedSet = new HashSet<LivingEntity>();
            for (LivingEntity entity : ServerEntity2SkillIDWithPeriod.keySet()) {
                if (entity.isAlive()) {
                    ConcurrentHashMap<String, Integer> entitySkill = ServerEntity2SkillIDWithPeriod.get(entity);
                    HashSet<String> skillActionToBeRemovedSet = new HashSet<String>();
                    for (String SkillID : entitySkill.keySet()) {
                        int tickPeriod = Math.max(1, entitySkill.get(SkillID));
                        boolean isActionDone = ServerEntity2SkillIDActionDone.get(entity).get(SkillID);

                        int skillTimer = ServerEntity2SkillIDActionTimer.get(entity).get(SkillID);
                        int currentTriggeredTimer = skillTimer / tickPeriod;
                        int previousSkillTriggeredTimer = ServerEntity2SkillIDActionPreviousTriggeredTimer.get(entity).get(SkillID);
                        if (!isActionDone && previousSkillTriggeredTimer != currentTriggeredTimer) {
                            MethodAction action = ServerEntity2SkillIDWithAction.get(entity).get(SkillID);
                            if (action != null) {
                                action.executeAction(entity);
                            }
                            ServerEntity2SkillIDActionPreviousTriggeredTimer.get(entity).put(SkillID, currentTriggeredTimer);
                        }
                        ServerEntity2SkillIDActionTimer.get(entity).put(SkillID, skillTimer + 1);
                        if (isActionDone) {
                            skillActionToBeRemovedSet.add(SkillID);
                        }
                    }
                    for (String SkillIDTBR : skillActionToBeRemovedSet) {
                        entitySkill.remove(SkillIDTBR);
                        ServerEntity2SkillIDWithPeriod.get(entity).remove(SkillIDTBR);
                        ServerEntity2SkillIDWithAction.get(entity).remove(SkillIDTBR);
                        ServerEntity2SkillIDActionDone.get(entity).remove(SkillIDTBR);
                        ServerEntity2SkillIDActionTimer.get(entity).remove(SkillIDTBR);
                        ServerEntity2SkillIDActionPreviousTriggeredTimer.get(entity).remove(SkillIDTBR);

                        MethodAction cleanAction;
                        if (getID2SkillData().get(SkillIDTBR) != null && (cleanAction = getID2SkillData().get(SkillIDTBR).getClearSkillAction()) != null) {
                            cleanAction.executeAction(entity);
                        }

                        cleanAction = ServerEntity2SkillIDWithCleanAction.get(entity).get(SkillIDTBR);
                        if (cleanAction != null) {
                            cleanAction.executeAction(entity);
                        }

                        ServerEntity2SkillIDWithCleanAction.get(entity).remove(SkillIDTBR);
                    }
                    if (entitySkill.size() <= 0) {
                        entityToBeRemovedSet.add(entity);
                    }
                } else {
                    entityToBeRemovedSet.add(entity);

                    for (String SkillID : ServerEntity2SkillIDWithPeriod.get(entity).keySet()) {
                        MethodAction cleanAction;
                        if (getID2SkillData().get(SkillID) != null && (cleanAction = getID2SkillData().get(SkillID).getClearSkillAction()) != null) {
                            cleanAction.executeAction(entity);
                        }

                        cleanAction = ServerEntity2SkillIDWithCleanAction.get(entity).get(SkillID);
                        if (cleanAction != null) {
                            cleanAction.executeAction(entity);
                        }
                    }
                }
            }

            for (LivingEntity entityTBR : entityToBeRemovedSet) {
                ServerEntity2SkillIDWithPeriod.remove(entityTBR);
                ServerEntity2SkillIDWithAction.remove(entityTBR);
                ServerEntity2SkillIDActionDone.remove(entityTBR);
                ServerEntity2SkillIDActionTimer.remove(entityTBR);
                ServerEntity2SkillIDActionPreviousTriggeredTimer.remove(entityTBR);
                ServerEntity2SkillIDWithCleanAction.remove(entityTBR);
            }
            if (ServerEntity2SkillIDWithPeriod.size() <= 0) {
                isMethodActionTimerActive = false;
//                printInGameMsg("Timer closed.");
            }
        }
    }

    public static boolean isMethodActionTimerActive() {
        return isMethodActionTimerActive;
    }

    public static void setMethodActionTimer(LivingEntity entity, String SkillID, int tickPeriod, MethodAction action) {
//        if (getID2SkillData().get(SkillID) != null && getID2SkillData().get(SkillID).getServerConditionRequirement().executeAction(entity, SkillID)) { return; }
//        if (ServerEntity2SkillIDWithPeriod == null) {
//            ServerEntity2SkillIDWithPeriod = new ConcurrentHashMap<LivingEntity, HashMap<String, Integer>>();
//            ServerEntity2SkillIDWithAction = new HashMap<LivingEntity, HashMap<String, MethodAction>>();
//            ServerEntity2SkillIDActionDone = new HashMap<LivingEntity, HashMap<String, Boolean>>();
//            ServerEntity2SkillIDActionTimer = new HashMap<LivingEntity, HashMap<String, Integer>>();
//            ServerEntity2SkillIDWithCleanAction = new HashMap<LivingEntity, HashMap<String, MethodAction>>();
//        }
        if (!ServerEntity2SkillIDWithPeriod.containsKey(entity)) {
            ServerEntity2SkillIDWithPeriod.put(entity, new ConcurrentHashMap<>());
            ServerEntity2SkillIDWithAction.put(entity, new HashMap<>());
            ServerEntity2SkillIDActionDone.put(entity, new HashMap<>());
            ServerEntity2SkillIDActionTimer.put(entity, new HashMap<>());
            ServerEntity2SkillIDActionPreviousTriggeredTimer.put(entity, new HashMap<>());
            ServerEntity2SkillIDWithCleanAction.put(entity, new HashMap<>());
        }
        ServerEntity2SkillIDWithPeriod.get(entity).put(SkillID, tickPeriod);
        ServerEntity2SkillIDWithAction.get(entity).put(SkillID, action);
        ServerEntity2SkillIDActionDone.get(entity).put(SkillID, false);
        ServerEntity2SkillIDActionTimer.get(entity).put(SkillID, 0);
        ServerEntity2SkillIDActionPreviousTriggeredTimer.get(entity).put(SkillID, -1);
        ServerEntity2SkillIDWithCleanAction.get(entity).put(SkillID, null);

        isMethodActionTimerActive = true;
    }

    public static void setMethodActionTimerWithCleanAction(LivingEntity entity, String SkillID, int tickPeriod, MethodAction action, MethodAction cleanAction, boolean shouldOverwrite) {
//        if (getID2SkillData().get(SkillID) != null && getID2SkillData().get(SkillID).getServerConditionRequirement().executeAction(entity, SkillID)) { return; }
//        if (ServerEntity2SkillIDWithPeriod == null) {
//            ServerEntity2SkillIDWithPeriod = new ConcurrentHashMap<LivingEntity, HashMap<String, Integer>>();
//            ServerEntity2SkillIDWithAction = new HashMap<LivingEntity, HashMap<String, MethodAction>>();
//            ServerEntity2SkillIDActionDone = new HashMap<LivingEntity, HashMap<String, Boolean>>();
//            ServerEntity2SkillIDActionTimer = new HashMap<LivingEntity, HashMap<String, Integer>>();
//            ServerEntity2SkillIDWithCleanAction = new HashMap<LivingEntity, HashMap<String, MethodAction>>();
//        }
        if (!ServerEntity2SkillIDWithPeriod.containsKey(entity)) {
            ServerEntity2SkillIDWithPeriod.put(entity, new ConcurrentHashMap<>());
            ServerEntity2SkillIDWithAction.put(entity, new HashMap<>());
            ServerEntity2SkillIDActionDone.put(entity, new HashMap<>());
            ServerEntity2SkillIDActionTimer.put(entity, new HashMap<>());
            ServerEntity2SkillIDActionPreviousTriggeredTimer.put(entity, new HashMap<>());
            ServerEntity2SkillIDWithCleanAction.put(entity, new HashMap<>());
        }
        if (!ServerEntity2SkillIDWithPeriod.get(entity).containsKey(SkillID)) {
            ServerEntity2SkillIDWithAction.get(entity).put(SkillID, action);
            ServerEntity2SkillIDActionDone.get(entity).put(SkillID, false);
            ServerEntity2SkillIDWithPeriod.get(entity).put(SkillID, tickPeriod);
            ServerEntity2SkillIDActionTimer.get(entity).put(SkillID, 0);
            ServerEntity2SkillIDActionPreviousTriggeredTimer.get(entity).put(SkillID, -1);
            ServerEntity2SkillIDWithCleanAction.get(entity).put(SkillID, cleanAction);
        }
        else {
            if (shouldOverwrite) {
                ServerEntity2SkillIDWithPeriod.get(entity).put(SkillID, tickPeriod);
                ServerEntity2SkillIDWithAction.get(entity).put(SkillID, action);
                ServerEntity2SkillIDActionDone.get(entity).put(SkillID, false);
                ServerEntity2SkillIDActionTimer.get(entity).put(SkillID, 0);
                ServerEntity2SkillIDActionPreviousTriggeredTimer.get(entity).put(SkillID, -1);
                ServerEntity2SkillIDWithCleanAction.get(entity).put(SkillID, cleanAction);
            }
        }
        isMethodActionTimerActive = true;
    }

    public static void setSkillActionDone(LivingEntity entity, String SkillID) {
        if (ServerEntity2SkillIDActionDone != null && ServerEntity2SkillIDActionDone.containsKey(entity) && ServerEntity2SkillIDActionDone.get(entity).containsKey(SkillID)) {
            ServerEntity2SkillIDActionDone.get(entity).put(SkillID, true);
        }
    }

    public static void setSkillActionDone(LivingEntity entity, String SkillID, MethodAction cleanAction) {
        if (ServerEntity2SkillIDActionDone != null && ServerEntity2SkillIDActionDone.containsKey(entity) && ServerEntity2SkillIDActionDone.get(entity).containsKey(SkillID)) {
            ServerEntity2SkillIDActionDone.get(entity).put(SkillID, true);
            ServerEntity2SkillIDWithCleanAction.get(entity).put(SkillID, cleanAction);
        }
    }
}

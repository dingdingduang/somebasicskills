package net.dingdingduang.somebasicskills.globalvalues;

import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.setSPlayerOnline;

public class GlobalServerLivingEntityValues {
    // for status bleed stack, channeling status, is running walking or something else
    // to help me build skills better
    private static HashMap<LivingEntity, HashMap<String, Integer>> SLivingEntityState;

    //================================================================================================
    //Initialization for server
    public static void GlobalServerLivingEntityValuesInit() {
        SLivingEntityState = new HashMap<LivingEntity, HashMap<String, Integer>>();
    }

    //================================================================================================
    //Initialization for each login player
    public static void initializeEntityStateHashMapsWhenLogin(ServerPlayerEntity a) {
        //Entity state
        SLivingEntityState.put(a, new HashMap<String, Integer>());
        setSPlayerOnline(a, true);
    }

    //================================================================================================
    //Basic
    public static HashMap<LivingEntity, HashMap<String, Integer>> getSLivingEntityState() { return SLivingEntityState; }
    public static void setSLivingEntityState(HashMap<LivingEntity, HashMap<String, Integer>> entityState) { SLivingEntityState = entityState; }

    public static void putStateOnLivingEntity(LivingEntity entity, String tagName, int val) {
        if (!SLivingEntityState.containsKey(entity)) { SLivingEntityState.put(entity, new HashMap<String, Integer>()); }
        SLivingEntityState.get(entity).put(tagName, val);
    }

    public static boolean hasStateOnLivingEntity(LivingEntity entity, String tagName) {
        if (!SLivingEntityState.containsKey(entity)) { return false; }
        return SLivingEntityState.get(entity).containsKey(tagName);
    }

    public static int getStateFromLivingEntity(LivingEntity entity, String tagName) {
        HashMap<String, Integer> tempStateMap;
        if (SLivingEntityState.containsKey(entity) && (tempStateMap = SLivingEntityState.get(entity)).containsKey(tagName)) {
            return tempStateMap.get(tagName);
        }

        return Constants.ACTION_OFF;
    }

    public static void removeStateFromLivingEntity(LivingEntity entity, String tagName) {
        if (!SLivingEntityState.containsKey(entity)) { return; }
        HashMap<String, Integer> tempStateMap = SLivingEntityState.get(entity);
        tempStateMap.remove(tagName);
        if (tempStateMap.size() <= 0) {
            SLivingEntityState.remove(entity);
        }
    }

    public static void increaseStateOnLivingEntityByX(LivingEntity entity, String tagName, int amount) {
        if (!SLivingEntityState.containsKey(entity)) { SLivingEntityState.put(entity, new HashMap<String, Integer>());}
        HashMap<String, Integer> tempStateMap = SLivingEntityState.get(entity);
        if (!tempStateMap.containsKey(tagName)) { tempStateMap.put(tagName, amount); }
        else { tempStateMap.put(tagName, tempStateMap.get(tagName) + amount); }
    }
}

package net.dingdingduang.somebasicskills.globalmethods;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkingFetchMsgMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.LocaleLanguageMethods.getLocalizationText;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerState;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.gui.overlay.SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class ServerSkillMethods {
    public static String getSkillDefaultDescription(SkillDataJson skillData, int currentSkillLevel) {
        return getLocalizationText(skillData.getTranslatableTextDescription());
    }

    public static void initializePlayerPassiveStatusWhenLogin(ServerPlayer sp1) {
        HashMap<String, Integer> playerSkillMap = getGlobalPlayerSkillID2lvlMap().get(sp1);
        HashMap<String, SkillDataJson> skillMap = getID2SkillData();

//        int i = 0;
        for (String SkillID: playerSkillMap.keySet()) {
            if (skillMap.containsKey(SkillID)) {
                SkillDataJson skill1 = skillMap.get(SkillID);
                if ((skill1.isPassiveType() || skill1.isBothType()) && skill1.getPassiveStatusWhenLoginAction() != null) {
                    skill1.getPassiveStatusWhenLoginAction().executeAction(sp1);
//                    printInGameMsg("test: "+i++);
                }
            }
        }
    }

    public static int querySPlayerCurrentSkillLevel(ServerPlayer sp1, String querySkillID) {
        Integer skillLevel;
        if (getGlobalPlayerSkillID2lvlMap().containsKey(sp1) && (skillLevel = getGlobalPlayerSkillID2lvlMap().get(sp1).get(querySkillID)) != null) {
            return skillLevel;
        }
        return 0;
    }

    public static void SendPacketIsInActionToClientSideFromServer(LivingEntity entity1, boolean ActionOn) {
//        int isInAction = setLivingEntityIsInAction(entity1, isAction);
//        if (entity1 instanceof ServerPlayer sp1) {
//            NetworkingFetchMsgMethods.FetchPlayerStateToClientSide(sp1, Constants.IS_IN_ACTION, isInAction);
//        }
        if (ActionOn) {
            incrementEntityStateByAmountX(entity1, Constants.IS_IN_ACTION, 1);
        }
        else {
            incrementEntityStateByAmountX(entity1, Constants.IS_IN_ACTION, -1);
        }
    }

    public static void ServerUpdateLivingEntityState(LivingEntity entity1, String stateName, int isAction) {
        //server side
        HashMap<String, Integer> EntityState = getSLivingEntityState().get(entity1);
        if (EntityState != null) {
            EntityState.put(stateName, isAction);
            //if is player send data to client side
            if (entity1 instanceof ServerPlayer sp1) {
                //client side
                NetworkingFetchMsgMethods.FetchPlayerStateToClientSide(sp1, stateName, isAction);
            }
        }
    }

//    public static int setLivingEntityIsInAction(LivingEntity entity1, int isAction) {
//        HashMap<String, Integer> EntityState = getSLivingEntityState().get(entity1);
//        if (isAction == Constants.ACTION_OFF) {
//            int tempInt;
//            if (EntityState.containsKey(Constants.IS_IN_ACTION) && (tempInt = EntityState.get(Constants.IS_IN_ACTION)) > 0) {
//                EntityState.put(Constants.IS_IN_ACTION, tempInt - 1);
//                return tempInt - 1;
//            }
//            else {
//                EntityState.put(Constants.IS_IN_ACTION, isAction);
//                return isAction;
//            }
//        }
//        else {
//            int tempInt;
//            if (EntityState.containsKey(Constants.IS_IN_ACTION) && (tempInt = EntityState.get(Constants.IS_IN_ACTION)) > 0) {
//                EntityState.put(Constants.IS_IN_ACTION, tempInt + 1);
//                return tempInt + 1;
//            }
//            else {
//                EntityState.put(Constants.IS_IN_ACTION, isAction);
//                return isAction;
//            }
//        }
//    }

    public static int getLivingEntityIsInAction(LivingEntity entity) {
//        HashMap<String, Integer> EntityState = getSLivingEntityState().get(entity);
//        int tempInt = Constants.ACTION_OFF;
//        if (EntityState.containsKey(Constants.IS_IN_ACTION)) {
//            tempInt = EntityState.get(Constants.IS_IN_ACTION);
//        }
//        return tempInt;
        return getEntityStateAmount(entity, Constants.IS_IN_ACTION);
    }

    public static boolean isLivingEntityInAction(LivingEntity entity) {
        return getEntityStateAmount(entity, Constants.IS_IN_ACTION) != 0;
    }

    public static boolean isLivingEntityInChannelingAction(LivingEntity entity) {
        return getEntityStateAmount(entity, Constants.IS_CHANNELING) != 0;
    }

//    public static int getLivingEntityActionInterrupted(LivingEntity entity) {
//        HashMap<String, Integer> EntityState = getSLivingEntityState().get(entity);
//        int tempInt = Constants.ACTION_OFF;
//        if (EntityState.containsKey(Constants.ACTION_INTERRUPTED)) {
//            tempInt = EntityState.get(Constants.ACTION_INTERRUPTED);
//        }
//        return tempInt;
//    }

    public static boolean isLivingEntityActionInterrupted(LivingEntity entity) {
//        return getLivingEntityActionInterrupted(entity) != 0;
        return getEntityStateAmount(entity, Constants.ACTION_INTERRUPTED) != 0;
    }

    public static boolean isEntityChannellingInterrupted(LivingEntity entity1) {
//        HashMap<LivingEntity, HashMap<String, Integer>> AllServerLivingEntityStateMap = getSLivingEntityState();
//        if (AllServerLivingEntityStateMap.containsKey(entity1)) {
//            HashMap<String, Integer> ServerLivingEntityState = AllServerLivingEntityStateMap.get(entity1);
//            if (ServerLivingEntityState.containsKey(Constants.CHANNELING_INTERRUPTED)) {
//                return ServerLivingEntityState.get(Constants.CHANNELING_INTERRUPTED) == 1;
//            }
//        }
//        return false;
        return getEntityStateAmount(entity1, Constants.CHANNELING_INTERRUPTED) != 0;
    }

    public static boolean SBSDefaultSkillCondition(String SkillID) {
        boolean isInCD = getSkillsInCooldownClientTimerOverlayInstance().isClientPlayerSkillInCD(SkillID);
        boolean isntInAction = true;
        if (getCPlayerState().containsKey(Constants.IS_IN_ACTION)) {
            isntInAction = getCPlayerState().get(Constants.IS_IN_ACTION) == Constants.ACTION_OFF;
        }
        return !isInCD && isntInAction;
    }

    //================
    public static void incrementEntityStateByAmountX(LivingEntity entity, String StateName, int amount) {
        HashMap<String, Integer> EntityState = getSLivingEntityState().get(entity);
        if (EntityState != null) {
            Integer tempInteger;
            if ((tempInteger = EntityState.get(StateName)) != null) {
                tempInteger = tempInteger + amount;
            } else {
                tempInteger = amount;
            }
            EntityState.put(StateName, tempInteger);
            if (entity instanceof ServerPlayer sp1) {
                //client side
                int primTempInteger = tempInteger;
                int StateNameHash = StateName.hashCode();
                int ActionStateNameHash = Constants.IS_IN_ACTION.hashCode();
                if (StateNameHash != ActionStateNameHash || primTempInteger == Constants.ACTION_OFF) {
                    NetworkingFetchMsgMethods.FetchPlayerStateToClientSide(sp1, StateName, primTempInteger);
                }
            }
        }
    }

    //================
    public static void incrementEntityStateByAmountXNoPacket(LivingEntity entity, String StateName, int amount) {
        HashMap<String, Integer> EntityState = getSLivingEntityState().get(entity);
        if (EntityState != null) {
            Integer tempInteger;
            if ((tempInteger = EntityState.get(StateName)) != null) {
                tempInteger = tempInteger + amount;
            } else {
                tempInteger = amount;
            }
            EntityState.put(StateName, tempInteger);
        }
    }

    public static void incrementEntityStateByAmountXNoPacketIfDoesNotContainState(LivingEntity entity, String StateName, int amount) {
        HashMap<String, Integer> EntityState = getSLivingEntityState().get(entity);
        Integer tempInteger;
        if (EntityState != null && (tempInteger = EntityState.get(StateName)) != null) {
            EntityState.put(StateName, tempInteger);
        }
    }

    public static int getEntityStateAmount(LivingEntity entity, String StateName) {
        HashMap<String, Integer> EntityState = getSLivingEntityState().get(entity);
        Integer tempInteger;
        if (EntityState != null && (tempInteger = EntityState.get(StateName)) != null) {
            return tempInteger;
        }
        else {
            return 0;
        }
    }

    public static void setEntityStateAmount(LivingEntity entity, String StateName, int amount) {
        HashMap<String, Integer> EntityState = getSLivingEntityState().get(entity);
        if (EntityState == null) {
            EntityState = new HashMap<>();
            getSLivingEntityState().put(entity, EntityState);
        }
        EntityState.put(StateName, amount);
        if (entity instanceof ServerPlayer sp1) {
            //client side
            int StateNameHash = StateName.hashCode();
            int ActionStateNameHash = Constants.IS_IN_ACTION.hashCode();
            if (StateNameHash != ActionStateNameHash || amount == Constants.ACTION_OFF) {
                NetworkingFetchMsgMethods.FetchPlayerStateToClientSide(sp1, StateName, amount);
            }
        }
    }

    public static void setEntityStateAmountNoPacket(LivingEntity entity, String StateName, int amount) {
        HashMap<String, Integer> EntityState = getSLivingEntityState().get(entity);
        if (EntityState == null) {
            EntityState = new HashMap<>();
            getSLivingEntityState().put(entity, EntityState);
        }
        EntityState.put(StateName, amount);
    }
}

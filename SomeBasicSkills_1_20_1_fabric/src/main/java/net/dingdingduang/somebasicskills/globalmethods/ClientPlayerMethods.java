package net.dingdingduang.somebasicskills.globalmethods;

import net.dingdingduang.somebasicskills.Constants;

import net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues;
import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerConfig2Settings;
import static net.dingdingduang.somebasicskills.sbsattributes.statusquery.AttributeClientPlayerStatusQueryMethods.*;

public class ClientPlayerMethods {
    public static void giveClientPlayerExpPoints(PlayerEntity a, int amount) {
        a.addExperience(amount);
    }

    public static int getClientPlayerTotalExpPoints(PlayerEntity a) {
        return a.totalExperience;
    }

    public static int getClientPlayerXpLevel(PlayerEntity a) {
        return a.experienceLevel;
    }

    public static void setClientPlayerInAction(int isInAction, String SkillID) {
        GlobalClientPlayerValues.getCPlayerState().put(Constants.IS_IN_ACTION, isInAction);
        GlobalClientPlayerValues.setCPlayerCurrentActiveSkillID(SkillID);
    }

    public static void resetClientPlayerCurrentActiveSkillID() {
        GlobalClientPlayerValues.setCPlayerCurrentActiveSkillID(Constants.NOTHING);
    }

    public static int getClientPlayerStateAmount(String StateName) {
        HashMap<String, Integer> EntityState = GlobalClientPlayerValues.getCPlayerState();
        if (EntityState.containsKey(StateName)) {
            return EntityState.get(StateName);
        }
        else {
            return 0;
        }
    }

    public static double getCPlayerSkillDamage() {
        return (1.0 + getCPlayerSkillDmgBase() + getCPlayerTemporarySkillDmgBase()) * (1.0 + getCPlayerSkillDmgMultiplier() + getCPlayerTemporarySkillDmgMultiplier());
    }

    public static int getCPlayerConfigVal(String ConfigName, String ConfigOptionName) {
        HashMap<String, HashMap<String, MethodConfigHelper>> CPSkillConfigCollectionsMap = getCPlayerConfig2Settings();
        HashMap<String, MethodConfigHelper> CPSkillConfigSubMap;
        MethodConfigHelper CurrentConfig;
        if (CPSkillConfigCollectionsMap != null &&
           (CPSkillConfigSubMap = CPSkillConfigCollectionsMap.get(ConfigName)) != null &&
           (CurrentConfig = CPSkillConfigSubMap.get(ConfigOptionName)) != null) {
            return CurrentConfig.getIntValue();
        }

        return 0;
    }
}

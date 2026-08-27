package net.dingdingduang.somebasicskills.globalmethods;

import net.dingdingduang.somebasicskills.Constants;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerState;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getClientPlayerSkillID2lvlMap;

public class ClientSkillMethods {
    public static boolean isClientPlayerEntityChannellingInterrupted() {
        HashMap<String, Integer> ClientPlayerState = getCPlayerState();
        if (ClientPlayerState.containsKey(Constants.CHANNELING_INTERRUPTED)) {
            return ClientPlayerState.get(Constants.CHANNELING_INTERRUPTED) == 1;
        }
        return false;
    }

    public static int queryCPlayerCurrentSkillLevel(String SkillID) {
        Integer otherSkillLevel = getClientPlayerSkillID2lvlMap().get(SkillID);
        if (otherSkillLevel == null) { return 0; }
        return otherSkillLevel;
    }
}

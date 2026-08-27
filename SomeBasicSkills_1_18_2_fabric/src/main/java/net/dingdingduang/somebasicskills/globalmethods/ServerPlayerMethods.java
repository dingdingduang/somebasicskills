package net.dingdingduang.somebasicskills.globalmethods;

import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerConfig;

public class ServerPlayerMethods {
    public static int getSPlayerConfigVal(ServerPlayerEntity a, String ConfigName, String ConfigOptionName) {
        HashMap<ServerPlayerEntity, HashMap<String, HashMap<String, MethodConfigHelper>>> AllSPConfigMap = getSPlayerConfig();
        HashMap<String, HashMap<String, MethodConfigHelper>> SPSkillConfigCollectionsMap;
        HashMap<String, MethodConfigHelper> SPSkillConfigSubMap;
        MethodConfigHelper CurrentConfig;
        if (AllSPConfigMap != null &&
                (SPSkillConfigCollectionsMap = AllSPConfigMap.get(a)) != null &&
                (SPSkillConfigSubMap = SPSkillConfigCollectionsMap.get(ConfigName)) != null &&
                (CurrentConfig = SPSkillConfigSubMap.get(ConfigOptionName)) != null) {
            return CurrentConfig.getIntValue();
        }

        return 0;
    }

    public static void giveServerPlayerExpPoints(ServerPlayerEntity a, int amount) {
        a.addExperience(amount);

    }

    public static int getServerPlayerTotalExpPoints(ServerPlayerEntity a) {
        return a.totalExperience;
    }

    public static int getServerPlayerXpLevel(ServerPlayerEntity a) {
        return a.experienceLevel;
    }

//    public static void updatePlayerStatus(ServerPlayerEntity sp1) {
//        MinecraftServer mcServer = getMinecraftServerInstance();
//        if (mcServer == null) { return; }
//
//        StatusPlayerNameFileWriteTo(mcServer, sp1);
//        NetworkingFetchMsgMethods.FetchPlayerStatusMapToClientSide(sp1);
//    }

//    public static boolean isPlayerOnline(ServerPlayerEntity sp1) {
//        return getMinecraftServerInstance().getPlayerList().getPlayer(sp1.getUUID()) != null;
//    }
//
//    public static boolean isPlayerOnline(PlayerEntity sp1) {
//        return isPlayerOnline(ConvertClientPlayerToServerPlayer(sp1));
//    }

//    public static double getSPlayerSkillDamage(ServerPlayerEntity sp1) {
//        return (1.0 + getSPlayerSkillDmgBase(sp1) + getSPlayerTemporarySkillDmgBase(sp1)) * (1.0 + getSPlayerSkillDmgMultiplier(sp1) + getSPlayerTemporarySkillDmgMultiplier(sp1));
//    }
}

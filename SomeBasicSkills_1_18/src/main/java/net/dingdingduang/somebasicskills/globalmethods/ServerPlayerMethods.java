package net.dingdingduang.somebasicskills.globalmethods;

import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.UUID;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftServerInstance;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerConfig;

public class ServerPlayerMethods {
    public static int getSPlayerConfigVal(ServerPlayer a, String ConfigName, String ConfigOptionName) {
        HashMap<ServerPlayer, HashMap<String, HashMap<String, MethodConfigHelper>>> AllSPConfigMap = getSPlayerConfig();
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

    public static ServerPlayer ConvertClientPlayerToServerPlayer(Player a) {
        if (a instanceof ServerPlayer) { return (ServerPlayer) a; }
        return getMinecraftServerInstance().getPlayerList().getPlayer(a.getUUID());
    }

    public static ServerPlayer getServerPlayerByUUID(UUID spUUID) {
        return getMinecraftServerInstance().getPlayerList().getPlayer(spUUID);
    }

    public static void giveServerPlayerExpPoints(ServerPlayer a, int amount) {
        a.giveExperiencePoints(amount);
    }

    public static void giveServerPlayerExpPoints(Player a, int amount) {
        (ConvertClientPlayerToServerPlayer(a)).giveExperiencePoints(amount);
    }

    public static int getServerPlayerTotalExpPoints(ServerPlayer a) {
        return a.totalExperience;
    }

    public static int getServerPlayerXpLevel(ServerPlayer a) {
        return a.experienceLevel;
    }

    public static int getServerPlayerTotalExpPoints(Player a) {
        return getServerPlayerTotalExpPoints(ConvertClientPlayerToServerPlayer(a));
    }

    public static int getServerPlayerXpLevel(Player a) {
        return getServerPlayerXpLevel(ConvertClientPlayerToServerPlayer(a));
    }

//    public static void updatePlayerStatus(ServerPlayer sp1) {
//        MinecraftServer mcServer = getMinecraftServerInstance();
//        if (mcServer == null) { return; }
//
//        StatusPlayerNameFileWriteTo(mcServer, sp1);
//        NetworkingFetchMsgMethods.FetchPlayerStatusMapToClientSide(sp1);
//    }

//    public static boolean isPlayerOnline(ServerPlayer sp1) {
//        return getMinecraftServerInstance().getPlayerList().getPlayer(sp1.getUUID()) != null;
//    }
//
//    public static boolean isPlayerOnline(Player sp1) {
//        return isPlayerOnline(ConvertClientPlayerToServerPlayer(sp1));
//    }

//    public static double getSPlayerSkillDamage(ServerPlayer sp1) {
//        return (1.0 + getSPlayerSkillDmgBase(sp1) + getSPlayerTemporarySkillDmgBase(sp1)) * (1.0 + getSPlayerSkillDmgMultiplier(sp1) + getSPlayerTemporarySkillDmgMultiplier(sp1));
//    }
}

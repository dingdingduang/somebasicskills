package net.dingdingduang.somebasicskills.event;

import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.world.entity.player.Player;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.*;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.*;

public class RemovePlayerDataWhenLogout {
    public static void RemoveServerPlayerData(ServerPlayer sp1) {
        //keyboard
//        getSPlayerAssigningQuickslot().remove(sp1);
//        getSPlayerKey2SkillID().remove(sp1);
//        getSPlayerSkillID2Key().remove(sp1);
//
//        getSPlayerAssigningKeyCombo().remove(sp1);
//        getSPlayerKeyCombo2SkillID().remove(sp1);
//        getSPlayerSkillID2KeyCombo().remove(sp1);

        //player state
        getSLivingEntityState().remove(sp1);
        //config
        getSPlayerConfig().remove(sp1);

        //still boost when player logout, need to be kept
//        getSPlayerValue2BaseMultiplierMap().remove(sp1);
    }

    public static void RemoveClientPlayerData() {
//        setClientPlayerSkillID2lvlMap(null);
//        setCPlayerBaseMultiplierStatusMap(null);
//        setCPlayerState(null);
        getClientPlayerSkillID2lvlMap().clear();
        getCPlayerBaseMultiplierStatusMap().clear();;
        getCPlayerState().clear();;
        //keyboard
        setCPlayerAssigningQuickslot(false);
//        setCPlayerKey2SkillID(null);
        clearCPlayerKey2SkillID();
//        setCPlayerSkillID2Key(null);
        clearCPlayerSkillID2Key();

        setCPlayerAssigningKeyCombo(false);
//        setCPlayerKeyCombo2SkillID(null);
        clearCPlayerKeyCombo2SkillID();
//        setCPlayerSkillID2KeyCombo(null);
        clearCPlayerSkillID2KeyCombo();

//        setCPlayerConfig2Settings(null);
        getCPlayerConfig2Settings().clear();

//        getCPlayerConfig2Settings().clear();
        getCPlayerSkillID2Priority().clear();
    }
}

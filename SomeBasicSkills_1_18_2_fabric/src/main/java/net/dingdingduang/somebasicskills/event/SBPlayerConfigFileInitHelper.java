package net.dingdingduang.somebasicskills.event;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree.CommonSkillHelperMethods;
import net.dingdingduang.somebasicskills.networking.NetworkingFetchMsgMethods;
import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerConfig;

public class SBPlayerConfigFileInitHelper {
    private ServerPlayerEntity configSP1;

    public SBPlayerConfigFileInitHelper(ServerPlayerEntity sp1) {
        this.configSP1 = sp1;
    }

    public void SBClientConfigInitMixinHelper() {

    }

    public void SBServerConfigInitMixinHelper() {

    }

    public void SBServerConfigAfterInitMixinHelper() {
        HashMap<String, HashMap<String, MethodConfigHelper>> tempConfigMap = getSPlayerConfig().get(this.configSP1);

        SendServerConfigToClientSide(tempConfigMap, this.configSP1, Constants.SB_GENERAL_SETTING, Constants.CONDITION_OPTION_INDEX);
        SendServerConfigToClientSide(tempConfigMap, this.configSP1, Constants.SB_GENERAL_SETTING, Constants.PLAY_SKILL_ERR_SOUND);
        SendServerConfigToClientSide(tempConfigMap, this.configSP1, Constants.SB_GENERAL_SETTING, Constants.SB_GENERAL_SETTING_LOCK_ON);
        SendServerConfigToClientSide(tempConfigMap, this.configSP1, Constants.SB_GENERAL_SETTING, Constants.SB_GENERAL_SETTING_LOCK_ON_RANGE);

        SBServerConfigAfterInitMixinInjectHelper();
    }

    public void SBServerConfigAfterInitMixinInjectHelper() {

    }

    public void SBPlayerUnstuckRequestClientMixinHelper() {

    }

    public void SBPlayerUnstuckRequestServerMixinHelper() {

    }

    public void SBPlayerUnstuckRequestOnClient() {

        SBPlayerUnstuckRequestClientMixinHelper();
    }

    public void SBPlayerUnstuckRequestOnServer() {
        HashMap<LivingEntity, HashMap<String, Integer>> ServerLivingEntityState = getSLivingEntityState();
        HashMap<String, Integer> LivingEntityState;
        //guard
        if (ServerLivingEntityState != null && ServerLivingEntityState.containsKey(configSP1) && (LivingEntityState = ServerLivingEntityState.get(configSP1)) != null) {
            LivingEntityState.put(Constants.IS_IN_ACTION, Constants.ACTION_OFF);
            LivingEntityState.put(Constants.IS_CHANNELING, Constants.ACTION_OFF);
            LivingEntityState.put(Constants.IS_BACKSTEPPING, Constants.ACTION_OFF);
            NetworkingFetchMsgMethods.FetchPlayerStateToClientSide(configSP1, Constants.IS_IN_ACTION, Constants.ACTION_OFF);
            NetworkingFetchMsgMethods.FetchPlayerStateToClientSide(configSP1, Constants.IS_CHANNELING, Constants.ACTION_OFF);
            NetworkingFetchMsgMethods.FetchPlayerStateToClientSide(configSP1, Constants.IS_BACKSTEPPING, Constants.ACTION_OFF);
            NetworkingFetchMsgMethods.FetchPlayerIsImmobilizedBooleanFromServer(configSP1, false);

            SBPlayerUnstuckRequestServerMixinHelper();
        }
    }


    public float setLockOnFacingAngle() {
        if (CommonSkillHelperMethods.helperGetClientPlayer() == null) {
            return 0;
        }
        else {
            return CommonSkillHelperMethods.helperGetEntityYRot(CommonSkillHelperMethods.helperGetClientPlayer());
        }
    }

    public static void SendServerConfigToClientSide(HashMap<String, HashMap<String, MethodConfigHelper>> tempConfigMap, ServerPlayerEntity sp1, String OptionName, String OptionDetailName) {
        int intVal = 0;
        if (tempConfigMap.containsKey(OptionName) && tempConfigMap.get(OptionName).containsKey(OptionDetailName)) {
            intVal = tempConfigMap.get(OptionName).get(OptionDetailName).getIntValue();
        }
        NetworkingFetchMsgMethods.FetchConfigKeyValToClientSide(sp1, OptionName, OptionDetailName, intVal);
    }
}

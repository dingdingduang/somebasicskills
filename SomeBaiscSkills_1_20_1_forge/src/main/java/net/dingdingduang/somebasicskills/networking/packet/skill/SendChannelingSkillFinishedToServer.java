package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkingFetchMsgMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class SendChannelingSkillFinishedToServer {
    private final String SkillID;
    private final float ClientUserSkillIDDefaultChannelingTime;
    private final int IsChannelingAction;
    private final int IsChannelingInterrupted;
    private final int TimeSpentOnChanneling;

    public SendChannelingSkillFinishedToServer(String skillID, float clientUserSkillIDDefaultChannelingTicks, int isChannelingAction, int isChannelingInterrupted, int timeSpentOnChanneling) {
        this.SkillID = skillID;
        this.ClientUserSkillIDDefaultChannelingTime = clientUserSkillIDDefaultChannelingTicks;
        this.IsChannelingAction = isChannelingAction;
        this.IsChannelingInterrupted = isChannelingInterrupted;
        this.TimeSpentOnChanneling = timeSpentOnChanneling;
    }

    public SendChannelingSkillFinishedToServer(FriendlyByteBuf buf) {
        this.SkillID = buf.readUtf();
        this.ClientUserSkillIDDefaultChannelingTime = buf.readFloat();
        this.IsChannelingAction = buf.readInt();
        this.IsChannelingInterrupted = buf.readInt();
        this.TimeSpentOnChanneling = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.SkillID);
        buf.writeFloat(this.ClientUserSkillIDDefaultChannelingTime);
        buf.writeInt(this.IsChannelingAction);
        buf.writeInt(this.IsChannelingInterrupted);
        buf.writeInt(this.TimeSpentOnChanneling);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on server side
            ServerPlayer sp1 = ctx.get().getSender();

            //if in cd, dont cast
            SkillDataJson skill1 = getID2SkillData().get(this.SkillID);
            int playerSkillLVL = 0;
            if (getGlobalPlayerSkillID2lvlMap().get(sp1).containsKey(SkillID)) {
                playerSkillLVL = getGlobalPlayerSkillID2lvlMap().get(sp1).get(SkillID);
                if (playerSkillLVL <= 0) { return; }
                playerSkillLVL = playerSkillLVL - 1;
            }
            float ServerSkillIDDefaultChannelingTime = skill1.getChannelingTime().get(playerSkillLVL).floatValue();

            if (this.ClientUserSkillIDDefaultChannelingTime >= ServerSkillIDDefaultChannelingTime - 1f) {
                if (skill1.isActiveType() && skill1.getActiveSkillAction1() != null) {
                    skill1.getActiveSkillAction1().executeAction(sp1);
//                    printInGameMsg("channel ticks: "+getSPlayerState().get(sp1));
                }
            }
            else {
                //kick?
                NetworkingFetchMsgMethods.FetchPlayerMsgFromServer(sp1, "Channeling Time #2 doesn't match with the current server!");
            }

            HashMap<LivingEntity, HashMap<String, Integer>> ServerLivingEntityState = getSLivingEntityState();
            if (ServerLivingEntityState.containsKey(sp1) && ServerLivingEntityState.get(sp1) != null) {
                ServerLivingEntityState.get(sp1).put(Constants.IS_CHANNELING, this.IsChannelingAction);
                if (this.IsChannelingInterrupted == 1) {
                    ServerLivingEntityState.get(sp1).put(Constants.CHANNELING_INTERRUPTED, this.IsChannelingInterrupted);
                }
                ServerLivingEntityState.get(sp1).put(Constants.CHANNELING_TICKS, this.TimeSpentOnChanneling);
            }
        });

        return true;
    }
}

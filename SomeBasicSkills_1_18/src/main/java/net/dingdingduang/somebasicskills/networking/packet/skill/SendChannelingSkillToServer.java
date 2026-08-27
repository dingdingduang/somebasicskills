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
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerLastTriggeredChannelingActiveSkillID;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class SendChannelingSkillToServer {
    private final String SkillID;
    private final float ClientUserSkillIDDefaultChannelingTime;

    public SendChannelingSkillToServer(String skillID, float clientUserSkillIDDefaultChannelingTicks) {
        this.SkillID = skillID;
        this.ClientUserSkillIDDefaultChannelingTime = clientUserSkillIDDefaultChannelingTicks;
    }

    public SendChannelingSkillToServer(FriendlyByteBuf buf) {
        this.SkillID = buf.readUtf();
        this.ClientUserSkillIDDefaultChannelingTime = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.SkillID);
        buf.writeFloat(this.ClientUserSkillIDDefaultChannelingTime);
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
                if (skill1.isActiveType() && skill1.getChannelingSkillAction1() != null) {
                    skill1.getChannelingSkillAction1().executeAction(sp1);
                    getSPlayerLastTriggeredChannelingActiveSkillID().put(sp1, this.SkillID);
                }
            }
            else {
                //kick?
                NetworkingFetchMsgMethods.FetchPlayerMsgFromServer(sp1, "Channeling Time #1 doesn't match with the current server!");
            }

            HashMap<LivingEntity, HashMap<String, Integer>> ServerLivingEntityState = getSLivingEntityState();
            if (ServerLivingEntityState.containsKey(sp1) && ServerLivingEntityState.get(sp1) != null) {
                ServerLivingEntityState.get(sp1).put(Constants.IS_CHANNELING, Constants.ACTION_ON);
                ServerLivingEntityState.get(sp1).put(Constants.CHANNELING_INTERRUPTED, Constants.ACTION_OFF);
            }
        });

        return true;
    }
}

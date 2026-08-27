package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.networking.NetworkingFetchMsgMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerLastTriggeredNotChannelingActiveSkillID;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class SendSkillActionWithLockOnToServer {
    private final String SkillID;
    private final float ClientUserSkillIDDefaultSkillCDTime;
    private final float ServerPlayerFacingAngle;

    public SendSkillActionWithLockOnToServer(String skillID, float clientUserSkillIDDefaultSkillCDTime, float serverPlayerFacingAngle) {
        this.SkillID = skillID;
        this.ClientUserSkillIDDefaultSkillCDTime = clientUserSkillIDDefaultSkillCDTime;
        this.ServerPlayerFacingAngle = serverPlayerFacingAngle;
    }

    public SendSkillActionWithLockOnToServer(FriendlyByteBuf buf) {
        this.SkillID = buf.readUtf();
        this.ClientUserSkillIDDefaultSkillCDTime = buf.readFloat();
        this.ServerPlayerFacingAngle = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.SkillID);
        buf.writeFloat(this.ClientUserSkillIDDefaultSkillCDTime);
        buf.writeFloat(this.ServerPlayerFacingAngle);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on server side
            ServerPlayer sp1 = ctx.get().getSender();

            if (sp1 == null) { return; }
            //TODO server timer cooldown if in cd, dont cast
            SkillDataJson skill1 = getID2SkillData().get(this.SkillID);
            int playerSkillLVL = 0;
            if (getGlobalPlayerSkillID2lvlMap().get(sp1).containsKey(SkillID)) {
                playerSkillLVL = getGlobalPlayerSkillID2lvlMap().get(sp1).get(SkillID);
                if (playerSkillLVL <= 0) { return; }
                playerSkillLVL = playerSkillLVL - 1;
            }
            float ServerSkillIDDefaultCDTime = skill1.getCooldownTime().get(playerSkillLVL).floatValue();

            if (this.ClientUserSkillIDDefaultSkillCDTime >= ServerSkillIDDefaultCDTime - 1f) {
                if (skill1.isActiveType() && skill1.getActiveSkillAction1() != null) {
                    sp1.setYRot(this.ServerPlayerFacingAngle);
                    skill1.getActiveSkillAction1().executeAction(sp1);
                    getSPlayerLastTriggeredNotChannelingActiveSkillID().put(sp1, this.SkillID);
                }
            }
            else {
                //kick
                NetworkingFetchMsgMethods.FetchPlayerMsgFromServer(sp1, "Cooldown doesn't match with the current server!");
            }
        });

        return true;
    }
}

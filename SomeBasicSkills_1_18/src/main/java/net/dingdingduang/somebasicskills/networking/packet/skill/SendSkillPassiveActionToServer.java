package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class SendSkillPassiveActionToServer {
    private final String SkillID;

    public SendSkillPassiveActionToServer(String skillID) {
        this.SkillID = skillID;
    }

    public SendSkillPassiveActionToServer(FriendlyByteBuf buf) {
        this.SkillID = buf.readUtf();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.SkillID);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on server side
            ServerPlayer sp1 = ctx.get().getSender();

            //TODO server timer cooldown if in cd, dont cast
            SkillDataJson skill1 = getID2SkillData().get(this.SkillID);

            if ((skill1.isPassiveType() || skill1.isBothType()) && skill1.getPassiveSkillAction1() != null) {
                skill1.getPassiveSkillAction1().executeAction(sp1);
            }
        });

        return true;
    }
}

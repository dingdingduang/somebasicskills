package net.dingdingduang.somebasicskills.networking.packet.screen;

import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalmethods.ServerPlayerMethods.giveServerPlayerExpPoints;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class SendSubBtnActionToServer {
    private final String SkillID;

    public SendSubBtnActionToServer(String skillID) {
        this.SkillID = skillID;
    }

    public SendSubBtnActionToServer(FriendlyByteBuf buf) {
        this.SkillID = buf.readUtf();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.SkillID);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on server side
            ServerPlayer sp1 = ctx.get().getSender();
            if (sp1 == null) { return; }

            SkillDataJson skill1 = getID2SkillData().get(this.SkillID);

            giveServerPlayerExpPoints(sp1, skill1.getXpCostPerLeveling());

            if (skill1.getPressSubBtnAction() != null) {
                //action send back packet if needed inside action method
                skill1.getPressSubBtnAction().executeAction(sp1);
            }
        });

        return true;
    }
}

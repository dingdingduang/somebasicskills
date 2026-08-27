package net.dingdingduang.somebasicskills.networking.packet.screen;

import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalmethods.ServerPlayerMethods.giveServerPlayerExpPoints;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class SendMaxBtnActionToServer {
    private final String SkillID;
    private final int TempLVL;

    public SendMaxBtnActionToServer(String skillID, int lvl) {
        this.SkillID = skillID;
        this.TempLVL = lvl;
    }

    public SendMaxBtnActionToServer(FriendlyByteBuf buf) {
        this.SkillID = buf.readUtf();
        this.TempLVL = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.SkillID);
        buf.writeInt(this.TempLVL);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on server side
            ServerPlayer sp1 = ctx.get().getSender();
            if (sp1 == null) { return; }

            SkillDataJson skill1 = getID2SkillData().get(this.SkillID);

            giveServerPlayerExpPoints(sp1, -skill1.getXpCostPerLeveling()*this.TempLVL);

            if (skill1.getPressAddBtnAction() != null) {
                for (int i = 0; i < this.TempLVL; i++) {
                    skill1.getPressAddBtnAction().executeAction(sp1);
                }
            }
        });

        return true;
    }
}

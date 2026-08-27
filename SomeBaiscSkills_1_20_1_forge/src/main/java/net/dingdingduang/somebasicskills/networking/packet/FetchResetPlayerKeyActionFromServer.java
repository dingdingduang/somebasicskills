package net.dingdingduang.somebasicskills.networking.packet;

import net.dingdingduang.somebasicskills.gui.overlay.SkillChannelingOverlay;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerLastKeyAction;

public class FetchResetPlayerKeyActionFromServer {
    private final String SkillID;

    public FetchResetPlayerKeyActionFromServer(String skillID) {
        this.SkillID = skillID;
    }

    public FetchResetPlayerKeyActionFromServer(FriendlyByteBuf buf) {
        this.SkillID = buf.readUtf();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.SkillID);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on client side
            SkillChannelingOverlay.resetSkillKeyCodeActionFromPlayerSkillID2KeyCode(this.SkillID);
            setCPlayerLastKeyAction(0);
        });

        return true;
    }
}

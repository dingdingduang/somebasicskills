package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.gui.overlay.SkillsInCooldownClientTimerOverlay;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FetchStartSkillCooldownFromServer {
    private final boolean IsTimerOn;
    private final String SkillID;

    public FetchStartSkillCooldownFromServer(boolean isTimerOn, String skillID) {
        this.IsTimerOn = isTimerOn;
        this.SkillID = skillID;
    }

    public FetchStartSkillCooldownFromServer(FriendlyByteBuf buf) {
        this.IsTimerOn = buf.readBoolean();
        this.SkillID = buf.readUtf();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(this.IsTimerOn);
        buf.writeUtf(this.SkillID);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on client side

            SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance().setCooldownTimer(this.IsTimerOn, this.SkillID);
        });

        return true;
    }
}

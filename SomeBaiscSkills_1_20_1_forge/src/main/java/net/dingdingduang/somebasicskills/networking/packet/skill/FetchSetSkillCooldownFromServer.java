package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.gui.overlay.SkillsInCooldownClientTimerOverlay;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FetchSetSkillCooldownFromServer {
    private final boolean IsTimerOn;
    private final String SkillID;
    private final double CooldownAmount;

    public FetchSetSkillCooldownFromServer(boolean isTimerOn, String skillID, double cooldownAmount) {
        this.IsTimerOn = isTimerOn;
        this.SkillID = skillID;
        this.CooldownAmount = cooldownAmount;
    }

    public FetchSetSkillCooldownFromServer(FriendlyByteBuf buf) {
        this.IsTimerOn = buf.readBoolean();
        this.SkillID = buf.readUtf();
        this.CooldownAmount = buf.readDouble();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(this.IsTimerOn);
        buf.writeUtf(this.SkillID);
        buf.writeDouble(this.CooldownAmount);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on client side

            SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance().setCooldownTimer(this.IsTimerOn, this.SkillID, this.CooldownAmount);
        });

        return true;
    }
}

package net.dingdingduang.somebasicskills.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerIsImmobilized;

public class FetchPlayerIsImmobilizedFromServer {
    private final boolean isImmobilized;

    public FetchPlayerIsImmobilizedFromServer(boolean IsImmobilized) {
        this.isImmobilized = IsImmobilized;
    }

    public FetchPlayerIsImmobilizedFromServer(FriendlyByteBuf buf) {
        this.isImmobilized = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(this.isImmobilized);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on client side
            setCPlayerIsImmobilized(this.isImmobilized);
        });

        return true;
    }
}

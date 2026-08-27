package net.dingdingduang.somebasicskills.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerBaseMultiplierStatusMap;

public class FetchStatusKeyValueOnlyFromServer {
    private final String StateName;
    private final double AnyDouble;

    public FetchStatusKeyValueOnlyFromServer(String stateName, double anyInteger) {
        this.StateName = stateName;
        this.AnyDouble = anyInteger;
    }

    public FetchStatusKeyValueOnlyFromServer(FriendlyByteBuf buf) {
        this.StateName = buf.readUtf();
        this.AnyDouble = buf.readDouble();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.StateName);
        buf.writeDouble(this.AnyDouble);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on client side
            getCPlayerBaseMultiplierStatusMap().put(this.StateName, this.AnyDouble);
        });

        return true;
    }
}

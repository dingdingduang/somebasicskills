package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerState;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;

public class SendPlayerStateKeyIncrementValueOnlyToServer {
    private final String StateName;
    private final int AnyInteger;

    public SendPlayerStateKeyIncrementValueOnlyToServer(String stateName, int anyInteger) {
        this.StateName = stateName;
        this.AnyInteger = anyInteger;
    }

    public SendPlayerStateKeyIncrementValueOnlyToServer(FriendlyByteBuf buf) {
        this.StateName = buf.readUtf();
        this.AnyInteger = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.StateName);
        buf.writeInt(this.AnyInteger);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sp1 = ctx.get().getSender();
            //do shiet on server side
            HashMap<String, Integer> tempPlayerState = getSLivingEntityState().get(sp1);
            int incrementInteger;
            if (tempPlayerState.containsKey(this.StateName)) {
                incrementInteger = tempPlayerState.get(this.StateName) + this.AnyInteger;
            }
            else {
                incrementInteger = this.AnyInteger;
            }
            tempPlayerState.put(this.StateName, incrementInteger);
        });

        return true;
    }
}

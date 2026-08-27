package net.dingdingduang.somebasicskills.networking.packet;

import com.google.common.collect.Maps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerBaseMultiplierStatusMap;

public class FetchStatusFromServer {
    private HashMap<String, Double> PlayerStatus;

    public FetchStatusFromServer(HashMap<String, Double> playerStatus) {
        this.PlayerStatus = playerStatus;
    }

    public FetchStatusFromServer(FriendlyByteBuf buf) {
        this.PlayerStatus = buf.readMap(Maps::newHashMapWithExpectedSize, FriendlyByteBuf::readUtf, FriendlyByteBuf::readDouble);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeMap(this.PlayerStatus, FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeDouble);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on client side
            setCPlayerBaseMultiplierStatusMap(this.PlayerStatus);
        });

        return true;
    }
}

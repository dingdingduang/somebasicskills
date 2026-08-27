package net.dingdingduang.somebasicskills.networking.packet.skill;

import com.google.common.collect.Maps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerState;

public class FetchPlayerStateMapFromServer {
    private HashMap<String, Integer> PlayerState;

    public FetchPlayerStateMapFromServer(HashMap<String, Integer> playerStatus) {
        this.PlayerState = playerStatus;
    }

    public FetchPlayerStateMapFromServer(FriendlyByteBuf buf) {
        this.PlayerState = buf.readMap(Maps::newHashMapWithExpectedSize, FriendlyByteBuf::readUtf, FriendlyByteBuf::readInt);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeMap(this.PlayerState, FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeInt);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on client side

            setCPlayerState(this.PlayerState);
        });

        return true;
    }
}

package net.dingdingduang.somebasicskills.networking.packet.skill;

import com.google.common.collect.Maps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;

public class SendPlayerStateMapToServer {
    private HashMap<String, Integer> PlayerState;

    public SendPlayerStateMapToServer(HashMap<String, Integer> playerStatus) {
        this.PlayerState = playerStatus;
    }

    public SendPlayerStateMapToServer(FriendlyByteBuf buf) {
        this.PlayerState = buf.readMap(Maps::newHashMapWithExpectedSize, FriendlyByteBuf::readUtf, FriendlyByteBuf::readInt);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeMap(this.PlayerState, FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeInt);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on server side
            ServerPlayer sp1 = ctx.get().getSender();

            getSLivingEntityState().put(sp1, this.PlayerState);
        });

        return true;
    }
}

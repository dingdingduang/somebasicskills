package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;

public class SendPlayerStateKeyValueOnlyToServer {
    private final String StateName;
    private final int AnyInteger;

    public SendPlayerStateKeyValueOnlyToServer(String stateName, int anyInteger) {
        this.StateName = stateName;
        this.AnyInteger = anyInteger;
    }

    public SendPlayerStateKeyValueOnlyToServer(FriendlyByteBuf buf) {
        this.StateName = buf.readUtf();
        this.AnyInteger = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.StateName);
        buf.writeInt(this.AnyInteger);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on server side
            ServerPlayer sp1 = ctx.get().getSender();

            HashMap<LivingEntity, HashMap<String, Integer>> ServerLivingEntityState = getSLivingEntityState();
            if (ServerLivingEntityState.containsKey(sp1) && ServerLivingEntityState.get(sp1) != null) {
                ServerLivingEntityState.get(sp1).put(this.StateName, this.AnyInteger);
            }
        });

        return true;
    }
}

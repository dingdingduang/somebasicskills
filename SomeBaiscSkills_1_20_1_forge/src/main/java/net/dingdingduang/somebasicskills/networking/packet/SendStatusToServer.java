package net.dingdingduang.somebasicskills.networking.packet;

import com.google.common.collect.Maps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods.StatusPlayerNameFileWriteTo;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftServerInstance;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerValue2BaseMultiplierMap;

public class SendStatusToServer {
    private HashMap<String, Double> PlayerStatus;

    public SendStatusToServer(HashMap<String, Double> playerStatus) {
        this.PlayerStatus = playerStatus;
    }

    public SendStatusToServer(FriendlyByteBuf buf) {
        this.PlayerStatus = buf.readMap(Maps::newHashMapWithExpectedSize, FriendlyByteBuf::readUtf, FriendlyByteBuf::readDouble);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeMap(this.PlayerStatus, FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeDouble);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on server side
            ServerPlayer sp1 = ctx.get().getSender();

            MinecraftServer mcServer = getMinecraftServerInstance();
            if (mcServer == null) { return; }

            getSPlayerValue2BaseMultiplierMap().put(sp1, this.PlayerStatus);
            StatusPlayerNameFileWriteTo(mcServer, sp1);

            //send server data back to client?
        });

        return true;
    }
}

package net.dingdingduang.somebasicskills.networking.packet;

import net.dingdingduang.somebasicskills.event.SBPlayerConfigFileInitHelper;
import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftServerInstance;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSConfig;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerConfig;

public class SendResetPlayerStateRequestToServer {
    public SendResetPlayerStateRequestToServer() {

    }

    public SendResetPlayerStateRequestToServer(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on server side
            ServerPlayer sp1 = ctx.get().getSender();

            if (sp1 == null) { return; }

            SBPlayerConfigFileInitHelper tempConfigStateHelper = new SBPlayerConfigFileInitHelper(sp1);
            tempConfigStateHelper.SBPlayerUnstuckRequestOnServer();
        });

        return true;
    }
}

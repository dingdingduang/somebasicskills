package net.dingdingduang.somebasicskills.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.printInGameMsg;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerState;

public class FetchStringMsgFromServer {
    private final String Message;

    public FetchStringMsgFromServer(String message) {
        this.Message = message;
    }

    public FetchStringMsgFromServer(FriendlyByteBuf buf) {
        this.Message = buf.readUtf();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.Message);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on client side

            printInGameMsg(this.Message);
        });

        return true;
    }
}

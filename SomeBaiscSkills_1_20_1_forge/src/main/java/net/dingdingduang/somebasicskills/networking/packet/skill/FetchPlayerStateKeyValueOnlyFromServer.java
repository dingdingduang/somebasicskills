package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.printInGameMsg;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerState;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerCurrentActiveSkillID;

public class FetchPlayerStateKeyValueOnlyFromServer {
    private final String StateName;
    private final int AnyInteger;

    public FetchPlayerStateKeyValueOnlyFromServer(String stateName, int anyInteger) {
        this.StateName = stateName;
        this.AnyInteger = anyInteger;
    }

    public FetchPlayerStateKeyValueOnlyFromServer(FriendlyByteBuf buf) {
        this.StateName = buf.readUtf();
        this.AnyInteger = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.StateName);
        buf.writeInt(this.AnyInteger);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on client side

            getCPlayerState().put(this.StateName, this.AnyInteger);
            if (this.StateName.hashCode() == Constants.IS_IN_ACTION.hashCode() && this.AnyInteger == 0) {
                setCPlayerCurrentActiveSkillID(Constants.NOTHING);
            }
        });

        return true;
    }
}

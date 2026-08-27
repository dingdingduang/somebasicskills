package net.dingdingduang.somebasicskills.networking.packet;

import net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree.CommonSkillHelperMethods;
import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerConfig2Settings;

public class FetchConfigKeyValOnlyFromServer {
    private final String ConfigOptionName;
    private final String ConfigDetailName;
    private final int ConfigOptionVal;

    public FetchConfigKeyValOnlyFromServer(String configOptionName, String configDetailName, int configOptionVal) {
        this.ConfigOptionName = configOptionName;
        this.ConfigDetailName = configDetailName;
        this.ConfigOptionVal = configOptionVal;
    }

    public FetchConfigKeyValOnlyFromServer(FriendlyByteBuf buf) {
        this.ConfigOptionName = buf.readUtf();
        this.ConfigDetailName = buf.readUtf();
        this.ConfigOptionVal = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.ConfigOptionName);
        buf.writeUtf(this.ConfigDetailName);
        buf.writeInt(this.ConfigOptionVal);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on client side
            if (CommonSkillHelperMethods.helperGetClientPlayer() == null) { return; }

            HashMap<String, MethodConfigHelper> configDetailOptions = getCPlayerConfig2Settings().get(this.ConfigOptionName);
            if (configDetailOptions != null && configDetailOptions.containsKey(this.ConfigDetailName)) {
                configDetailOptions.get(this.ConfigDetailName).setIntValue(this.ConfigOptionVal);
            }

            //send server data back to client?
        });

        return true;
    }
}

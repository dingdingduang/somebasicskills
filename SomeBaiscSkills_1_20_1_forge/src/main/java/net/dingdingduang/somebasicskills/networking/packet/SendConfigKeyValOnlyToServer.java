package net.dingdingduang.somebasicskills.networking.packet;

import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftServerInstance;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.*;

public class SendConfigKeyValOnlyToServer {
    private final String ConfigOptionName;
    private final String ConfigDetailOptionName;
    private final int ConfigOptionVal;
    private final boolean IsConfigDetailOptionBoolean;

    public SendConfigKeyValOnlyToServer(String configOptionName, String configDetailOptionName, int configOptionVal, boolean isConfigDetailOptionBoolean) {
        this.ConfigOptionName = configOptionName;
        this.ConfigDetailOptionName = configDetailOptionName;
        this.ConfigOptionVal = configOptionVal;
        this.IsConfigDetailOptionBoolean = isConfigDetailOptionBoolean;
    }

    public SendConfigKeyValOnlyToServer(FriendlyByteBuf buf) {
        this.ConfigOptionName = buf.readUtf();
        this.ConfigDetailOptionName = buf.readUtf();
        this.ConfigOptionVal = buf.readInt();
        this.IsConfigDetailOptionBoolean = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.ConfigOptionName);
        buf.writeUtf(this.ConfigDetailOptionName);
        buf.writeInt(this.ConfigOptionVal);
        buf.writeBoolean(this.IsConfigDetailOptionBoolean);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on server side
            ServerPlayer sp1 = ctx.get().getSender();

            MinecraftServer mcServer = getMinecraftServerInstance();
            if (mcServer == null) { return; }

            if (getSConfig().contains(this.ConfigOptionName) && getSConfig().contains(this.ConfigDetailOptionName)) {
                HashMap<String, HashMap<String, MethodConfigHelper>> tempConfigHelperMap = getSPlayerConfig().get(sp1);
                HashMap<String, MethodConfigHelper> tempConfigDetailMap = tempConfigHelperMap.get(this.ConfigOptionName);
                if (tempConfigDetailMap == null) {
                    tempConfigDetailMap = new HashMap<>();
                    tempConfigHelperMap.put(this.ConfigOptionName, tempConfigDetailMap);
                }
                MethodConfigHelper methodConfigHelper = tempConfigDetailMap.get(this.ConfigDetailOptionName);
                if (methodConfigHelper == null) {
                    methodConfigHelper = new MethodConfigHelper(null, 0, this.IsConfigDetailOptionBoolean);
                    tempConfigDetailMap.put(this.ConfigDetailOptionName, methodConfigHelper);
                }
                methodConfigHelper.setIntValue(this.ConfigOptionVal);

                FileReadWriteMethods.ConfigPlayernameFileWriteTo(mcServer, sp1);
            }

            //send server data back to client?
        });

        return true;
    }
}

package net.dingdingduang.somebasicskills.networking.packet;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.*;

public record SendConfigKeyValOnlyToServer(String ConfigOptionName, String ConfigDetailOptionName, int ConfigOptionVal, boolean IsConfigDetailOptionBoolean) implements CustomPacketPayload {
    public static final ResourceLocation SEND_CONFIG_KEY_VAL_ONLY_TO_SERVER = getMCResourceLocation(Constants.MOD_ID, "send_config_key_val_only_to_server");

    public SendConfigKeyValOnlyToServer(FriendlyByteBuf buf) {
        this(buf.readUtf(), buf.readUtf(), buf.readInt(), buf.readBoolean());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(ConfigOptionName());
        buf.writeUtf(ConfigDetailOptionName());
        buf.writeInt(ConfigOptionVal());
        buf.writeBoolean(IsConfigDetailOptionBoolean());
    }

    @Override
    public ResourceLocation id() {
        return SEND_CONFIG_KEY_VAL_ONLY_TO_SERVER;
    }

    public static void handle(final SendConfigKeyValOnlyToServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread
                    if (context.player().isPresent()) {
                        ServerPlayer sp1 = (ServerPlayer) context.player().get();

                        if (getSConfig().contains(data.ConfigOptionName()) && getSConfig().contains(data.ConfigDetailOptionName())) {
                            HashMap<String, HashMap<String, MethodConfigHelper>> tempConfigHelperMap = getSPlayerConfig().get(sp1);
                            HashMap<String, MethodConfigHelper> tempConfigDetailMap = tempConfigHelperMap.get(data.ConfigOptionName());
                            if (tempConfigDetailMap == null) {
                                tempConfigDetailMap = new HashMap<>();
                                tempConfigHelperMap.put(data.ConfigOptionName(), tempConfigDetailMap);
                            }
                            MethodConfigHelper methodConfigHelper = tempConfigDetailMap.get(data.ConfigDetailOptionName());
                            if (methodConfigHelper == null) {
                                methodConfigHelper = new MethodConfigHelper(null, 0, data.IsConfigDetailOptionBoolean());
                                tempConfigDetailMap.put(data.ConfigDetailOptionName(), methodConfigHelper);
                            }
                            methodConfigHelper.setIntValue(data.ConfigOptionVal());

                            MinecraftServer mcServer = getMinecraftServerInstance();
                            FileReadWriteMethods.ConfigPlayernameFileWriteTo(mcServer, sp1);
                        }
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

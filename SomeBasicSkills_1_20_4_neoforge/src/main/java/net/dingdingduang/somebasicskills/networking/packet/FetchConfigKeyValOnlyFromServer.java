package net.dingdingduang.somebasicskills.networking.packet;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree.CommonSkillHelperMethods;
import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerConfig2Settings;

public record FetchConfigKeyValOnlyFromServer(String ConfigOptionName, String ConfigDetailName, int ConfigOptionVal) implements CustomPacketPayload {
    public static final ResourceLocation FETCH_CONFIG_KEY_VAL_ONLY_FROM_SERVER = getMCResourceLocation(Constants.MOD_ID, "fetch_config_key_val_only_from_server");

    public FetchConfigKeyValOnlyFromServer(FriendlyByteBuf buf) {
        this(buf.readUtf(), buf.readUtf(), buf.readInt());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(ConfigOptionName());
        buf.writeUtf(ConfigDetailName());
        buf.writeInt(ConfigOptionVal());
    }

    @Override
    public ResourceLocation id() {
        return FETCH_CONFIG_KEY_VAL_ONLY_FROM_SERVER;
    }

    public static void handle(final FetchConfigKeyValOnlyFromServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread

                    //do shiet on client side
                    if (CommonSkillHelperMethods.helperGetClientPlayer() == null) { return; }

                    HashMap<String, MethodConfigHelper> configDetailOptions = getCPlayerConfig2Settings().get(data.ConfigOptionName());
                    if (configDetailOptions != null && configDetailOptions.containsKey(data.ConfigDetailName())) {
                        configDetailOptions.get(data.ConfigDetailName()).setIntValue(data.ConfigOptionVal());
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

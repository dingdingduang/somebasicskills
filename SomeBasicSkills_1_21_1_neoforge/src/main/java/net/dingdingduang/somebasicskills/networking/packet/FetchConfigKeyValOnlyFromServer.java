package net.dingdingduang.somebasicskills.networking.packet;

import io.netty.buffer.ByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree.CommonSkillHelperMethods;
import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerConfig2Settings;

public record FetchConfigKeyValOnlyFromServer(String ConfigOptionName, String ConfigDetailName, int ConfigOptionVal) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<FetchConfigKeyValOnlyFromServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "fetch_config_key_val_only_from_server") );

    public static final StreamCodec<ByteBuf, FetchConfigKeyValOnlyFromServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            FetchConfigKeyValOnlyFromServer::ConfigOptionName,
            ByteBufCodecs.STRING_UTF8,
            FetchConfigKeyValOnlyFromServer::ConfigDetailName,
            ByteBufCodecs.INT,
            FetchConfigKeyValOnlyFromServer::ConfigOptionVal,
            FetchConfigKeyValOnlyFromServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final FetchConfigKeyValOnlyFromServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
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
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

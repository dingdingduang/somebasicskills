package net.dingdingduang.somebasicskills.networking.packet;

import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree.CommonSkillHelperMethods;
import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.codec.PacketCodecs;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerConfig2Settings;

public record FetchConfigKeyValOnlyFromServer(String ConfigOptionName, String ConfigDetailName, int ConfigOptionVal) implements CustomPayload {
    public static final CustomPayload.Id<FetchConfigKeyValOnlyFromServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "fetch_config_key_val_only_from_server") );

    public static final PacketCodec<RegistryByteBuf, FetchConfigKeyValOnlyFromServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            FetchConfigKeyValOnlyFromServer::ConfigOptionName,
            PacketCodecs.STRING,
            FetchConfigKeyValOnlyFromServer::ConfigDetailName,
            PacketCodecs.INTEGER,
            FetchConfigKeyValOnlyFromServer::ConfigOptionVal,
            FetchConfigKeyValOnlyFromServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final FetchConfigKeyValOnlyFromServer data, final ClientPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.client().execute(() -> {
                    //do on main thread

                    //do shiet on client side
                    if (CommonSkillHelperMethods.helperGetClientPlayer() == null) { return; }

                    HashMap<String, MethodConfigHelper> configDetailOptions = getCPlayerConfig2Settings().get(data.ConfigOptionName());
                    if (configDetailOptions != null && configDetailOptions.containsKey(data.ConfigDetailName())) {
                        configDetailOptions.get(data.ConfigDetailName()).setIntValue(data.ConfigOptionVal());
                    }
                });
    }
}

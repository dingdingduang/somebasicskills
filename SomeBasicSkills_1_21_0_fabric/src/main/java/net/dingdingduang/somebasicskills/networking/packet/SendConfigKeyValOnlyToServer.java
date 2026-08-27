package net.dingdingduang.somebasicskills.networking.packet;

import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.network.codec.PacketCodecs;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.*;

public record SendConfigKeyValOnlyToServer(String ConfigOptionName, String ConfigDetailOptionName, int ConfigOptionVal, boolean IsConfigDetailOptionBoolean) implements CustomPayload {
    public static final CustomPayload.Id<SendConfigKeyValOnlyToServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "send_config_key_val_only_to_server") );

    public static final PacketCodec<RegistryByteBuf, SendConfigKeyValOnlyToServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            SendConfigKeyValOnlyToServer::ConfigOptionName,
            PacketCodecs.STRING,
            SendConfigKeyValOnlyToServer::ConfigDetailOptionName,
            PacketCodecs.INTEGER,
            SendConfigKeyValOnlyToServer::ConfigOptionVal,
            PacketCodecs.BOOL,
            SendConfigKeyValOnlyToServer::IsConfigDetailOptionBoolean,
            SendConfigKeyValOnlyToServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final SendConfigKeyValOnlyToServer data, final ServerPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.server().execute(() -> {
                    //do on main thread
                    ServerPlayerEntity sp1 = context.player();

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

                        MinecraftServer mcServer = getMinecraftServerInstance(sp1);
                        FileReadWriteMethods.ConfigPlayernameFileWriteTo(mcServer, sp1);
                    }
                });
    }
}

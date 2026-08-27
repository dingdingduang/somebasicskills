package net.dingdingduang.somebasicskills.networking.packet;

import io.netty.buffer.ByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.*;

public record SendConfigKeyValOnlyToServer(String ConfigOptionName, String ConfigDetailOptionName, int ConfigOptionVal, boolean IsConfigDetailOptionBoolean) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SendConfigKeyValOnlyToServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "send_config_key_val_only_to_server") );

    public static final StreamCodec<ByteBuf, SendConfigKeyValOnlyToServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            SendConfigKeyValOnlyToServer::ConfigOptionName,
            ByteBufCodecs.STRING_UTF8,
            SendConfigKeyValOnlyToServer::ConfigDetailOptionName,
            ByteBufCodecs.INT,
            SendConfigKeyValOnlyToServer::ConfigOptionVal,
            ByteBufCodecs.BOOL,
            SendConfigKeyValOnlyToServer::IsConfigDetailOptionBoolean,
            SendConfigKeyValOnlyToServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final SendConfigKeyValOnlyToServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                    //do on main thread
                    ServerPlayer sp1 = (ServerPlayer) context.player();

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
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

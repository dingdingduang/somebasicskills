package net.dingdingduang.somebasicskills.networking.packet;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkFabricGeneralMethods;
import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftServerInstance;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.*;

public class SendConfigKeyValOnlyToServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "send_config_key_val_only_to_server");

    public static PacketByteBuf setupPacket(String configOptionName, String configDetailName, int configOptionVal, boolean isConfigDetailOptionBoolean) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeString(configOptionName);
        packetToBeSent.writeString(configDetailName);
        packetToBeSent.writeInt(configOptionVal);
        packetToBeSent.writeBoolean(isConfigDetailOptionBoolean);
        return packetToBeSent;
    }

    public static void toServerHandle(MinecraftServer server, ServerPlayerEntity serverPlayer, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final String ConfigOptionName = buf.readString();
        final String ConfigDetailOptionName = buf.readString();
        final int ConfigOptionVal = buf.readInt();
        final boolean IsConfigDetailOptionBoolean = buf.readBoolean();

        server.execute( () -> {
            //start action

            MinecraftServer mcServer = getMinecraftServerInstance(serverPlayer);
            if (mcServer == null) { return; }

            if (getSConfig().contains(ConfigOptionName) && getSConfig().contains(ConfigDetailOptionName)) {
                HashMap<String, HashMap<String, MethodConfigHelper>> tempConfigHelperMap = getSPlayerConfig().get(serverPlayer);
                HashMap<String, MethodConfigHelper> tempConfigDetailMap = tempConfigHelperMap.get(ConfigOptionName);
                if (tempConfigDetailMap == null) {
                    tempConfigDetailMap = new HashMap<>();
                    tempConfigHelperMap.put(ConfigOptionName, tempConfigDetailMap);
                }
                MethodConfigHelper methodConfigHelper = tempConfigDetailMap.get(ConfigDetailOptionName);
                if (methodConfigHelper == null) {
                    methodConfigHelper = new MethodConfigHelper(null, 0, IsConfigDetailOptionBoolean);
                    tempConfigDetailMap.put(ConfigDetailOptionName, methodConfigHelper);
                }
                methodConfigHelper.setIntValue(ConfigOptionVal);

                FileReadWriteMethods.ConfigPlayernameFileWriteTo(mcServer, serverPlayer);
            }
        });
    }
}

package net.dingdingduang.somebasicskills.networking.packet;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.anexampleskilltree.commonskilltree.CommonSkillHelperMethods;
import net.dingdingduang.somebasicskills.networking.NetworkFabricGeneralMethods;
import net.dingdingduang.somebasicskills.util.MethodConfigHelper;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerConfig2Settings;

public class FetchConfigKeyValOnlyFromServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "fetch_config_key_val_only_from_server");

    public static PacketByteBuf setupPacket(String configOptionName, String configDetailName, int configOptionVal) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeString(configOptionName);
        packetToBeSent.writeString(configDetailName);
        packetToBeSent.writeInt(configOptionVal);
        return packetToBeSent;
    }

    public static void toClientHandle(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final String ConfigOptionName = buf.readString();
        final String ConfigDetailName = buf.readString();
        final int ConfigOptionVal = buf.readInt();

        client.execute( () -> {
            //start action
            if (CommonSkillHelperMethods.helperGetClientPlayer() == null) { return; }

            HashMap<String, MethodConfigHelper> configDetailOptions = getCPlayerConfig2Settings().get(ConfigOptionName);
            if (configDetailOptions != null && configDetailOptions.containsKey(ConfigDetailName)) {
                configDetailOptions.get(ConfigDetailName).setIntValue(ConfigOptionVal);
            }

            //send server data back to client?
        });
    }
}

package net.dingdingduang.somebasicskills.networking.packet;

import com.google.common.collect.Maps;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkFabricGeneralMethods;
import net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.initializeClientPlayerHashMapsWhenLogin;
import static net.dingdingduang.somebasicskills.sbsattributes.statusquery.AttributeClientPlayerStatusQueryMethods.setupSkillDataClientPlayerInitialization;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setClientPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientValuesInit.globalClientValuesInit;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientValuesInit.isGlobalClientValuesInitialized;

public class FetchLoginDataFromServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "fetch_login_data_from_server");

    public static PacketByteBuf setupPacket(HashMap<String, Integer> skillID2LVL) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeMap(skillID2LVL, PacketByteBuf::writeString, PacketByteBuf::writeInt);
        return packetToBeSent;
    }

    public static void toClientHandle(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final HashMap<String, Integer> SkillID2LVL = buf.readMap(Maps::newHashMapWithExpectedSize, PacketByteBuf::readString, PacketByteBuf::readInt);

        client.execute( () -> {
            //start action
            //do shiet on client side
            if (!isGlobalClientValuesInitialized()) {
                globalClientValuesInit();
            }

            //initialize player hashmap
            initializeClientPlayerHashMapsWhenLogin();

            //initialize player status
            setupSkillDataClientPlayerInitialization();

            setClientPlayerSkillID2lvlMap(SkillID2LVL);
            FileReadWriteMethods.SkillPriorityPlayernameFileReadFrom();

            //read player keyCombo and quickSlot setting
            FileReadWriteMethods.ClientQuickslotPlayernameFileReadFrom();
            FileReadWriteMethods.ClientKeycomboPlayernameFileReadFrom();
        });
    }
}

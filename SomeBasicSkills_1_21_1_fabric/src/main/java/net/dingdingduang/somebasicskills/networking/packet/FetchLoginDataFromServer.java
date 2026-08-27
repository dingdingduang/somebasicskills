package net.dingdingduang.somebasicskills.networking.packet;

import com.google.common.collect.Maps;
import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.codec.PacketCodecs;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.initializeClientPlayerHashMapsWhenLogin;
import static net.dingdingduang.somebasicskills.sbsattributes.statusquery.AttributeClientPlayerStatusQueryMethods.setupSkillDataClientPlayerInitialization;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setClientPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientValuesInit.globalClientValuesInit;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientValuesInit.isGlobalClientValuesInitialized;

public record FetchLoginDataFromServer(HashMap<String, Integer> SkillID2LVL) implements CustomPayload {
    public static final CustomPayload.Id<FetchLoginDataFromServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "fetch_login_data_from_server") );

    public static final PacketCodec<RegistryByteBuf, FetchLoginDataFromServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.map(Maps::newHashMapWithExpectedSize, PacketCodecs.STRING, PacketCodecs.INTEGER),
            FetchLoginDataFromServer::SkillID2LVL,
            FetchLoginDataFromServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final FetchLoginDataFromServer data, final ClientPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.client().execute(() -> {
                    //do on main thread

                    //do shiet on client side
                    if (!isGlobalClientValuesInitialized()) {
                        globalClientValuesInit();
                    }

                    //initialize player hashmap
                    initializeClientPlayerHashMapsWhenLogin();

                    //initialize player status
                    setupSkillDataClientPlayerInitialization();

                    setClientPlayerSkillID2lvlMap(data.SkillID2LVL());
                    FileReadWriteMethods.SkillPriorityPlayernameFileReadFrom();

                    //read player keyCombo and quickSlot setting
                    FileReadWriteMethods.ClientQuickslotPlayernameFileReadFrom();
                    FileReadWriteMethods.ClientKeycomboPlayernameFileReadFrom();

                });
    }
}

package net.dingdingduang.somebasicskills.networking.packet;

import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.initializeClientPlayerHashMapsWhenLogin;
import static net.dingdingduang.somebasicskills.sbsattributes.statusquery.AttributeClientPlayerStatusQueryMethods.setupSkillDataClientPlayerInitialization;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setClientPlayerSkillID2lvlMap;
//import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.initializeClientPlayerHashMapsWhenLogin;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientValuesInit.globalClientValuesInit;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientValuesInit.isGlobalClientValuesInitialized;

public record FetchLoginDataFromServer(HashMap<String, Integer> SkillID2LVL) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<FetchLoginDataFromServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "fetch_login_data_from_server") );

    public static final StreamCodec<ByteBuf, FetchLoginDataFromServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.map(Maps::newHashMapWithExpectedSize, ByteBufCodecs.STRING_UTF8, ByteBufCodecs.INT),
            FetchLoginDataFromServer::SkillID2LVL,
            FetchLoginDataFromServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final FetchLoginDataFromServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
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

                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

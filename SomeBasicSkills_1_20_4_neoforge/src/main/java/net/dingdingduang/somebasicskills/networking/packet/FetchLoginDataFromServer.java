package net.dingdingduang.somebasicskills.networking.packet;

import com.google.common.collect.Maps;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

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
    public static final ResourceLocation FETCH_LOGIN_DATA_FROM_SERVER = getMCResourceLocation(Constants.MOD_ID, "fetch_login_data_from_server");

    public FetchLoginDataFromServer(FriendlyByteBuf buf) {
        this((HashMap<String, Integer>) buf.readMap(Maps::newHashMapWithExpectedSize, FriendlyByteBuf::readUtf, FriendlyByteBuf::readInt));
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeMap(SkillID2LVL(), FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeInt);
    }

    @Override
    public ResourceLocation id() {
        return FETCH_LOGIN_DATA_FROM_SERVER;
    }

    public static void handle(final FetchLoginDataFromServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
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
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

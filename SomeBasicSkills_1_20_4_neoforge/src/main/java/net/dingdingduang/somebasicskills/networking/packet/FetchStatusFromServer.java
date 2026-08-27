package net.dingdingduang.somebasicskills.networking.packet;

import com.google.common.collect.Maps;
import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerBaseMultiplierStatusMap;

public record FetchStatusFromServer(HashMap<String, Double> PlayerStatus) implements CustomPacketPayload {
    public static final ResourceLocation FETCH_STATUS_FROM_SERVER = getMCResourceLocation(Constants.MOD_ID, "fetch_status_from_server");

    public FetchStatusFromServer(FriendlyByteBuf buf) {
        this((HashMap<String, Double>) buf.readMap(Maps::newHashMapWithExpectedSize, FriendlyByteBuf::readUtf, FriendlyByteBuf::readDouble));
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeMap(PlayerStatus(), FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeDouble);
    }

    @Override
    public ResourceLocation id() {
        return FETCH_STATUS_FROM_SERVER;
    }

    public static void handle(final FetchStatusFromServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread

                    setCPlayerBaseMultiplierStatusMap(data.PlayerStatus());
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

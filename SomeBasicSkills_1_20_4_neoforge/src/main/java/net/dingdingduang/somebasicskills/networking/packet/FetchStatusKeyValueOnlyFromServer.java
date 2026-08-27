package net.dingdingduang.somebasicskills.networking.packet;

import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerBaseMultiplierStatusMap;

public record FetchStatusKeyValueOnlyFromServer(String StateName, double AnyDouble) implements CustomPacketPayload {
    public static final ResourceLocation FETCH_STATUS_KEY_VALUE_ONLY_FROM_SERVER = getMCResourceLocation(Constants.MOD_ID, "fetch_status_key_value_only_from_server");

    public FetchStatusKeyValueOnlyFromServer(FriendlyByteBuf buf) {
        this(buf.readUtf(), buf.readDouble());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(StateName());
        buf.writeDouble(AnyDouble());
    }

    @Override
    public ResourceLocation id() {
        return FETCH_STATUS_KEY_VALUE_ONLY_FROM_SERVER;
    }

    public static void handle(final FetchStatusKeyValueOnlyFromServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread
                    getCPlayerBaseMultiplierStatusMap().put(data.StateName(), data.AnyDouble());

                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

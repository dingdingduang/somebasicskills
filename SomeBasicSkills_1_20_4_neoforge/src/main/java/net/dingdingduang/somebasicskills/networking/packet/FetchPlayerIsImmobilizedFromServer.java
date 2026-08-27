package net.dingdingduang.somebasicskills.networking.packet;

import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerIsImmobilized;

public record FetchPlayerIsImmobilizedFromServer(boolean isImmobilized) implements CustomPacketPayload {
    public static final ResourceLocation FETCH_PLAYER_IS_IMMOBILIZED_FROM_SERVER = getMCResourceLocation(Constants.MOD_ID, "fetch_player_is_immobilized_from_server");

    public FetchPlayerIsImmobilizedFromServer(FriendlyByteBuf buf) {
        this(buf.readBoolean());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeBoolean(isImmobilized());
    }

    @Override
    public ResourceLocation id() {
        return FETCH_PLAYER_IS_IMMOBILIZED_FROM_SERVER;
    }

    public static void handle(final FetchPlayerIsImmobilizedFromServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread

                    setCPlayerIsImmobilized(data.isImmobilized());
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

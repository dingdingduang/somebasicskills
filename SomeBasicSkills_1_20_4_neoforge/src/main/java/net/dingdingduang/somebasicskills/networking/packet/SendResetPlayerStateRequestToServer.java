package net.dingdingduang.somebasicskills.networking.packet;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.event.SBPlayerConfigFileInitHelper;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;

public record SendResetPlayerStateRequestToServer() implements CustomPacketPayload {
    public static final ResourceLocation SEND_RESET_PLAYER_STATE_REQUEST_TO_SERVER = getMCResourceLocation(Constants.MOD_ID, "send_reset_player_state_request_to_server");

    public SendResetPlayerStateRequestToServer() {

    }

    public SendResetPlayerStateRequestToServer(FriendlyByteBuf buf) {
        this();
    }

    public void write(FriendlyByteBuf buf) {

    }

    @Override
    public ResourceLocation id() {
        return SEND_RESET_PLAYER_STATE_REQUEST_TO_SERVER;
    }

    public static void handle(final SendResetPlayerStateRequestToServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread
                    if (context.player().isPresent()) {
                        ServerPlayer sp1 = (ServerPlayer) context.player().get();

                        SBPlayerConfigFileInitHelper tempConfigStateHelper = new SBPlayerConfigFileInitHelper(sp1);
                        tempConfigStateHelper.SBPlayerUnstuckRequestOnServer();

                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

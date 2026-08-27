package net.dingdingduang.somebasicskills.networking.packet;

import net.dingdingduang.somebasicskills.networking.NetworkingGeneralMethods;
import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.event.SBPlayerConfigFileInitHelper;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;

public record SendResetPlayerStateRequestToServer() implements CustomPayload {
    public static final CustomPayload.Id<SendResetPlayerStateRequestToServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "send_reset_player_state_request_to_server") );

    public static final PacketCodec<RegistryByteBuf, SendResetPlayerStateRequestToServer> STREAM_CODEC = NetworkingGeneralMethods.tuple(
            SendResetPlayerStateRequestToServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final SendResetPlayerStateRequestToServer data, final ServerPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.server().execute(() -> {
                    //do on main thread
                    ServerPlayerEntity sp1 = context.player();

                    SBPlayerConfigFileInitHelper tempConfigStateHelper = new SBPlayerConfigFileInitHelper(sp1);
                    tempConfigStateHelper.SBPlayerUnstuckRequestOnServer();

                });
    }
}

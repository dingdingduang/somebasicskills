package net.dingdingduang.somebasicskills.networking.packet;

import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.gui.overlay.SkillChannelingOverlay;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.codec.PacketCodecs;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerLastKeyAction;

public record FetchResetPlayerKeyActionFromServer(String SkillID) implements CustomPayload {
    public static final CustomPayload.Id<FetchResetPlayerKeyActionFromServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "fetch_reset_player_key_action_from_server") );

    public static final PacketCodec<RegistryByteBuf, FetchResetPlayerKeyActionFromServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            FetchResetPlayerKeyActionFromServer::SkillID,
            FetchResetPlayerKeyActionFromServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final FetchResetPlayerKeyActionFromServer data, final ClientPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.client().execute(() -> {
                    //do on main thread

                    //do shiet on client side
                    SkillChannelingOverlay.resetSkillKeyCodeActionFromPlayerSkillID2KeyCode(data.SkillID());
                    setCPlayerLastKeyAction(0);

                });
    }
}

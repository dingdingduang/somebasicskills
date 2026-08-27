package net.dingdingduang.somebasicskills.networking.packet;

import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.codec.PacketCodecs;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;

public record FetchStringMsgFromServer(String Message) implements CustomPayload {
    public static final CustomPayload.Id<FetchStringMsgFromServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "fetch_string_msg_from_server") );

    public static final PacketCodec<RegistryByteBuf, FetchStringMsgFromServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            FetchStringMsgFromServer::Message,
            FetchStringMsgFromServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final FetchStringMsgFromServer data, final ClientPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.client().execute(() -> {
                    //do on main thread

                    printInGameMsg(data.Message());
                });
    }
}

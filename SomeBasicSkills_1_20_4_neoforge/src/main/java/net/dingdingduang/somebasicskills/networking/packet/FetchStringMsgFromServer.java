package net.dingdingduang.somebasicskills.networking.packet;

import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;

public record FetchStringMsgFromServer(String Message) implements CustomPacketPayload {
    public static final ResourceLocation FETCH_STRING_MSG_FROM_SERVER = getMCResourceLocation(Constants.MOD_ID, "fetch_string_msg_from_server");

    public FetchStringMsgFromServer(FriendlyByteBuf buf) {
        this(buf.readUtf());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(Message());
    }

    @Override
    public ResourceLocation id() {
        return FETCH_STRING_MSG_FROM_SERVER;
    }

    public static void handle(final FetchStringMsgFromServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread

                    printInGameMsg(data.Message());
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

package net.dingdingduang.somebasicskills.networking.packet.skill;

import io.netty.buffer.ByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerState;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerCurrentActiveSkillID;

public record FetchPlayerStateKeyValueOnlyFromServer(String StateName, int AnyInteger) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<FetchPlayerStateKeyValueOnlyFromServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "fetch_player_state_key_value_only_from_server") );

    public static final StreamCodec<ByteBuf, FetchPlayerStateKeyValueOnlyFromServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            FetchPlayerStateKeyValueOnlyFromServer::StateName,
            ByteBufCodecs.INT,
            FetchPlayerStateKeyValueOnlyFromServer::AnyInteger,
            FetchPlayerStateKeyValueOnlyFromServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final FetchPlayerStateKeyValueOnlyFromServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                    //do on main thread
                    getCPlayerState().put(data.StateName(), data.AnyInteger());
                    if (data.StateName().hashCode() == Constants.IS_IN_ACTION.hashCode() && data.AnyInteger() == 0) {
                        setCPlayerCurrentActiveSkillID(Constants.NOTHING);
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.codec.PacketCodecs;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerState;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerCurrentActiveSkillID;

public record FetchPlayerStateKeyValueOnlyFromServer(String StateName, int AnyInteger) implements CustomPayload {
    public static final CustomPayload.Id<FetchPlayerStateKeyValueOnlyFromServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "fetch_player_state_key_value_only_from_server") );

    public static final PacketCodec<RegistryByteBuf, FetchPlayerStateKeyValueOnlyFromServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            FetchPlayerStateKeyValueOnlyFromServer::StateName,
            PacketCodecs.INTEGER,
            FetchPlayerStateKeyValueOnlyFromServer::AnyInteger,
            FetchPlayerStateKeyValueOnlyFromServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final FetchPlayerStateKeyValueOnlyFromServer data, final ClientPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.client().execute(() -> {
                    //do on main thread
                    getCPlayerState().put(data.StateName(), data.AnyInteger());
                    if (data.StateName().hashCode() == Constants.IS_IN_ACTION.hashCode() && data.AnyInteger() == 0) {
                        setCPlayerCurrentActiveSkillID(Constants.NOTHING);
                    }
                });
    }
}

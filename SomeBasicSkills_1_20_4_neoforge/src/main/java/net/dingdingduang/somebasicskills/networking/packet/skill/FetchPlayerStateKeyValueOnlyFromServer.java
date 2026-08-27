package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerState;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerCurrentActiveSkillID;

public record FetchPlayerStateKeyValueOnlyFromServer(String StateName, int AnyInteger) implements CustomPacketPayload {
    public static final ResourceLocation FETCH_PLAYER_STATE_KEY_VALUE_ONLY_FROM_SERVER = getMCResourceLocation(Constants.MOD_ID, "fetch_player_state_key_value_only_from_server");

    public FetchPlayerStateKeyValueOnlyFromServer(FriendlyByteBuf buf) {
        this(buf.readUtf(), buf.readInt());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(StateName());
        buf.writeInt(AnyInteger());
    }

    @Override
    public ResourceLocation id() {
        return FETCH_PLAYER_STATE_KEY_VALUE_ONLY_FROM_SERVER;
    }

    public static void handle(final FetchPlayerStateKeyValueOnlyFromServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread
                    getCPlayerState().put(data.StateName(), data.AnyInteger());
                    if (data.StateName().hashCode() == Constants.IS_IN_ACTION.hashCode() && data.AnyInteger() == 0) {
                        setCPlayerCurrentActiveSkillID(Constants.NOTHING);
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

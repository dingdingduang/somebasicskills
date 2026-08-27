package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;

public record SendPlayerStateKeyIncrementValueOnlyToServer(String StateName, int AnyInteger) implements CustomPacketPayload {
    public static final ResourceLocation SEND_PLAYER_STATE_KEY_INCREMENT_VALUE_ONLY_TO_SERVER = getMCResourceLocation(Constants.MOD_ID, "send_player_state_key_increment_value_only_to_server");

    public SendPlayerStateKeyIncrementValueOnlyToServer(FriendlyByteBuf buf) {
        this(buf.readUtf(), buf.readInt());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(StateName());
        buf.writeInt(AnyInteger());
    }

    @Override
    public ResourceLocation id() {
        return SEND_PLAYER_STATE_KEY_INCREMENT_VALUE_ONLY_TO_SERVER;
    }

    public static void handle(final SendPlayerStateKeyIncrementValueOnlyToServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread
                    //do shiet on server side
                    if (context.player().isPresent()) {
                        ServerPlayer sp1 = (ServerPlayer) context.player().get();
                        HashMap<String, Integer> tempPlayerState = getSLivingEntityState().get(sp1);
                        int incrementInteger;
                        if (tempPlayerState.containsKey(data.StateName())) {
                            incrementInteger = tempPlayerState.get(data.StateName()) + data.AnyInteger();
                        } else {
                            incrementInteger = data.AnyInteger();
                        }
                        tempPlayerState.put(data.StateName(), incrementInteger);
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

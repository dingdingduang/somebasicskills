package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;

public record SendPlayerStateKeyValueOnlyToServer(String StateName, int AnyInteger) implements CustomPacketPayload {
    public static final ResourceLocation SEND_PLAYER_STATE_KEY_VALUE_ONLY_TO_SERVER = getMCResourceLocation(Constants.MOD_ID, "send_player_state_key_value_only_to_server");

    public SendPlayerStateKeyValueOnlyToServer(FriendlyByteBuf buf) {
        this(buf.readUtf(), buf.readInt());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(StateName());
        buf.writeInt(AnyInteger());
    }

    @Override
    public ResourceLocation id() {
        return SEND_PLAYER_STATE_KEY_VALUE_ONLY_TO_SERVER;
    }

    public static void handle(final SendPlayerStateKeyValueOnlyToServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread
                    if (context.player().isPresent()) {
                        ServerPlayer sp1 = (ServerPlayer) context.player().get();

                        HashMap<LivingEntity, HashMap<String, Integer>> ServerLivingEntityState = getSLivingEntityState();
                        if (ServerLivingEntityState.containsKey(sp1) && ServerLivingEntityState.get(sp1) != null) {
                            ServerLivingEntityState.get(sp1).put(data.StateName(), data.AnyInteger());
                        }
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

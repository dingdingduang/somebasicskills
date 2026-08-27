package net.dingdingduang.somebasicskills.networking.packet.skill;

import com.google.common.collect.Maps;
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

public record SendPlayerStateMapToServer(HashMap<String, Integer> PlayerState) implements CustomPacketPayload {
    public static final ResourceLocation SEND_PLAYER_STATE_MAP_TO_SERVER = getMCResourceLocation(Constants.MOD_ID, "send_player_state_map_to_server");

    public SendPlayerStateMapToServer(FriendlyByteBuf buf) {
        this((HashMap<String, Integer>) buf.readMap(Maps::newHashMapWithExpectedSize, FriendlyByteBuf::readUtf, FriendlyByteBuf::readInt));
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeMap(PlayerState(), FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeInt);
    }

    @Override
    public ResourceLocation id() {
        return SEND_PLAYER_STATE_MAP_TO_SERVER;
    }

    public static void handle(final SendPlayerStateMapToServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread
                    if (context.player().isPresent()) {
                        ServerPlayer sp1 = (ServerPlayer) context.player().get();

                        getSLivingEntityState().put(sp1, data.PlayerState());
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

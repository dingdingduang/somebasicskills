package net.dingdingduang.somebasicskills.networking.packet.skill;

import com.google.common.collect.Maps;
import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerState;

public record FetchPlayerStateMapFromServer(HashMap<String, Integer> PlayerState) implements CustomPacketPayload {
    public static final ResourceLocation FETCH_PLAYER_STATE_MAP_FROM_SERVER = getMCResourceLocation(Constants.MOD_ID, "fetch_player_state_map_from_server");

    public FetchPlayerStateMapFromServer(FriendlyByteBuf buf) {
//        HashMap<String, Integer> tempPlayerState = buf.readMap(Maps::newHashMapWithExpectedSize, FriendlyByteBuf::readUtf, FriendlyByteBuf::readInt);
        this((HashMap<String, Integer>) buf.readMap(Maps::newHashMapWithExpectedSize, FriendlyByteBuf::readUtf, FriendlyByteBuf::readInt));
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeMap(PlayerState(), FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeInt);
    }

    @Override
    public ResourceLocation id() {
        return FETCH_PLAYER_STATE_MAP_FROM_SERVER;
    }

    public static void handle(final FetchPlayerStateMapFromServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread
                    setCPlayerState(data.PlayerState());
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

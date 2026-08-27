package net.dingdingduang.somebasicskills.networking.packet;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.gui.overlay.SkillChannelingOverlay;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerLastKeyAction;

public record FetchResetPlayerKeyActionFromServer(String SkillID) implements CustomPacketPayload {
    public static final ResourceLocation FETCH_RESET_PLAYER_KEY_ACTION_FROM_SERVER = getMCResourceLocation(Constants.MOD_ID, "fetch_reset_player_key_action_from_server");

    public FetchResetPlayerKeyActionFromServer(FriendlyByteBuf buf) {
        this(buf.readUtf());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(SkillID());
    }

    @Override
    public ResourceLocation id() {
        return FETCH_RESET_PLAYER_KEY_ACTION_FROM_SERVER;
    }

    public static void handle(final FetchResetPlayerKeyActionFromServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread

                    //do shiet on client side
                    SkillChannelingOverlay.resetSkillKeyCodeActionFromPlayerSkillID2KeyCode(data.SkillID());
                    setCPlayerLastKeyAction(0);

                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.gui.overlay.SkillsInCooldownClientTimerOverlay;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;

public record FetchStartSkillCooldownFromServer(boolean IsTimerOn, String SkillID) implements CustomPacketPayload {
    public static final ResourceLocation FETCH_START_SKILL_COOLDOWN_FROM_SERVER = getMCResourceLocation(Constants.MOD_ID, "fetch_start_skill_cooldown_from_server");

    public FetchStartSkillCooldownFromServer(FriendlyByteBuf buf) {
        this(buf.readBoolean(), buf.readUtf());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeBoolean(IsTimerOn());
        buf.writeUtf(SkillID());
    }

    @Override
    public ResourceLocation id() {
        return FETCH_START_SKILL_COOLDOWN_FROM_SERVER;
    }

    public static void handle(final FetchStartSkillCooldownFromServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread
                    SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance().setCooldownTimer(data.IsTimerOn(), data.SkillID());
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

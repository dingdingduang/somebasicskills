package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.gui.overlay.SkillsInCooldownClientTimerOverlay;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;

public record FetchIncreaseSkillCooldownFromServer(boolean IsTimerOn, String SkillID, double CooldownAmount) implements CustomPacketPayload {
    public static final ResourceLocation FETCH_INCREASE_SKILL_COOLDOWN_FROM_SERVER = getMCResourceLocation(Constants.MOD_ID, "fetch_increase_skill_cooldown_from_server");

    public FetchIncreaseSkillCooldownFromServer(FriendlyByteBuf buf) {
        this(buf.readBoolean(), buf.readUtf(), buf.readDouble());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeBoolean(IsTimerOn());
        buf.writeUtf(SkillID());
        buf.writeDouble(CooldownAmount());
    }

    @Override
    public ResourceLocation id() {
        return FETCH_INCREASE_SKILL_COOLDOWN_FROM_SERVER;
    }

    public static void handle(final FetchIncreaseSkillCooldownFromServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance().increaseSkillCooldownTimer(data.IsTimerOn(), data.SkillID(), data.CooldownAmount());
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }

}

package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.gui.overlay.SkillsInCooldownClientTimerOverlay;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.codec.PacketCodecs;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;

public record FetchIncreaseSkillCooldownFromServer(boolean IsTimerOn, String SkillID, double CooldownAmount) implements CustomPayload {
    public static final CustomPayload.Id<FetchIncreaseSkillCooldownFromServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "fetch_increase_skill_cooldown_from_server") );

    public static final PacketCodec<RegistryByteBuf, FetchIncreaseSkillCooldownFromServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL,
            FetchIncreaseSkillCooldownFromServer::IsTimerOn,
            PacketCodecs.STRING,
            FetchIncreaseSkillCooldownFromServer::SkillID,
            PacketCodecs.DOUBLE,
            FetchIncreaseSkillCooldownFromServer::CooldownAmount,
            FetchIncreaseSkillCooldownFromServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final FetchIncreaseSkillCooldownFromServer data, final ClientPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.client().execute(() -> {
                    SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance().increaseSkillCooldownTimer(data.IsTimerOn(), data.SkillID(), data.CooldownAmount());
                });
    }

}

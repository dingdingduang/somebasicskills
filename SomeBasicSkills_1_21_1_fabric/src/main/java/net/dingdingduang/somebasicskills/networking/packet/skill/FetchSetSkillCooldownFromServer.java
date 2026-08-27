package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.gui.overlay.SkillsInCooldownClientTimerOverlay;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.codec.PacketCodecs;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;

public record FetchSetSkillCooldownFromServer(boolean IsTimerOn, String SkillID, double CooldownAmount) implements CustomPayload {
    public static final CustomPayload.Id<FetchSetSkillCooldownFromServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "fetch_set_skill_cooldown_from_server") );

    public static final PacketCodec<RegistryByteBuf, FetchSetSkillCooldownFromServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL,
            FetchSetSkillCooldownFromServer::IsTimerOn,
            PacketCodecs.STRING,
            FetchSetSkillCooldownFromServer::SkillID,
            PacketCodecs.DOUBLE,
            FetchSetSkillCooldownFromServer::CooldownAmount,
            FetchSetSkillCooldownFromServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final FetchSetSkillCooldownFromServer data, final ClientPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.client().execute(() -> {
                    //do on main thread
                    SkillsInCooldownClientTimerOverlay.getSkillsInCooldownClientTimerOverlayInstance().setCooldownTimer(data.IsTimerOn(), data.SkillID(), data.CooldownAmount());
                });
    }
}

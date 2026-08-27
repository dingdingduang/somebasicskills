package net.dingdingduang.somebasicskills.networking.packet.screen;

import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.network.codec.PacketCodecs;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.ServerPlayerMethods.giveServerPlayerExpPoints;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public record SendMaxBtnActionToServer(String SkillID, int TempLVL) implements CustomPayload {
    public static final CustomPayload.Id<SendMaxBtnActionToServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "send_max_btn_action_to_server") );

    public static final PacketCodec<RegistryByteBuf, SendMaxBtnActionToServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            SendMaxBtnActionToServer::SkillID,
            PacketCodecs.INTEGER,
            SendMaxBtnActionToServer::TempLVL,
            SendMaxBtnActionToServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final SendMaxBtnActionToServer data, final ServerPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.server().execute(() -> {
                    //do on main thread
                    ServerPlayerEntity sp1 = context.player();

                    SkillDataJson skill1 = getID2SkillData().get(data.SkillID());

                    giveServerPlayerExpPoints(sp1, -skill1.getXpCostPerLeveling()*data.TempLVL());

                    if (skill1.getPressAddBtnAction() != null) {
                        for (int i = 0; i < data.TempLVL(); i++) {
                            skill1.getPressAddBtnAction().executeAction(sp1);
                        }
                    }
                });
    }
}

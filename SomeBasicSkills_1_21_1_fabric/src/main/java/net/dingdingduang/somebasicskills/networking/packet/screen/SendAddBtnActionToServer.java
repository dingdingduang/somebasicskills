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

public record SendAddBtnActionToServer(String SkillID) implements CustomPayload {
    public static final CustomPayload.Id<SendAddBtnActionToServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "send_add_btn_action_to_server") );

    public static final PacketCodec<RegistryByteBuf, SendAddBtnActionToServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            SendAddBtnActionToServer::SkillID,
            SendAddBtnActionToServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final SendAddBtnActionToServer data, final ServerPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.server().execute(() -> {
                    //do on main thread
                    ServerPlayerEntity sp1 = context.player();

                    SkillDataJson skill1 = getID2SkillData().get(data.SkillID());

                    giveServerPlayerExpPoints(sp1, -skill1.getXpCostPerLeveling());

                    if (skill1.getPressAddBtnAction() != null) {
                        //action send back packet if needed inside action method
                        skill1.getPressAddBtnAction().executeAction(sp1);
                        //                skill1.getPressSubBtnAction().executeAction(sp1);
                        //                skill1.getPassiveSkillAction1().executeAction(sp1);
                        //                printInGameMsg("successful");
                    }
                });
    }
}

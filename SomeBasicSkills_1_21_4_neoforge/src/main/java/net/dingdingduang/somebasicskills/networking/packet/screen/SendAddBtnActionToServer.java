package net.dingdingduang.somebasicskills.networking.packet.screen;

import io.netty.buffer.ByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalmethods.ServerPlayerMethods.giveServerPlayerExpPoints;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public record SendAddBtnActionToServer(String SkillID) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SendAddBtnActionToServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "send_add_btn_action_to_server") );

    public static final StreamCodec<ByteBuf, SendAddBtnActionToServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            SendAddBtnActionToServer::SkillID,
            SendAddBtnActionToServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final SendAddBtnActionToServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                    //do on main thread
                    ServerPlayer sp1 = (ServerPlayer) context.player();

                    SkillDataJson skill1 = getID2SkillData().get(data.SkillID());

                    giveServerPlayerExpPoints(sp1, -skill1.getXpCostPerLeveling());

                    if (skill1.getPressAddBtnAction() != null) {
                        //action send back packet if needed inside action method
                        skill1.getPressAddBtnAction().executeAction(sp1);
                        //                skill1.getPressSubBtnAction().executeAction(sp1);
                        //                skill1.getPassiveSkillAction1().executeAction(sp1);
                        //                printInGameMsg("successful");
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

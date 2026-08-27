package net.dingdingduang.somebasicskills.networking.packet.screen;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalmethods.ServerPlayerMethods.giveServerPlayerExpPoints;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public record SendAddBtnActionToServer(String SkillID) implements CustomPacketPayload {
    public static final ResourceLocation SEND_ADD_BTN_ACTION_TO_SERVER = getMCResourceLocation(Constants.MOD_ID, "send_add_btn_action_to_server");

    @Override
    public ResourceLocation id() {
        return SEND_ADD_BTN_ACTION_TO_SERVER;
    }

    public SendAddBtnActionToServer(FriendlyByteBuf buf) {
        this(buf.readUtf());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(SkillID());
    }

    public static void handle(final SendAddBtnActionToServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread
                    if (context.player().isPresent()) {
                        ServerPlayer sp1 = (ServerPlayer) context.player().get();

                        SkillDataJson skill1 = getID2SkillData().get(data.SkillID());

                        giveServerPlayerExpPoints(sp1, -skill1.getXpCostPerLeveling());

                        if (skill1.getPressAddBtnAction() != null) {
                            //action send back packet if needed inside action method
                            skill1.getPressAddBtnAction().executeAction(sp1);
            //                skill1.getPressSubBtnAction().executeAction(sp1);
            //                skill1.getPassiveSkillAction1().executeAction(sp1);
            //                printInGameMsg("successful");
                        }
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

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

public record SendMaxBtnActionToServer(String SkillID, int TempLVL) implements CustomPacketPayload {
    public static final ResourceLocation SEND_MAX_BTN_ACTION_TO_SERVER = getMCResourceLocation(Constants.MOD_ID, "send_max_btn_action_to_server");

    public SendMaxBtnActionToServer(FriendlyByteBuf buf) {
        this(buf.readUtf(), buf.readInt());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(SkillID());
        buf.writeInt(TempLVL());
    }

    @Override
    public ResourceLocation id() {
        return SEND_MAX_BTN_ACTION_TO_SERVER;
    }

    public static void handle(final SendMaxBtnActionToServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread
                    if (context.player().isPresent()) {
                        ServerPlayer sp1 = (ServerPlayer) context.player().get();

                        SkillDataJson skill1 = getID2SkillData().get(data.SkillID());

                        giveServerPlayerExpPoints(sp1, -skill1.getXpCostPerLeveling()*data.TempLVL());

                        if (skill1.getPressAddBtnAction() != null) {
                            for (int i = 0; i < data.TempLVL(); i++) {
                                skill1.getPressAddBtnAction().executeAction(sp1);
                            }
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

package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public record SendSkillPassiveActionTwoToServer(String SkillID) implements CustomPacketPayload {
    public static final ResourceLocation SEND_SKILL_PASSIVE_ACTION_TWO_TO_SERVER = getMCResourceLocation(Constants.MOD_ID, "send_skill_passive_action_two_to_server");

    public SendSkillPassiveActionTwoToServer(FriendlyByteBuf buf) {
        this(buf.readUtf());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(SkillID());
    }

    @Override
    public ResourceLocation id() {
        return SEND_SKILL_PASSIVE_ACTION_TWO_TO_SERVER;
    }

    public static void handle(final SendSkillPassiveActionTwoToServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread
                    if (context.player().isPresent()) {
                        ServerPlayer sp1 = (ServerPlayer) context.player().get();

                        //TODO server timer cooldown if in cd, dont cast
                        SkillDataJson skill1 = getID2SkillData().get(data.SkillID());

                        if ((skill1.isPassiveType() || skill1.isBothType()) && skill1.getPassiveSkillAction2() != null) {
                            skill1.getPassiveSkillAction2().executeAction(sp1);
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

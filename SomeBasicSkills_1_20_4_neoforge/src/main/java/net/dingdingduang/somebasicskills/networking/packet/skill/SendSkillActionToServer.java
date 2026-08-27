package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkingFetchMsgMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerLastTriggeredNotChannelingActiveSkillID;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public record SendSkillActionToServer(String SkillID, float ClientUserSkillIDDefaultSkillCDTime) implements CustomPacketPayload {
    public static final ResourceLocation SEND_SKILL_ACTION_TO_SERVER = getMCResourceLocation(Constants.MOD_ID, "send_skill_action_to_server");

    public SendSkillActionToServer(FriendlyByteBuf buf) {
        this(buf.readUtf(), buf.readFloat());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(SkillID());
        buf.writeFloat(ClientUserSkillIDDefaultSkillCDTime());
    }

    @Override
    public ResourceLocation id() {
        return SEND_SKILL_ACTION_TO_SERVER;
    }

    public static void handle(final SendSkillActionToServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread
                    if (context.player().isPresent()) {
                        ServerPlayer sp1 = (ServerPlayer) context.player().get();

                        //TODO server timer cooldown if in cd, dont cast
                        SkillDataJson skill1 = getID2SkillData().get(data.SkillID());
                        int playerSkillLVL = 0;
                        if (getGlobalPlayerSkillID2lvlMap().get(sp1).containsKey(data.SkillID())) {
                            playerSkillLVL = getGlobalPlayerSkillID2lvlMap().get(sp1).get(data.SkillID());
                            if (playerSkillLVL <= 0) {
                                return;
                            }
                            playerSkillLVL = playerSkillLVL - 1;
                        }
                        float ServerSkillIDDefaultCDTime = skill1.getCooldownTime().get(playerSkillLVL).floatValue();

                        if (data.ClientUserSkillIDDefaultSkillCDTime() >= ServerSkillIDDefaultCDTime - 1f) {
                            if (skill1.isActiveType() && skill1.getActiveSkillAction1() != null) {
                                skill1.getActiveSkillAction1().executeAction(sp1);
                                getSPlayerLastTriggeredNotChannelingActiveSkillID().put(sp1, data.SkillID());
                            }
                        }
                        else {
                            //kick
                            NetworkingFetchMsgMethods.FetchPlayerMsgFromServer(sp1, "Cooldown doesn't match with the current server!");
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

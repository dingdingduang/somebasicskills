package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkingFetchMsgMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getPacketFailedTranslatableComponent;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerLivingEntityValues.getSLivingEntityState;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getSPlayerLastTriggeredChannelingActiveSkillID;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public record SendChannelingSkillToServer(String SkillID, float ClientUserSkillIDDefaultChannelingTime) implements CustomPacketPayload {
    public static final ResourceLocation SEND_CHANNELING_SKILL_TO_SERVER = getMCResourceLocation(Constants.MOD_ID, "send_channeling_skill_to_server");

    public SendChannelingSkillToServer(FriendlyByteBuf buf) {
        this(buf.readUtf(), buf.readFloat());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(SkillID());
        buf.writeFloat(ClientUserSkillIDDefaultChannelingTime());
    }

    @Override
    public ResourceLocation id() {
        return SEND_CHANNELING_SKILL_TO_SERVER;
    }

    public static void handle(final SendChannelingSkillToServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread
                    if (context.player().isPresent()) {
                        ServerPlayer sp1 = (ServerPlayer) context.player().get();
                        //if in cd, dont cast
                        SkillDataJson skill1 = getID2SkillData().get(data.SkillID());
                        int playerSkillLVL = 0;
                        if (getGlobalPlayerSkillID2lvlMap().get(sp1).containsKey(data.SkillID())) {
                            playerSkillLVL = getGlobalPlayerSkillID2lvlMap().get(sp1).get(data.SkillID());
                            if (playerSkillLVL <= 0) {
                                return;
                            }
                            playerSkillLVL = playerSkillLVL - 1;
                        }
                        float ServerSkillIDDefaultChannelingTime = skill1.getChannelingTime().get(playerSkillLVL).floatValue();

                        if (data.ClientUserSkillIDDefaultChannelingTime() >= ServerSkillIDDefaultChannelingTime - 1f) {
                            if (skill1.isActiveType() && skill1.getChannelingSkillAction1() != null) {
                                skill1.getChannelingSkillAction1().executeAction(sp1);
                                getSPlayerLastTriggeredChannelingActiveSkillID().put(sp1, data.SkillID());
                            }
                        } else {
                            //kick?
                            NetworkingFetchMsgMethods.FetchPlayerMsgFromServer(sp1, "Channeling Time #1 doesn't match with the current server!");
                        }

                        HashMap<LivingEntity, HashMap<String, Integer>> ServerLivingEntityState = getSLivingEntityState();
                        if (ServerLivingEntityState.containsKey(sp1) && ServerLivingEntityState.get(sp1) != null) {
                            ServerLivingEntityState.get(sp1).put(Constants.IS_CHANNELING, Constants.ACTION_ON);
                            ServerLivingEntityState.get(sp1).put(Constants.CHANNELING_INTERRUPTED, Constants.ACTION_OFF);
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

package net.dingdingduang.somebasicskills.networking.packet.skill;

import com.google.common.collect.Maps;
import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.HashMap;
import java.util.Map;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;
import static net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods.SkillPlayernameFileWriteTo;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;

public record SendSkillLevelMapToServer(HashMap<String, Integer> SkillID2LVL) implements CustomPacketPayload {
    public static final ResourceLocation SEND_SKILL_LEVEL_MAP_TO_SERVER = getMCResourceLocation(Constants.MOD_ID, "send_skill_level_map_to_server");

    public SendSkillLevelMapToServer(FriendlyByteBuf buf) {
        this((HashMap<String, Integer>) buf.readMap(Maps::newHashMapWithExpectedSize, FriendlyByteBuf::readUtf, FriendlyByteBuf::readInt));
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeMap(SkillID2LVL(), FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeInt);
    }

    @Override
    public ResourceLocation id() {
        return SEND_SKILL_LEVEL_MAP_TO_SERVER;
    }

    public static void handle(final SendSkillLevelMapToServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread
                    if (context.player().isPresent()) {
                        ServerPlayer sp1 = (ServerPlayer) context.player().get();

                        MinecraftServer mcServer = getMinecraftServerInstance();

                        HashMap<String, Integer> correctSkillID2LVLMap = new HashMap<>();
                        for (Map.Entry<String, Integer> entry: data.SkillID2LVL().entrySet()) {
                            if (entry.getValue() > 0) {
                                correctSkillID2LVLMap.put(entry.getKey(), Math.min(entry.getValue(), getID2SkillData().get(entry.getKey()).getTotalLevel()));
                            }
                        }
                        getGlobalPlayerSkillID2lvlMap().put(sp1, correctSkillID2LVLMap);
                        SkillPlayernameFileWriteTo(mcServer, sp1);

                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

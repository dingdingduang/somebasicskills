package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;
import static net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods.SkillPlayernameFileWriteTo;

public record SendSkillLevelKeyValueOnlyToServer(String SkillID, int SkillLevel) implements CustomPacketPayload {
    public static final ResourceLocation SEND_SKILL_LEVEL_KEY_VALUE_ONLY_TO_SERVER = getMCResourceLocation(Constants.MOD_ID, "send_skill_level_key_value_only_to_server");

    public SendSkillLevelKeyValueOnlyToServer(FriendlyByteBuf buf) {
        this(buf.readUtf(), buf.readInt());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(SkillID());
        buf.writeInt(SkillLevel());
    }

    @Override
    public ResourceLocation id() {
        return SEND_SKILL_LEVEL_KEY_VALUE_ONLY_TO_SERVER;
    }

    public static void handle(final SendSkillLevelKeyValueOnlyToServer data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    //do on main thread
                    if (context.player().isPresent()) {
                        ServerPlayer sp1 = (ServerPlayer) context.player().get();

                        MinecraftServer mcServer = getMinecraftServerInstance();

                        HashMap<String, Integer> ServerPlayerSkillID2LVLMap = getGlobalPlayerSkillID2lvlMap().get(sp1);
                        if (data.SkillLevel() <= 0) {
                            ServerPlayerSkillID2LVLMap.remove(data.SkillID());
                        }
                        else {
                            ServerPlayerSkillID2LVLMap.put(data.SkillID(), Math.min(data.SkillLevel(), getID2SkillData().get(data.SkillID()).getTotalLevel()));
                        }
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

package net.dingdingduang.somebasicskills.networking.packet.skill;

import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashMap;
import java.util.Map;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;
import static net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods.SkillPlayernameFileWriteTo;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;

public record SendSkillLevelMapToServer(HashMap<String, Integer> SkillID2LVL) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SendSkillLevelMapToServer> TYPE = new CustomPacketPayload.Type<>(getMCResourceLocation(Constants.MOD_ID, "send_skill_level_map_to_server") );

    public static final StreamCodec<ByteBuf, SendSkillLevelMapToServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.map(Maps::newHashMapWithExpectedSize, ByteBufCodecs.STRING_UTF8, ByteBufCodecs.INT),
            SendSkillLevelMapToServer::SkillID2LVL,
            SendSkillLevelMapToServer::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final SendSkillLevelMapToServer data, final IPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.enqueueWork(() -> {
                    //do on main thread
                    ServerPlayer sp1 = (ServerPlayer) context.player();

                    MinecraftServer mcServer = getMinecraftServerInstance();

                    HashMap<String, Integer> correctSkillID2LVLMap = new HashMap<>();
                    for (Map.Entry<String, Integer> entry: data.SkillID2LVL().entrySet()) {
                        if (entry.getValue() > 0) {
                            correctSkillID2LVLMap.put(entry.getKey(), Math.min(entry.getValue(), getID2SkillData().get(entry.getKey()).getTotalLevel()));
                        }
                    }
                    getGlobalPlayerSkillID2lvlMap().put(sp1, correctSkillID2LVLMap);
                    SkillPlayernameFileWriteTo(mcServer, sp1);
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(getPacketFailedTranslatableComponent(e.getMessage()));
                    return null;
                });
    }
}

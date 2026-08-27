package net.dingdingduang.somebasicskills.networking.packet.skill;

import com.google.common.collect.Maps;
import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.network.codec.PacketCodecs;

import java.util.HashMap;
import java.util.Map;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;
import static net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods.SkillPlayernameFileWriteTo;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;

public record SendSkillLevelMapToServer(HashMap<String, Integer> SkillID2LVL) implements CustomPayload {
    public static final CustomPayload.Id<SendSkillLevelMapToServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "send_skill_level_map_to_server") );

    public static final PacketCodec<RegistryByteBuf, SendSkillLevelMapToServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.map(Maps::newHashMapWithExpectedSize, PacketCodecs.STRING, PacketCodecs.INTEGER),
            SendSkillLevelMapToServer::SkillID2LVL,
            SendSkillLevelMapToServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final SendSkillLevelMapToServer data, final ServerPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.server().execute(() -> {
                    //do on main thread
                    ServerPlayerEntity sp1 = context.player();

                    MinecraftServer mcServer = getMinecraftServerInstance(sp1);

                    HashMap<String, Integer> correctSkillID2LVLMap = new HashMap<>();
                    for (Map.Entry<String, Integer> entry: data.SkillID2LVL().entrySet()) {
                        if (entry.getValue() > 0) {
                            correctSkillID2LVLMap.put(entry.getKey(), Math.min(entry.getValue(), getID2SkillData().get(entry.getKey()).getTotalLevel()));
                        }
                    }
                    getGlobalPlayerSkillID2lvlMap().put(sp1, correctSkillID2LVLMap);
                    SkillPlayernameFileWriteTo(mcServer, sp1);
                });
    }
}

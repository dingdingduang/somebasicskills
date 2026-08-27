package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.minecraft.network.RegistryByteBuf;
import net.dingdingduang.somebasicskills.Constants;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.network.codec.PacketCodecs;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;
import static net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods.SkillPlayernameFileWriteTo;

public record SendSkillLevelKeyValueOnlyToServer(String SkillID, int SkillLevel) implements CustomPayload {
    public static final CustomPayload.Id<SendSkillLevelKeyValueOnlyToServer> TYPE = new CustomPayload.Id<>(getMCResourceLocation(Constants.MOD_ID, "send_skill_level_key_value_only_to_server") );

    public static final PacketCodec<RegistryByteBuf, SendSkillLevelKeyValueOnlyToServer> STREAM_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            SendSkillLevelKeyValueOnlyToServer::SkillID,
            PacketCodecs.INTEGER,
            SendSkillLevelKeyValueOnlyToServer::SkillLevel,
            SendSkillLevelKeyValueOnlyToServer::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return TYPE;
    }

    public static void handle(final SendSkillLevelKeyValueOnlyToServer data, final ServerPlayNetworking.Context context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.server().execute(() -> {
                    //do on main thread
                    ServerPlayerEntity sp1 = context.player();

                    MinecraftServer mcServer = getMinecraftServerInstance(sp1);

                    HashMap<String, Integer> ServerPlayerSkillID2LVLMap = getGlobalPlayerSkillID2lvlMap().get(sp1);
                    if (data.SkillLevel() <= 0) {
                        ServerPlayerSkillID2LVLMap.remove(data.SkillID());
                    }
                    else {
                        ServerPlayerSkillID2LVLMap.put(data.SkillID(), Math.min(data.SkillLevel(), getID2SkillData().get(data.SkillID()).getTotalLevel()));
                    }
                    SkillPlayernameFileWriteTo(mcServer, sp1);
                });
    }
}

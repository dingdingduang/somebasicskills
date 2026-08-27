package net.dingdingduang.somebasicskills.networking.packet.skill;

import com.google.common.collect.Maps;
import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkFabricGeneralMethods;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;
import static net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods.SkillPlayernameFileWriteTo;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftServerInstance;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;

public class SendSkillLevelMapToServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "send_skill_level_map_to_server");

    public static PacketByteBuf setupPacket(HashMap<String, Integer> skillLVLMap) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeMap(skillLVLMap, PacketByteBuf::writeString, PacketByteBuf::writeInt);
        return packetToBeSent;
    }

    public static void toServerHandle(MinecraftServer server, ServerPlayerEntity serverPlayer, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final HashMap<String, Integer> SkillID2LVL = buf.readMap(Maps::newHashMapWithExpectedSize, PacketByteBuf::readString, PacketByteBuf::readInt);

        server.execute( () -> {
            //start action
            MinecraftServer mcServer = getMinecraftServerInstance(serverPlayer);
            if (mcServer == null) { return; }

            HashMap<String, Integer> correctSkillID2LVLMap = new HashMap<>();
            for (Map.Entry<String, Integer> entry: SkillID2LVL.entrySet()) {
                if (entry.getValue() > 0) {
                    correctSkillID2LVLMap.put(entry.getKey(), Math.min(entry.getValue(), getID2SkillData().get(entry.getKey()).getTotalLevel()));
                }
            }
            getGlobalPlayerSkillID2lvlMap().put(serverPlayer, correctSkillID2LVLMap);
            SkillPlayernameFileWriteTo(mcServer, serverPlayer);
        });
    }
}

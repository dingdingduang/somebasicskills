package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkFabricGeneralMethods;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.HashMap;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftServerInstance;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;
import static net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods.SkillPlayernameFileWriteTo;

public class SendSkillLevelKeyValueOnlyToServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "send_skill_level_key_value_only_to_server");

    public static PacketByteBuf setupPacket(String skillID, int skillLevel) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeString(skillID);
        packetToBeSent.writeInt(skillLevel);
        return packetToBeSent;
    }

    public static void toServerHandle(MinecraftServer server, ServerPlayerEntity serverPlayer, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final String SkillID = buf.readString();
        final int SkillLevel = buf.readInt();

        server.execute( () -> {
            //start action
            MinecraftServer mcServer = getMinecraftServerInstance(serverPlayer);
            if (mcServer == null) { return; }

            HashMap<String, Integer> ServerPlayerSkillID2LVLMap = getGlobalPlayerSkillID2lvlMap().get(serverPlayer);
            if (SkillLevel <= 0) {
                ServerPlayerSkillID2LVLMap.remove(SkillID);
            }
            else {
                ServerPlayerSkillID2LVLMap.put(SkillID, Math.min(SkillLevel, getID2SkillData().get(SkillID).getTotalLevel()));
            }
            SkillPlayernameFileWriteTo(mcServer, serverPlayer);
        });
    }
}

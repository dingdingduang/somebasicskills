package net.dingdingduang.somebasicskills.networking.packet.screen;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkFabricGeneralMethods;
import net.dingdingduang.somebasicskills.skilldata.SkillDataJson;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalmethods.ServerPlayerMethods.giveServerPlayerExpPoints;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;

public class SendMaxBtnActionToServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "send_max_btn_action_to_server");

    public static PacketByteBuf setupPacket(String skillID, int tempLVL) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeString(skillID);
        packetToBeSent.writeInt(tempLVL);
        return packetToBeSent;
    }

    public static void toServerHandle(MinecraftServer server, ServerPlayerEntity serverPlayer, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final String SkillID = buf.readString();
        final int TempLVL = buf.readInt();

        server.execute( () -> {
            //start action
            SkillDataJson skill1 = getID2SkillData().get(SkillID);

            giveServerPlayerExpPoints(serverPlayer, -skill1.getXpCostPerLeveling()*TempLVL);

            if (skill1.getPressAddBtnAction() != null) {
                for (int i = 0; i < TempLVL; i++) {
                    skill1.getPressAddBtnAction().executeAction(serverPlayer);
                }
            }
        });
    }
}

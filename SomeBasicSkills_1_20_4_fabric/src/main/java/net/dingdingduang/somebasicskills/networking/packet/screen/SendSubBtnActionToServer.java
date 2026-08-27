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

public class SendSubBtnActionToServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "send_sub_btn_action_to_server");

    public static PacketByteBuf setupPacket(String skillID) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeString(skillID);
        return packetToBeSent;
    }

    public static void toServerHandle(MinecraftServer server, ServerPlayerEntity serverPlayer, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        String SkillID = buf.readString();

        server.execute( () -> {
            //start action
            SkillDataJson skill1 = getID2SkillData().get(SkillID);

            giveServerPlayerExpPoints(serverPlayer, skill1.getXpCostPerLeveling());

            if (skill1.getPressSubBtnAction() != null) {
                //action send back packet if needed inside action method
                skill1.getPressSubBtnAction().executeAction(serverPlayer);
            }
        });
    }
}

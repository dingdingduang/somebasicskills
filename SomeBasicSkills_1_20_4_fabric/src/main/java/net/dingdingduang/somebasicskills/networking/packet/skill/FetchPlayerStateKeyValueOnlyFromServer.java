package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.networking.NetworkFabricGeneralMethods;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.getCPlayerState;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setCPlayerCurrentActiveSkillID;

public class FetchPlayerStateKeyValueOnlyFromServer {
    public static Identifier ID = getMCResourceLocation(Constants.MOD_ID, "fetch_player_state_key_value_only_from_server");

    public static PacketByteBuf setupPacket(String stateName, int anyInteger) {
        PacketByteBuf packetToBeSent = NetworkFabricGeneralMethods.createPackage();
        packetToBeSent.writeString(stateName);
        packetToBeSent.writeInt(anyInteger);
        return packetToBeSent;
    }

    public static void toClientHandle(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //decode packet here
        final String StateName = buf.readString();
        final int AnyInteger = buf.readInt();

        client.execute( () -> {
            //start action

            getCPlayerState().put(StateName, AnyInteger);
            if (StateName.hashCode() == Constants.IS_IN_ACTION.hashCode() && AnyInteger == 0) {
                setCPlayerCurrentActiveSkillID(Constants.NOTHING);
            }
        });
    }
}

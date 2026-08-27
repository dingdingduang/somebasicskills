package net.dingdingduang.somebasicskills.networking.packet.skill;

import com.google.common.collect.Maps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;
import static net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods.SkillPlayernameFileWriteTo;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftServerInstance;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;

public class SendSkillLevelMapToServer {
    private HashMap<String, Integer> SkillID2LVL;

    public SendSkillLevelMapToServer(HashMap<String, Integer> skillID2LVL) {
        this.SkillID2LVL = skillID2LVL;
    }

    public SendSkillLevelMapToServer(FriendlyByteBuf buf) {
        this.SkillID2LVL = buf.readMap(Maps::newHashMapWithExpectedSize, FriendlyByteBuf::readUtf, FriendlyByteBuf::readInt);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeMap(this.SkillID2LVL, FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeInt);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on server side
            ServerPlayer sp1 = ctx.get().getSender();

            MinecraftServer mcServer = getMinecraftServerInstance();
            if (mcServer == null) { return; }

            HashMap<String, Integer> correctSkillID2LVLMap = new HashMap<>();
            for (Map.Entry<String, Integer> entry: SkillID2LVL.entrySet()) {
                if (entry.getValue() > 0) {
                    correctSkillID2LVLMap.put(entry.getKey(), Math.min(entry.getValue(), getID2SkillData().get(entry.getKey()).getTotalLevel()));
                }
            }
            getGlobalPlayerSkillID2lvlMap().put(sp1, correctSkillID2LVLMap);
            SkillPlayernameFileWriteTo(mcServer, sp1);
        });

        return true;
    }
}

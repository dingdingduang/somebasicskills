package net.dingdingduang.somebasicskills.networking.packet.skill;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMinecraftServerInstance;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalServerPlayerValues.getGlobalPlayerSkillID2lvlMap;
import static net.dingdingduang.somebasicskills.skilldata.SkillDataInitialization.getID2SkillData;
import static net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods.SkillPlayernameFileWriteTo;

public class SendSkillLevelKeyValueOnlyToServer {
    private final String SkillID;
    private final int SkillLevel;

    public SendSkillLevelKeyValueOnlyToServer(String skillID, int skillLevel) {
        this.SkillID = skillID;
        this.SkillLevel = skillLevel;
    }

    public SendSkillLevelKeyValueOnlyToServer(FriendlyByteBuf buf) {
        this.SkillID = buf.readUtf();
        this.SkillLevel = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.SkillID);
        buf.writeInt(this.SkillLevel);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on server side
            ServerPlayer sp1 = ctx.get().getSender();

            MinecraftServer mcServer = getMinecraftServerInstance();
            if (mcServer == null) { return; }

            HashMap<String, Integer> ServerPlayerSkillID2LVLMap = getGlobalPlayerSkillID2lvlMap().get(sp1);
            if (this.SkillLevel <= 0) {
                ServerPlayerSkillID2LVLMap.remove(this.SkillID);
            }
            else {
                ServerPlayerSkillID2LVLMap.put(this.SkillID, Math.min(this.SkillLevel, getID2SkillData().get(this.SkillID).getTotalLevel()));
            }
            SkillPlayernameFileWriteTo(mcServer, sp1);
        });

        return true;
    }
}

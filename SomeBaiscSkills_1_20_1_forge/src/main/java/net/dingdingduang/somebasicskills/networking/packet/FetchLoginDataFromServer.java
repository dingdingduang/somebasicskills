package net.dingdingduang.somebasicskills.networking.packet;

import com.google.common.collect.Maps;
import net.dingdingduang.somebasicskills.util.fileio.FileReadWriteMethods;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.function.Supplier;

import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.initializeClientPlayerHashMapsWhenLogin;
import static net.dingdingduang.somebasicskills.sbsattributes.statusquery.AttributeClientPlayerStatusQueryMethods.setupSkillDataClientPlayerInitialization;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.setClientPlayerSkillID2lvlMap;
//import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientPlayerValues.initializeClientPlayerHashMapsWhenLogin;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientValuesInit.globalClientValuesInit;
import static net.dingdingduang.somebasicskills.globalvalues.GlobalClientValuesInit.isGlobalClientValuesInitialized;

public class FetchLoginDataFromServer {
    private HashMap<String, Integer> SkillID2LVL;

    public FetchLoginDataFromServer(HashMap<String, Integer> a) {
        this.SkillID2LVL = a;
    }

    public FetchLoginDataFromServer(FriendlyByteBuf buf) {
        this.SkillID2LVL = buf.readMap(Maps::newHashMapWithExpectedSize, FriendlyByteBuf::readUtf, FriendlyByteBuf::readInt);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeMap(this.SkillID2LVL, FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeInt);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //do shiet on client side
            if (!isGlobalClientValuesInitialized()) {
                globalClientValuesInit();
            }

            //initialize player hashmap
            initializeClientPlayerHashMapsWhenLogin();

            //initialize player status
            setupSkillDataClientPlayerInitialization();

            setClientPlayerSkillID2lvlMap(this.SkillID2LVL);
            FileReadWriteMethods.SkillPriorityPlayernameFileReadFrom();

            //read player keyCombo and quickSlot setting
            FileReadWriteMethods.ClientQuickslotPlayernameFileReadFrom();
            FileReadWriteMethods.ClientKeycomboPlayernameFileReadFrom();
        });

        return true;
    }
}

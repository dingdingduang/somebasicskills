package net.dingdingduang.somebasicskills.sbsattributes;

import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

//for hashmap key unique
public class UniqueAttributePrimitiveOnly extends AttributePrimitiveOnly {
    private String UniqueID;

    public UniqueAttributePrimitiveOnly(double amount, int timeTicks, int operation, ResourceLocation attrUUID, String attrName, String skillID, String uniqueID) {
        super(amount, timeTicks, operation, attrUUID, attrName, skillID);
        this.UniqueID = uniqueID;
    }

    public String getUniqueID() {
        return this.UniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.UniqueID = uniqueID;
    }

    @Override
    public int hashCode() {
        return this.UniqueID.hashCode()+this.getOp();
//        return this.getSkillID().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UniqueAttributePrimitiveOnly uniqueAttr) {
            return (uniqueAttr.getUniqueID()+this.getOp()).equals(this.UniqueID+this.getOp());
//            return (uniqueAttr.getSkillID()).equals(this.getSkillID());
        }
        return false;
    }
}

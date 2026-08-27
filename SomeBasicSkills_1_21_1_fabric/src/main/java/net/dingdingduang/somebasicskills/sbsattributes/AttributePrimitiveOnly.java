package net.dingdingduang.somebasicskills.sbsattributes;

import net.dingdingduang.somebasicskills.util.MethodAction;
import net.minecraft.util.Identifier;

import java.util.UUID;

//runs each time, default period is 10 ticks (0.5s) in AttributeServerTimerOverlay.java
public class AttributePrimitiveOnly {
    private double Amount;
    private int TimeTicks;
    private Identifier AttrUUID;
    private int op;
    private MethodAction AttributeMethodAction;
    private MethodAction AttributeFinalMethodAction;
    private String AttrName;
    private String SkillID; //future plan: details for character status info panel

    public AttributePrimitiveOnly(double amount, int timeTicks, int operation, Identifier attrUUID, String attrName, String skillID) {
        this.Amount = amount;
        this.TimeTicks = timeTicks;
        this.AttrUUID = attrUUID;
        this.AttrName = attrName;
        this.op = operation;
        this.SkillID = skillID;
    }

    public double getAmount() { return this.Amount; }
    public void setAmount(double amount) { this.Amount = amount; }
    public int getTimeTicks() { return this.TimeTicks; }
    public void setTimeTicks(int timeTicks) { this.TimeTicks = timeTicks; }
    public Identifier getAttrUUID() { return this.AttrUUID; }
    public void setAttrUUID(Identifier attrUUID) { this.AttrUUID = attrUUID; }
    public String getAttrName() { return this.AttrName; }
    public void setAttrName(String skillID) { this.AttrName = skillID; }
    public int getOp() { return this.op; }
    public void setOp(int op) { this.op = op; }
    public String getSkillID() { return this.SkillID; }
    public void setSkillID(String skillID) { this.SkillID = skillID; }

    public MethodAction getAttributeMethodAction() {
        return this.AttributeMethodAction;
    }

    public void setAttributeMethodAction(MethodAction attributeMethodAction) {
        this.AttributeMethodAction = attributeMethodAction;
    }
    public MethodAction getAttributeFinalMethodAction() {
        return this.AttributeFinalMethodAction;
    }

    public void setAttributeFinalMethodAction(MethodAction attributeFinalMethodAction) {
        this.AttributeFinalMethodAction = attributeFinalMethodAction;
    }
}

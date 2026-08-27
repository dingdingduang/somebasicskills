package net.dingdingduang.somebasicskills.sbsattributes;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.HashMap;

public class AttributeHelper {
    private Holder<Attribute> AttrType;
//    private String AttrID;
    //TODO: finish indexing
    private HashMap<AttributePrimitiveOnly, Integer> AttributePrimitive2Index;
    private AttributeModifier AttrModifier;

    public AttributeHelper(Holder<Attribute> attributeType) {
        this.AttrType = attributeType;
//        this.AttrID = attributeType.getDescriptionId();
        this.AttributePrimitive2Index = new HashMap<AttributePrimitiveOnly, Integer>();
    }

//    @Override
//    public int hashCode() {
//        return this.AttrID.hashCode();
//    }

    public Holder<Attribute> getAttrType() { return AttrType; }
    public void setAttrType(Holder<Attribute> attrType) { this.AttrType = attrType; }

    public HashMap<AttributePrimitiveOnly, Integer> getAttributePrimitive2Index() { return this.AttributePrimitive2Index; }
    public void setAttributePrimitive2Index(HashMap<AttributePrimitiveOnly, Integer> attributePrimitive2Index) { this.AttributePrimitive2Index = attributePrimitive2Index; }

    public AttributeModifier getAttrModifier() { return this.AttrModifier; }
    public void setAttrModifier(AttributeModifier attrModifier) { this.AttrModifier = attrModifier; }
}

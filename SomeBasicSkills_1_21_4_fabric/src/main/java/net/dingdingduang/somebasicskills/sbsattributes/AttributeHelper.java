package net.dingdingduang.somebasicskills.sbsattributes;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.HashMap;

public class AttributeHelper {
    private RegistryEntry<EntityAttribute> AttrType;
//    private String AttrID;
    //TODO: finish indexing
    private HashMap<AttributePrimitiveOnly, Integer> AttributePrimitive2Index;
    private EntityAttributeModifier AttrModifier;

    public AttributeHelper(RegistryEntry<EntityAttribute> attributeType) {
        this.AttrType = attributeType;
//        this.AttrID = attributeType.getDescriptionId();
        this.AttributePrimitive2Index = new HashMap<AttributePrimitiveOnly, Integer>();
    }

//    @Override
//    public int hashCode() {
//        return this.AttrID.hashCode();
//    }

    public RegistryEntry<EntityAttribute> getAttrType() { return AttrType; }
    public void setAttrType(RegistryEntry<EntityAttribute> attrType) { this.AttrType = attrType; }

    public HashMap<AttributePrimitiveOnly, Integer> getAttributePrimitive2Index() { return this.AttributePrimitive2Index; }
    public void setAttributePrimitive2Index(HashMap<AttributePrimitiveOnly, Integer> attributePrimitive2Index) { this.AttributePrimitive2Index = attributePrimitive2Index; }

    public EntityAttributeModifier getAttrModifier() { return this.AttrModifier; }
    public void setAttrModifier(EntityAttributeModifier attrModifier) { this.AttrModifier = attrModifier; }
}

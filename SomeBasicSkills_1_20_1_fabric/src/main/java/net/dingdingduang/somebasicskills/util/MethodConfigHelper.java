package net.dingdingduang.somebasicskills.util;

public class MethodConfigHelper {
    private MethodConfigAction MethodAction;
    private int IntValue;
    private boolean isBooleanConfig;
//    private String ID;

    public MethodConfigHelper(MethodConfigAction action, int intValue, boolean IsBooleanConfig) {
        this.MethodAction = action;
        this.IntValue = intValue;
//        this.ID = strID;
        this.isBooleanConfig = IsBooleanConfig;
    }

    public MethodConfigAction getMethodAction() { return this.MethodAction; }
    public void setMethodAction(MethodConfigAction methodAction) { this.MethodAction = methodAction; }
    public int getIntValue() { return this.IntValue; }
    public void setIntValue(int value) { this.IntValue = value; }
    public boolean isBooleanConfig() { return this.isBooleanConfig; }
    public void setBooleanConfig(boolean booleanConfig) { this.isBooleanConfig = booleanConfig; }
//    public String getID() { return this.ID; }
//    public void setID(String ID) { this.ID = ID; }


//    @Override
//    public int hashCode() {
//        return this.ID.hashCode();
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj instanceof MethodConfigHelper helper) {
//            return (helper.getID()).equals(this.ID);
//        }
//        return false;
//    }
}

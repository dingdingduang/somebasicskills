package net.dingdingduang.somebasicskills.skilldata;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;

public class SkillDataInitialization {
    private static HashMap<String, ArrayList<String>> Category2SkillID;
    private static HashMap<String, SkillDataJson> Role2TitleMap;
    private static HashMap<String, HashMap<String, ResourceLocation>> Role2IconLocMap;
    private static HashMap<String, SkillDataJson> ID2SkillData;

    public static void SkillDataInit() {
        Category2SkillID = new HashMap<String, ArrayList<String>>();
        Role2TitleMap = new HashMap<String, SkillDataJson>();
        Role2IconLocMap = new HashMap<String, HashMap<String, ResourceLocation>>();
        ID2SkillData = new HashMap<String, SkillDataJson>();
    }

    public static HashMap<String, ArrayList<String>> getCategory2SkillID() { return Category2SkillID; }
    public static void setCategory2SkillID(HashMap<String, ArrayList<String>> category2SkillID) { Category2SkillID = category2SkillID; }

    public static HashMap<String, SkillDataJson> getRole2TitleMap() { return Role2TitleMap; }
    public static void setRole2TitleMap(HashMap<String, SkillDataJson> role2TitleMap) { Role2TitleMap = role2TitleMap; }
    public static HashMap<String, HashMap<String, ResourceLocation>> getRole2IconLocMap() { return Role2IconLocMap; }
    public static void setRole2IconLocMap(HashMap<String, HashMap<String, ResourceLocation>> role2IconLocMap) { Role2IconLocMap = role2IconLocMap; }

    public static HashMap<String, SkillDataJson> getID2SkillData() { return ID2SkillData; }
    public static void setID2SkillData(HashMap<String, SkillDataJson> ID2SkillData) { SkillDataInitialization.ID2SkillData = ID2SkillData; }
}

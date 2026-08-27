package net.dingdingduang.somebasicskills.skilldata;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import net.dingdingduang.somebasicskills.Constants;
import net.dingdingduang.somebasicskills.globalmethods.ServerSkillMethods;
import net.dingdingduang.somebasicskills.resourcelocation.json.JsonResouceLocation;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.neoforged.fml.ModList;

//import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getClientResourceManager;
//import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getServerResourceManager;
import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.*;


public class SkillDataJsonReader {
    public static List<SkillDataJson> readSkillDataJson(ResourceLocation resourceLocation, boolean isLenient) {
//        Type SKILLDATA_TYPE = new TypeToken<List<SkillDataJson>>() {}.getType();
//        Gson gson = new Gson();
//
//        Resource resource = getClientResourceManager().getResource(JsonResouceLocation.SKILLDATA_COMMON).orElseThrow();
//        JsonReader jsonreader1 = new JsonReader(new InputStreamReader(resource.open(), StandardCharsets.UTF_8));
//
//        List<SkillDataJson> data = gson.fromJson(jsonreader1, SKILLDATA_TYPE);

        Gson gson = new Gson();

        Type SKILLDATA_TYPE = new TypeToken<List<SkillDataJson>>() {}.getType();
        InputStream resourceLocationString = null;
        try {
            Resource tempResource = getMinecraftInstance().getResourceManager().getResourceOrThrow(resourceLocation);
            resourceLocationString = tempResource.open();
        }
        catch (Exception fileNotFoundException) {
            throw new NoSuchElementException("Resource cannot be opened: " + resourceLocation);
        }

        if (resourceLocationString == null) { throw new NoSuchElementException("Skilldata file missing: " + resourceLocation); }

        Reader tempReader = new InputStreamReader(new BufferedInputStream(resourceLocationString), StandardCharsets.UTF_8);
        JsonReader tempJsonReader = new JsonReader(tempReader);

        if (isLenient) {
            tempJsonReader.setLenient(true);
        }

        return gson.fromJson(tempJsonReader, SKILLDATA_TYPE);
    }

    public static void setupSkillDataList(List<SkillDataJson> skillDataList, String MOD_ID) {
        HashMap<String, ArrayList<String>> tempCat2IDMap = SkillDataInitialization.getCategory2SkillID();
        HashMap<String, ResourceLocation> IconStr2ResLocMap = new HashMap<String, ResourceLocation>();
        String CategoryName = "unclassified", SkillID;
        ArrayList<String> tempStrList;
        for (SkillDataJson skill1: skillDataList) {
            CategoryName = skill1.getRoleCategoryName();
            SkillID = skill1.getSkillID();
            if (SkillID.equals(Constants.SKILL_TITLE)) {
                SkillDataInitialization.getRole2TitleMap().put(CategoryName, skill1);
                IconStr2ResLocMap.put(CategoryName+Constants.SKILL_TITLE, getMCResourceLocation(MOD_ID, skill1.getResLocBtn()));
                IconStr2ResLocMap.put("DISBTN"+CategoryName+Constants.SKILL_TITLE, getMCResourceLocation(MOD_ID, skill1.getResLocDisBtn()));
                continue;
            }

            //init advanceSkillIDArray
            if (skill1.getAdvancedSkillID() == null) {
                skill1.setAdvancedSkillID(new ArrayList<String>());
            }
            //=-=-=-=-=-=-=-=-=-=-=-=
            //TODO TEST, make them not null for activating skill
            if (!skill1.isChannelingCast()) {
                boolean isBothType = skill1.isBothType();
                if (skill1.isActiveType() || isBothType) {
                    if (skill1.getActiveSkillAction1() == null) {
                        skill1.setActiveSkillAction1(
                                (sp1) -> {
//                                getSkillsInCooldownServerTimerOverlayInstance().setCooldownTimer(true, sp1, skill1.getSkillID());
                                    printInGameMsg("SkillID: " + skill1.getSkillID() + ", player: " + sp1.getName());

//                                sp1.hurt(sp1.damageSources().generic(), 1.0f);
                                }
                        );
                    }
                }
                if (skill1.isPassiveType() || isBothType) {
                    if (skill1.getPassiveSkillAction1() == null) {
                        skill1.setPassiveSkillAction1(
                                (sp1) -> {
                                    printInGameMsg("Passive SkillID: " + skill1.getSkillID() + ", player: " + sp1.getName());
                                }
                        );
                    }
                }
            }
            else {
                if (skill1.getActiveSkillAction1() == null) {
                    skill1.setActiveSkillAction1(
                            (sp1) -> {
                                printInGameMsg("Channeling Finish SkillID: " + skill1.getSkillID() + ", player: " + sp1.getName());

//                                sp1.hurt(sp1.damageSources().generic(), 2.0f);
                            }
                    );
                }
                if (skill1.getChannelingSkillAction1() == null) {
                    skill1.setChannelingSkillAction1(
                            (sp1) -> {
//                                getSkillsInCooldownServerTimerOverlayInstance().setCooldownTimer(true, sp1, skill1.getSkillID());
                                printInGameMsg("Channeling SkillID: " + skill1.getSkillID() + ", player: " + sp1.getName());

//                                sp1.hurt(sp1.damageSources().generic(), 1.0f);
                            }
                    );
                }
                if (skill1.isBothType()) {
                    if (skill1.getPassiveSkillAction1() == null) {
                        skill1.setPassiveSkillAction1(
                                (sp1) -> {
                                    printInGameMsg("Passive SkillID: " + skill1.getSkillID() + ", player: " + sp1.getName());
                                }
                        );
                    }
                }
            }
            //-=-=-=-=-=-=-=-=-=-=-=-=-test ends
            //Method Action init
//            skill1.setActiveSkillAction1( (sp1) -> { ; } );
//            skill1.setActiveSkillAction2( (sp1) -> { ; } );
//            skill1.setActiveSkillAction3( (sp1) -> { ; } );
//            skill1.setPassiveSkillAction1( (sp1) -> { printInGameMsg("unchanged passive action!");; } );
//            skill1.setPassiveSkillAction2( (sp1) -> { ; } );
//            skill1.setPassiveSkillAction3( (sp1) -> { ; } );
//            skill1.setPressAddBtnAction( (sp1) -> { printInGameMsg("unchanged pressed add action!"); } );
//            skill1.setPressSubBtnAction( (sp1) -> { printInGameMsg("unchanged sub action!");; } );
//            skill1.setChannelingSkillAction1( (sp1) -> { ; } );
//            skill1.setClientConditionRequirement(
//                    (SkillID2) -> {
////                        boolean isInCD = getSkillsInCooldownClientTimerOverlayInstance().isClientPlayerSkillInCD(SkillID2);
////                        boolean isntInAction = true;
////                        if (getCPlayerState().containsKey(IS_IN_ACTION)) {
////                            isntInAction = getCPlayerState().get(IS_IN_ACTION) == Constants.ACTION_OFF;
////                        }
////                        return !isInCD && isntInAction;
//                        return DefaultSkillCondition(SkillID2);
//                    }
//            );
            skill1.setClientConditionRequirement(ServerSkillMethods::SBSDefaultSkillCondition);
            skill1.setTranslatableTooltipGetter(ServerSkillMethods::getSkillDefaultDescription);

            SkillDataInitialization.getID2SkillData().put(SkillID, skill1);
//            if (tempCat2IDMap.get(skill1.getRoleCategoryName()) == null) {
            if (tempCat2IDMap.containsKey(CategoryName)) {
                tempCat2IDMap.get(CategoryName).add(SkillID);
            }
            else {
                tempStrList = new ArrayList<String>();
                tempStrList.add(SkillID);
                tempCat2IDMap.put(CategoryName, tempStrList);
            }

//            IconStr2ResLocMap.put(SkillID, getMCResourceLocation(Constants.MOD_ID, skill1.getResLocBtn()));
//            IconStr2ResLocMap.put("DISBTN"+SkillID, getMCResourceLocation(Constants.MOD_ID, skill1.getResLocDisBtn()));
            IconStr2ResLocMap.put(SkillID, getMCResourceLocation(MOD_ID, skill1.getResLocBtn()));
            IconStr2ResLocMap.put(Constants.GRAY_BTN_ICON_PREFIX + SkillID, getMCResourceLocation(MOD_ID, skill1.getResLocDisBtn()));
        }

        //check pre-requisites, setup advanceSkillIDArray if it is not empty
        for (SkillDataJson skill1: skillDataList) {
            SkillID = skill1.getSkillID();
            if (!skill1.getPreRequisiteSkillID().isEmpty()) {
                for (String tempSkillID: skill1.getPreRequisiteSkillID()) {
                    SkillDataInitialization.getID2SkillData().get(tempSkillID).getAdvancedSkillID().add(SkillID);
                }
            }
        }

        SkillDataInitialization.getRole2IconLocMap().put(CategoryName, IconStr2ResLocMap);
    }

    public SkillDataJsonReader() { }

    public void SBSkillJsonMixinHelper() {

    }

    public static void SkillDataJsonInit() {
        ResourceLocation tempResLoc;

        tempResLoc = JsonResouceLocation.SKILLDATA_COMMON;
        setupSkillDataList(readSkillDataJson(tempResLoc, false), Constants.MOD_ID);

        tempResLoc = JsonResouceLocation.SKILLDATA_MC_ATTR;
        setupSkillDataList(readSkillDataJson(tempResLoc, false), Constants.MOD_ID);

        SkillDataJsonReader a = new SkillDataJsonReader();
        a.SBSkillJsonMixinHelper();

//        tempResLoc = JsonResouceLocation.SKILLDATA_ASURA;
//        setupSkillDataList(readSkillDataJson(tempResLoc, false));
//
//        tempResLoc = JsonResouceLocation.SKILLDATA_BERSERKER;
//        setupSkillDataList(readSkillDataJson(tempResLoc, false));
//
//        tempResLoc = JsonResouceLocation.SKILLDATA_BLADEMASTER;
//        setupSkillDataList(readSkillDataJson(tempResLoc, false));
//
//        tempResLoc = JsonResouceLocation.SKILLDATA_SOULBENDER;
//        setupSkillDataList(readSkillDataJson(tempResLoc, false));

    }
}

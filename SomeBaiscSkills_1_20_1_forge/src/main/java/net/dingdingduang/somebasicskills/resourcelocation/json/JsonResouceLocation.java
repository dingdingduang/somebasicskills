package net.dingdingduang.somebasicskills.resourcelocation.json;

import net.dingdingduang.somebasicskills.Constants;
import net.minecraft.resources.ResourceLocation;

import static net.dingdingduang.somebasicskills.globalmethods.GeneralMethods.getMCResourceLocation;

public class JsonResouceLocation {
    public static final ResourceLocation SKILLDATA_COMMON = getMCResourceLocation(Constants.MOD_ID, "skilldata/commonskill.json");
    public static final ResourceLocation SKILLDATA_MC_ATTR = getMCResourceLocation(Constants.MOD_ID, "skilldata/mcdefaultattributeskill.json");

//    public static final ResourceLocation SKILLDATA_ASURA = getMCResourceLocation(Constants.MOD_ID, "skilldata/asuraskill.json");
//    public static final ResourceLocation SKILLDATA_BERSERKER = getMCResourceLocation(Constants.MOD_ID, "skilldata/berserkerskill.json");
//    public static final ResourceLocation SKILLDATA_BLADEMASTER = getMCResourceLocation(Constants.MOD_ID, "skilldata/blademasterskill.json");
//    public static final ResourceLocation SKILLDATA_SOULBENDER = getMCResourceLocation(Constants.MOD_ID, "skilldata/soulbenderskill.json");
}

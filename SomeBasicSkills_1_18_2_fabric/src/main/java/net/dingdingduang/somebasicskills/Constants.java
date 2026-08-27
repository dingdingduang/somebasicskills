package net.dingdingduang.somebasicskills;

public final class Constants {
  public static final String MOD_ID = "somebasicskills";

  public static final String SKILL_TITLE = "TITLE";
  public static final String GRAY_BTN_ICON_PREFIX = "DISBTN";
  public static final String NOTHING = "NOTHING";

  //GUI
  public static final String GUI_SKILLINFO_LEVEL = "somebasicskills.gui.skillinfo.playerlevel";
  public static final String GUI_ROLE_SELECTION_TITLE = "somebasicskills.gui.roleselection.title";


  //Config
  public static final String SB_GENERAL_SETTING = "somebasicskills.gui.general.setting";
  public static final String SB_GENERAL_SETTING_BOOLEAN_YES = "somebasicskills.gui.general.setting.booleanyes";
  public static final String SB_GENERAL_SETTING_BOOLEAN_NO = "somebasicskills.gui.general.setting.booleanno";
  public static final String CONDITION_OPTION_INDEX = SB_GENERAL_SETTING + ".whetherhurtneutrallivingentities";
  public static final String PLAY_SKILL_ERR_SOUND = SB_GENERAL_SETTING + ".playskillerrsound";
  public static final String SB_GENERAL_SETTING_LOCK_ON = SB_GENERAL_SETTING + ".lockon";
  public static final String SB_GENERAL_SETTING_LOCK_ON_RANGE = SB_GENERAL_SETTING + ".lockonrange";

//  public static final String SKILLTREE_TITLE = "somebasicskills.skilltree.title.";
//  public static final String SKILL_TITLE = "somebasicskills.skill.title.";
//  public static final String SKILL_DESCRIPTION = "somebasicskills.skill.description.";

  //player state
  public static final String IS_CHANNELING = "_SBS_IS_CHANNELING";
  public static final String CHANNELING_INTERRUPTED = "_SBS_CHANNELING_FAILED";
  public static final String CHANNELING_TICKS = "_SBS_ChannelingTicks";
//  public static final String IS_CHANNELING_ALLOWED_PRESS_OTHER_KEYS = "_SBS_IS_CHANNELING_ALLOWED_PRESS_OTHER_KEYS";

  public static final String IS_IN_ACTION = "_SBS_IS_IN_ACTION";
  public static final String ACTION_INTERRUPTED = "_SBS_ACTION_INTERRUPTED";
  public static final int ACTION_OFF = 0;
  public static final int ACTION_ON = 1;

  public static final int OP_ADDITION = 0;
  public static final int OP_MULTIPLY_BASE = 1;
  public static final int OP_MULTIPLY_TOTAL = 2;

  //skill
  public static final String IS_BACKSTEPPING = "IS_BACKSTEPPING";
}

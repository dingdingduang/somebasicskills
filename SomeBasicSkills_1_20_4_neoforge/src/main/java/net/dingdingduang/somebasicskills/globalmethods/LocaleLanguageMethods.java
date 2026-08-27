package net.dingdingduang.somebasicskills.globalmethods;

import net.minecraft.locale.Language;

import java.util.IllegalFormatException;
import java.util.Map;

public class LocaleLanguageMethods {
    public static Language getLanguageInstance() {
        return Language.getInstance();
    }

    public static Map<String, String> getLanguageDataMap() {
        return getLanguageInstance().getLanguageData();
    }

    public static String getTranslatableDescriptionWithArgs(String translatableText, Object... args) {
        try {
            return String.format(getLocalizationText(translatableText), args);
        }
        catch (IllegalFormatException e) {
            System.out.println(e.getMessage());
        }
        return getLocalizationText(translatableText);
    }

    public static String getLocalizationText(String translateText) {
        Map<String, String> tempLangDataMap = getLanguageDataMap();
        if (tempLangDataMap.containsKey(translateText)) {
            return getLanguageDataMap().get(translateText);
        }
        else {
//            return "Language File doesn't have the correct translated text.";
            return translateText;
        }
    }
}

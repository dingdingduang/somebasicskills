package net.dingdingduang.somebasicskills.globalmethods;

import net.minecraft.util.Language;

import java.util.IllegalFormatException;

public class LocaleLanguageMethods {
    public static Language getLanguageInstance() {
        return Language.getInstance();
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
        return getLanguageInstance().get(translateText);
    }
}

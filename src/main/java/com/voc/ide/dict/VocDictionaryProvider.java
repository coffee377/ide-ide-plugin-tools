package com.voc.ide.dict;

import com.intellij.spellchecker.dictionary.CustomDictionaryProvider;
import com.intellij.spellchecker.dictionary.Dictionary;
import com.intellij.spellchecker.dictionary.RuntimeDictionaryProvider;
import com.intellij.spellchecker.dictionary.UserDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 15:32
 */
public class VocDictionaryProvider implements CustomDictionaryProvider, RuntimeDictionaryProvider {
    UserDictionary dict = new UserDictionary("voc");

    public VocDictionaryProvider() {
        dict.addToDictionary("Yujie");
        dict.addToDictionary("zhangsan");
        dict.addToDictionary("lisi");
    }

    @Override
    public @Nullable Dictionary get(@NotNull String s) {
        System.out.println(">>>>>>>>>>>> " + s);
        return dict;
    }

    @Override
    public boolean isApplicable(@NotNull String s) {
        System.out.println("--------------- " + s);
        return "yujie".equalsIgnoreCase(s);
    }

    @Override
    public Dictionary[] getDictionaries() {
        return new Dictionary[]{dict};
    }
}

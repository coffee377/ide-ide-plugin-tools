package com.voc.ide.dict;

import com.intellij.spellchecker.dictionary.Dictionary;
import com.intellij.spellchecker.dictionary.RuntimeDictionaryProvider;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 15:32
 */
public class CloudDictionaryProvider implements RuntimeDictionaryProvider {
    CloudDictionary dict = new CloudDictionaryImpl(null, "test");

    public CloudDictionaryProvider() {
        dict.addToDictionary("Yujie");
        dict.addToDictionary("zhangsan");
        dict.addToDictionary("lisi");
        dict.addToDictionary("dingtalk");
    }

    @Override
    public Dictionary[] getDictionaries() {
        return new Dictionary[]{dict};
    }
}

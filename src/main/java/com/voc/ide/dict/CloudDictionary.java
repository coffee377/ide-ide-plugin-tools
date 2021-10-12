package com.voc.ide.dict;

import com.intellij.spellchecker.dictionary.EditableDictionary;
import com.intellij.spellchecker.dictionary.UserDictionary;
import com.intellij.util.containers.CollectionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Set;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 16:13
 * @see UserDictionary
 */
public abstract class CloudDictionary implements EditableDictionary {
    private final String shareCode;
    private final Set<String> words;

    public CloudDictionary(String shareCode) {
        this.shareCode = shareCode;
        this.words = CollectionFactory.createSmallMemoryFootprintSet();
    }

    @Override
    public @NotNull String getName() {
        return "Cloud_" + shareCode;
    }

    @Override
    public @Nullable Boolean contains(@NotNull String word) {
        boolean contains = this.words.contains(word);
        return contains ? true : null;
    }

    @Override
    public @NotNull Set<String> getWords() {
        return this.words;
    }

    @Override
    public void addToDictionary(@Nullable String s) {

    }

    @Override
    public void removeFromDictionary(@Nullable String s) {

    }

    @Override
    public void addToDictionary(@Nullable Collection<String> collection) {

    }

    @Override
    public void replaceAll(@Nullable Collection<String> collection) {

    }

    @Override
    public void clear() {

    }

    @Override
    public @NotNull Set<String> getEditableWords() {
        return null;
    }
}

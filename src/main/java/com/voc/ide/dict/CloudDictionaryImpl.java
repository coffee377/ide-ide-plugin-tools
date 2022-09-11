package com.voc.ide.dict;

import com.intellij.spellchecker.dictionary.UserDictionary;
import com.intellij.util.containers.CollectionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 16:13
 * @see UserDictionary
 */
public class CloudDictionaryImpl implements CloudDictionary {
    public final String token;
    private final String shareCode;
    private final Set<String> words;

    public CloudDictionaryImpl(String token, String shareCode) {
        this.token = token;
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
    public @NotNull Set<String> getEditableWords() {
        if (isOwner(token, shareCode)) {
            return this.words;
        }
        return Collections.emptySet();
    }

    @Override
    public void addToDictionary(@Nullable String s) {
        this.words.add(s);
        // TODO: 2022/9/12 22:28 拥有者存储本地并同步到云端
    }

    @Override
    public void removeFromDictionary(@Nullable String s) {
        this.words.remove(s);
        // TODO: 2022/9/12 22:29 拥有者存储本地并同步到云端
    }

    @Override
    public void addToDictionary(@Nullable Collection<String> collection) {
        // TODO: 2022/9/12 22:29 拥有者存储本地并同步到云端
    }

    @Override
    public void replaceAll(@Nullable Collection<String> collection) {
        this.clear();
        this.addToDictionary(collection);
    }

    @Override
    public void clear() {
        this.words.clear();
        // TODO: 2022/9/12 22:26 清理远程词库
    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public boolean isOwner(@Nullable String token, @NotNull String shareCode) {
        return false;
    }
}

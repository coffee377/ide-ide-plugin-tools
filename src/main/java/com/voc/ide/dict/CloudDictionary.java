package com.voc.ide.dict;

import com.intellij.spellchecker.dictionary.EditableDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 16:32
 */
public interface CloudDictionary extends EditableDictionary {

    /**
     * 云 API令牌
     *
     * @return String
     */
    @Nullable String getToken();

    /**
     * 是否云字典拥有者
     *
     * @param token     当前使用人的令牌
     * @param shareCode 分享码
     * @return boolean
     */
    boolean isOwner(@Nullable String token, @NotNull String shareCode);

}

package com.voc.ide.dict;

import com.intellij.spellchecker.dictionary.EditableDictionary;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 16:32
 */
public interface ICloudDictionary extends EditableDictionary {

    /**
     * 云API令牌
     *
     * @return String
     */
    String getToken();

    /**
     * 是否云字典拥有者
     *
     * @param shareCode 分享码
     * @return boolean
     */
    boolean isOwner(String shareCode);

}

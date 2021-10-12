package com.voc.ide.plugin.tpl;

import com.intellij.lang.Language;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 14:05
 */
public class TplLanguage extends Language {

    public static final TplLanguage INSTANCE = new TplLanguage();

    public TplLanguage() {
        super("TPL");
    }
}

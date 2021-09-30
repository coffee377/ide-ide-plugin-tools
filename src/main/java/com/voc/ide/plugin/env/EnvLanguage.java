package com.voc.ide.plugin.env;

import com.intellij.lang.Language;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/29 17:35
 */
public class EnvLanguage extends Language {

    public static final EnvLanguage INSTANCE = new EnvLanguage();

    public EnvLanguage() {
        super("Env");
    }
}

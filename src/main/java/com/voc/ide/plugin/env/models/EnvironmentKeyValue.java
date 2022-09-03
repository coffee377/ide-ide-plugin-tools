package com.voc.ide.plugin.env.models;

import org.jetbrains.annotations.NotNull;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/03 21:06
 */
public class EnvironmentKeyValue {
    private final String key;
    private final String value;

    public EnvironmentKeyValue(@NotNull String key, @NotNull String value) {
        this.key = key;
        this.value = value;
    }

    @NotNull
    public String getKey() {
        return key;
    }

    @NotNull
    public String getValue() {
        return value;
    }
}

package com.voc.ide.plugin;

import com.intellij.DynamicBundle;
import org.jetbrains.annotations.PropertyKey;

import java.util.function.Supplier;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 09:57
 */
public class DevToolsBundle extends DynamicBundle {
    public static final String DEV_TOOLS_BUNDLE = "messages.DevToolsBundle";
    private static final DevToolsBundle INSTANCE = new DevToolsBundle();

    public DevToolsBundle() {
        super(DEV_TOOLS_BUNDLE);
    }

    public static String message(@PropertyKey(resourceBundle = DEV_TOOLS_BUNDLE) String key, Object... params) {
        return INSTANCE.getMessage(key, params);
    }

    public static Supplier<String> messagePointer(@PropertyKey(resourceBundle = DEV_TOOLS_BUNDLE) String key, Object... params) {
        return INSTANCE.getLazyMessage(key, params);
    }

}

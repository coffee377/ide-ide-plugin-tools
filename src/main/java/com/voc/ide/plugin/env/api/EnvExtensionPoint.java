package com.voc.ide.plugin.env.api;

import com.intellij.openapi.extensions.CustomLoadingExtensionPointBean;
import com.intellij.openapi.extensions.RequiredElement;
import com.intellij.util.KeyedLazyInstance;
import com.intellij.util.xmlb.annotations.Attribute;
import org.jetbrains.annotations.Nullable;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/05 21:12
 */
public abstract class EnvExtensionPoint<T> extends CustomLoadingExtensionPointBean<T> implements KeyedLazyInstance<T> {

    @Attribute("implementationClass")
    @RequiredElement
    public String implementationClass;

    @Override
    protected @Nullable String getImplementationClassName() {
        return implementationClass;
    }

}

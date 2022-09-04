package com.voc.ide.plugin.env.api;

import com.intellij.openapi.extensions.ExtensionPointName;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/05 23:54
 */
public class EnvVarsProviderEP extends EnvExtensionPoint<EnvVarsProvider> {
    ExtensionPointName<EnvVarsProviderEP> EP_NAME =  ExtensionPointName.create("com.voc.ide.plugin.tools.env.vars.provider");
}

package com.voc.ide.plugin.env.util;

import com.voc.ide.plugin.env.api.EnvironmentVariablesProvider;
import com.voc.ide.plugin.env.api.EnvironmentVariablesUsagesProvider;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/11 17:47
 */
public class ProviderUtils {

    public static final EnvironmentVariablesProvider[] PROVIDERS = EnvironmentVariablesProvider.EP_NAME.getExtensions();
    public static final EnvironmentVariablesUsagesProvider[] USAGES_PROVIDERS = EnvironmentVariablesUsagesProvider.EP_NAME.getExtensions();
}

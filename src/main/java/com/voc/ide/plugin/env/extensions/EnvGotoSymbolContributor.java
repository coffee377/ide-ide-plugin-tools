package com.voc.ide.plugin.env.extensions;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.intellij.serviceContainer.BaseKeyedLazyInstance;
import com.voc.ide.plugin.env.api.EnvVarsProvider;
import com.voc.ide.plugin.env.psi.EnvProperty;
import com.voc.ide.plugin.env.psi.impl.EnvPsiImplUtil;
import com.voc.ide.plugin.env.psi.util.EnvUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/04 17:47
 */
public class EnvGotoSymbolContributor implements ChooseByNameContributor {
    @Override
    public String @NotNull [] getNames(Project project, boolean includeNonProjectItems) {
        List<EnvProperty> properties = EnvUtil.findProperties(project);
        return properties.stream().map(EnvPsiImplUtil::getKeyText).toArray(String[]::new);
    }

    @Override
    public NavigationItem @NotNull [] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
        // TODO: include non project items
//        List<EnvVarsProvider> extensions = EnvVariablesProvider.EP_NAME.getExtensionList().stream().map(BaseKeyedLazyInstance::getInstance).collect(Collectors.toList());
        List<EnvProperty> properties = EnvUtil.findProperties(project, name);
        return properties.toArray(new NavigationItem[0]);
    }
}

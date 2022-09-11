package com.voc.ide.plugin.env.api;

import com.intellij.lang.properties.psi.Property;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 11:41
 */
public interface EnvironmentVariablesUsagesProvider {
    ExtensionPointName<EnvironmentVariablesUsagesProvider> EP_NAME = new ExtensionPointName<>("com.voc.ide.plugin.tools.env.vars.usages.provider");

    boolean acceptFile(VirtualFile file);

    @NotNull
    Collection<Property> getUsages(PsiFile psiFile);
}

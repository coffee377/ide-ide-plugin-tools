package com.voc.ide.plugin.env.api;

import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.voc.ide.plugin.env.models.KeyUsagePsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 11:41
 */
public interface EnvVarsUsagesProvider {
    ExtensionPointName<EnvVarsUsagesProvider> EP_NAME = new ExtensionPointName<>("com.voc.ide.plugin.tools.env.vars.usages.provider");

    boolean acceptFile(VirtualFile file);

    @NotNull
    Collection<KeyUsagePsiElement> getUsages(PsiFile psiFile);
}

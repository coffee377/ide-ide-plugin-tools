package com.voc.ide.plugin.env.api;

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
public interface EnvironmentVariablesUsagesProvider {
    boolean acceptFile(VirtualFile file);

    @NotNull
    Collection<KeyUsagePsiElement> getUsages(PsiFile psiFile);
}

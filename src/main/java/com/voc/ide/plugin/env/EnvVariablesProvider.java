package com.voc.ide.plugin.env;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.voc.ide.plugin.env.api.EnvironmentVariablesProvider;
import com.voc.ide.plugin.env.api.FileAcceptResult;
import com.voc.ide.plugin.env.models.KeyValuePsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 11:44
 */
public class EnvVariablesProvider implements EnvironmentVariablesProvider {
    @Override
    public @NotNull FileAcceptResult acceptFile(VirtualFile virtualFile) {
        return null;
    }

    @Override
    public @NotNull Collection<KeyValuePsiElement> getElements(PsiFile psiFile) {
        return null;
    }
}

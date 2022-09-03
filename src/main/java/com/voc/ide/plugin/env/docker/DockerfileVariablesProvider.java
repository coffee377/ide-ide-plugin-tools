package com.voc.ide.plugin.env.docker;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.voc.ide.plugin.env.api.EnvironmentVariablesProvider;
import com.voc.ide.plugin.env.api.FileAcceptResult;
import com.voc.ide.plugin.env.models.KeyValuePsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/03 22:51
 */
public class DockerfileVariablesProvider implements EnvironmentVariablesProvider {
    @Override
    public @NotNull FileAcceptResult acceptFile(VirtualFile virtualFile) {
        return null;
    }

    @Override
    public @NotNull Collection<KeyValuePsiElement> getElements(PsiFile psiFile) {
        return null;
    }
}

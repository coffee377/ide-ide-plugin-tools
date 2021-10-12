package com.voc.ide.plugin.env.api;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.voc.ide.plugin.env.models.KeyValuePsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 11:39
 */
public interface EnvironmentVariablesProvider {
    /**
     * 是否接受文件
     *
     * @param virtualFile VirtualFile
     * @return FileAcceptResult
     */
    @NotNull
    FileAcceptResult acceptFile(VirtualFile virtualFile);

    /**
     * 获取元素
     *
     * @param psiFile PsiFile
     * @return Collection<KeyValuePsiElement>
     */
    @NotNull
    Collection<KeyValuePsiElement> getElements(PsiFile psiFile);
}

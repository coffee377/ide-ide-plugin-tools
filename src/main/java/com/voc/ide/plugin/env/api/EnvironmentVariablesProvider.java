package com.voc.ide.plugin.env.api;

import com.intellij.lang.properties.psi.Property;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 11:39
 */
public interface EnvironmentVariablesProvider {
    ExtensionPointName<EnvironmentVariablesProvider> EP_NAME = ExtensionPointName.create("com.voc.ide.plugin.tools.env.vars.provider");

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
    Collection<Property> getElements(PsiFile psiFile);

    @NotNull
    default Collection<Property> getProperty(PsiFile psiFile) {
        return Collections.emptyList();
    }
}

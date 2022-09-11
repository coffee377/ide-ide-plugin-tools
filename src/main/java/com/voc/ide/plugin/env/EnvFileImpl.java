package com.voc.ide.plugin.env;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.properties.PropertiesLanguage;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 12:10
 */
public class EnvFileImpl extends PsiFileBase {
    protected EnvFileImpl(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, PropertiesLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return EnvFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Env file";
    }

}

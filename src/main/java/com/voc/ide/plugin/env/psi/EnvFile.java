package com.voc.ide.plugin.env.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.voc.ide.plugin.env.EnvFileType;
import com.voc.ide.plugin.env.EnvLanguage;
import org.jetbrains.annotations.NotNull;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 12:10
 */
public class EnvFile extends PsiFileBase {
    protected EnvFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, EnvLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return EnvFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "env file";
    }

}

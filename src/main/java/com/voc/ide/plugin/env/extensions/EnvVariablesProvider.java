package com.voc.ide.plugin.env.extensions;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.voc.ide.plugin.env.EnvFile;
import com.voc.ide.plugin.env.EnvFileType;
import com.voc.ide.plugin.env.api.EnvVarsProvider;
import com.voc.ide.plugin.env.api.FileAcceptResult;
import com.voc.ide.plugin.env.models.KeyValuePsiElement;
import com.voc.ide.plugin.env.psi.EnvPsiElementsVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 11:44
 */
public class EnvVariablesProvider implements EnvVarsProvider {
    @Override
    public @NotNull FileAcceptResult acceptFile(VirtualFile virtualFile) {
        if(!virtualFile.getFileType().equals(EnvFileType.INSTANCE)) {
            return FileAcceptResult.NOT_ACCEPTED;
        }

        // .env.dist , .env.example files are secondary
        return virtualFile.getName().equals(".env") ? FileAcceptResult.ACCEPTED : FileAcceptResult.ACCEPTED_SECONDARY;
    }

    @Override
    public @NotNull Collection<KeyValuePsiElement> getElements(PsiFile psiFile) {
        if(psiFile instanceof EnvFile) {
            EnvPsiElementsVisitor visitor = new EnvPsiElementsVisitor();
            psiFile.acceptChildren(visitor);

            return visitor.getCollectedItems();
        }

        return Collections.emptyList();
    }
}

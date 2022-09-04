package com.voc.ide.plugin.env.docker;

import com.intellij.docker.dockerFile.DockerFileType;
import com.intellij.docker.dockerFile.DockerPsiFile;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.voc.ide.plugin.env.api.EnvVarsProvider;
import com.voc.ide.plugin.env.api.FileAcceptResult;
import com.voc.ide.plugin.env.models.KeyValuePsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/03 22:51
 */
public class DockerfileVariablesProvider implements EnvVarsProvider {
    @Override
    public @NotNull FileAcceptResult acceptFile(VirtualFile file) {
        return file.getFileType().equals(DockerFileType.DOCKER_FILE_TYPE) ? FileAcceptResult.ACCEPTED : FileAcceptResult.NOT_ACCEPTED;
    }

    @Override
    public @NotNull Collection<KeyValuePsiElement> getElements(PsiFile psiFile) {
        if(psiFile instanceof DockerPsiFile) {
            DockerfilePsiElementsVisitor visitor = new DockerfilePsiElementsVisitor();
            psiFile.acceptChildren(visitor);
            return visitor.getCollectedItems();
        }
        return Collections.emptyList();
    }
}

package com.voc.ide.plugin.env.docker;

import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler;
import com.intellij.docker.dockerFile.parser.psi.DockerFileEnvRegularDeclaration;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.voc.ide.plugin.env.api.EnvironmentVariablesApi;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/03 22:52
 */
public class DockerfileKeyGotoHandler implements GotoDeclarationHandler {
    @Override
    public PsiElement @Nullable [] getGotoDeclarationTargets(@Nullable PsiElement psiElement, int i, Editor editor) {
        if (psiElement == null || psiElement.getParent() == null) {
            return PsiElement.EMPTY_ARRAY;
        }

        if (!psiElement.getContainingFile().getName().equals("Dockerfile")) {
            return PsiElement.EMPTY_ARRAY;
        }

        psiElement = psiElement.getParent();

        if (!(psiElement instanceof DockerFileEnvRegularDeclaration)) {
            return PsiElement.EMPTY_ARRAY;
        }

        return PsiElement.EMPTY_ARRAY;
//        return EnvironmentVariablesApi.getKeyUsages(psiElement.getProject(), EnvironmentVariablesUtil.getKeyFromString((((DockerFileEnvRegularDeclaration) psiElement).getDeclaredName().getText())));

    }

}

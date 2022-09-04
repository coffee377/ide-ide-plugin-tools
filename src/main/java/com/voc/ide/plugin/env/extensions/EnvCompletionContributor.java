package com.voc.ide.plugin.env.extensions;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.editor.Editor;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import com.voc.ide.plugin.env.psi.EnvTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/04 13:44
 */
public class EnvCompletionContributor extends BaseEnvCompletionProvider {

    public EnvCompletionContributor() {
        this.extend(CompletionType.SMART, PlatformPatterns.psiElement(EnvTypes.VALUE_CHARS),
                new CompletionProvider<>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters,
                                          @NotNull ProcessingContext context,
                                          @NotNull CompletionResultSet resultSet) {
                PsiElement psiElement = parameters.getOriginalPosition();
                if (psiElement == null) {
                    return;
                }
                fillCompletionResultSet(resultSet, psiElement.getProject());
            }
        });
    }

    @Override
    public PsiElement @Nullable [] getGotoDeclarationTargets(@Nullable PsiElement psiElement, int i, Editor editor) {
        return new PsiElement[0];
    }
}

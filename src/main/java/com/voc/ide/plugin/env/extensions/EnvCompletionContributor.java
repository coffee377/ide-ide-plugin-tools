package com.voc.ide.plugin.env.extensions;

import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/04 13:44
 */
public class EnvCompletionContributor extends BaseEnvCompletionProvider {

    public EnvCompletionContributor() {
//        this.extend(CompletionType.SMART, PlatformPatterns.psiElement(EnvTypes.VALUE_CHARS),
//                new CompletionProvider<>() {
//            @Override
//            protected void addCompletions(@NotNull CompletionParameters parameters,
//                                          @NotNull ProcessingContext context,
//                                          @NotNull CompletionResultSet resultSet) {
//                PsiElement psiElement = parameters.getOriginalPosition();
//                if (psiElement == null) {
//                    return;
//                }
//                fillCompletionResultSet(resultSet, psiElement.getProject());
//            }
//        });
    }

    @Override
    public PsiElement @Nullable [] getGotoDeclarationTargets(@Nullable PsiElement psiElement, int i, Editor editor) {
        return new PsiElement[0];
    }
}

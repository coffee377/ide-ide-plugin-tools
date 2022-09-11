package com.voc.ide.javadoc;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PsiJavaPatterns;
import com.intellij.psi.JavaDocTokenType;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/11 23:11
 */
public class CustomJavaDocCompletionContributor extends CompletionContributor {

    public CustomJavaDocCompletionContributor() {
        this.extend(CompletionType.BASIC, PsiJavaPatterns.psiElement(JavaDocTokenType.DOC_TAG_NAME), new CompletionProvider<>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
                result.addElement(LookupElementBuilder.create("email"));
                result.addElement(LookupElementBuilder.create("time"));
                result.addElement(LookupElementBuilder.create("date"));
            }
        });
    }
}

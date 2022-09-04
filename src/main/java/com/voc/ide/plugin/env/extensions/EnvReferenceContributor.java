package com.voc.ide.plugin.env.extensions;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import com.voc.ide.plugin.DevToolsIcons;
import com.voc.ide.plugin.env.psi.EnvProperty;
import com.voc.ide.plugin.env.psi.util.EnvUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.voc.ide.plugin.env.extensions.EnvAnnotator.ENV_PREFIX_STR;
import static com.voc.ide.plugin.env.extensions.EnvAnnotator.ENV_SEPARATOR_STR;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/04 14:25
 */
public class EnvReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(PsiLiteralExpression.class),
                new PsiReferenceProvider() {
                    @Override
                    public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element,
                                                                           @NotNull ProcessingContext context) {
                        PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
                        String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
                        if ((value != null && value.startsWith(ENV_PREFIX_STR + ENV_SEPARATOR_STR))) {
                            TextRange property = new TextRange(ENV_PREFIX_STR.length() + ENV_SEPARATOR_STR.length() + 1, value.length() + 1);
                            return new PsiReference[]{new EnvReference(element, property)};
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                });
    }
    
    static class EnvReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

        private final String key;

        public EnvReference(@NotNull PsiElement element, TextRange textRange) {
            super(element, textRange);
            key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
        }

        @Override
        public ResolveResult @NotNull [] multiResolve(boolean b) {
            Project project = myElement.getProject();
            final List<EnvProperty> properties = EnvUtil.findProperties(project, key);
            List<ResolveResult> results = new ArrayList<>();
            for (EnvProperty property : properties) {
                results.add(new PsiElementResolveResult(property));
            }
            return results.toArray(new ResolveResult[0]);
        }

        @Override
        public @Nullable PsiElement resolve() {
            ResolveResult[] resolveResults = multiResolve(false);
            return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
        }

        @Override
        public Object @NotNull [] getVariants() {
            Project project = myElement.getProject();
            List<EnvProperty> properties = EnvUtil.findProperties(project);
            List<LookupElement> variants = new ArrayList<>();
            for (final EnvProperty property : properties) {
                variants.add(LookupElementBuilder
                        .create(property).withIcon(DevToolsIcons.ENV_FILE_REFERENCE)
                        .withTypeText(property.getContainingFile().getName())
                );
            }
            return variants.toArray();
        }
    }
}

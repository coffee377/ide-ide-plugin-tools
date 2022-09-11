package com.voc.ide.plugin.env;

import com.google.common.collect.Lists;
import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.lang.documentation.DocumentationMarkup;
import com.intellij.lang.properties.psi.Property;
import com.intellij.lang.properties.psi.impl.PropertyKeyImpl;
import com.intellij.lang.properties.psi.impl.PropertyValueImpl;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.presentation.java.SymbolPresentationUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/13 00:23
 */
public class EnvDocumentationProvider extends AbstractDocumentationProvider {
    @Override
    public @Nullable @Nls String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
        if (element instanceof PropertyKeyImpl || element instanceof PropertyValueImpl) {
            Property parent = (Property) element.getParent();
            final String key = parent.getKey();
            final String file = SymbolPresentationUtil.getFilePathPresentation(element.getContainingFile());
            return "\"" + key + "\" in " + file;
        }
        return null;
    }

    @Override
    public @Nullable @Nls String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {
        if (element instanceof PropertyKeyImpl || element instanceof PropertyValueImpl) {
            Property parent = (Property) element.getParent();
            final String key = parent.getKey();
            final String value = parent.getValue();
            final String file = SymbolPresentationUtil.getFilePathPresentation(element.getContainingFile());
            final String docComment = findDocumentationComment(parent);
            return renderFullDoc(key, value, file, docComment);
        }
        return null;
    }

    public @NotNull String findDocumentationComment(Property property) {
        List<String> result = new LinkedList<>();
        PsiElement element = property.getPrevSibling();
        while (element instanceof PsiComment || element instanceof PsiWhiteSpace) {
            if (element instanceof PsiComment) {
                String commentText = element.getText().replaceFirst("[!# ]+", "");
                result.add(commentText);
            }
            element = element.getPrevSibling();
        }
        return StringUtil.join(Lists.reverse(result),"\n ");
    }


    /**
     * Creates a key/value row for the rendered documentation.
     */
    private void addKeyValueSection(String key, String value, StringBuilder sb) {
        sb.append(DocumentationMarkup.SECTION_HEADER_START);
        sb.append(key);
        sb.append(DocumentationMarkup.SECTION_SEPARATOR);
        sb.append("<p>");
        sb.append(value);
        sb.append(DocumentationMarkup.SECTION_END);
    }

    /**
     * Creates the formatted documentation using {@link DocumentationMarkup}. See the Java doc of
     * {@link com.intellij.lang.documentation.DocumentationProvider#generateDoc(PsiElement, PsiElement)} for more
     * information about building the layout.
     */
    private String renderFullDoc(String key, String value, String file, String docComment) {
        StringBuilder sb = new StringBuilder();
        sb.append(DocumentationMarkup.DEFINITION_START);
        sb.append("Env Property");
        sb.append(DocumentationMarkup.DEFINITION_END);
        sb.append(DocumentationMarkup.CONTENT_START);
        sb.append(value);
        sb.append(DocumentationMarkup.CONTENT_END);
        sb.append(DocumentationMarkup.SECTIONS_START);
        addKeyValueSection("Key:", key, sb);
        addKeyValueSection("Value:", value, sb);
        addKeyValueSection("File:", file, sb);
        addKeyValueSection("Comment:", docComment, sb);
        sb.append(DocumentationMarkup.SECTIONS_END);
        return sb.toString();
    }

}

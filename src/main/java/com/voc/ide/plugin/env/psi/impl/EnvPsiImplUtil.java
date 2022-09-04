package com.voc.ide.plugin.env.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.TokenSet;
import com.voc.ide.plugin.DevToolsIcons;
import com.voc.ide.plugin.env.psi.EnvElementFactory;
import com.voc.ide.plugin.env.psi.EnvProperty;
import com.voc.ide.plugin.env.psi.EnvTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/04 11:01
 */
public class EnvPsiImplUtil {

    public static String getKeyText(EnvProperty element) {
        return element.getKey().getText().replaceAll("\\\\ ", " ");
    }

    public static String getValueText(EnvProperty element) {
        ASTNode valueNode = element.getNode().findChildByType(EnvTypes.VALUE);
        if (valueNode != null) {
            return Arrays.stream(valueNode.getChildren(TokenSet.create(EnvTypes.VALUE_CHARS)))
                    .map(ASTNode::getText)
                    .collect(Collectors.joining(""));
        } else {
            return "";
        }
    }

    public static String getName(EnvProperty element) {
        return getKeyText(element);
    }

    public static PsiElement setName(EnvProperty element, @NotNull String newName) {
        ASTNode keyNode = element.getNode().findChildByType(EnvTypes.KEY);
        if (keyNode != null) {
            EnvProperty property = EnvElementFactory.createProperty(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(EnvProperty property) {
        return property.getKey();
    }

    public static ItemPresentation getPresentation(final EnvProperty element) {
        return new ItemPresentation() {
            @Override
            public @NlsSafe @Nullable String getPresentableText() {
                return element.getKeyText();
            }

            @Override
            public @Nullable Icon getIcon(boolean unused) {
                return DevToolsIcons.ENV_FILE_REFERENCE;
            }

            @Override
            public @NlsSafe @Nullable String getLocationString() {
                PsiFile containingFile = element.getContainingFile();
                return containingFile == null ? null : containingFile.getName();
            }
        };
    }

}

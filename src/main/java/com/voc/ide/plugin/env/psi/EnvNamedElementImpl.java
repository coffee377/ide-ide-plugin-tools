package com.voc.ide.plugin.env.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 12:17
 */
public class EnvNamedElementImpl extends ASTWrapperPsiElement implements EnvNamedElement {

    public EnvNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @Nullable PsiElement getNameIdentifier() {
        return null;
    }

    @Override
    public PsiElement setName(@NlsSafe @NotNull String s) throws IncorrectOperationException {
        return null;
    }
}

package com.voc.ide.plugin.env.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.voc.ide.plugin.env.psi.EnvNamedElement;
import org.jetbrains.annotations.NotNull;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/04 11:26
 */
public abstract class EnvNamedElementImpl extends ASTWrapperPsiElement implements EnvNamedElement {

    public EnvNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

}

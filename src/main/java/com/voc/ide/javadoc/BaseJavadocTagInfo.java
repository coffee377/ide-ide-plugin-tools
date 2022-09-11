package com.voc.ide.javadoc;

import com.intellij.psi.*;
import com.intellij.psi.javadoc.JavadocTagInfo;
import com.intellij.psi.javadoc.PsiDocTagValue;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/11 21:24
 */
public abstract class BaseJavadocTagInfo implements JavadocTagInfo {
    private final String name;
    private final boolean inline;

    public BaseJavadocTagInfo(String name, boolean inline) {
        this.name = name;
        this.inline = inline;
    }

    public BaseJavadocTagInfo(String name) {
        this(name, true);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isInline() {
        return inline;
    }

    @Override
    public boolean isValidInContext(PsiElement psiElement) {
        return Stream.of(PsiClass.class, PsiPackage.class, PsiMethod.class, PsiJavaModule.class).anyMatch(clazz -> clazz.isInstance(psiElement));
    }

    @Override
    public @Nullable @Nls String checkTagValue(PsiDocTagValue psiDocTagValue) {
        return null;
    }

    @Override
    public @Nullable PsiReference getReference(PsiDocTagValue psiDocTagValue) {
        return null;
    }
}

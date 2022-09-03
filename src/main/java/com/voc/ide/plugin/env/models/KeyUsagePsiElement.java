package com.voc.ide.plugin.env.models;

import com.intellij.psi.PsiElement;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/03 21:01
 */
public class KeyUsagePsiElement {
    private final String key;
    private final PsiElement element;

    public KeyUsagePsiElement(String key, PsiElement element) {
        this.key = key.trim();
        this.element = element;
    }

    public String getKey() {
        return key;
    }

    public PsiElement getElement() {
        return element;
    }
}

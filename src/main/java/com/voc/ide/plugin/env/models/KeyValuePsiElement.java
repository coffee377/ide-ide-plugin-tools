package com.voc.ide.plugin.env.models;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 11:51
 */
public class KeyValuePsiElement {
    private final String key;
    private final String value;
    private final PsiElement element;

    public KeyValuePsiElement(String key, String value, PsiElement element) {
        this.key = key;
        this.value = value;
        this.element = element;
    }

    public String getKey() {
        return this.key.trim();
    }

    public String getShortValue() {
        if (this.value.indexOf(10) != -1) {
            String var10000 = this.clearString(this.value.substring(0, this.value.indexOf(10)));
            return var10000 + "...";
        } else {
            return this.value.trim();
        }
    }

    private String clearString(String s) {
        return StringUtil.trim(s.trim(), (ch) -> ch != '\\').trim();
    }

    public PsiElement getElement() {
        return this.element;
    }
}

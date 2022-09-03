package com.voc.ide.plugin.env.models;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 11:51
 */
public class KeyValuePsiElement extends KeyUsagePsiElement {
    private final String value;


    public KeyValuePsiElement(String key, String value, PsiElement element) {
        super(key, element);
        this.value = value;
    }

    public String getShortValue() {
        if (value.indexOf('\n') != -1) {
            return clearString(value.substring(0, value.indexOf('\n'))) + "...";
        }

        return value.trim();
    }

    private String clearString(String s) {
        return StringUtil.trim(s.trim(), (ch) -> ch != '\\').trim();
    }

}

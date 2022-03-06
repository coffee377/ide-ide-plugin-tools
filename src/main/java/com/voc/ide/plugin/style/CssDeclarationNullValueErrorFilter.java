package com.voc.ide.plugin.style;

import com.intellij.codeInsight.highlighting.HighlightErrorFilter;
import com.intellij.lang.Language;
import com.intellij.lang.css.CSSLanguage;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.css.CssDeclaration;
import com.intellij.psi.css.CssTermList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.less.LESSLanguage;
import org.jetbrains.plugins.scss.SCSSLanguage;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/03/04 14:36
 */
public class CssDeclarationNullValueErrorFilter extends HighlightErrorFilter {
    /**
     * 抑制自定义属性空值错误
     */
    private final boolean suppressCustomPropertyNullValue;
    /**
     * 抑制原生属性空值错误
     */
    private final boolean suppressNativePropertyNullValue;

    public CssDeclarationNullValueErrorFilter() {
        this(true, true);
    }

    public CssDeclarationNullValueErrorFilter(boolean suppressCustomPropertyNullValue, boolean suppressNativePropertyNullValue) {
        this.suppressCustomPropertyNullValue = suppressCustomPropertyNullValue;
        this.suppressNativePropertyNullValue = suppressNativePropertyNullValue;
    }

    @Override
    public boolean shouldHighlightErrorElement(@NotNull PsiErrorElement psiErrorElement) {
        CssDeclaration cssDeclaration = getCssDeclaration(psiErrorElement);
        if (cssDeclaration != null) {
            if (suppressCustomPropertyNullValue && suppressNativePropertyNullValue) {
                return false;
            } else if (isCustomVariableNullValue(cssDeclaration)) {
                return !suppressCustomPropertyNullValue;
            } else {
                return !suppressNativePropertyNullValue;
            }
        }
        return true;
    }

    /**
     * 是否自定义属性
     *
     * @param cssDeclaration CssDeclaration
     * @return false
     */
    public boolean isCustomVariableNullValue(CssDeclaration cssDeclaration) {
        if (cssDeclaration != null) {
            return cssDeclaration.isCustomProperty();
        }
        return false;
    }

    protected CssDeclaration getCssDeclaration(@NotNull PsiErrorElement element) {
        PsiElement context = element.getContext();
        Language language = element.getLanguage();
        boolean support = language.equals(CSSLanguage.INSTANCE) || language.equals(LESSLanguage.INSTANCE)
                || language.equals(SCSSLanguage.INSTANCE);
        if (context instanceof CssTermList && support) {
            PsiElement cssDeclaration = context.getContext();
            if (cssDeclaration instanceof CssDeclaration) {
                return ((CssDeclaration) cssDeclaration);
            }
        }

        return null;
    }


}

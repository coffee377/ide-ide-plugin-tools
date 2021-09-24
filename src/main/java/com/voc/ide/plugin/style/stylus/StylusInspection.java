package com.voc.ide.plugin.style.stylus;

import com.intellij.psi.PsiElement;
import com.intellij.psi.css.inspections.CssApiBaseInspection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.stylus.StylusCssInspectionFilter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/24 15:53
 */
public class StylusInspection extends StylusCssInspectionFilter {
    @Override
    public boolean isSupported(@NotNull Class<? extends CssApiBaseInspection> clazz, @NotNull PsiElement context) {
        return true;
    }
}

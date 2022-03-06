package com.voc.ide.plugin.style.stylus;

import com.intellij.psi.PsiElement;
import com.intellij.psi.css.CssDeclaration;
import com.intellij.psi.css.inspections.CssApiBaseInspection;
import com.intellij.psi.css.inspections.invalid.CssInvalidPropertyValueInspection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.stylus.StylusCssInspectionFilter;

import static com.voc.ide.plugin.style.IMiniProgramUnitInspection.MINI_PROGRAM_UNIT_PATTERN;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/24 15:53
 */
public class StylusInspection extends StylusCssInspectionFilter {
    @Override
    public boolean isSupported(@NotNull Class<? extends CssApiBaseInspection> clazz, @NotNull PsiElement psiElement) {
        if (clazz.isAssignableFrom(CssInvalidPropertyValueInspection.class) && psiElement instanceof CssDeclaration) {
            return !MINI_PROGRAM_UNIT_PATTERN.matcher(psiElement.getText()).find();
        }
        return super.isSupported(clazz, psiElement);
    }
}

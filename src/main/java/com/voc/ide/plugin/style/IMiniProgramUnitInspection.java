package com.voc.ide.plugin.style;

import com.intellij.psi.PsiElement;
import com.intellij.psi.css.CssDeclaration;
import com.intellij.psi.css.CssTerm;
import com.intellij.psi.css.CssTermList;
import com.intellij.psi.css.impl.CssTermTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/24 15:24
 */
public interface IMiniProgramUnitInspection {
    Pattern MINI_PROGRAM_UNIT_PATTERN = Pattern.compile("[ru]px", Pattern.CASE_INSENSITIVE);

    /**
     * 小程序像素单位检查
     *
     * @param element PsiElement
     * @param toolId  String
     * @return boolean
     */
    default boolean withMiniProgramUnit(@NotNull PsiElement element, @NotNull String toolId) {
        if ("CssInvalidPropertyValue".equals(toolId) && element instanceof CssDeclaration) {
            CssTermList cssTermList = ((CssDeclaration) element).getValue();
            return Arrays.stream(cssTermList != null ? cssTermList.getTerms() : new CssTerm[0])
                    .anyMatch(cssTerm -> CssTermTypes.NUMBER_WITH_UNKNOWN_UNIT.equals(cssTerm.getTermType())
                            && MINI_PROGRAM_UNIT_PATTERN.matcher(cssTerm.getText()).find());
        }
        return false;
    }
}

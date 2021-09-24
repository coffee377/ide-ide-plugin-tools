package com.voc.ide.plugin.style;

import com.intellij.psi.PsiElement;
import com.intellij.psi.css.CssDeclaration;
import com.intellij.psi.css.CssTerm;
import com.intellij.psi.css.impl.CssTermTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/24 15:24
 */
public interface INumberUnitInspection {

    /**
     * @param element PsiElement
     * @param toolId  String
     * @return boolean
     */
    default boolean withRpxOrUpxUnit(@NotNull PsiElement element, @NotNull String toolId) {
        if ("CssInvalidPropertyValue".equals(toolId) && element instanceof CssDeclaration) {
            com.intellij.psi.css.CssTermList cssTermList = ((CssDeclaration) element).getValue();
            return Arrays.stream(cssTermList != null ? cssTermList.getTerms() : new CssTerm[0])
                    .anyMatch(cssTerm -> CssTermTypes.NUMBER_WITH_UNKNOWN_UNIT.equals(cssTerm.getTermType())
                            && StyleTermListBlock.RPX_OR_UPX_UNIT_PATTERN.matcher(cssTerm.getText()).matches());
        }
        return false;
    }
}

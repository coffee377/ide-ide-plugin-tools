package com.voc.ide.plugin.style.sass;

import com.intellij.psi.PsiElement;
import com.voc.ide.plugin.style.INumberUnitInspection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.less.inspections.suppress.LessInspectionSuppressor;
import org.jetbrains.plugins.scss.inspections.suppress.SassScssInspectionSuppressor;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/23 17:22
 */
public class SassInspection extends SassScssInspectionSuppressor implements INumberUnitInspection {

    @Override
    public boolean isSuppressedFor(@NotNull PsiElement element, @NotNull String toolId) {
        if (this.withRpxOrUpxUnit(element, toolId)) {
            return true;
        }
        return super.isSuppressedFor(element, toolId);
    }

}

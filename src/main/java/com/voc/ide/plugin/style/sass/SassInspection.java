package com.voc.ide.plugin.style.sass;

import com.intellij.psi.PsiElement;
import com.voc.ide.plugin.style.IMiniProgramUnitInspection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.scss.inspections.suppress.SassScssInspectionSuppressor;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/23 17:22
 */
public class SassInspection extends SassScssInspectionSuppressor implements IMiniProgramUnitInspection {

    @Override
    public boolean isSuppressedFor(@NotNull PsiElement element, @NotNull String toolId) {
        if (this.withMiniProgramUnit(element, toolId)) {
            return true;
        }
        return super.isSuppressedFor(element, toolId);
    }

}

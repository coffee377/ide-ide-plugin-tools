package com.voc.ide.plugin.env.psi;

import com.intellij.psi.tree.IElementType;
import com.voc.ide.plugin.env.EnvLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 12:08
 */
public class EnvElementType extends IElementType {
    public EnvElementType(@NonNls @NotNull String debugName) {
        super(debugName, EnvLanguage.INSTANCE);
    }
}

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
public class EnvTokenType extends IElementType {
    public EnvTokenType(@NonNls @NotNull String debugName) {
        super(debugName, EnvLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "EnvTokenType." + super.toString();
    }
}

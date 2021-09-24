package com.voc.ide.plugin.style.less;

import com.intellij.formatting.Alignment;
import com.intellij.formatting.Indent;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.voc.ide.plugin.style.StyleTermListBlock;
import org.jetbrains.plugins.less.LESSLanguage;
import org.jetbrains.plugins.less.formatter.LessFormattingModelBuilder;
import org.jetbrains.plugins.less.settings.LessCodeStyleSettings;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/24 09:37
 */
public class LessFormatter extends LessFormattingModelBuilder {

    @Override
    protected LessFormattingExtension createExtension(CodeStyleSettings settings) {
        CommonCodeStyleSettings commonSettings = settings.getCommonSettings(LESSLanguage.INSTANCE);
        LessCodeStyleSettings customSettings = settings.getCustomSettings(LessCodeStyleSettings.class);
        return new LessFormattingExtension(commonSettings, customSettings) {
            @Override
            public CssTermListBlock createTermListBlock(ASTNode _node, Indent indent, Alignment alignment, boolean shouldIndentContent) {
                return new StyleTermListBlock(_node, indent, this, alignment, shouldIndentContent);
            }
        };
    }
}

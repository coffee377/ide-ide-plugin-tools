package com.voc.ide.plugin.style.stylus;

import com.intellij.formatting.Alignment;
import com.intellij.formatting.Indent;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.voc.ide.plugin.style.StyleTermListBlock;
import org.jetbrains.plugins.stylus.StylusLanguage;
import org.jetbrains.plugins.stylus.formatter.StylusFormattingModelBuilder;
import org.jetbrains.plugins.stylus.settings.StylusCodeStyleSettings;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/24 15:50
 */
public class StylusFormatter extends StylusFormattingModelBuilder {
    @Override
    protected StylusFormattingExtension createExtension(CodeStyleSettings settings) {
        CommonCodeStyleSettings commonSettings = settings.getCommonSettings(StylusLanguage.INSTANCE);
        StylusCodeStyleSettings customSettings = settings.getCustomSettings(StylusCodeStyleSettings.class);
        return new StylusFormattingExtension(commonSettings, customSettings) {
            @Override
            public CssTermListBlock createTermListBlock(ASTNode node, Indent indent, Alignment alignment, boolean shouldIndentContent) {
                return new StyleTermListBlock(node, indent, this, alignment, shouldIndentContent);
            }
        };
    }
}

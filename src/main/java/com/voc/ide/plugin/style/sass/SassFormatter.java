package com.voc.ide.plugin.style.sass;

import com.intellij.formatting.Alignment;
import com.intellij.formatting.Indent;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.voc.ide.plugin.style.StyleTermListBlock;
import org.jetbrains.plugins.sass.SASSLanguage;
import org.jetbrains.plugins.sass.formatter.SassFormattingModelBuilder;
import org.jetbrains.plugins.sass.settings.SassCodeStyleSettings;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/24 15:34
 */
public class SassFormatter extends SassFormattingModelBuilder {

    @Override
    protected SassFormattingExtension createExtension(CodeStyleSettings settings) {
        CommonCodeStyleSettings commonSettings = settings.getCommonSettings(SASSLanguage.INSTANCE);
        SassCodeStyleSettings customSettings = settings.getCustomSettings(SassCodeStyleSettings.class);
        return new SassFormattingExtension(commonSettings, customSettings) {
            @Override
            public CssTermListBlock createTermListBlock(ASTNode _node, Indent indent, Alignment alignment, boolean shouldIndentContent) {
                return new StyleTermListBlock(_node, indent, this, alignment, shouldIndentContent);
            }
        };
    }
}

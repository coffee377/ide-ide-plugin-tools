package com.voc.ide.plugin.style.scss;

import com.intellij.formatting.Alignment;
import com.intellij.formatting.Indent;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.voc.ide.plugin.style.StyleTermListBlock;
import org.jetbrains.plugins.scss.SCSSLanguage;
import org.jetbrains.plugins.scss.formatter.SCSSFormattingModelBuilder;
import org.jetbrains.plugins.scss.settings.ScssCodeStyleSettings;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/24 16:13
 */
public class ScssFormatter extends SCSSFormattingModelBuilder {

    @Override
    protected ScssFormattingExtension createExtension(CodeStyleSettings settings) {
        CommonCodeStyleSettings commonSettings = settings.getCommonSettings(SCSSLanguage.INSTANCE);
        ScssCodeStyleSettings customSettings = settings.getCustomSettings(ScssCodeStyleSettings.class);
        return new ScssFormattingExtension(commonSettings, customSettings) {
                    @Override
            public CssTermListBlock createTermListBlock(ASTNode _node, Indent indent, Alignment alignment, boolean shouldIndentContent) {
                return new StyleTermListBlock(_node, indent, this, alignment, shouldIndentContent);
            }
        };
    }
}

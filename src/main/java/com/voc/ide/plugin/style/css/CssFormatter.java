package com.voc.ide.plugin.style.css;

import com.intellij.formatting.Alignment;
import com.intellij.formatting.Indent;
import com.intellij.lang.ASTNode;
import com.intellij.lang.css.CSSLanguage;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.css.codeStyle.CssCodeStyleSettings;
import com.intellij.psi.css.impl.util.editor.CssFormattingModelBuilder;
import com.voc.ide.plugin.style.StyleTermListBlock;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/22 15:41
 */
public class CssFormatter extends CssFormattingModelBuilder {

    @Override
    protected CssFormattingExtension createExtension(CodeStyleSettings settings) {
        CommonCodeStyleSettings commonSettings = settings.getCommonSettings(CSSLanguage.INSTANCE);
        CssCodeStyleSettings customSettings = settings.getCustomSettings(CssCodeStyleSettings.class);
        return new CssFormattingExtension(commonSettings, customSettings) {
            @Override
            public CssTermListBlock createTermListBlock(ASTNode _node, Indent indent, Alignment alignment, boolean shouldIndentContent) {
                return new StyleTermListBlock(_node, indent, this, alignment, shouldIndentContent);
            }
        };
    }
}

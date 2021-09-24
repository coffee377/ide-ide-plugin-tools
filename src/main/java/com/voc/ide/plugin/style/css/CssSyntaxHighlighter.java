package com.voc.ide.plugin.style.css;

import com.intellij.lexer.Lexer;
import com.intellij.psi.css.impl.util.CssHighlighter;
import com.intellij.psi.css.impl.util.CssHighlighterLexer;
import com.voc.ide.plugin.style.StyleUtils;
import org.jetbrains.annotations.NotNull;

/**
 * 样式高亮支持小程序单位(rpx/upx)
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/23 16:01
 */
public class CssSyntaxHighlighter extends CssHighlighter {

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new CssHighlighterLexer(StyleUtils.valueIdentifiersWithRpxAndUpx());
    }

}

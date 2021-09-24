package com.voc.ide.plugin.style.scss;

import com.intellij.lexer.Lexer;
import com.intellij.psi.css.impl.util.scheme.CssElementDescriptorFactory2;
import com.voc.ide.plugin.style.StyleUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.scss.highlighting.SCSSSyntaxHighlighter;
import org.jetbrains.plugins.scss.lexer.SCSSHighlightingLexer;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/24 16:10
 */
public class ScssSyntaxHighlighter extends SCSSSyntaxHighlighter {

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new SCSSHighlightingLexer(StyleUtils.valueIdentifiersWithRpxAndUpx());
    }

}

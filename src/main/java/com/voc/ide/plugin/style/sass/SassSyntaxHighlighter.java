package com.voc.ide.plugin.style.sass;

import com.intellij.lexer.Lexer;
import com.intellij.psi.css.impl.util.scheme.CssElementDescriptorFactory2;
import com.voc.ide.plugin.style.StyleUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.less.highlighting.LESSSyntaxHighlighter;
import org.jetbrains.plugins.less.lexer.LESSHighlightingLexer;
import org.jetbrains.plugins.sass.highlighting.SASSSyntaxHighlighter;
import org.jetbrains.plugins.sass.lexer.SASSHighlighterLexer;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/24 14:45
 */
public class SassSyntaxHighlighter extends SASSSyntaxHighlighter {

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new SASSHighlighterLexer(CssElementDescriptorFactory2.getInstance().getValueIdentifiers());
//        return new SASSHighlighterLexer(StyleUtils.valueIdentifiersWithRpxAndUpx());
    }
}

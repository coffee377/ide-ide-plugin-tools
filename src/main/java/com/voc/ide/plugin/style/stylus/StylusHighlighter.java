package com.voc.ide.plugin.style.stylus;

import com.intellij.lexer.Lexer;
import com.voc.ide.plugin.style.StyleUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.stylus.StylusSyntaxHighlighter;
import org.jetbrains.plugins.stylus.highlighting.StylusHighlighterLexer;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/24 15:47
 */
public class StylusHighlighter extends StylusSyntaxHighlighter {

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new StylusHighlighterLexer(StyleUtils.valueIdentifiersWithRpxAndUpx());
    }

}

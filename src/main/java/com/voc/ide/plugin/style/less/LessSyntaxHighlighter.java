package com.voc.ide.plugin.style.less;

import com.intellij.lexer.Lexer;
import com.voc.ide.plugin.style.StyleUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.less.highlighting.LESSSyntaxHighlighter;
import org.jetbrains.plugins.less.lexer.LESSHighlightingLexer;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/24 14:45
 */
public class LessSyntaxHighlighter extends LESSSyntaxHighlighter {

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new LESSHighlightingLexer(StyleUtils.valueIdentifiersWithRpxAndUpx());
    }
}

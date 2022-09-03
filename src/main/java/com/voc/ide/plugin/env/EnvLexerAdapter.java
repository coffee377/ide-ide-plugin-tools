package com.voc.ide.plugin.env;

import com.intellij.lexer.FlexAdapter;
import com.voc.ide.plugin.env.grammars.EnvLexer;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/04 10:09
 */
public class EnvLexerAdapter extends FlexAdapter {

    public EnvLexerAdapter() {
        super(new EnvLexer());
    }
}

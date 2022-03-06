package com.voc.ide.plugin.style;

import com.intellij.psi.css.impl.util.scheme.CssElementDescriptorFactory2;
import org.jetbrains.plugins.sass.lexer.SASSHighlighterLexer;

import java.util.Set;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/24 15:03
 */
public class StyleUtils {

    public static Set<String> valueIdentifiersWithRpxAndUpx() {
        // css ,less ,sass
        // CssElementDescriptorFactory2.getInstance().getValueIdentifiers());
        Set<String> valueIdentifiers = CssElementDescriptorFactory2.getInstance().getValueIdentifiers();
        CssElementDescriptorFactory2.getInstance().getValueIdentifiers();
        valueIdentifiers.add(StyleNumberUnit.RPX_UNIT);
        valueIdentifiers.add(StyleNumberUnit.UPX_UNIT);
        return valueIdentifiers;
    }

}

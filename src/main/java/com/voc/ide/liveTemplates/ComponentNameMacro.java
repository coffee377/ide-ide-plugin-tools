package com.voc.ide.liveTemplates;

import com.intellij.codeInsight.template.*;
import com.intellij.codeInsight.template.macro.MacroBase;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/01 09:01
 */
public class ComponentNameMacro extends MacroBase {
    public ComponentNameMacro() {
        super("componentName", "componentName(String)");
    }

    private ComponentNameMacro(String name, String description) {
        super(name, description);
    }

    @Override
    protected @Nullable Result calculateResult(Expression @NotNull [] params,
                                               ExpressionContext context, boolean quick) {
        // Retrieve the text from the macro or selection, if any is available.
        String text = getTextResult(params, context, true);
        if (text == null) {
            return null;
        }
        if (text.length() > 0) {
            // Capitalize the start of every word
            text = StringUtil.toTitleCase(text);
        }
        return new TextResult(text);

    }


    @Override
    public boolean isAcceptableInContext(TemplateContextType context) {
        return super.isAcceptableInContext(context);
    }
}

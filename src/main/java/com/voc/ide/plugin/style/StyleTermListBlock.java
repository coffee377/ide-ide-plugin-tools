package com.voc.ide.plugin.style;

import com.intellij.formatting.Alignment;
import com.intellij.formatting.Block;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Spacing;
import com.intellij.lang.ASTNode;
import com.intellij.psi.css.impl.CssElementTypes;
import com.intellij.psi.css.impl.util.editor.CssFormattingModelBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/23 16:36
 */
public class StyleTermListBlock extends CssFormattingModelBuilder.CssTermListBlock {
    public static final Pattern RPX_OR_UPX_UNIT_PATTERN = Pattern.compile(".*[r|u]px$", Pattern.CASE_INSENSITIVE);

    public StyleTermListBlock(ASTNode astNode, Indent indent, CssFormattingModelBuilder.CssFormattingExtension extension, Alignment alignment, boolean shouldIndentContent) {
        super(astNode, indent, extension, alignment, shouldIndentContent);
    }

    @Override
    public Spacing getSpacing(Block child1, @NotNull Block child2) {
        if (this.withRpxOrUpxUnit(child1, child2)) {
            return Spacing.createSpacing(0, 0, 0, false, 0);
        }
        return super.getSpacing(child1, child2);
    }

    protected boolean withRpxOrUpxUnit(Block child1, @NotNull Block child2) {
        if (child1 instanceof CssFormattingModelBuilder.CssFormatterBlock && child2 instanceof CssFormattingModelBuilder.CssFormatterBlock) {
            CssFormattingModelBuilder.CssFormatterBlock block1 = (CssFormattingModelBuilder.CssFormatterBlock) child1;
            CssFormattingModelBuilder.CssFormatterBlock block2 = (CssFormattingModelBuilder.CssFormatterBlock) child2;
            boolean match = RPX_OR_UPX_UNIT_PATTERN.matcher(block2.toString()).matches();
            return block1.myType.equals(CssElementTypes.CSS_NUMBER) && block2.myType.equals(CssElementTypes.CSS_IDENT) && match;
        }
        return false;
    }

}

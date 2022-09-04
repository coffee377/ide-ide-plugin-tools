package com.voc.ide.plugin.env.extensions;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.voc.ide.plugin.env.EnvLanguage;
import com.voc.ide.plugin.env.psi.EnvTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/04 14:32
 */
public class EnvFormattingModelBuilder implements FormattingModelBuilder {

    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        return new SpacingBuilder(settings, EnvLanguage.INSTANCE)
                .around(EnvTypes.SEPARATOR)
                .spaceIf(settings.getCommonSettings(EnvLanguage.INSTANCE.getID()).SPACE_AROUND_ASSIGNMENT_OPERATORS)
                .before(EnvTypes.PROPERTY)
                .none();
    }

    @Override
    public @NotNull FormattingModel createModel(@NotNull FormattingContext formattingContext) {
        final CodeStyleSettings codeStyleSettings = formattingContext.getCodeStyleSettings();
        return FormattingModelProvider
                .createFormattingModelForPsiFile(formattingContext.getContainingFile(),
                        new EnvBlock(formattingContext.getNode(),
                                Wrap.createWrap(WrapType.NONE, false),
                                Alignment.createAlignment(),
                                createSpaceBuilder(codeStyleSettings)),
                        codeStyleSettings);
    }

    static class EnvBlock extends AbstractBlock {
        private final SpacingBuilder spacingBuilder;

        protected EnvBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment, SpacingBuilder spacingBuilder) {
            super(node, wrap, alignment);
            this.spacingBuilder = spacingBuilder;
        }

        @Override
        protected List<Block> buildChildren() {
            List<Block> blocks = new ArrayList<>();
            ASTNode child = myNode.getFirstChildNode();
            while (child != null) {
                if (child.getElementType() != TokenType.WHITE_SPACE) {
                    Block block = new EnvBlock(child, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment(), spacingBuilder);
                    blocks.add(block);
                }
                child = child.getTreeNext();
            }
            return blocks;
        }

        @Override
        public Indent getIndent() {
            return Indent.getNoneIndent();
        }

        @Override
        public @Nullable Spacing getSpacing(@Nullable Block block, @NotNull Block block1) {
            return spacingBuilder.getSpacing(this, block, block1);
        }

        @Override
        public boolean isLeaf() {
            return myNode.getFirstChildNode() == null;
        }
    }
}

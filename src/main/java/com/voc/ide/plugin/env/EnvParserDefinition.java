package com.voc.ide.plugin.env;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.voc.ide.plugin.env.parser.EnvParser;
import com.voc.ide.plugin.env.psi.EnvTypes;
import org.jetbrains.annotations.NotNull;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/03 21:33
 */
public class EnvParserDefinition implements ParserDefinition {

    public static final TokenSet COMMENTS = TokenSet.create(EnvTypes.COMMENT);

    public static final IFileElementType FILE = new IFileElementType(EnvLanguage.INSTANCE);


    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new EnvLexerAdapter();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new EnvParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode astNode) {
        return EnvTypes.Factory.createElement(astNode);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider fileViewProvider) {
        return new EnvFile(fileViewProvider);
    }
}

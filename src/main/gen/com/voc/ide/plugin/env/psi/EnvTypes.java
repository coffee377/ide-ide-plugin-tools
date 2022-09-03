// This is a generated file. Not intended for manual editing.
package com.voc.ide.plugin.env.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.voc.ide.plugin.env.psi.impl.*;

public interface EnvTypes {

  IElementType PROPERTY = new EnvElementType("PROPERTY");

  IElementType COMMENT = new EnvTokenType("COMMENT");
  IElementType CRLF = new EnvTokenType("CRLF");
  IElementType KEY = new EnvTokenType("KEY");
  IElementType SEPARATOR = new EnvTokenType("SEPARATOR");
  IElementType VALUE = new EnvTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROPERTY) {
        return new EnvPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}

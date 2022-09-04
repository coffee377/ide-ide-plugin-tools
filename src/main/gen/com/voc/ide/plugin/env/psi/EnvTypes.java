// This is a generated file. Not intended for manual editing.
package com.voc.ide.plugin.env.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.voc.ide.plugin.env.psi.impl.*;

public interface EnvTypes {

  IElementType KEY = new EnvElementType("KEY");
  IElementType PROPERTY = new EnvElementType("PROPERTY");
  IElementType VALUE = new EnvElementType("VALUE");

  IElementType COMMENT = new EnvTokenType("COMMENT");
  IElementType CRLF = new EnvTokenType("CRLF");
  IElementType EXPORT = new EnvTokenType("EXPORT");
  IElementType KEY_CHARS = new EnvTokenType("KEY_CHARS");
  IElementType QUOTE = new EnvTokenType("QUOTE");
  IElementType SEPARATOR = new EnvTokenType("SEPARATOR");
  IElementType VALUE_CHARS = new EnvTokenType("VALUE_CHARS");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == KEY) {
        return new EnvKeyImpl(node);
      }
      else if (type == PROPERTY) {
        return new EnvPropertyImpl(node);
      }
      else if (type == VALUE) {
        return new EnvValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}

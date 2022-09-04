// This is a generated file. Not intended for manual editing.
package com.voc.ide.plugin.env.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class EnvVisitor extends PsiElementVisitor {

  public void visitKey(@NotNull EnvKey o) {
    visitPsiElement(o);
  }

  public void visitProperty(@NotNull EnvProperty o) {
    visitNamedElement(o);
  }

  public void visitValue(@NotNull EnvValue o) {
    visitPsiElement(o);
  }

  public void visitNamedElement(@NotNull EnvNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}

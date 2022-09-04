// This is a generated file. Not intended for manual editing.
package com.voc.ide.plugin.env.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.voc.ide.plugin.env.psi.EnvTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.voc.ide.plugin.env.psi.*;

public class EnvKeyImpl extends ASTWrapperPsiElement implements EnvKey {

  public EnvKeyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull EnvVisitor visitor) {
    visitor.visitKey(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof EnvVisitor) accept((EnvVisitor)visitor);
    else super.accept(visitor);
  }

}

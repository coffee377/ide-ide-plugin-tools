// This is a generated file. Not intended for manual editing.
package com.voc.ide.plugin.env.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.voc.ide.plugin.env.psi.EnvTypes.*;
import com.voc.ide.plugin.env.psi.*;
import com.intellij.navigation.ItemPresentation;

public class EnvPropertyImpl extends EnvNamedElementImpl implements EnvProperty {

  public EnvPropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull EnvVisitor visitor) {
    visitor.visitProperty(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof EnvVisitor) accept((EnvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public EnvKey getKey() {
    return findNotNullChildByClass(EnvKey.class);
  }

  @Override
  @Nullable
  public EnvValue getValue() {
    return findChildByClass(EnvValue.class);
  }

  @Override
  public String getKeyText() {
    return EnvPsiImplUtil.getKeyText(this);
  }

  @Override
  public String getValueText() {
    return EnvPsiImplUtil.getValueText(this);
  }

  @Override
  public String getName() {
    return EnvPsiImplUtil.getName(this);
  }

  @Override
  public PsiElement setName(@NotNull String newName) {
    return EnvPsiImplUtil.setName(this, newName);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return EnvPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  public @Nullable ItemPresentation getPresentation() {
    return EnvPsiImplUtil.getPresentation(this);
  }

}

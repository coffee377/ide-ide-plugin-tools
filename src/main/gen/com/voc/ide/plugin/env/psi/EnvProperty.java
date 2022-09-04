// This is a generated file. Not intended for manual editing.
package com.voc.ide.plugin.env.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface EnvProperty extends EnvNamedElement {

  @NotNull
  EnvKey getKey();

  @Nullable
  EnvValue getValue();

  String getKeyText();

  String getValueText();

  String getName();

  PsiElement setName(@NotNull String newName);

  PsiElement getNameIdentifier();

  @Nullable ItemPresentation getPresentation();

}

package com.voc.ide.liveTemplates;

import com.intellij.codeInsight.template.EverywhereContextType;
import com.intellij.codeInsight.template.JavaCodeContextType;
import com.intellij.codeInsight.template.TemplateActionContext;
import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.lang.properties.PropertiesLanguage;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.util.PsiUtilCore;
import com.voc.ide.plugin.env.EnvFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/12 09:38
 */
public abstract class EnvContextType extends TemplateContextType {

    public EnvContextType(@NotNull String id, @NotNull String presentableName,
                          @Nullable Class<? extends TemplateContextType> baseContextType) {
        super(id, presentableName, baseContextType);
    }

    @Override
    public boolean isInContext(@NotNull TemplateActionContext templateActionContext) {
        PsiFile file = templateActionContext.getFile();
        FileType fileType = file.getFileType();
        if (!(fileType instanceof EnvFileType)) {
            return false;
        }
        int startOffset = templateActionContext.getStartOffset();
        if (PsiUtilCore.getLanguageAtOffset(file, startOffset).isKindOf(PropertiesLanguage.INSTANCE)) {
            PsiElement element = file.findElementAt(startOffset);
            if (element instanceof PsiWhiteSpace) {
                return false;
            } else {
                return element != null && this.isInContext(element);
            }
        } else {
            return false;
        }
    }

    protected abstract boolean isInContext(@NotNull PsiElement element);

    public static class Generic extends JavaCodeContextType {
        public Generic() {
            super("ENV_FILE", EnvFileType.INSTANCE.getDisplayName(), EverywhereContextType.class);
        }

        protected boolean isInContext(@NotNull PsiElement element) {
            return true;
        }
    }

    public static class Key extends EnvContextType {
        public Key() {
            super("ENV_KEY", "Key", Generic.class);
        }

        @Override
        protected boolean isInContext(@NotNull PsiElement element) {
            return true;
        }
    }

    public static class Value extends EnvContextType {
        public Value() {
            super("ENV_VALUE", "Value", Generic.class);
        }

        @Override
        protected boolean isInContext(@NotNull PsiElement element) {
            return true;
        }
    }
}

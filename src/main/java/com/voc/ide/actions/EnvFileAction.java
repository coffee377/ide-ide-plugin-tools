package com.voc.ide.actions;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.voc.ide.plugin.DevToolsBundle;
import com.voc.ide.plugin.DevToolsIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/10/12 15:29
 */
public class EnvFileAction extends CreateFileFromTemplateAction implements DumbAware {
    public static final String TEMPLATE_NAME = "Env File";

    public EnvFileAction() {
        super(DevToolsBundle.message("filetype.env.name"), DevToolsBundle.message("filetype.env.action.description"), DevToolsIcons.ENV_FILE);
    }

    @Override
    protected void buildDialog(@NotNull Project project, @NotNull PsiDirectory psiDirectory, CreateFileFromTemplateDialog.@NotNull Builder builder) {
        builder.setTitle(DevToolsBundle.message("filetype.env.action.new.file.dialog.title"))
                .addKind(DevToolsBundle.message("filetype.env.action.new.file.name"), DevToolsIcons.ENV_FILE,
                        TEMPLATE_NAME);
    }

    @Override
    protected PsiFile createFile(String name, String templateName, PsiDirectory dir) {
        if (".".equals(name.trim())) {
            return super.createFile(".env", templateName, dir);
        }
        return super.createFile(name, templateName, dir);
    }

    @Override
    protected String getActionName(PsiDirectory psiDirectory, @NonNls @NotNull String newName, @NonNls String templateName) {
        return DevToolsBundle.message("filetype.env.action.name", newName);
    }
}

package com.voc.ide.plugin.env.action;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.voc.ide.plugin.DevToolsBundle;
import com.voc.ide.plugin.DevToolsIcons;
import com.voc.ide.plugin.env.util.EnvKind;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/10/12 15:29
 */
public class CreateEnvFileAction extends CreateFileFromTemplateAction implements DumbAware {

    public CreateEnvFileAction() {
        super(DevToolsBundle.message("filetype.env.name"), DevToolsBundle.message("filetype.env.action.description"), DevToolsIcons.ENV_FILE);
    }

    @Override
    protected void buildDialog(@NotNull Project project, @NotNull PsiDirectory psiDirectory, CreateFileFromTemplateDialog.@NotNull Builder builder) {
        builder.setTitle(DevToolsBundle.message("filetype.env.action.new.file.dialog.title"));

        builder.addKind(EnvKind.DEFAULT.getKind(), EnvKind.DEFAULT.getIcon(), EnvKind.DEFAULT.getTemplateName());

        // TODO: 2022/9/3 19:46 支持 nodejs 才加载
        builder.addKind(EnvKind.NODE_JS.getKind(), EnvKind.NODE_JS.getIcon(), EnvKind.NODE_JS.getTemplateName());

        // TODO: 2022/9/3 19:46 支持 docker 才加载
        builder.addKind(EnvKind.DOCKER.getKind(), EnvKind.DOCKER.getIcon(), EnvKind.DOCKER.getTemplateName())

        ;
    }

    @Override
    protected String getActionName(PsiDirectory psiDirectory, @NonNls @NotNull String kind, @NonNls String templateName) {
        return DevToolsBundle.message("filetype.env.action.name", kind);
    }

}

package com.voc.ide.plugin.env.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.voc.ide.plugin.env.util.EnvUtil;
import org.jetbrains.annotations.NotNull;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/12 18:48
 */
public class TestAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        Project project = anActionEvent.getProject();
        EnvUtil.findProperties2(project, null);
    }
}

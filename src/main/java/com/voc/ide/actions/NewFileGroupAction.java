package com.voc.ide.actions;

import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.DumbAware;
import com.voc.ide.plugin.DevToolsBundle;
import com.voc.ide.plugin.DevToolsIcons;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/10/11 15:25
 */
public class NewFileGroupAction extends DefaultActionGroup implements DumbAware {

    public NewFileGroupAction() {
        setPopup(true);
        Presentation templatePresentation = this.getTemplatePresentation();
        templatePresentation.setText(DevToolsBundle.message("filetype.tpl.description"));
        templatePresentation.setIcon(DevToolsIcons.TPL_FILE);
    }
}

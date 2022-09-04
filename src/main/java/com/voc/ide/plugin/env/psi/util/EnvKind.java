package com.voc.ide.plugin.env.psi.util;

import com.voc.ide.plugin.DevToolsBundle;
import com.voc.ide.plugin.DevToolsIcons;

import javax.swing.*;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/01 22:07
 */
public enum EnvKind {

    DEFAULT(DevToolsBundle.message("filetype.env.action.new.file.default.name"), DevToolsIcons.ENV_FILE, "Env File"),
    NODE_JS(DevToolsBundle.message("filetype.env.action.new.file.nodejs.name"), DevToolsIcons.ENV_FILE_NODE_JS, "Node JS Env File"),
    DOCKER(DevToolsBundle.message("filetype.env.action.new.file.docker.compose.name"), DevToolsIcons.ENV_FILE_DOCKER, "Docker Env File");

    EnvKind(String kind, Icon icon, String templateName) {
        this.kind = kind;
        this.icon = icon;
        this.templateName = templateName;
    }

    private final String kind;

    private final Icon icon;

    private final String templateName;

    public String getKind() {
        return kind;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getTemplateName() {
        return templateName;
    }
}

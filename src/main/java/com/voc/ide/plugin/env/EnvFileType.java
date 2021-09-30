package com.voc.ide.plugin.env;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.voc.ide.DevToolsBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/29 17:30
 */
public class EnvFileType extends LanguageFileType {

    public EnvFileType() {
        super(EnvLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return DevToolsBundle.message("filetype.env.name");
    }

    @Override
    public @NotNull String getDescription() {
        return DevToolsBundle.message("filetype.env.description");
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return "env";
    }

    @Override
    public @Nullable Icon getIcon() {
        return EnvIcons.FILE;
    }
}

package com.voc.ide.plugin.tpl;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.fileTypes.TemplateLanguageFileType;
import com.voc.ide.plugin.DevToolsBundle;
import com.voc.ide.plugin.DevToolsIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 14:11
 */
public class TplFileType extends LanguageFileType implements TemplateLanguageFileType {
    public static final TplFileType INSTANCE = new TplFileType();

    public TplFileType() {
        super(TplLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return DevToolsBundle.message("filetype.tpl.name");
    }

    @Override
    public @NotNull String getDescription() {
        return DevToolsBundle.message("filetype.tpl.description");
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return "tpl";
    }

    @Override
    public @Nullable Icon getIcon() {
        return DevToolsIcons.TPL_FILE;
    }
}

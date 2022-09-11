package com.voc.ide.plugin.env;

import com.intellij.lang.properties.PropertiesFileType;
import com.intellij.lang.properties.PropertiesLanguage;
import com.intellij.lang.properties.charset.Native2AsciiCharset;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.encoding.EncodingRegistry;
import com.voc.ide.plugin.DevToolsBundle;
import com.voc.ide.plugin.DevToolsIcons;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.nio.charset.Charset;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/29 17:30
 */
public class EnvFileType extends LanguageFileType {
    public static final EnvFileType INSTANCE = new EnvFileType();
    public static final String SUFFIX = "env";

    public EnvFileType() {
        super(PropertiesLanguage.INSTANCE);
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
        return SUFFIX;
    }

    @Override
    public @Nullable Icon getIcon() {
        return DevToolsIcons.ENV_FILE;
    }

    @Override
    public @NonNls @Nullable String getCharset(@NotNull VirtualFile file, byte @NotNull [] content) {
        Charset charset = EncodingRegistry.getInstance().getDefaultCharsetForPropertiesFiles(file);
        if (charset == null) {
            charset = PropertiesFileType.PROPERTIES_DEFAULT_CHARSET;
        }
        if (EncodingRegistry.getInstance().isNative2Ascii(file)) {
            charset = Native2AsciiCharset.wrap(charset);
        }
        return charset.name();
    }

    @Override
    public @Nls @NotNull String getDisplayName() {
        return "Env";
    }
}

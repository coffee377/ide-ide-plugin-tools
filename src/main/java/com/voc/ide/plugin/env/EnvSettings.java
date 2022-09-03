package com.voc.ide.plugin.env;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/03 23:27
 */
@State(name = "EnvSettings", storages = {@Storage("env.xml")})
public class EnvSettings implements PersistentStateComponent<EnvSettings> {
    public boolean completionEnabled = true;
    public boolean storeValues = true;

    @Override
    public @Nullable EnvSettings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull EnvSettings envSettings) {
        XmlSerializerUtil.copyBean(envSettings, this);
    }

    public static EnvSettings getInstance(Project project) {
        return project.getService(EnvSettings.class);
    }
}

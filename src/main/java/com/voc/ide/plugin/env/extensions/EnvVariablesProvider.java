package com.voc.ide.plugin.env.extensions;

import com.intellij.lang.properties.IProperty;
import com.intellij.lang.properties.psi.PropertiesFile;
import com.intellij.lang.properties.psi.Property;
import com.intellij.lang.properties.psi.impl.PropertiesFileImpl;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.voc.ide.plugin.env.EnvFileType;
import com.voc.ide.plugin.env.api.EnvironmentVariablesProvider;
import com.voc.ide.plugin.env.api.FileAcceptResult;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/09/30 11:44
 */
public class EnvVariablesProvider implements EnvironmentVariablesProvider {
    @Override
    public @NotNull FileAcceptResult acceptFile(VirtualFile virtualFile) {
        if (!virtualFile.getFileType().equals(EnvFileType.INSTANCE)) {
            return FileAcceptResult.NOT_ACCEPTED;
        }

        // .env.dist , .env.example files are secondary
        return virtualFile.getName().equals(".env") ? FileAcceptResult.ACCEPTED : FileAcceptResult.ACCEPTED_SECONDARY;
    }

    @Override
    public @NotNull Collection<Property> getElements(PsiFile psiFile) {
        if (psiFile instanceof PropertiesFile) {
            List<IProperty> properties = ((PropertiesFileImpl) psiFile).getProperties();
            List<Property> collect = properties.stream().map(prop -> (Property) prop).collect(Collectors.toList());
            return collect;
        }
        return EnvironmentVariablesProvider.super.getProperty(psiFile);
    }


    @Override
    public @NotNull Collection<Property> getProperty(PsiFile psiFile) {
        return getElements(psiFile);
    }
}

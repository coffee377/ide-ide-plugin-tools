package com.voc.ide.plugin.env.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.voc.ide.plugin.env.EnvFile;
import com.voc.ide.plugin.env.EnvFileType;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/04 11:54
 */
public class EnvElementFactory {
    public static EnvFile createFile(Project project, String text) {
        return (EnvFile) PsiFileFactory.getInstance(project).createFileFromText("dummy.env", EnvFileType.INSTANCE, text);
    }

    public static EnvProperty createProperty(Project project, String text) {
        final EnvFile file = createFile(project, text);
        return (EnvProperty) file.getFirstChild();
    }

    public static EnvProperty createProperty(Project project, String name, String value) {
        return createProperty(project,name + " = " + value);
    }

    public static PsiElement createCRLF(Project project) {
        final EnvFile file = createFile(project, "\n");
        return file.getFirstChild();
    }

}

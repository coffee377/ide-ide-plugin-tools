package com.voc.ide.plugin.env.handler;

import com.intellij.ide.fileTemplates.CreateFromTemplateHandler;
import com.intellij.ide.fileTemplates.DefaultCreateFromTemplateHandler;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.psi.PsiDirectory;
import com.voc.ide.plugin.env.psi.util.EnvFileName;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/03 14:02
 */
public class EnvCreateFromTemplateHandler extends DefaultCreateFromTemplateHandler implements CreateFromTemplateHandler {
    @Override
    public boolean handlesTemplate(@NotNull FileTemplate template) {
        return "env".equals(template.getExtension());
    }

    @Override
    protected String checkAppendExtension(String fileName, @NotNull FileTemplate template) {

        if (fileName.endsWith(".env") || fileName.startsWith(".env")) {
            return fileName;
        }

        if ("env".equals(fileName) || ".".equals(fileName)) {
            return EnvFileName.forMode(""); // .env
        }

        return EnvFileName.forMode(fileName);

    }

    @Override
    public boolean canCreate(PsiDirectory @NotNull [] dirs) {
        return super.canCreate(dirs);
    }

    @Override
    public void prepareProperties(@NotNull Map<String, Object> map) {
        map.put("author", "coffee377");
    }
}

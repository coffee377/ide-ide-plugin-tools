package com.voc.ide.plugin.env.extensions;

import com.intellij.util.indexing.*;
import com.intellij.util.io.DataExternalizer;
import com.intellij.util.io.EnumeratorStringDescriptor;
import com.intellij.util.io.KeyDescriptor;
import com.voc.ide.plugin.env.EnvSettings;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/05 00:30
 */
public class EnvFileIndex extends FileBasedIndexExtension<String, String> {
    public static final ID<String, String> KEY = ID.create("com.voc.ide.plugin.tools.env.kv");

    @Override
    public @NotNull ID<String, String> getName() {
        return KEY;
    }

    @Override
    public @NotNull DataIndexer<String, String, FileContent> getIndexer() {
        return fileContent -> {
            final Map<String, String> map = new HashMap<>();
            boolean storeValues = EnvSettings.getInstance(fileContent.getProject()).storeValues;
            EnvVariablesProvider.EP_NAME.forEachExtensionSafe(provider -> {
//                Collection<KeyValuePsiElement> elements = provider.getElements(fileContent.getPsiFile());
//                elements.forEach(keyValueElement -> {
//                    if (storeValues) {
//                        map.put(keyValueElement.getKey(), keyValueElement.getShortValue());
//                    } else {
//                        map.put(keyValueElement.getKey(), "");
//                    }
//                });
            });
            return map;
        };
    }

    @Override
    public @NotNull KeyDescriptor<String> getKeyDescriptor() {
         return EnumeratorStringDescriptor.INSTANCE;
    }

    @Override
    public @NotNull DataExternalizer<String> getValueExternalizer() {
        return EnumeratorStringDescriptor.INSTANCE;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public FileBasedIndex.@NotNull InputFilter getInputFilter() {
        return file -> EnvVariablesProvider.EP_NAME.getExtensionList()
                .stream().anyMatch(provider -> provider.acceptFile(file).isAccepted());
    }

    @Override
    public boolean dependsOnFileContent() {
        return true;
    }
}

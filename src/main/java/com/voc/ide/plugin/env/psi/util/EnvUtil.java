package com.voc.ide.plugin.env.psi.util;

import com.google.common.collect.Lists;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.voc.ide.plugin.env.EnvFile;
import com.voc.ide.plugin.env.EnvFileType;
import com.voc.ide.plugin.env.psi.EnvProperty;
import com.voc.ide.plugin.env.psi.impl.EnvPsiImplUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/04 11:06
 */
public class EnvUtil {

    /**
     * Searches the entire project for Simple language files with instances of the Simple property with the given key.
     *
     * @param project current project
     * @param key     to check
     * @return matching properties
     */
    public static List<EnvProperty> findProperties(Project project, @Nullable String key) {
        List<EnvProperty> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(EnvFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            EnvFile simpleFile = (EnvFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                EnvProperty[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, EnvProperty.class);
                if (properties != null) {
                    if (!StringUtil.isEmpty(key)) {
                        for (EnvProperty property : properties) {
                            String keyText = EnvPsiImplUtil.getKeyText(property);
                            if (key.equals(keyText)) {
                                result.add(property);
                            }
                        }
                    } else {
                        Collections.addAll(result, properties);
                    }
                }
            }
        }
        return result;
    }

    public static List<EnvProperty> findProperties(Project project) {
        return findProperties(project, null);
    }

    /**
     * Attempts to collect any comment elements above the Simple key/value pair.
     */
    public static @NotNull String findDocumentationComment(EnvProperty property) {
        List<String> result = new LinkedList<>();
        PsiElement element = property.getPrevSibling();
        while (element instanceof PsiComment || element instanceof PsiWhiteSpace) {
            if (element instanceof PsiComment) {
                String commentText = element.getText().replaceFirst("[!# ]+", "");
                result.add(commentText);
            }
            element = element.getPrevSibling();
        }
        return StringUtil.join(Lists.reverse(result), "\n ");
    }

}

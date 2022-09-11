package com.voc.ide.plugin.env.util;

import com.intellij.lang.properties.IProperty;
import com.intellij.lang.properties.psi.PropertiesFile;
import com.intellij.lang.properties.psi.Property;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.voc.ide.plugin.env.EnvFileType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
//    public static List<EnvProperty> findProperties(Project project, @Nullable String key) {
//        List<EnvProperty> result = new ArrayList<>();
//        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(EnvFileType.INSTANCE, GlobalSearchScope.allScope(project));
//        for (VirtualFile virtualFile : virtualFiles) {
//            EnvFile envFile = (EnvFile) PsiManager.getInstance(project).findFile(virtualFile);
//            if (envFile != null) {
////                EnvProperty[] properties = PsiTreeUtil.getChildrenOfType(envFile, EnvProperty.class);
////                if (properties != null) {
////                    if (!StringUtil.isEmpty(key)) {
////                        for (EnvProperty property : properties) {
////                            String keyText = EnvPsiImplUtil.getKeyText(property);
////                            if (key.equals(keyText)) {
////                                result.add(property);
////                            }
////                        }
////                    } else {
////                        Collections.addAll(result, properties);
////                    }
////                }
//            }
//        }
//        return result;
//    }

    public static List<Property> findProperties2(Project project, String key) {
        List<Property> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(EnvFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            PropertiesFile envFile = (PropertiesFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (envFile != null) {
                List<IProperty> properties = envFile.findPropertiesByKey(key);
                List<Property> collect = properties.stream().map(prop -> (Property) prop).collect(Collectors.toList());
                result.addAll(collect);
//                List<IProperty> properties = envFile.getProperties();
//                envFile.findPropertiesByKey("");
//                PsiTreeUtil.getChildrenOfType(envFile, PropertiesList.class);
            }
        }
        return result;
    }

//    public static List<EnvProperty> findProperties(Project project) {
//        return findProperties(project, null);
//    }

    /**
     * Attempts to collect any comment elements above the Simple key/value pair.
     */
//    public static @NotNull String findDocumentationComment(EnvProperty property) {
//        List<String> result = new LinkedList<>();
////        PsiElement element = property.getPrevSibling();
////        while (element instanceof PsiComment || element instanceof PsiWhiteSpace) {
////            if (element instanceof PsiComment) {
////                String commentText = element.getText().replaceFirst("[!# ]+", "");
////                result.add(commentText);
////            }
////            element = element.getPrevSibling();
////        }
//        return StringUtil.join(Lists.reverse(result), "\n ");
//    }

}

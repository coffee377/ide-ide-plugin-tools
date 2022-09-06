package com.voc.ide.plugin.env.api;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiSearchHelper;
import com.intellij.util.indexing.FileBasedIndex;
import com.voc.ide.plugin.env.extensions.EnvFileIndex;
import com.voc.ide.plugin.env.psi.util.EnvUtil;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class EnvironmentVariablesApi {

    @NotNull
    public static Map<String, String> getAllKeyValues(Project project) {
        FileBasedIndex fileBasedIndex = FileBasedIndex.getInstance();
        Map<String, String> keyValues = new HashMap<>();
        Map<String, String> secondaryKeyValues = new HashMap<>();
        Map<VirtualFile, FileAcceptResult> resultsCache = new HashMap<>();

        GlobalSearchScope scope = GlobalSearchScope.allScope(project);

        fileBasedIndex.processAllKeys(EnvFileIndex.KEY, key -> {
            for (VirtualFile virtualFile : fileBasedIndex.getContainingFiles(EnvFileIndex.KEY, key, scope)) {

                FileAcceptResult fileAcceptResult;

                if (resultsCache.containsKey(virtualFile)) {
                    fileAcceptResult = resultsCache.get(virtualFile);
                } else {
                    fileAcceptResult = getFileAcceptResult(virtualFile);
                    resultsCache.put(virtualFile, fileAcceptResult);
                }

                if (!fileAcceptResult.isAccepted()) {
                    continue;
                }

                fileBasedIndex.processValues(EnvFileIndex.KEY, key, virtualFile, ((file, val) -> {
                    if (fileAcceptResult.isPrimary()) {
                        keyValues.putIfAbsent(key, val);
                    } else {
                        secondaryKeyValues.putIfAbsent(key, val);
                    }

                    return true;
                }), scope);
            }

            return true;
        }, project);

        secondaryKeyValues.putAll(keyValues);

        return secondaryKeyValues;
    }

    /**
     * @param project project
     * @param key     environment variable key
     * @return All key declarations, in .env files, Dockerfile, docker-compose.yml, etc
     */
    @NotNull
    public static PsiElement[] getKeyDeclarations(Project project, String key) {
        List<PsiElement> targets = new ArrayList<>();
        List<PsiElement> secondaryTargets = new ArrayList<>();

        FileBasedIndex.getInstance().getFilesWithKey(EnvFileIndex.KEY, new HashSet<>(Collections.singletonList(key)), virtualFile -> {
            PsiFile psiFileTarget = PsiManager.getInstance(project).findFile(virtualFile);
            if (psiFileTarget == null) {
                return true;
            }

//            for (EnvironmentVariablesProvider provider : EnvironmentVariablesProviderUtil.PROVIDERS) {
//                FileAcceptResult fileAcceptResult = provider.acceptFile(virtualFile);
//                if (!fileAcceptResult.isAccepted()) {
//                    continue;
//                }
//
//                (fileAcceptResult.isPrimary() ? targets : secondaryTargets).addAll(EnvUtil.getElementsByKey(key,
//                        provider.getElements(psiFileTarget)));
//            }

            return true;
        }, GlobalSearchScope.allScope(project));

        return (targets.size() > 0 ? targets : secondaryTargets).toArray(PsiElement.EMPTY_ARRAY);
    }

    /**
     * @param project project
     * @param key     environment variable key
     * @return All key usages, like getenv('KEY')
     */
    @NotNull
    public static PsiElement[] getKeyUsages(Project project, String key) {
        List<PsiElement> targets = new ArrayList<>();

        PsiSearchHelper searchHelper = PsiSearchHelper.getInstance(project);

//        Processor<PsiFile> psiFileProcessor = psiFile -> {
//            for (EnvironmentVariablesUsagesProvider provider : EnvironmentVariablesProviderUtil.USAGES_PROVIDERS) {
//                targets.addAll(EnvironmentVariablesUtil.getUsagesElementsByKey(key, provider.getUsages(psiFile)));
//            }
//
//            return true;
//        };
//
//        searchHelper.processAllFilesWithWord(key, GlobalSearchScope.allScope(project), psiFileProcessor, true);
//        searchHelper.processAllFilesWithWordInLiterals(key, GlobalSearchScope.allScope(project), psiFileProcessor);
//        searchHelper.processAllFilesWithWordInText(key, GlobalSearchScope.allScope(project), psiFileProcessor, true);

        return targets.toArray(PsiElement.EMPTY_ARRAY);
    }

    private static FileAcceptResult getFileAcceptResult(VirtualFile virtualFile) {
//        for (EnvironmentVariablesProvider provider : EnvironmentVariablesProviderUtil.PROVIDERS) {
//            FileAcceptResult fileAcceptResult = provider.acceptFile(virtualFile);
//            if (!fileAcceptResult.isAccepted()) {
//                continue;
//            }
//
//            return fileAcceptResult;
//        }

        return FileAcceptResult.NOT_ACCEPTED;
    }
}

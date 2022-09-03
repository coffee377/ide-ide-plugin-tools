package com.voc.ide.plugin.env.inspections;

import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DuplicateKeyInspection extends LocalInspectionTool {
    @Override
    public @Nls(capitalization = Nls.Capitalization.Sentence) @NotNull String getDisplayName() {
        return "Duplicate key";
    }

    @Override
    public boolean runForWholeFile() {
        return true;
    }

    @Override
    public ProblemDescriptor @Nullable [] checkFile(@NotNull PsiFile file, @NotNull InspectionManager manager, boolean isOnTheFly) {
//        if (file instanceof EnvFile) {
//            return analyzeFile(file, manager, isOnTheFly).getResultsArray();
//        }
        return super.checkFile(file, manager, isOnTheFly);
    }

    @NotNull
    private ProblemsHolder analyzeFile(@NotNull PsiFile file, @NotNull InspectionManager manager, boolean isOnTheFly) {
//        EnvPsiElementsVisitor visitor = new EnvPsiElementsVisitor();
//        file.acceptChildren(visitor);
//
        ProblemsHolder problemsHolder = new ProblemsHolder(manager, file, isOnTheFly);
//
//        Map<String, PsiElement> existingKeys = new HashMap<>();
//        Set<PsiElement> markedElements = new HashSet<>();
//        for (KeyValuePsiElement keyValue : visitor.getCollectedItems()) {
//            final String key = keyValue.getKey();
//
//            if (existingKeys.containsKey(key)) {
//                problemsHolder.registerProblem(keyValue.getElement(), "Duplicate key");
//
//                PsiElement markedElement = existingKeys.get(key);
//                if (!markedElements.contains(markedElement)) {
//                    problemsHolder.registerProblem(markedElement, "Duplicate key");
//                    markedElements.add(markedElement);
//                }
//            } else {
//                existingKeys.put(key, keyValue.getElement());
//            }
//        }

        return problemsHolder;
    }
}

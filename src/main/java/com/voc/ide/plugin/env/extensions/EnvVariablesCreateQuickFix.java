package com.voc.ide.plugin.env.extensions;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.IncorrectOperationException;
import com.voc.ide.plugin.env.EnvFileImpl;
import com.voc.ide.plugin.env.EnvFileType;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/04 19:52
 */
public class EnvVariablesCreateQuickFix extends BaseIntentionAction {
    private final String key;

    public EnvVariablesCreateQuickFix(String key) {
        this.key = key;
        this.setText("Create environment variable '" + key + "'");
    }

    @Override
    public @NotNull @IntentionFamilyName String getFamilyName() {
        return "Create environment variable";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile psiFile) {
        return true;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile psiFile) throws IncorrectOperationException {
        ApplicationManager.getApplication().invokeLater(() -> {
            Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(EnvFileType.INSTANCE, GlobalSearchScope.allScope(project));
            if (virtualFiles.size() == 1) {
                createProperty(project, virtualFiles.iterator().next());
            } else {
                final FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor(EnvFileType.INSTANCE);
                descriptor.setRoots(ProjectUtil.guessProjectDir(project));
                final VirtualFile file1 = FileChooser.chooseFile(descriptor, project, null);
                if (file1 != null) {
                    createProperty(project, file1);
                }
            }
        });
    }

    private void createProperty(Project project, VirtualFile file) {
        WriteCommandAction.writeCommandAction(project).run(() -> {
            EnvFileImpl envFile = (EnvFileImpl) PsiManager.getInstance(project).findFile(file);
            if (envFile == null) {
                return;
            }

            ASTNode lastChildNode = envFile.getNode().getLastChildNode();
            // TODO: Add another check for CRLF
//            if (lastChildNode != null && !lastChildNode.getElementType().equals(EnvTypes.CRLF)) {
//                envFile.getNode().addChild(EnvElementFactory.createCRLF(project).getNode());
//            }
//            // IMPORTANT: change spaces to escaped spaces or the new node will only have the first word for the key
//            EnvProperty property = EnvElementFactory.createProperty(project, key.replaceAll(" ", "\\\\ "), "");
//            envFile.getNode().addChild(property.getNode());
//            ((Navigatable) property.getLastChild().getNavigationElement()).navigate(true);
//            Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
//            if (editor != null) {
//                editor.getCaretModel().moveCaretRelatively(2, 0, false, false, false);
//            }

        });
    }
}

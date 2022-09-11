package com.voc.ide.plugin.env.extensions;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.lang.properties.psi.Property;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.impl.source.tree.java.PsiJavaTokenImpl;
import com.voc.ide.plugin.DevToolsIcons;
import com.voc.ide.plugin.env.util.EnvUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/04 14:04
 */
public class EnvLineMarkerProvider extends RelatedItemLineMarkerProvider {

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        // This must be an element with a literal expression as a parent
        if (!(element instanceof PsiJavaTokenImpl) || !(element.getParent() instanceof PsiLiteralExpression)) {
            return;
        }

        // The literal expression must start with the Simple language literal expression
        PsiLiteralExpression literalExpression = (PsiLiteralExpression) element.getParent();
        String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
        if (value == null) {
            return;
        }

        // Get the Simple language property usage
        Project project = element.getProject();
//        String possibleProperties = value.substring(EnvAnnotator.ENV_PREFIX_STR.length() + EnvAnnotator.ENV_SEPARATOR_STR.length());
        //        List<Property>
//        PsiTreeUtil.findChildOfType(PropertiesFile.class)

        List<Property> properties = EnvUtil.findProperties2(project, value);

//        final List<EnvProperty> properties = EnvUtil.findProperties(project, possibleProperties);
        if (properties.size() > 0) {
            // Add the property to a collection of line marker info
            NavigationGutterIconBuilder<PsiElement> builder =
                    NavigationGutterIconBuilder.create(DevToolsIcons.ENV_FILE)
                            .setTargets(properties)
                            .setTooltipText("Navigate to Env language property");
            result.add(builder.createLineMarkerInfo(element));
        }
    }
}

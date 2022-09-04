package com.voc.ide.plugin.env.psi;

import com.voc.ide.plugin.env.models.KeyValuePsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/04 23:51
 */
public class EnvPsiElementsVisitor extends EnvVisitor {

    private final Collection<KeyValuePsiElement> collectedItems = new HashSet<>();

    @Override
    public void visitProperty(@NotNull EnvProperty property) {
        collectedItems.add(new KeyValuePsiElement(
                property.getKeyText(),
                property.getValueText(),
                property)
        );
    }

    public Collection<KeyValuePsiElement> getCollectedItems() {
        return collectedItems;
    }
}

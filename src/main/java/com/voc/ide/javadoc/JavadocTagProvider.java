package com.voc.ide.javadoc;

import com.intellij.psi.javadoc.CustomJavadocTagProvider;
import com.intellij.psi.javadoc.JavadocTagInfo;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/11 22:02
 *
 */
public class JavadocTagProvider implements CustomJavadocTagProvider {

    @Override
    public List<JavadocTagInfo> getSupportedTags() {
        return Arrays.asList(new EmailDocTagInfo(), new TimeDocTagInfo(), new DateDocTagInfo());
    }

}

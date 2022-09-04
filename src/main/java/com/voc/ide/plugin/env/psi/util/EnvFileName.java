package com.voc.ide.plugin.env.psi.util;

import java.util.regex.Pattern;

/**
 * <p>.env{@code [.<mode>]}[.local]</p>
 *
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/01 10:10
 */
public class EnvFileName {
    public static final Pattern ENV_NAME_PATTERN = Pattern.compile(".env.?(.*).?local?", Pattern.CASE_INSENSITIVE);

    /**
     *
     */
    private boolean suffix;

    /**
     * 指定模式
     */
    private final String mode;

    /**
     * 是否本地文件
     */
    private final boolean local;

    public EnvFileName(String mode, boolean local) {
        this.mode = mode;
        this.local = local;
    }

    public String getMode() {
        return mode;
    }

    public boolean isLocal() {
        return local;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(".env");
        if (mode != null && !mode.equals("")) {
            sb.append(".").append(mode);
        }
        if (local) {
            sb.append(".local");
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public static String forMode(String mode) {
        return new EnvFileName(mode, false).toString();
    }

    public static String forLocalMode(String mode) {
        return new EnvFileName(mode, true).toString();
    }


}

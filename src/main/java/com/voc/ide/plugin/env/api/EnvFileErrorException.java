package com.voc.ide.plugin.env.api;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/04 21:24
 */
public class EnvFileErrorException extends Exception {
    public EnvFileErrorException(String message) {
        super(message);
    }

    public EnvFileErrorException(Throwable cause) {
        super(cause);
    }

    public EnvFileErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}

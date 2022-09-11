package com.voc.ide.plugin.env;


import com.voc.ide.plugin.env.util.EnvFileName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/01 10:27
 */
public class EnvFileNameTest {

    @Test
    @DisplayName(".env")
    public void defaultEnvFile() {
        assertEquals(".env", EnvFileName.forMode(""));
        assertEquals(".env", EnvFileName.forMode(null));
    }

    @Test
    @DisplayName(".env.local")
    public void localEnvFile() {
        assertEquals(".env.local", EnvFileName.forLocalMode(""));
        assertEquals(".env.local", EnvFileName.forLocalMode(null));
    }

    @Test
    @DisplayName(".env.prod")
    public void prodEnvFile(){
        assertEquals(".env.prod", EnvFileName.forMode("prod"));
    }

    @Test
    @DisplayName(".env.prod")
    public void prodLocalEnvFile(){
        assertEquals(".env.prod.local", EnvFileName.forLocalMode("prod"));
    }
}

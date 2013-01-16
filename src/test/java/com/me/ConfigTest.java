package com.me;

import com.me.web.servlet.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import static org.junit.Assert.*;
/**
 * User: t.ding
 * Date: 13-1-14
 */
public class ConfigTest {
    @Test
    public void testCase() {
        String configClassName = "com.me.TestConfig";

        if (null == configClassName)
            return;

        Config config = null;
        try {
            config = (Config) Class.forName(configClassName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        assertNotNull(config);
    }

}

package com.me;

import com.me.web.servlet.Config;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * User: t.ding
 * Date: 13-1-14
 */
public class ConfigTest {
    @Test
    public void testCase() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String configClassName = "com.me.TestConfig";
        Config config = (Config) Class.forName(configClassName).newInstance();

        assertThat(config, CoreMatchers.notNullValue());
    }

}

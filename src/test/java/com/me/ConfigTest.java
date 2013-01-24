package com.me;

import com.me.web.servlet.config.Config;
import com.me.web.servlet.view.ViewEngine;
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

        config.executeConfigs();

        assertThat(config.getControllerPackages()[0], CoreMatchers.equalTo("com.me"));
        ViewEngine engine = config.getViewEngine();
        assertThat(engine, CoreMatchers.notNullValue());

        assertThat(engine.defaultSuffix(), CoreMatchers.equalTo("jsp"));

    }

}

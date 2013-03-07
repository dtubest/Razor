package com.me.web.servlet.config;

import com.me.web.servlet.view.JspEngine;
import com.me.web.servlet.view.ViewEngine;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * User: t.ding
 * Date: 13-1-24
 */
public class TemplateTest {
    @Test
    public void test() {
        Template template = new Template();
        assertThat(template.path(), CoreMatchers.nullValue());
        assertThat(template.suffix(), CoreMatchers.nullValue());
        assertThat(template.engine(), CoreMatchers.nullValue());

        template.path("/web-inf").suffix("js").engine(JspEngine.class);

        assertThat(template.path(), CoreMatchers.equalTo("/web-inf"));
        assertThat(template.suffix(), CoreMatchers.equalTo("js"));
        assertThat(template.engine().getName(), CoreMatchers.equalTo(JspEngine.class.getName()));

        ViewEngine engine = template.createEngine();
        assertThat(engine, CoreMatchers.notNullValue());
        assertThat(engine.defaultSuffix(), CoreMatchers.equalTo("jsp"));
    }
}

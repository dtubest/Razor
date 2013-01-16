package com.me.web.servlet.handling;

import javassist.ClassClassPath;
import org.junit.Test;

/**
 * User: t.ding
 * Date: 13-1-16
 */
public class ParamExtractableHandlerAdapterTest {
    @Test
    public void test() {
        ClassClassPath path = new ClassClassPath(this.getClass());
        System.out.println(path + "=============================");
    }
}

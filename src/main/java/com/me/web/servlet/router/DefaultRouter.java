package com.me.web.servlet.router;

import com.me.web.servlet.AbstractRouter;
import com.me.web.servlet.Handler;
import com.me.web.servlet.RouteStrategy;
import com.me.web.servlet.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * User: t.ding
 * Date: 12-11-24
 */
public class DefaultRouter extends AbstractRouter {
    private RouteStrategy strategy;

    @Override
    protected RouteStrategy getStrategy() {
        return strategy;
    }

    protected View retrieveView(Handler handler) {

        // todo 首先要得到controller类的名字
        String className = "test.FirstController";

        // todo 然后要得到方法名
        String methodName = "logic";
        Object view = null;
        try {
            Class<?> aClass = getClass().getClassLoader().loadClass(className);
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    view = method.invoke(aClass.newInstance());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return (View) view;
    }
}

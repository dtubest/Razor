package com.me.web.servlet;

import com.me.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: t.ding
 * Date: 13-1-15
 */
public class ControllerPackage {
    private List<String> packages = new ArrayList<String>();

    public void addPackage(String pacName) {
        ClassUtils.validatePacName(pacName);

        packages.add(pacName);
    }

    public void clear() {
        packages.clear();
    }

    public String[] getPackages() {
        return packages.toArray(new String[packages.size()]);
    }
}

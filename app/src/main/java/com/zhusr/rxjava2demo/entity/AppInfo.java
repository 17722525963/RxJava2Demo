package com.zhusr.rxjava2demo.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by zsr on 2017/10/27.
 */

public class AppInfo {

    private String name;
    private Drawable icon;
    private String packageName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "name='" + name + '\'' +
                ", icon=" + icon +
                ", packageName='" + packageName + '\'' +
                '}';
    }
}

package com.zhusr.rxjava2demo.base;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by zsr on 2017/11/1.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Logger
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}

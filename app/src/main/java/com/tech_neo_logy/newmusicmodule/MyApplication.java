package com.tech_neo_logy.newmusicmodule;

import android.app.Application;
import android.content.Context;

/**
 * Created by Prafull on 31-Mar-16.
 */
public class MyApplication extends Application{

    private static MyApplication sInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getAppInstance(){
        return sInstance;
    }

    public static Context getAppcontext()
    {
        return sInstance.getApplicationContext();
    }

}

package hhh.bawei.com.myshopping.application;

import android.app.Application;


public class IApplication extends Application {

    public static IApplication application ;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

    }
}

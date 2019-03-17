package by.vladislaw.kravchenok.projectfortests;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

public class App extends Application {

    private static Context context;

    @Override

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
    }


    public static Context context(){
        return context;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}

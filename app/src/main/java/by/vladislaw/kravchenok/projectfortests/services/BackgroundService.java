package by.vladislaw.kravchenok.projectfortests.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class BackgroundService extends Service {


    //This method was invoked only once when the background service component was created for the first time
    @Override
    public void onCreate() {
        super.onCreate();
    }


    //this method was invoked every time when service was started
    //You can execute task or start child thread in this method.
    //Because android background service is execute in ui thread,
    //so if you don't use child thread and if task is very time consuming,
    //ui thread will be blocked by the service object.
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 100; i > 0; --i) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e("BACKGROUND SERVICE BACKGROUND SERVICE BACKGROUND SERVICE", i + "");
                }
                stopSelf();
            }
        };
        new Handler().post(runnable);
        return super.onStartCommand(intent, flags, startId);
    }

    //When the background service is destroyed, this method will be called.
    // You can release related resouces in it.
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }
}

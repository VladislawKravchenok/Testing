package by.vladislaw.kravchenok.projectfortests.activities;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import by.vladislaw.kravchenok.projectfortests.R;

import static by.vladislaw.kravchenok.projectfortests.App.context;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeUiComponents();
    }

    private void initializeUiComponents() {
        findViewById(R.id.materialDesignButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHeadUpNotification();
            }
        });
    }


    private void showHeadUpNotification() {
       // Intent intent = new Intent(this, NotificationActivity.class);
       // PendingIntent pi = PendingIntent.getActivity(NotificationActivity.this, 0, intent, 0);


//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
//            Notification.Builder builder = new Notification.Builder(this);
//            builder.setContentTitle("Title")
//                    .setContentText("the text")
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                    .setContentIntent(pi)
//                    .setVibrate(new long[]{Notification.DEFAULT_VIBRATE})
//                    .setCategory(Notification.CATEGORY_ALARM)
//                    .setPriority(Notification.PRIORITY_MAX);
//
//            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//            notificationManager.notify(666, builder.build());
//        } else {
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//            builder.setSmallIcon(R.mipmap.ic_launcher)
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                    .setContentTitle("Title")
//                    .setContentText("the text")
//                    .setContentIntent(pi)
//                    .setDefaults(Notification.DEFAULT_ALL)
//                    .setCategory(NotificationCompat.CATEGORY_CALL)
//                    .setPriority(NotificationManager.IMPORTANCE_HIGH);
//            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//            notificationManager.notify(0, builder.build());
//        }


        //startActivity(getPermissionManagerIntent());



    }

    public static Intent getPermissionManagerIntent() {
        Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        localIntent.putExtra("extra_pkgname", context().getPackageName());
        localIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        localIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return localIntent;
    }



}
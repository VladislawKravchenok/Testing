package by.vladislaw.kravchenok.projectfortests.tools;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

import static by.vladislaw.kravchenok.projectfortests.App.context;
import static by.vladislaw.kravchenok.projectfortests.Constants.MY_PERMISSIONS_REQUEST_CALL_PHONE;

public class PhoneCalling {

    private static String phoneNumber = "tel:+375296034321";

    public static void call(Activity activity) {

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(phoneNumber));

        if (ActivityCompat.checkSelfPermission(context(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
            return;
        }
        context().startActivity(callIntent);
    }
}

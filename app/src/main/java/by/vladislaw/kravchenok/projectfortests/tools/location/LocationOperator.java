package by.vladislaw.kravchenok.projectfortests.tools.location;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Locale;

import by.vladislaw.kravchenok.projectfortests.R;
import by.vladislaw.kravchenok.projectfortests.activities.MapsActivity;


/**
 * Created by Vladislaw Kravchenok on 16.03.2019.
 */
public class LocationOperator {

    public static final int REQUEST__LOCATION_PERMISSIONS_REQUEST_CODE = 911;
    private static final String TAG = MapsActivity.class.getSimpleName();
    private static String mLatitudeLabel;
    private static String mLongitudeLabel;
    private static Location lastLocation;

    @SuppressWarnings("MissingPermission")
    public static void getLastLocation(FusedLocationProviderClient mFusedLocationClient,
                                       final GoogleMap mMap,
                                       final MapsActivity activity) {

        mLatitudeLabel = activity.getResources().getString(R.string.latitude_label);
        mLongitudeLabel = activity.getResources().getString(R.string.longitude_label);
        if (checkPermissions(activity)) {
            mFusedLocationClient.getLastLocation()
                    .addOnCompleteListener(activity, new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            if (task.isSuccessful() && task.getResult() != null) {
                                lastLocation = task.getResult();
                                LatLng latLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                                goToCurrentLocation(latLng, mMap);
                                ((TextView)activity.findViewById(R.id.latitude_text)).setText(String.format(Locale.ENGLISH, "%s: %f",
                                        mLatitudeLabel,
                                        lastLocation.getLatitude()));
                                ((TextView)activity.findViewById(R.id.longitude_text)).setText(String.format(Locale.ENGLISH, "%s: %f",
                                        mLongitudeLabel,
                                        lastLocation.getLongitude()));
                            } else {
                                Log.w(TAG, "getLastLocation:exception", task.getException());
                            }
                        }
                    });
        } else {
            requestPermissions(activity);
        }
    }

    private static boolean checkPermissions(Activity activity) {
        return ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    private static void startLocationPermissionRequest(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST__LOCATION_PERMISSIONS_REQUEST_CODE);
    }

    private static void requestPermissions(Activity activity) {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.ACCESS_COARSE_LOCATION);
        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");

        } else {
            Log.i(TAG, "Requesting permission");
            startLocationPermissionRequest(activity);
        }
    }

    private static void goToCurrentLocation(LatLng latLng, GoogleMap mMap) {
        if (mMap.getCameraPosition().zoom < 12) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
        } else {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, mMap.getCameraPosition().zoom));
        }
    }
}

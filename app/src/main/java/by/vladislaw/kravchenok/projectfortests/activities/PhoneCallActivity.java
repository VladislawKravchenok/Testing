package by.vladislaw.kravchenok.projectfortests.activities;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import by.vladislaw.kravchenok.projectfortests.R;

import static by.vladislaw.kravchenok.projectfortests.Constants.MY_PERMISSIONS_REQUEST_CALL_PHONE;
import static by.vladislaw.kravchenok.projectfortests.tools.PhoneCalling.call;

public class PhoneCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call);
    }

    @Override
    protected void onResume() {
        super.onResume();
        findViewById(R.id.materialDesignButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(PhoneCallActivity.this);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            call(PhoneCallActivity.this);
        }
    }
}

package by.vladislaw.kravchenok.projectfortests.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import by.vladislaw.kravchenok.projectfortests.R;

import static by.vladislaw.kravchenok.projectfortests.App.context;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    @Override
    protected void onResume() {
        super.onResume();
        findViewById(R.id.materialDesignButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(getPermissionManagerIntent());
            }
        });
    }

    public static Intent getPermissionManagerIntent() {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.putExtra("extra_package_uid", android.os.Process.myUid());
        intent.putExtra("extra_pkgname", context().getPackageName());
        return intent;
    }


}

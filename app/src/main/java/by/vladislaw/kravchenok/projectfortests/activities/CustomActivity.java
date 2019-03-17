package by.vladislaw.kravchenok.projectfortests.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import by.vladislaw.kravchenok.projectfortests.R;

import static by.vladislaw.kravchenok.projectfortests.customUi.timepicker.DoubleTimePicker.provideFragmentActivity;

public class CustomActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");
        provideFragmentActivity(this);
        setContentView(R.layout.activity_custom);

    }
}

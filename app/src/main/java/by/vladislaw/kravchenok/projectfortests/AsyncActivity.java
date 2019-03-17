package by.vladislaw.kravchenok.projectfortests;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Vladislaw Kravchenok on 02.03.2019.
 */
public class AsyncActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
    }

    @Override
    protected void onResume() {
        super.onResume();
        doBackgroundTask();
    }

    private String text = "fuck";

    private void doBackgroundTask() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //do work
                for (int i = 0; i < 1000000; i++) {
                    if (i == 100000) {
                        text = text.concat(" your");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((TextView) findViewById(R.id.text)).setText(text);
                            }
                        });
                    }

                    if (i == 999999) {
                        text = text.concat(" self");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((TextView) findViewById(R.id.text)).setText(text);
                            }
                        });
                    }
                }

            }
        });

        thread.start();
    }

}

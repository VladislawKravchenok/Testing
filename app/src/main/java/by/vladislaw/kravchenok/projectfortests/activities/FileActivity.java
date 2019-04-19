package by.vladislaw.kravchenok.projectfortests.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.Externalizable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import by.vladislaw.kravchenok.projectfortests.R;

import static android.support.v4.content.FileProvider.getUriForFile;
import static by.vladislaw.kravchenok.projectfortests.Constants.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE;

/**
 * Created by Vladislaw Kravchenok on 19.04.2019.
 */
public class FileActivity extends AppCompatActivity {

    File rootDir;

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
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FileActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                } else {
                    makeExperiment();
                }


            }
        });
    }

    public void makeExperiment() {
        String albumName = "noxboximages";
        String textToShare = "Мне понравилась услуга Ночлег, подключайтесь! https://play.google.com/store/apps/details?id=live.noxbox&referrer=cpTxsUK1MoSeZu87yGSuckuGhk32";

        if (isExternalStorageWritable() && isExternalStorageReadable()) {
            rootDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), albumName);
            if (!rootDir.mkdirs()) {
                Log.e("AAAAAAAAAAAAA", "Directory not created");
                rootDir.mkdir();
            }
            if (rootDir.exists()) {
                Drawable drawable = getDrawable(R.drawable.illus);

                //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.accept);
                Bitmap bitmap = getBitmap(R.drawable.illus);

                String fname = "noxboxshare.jpg";
                File file = new File(rootDir, fname);
                if (file.exists())
                    file.delete();

                try {
                    FileOutputStream out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Drawable fromPublicStorage = null;
                Uri uri = null;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    uri = FileProvider.getUriForFile(getApplicationContext(), "by.vladislaw.kravchenok.projectfortests.fileprovider", file);
                }else{
                    uri = Uri.fromFile(file);//early than 24
                }
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    fromPublicStorage = Drawable.createFromStream(inputStream, uri.toString());
                } catch (FileNotFoundException e) {
                    fromPublicStorage = getResources().getDrawable(R.drawable.sausage);
                    FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".fileprovider", file);
                }

                if (fromPublicStorage != null) {
                    findViewById(R.id.root).setBackground(fromPublicStorage);
                }
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                shareIntent.setType("image/jpeg");
                shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
                //shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(shareIntent, "Send"));
            }
        }
    }

    private Bitmap getBitmap(int drawableRes) {
        Drawable drawable = getResources().getDrawable(drawableRes);
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);

        return bitmap;
    }


    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                    makeExperiment();
                    break;
                }
            }
        }
    }
}

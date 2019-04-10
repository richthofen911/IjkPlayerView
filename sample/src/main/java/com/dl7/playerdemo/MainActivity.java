package com.dl7.playerdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE_CHOOSE_FILE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_find_file).setOnClickListener(MainActivity.this);
        findViewById(R.id.btn_play).setOnClickListener(MainActivity.this);

        /*
        findViewById(R.id.btn_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IjkPlayerActivity.class));
            }
        });
        findViewById(R.id.btn_fullscreen_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IjkFullscreenActivity.class));
            }
        });
        findViewById(R.id.btn_test_aspect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestAspectActivity.class));
            }
        });
        findViewById(R.id.btn_custom_danmaku).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomDanmakuActivity.class));
            }
        });
        findViewById(R.id.btn_switch_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SwitchVideoActivity.class));
            }
        });
        */
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_find_file:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                //intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("video/*");
                startActivityForResult(intent, REQUEST_CODE_CHOOSE_FILE);
                break;
            case R.id.btn_play:
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CHOOSE_FILE) {
            if (resultCode == RESULT_OK) {
                final File file = new File(data.getData().getPath());
                final String[] split = file.getParent().split(":");
                Log.d("FilePath", split[1]);
            }
        }
    }
}

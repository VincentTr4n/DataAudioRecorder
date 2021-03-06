package com.example.cotye.dataaudiorecorder;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

import cafe.adriel.androidaudiorecorder.AndroidAudioRecorder;
import cafe.adriel.androidaudiorecorder.model.AudioChannel;
import cafe.adriel.androidaudiorecorder.model.AudioSampleRate;
import cafe.adriel.androidaudiorecorder.model.AudioSource;

public class MainActivity extends AppCompatActivity{

    private static final int REQUEST_RECORD_AUDIO = 0;
    private static String AUDIO_FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

    private String selectedCommander = "my_data";
    private String filePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(
                    new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimaryDark)));
        }


        Util.requestPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO);
        Util.requestPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);


        Button btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordAudio(v);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RECORD_AUDIO) {
            if (resultCode == RESULT_OK) {
//                Toast.makeText(this, "Audio recorded successfully! in " + filePath, Toast.LENGTH_SHORT).show();
                Log.e("MyTag", AUDIO_FILE_PATH);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Audio was not recorded", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void recordAudio(View v) {
        filePath = Util.createFilePath(AUDIO_FILE_PATH , selectedCommander);
//        Toast.makeText(this, filePath, Toast.LENGTH_LONG).show();
        AndroidAudioRecorder.with(this)
                // Required
                .setFilePath(filePath)
                .setColor(ContextCompat.getColor(this, R.color.recorder_bg))
                .setRequestCode(REQUEST_RECORD_AUDIO)

                // Optional
                .setSource(AudioSource.MIC)
                .setChannel(AudioChannel.MONO)
                .setSampleRate(AudioSampleRate.HZ_16000)
                .setAutoStart(false)
                .setKeepDisplayOn(true)
                // Start recording

                .record();
    }
}

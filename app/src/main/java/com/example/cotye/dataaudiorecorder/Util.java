package com.example.cotye.dataaudiorecorder;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.io.File;
import java.util.Random;

public class Util {
    public static void requestPermission(Activity activity, String permission) {
        if (ContextCompat.checkSelfPermission(activity, permission)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, 0);
        }
    }
    public static String createFilePath(String path, String commander){
        Random rnd = new Random();
        int num = Math.abs(rnd.nextInt());
        File file = new File(path + "/" + commander +"_" + String.valueOf(num) + ".wav");
        while(file.exists()){
            num = Math.abs(rnd.nextInt());
            file = new File(path + "/" + commander +"_" + String.valueOf(num) + ".wav");
        }
        return file.toString();
    }
}

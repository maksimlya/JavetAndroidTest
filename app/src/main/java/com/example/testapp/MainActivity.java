package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    public static void Log(String val) {
        Log.e("infra", val);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            InputStreamReader is = new InputStreamReader(getAssets().open("module.js"));
            CDTShell cdtShell = CDTShell.getInstance();
            cdtShell.setJsModule(is);
            cdtShell.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
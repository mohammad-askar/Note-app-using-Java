package com.mohammad.askar.noteappjava.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mohammad.askar.noteappjava.R;

public class SplashScreen extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initHandler();
    }

    private void initHandler() {

        handler = new Handler();
        handler.postDelayed(
                this::startActivity,
                1500L);

    }

    private void startActivity(){
        startActivity(new Intent(SplashScreen.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler = null;
    }
}
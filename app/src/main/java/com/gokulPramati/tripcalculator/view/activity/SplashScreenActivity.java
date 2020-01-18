package com.gokulPramati.tripcalculator.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.gokulPramati.tripcalculator.R;


public class SplashScreenActivity extends AppCompatActivity {

    private static final int UI_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        navigateToHome();

    }

    public void navigateToHome() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, UI_DELAY);
    }
}

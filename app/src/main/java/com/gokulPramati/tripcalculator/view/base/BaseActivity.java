package com.gokulPramati.tripcalculator.view.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.listener.TitleChangeListener;

public class BaseActivity extends AppCompatActivity implements TitleChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        BaseFragment baseFragment= new BaseFragment();
        baseFragment.setTitleChangeListener(this);
    }

    @Override
    public void onTitleChange(String title) {
        //Todo Title change
    }
}

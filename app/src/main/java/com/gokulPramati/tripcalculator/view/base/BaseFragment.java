package com.gokulPramati.tripcalculator.view.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.listener.TitleChangeListener;

public class BaseFragment extends Fragment {
    public TitleChangeListener titleChangeListener;
    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false);

    }

    /**
     * changet the title of the activity
     * @param titleChangeListener
     */
    public void setTitleChangeListener(TitleChangeListener titleChangeListener){
        this.titleChangeListener=titleChangeListener;
    }
}

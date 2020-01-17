package com.gokulPramati.tripcalculator.view.activity;

/**
 * Created by Gokulakrishnan Mani on 2020-01-14.
 */
import android.os.Bundle;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.database.DatabaseHelper;
import com.gokulPramati.tripcalculator.listener.TripClickListener;
import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.view.fragment.TripFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import java.util.List;

public class HomeActivity extends AppCompatActivity implements TripClickListener,View.OnClickListener {
FrameLayout fragmentContainer;
    BottomSheetBehavior sheetBehavior;
    LinearLayout layoutBottomSheet;
    RelativeLayout addTripButton;
    TripFragment tripFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
    }
    public void initView(){

        fragmentContainer= findViewById(R.id.fragmentContainer);
        layoutBottomSheet=findViewById(R.id.bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        addTripButton=findViewById(R.id.addTripButton);
        addTripButton.setOnClickListener(this);

        /**
         * bottom sheet state change listener
         * we are changing button text when sheet changed state
         * */
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        tripFragment= new TripFragment();
        Fragment fragment = tripFragment;
        tripFragment.setAddTripListener(this);
        loadFragment(fragment);
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragmentContainer, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
    public void databaseAction(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        // Creating tags
        Trip trip = new Trip("Goa Trip","Goa","goa description");
        trip.setCommonExpenditureAmount("7000");
        long tripId= databaseHelper.addTrip(trip);

        List<Trip> allTrip = databaseHelper.getAllTrip();
        for (Trip trp : allTrip) {
            Log.d("Trip Name", trp.getName());
        }
        // Updating tag name
        //  trip.setName("test trip");
        //  databaseHelper.updateTrip(trip);

        // Don't forget to close database connection
        databaseHelper.closeDB();
    }

    @Override
    public void onAddTripClick() {
        if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    @Override
    public void onTripAdded() {
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @Override
    public void onClick(View v) {
        /**
         * manually opening / closing bottom sheet on button click
         */
        if(v.getId()== R.id.addTripButton)

            tripFragment.validateTripDetails(new Trip.TripBuilder().id(1).name("uutiu").location("location").
                    description("descre").commonExpenditure("234.0").build());

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}

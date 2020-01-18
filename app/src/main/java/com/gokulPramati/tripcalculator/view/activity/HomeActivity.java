package com.gokulPramati.tripcalculator.view.activity;

/**
 * Created by Gokulakrishnan Mani on 2020-01-14.
 */

import android.content.Intent;
import android.os.Bundle;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.database.DatabaseHelper;
import com.gokulPramati.tripcalculator.listener.TripClickListener;
import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.model.TripReport;
import com.gokulPramati.tripcalculator.presenter.ReportPresenter;
import com.gokulPramati.tripcalculator.utils.CommonUtils;
import com.gokulPramati.tripcalculator.utils.JsonParser;
import com.gokulPramati.tripcalculator.view.fragment.MemberDetailsFragment;
import com.gokulPramati.tripcalculator.view.fragment.TripDetailFragment;
import com.gokulPramati.tripcalculator.view.fragment.TripFragment;
import com.gokulPramati.tripcalculator.viewcontract.ReportContract;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class HomeActivity extends AppCompatActivity implements TripClickListener, View.OnClickListener, ReportContract {
    FrameLayout fragmentContainer;
    BottomSheetBehavior tripSheetBehavior;
    BottomSheetBehavior memberSheetBehavior;
    BottomSheetBehavior expenditureSheetBehavior;
    LinearLayout layoutTripBottomSheet;
    LinearLayout layoutMemberBottomSheet;
    LinearLayout layoutExpenditureBottomSheet;
    RelativeLayout addTripButton;
    TripFragment tripFragment;
    ReportPresenter reportPresenter;
    EditText tripNameTv,tripLocationTv,tripDescTv,commonExpenditureTv;
    String tripName,tripLocation,tripDesc,commonExp=null;
    TripDetailFragment tripDetailFragment;
    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
    }

    public void initView() {
       // databaseAction();
        fragmentContainer = findViewById(R.id.fragmentContainer);
        layoutTripBottomSheet = findViewById(R.id.trip_bottom_sheet);
        layoutMemberBottomSheet= findViewById(R.id.member_bottom_sheet);
        layoutExpenditureBottomSheet = findViewById(R.id.expend_bottom_sheet);
        tripSheetBehavior = BottomSheetBehavior.from(layoutTripBottomSheet);
        memberSheetBehavior=BottomSheetBehavior.from(layoutMemberBottomSheet);
        expenditureSheetBehavior=BottomSheetBehavior.from(layoutExpenditureBottomSheet);
        addTripButton = findViewById(R.id.addTripButton);
        addTripButton.setOnClickListener(this);
        reportPresenter=new ReportPresenter(this);
        tripNameTv=findViewById(R.id.input_name);
        tripLocationTv=findViewById(R.id.input_location);
        tripDescTv=findViewById(R.id.input_desc);
        commonExpenditureTv=findViewById(R.id.input_expenditure);
        tripFragment = new TripFragment();
        Fragment fragment = tripFragment;
        tripFragment.setAddTripListener(this);
        loadFragment(fragment);
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragmentContainer, fragment);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void onAddTripClick() {
        if (tripSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            tripSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            tripSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    @Override
    public void onTripAdded() {
        tripSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @Override
    public void onTriItemClick(Trip trip) {
        tripDetailFragment = new TripDetailFragment();
        Fragment fragment = tripDetailFragment;
        //tripDetailFragment.setAddTripListener(this);
        menuItem.setVisible(true);
        loadFragment(fragment);
    }

    @Override
    public void onClick(View v) {
        /**
         * manually opening / closing bottom sheet on button click
         */
        if (v.getId() == R.id.addTripButton) {
            tripName = tripNameTv.getText().toString().trim();
            tripLocation = tripLocationTv.getText().toString().trim();
            tripDesc = tripDescTv.getText().toString().trim();
            commonExp = commonExpenditureTv.getText().toString().trim();

            tripFragment.validateTripDetails(new Trip.TripBuilder().name(tripName).location(tripLocation).
                    description(tripDesc).commonExpenditure(commonExp).build());
        }else if (v.getId() == R.id.addTripMember) {
//            tripName = tripNameTv.getText().toString().trim();
//            tripLocation = tripLocationTv.getText().toString().trim();
//            tripDesc = tripDescTv.getText().toString().trim();
//            commonExp = commonExpenditureTv.getText().toString().trim();
//
//            tripFragment.validateTripDetails(new Trip.TripBuilder().name(tripName).location(tripLocation).
//                    description(tripDesc).commonExpenditure(commonExp).build());
       }else if (v.getId() == R.id.addExpenditure) {
//            tripName = tripNameTv.getText().toString().trim();
//            tripLocation = tripLocationTv.getText().toString().trim();
//            tripDesc = tripDescTv.getText().toString().trim();
//            commonExp = commonExpenditureTv.getText().toString().trim();
//
//            tripFragment.validateTripDetails(new Trip.TripBuilder().name(tripName).location(tripLocation).
//                    description(tripDesc).commonExpenditure(commonExp).build());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
         menuItem = menu.findItem(R.id.action_generate_report);
         menuItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_generate_report) {
            generateReport(1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void generateReport(int trip_id) {
        reportPresenter.generateReport(HomeActivity.this,trip_id);
    }

    @Override
    public void onReportError(String error) {
        CommonUtils.showLongToast(error, this);
    }

    @Override
    public void onReportReady(Trip trip, TripReport reports) {
        Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
        String tripData=null;
        String reportData=null;
        try {
            tripData = JsonParser.ToJsonString(trip);
             reportData = JsonParser.ToJsonString(reports);
        }catch (Exception e){
            CommonUtils.printInfo(e.getMessage());
        }
        intent.putExtra(CommonUtils.TRIP_DATA,tripData);
        intent.putExtra(CommonUtils.REPORT_DATA,reportData);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
       // int count = getSupportFragmentManager().getBackStackEntryCount();
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if(fragment instanceof TripFragment){
            if(tripSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
                tripSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }else{
                super.onBackPressed();
                finish();
            }
        }else if(fragment instanceof TripDetailFragment){
            if(memberSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
                memberSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }else{
                getSupportFragmentManager().popBackStack();
                menuItem.setVisible(false);
            }
        }else if(fragment instanceof MemberDetailsFragment){
            if(expenditureSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
                expenditureSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }else{
                getSupportFragmentManager().popBackStack();
            }
        }

    }

    public void handleMenuVisibility(){
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if(menuItem!=null){
            if(fragment!=null && (fragment instanceof TripDetailFragment)){
                menuItem.setVisible(true);
            }else{
                menuItem.setVisible(false);
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        handleMenuVisibility();
    }
}

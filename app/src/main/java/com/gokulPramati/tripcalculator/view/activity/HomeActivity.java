package com.gokulPramati.tripcalculator.view.activity;

/**
 * Created by Gokulakrishnan Mani on 2020-01-14.
 */

import android.content.Intent;
import android.os.Bundle;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.listener.TripClickListener;
import com.gokulPramati.tripcalculator.listener.TripDetailClickListener;
import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.model.TripMember;
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

public class HomeActivity extends AppCompatActivity implements TripClickListener, TripDetailClickListener,
        View.OnClickListener, ReportContract {
    FrameLayout fragmentContainer;
    BottomSheetBehavior tripSheetBehavior;
    BottomSheetBehavior memberSheetBehavior;
    BottomSheetBehavior expenditureSheetBehavior;
    LinearLayout layoutTripBottomSheet;
    LinearLayout layoutMemberBottomSheet;
    LinearLayout layoutExpenditureBottomSheet;
    RelativeLayout addTripButton, addMmberButton;
    TripFragment tripFragment;
    ReportPresenter reportPresenter;
    //Trip Input Fields
    EditText tripNameTv, tripLocationTv, tripDescTv, commonExpenditureTv;
    String tripName, tripLocation, tripDesc, commonExp = null;
    //Member Input Fields
    EditText memberNameTv, memberEmailTv, memberPhoneTv, memberInitialPayTv;
    String memberName, memberEmail, memberPhone, memberInitialPay;

    //Expenditure Input Fields
    EditText expDescTv, expAmountTv;
    String expDesc, expAmount;

    TripDetailFragment tripDetailFragment;
    MenuItem menuItem;
    int currentTripId;

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
        layoutMemberBottomSheet = findViewById(R.id.member_bottom_sheet);
        layoutExpenditureBottomSheet = findViewById(R.id.expend_bottom_sheet);
        tripSheetBehavior = BottomSheetBehavior.from(layoutTripBottomSheet);
        memberSheetBehavior = BottomSheetBehavior.from(layoutMemberBottomSheet);
        expenditureSheetBehavior = BottomSheetBehavior.from(layoutExpenditureBottomSheet);
        addTripButton = findViewById(R.id.addTripButton);
        addMmberButton = findViewById(R.id.addTripMember);
        addTripButton.setOnClickListener(this);
        addMmberButton.setOnClickListener(this);
        reportPresenter = new ReportPresenter(this);

        //Trip Field Initialization
        tripNameTv = findViewById(R.id.input_name);
        tripLocationTv = findViewById(R.id.input_location);
        tripDescTv = findViewById(R.id.input_desc);
        commonExpenditureTv = findViewById(R.id.input_expenditure);

        //Member Field Initialization
        memberNameTv = findViewById(R.id.input_member_name);
        memberEmailTv = findViewById(R.id.input_email);
        memberPhoneTv = findViewById(R.id.input_phone);
        memberInitialPayTv = findViewById(R.id.input_initial_pay);

        //load TripFragment
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
        String tripString = JsonParser.ToJsonString(trip);
        if(tripString!=null) {
            Bundle bundl = new Bundle();
            bundl.putString(CommonUtils.TRIP_DATA, tripString);
            fragment.setArguments(bundl);
        }
        tripDetailFragment.setAddTripListDetailListener(this);
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

        } else if (v.getId() == R.id.addTripMember) {
            memberName = memberNameTv.getText().toString().trim();
            memberEmail = memberEmailTv.getText().toString().trim();
            memberPhone = memberPhoneTv.getText().toString().trim();
            memberInitialPay = memberInitialPayTv.getText().toString().trim();

            tripDetailFragment.validateTripMemberDetails(new TripMember.TripMemberBuilder().name(memberName)
                    .email(memberEmail)
                    .phoneNumber(memberPhone)
                    .tripId(currentTripId)
                    .initialContribution(memberInitialPay)
                    .build());
        } else if (v.getId() == R.id.addExpenditure) {
//            tripName = tripNameTv.getText().toString().trim();
//            tripLocation = tripLocationTv.getText().toString().trim();
//            tripDesc = tripDescTv.getText().toString().trim();
//            commonExp = commonExpenditureTv.getText().toString().trim();
//
//            tripFragment.validateTripMemberDetails(new Trip.TripBuilder().name(tripName).location(tripLocation).
//                    description(tripDesc).commonExpenditure(commonExp).build());
        }
        CommonUtils.hideKeyboard(HomeActivity.this);
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
            generateReport(currentTripId);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void generateReport(int trip_id) {
        reportPresenter.generateReport(HomeActivity.this, trip_id);
    }

    @Override
    public void onReportError(String error) {
        CommonUtils.showLongToast(error, this);
    }

    @Override
    public void onReportReady(Trip trip, TripReport reports) {
        Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
        String tripData = null;
        String reportData = null;
        try {
            tripData = JsonParser.ToJsonString(trip);
            reportData = JsonParser.ToJsonString(reports);
        } catch (Exception e) {
            CommonUtils.printInfo(e.getMessage());
        }
        intent.putExtra(CommonUtils.TRIP_DATA, tripData);
        intent.putExtra(CommonUtils.REPORT_DATA, reportData);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment instanceof TripFragment) {
            if (tripSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                tripSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            } else {
                super.onBackPressed();
                finish();
            }
        } else if (fragment instanceof TripDetailFragment) {
            if (memberSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                memberSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            } else {
                getSupportFragmentManager().popBackStack();
                menuItem.setVisible(false);
            }
        } else if (fragment instanceof MemberDetailsFragment) {
            if (expenditureSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                expenditureSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            } else {
                getSupportFragmentManager().popBackStack();
            }
        }

    }

    public void handleMenuVisibility() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (menuItem != null) {
            if (fragment != null && (fragment instanceof TripDetailFragment)) {
                menuItem.setVisible(true);
            } else {
                menuItem.setVisible(false);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        handleMenuVisibility();
    }

    @Override
    public void onAddMemberClick(int tripId) {
        currentTripId=tripId;
        if (memberSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            memberSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            memberSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    @Override
    public void onMemberAdded() {
        memberSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @Override
    public void onMemberItemClick(TripMember tripMember) {

    }
}

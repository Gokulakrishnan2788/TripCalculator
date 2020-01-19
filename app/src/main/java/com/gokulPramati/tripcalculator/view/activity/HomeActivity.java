package com.gokulPramati.tripcalculator.view.activity;

/**
 * Created by Gokulakrishnan Mani on 2020-01-14.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.listener.MemberDetailClickListener;
import com.gokulPramati.tripcalculator.listener.TripClickListener;
import com.gokulPramati.tripcalculator.listener.TripDetailClickListener;
import com.gokulPramati.tripcalculator.model.MemberExpenditures;
import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.model.TripMember;
import com.gokulPramati.tripcalculator.model.TripReport;
import com.gokulPramati.tripcalculator.presenter.ReportPresenter;
import com.gokulPramati.tripcalculator.utils.CommonUtils;
import com.gokulPramati.tripcalculator.utils.JsonParser;
import com.gokulPramati.tripcalculator.view.base.BaseActivity;
import com.gokulPramati.tripcalculator.view.fragment.ExpenditureDialg;
import com.gokulPramati.tripcalculator.view.fragment.MemberDetailsFragment;
import com.gokulPramati.tripcalculator.view.fragment.TripDetailFragment;
import com.gokulPramati.tripcalculator.view.fragment.TripFragment;
import com.gokulPramati.tripcalculator.viewcontract.ReportContract;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class HomeActivity extends BaseActivity implements TripClickListener, TripDetailClickListener,
        View.OnClickListener, MemberDetailClickListener, ReportContract {

    FrameLayout fragmentContainer;
    BottomSheetBehavior tripSheetBehavior;
    BottomSheetBehavior memberSheetBehavior;
    BottomSheetBehavior expenditureSheetBehavior;
    LinearLayout layoutTripBottomSheet;
    LinearLayout layoutMemberBottomSheet;
    LinearLayout layoutExpenditureBottomSheet;
    RelativeLayout addTripButton, addMmberButton, addExpButton;
    TripFragment tripFragment;
    ReportPresenter reportPresenter;
    //Trip Input Fields
    EditText tripNameTv, tripLocationTv, tripDescTv, commonExpenditureTv;
    String tripName, tripLocation, tripDesc, commonExp = null;
    //Member Input Fields
    EditText memberNameTv, memberEmailTv, memberPhoneTv, memberInitialPayTv;
    String memberName, memberEmail, memberPhone, memberInitialPay;

    //Expend Input Fields
    EditText expenditureDescTv;
    EditText expenditureAmountTv;
    String expDesc, expAmount;

    TripDetailFragment tripDetailFragment;
    MemberDetailsFragment memberDetailsFragment;
    MenuItem menuItem;
    long currentTripId;
    long currentMemberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
    }

    public void initView() {
        fragmentContainer = findViewById(R.id.fragmentContainer);
        layoutTripBottomSheet = findViewById(R.id.trip_bottom_sheet);
        layoutMemberBottomSheet = findViewById(R.id.member_bottom_sheet);
        layoutExpenditureBottomSheet = findViewById(R.id.expend_bottom_sheet);
        tripSheetBehavior = BottomSheetBehavior.from(layoutTripBottomSheet);
        memberSheetBehavior = BottomSheetBehavior.from(layoutMemberBottomSheet);
        expenditureSheetBehavior = BottomSheetBehavior.from(layoutExpenditureBottomSheet);
        addTripButton = findViewById(R.id.addTripButton);
        addMmberButton = findViewById(R.id.addTripMember);
        addExpButton = findViewById(R.id.addExpenditure);
        addTripButton.setOnClickListener(this);
        addMmberButton.setOnClickListener(this);
        addExpButton.setOnClickListener(this);
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

        //Expenditure Field Initialization
        expenditureDescTv = findViewById(R.id.input_exp_desc);
        expenditureAmountTv = findViewById(R.id.input_amount);

        //load TripFragment
        tripFragment = new TripFragment();
        Fragment fragment = tripFragment;
        tripFragment.setAddTripListener(this);
        tripFragment.setTitleChangeListener(this);
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
        if (tripString != null) {
            Bundle bundl = new Bundle();
            bundl.putString(CommonUtils.TRIP_DATA, tripString);
            fragment.setArguments(bundl);
        }
        tripDetailFragment.setAddTripListDetailListener(this);
        tripDetailFragment.setTitleChangeListener(this);

        menuItem.setVisible(true);
        currentTripId = trip.getId();
        loadFragment(fragment);
    }

    @Override
    public void onClick(View v) {
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
                    .tripId((int) currentTripId)
                    .initialContribution(memberInitialPay)
                    .build());
        } else if (v.getId() == R.id.addExpenditure) {
            expDesc = expenditureDescTv.getText().toString().trim();
            expAmount = expenditureAmountTv.getText().toString().trim();
            memberDetailsFragment.validateTripMemberExpenditure(new MemberExpenditures(currentTripId, currentMemberId, expAmount, expDesc));
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

            ExpenditureDialg dialg = new ExpenditureDialg(new ExpenditureDialg.ExpenditureDialogListener() {
                @Override
                public void onFinishUserDialog(String commonExpenditure) {
                    if (commonExpenditure != null && !commonExpenditure.isEmpty()) {
                        updateCommonExpense(commonExpenditure);
                    }
                    generateReport((int) currentTripId);

                }
            });
            dialg.show(getSupportFragmentManager(), "");
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
                onTitleChange(getString(R.string.trip_list));
                menuItem.setVisible(false);
            }
        } else if (fragment instanceof MemberDetailsFragment) {
            if (expenditureSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                expenditureSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            } else {
                getSupportFragmentManager().popBackStack();
                onTitleChange(getString(R.string.trip_details));
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
        currentTripId = tripId;
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
        memberDetailsFragment = new MemberDetailsFragment();
        Fragment fragment = memberDetailsFragment;
        String tripMemberString = JsonParser.ToJsonString(tripMember);
        if (tripMemberString != null) {
            Bundle bundle = new Bundle();
            bundle.putString(CommonUtils.MEMBER_DATA, tripMemberString);
            fragment.setArguments(bundle);
        }
        memberDetailsFragment.setMemberDetailListener(this);
        memberDetailsFragment.setTitleChangeListener(this);
        menuItem.setVisible(false);
        loadFragment(fragment);

    }

    @Override
    public void onTitleChange(String title) {
        if (!isFinishing() && getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }


    @Override
    public void onAddExpenditureClick(int member_id, int trip_id) {
        currentTripId = trip_id;
        currentMemberId = member_id;
        if (expenditureSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            expenditureSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            expenditureSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    @Override
    public void onExpenditureAdded() {
        expenditureSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    public void updateCommonExpense(String commonExp) {
        tripDetailFragment.updateCommonExpense(commonExp, (int) currentTripId);
        tripFragment.updateCommonExpense(commonExp, (int) currentTripId);

    }
}

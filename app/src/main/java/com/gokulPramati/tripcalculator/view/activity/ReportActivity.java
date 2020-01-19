package com.gokulPramati.tripcalculator.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.adapter.ReportAdapter;
import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.model.TripReport;
import com.gokulPramati.tripcalculator.utils.CommonUtils;
import com.gokulPramati.tripcalculator.utils.JsonParser;
import com.gokulPramati.tripcalculator.view.base.BaseActivity;

public class ReportActivity extends BaseActivity {

    TextView tripNameTv, tripLocationTv, tripDescTv, commonExpenditureTv, totalExpTv;
    String tripName, tripLocation, tripDesc, commonExp, totalExp;
    RecyclerView memberReportRv;
    ReportAdapter reportAdapter;
    Trip trip;
    TripReport tripReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            trip = JsonParser.ToObject(intent.getStringExtra(CommonUtils.TRIP_DATA), Trip.class);
            tripReport = JsonParser.ToObject(intent.getStringExtra(CommonUtils.REPORT_DATA), TripReport.class);
        }
        tripNameTv = findViewById(R.id.tripName);
        tripLocationTv = findViewById(R.id.tripLocation);
        tripDescTv = findViewById(R.id.tripDescription);
        commonExpenditureTv = findViewById(R.id.tripCommExp);
        totalExpTv = findViewById(R.id.tripTotalExp);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        memberReportRv = findViewById(R.id.memberReportRv);


        updateView();
    }

    public void updateView() {
        tripNameTv.setText(trip.getName());
        tripLocationTv.setText(trip.getLocation());
        tripDescTv.setText(trip.getDescription());
        commonExpenditureTv.setText(trip.getCommonExpenditureAmount());
        // totalExpTv.setText(trip.get);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        memberReportRv.setLayoutManager(linearLayoutManager);
        memberReportRv.setItemAnimator(new DefaultItemAnimator());
        reportAdapter = new ReportAdapter(ReportActivity.this, tripReport);
        memberReportRv.setAdapter(reportAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

}

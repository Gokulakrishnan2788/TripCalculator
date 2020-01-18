package com.gokulPramati.tripcalculator.view.fragment;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.adapter.ReportAdapter;
import com.gokulPramati.tripcalculator.adapter.TripAdapter;
import com.gokulPramati.tripcalculator.database.DatabaseHelper;
import com.gokulPramati.tripcalculator.listener.TripClickListener;
import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.presenter.TripPresenter;
import com.gokulPramati.tripcalculator.utils.CommonUtils;
import com.gokulPramati.tripcalculator.view.activity.HomeActivity;
import com.gokulPramati.tripcalculator.view.activity.ReportActivity;
import com.gokulPramati.tripcalculator.viewcontract.TripContract;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TripFragment extends Fragment implements TripContract {

    private DatabaseHelper databaseHelper;
    private View view;
    private TripPresenter tripPresenter;
    private TripClickListener addTripClickListener;
    private RecyclerView tripRecyclerView;
    private TripAdapter tripAdapter;
    private List<Trip> tripList = new ArrayList<>();
    private TextView noTripTv;
    public TripFragment() {
        // Required empty public constructor
    }

    public void setAddTripListener(TripClickListener addTripListener){
       this.addTripClickListener=addTripListener;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view== null)
        view =inflater.inflate(R.layout.fragment_home, container, false);
        initView();

        return  view;
    }

   public void initView(){
       DatabaseHelper databaseHelper = DatabaseHelper.getInstance(getContext());
        tripPresenter = new TripPresenter(this,databaseHelper);
       FloatingActionButton fab = view.findViewById(R.id.fab);
       noTripTv=view.findViewById(R.id.no_tirp_tv);
       tripRecyclerView=view.findViewById(R.id.tripRv);
       fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               addTripClickListener.onAddTripClick();
           }
       });
       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
       tripRecyclerView.setLayoutManager(linearLayoutManager);
       tripRecyclerView.setItemAnimator(new DefaultItemAnimator());
       tripList= DatabaseHelper.getInstance(getContext()).getAllTrip();
       tripAdapter =new TripAdapter(getContext(),tripList,addTripClickListener);
       if(tripList.isEmpty()){
           noTripTv.setVisibility(View.VISIBLE);
           tripRecyclerView.setVisibility(View.GONE);
       }else{
         showList();
       }
       tripRecyclerView.setAdapter(tripAdapter);
   }

   public void showList(){
       noTripTv.setVisibility(View.GONE);
       tripRecyclerView.setVisibility(View.VISIBLE);
   }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void addTrip(Trip trip) {
    CommonUtils.showLongToast("Trip Added",getContext());
    tripList.add(trip);
    tripAdapter.notifyDataSetChanged();
    showList();
    addTripClickListener.onTripAdded();
    }

    @Override
    public void updateTrip(Trip trip) {

    }

    @Override
    public void deleteTrip(Trip trip) {

    }

    @Override
    public void setTripNameError() {
        CommonUtils.showLongToast(getContext().getString(R.string.invalid_name), getContext());
    }

    @Override
    public void setLocationError() {
        CommonUtils.showLongToast(getContext().getString(R.string.invalid_location), getContext());
    }

    @Override
    public void setDescriptionError() {
        CommonUtils.showLongToast(getContext().getString(R.string.invalid_desc), getContext());
    }

    @Override
    public void setCommonExpenditureError() {
        CommonUtils.showLongToast(getContext().getString(R.string.invlaid_exp), getContext());
    }

    @Override
    public void onValidationSuccess(Trip trip) {
        CommonUtils.showLongToast(getContext().getString(R.string.validation_su), getContext());
        tripPresenter.addTripData(trip);
    }

    public void validateTripDetails(Trip trip)
    {
        tripPresenter.validateTripData(trip);
    }

    public void navigateToTripDetails(Trip trip){

    }

    public void updateTripList(){

    }


}

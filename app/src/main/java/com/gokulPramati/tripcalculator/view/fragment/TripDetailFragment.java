package com.gokulPramati.tripcalculator.view.fragment;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.adapter.MemberAdapter;
import com.gokulPramati.tripcalculator.adapter.TripAdapter;
import com.gokulPramati.tripcalculator.database.DatabaseHelper;
import com.gokulPramati.tripcalculator.listener.TitleChangeListener;
import com.gokulPramati.tripcalculator.listener.TripClickListener;
import com.gokulPramati.tripcalculator.listener.TripDetailClickListener;
import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.model.TripMember;
import com.gokulPramati.tripcalculator.presenter.TripDetailPresenter;
import com.gokulPramati.tripcalculator.presenter.TripPresenter;
import com.gokulPramati.tripcalculator.utils.CommonUtils;
import com.gokulPramati.tripcalculator.utils.JsonParser;
import com.gokulPramati.tripcalculator.view.activity.HomeActivity;
import com.gokulPramati.tripcalculator.view.base.BaseFragment;
import com.gokulPramati.tripcalculator.viewcontract.TripDetailContract;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TripDetailFragment extends BaseFragment implements TripDetailContract {

    View view;
    TripDetailPresenter tripDetailPresenter;
    TextView noMemberView;
    RecyclerView tripMemberRecyclerView;
    TripDetailClickListener tripDetailClickListener;
    List<TripMember> memberList;
    MemberAdapter memberAdapter;
    TextView tripNameTv, tripLocationTv, tripDescTv, tripCommonExpTv;
    Trip currentTrip;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setAddTripListDetailListener(TripDetailClickListener tripListDetailListener) {
        this.tripDetailClickListener = tripListDetailListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null)
            view = inflater.inflate(R.layout.fragment_trip_detail, container, false);
        initView();
        return view;
    }

    public void initView() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(getContext());

        Bundle bundle = getArguments();
        currentTrip = null;
        if (bundle != null) {
            String tripString = bundle.getString(CommonUtils.TRIP_DATA);
            if (tripString != null) {
                currentTrip = JsonParser.ToObject(tripString, Trip.class);

            }
        }


        tripNameTv = view.findViewById(R.id.tripName);
        tripLocationTv = view.findViewById(R.id.tripLocation);
        tripDescTv = view.findViewById(R.id.tripDescription);
        tripCommonExpTv = view.findViewById(R.id.tripCommExp);
        if (currentTrip != null) {
            setTripData(currentTrip);
            memberList = DatabaseHelper.getInstance(getContext()).getAllTripMember((int)currentTrip.getId());
        }
        tripDetailPresenter = new TripDetailPresenter(this);
        FloatingActionButton fab = view.findViewById(R.id.add_member);
        noMemberView = view.findViewById(R.id.no_member_tv);
        tripMemberRecyclerView = view.findViewById(R.id.memberDetailRv);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tripDetailClickListener.onAddMemberClick((int)currentTrip.getId());
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        tripMemberRecyclerView.setLayoutManager(linearLayoutManager);
        tripMemberRecyclerView.setItemAnimator(new DefaultItemAnimator());

        memberAdapter = new MemberAdapter(getContext(), memberList, tripDetailClickListener);
        if (memberList.isEmpty()) {
            noMemberView.setVisibility(View.VISIBLE);
            tripMemberRecyclerView.setVisibility(View.GONE);
        } else {
            showList();
        }
        tripMemberRecyclerView.setAdapter(memberAdapter);
    }

    public void setTripData(Trip tripData) {
        tripNameTv.setText(tripData.getName());
        tripLocationTv.setText(tripData.getLocation());
        tripDescTv.setText(tripData.getDescription());
        tripCommonExpTv.setText(tripData.getCommonExpenditureAmount());
    }

    public void showList() {
        noMemberView.setVisibility(View.GONE);
        tripMemberRecyclerView.setVisibility(View.VISIBLE);
    }

    public void validateTripMemberDetails(TripMember tripMember) {
        tripDetailPresenter.validateMemberData(tripMember);
    }
   public void updateCommonExpense(String commonExp,int tripId){
       tripDetailPresenter.updateCommonExpense(commonExp,tripId,getActivity());
       currentTrip.setCommonExpenditureAmount(commonExp);
       tripCommonExpTv.setText(commonExp);
   }
    @Override
    public void addMember(TripMember tripMember) {
        CommonUtils.showLongToast("Member Added", getContext());
        memberList.add(tripMember);
        memberAdapter.notifyDataSetChanged();
        showList();
        tripDetailClickListener.onMemberAdded();

    }

    @Override
    public void updateMember(TripMember tripMember) {

    }

    @Override
    public void deleteMember(TripMember tripMember) {

    }

    @Override
    public void setMemberNameError() {
        CommonUtils.showLongToast(getContext().getString(R.string.invalid_name), getContext());
    }

    @Override
    public void setMemberEmailError() {
        CommonUtils.showLongToast(getContext().getString(R.string.invalid_email), getContext());
    }

    @Override
    public void setMemberPhoneError() {
        CommonUtils.showLongToast(getContext().getString(R.string.invalid_phone), getContext());
    }

    @Override
    public void setMemberInitialContributionError() {
        CommonUtils.showLongToast(getContext().getString(R.string.invalid_contir), getContext());
    }

    @Override
    public void onValidationSuccess(TripMember tripMember) {
        CommonUtils.showLongToast(getContext().getString(R.string.validation_su), getContext());
        tripDetailPresenter.addMemberData(tripMember, getActivity());

    }

    @Override
    public void onResume() {
        super.onResume();
       titleChangeListener.onTitleChange(getString(R.string.trip_details));
    }
}

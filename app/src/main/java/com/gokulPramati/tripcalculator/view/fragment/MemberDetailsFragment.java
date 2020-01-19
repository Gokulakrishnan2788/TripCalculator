package com.gokulPramati.tripcalculator.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.adapter.ExpenditureAdapter;
import com.gokulPramati.tripcalculator.adapter.MemberAdapter;
import com.gokulPramati.tripcalculator.database.DatabaseHelper;

import com.gokulPramati.tripcalculator.listener.MemberDetailClickListener;
import com.gokulPramati.tripcalculator.model.MemberExpenditures;

import com.gokulPramati.tripcalculator.model.TripMember;
import com.gokulPramati.tripcalculator.presenter.MemberDetailPresenter;
import com.gokulPramati.tripcalculator.utils.CommonUtils;
import com.gokulPramati.tripcalculator.utils.JsonParser;
import com.gokulPramati.tripcalculator.view.base.BaseFragment;
import com.gokulPramati.tripcalculator.viewcontract.MemberDetailContract;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MemberDetailsFragment extends BaseFragment implements MemberDetailContract {

View view;
TripMember tripMember;
TextView memberNameTv,memberEmailTv,memberPhoneTv,memberInitialPayTv;
List<MemberExpenditures> memberExpendituresList  = new ArrayList<>();
MemberDetailPresenter memberDetailPresenter;
TextView noExpendView;
RecyclerView expenditureRecylerView;
MemberDetailClickListener memberDetailClickListener;
ExpenditureAdapter expenditureAdapter;

public void setMemberDetailListener(MemberDetailClickListener memberDetailListener){
    this.memberDetailClickListener=memberDetailListener;
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null)
            view = inflater.inflate(R.layout.fragment_member_details, container, false);
        initView();
        return view;

    }
    public void initView() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(getContext());

        Bundle bundle = getArguments();
        tripMember = null;
        if (bundle != null) {
            String tripString = bundle.getString(CommonUtils.MEMBER_DATA);
            if (tripString != null) {
                tripMember = JsonParser.ToObject(tripString, TripMember.class);
            }
        }

        memberNameTv = view.findViewById(R.id.memberName);
        memberEmailTv = view.findViewById(R.id.memberEmail);
        memberPhoneTv = view.findViewById(R.id.memberPhone);
        memberInitialPayTv = view.findViewById(R.id.memberInitialPay);
        if (tripMember != null) {
            setTripMemberExpData();
            memberExpendituresList = DatabaseHelper.getInstance(getContext()).getAllTripMemberExpenditure(tripMember.getTripId(),tripMember.getId());
        }
        memberDetailPresenter = new MemberDetailPresenter(this);
        FloatingActionButton fab = view.findViewById(R.id.add_expenditure);
        noExpendView = view.findViewById(R.id.no_member_tv);
        expenditureRecylerView = view.findViewById(R.id.expenditureRv);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memberDetailClickListener.onAddExpenditureClick((int)tripMember.getId(),(int)tripMember.getTripId());
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        expenditureRecylerView.setLayoutManager(linearLayoutManager);
        expenditureRecylerView.setItemAnimator(new DefaultItemAnimator());

        expenditureAdapter = new ExpenditureAdapter(getContext(), memberExpendituresList);
        if (memberExpendituresList.isEmpty()) {
            noExpendView.setVisibility(View.VISIBLE);
            expenditureRecylerView.setVisibility(View.GONE);
        } else {
            showList();
        }
        expenditureRecylerView.setAdapter(expenditureAdapter);
    }

    public void showList() {
        noExpendView.setVisibility(View.GONE);
        expenditureRecylerView.setVisibility(View.VISIBLE);
    }

    public void setTripMemberExpData() {
        memberNameTv.setText(tripMember.getName());
        memberEmailTv.setText(tripMember.getEmail());
        memberPhoneTv .setText(tripMember.getPhoneNumber());
        memberInitialPayTv.setText(tripMember.getInitialContribution());
    }

    public void validateTripMemberExpenditure(MemberExpenditures memberExpenditures) {
        memberDetailPresenter.validateExpenditureData(memberExpenditures);
    }

    @Override
    public void addExpenditure(MemberExpenditures memberExpenditures) {
        CommonUtils.showLongToast("exp Added", getContext());
        memberExpendituresList.add(memberExpenditures);
        expenditureAdapter.notifyDataSetChanged();
        showList();
        memberDetailClickListener.onExpenditureAdded();
    }

    @Override
    public void updateExpenditure(MemberExpenditures memberExpenditures) {

    }

    @Override
    public void deleteExpenditure(MemberExpenditures memberExpenditures) {

    }

    @Override
    public void setExpenditureDescError() {
        CommonUtils.showLongToast(getContext().getString(R.string.invalid_desc), getContext());
    }

    @Override
    public void setExpenditureAmountError() {
        CommonUtils.showLongToast(getContext().getString(R.string.invalid_amount), getContext());
    }

    @Override
    public void onValidationSuccess(MemberExpenditures memberExpenditures) {
        CommonUtils.showLongToast(getContext().getString(R.string.validation_su), getContext());
        memberDetailPresenter.addExpenditure(memberExpenditures,getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        titleChangeListener.onTitleChange(getString(R.string.member_details));
    }
}

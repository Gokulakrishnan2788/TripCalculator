package com.gokulPramati.tripcalculator.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.model.MemberExpenditures;
import com.gokulPramati.tripcalculator.viewcontract.MemberDetailContract;

public class MemberDetailsFragment extends Fragment implements MemberDetailContract {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_member_details, container, false);
    }

    @Override
    public void addExpenditure(MemberExpenditures memberExpenditures) {

    }

    @Override
    public void updateExpenditure(MemberExpenditures memberExpenditures) {

    }

    @Override
    public void deleteExpenditure(MemberExpenditures memberExpenditures) {

    }

    @Override
    public void setExpenditureDescError() {

    }

    @Override
    public void setExpenditureAmountError() {

    }

    @Override
    public void onValidationSuccess(MemberExpenditures memberExpenditures) {

    }
}

package com.gokulPramati.tripcalculator.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.fragment.app.DialogFragment;

import com.gokulPramati.tripcalculator.R;

/**
 * Created by Gokulakrishnan Mani on 2020-01-19.
 */
public class ExpenditureDialg extends DialogFragment {
    private EditText mEditText;
    private RelativeLayout proceedButton;
    ExpenditureDialogListener expenditureDialogListener;


    public interface ExpenditureDialogListener {
        void onFinishUserDialog(String commonExpenditure);
    }
    public ExpenditureDialg(ExpenditureDialogListener expenditureDialogListener){
        this.expenditureDialogListener=expenditureDialogListener;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dialog, container);
        mEditText = (EditText) view.findViewById(R.id.input_amount);
        proceedButton=view.findViewById(R.id.proceed);
        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenditureDialogListener.onFinishUserDialog(mEditText.getText().toString());
                dismiss();
            }
        });
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle(getResources().getString(R.string.do_you_want_to_update_common_expediture_optional));

        return view;
    }
}

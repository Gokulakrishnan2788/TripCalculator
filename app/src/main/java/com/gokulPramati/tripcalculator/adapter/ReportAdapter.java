package com.gokulPramati.tripcalculator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.model.TripReport;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class ReportAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    TripReport tripReport;

    public ReportAdapter(Context context, TripReport tripReport) {
        this.context = context;
        this.tripReport = tripReport;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType % 2 == 0) {
            View view = inflater.inflate(R.layout.layout_member_report_light, parent, false);
            viewHolder = new ReportViewHolderLight(view);
        } else {
            View view = inflater.inflate(R.layout.layout_member_report_dark, parent, false);
            viewHolder = new ReportViewHolderDark(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()%2) {
            case 0:
                ReportViewHolderLight vh1 = (ReportViewHolderLight) holder;
                configureReportViewHolderLight(vh1, position);
                break;
            case 1:
                ReportViewHolderDark vh2 = (ReportViewHolderDark) holder;
                configureReportViewHolderDark(vh2, position);
                break;
            default:
                ReportViewHolderLight vh3 = (ReportViewHolderLight) holder;
                configureReportViewHolderLight(vh3, position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return tripReport.getMemberReports().size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ReportViewHolderLight extends RecyclerView.ViewHolder {
        TextView memberNameTv, initilaPayTv, totalExpTv, balPayTv;
        public ReportViewHolderLight(View v) {
            super(v);
            memberNameTv = v.findViewById(R.id.member_name);
            initilaPayTv = v.findViewById(R.id.intialpay);
            totalExpTv = v.findViewById(R.id.totalexp);
            balPayTv = v.findViewById(R.id.balance_pay);
        }


    }
    public class ReportViewHolderDark extends RecyclerView.ViewHolder {
        TextView memberNameTv, initilaPayTv, totalExpTv, balPayTv;

        public ReportViewHolderDark(View v) {
            super(v);
            memberNameTv = v.findViewById(R.id.member_name);
            initilaPayTv = v.findViewById(R.id.intialpay);
            totalExpTv = v.findViewById(R.id.totalexp);
            balPayTv = v.findViewById(R.id.balance_pay);
        }
    }

    private void configureReportViewHolderLight(ReportViewHolderLight vh1, int position) {
        vh1.memberNameTv.setText(tripReport.getMemberReports().get(position).getName());
        vh1.initilaPayTv.setText(tripReport.getMemberReports().get(position).getInitialContribution());
        vh1.totalExpTv.setText(tripReport.getMemberReports().get(position).getTotalExpenditure());
        float balance= Float.parseFloat(tripReport.getMemberReports().get(position).getInitialContribution())-Float.parseFloat(tripReport.getMemberReports().get(position).getTotalExpenditure());
        vh1.balPayTv.setText(String.valueOf(balance));
    }

    private void configureReportViewHolderDark(ReportViewHolderDark vh2,int position) {
        vh2.memberNameTv.setText(tripReport.getMemberReports().get(position).getName());
        vh2.initilaPayTv.setText(tripReport.getMemberReports().get(position).getInitialContribution());
        vh2.totalExpTv.setText(tripReport.getMemberReports().get(position).getTotalExpenditure());
        float balance= Float.parseFloat(tripReport.getMemberReports().get(position).getInitialContribution())-Float.parseFloat(tripReport.getMemberReports().get(position).getTotalExpenditure());
        vh2.balPayTv.setText(String.valueOf(balance));
    }

}

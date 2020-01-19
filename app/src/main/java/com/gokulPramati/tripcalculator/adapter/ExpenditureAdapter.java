package com.gokulPramati.tripcalculator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.model.MemberExpenditures;

import java.util.List;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class ExpenditureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<MemberExpenditures> memberExpendituresList;
    public ExpenditureAdapter(Context context, List<MemberExpenditures> memberExpendituresList) {
        this.context=context;
        this.memberExpendituresList=memberExpendituresList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType % 2 == 0) {
            View view = inflater.inflate(R.layout.layout_member_expenditure_list_item_light, parent, false);
            viewHolder = new ViewHolderLight(view);
        } else {
            View view = inflater.inflate(R.layout.layout_member_expenditure_list_item_dark, parent, false);
            viewHolder = new ViewHolderDark(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()%2) {
            case 0:
                ViewHolderLight vh1 = (ViewHolderLight) holder;
                configureViewHolderLight(vh1, position);
                break;
            case 1:
                ViewHolderDark vh2 = (ViewHolderDark) holder;
                configureViewHolderDark(vh2, position);
                break;
            default:
                ViewHolderLight vh3 = (ViewHolderLight) holder;
                configureViewHolderLight(vh3, position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return memberExpendituresList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;

    }
    public class ViewHolderLight extends RecyclerView.ViewHolder {
        TextView descTv, amountTv;

        public ViewHolderLight(View v) {
            super(v);
            descTv = v.findViewById(R.id.expend_desc);
            amountTv = v.findViewById(R.id.expend_amount);
        }
    }
    public class ViewHolderDark extends RecyclerView.ViewHolder {
        TextView descTv, amountTv;
        public ViewHolderDark(View v) {
            super(v);
            descTv = v.findViewById(R.id.expend_desc);
            amountTv = v.findViewById(R.id.expend_amount);

        }
    }

    private void configureViewHolderLight(ViewHolderLight vh1, int position) {
        vh1.descTv.setText(memberExpendituresList.get(position).getDescription());
        vh1.amountTv.setText(String.valueOf(memberExpendituresList.get(position).getAmount()));
    }

    private void configureViewHolderDark(ViewHolderDark vh2, int position) {
        vh2.descTv.setText(memberExpendituresList.get(position).getDescription());
        vh2.amountTv.setText(String.valueOf(memberExpendituresList.get(position).getAmount()));
    }
}

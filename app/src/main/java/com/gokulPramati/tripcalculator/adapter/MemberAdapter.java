package com.gokulPramati.tripcalculator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.listener.TripDetailClickListener;
import com.gokulPramati.tripcalculator.model.TripMember;

import java.util.List;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class MemberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    Context context;
    List<TripMember> tripMembers;
    TripDetailClickListener tripDetailClickListener;
    public MemberAdapter(Context context, List<TripMember> tripMembers,
                  TripDetailClickListener tripDetailClickListener){
        this.context=context;
        this.tripMembers=tripMembers;
        this.tripDetailClickListener=tripDetailClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType % 2 == 0) {
            View view = inflater.inflate(R.layout.layout_member_list_item_light, parent, false);
            viewHolder = new ViewHolderLight(view);
        } else {
            View view = inflater.inflate(R.layout.layout_member_list_item_dark, parent, false);
            viewHolder = new ViewHolderDark(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tripDetailClickListener.onMemberItemClick(tripMembers.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return tripMembers.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;

    }
    public class ViewHolderLight extends RecyclerView.ViewHolder {
        TextView memberNameTv, memberInitalContributionTv;

        public ViewHolderLight(View v) {
            super(v);
            memberNameTv = v.findViewById(R.id.member_name);
            memberInitalContributionTv = v.findViewById(R.id.intialpay);
        }
    }
    public class ViewHolderDark extends RecyclerView.ViewHolder {
        TextView memberNameTv, memberInitalContributionTv;
        public ViewHolderDark(View v) {
            super(v);
            memberNameTv = v.findViewById(R.id.member_name);
            memberInitalContributionTv = v.findViewById(R.id.intialpay);

        }
    }

    private void configureViewHolderLight(ViewHolderLight vh1, int position) {
        vh1.memberNameTv.setText(tripMembers.get(position).getName());
        vh1.memberInitalContributionTv.setText(String.valueOf(tripMembers.get(position).getInitialContribution()));
    }

    private void configureViewHolderDark(ViewHolderDark vh2, int position) {
        vh2.memberNameTv.setText(tripMembers.get(position).getName());
        vh2.memberInitalContributionTv.setText(String.valueOf(tripMembers.get(position).getInitialContribution()));
    }

}

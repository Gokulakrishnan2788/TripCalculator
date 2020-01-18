package com.gokulPramati.tripcalculator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.listener.TripClickListener;
import com.gokulPramati.tripcalculator.model.Trip;

import java.util.List;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class TripAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Trip> tripList;
    TripClickListener tripClickListener;

    public TripAdapter(Context context, List<Trip> tripList, TripClickListener tripClickListener) {
        this.context = context;
        this.tripList = tripList;
        this.tripClickListener=tripClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.trip_item_layout, parent, false);
        viewHolder = new TripViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        TripViewHolder vh= (TripViewHolder) holder;
        configureTripViewHolder(vh, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tripClickListener.onTriItemClick(tripList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }

    public class TripViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv,DescriptionTv;

        public TripViewHolder(View v) {
            super(v);
            titleTv = v.findViewById(R.id.trip_title);
            DescriptionTv = v.findViewById(R.id.trip_description);

        }

    }

    private void configureTripViewHolder(TripViewHolder vh, int position) {
        vh.titleTv.setText(tripList.get(position).getName());
        vh.DescriptionTv.setText(tripList.get(position).getDescription());
    }

}

package com.example.dchya24.submission4.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dchya24.submission4.R;
import com.example.dchya24.submission4.model.CreatedBy;

import java.util.ArrayList;

public class CreatorAdapter extends RecyclerView.Adapter<CreatorAdapter.CreatorViewHolder> {
    private ArrayList<CreatedBy> createdByArrayList;
    private final Context context;

    public CreatorAdapter(Context context) {
        this.context = context;
        createdByArrayList = new ArrayList<>();
    }

    public void setCreatedByArrayList(ArrayList<CreatedBy> createdByArrayList) {
        this.createdByArrayList = createdByArrayList;
    }

    public ArrayList<CreatedBy> getCreatedByArrayList() {
        return createdByArrayList;
    }

    @NonNull
    @Override
    public CreatorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_creators, viewGroup, false);

        return new CreatorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreatorViewHolder creatorViewHolder, int i) {
        creatorViewHolder.onBind(getCreatedByArrayList().get(i));
    }

    @Override
    public int getItemCount() {
        return createdByArrayList.size();
    }

    public class CreatorViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCreator;
        private TextView tvCreator;

        public CreatorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCreator = itemView.findViewById(R.id.tv_creator);
            imgCreator = itemView.findViewById(R.id.img_creator);
        }

        public void onBind(CreatedBy createdBy){
            tvCreator.setText(createdBy.getName());
            Glide.with(context).load(createdBy.getProfilePath()).into(imgCreator);
        }
    }
}

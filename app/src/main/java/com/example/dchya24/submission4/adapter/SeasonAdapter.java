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
import com.example.dchya24.submission4.model.Season;

import java.util.ArrayList;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder> {
    private ArrayList<Season> seasonArrayList;
    private final Context context;

    public SeasonAdapter(Context context) {
        this.context = context;
        seasonArrayList = new ArrayList<>();
    }

    public void setSeasonArrayList(ArrayList<Season> seasonArrayList) {
        this.seasonArrayList = seasonArrayList;
    }

    @NonNull
    @Override
    public SeasonAdapter.SeasonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_seasons, viewGroup, false);

        return new SeasonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonAdapter.SeasonViewHolder tvSeasonsViewHolder, int i) {
        tvSeasonsViewHolder.onBind(seasonArrayList.get(i));
    }

    @Override
    public int getItemCount() {
        return seasonArrayList.size();
    }

    public class SeasonViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSeasonName, tvAirDate;
        ImageView imgPosterSeason;

        public SeasonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAirDate = itemView.findViewById(R.id.tv_air_date_season);
            tvSeasonName = itemView.findViewById(R.id.tv_title_season);
            imgPosterSeason = itemView.findViewById(R.id.img_season);
        }

        public void onBind(Season season){
            tvSeasonName.setText(season.getName());
            tvAirDate.setText(season.getAirDate());

            Glide.with(context).load(season.getPosterPath()).into(imgPosterSeason);
        }
    }
}

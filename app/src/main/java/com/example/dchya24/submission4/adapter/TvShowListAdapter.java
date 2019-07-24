package com.example.dchya24.submission4.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dchya24.submission4.R;
import com.example.dchya24.submission4.model.DiscoverTvShow;
import com.example.dchya24.submission4.support.CustomOnClickListener;
import com.example.dchya24.submission4.views.TvShowDetailActivity;

import java.util.ArrayList;

public class TvShowListAdapter extends RecyclerView.Adapter<TvShowListAdapter.ViewHolder> {
    private ArrayList<DiscoverTvShow> discoverTvShowArrayList = new ArrayList<>();
    private final Context context;

    public TvShowListAdapter(Context context) {
        this.context = context;
    }

    public void setdIscoverTvShowArrayList(ArrayList<DiscoverTvShow> dIscoverTvShowArrayList) {
        this.discoverTvShowArrayList = dIscoverTvShowArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_catalogue, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(discoverTvShowArrayList.get(i));

        viewHolder.cvItem.setOnClickListener(new CustomOnClickListener(i, new CustomOnClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View v, int position) {
                Intent intent = new Intent(context, TvShowDetailActivity.class);
                intent.putExtra(TvShowDetailActivity.EXTRA_DATA, discoverTvShowArrayList.get(position).getId());
                context.startActivity(intent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return discoverTvShowArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvOverview, tvFirstAirDate;
        private ImageView imagePoster;
        private CardView cvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvOverview =itemView.findViewById(R.id.tv_overview);
            tvFirstAirDate = itemView.findViewById(R.id.tv_release);
            imagePoster = itemView.findViewById(R.id.img_poster);
            cvItem = itemView.findViewById(R.id.cv_item);
        }

        private void bind(DiscoverTvShow discoverTvShow){
            tvTitle.setText(discoverTvShow.getName());
            tvFirstAirDate.setText(discoverTvShow.getFirst_air_date());
            tvOverview.setText(discoverTvShow.getOverview());

            Glide.with(context).load(discoverTvShow.getPoster_path()).into(imagePoster);
        }
    }
}

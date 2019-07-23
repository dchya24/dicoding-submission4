package com.example.dchya24.submission4.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dchya24.submission4.R;
import com.example.dchya24.submission4.model.DiscoverMovie;
import com.example.dchya24.submission4.support.CustomOnClickListener;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private ArrayList<DiscoverMovie> discoverMovies = new ArrayList<>();
    private final Context context;

    public MovieListAdapter(Context context) {
        this.context = context;
    }

    public void setDiscoverMovies(ArrayList<DiscoverMovie> discoverMovies) {
        this.discoverMovies = discoverMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_catalogue, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(discoverMovies.get(i));
        viewHolder.cvItem.setOnClickListener(new CustomOnClickListener(i, new CustomOnClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View v, int position) {
                Toast.makeText(context, discoverMovies.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        }));

    }

    @Override
    public int getItemCount() {
        return discoverMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvOverview, tvReleaseDate;
        private ImageView imagePoster;
        public CardView cvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvOverview = itemView.findViewById(R.id.tv_overview);
            tvReleaseDate = itemView.findViewById(R.id.tv_release);
            imagePoster = itemView.findViewById(R.id.img_poster);
            cvItem = itemView.findViewById(R.id.cv_item);
        }

        private void bind(DiscoverMovie discoverMovie){
            tvTitle.setText(discoverMovie.getTitle());
            tvOverview.setText(discoverMovie.getOverview());
            tvReleaseDate.setText(discoverMovie.getRelease_date());

            Glide.with(context).load(discoverMovie.getPoster_path()).into(imagePoster);
        }
    }
}

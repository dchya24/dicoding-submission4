package com.example.dchya24.submission4.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dchya24.submission4.R;
import com.example.dchya24.submission4.model.Genre;

import java.util.ArrayList;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.TagsViewHolder> {
    private ArrayList<Genre> genreArrayList = new ArrayList<>();

    public GenreAdapter() {

    }

    public void setGenreArrayList(ArrayList<Genre> genreArrayList) {
        this.genreArrayList = genreArrayList;
    }

    @NonNull
    @Override
    public GenreAdapter.TagsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tags, viewGroup, false);

        return new TagsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreAdapter.TagsViewHolder tagsViewHolder, int i) {
        tagsViewHolder.bind(genreArrayList.get(i));
    }

    @Override
    public int getItemCount() {
        return genreArrayList.size();
    }

    public class TagsViewHolder extends RecyclerView.ViewHolder {
        private TextView tvGenre;

        public TagsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGenre = itemView.findViewById(R.id.tv_tags);
        }

        public void bind(Genre genre){
            tvGenre.setText(genre.getName());
        }
    }
}

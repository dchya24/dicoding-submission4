package com.example.dchya24.submission4.widget;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.example.dchya24.submission4.R;
import com.example.dchya24.submission4.database.AppDatabase;
import com.example.dchya24.submission4.model.DiscoverMovie;
import com.example.dchya24.submission4.model.MovieFavorite;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private final List<String> widgetItems = new ArrayList<>();
    private final Context context;

    public StackRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        final long identityToken = Binder.clearCallingIdentity();

        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "entertaimentdb").build();
        List<MovieFavorite> movieFavorites = db.movieFavoritDAO().getMovieFavorite();

        Gson gson = new Gson();

        for(int i = 0; i < movieFavorites.size(); i++){
            String data = gson.toJson(movieFavorites.get(i));
            try{
                DiscoverMovie discoverMovie = new DiscoverMovie(new JSONObject(data));
                widgetItems.add(discoverMovie.getPosterUrl());

            }catch (Exception e){
                Log.e("ERROR", e.getMessage());
            }
        }

        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return widgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        try{
            Bitmap bitmap = Glide.with(context)
                    .asBitmap()
                    .load(widgetItems.get(position))
                    .submit().get();

            remoteViews.setImageViewBitmap(R.id.img_stack_widget, bitmap);

        }catch (Exception e){
            Log.e("GLIDE -> BITMAP", e.getMessage());
        }

        Bundle extras = new Bundle();
        extras.putInt(MovieFavoriteWidget.EXTRA_ITEM, position);
        Intent fillIntent  = new Intent();
        fillIntent.putExtras(extras);

        remoteViews.setOnClickFillInIntent(R.id.img_stack_widget, fillIntent);

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}

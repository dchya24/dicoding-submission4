package com.example.dchya24.submission4.providers;

import android.arch.persistence.room.Room;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.dchya24.submission4.dao.MovieFavoritDAO;
import com.example.dchya24.submission4.database.AppDatabase;

public class MovieContentProvider extends ContentProvider {
    public static final String AUTHORITY = "com.example.dchya24.submission4";

    private static final int CODE_MOVIE= 1;
    private static final int CODE_MOVIE_ID = 2;

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, "tbmoviefavorite", CODE_MOVIE);
        MATCHER.addURI(AUTHORITY, "tbmoviefavorite/#", CODE_MOVIE_ID);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final int code = MATCHER.match(uri);

        if(code == CODE_MOVIE || code == CODE_MOVIE_ID){
            final Context context = getContext();
            if(context == null){
                return null;
            }

            AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "entertaimentdb").build();
            MovieFavoritDAO movieFavoritDAO = db.movieFavoritDAO();

            final Cursor cursor;
            cursor = movieFavoritDAO.selectAllMovieFavorite();

            cursor.setNotificationUri(context.getContentResolver(), uri);
            return cursor;
        }else{
            throw new IllegalArgumentException("Unkown Uri " + uri);
        }

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

}

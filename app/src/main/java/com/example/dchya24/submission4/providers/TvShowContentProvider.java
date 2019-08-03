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

import com.example.dchya24.submission4.dao.TvShowFavoriteDAO;
import com.example.dchya24.submission4.database.AppDatabase;

public class TvShowContentProvider extends ContentProvider {
    public static final String AUTHORITY = "com.example.dchya24.submission4";

    private static final int CODE_TVSHOW= 1;
    private static final int CODE_TVSHOW_ID = 2;

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, "tbtvshowfavorite", CODE_TVSHOW);
        MATCHER.addURI(AUTHORITY, "tbtvshowfavorite/#", CODE_TVSHOW_ID);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final int code = MATCHER.match(uri);

        if(code == CODE_TVSHOW || code == CODE_TVSHOW_ID){
            final Context context = getContext();
            if(context == null){
                return null;
            }

            AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "entertaimentdb").build();
            TvShowFavoriteDAO tvShowFavoriteDAO = db.tvShowFavoriteDAO();

            final Cursor cursor;
            cursor = tvShowFavoriteDAO.selectAllTvShowFavorite();

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

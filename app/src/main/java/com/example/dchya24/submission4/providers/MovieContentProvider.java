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

import com.example.dchya24.submission4.database.AppDatabase;
import com.example.dchya24.submission4.model.MovieFavorite;
import com.example.dchya24.submission4.model.TvShowFavorite;

public class MovieContentProvider extends ContentProvider {
    public static final String AUTHORITY = "com.example.dchya24.submission4";

    private static final int CODE_MOVIE= 1;
    private static final int CODE_MOVIE_ID = 2;
    private static final int CODE_TVSHOW= 3;
    private static final int CODE_TVSHOW_ID = 4;

    private AppDatabase db;

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, "tbmoviefavorite", CODE_MOVIE);
        MATCHER.addURI(AUTHORITY, "tbmoviefavorite/#", CODE_MOVIE_ID);
        MATCHER.addURI(AUTHORITY, "tbtvshowfavorite", CODE_TVSHOW);
        MATCHER.addURI(AUTHORITY, "tbtvshowfavorite/#", CODE_TVSHOW_ID);
    }

    @Override
    public boolean onCreate() {
        db = Room.databaseBuilder(getContext(), AppDatabase.class, "entertaimentdb").build();
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final int code = MATCHER.match(uri);

        if(code == CODE_MOVIE || code == CODE_MOVIE_ID || code == CODE_TVSHOW || code == CODE_TVSHOW_ID){
            final Context context = getContext();
            if(context == null){
                return null;
            }

            final Cursor cursor;
            switch (MATCHER.match(uri)){
                case CODE_MOVIE:
                    cursor = db.movieFavoritDAO().selectAllMovieFavorite();
                    break;
                case CODE_MOVIE_ID:
                    cursor = db.movieFavoritDAO().getMovieByIdProvider(uri.getLastPathSegment());
                    break;
                case CODE_TVSHOW:
                    cursor = db.tvShowFavoriteDAO().selectAllTvShowFavorite();
                    break;
                case CODE_TVSHOW_ID:
                    cursor = db.tvShowFavoriteDAO().getTvShowFavoriteByIdProvider(uri.getLastPathSegment());
                    break;
                default:
                    cursor = null;
                    break;
            }

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
        long status;

        switch (MATCHER.match(uri)){
            case CODE_MOVIE:
                MovieFavorite movieFavorite = new MovieFavorite();

                movieFavorite.setOverview(values.getAsString("overview"));
                movieFavorite.setPoster_path(values.getAsString("poster_path"));
                movieFavorite.setId(values.getAsInteger("id"));
                movieFavorite.setTitle(values.getAsString("title"));
                movieFavorite.setRelease_date(values.getAsString("release_date"));
                movieFavorite.setDate(values.getAsString("date"));

                status = db.movieFavoritDAO().insertMovieFav(movieFavorite);
                break;
            case CODE_TVSHOW:
                TvShowFavorite tvShowFavorite = new TvShowFavorite();

                tvShowFavorite.setId(values.getAsInteger("id"));
                tvShowFavorite.setOverview(values.getAsString("overview"));
                tvShowFavorite.setPoster_path(values.getAsString("poster_path"));
                tvShowFavorite.setName(values.getAsString("name"));
                tvShowFavorite.setDate(values.getAsString("date"));
                tvShowFavorite.setFirst_air_date(values.getAsString("release_date"));

                status = db.tvShowFavoriteDAO().insertTvShowFav(tvShowFavorite);
                break;
            default:
                status = 0;
                break;
        }

        return Uri.parse(uri +"/" + status);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int deleted;

        switch (MATCHER.match(uri)){
            case CODE_MOVIE_ID:
                deleted = db.movieFavoritDAO().deleteMovieByProvider(uri.getLastPathSegment());
                break;
            case CODE_TVSHOW_ID:
                deleted = db.tvShowFavoriteDAO().deleteTvShowFavoriteByIdProvider(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }

        return deleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

}

package com.example.dchya24.submission4.reminder;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.example.dchya24.submission4.BuildConfig;
import com.example.dchya24.submission4.R;
import com.example.dchya24.submission4.api.MovieDbApiInterface;
import com.example.dchya24.submission4.api.MovieDbApiService;
import com.example.dchya24.submission4.model.DiscoverMovie;
import com.example.dchya24.submission4.support.GetCurrentDate;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReminderMovieRelease extends BroadcastReceiver {
    public static final int ID_REPEATING = 101;
    private ArrayList<DiscoverMovie> discoverMovieArrayList = new ArrayList<>();
    private int idNotif = 0;
    private final static String GROUP_MOVIES = "goup_movies";


    public ReminderMovieRelease(){
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean status = preferences.getBoolean("movie_release", false);

        if(status){
            getMovieRelease(context);
        }

    }

    public void setReminderMovieRelease(Context context){
        String TIME_FORMAT = "HH:mm";
        String time = "08:00";

        if(GetCurrentDate.isDateInvalid(time, TIME_FORMAT)) return;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReminderMovieRelease.class);

        String[] timeArray = time.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, 0);

        if(alarmManager != null){
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }

    }

    public void canceledReminderMovieRelease(Context context){
        Intent intent = new Intent(context, ReminderMovieRelease.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if(alarmManager != null){
            alarmManager.cancel(pendingIntent);
        }
    }

    private void getMovieRelease(final Context context){
        MovieDbApiInterface movieDbApiInterface = MovieDbApiService.getClient();

        String date = GetCurrentDate.getDate();
        Map<String, String> map = new HashMap<>();
        map.put("api_key", BuildConfig.API_KEY);
        map.put("language", "en-US");
        map.put("primary_release_date.gte", date);
        map.put("primary_release_date.lte", date);


        Call<ResponseBody> responseBodyCall = movieDbApiInterface.getDiscoverMovieJson(map);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    String data = response.body().string();
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray results = jsonObject.getJSONArray("results");

                    for(int i = 0; i < results.length(); i++){
                        DiscoverMovie discoverMovie = new DiscoverMovie(results.getJSONObject(i));
                        discoverMovieArrayList.add(discoverMovie);

                        idNotif++;
                    }

                    sendStackNotif(context);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void sendStackNotif(Context context){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder;

        String CHANNEL_ID = "channel_1";
        int maxNotif = 2;
        if(idNotif < maxNotif) {
            builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_local_movies_black_24dp)
                    .setContentTitle("New Film Release Now !!")
                    .setContentText(discoverMovieArrayList.get(idNotif).getTitle())
                    .setGroup(GROUP_MOVIES)
                    .setColor(ContextCompat.getColor(context, R.color.fontLight))
                    .setAutoCancel(true);
        }else{
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle()
                    .addLine(discoverMovieArrayList.get(idNotif - 1).getTitle())
                    .addLine(discoverMovieArrayList.get(idNotif - 2).getTitle())
                    .addLine(discoverMovieArrayList.get(idNotif - 3).getTitle())
                    .setBigContentTitle("New Film Release Now!")
                    .setSummaryText("Film Release: " + idNotif);

            builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setContentTitle(idNotif + "Film Release")
                    .setSmallIcon(R.drawable.ic_local_movies_black_24dp)
                    .setGroup(GROUP_MOVIES)
                    .setGroupSummary(true)
                    .setStyle(inboxStyle)
                    .setAutoCancel(true);
        }


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String CHANNEL_NAME = "ReminderMovieRelease Channel";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            channel.enableVibration(true);

            builder.setChannelId(CHANNEL_ID);

            if(notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();

        if(notificationManager != null){
            notificationManager.notify(idNotif, notification);
        }
    }

}

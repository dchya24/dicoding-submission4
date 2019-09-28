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

import com.example.dchya24.submission4.R;
import com.example.dchya24.submission4.support.GetCurrentDate;

import java.util.Calendar;

public class ReminderUser  extends BroadcastReceiver {
    public static final int ID_REPEATING = 102;

    public ReminderUser(){
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String title = context.getResources().getString(R.string.app_title);
        String message = context.getResources().getString(R.string.app_message);

        int notifId = 102;


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean status = preferences.getBoolean("movie_release", false);

        if(status){
            showNotification(context, title, message, notifId);
        }
    }


    private void showNotification(Context context, String title, String message, int notifId){
        String CHANNEL_ID = "channel_2";
        String CHANNEL_NAME = "ReminderUser Channel";

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_local_movies_black_24dp)
                .setBadgeIconType(R.drawable.ic_local_movies_black_24dp)
                .setContentTitle(title)
                .setContentText(message)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setColor(ContextCompat.getColor(context, R.color.fontLight));

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            channel.enableVibration(false);

            builder.setChannelId(CHANNEL_ID);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});

            if(notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();

        if(notificationManager != null){
            notificationManager.notify(notifId, notification);
        }
    }

    public void setReminderUser(Context context){
        String TIME_FORMAT = "HH:mm";

        String TIME = "07:00";
        if(GetCurrentDate.isDateInvalid(TIME, TIME_FORMAT)) return;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, ReminderUser.class);

        String[] timeArray = TIME.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, 0);

        if(alarmManager != null){
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }

    }

    public void cancelingReminderUser(Context context){
        Intent intent = new Intent(context, ReminderUser.class);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
    }


}

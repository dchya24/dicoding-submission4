package com.example.dchya24.submission4.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;

import com.example.dchya24.submission4.R;
import com.example.dchya24.submission4.reminder.ReminderMovieRelease;
import com.example.dchya24.submission4.reminder.ReminderUser;

public class MyPreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private String REMINDER;
    private String MOVIE_RELEASE;

    private CheckBoxPreference reminderPreference;
    private CheckBoxPreference movieReleasePreference;

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences);
        init();
        setSummaries();
    }

    private void init(){
        REMINDER = getResources().getString(R.string.key_reminder);
        MOVIE_RELEASE = getResources().getString(R.string.key_movie_release);

        reminderPreference = (CheckBoxPreference) findPreference(REMINDER);
        movieReleasePreference = (CheckBoxPreference) findPreference(MOVIE_RELEASE);

    }

    @Override
    public void onResume(){
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(REMINDER)){
            boolean reminder = sharedPreferences.getBoolean(REMINDER, true);
            reminderPreference.setChecked(reminder);
            Log.d(REMINDER, "Was " + reminder);

            ReminderUser reminderUser = new ReminderUser();

            if(reminder){
                reminderUser.setReminderUser(getContext());
                Log.d(ReminderUser.class.getSimpleName(), "Was Set Alarm");

            }else {
                reminderUser.cancelingReminderUser(getContext());
                Log.d(ReminderUser.class.getSimpleName(), "Was Canceled Alarm");
            }
        }

        if(key.equals(MOVIE_RELEASE)){
            boolean reminder = sharedPreferences.getBoolean(MOVIE_RELEASE, true);
            movieReleasePreference.setChecked(reminder);
            Log.d(MOVIE_RELEASE, "Was " + reminder);

            ReminderMovieRelease reminderMovieRelease = new ReminderMovieRelease();

            if(reminder){
                reminderMovieRelease.setReminderMovieRelease(getContext());
                Log.d(ReminderMovieRelease.class.getSimpleName(), "Was Set Alarm");

            }else {
                reminderMovieRelease.canceledReminderMovieRelease(getContext());
                Log.d(ReminderMovieRelease.class.getSimpleName(), "Was Canceled Alarm");
            }
        }
    }

    private void setSummaries(){
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        reminderPreference.setChecked(sharedPreferences.getBoolean(REMINDER, true));
        movieReleasePreference.setChecked(sharedPreferences.getBoolean(MOVIE_RELEASE, true));
    }
}

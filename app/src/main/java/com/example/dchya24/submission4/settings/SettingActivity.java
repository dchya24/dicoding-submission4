package com.example.dchya24.submission4.settings;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.dchya24.submission4.R;

import java.util.Objects;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.setting_holder, new MyPreferenceFragment()).commit();

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(getResources().getString(R.string.reminder_text));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean status = preferences.getBoolean("movie_release", false);
        boolean reminder = preferences.getBoolean("reminder", false);
        Log.d("Reminder", "Status: " + reminder);
        Log.d("Movie Release", "Release: " + status);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        finish();

        return super.onOptionsItemSelected(menuItem);
    }
}

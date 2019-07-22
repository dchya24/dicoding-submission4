package com.example.dchya24.submission4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movie:
                    mTextMessage.setText(R.string.navigation_movie);
                    return true;
                case R.id.navigation_tv_show:
                    mTextMessage.setText(R.string.navigation_tv_show);
                    return true;
                case R.id.navigation_movie_favorit:
                    mTextMessage.setText(R.string.navigation_movie_favorit);
                    return true;
                case R.id.navigation_tv_show_favorit:
                    mTextMessage.setText(R.string.navigation_tv_show_favorit);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}

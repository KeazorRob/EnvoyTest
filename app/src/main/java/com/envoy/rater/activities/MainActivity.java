package com.envoy.rater.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.envoy.rater.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.title_activity_home_games);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @OnClick({ R.id.listGames, R.id.rateGames })
    public void myClickHandler(View button) {
        switch(button.getId()) {
            case R.id.listGames:
                startActivity(new Intent(this, GamesActivity.class));
                break;
            case R.id.rateGames:
                startActivity(new Intent(this, RateActivity.class));
                break;
        }
    }
}

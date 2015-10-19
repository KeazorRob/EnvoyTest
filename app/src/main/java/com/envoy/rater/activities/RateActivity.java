package com.envoy.rater.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.envoy.rater.R;
import com.envoy.rater.fragments.RateGames;

import butterknife.ButterKnife;

public class RateActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rate);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpActionBar();

        if (savedInstanceState == null) {
            addFragment(R.id.container, RateGames.newInstance(), true);
        }
    }

}

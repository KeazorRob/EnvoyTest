package com.envoy.rater.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.envoy.rater.R;
import com.envoy.rater.events.Msg;
import com.envoy.rater.fragments.MyGames;
import com.envoy.rater.fragments.NewGame;

import butterknife.ButterKnife;

public class GamesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_games);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpActionBar();

        if (savedInstanceState == null) {
            addFragment(R.id.container, MyGames.newInstance(), true);
        }
    }

    public void onEvent(Msg event) {
        switch(event.getEvent()) {
            case NEW_GAME:{
                addFragment(R.id.container, NewGame.newInstance(), true);
            }
            break;
        }
    }
}

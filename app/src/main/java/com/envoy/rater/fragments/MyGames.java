package com.envoy.rater.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.envoy.rater.R;
import com.envoy.rater.adapters.EnvoyAdapter;
import com.envoy.rater.controller.EnvoyApp;
import com.envoy.rater.events.Msg;
import com.envoy.rater.utilities.DbUtilities;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

public class MyGames extends Base {
    private ListView mListView;
    private EnvoyAdapter mAdapter;

    public static MyGames newInstance() {
        MyGames fragment = new MyGames();
        return fragment;
    }

    public MyGames() {}

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);

        View view = getView();

        mAdapter = new EnvoyAdapter(getActivity(), new ArrayList<>(DbUtilities.getGames()),
                EnvoyAdapter.LIST_GAMES_ADAPTER);

        mListView = (ListView) view.findViewById(android.R.id.list);
        mListView.setAdapter(mAdapter);

        TextView emptyText = (TextView)view.findViewById(android.R.id.empty);
        mListView.setEmptyView(emptyText);
    }

    public void onResume() {
        super.onResume();
        EventBus.getDefault().registerSticky(this);
    }

    public void onStop() {
        super.onResume();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(Msg event) {
        switch (event.getEvent()) {
            case UPDATE_GAMES:
                mAdapter.clear();
                mAdapter.addAll(DbUtilities.getGames());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_games, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list_games, menu);
        showGlobalContextActionBar(EnvoyApp.getApp().getResources().getString(R.string.title_activity_list_games));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                EventBus.getDefault().post(new Msg(Msg.EventType.NEW_GAME));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

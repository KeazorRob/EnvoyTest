package com.envoy.rater.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.envoy.rater.R;
import com.envoy.rater.adapters.EnvoyAdapter;
import com.envoy.rater.controller.EnvoyApp;
import com.envoy.rater.events.Msg;
import com.envoy.rater.utilities.DbUtilities;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

public class RateGames extends Base implements ListView.OnItemClickListener {
    private ListView mListView;
    private EnvoyAdapter mAdapter;

    public static RateGames newInstance() {
        RateGames fragment = new RateGames();
        return fragment;
    }

    public RateGames() {}

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);

        View view = getView();

        mAdapter = new EnvoyAdapter(getActivity(), new ArrayList<>(DbUtilities.getGames()),
                EnvoyAdapter.RATE_GAMES_ADAPTER);

        mListView = (ListView) view.findViewById(android.R.id.list);
        mListView.setOnItemClickListener(this);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_games, container, false);
    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        EnvoyAdapter.Holder holder = (EnvoyAdapter.Holder)view.getTag();
        Rater dialog = Rater.newDialog(holder.game);
        dialog.show(getChildFragmentManager(), null);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_base, menu);
        showGlobalContextActionBar(EnvoyApp.getApp().getResources().getString(R.string.title_activity_rate_games));
    }

}

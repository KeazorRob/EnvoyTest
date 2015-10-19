package com.envoy.rater.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.envoy.rater.R;
import com.envoy.rater.orm.Game;
import com.envoy.rater.utilities.DbUtilities;
import com.envoy.rater.utilities.ImgUtilities;

import java.util.ArrayList;

public class EnvoyAdapter extends ArrayAdapter<Game> implements CompoundButton.OnCheckedChangeListener {
    public static final int NUM_ITEMS = 2;
    public static final int LIST_GAMES_ADAPTER = 0;
    public static final int RATE_GAMES_ADAPTER = 1;

    private int mType;

    LayoutInflater mLayoutInflater;
    Game mGame;
    Holder holder;

    public EnvoyAdapter(Context context, ArrayList<Game> games, int type) {
        super(context, 0, games);
        mType = type;
        mLayoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null || !(view.getTag() instanceof Holder)) {
            view = buildView(parent);
            holder = new Holder();
            holder.imgGame = (ImageView) view.findViewById(R.id.imgGame);
            holder.gameName = (TextView)view.findViewById(R.id.gameName);
            holder.consoleName = (TextView)view.findViewById(R.id.consoleName);

            if (mType == LIST_GAMES_ADAPTER) {
                holder.finished = (CheckBox)view.findViewById(R.id.finished);
                holder.finished.setOnCheckedChangeListener(this);
            } else {
                holder.rating = (RatingBar)view.findViewById(R.id.rating);
            }

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        mGame = getItem(position);

        if (mGame.getGameImage() == null) {
            holder.imgGame.setImageResource(R.drawable.ic_action_picture);
        } else {
            holder.imgGame.setImageBitmap(ImgUtilities.getBitmap(mGame.getGameImage()));
        }
        holder.gameName.setText(mGame.getGameName());
        holder.consoleName.setText(mGame.getConsoleName());

        if (mType == LIST_GAMES_ADAPTER) {
            holder.finished.setTag(mGame);
            holder.finished.setChecked(mGame.getIsGameFinished());
        } else {
            holder.game = mGame;
            holder.rating.setRating(mGame.getStarsNumber());
        }

        return view;
    }

    public int getViewTypeCount() {
        return NUM_ITEMS;
    }

    public int getItemViewType(int position) {
        return mType;
    }

    private View buildView(ViewGroup parent) {
        View view;

        switch(mType) {
            case LIST_GAMES_ADAPTER:
                view = mLayoutInflater.inflate(R.layout.game_item_add, parent, false);
                break;

            default:
            case RATE_GAMES_ADAPTER:
                view = mLayoutInflater.inflate(R.layout.game_item_rate, parent, false);
                break;
        }

        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Game game = (Game)buttonView.getTag();
        if (isChecked != game.getIsGameFinished()) {
            game.setIsGameFinished(isChecked);
            DbUtilities.updateGame(game);
            notifyDataSetChanged();
        }
    }

    public static class Holder {
        ImageView imgGame;
        TextView gameName;
        TextView consoleName;
        CheckBox finished;
        RatingBar rating;
        public Game game;
    }

}

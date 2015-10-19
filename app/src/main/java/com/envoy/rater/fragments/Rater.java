package com.envoy.rater.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RatingBar;

import com.envoy.rater.R;
import com.envoy.rater.events.Msg;
import com.envoy.rater.orm.Game;
import com.envoy.rater.utilities.DbUtilities;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class Rater extends DialogFragment implements RatingBar.OnRatingBarChangeListener {

    Game mGame;
    int mValue = -1;

    @Bind(R.id.rating)
    RatingBar mRating;

    public static Rater newDialog(Game game) {
        Rater dialog = new Rater();
        dialog.mGame = game;
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rater_dialog, container,
                false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Dialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        mRating.setRating(mGame.getStarsNumber());
        mRating.setOnRatingBarChangeListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mGame = null;
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.ok)
    public void ok() {
        if (mValue != -1) {
            mGame.setStarsNumber(mValue);
            DbUtilities.updateGame(mGame);
            EventBus.getDefault().postSticky(new Msg(Msg.EventType.UPDATE_GAMES));
        }
        dismiss();
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        mValue = (int)rating;
    }
}

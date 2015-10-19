package com.envoy.rater.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.envoy.rater.R;
import com.envoy.rater.controller.EnvoyApp;
import com.envoy.rater.events.Msg;
import com.envoy.rater.orm.Game;
import com.envoy.rater.utilities.DbUtilities;
import com.envoy.rater.utilities.ImgUtilities;
import com.envoy.rater.utilities.OtherUtilities;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class NewGame extends Base {
    private static final int PICTURE_SELECTOR = 1;

    @Bind(R.id.gameName)
    EditText gameName;
    @Bind(R.id.consoleName)
    EditText consoleName;
    @Bind(R.id.photo)
    ImageView photo;

    Bitmap image;

    public static NewGame newInstance() {
        NewGame fragment = new NewGame();
        return fragment;
    }

    public NewGame() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void onResume() {
        super.onResume();
        if (image != null) {
            photo.setImageBitmap(image);
        }
    }

    @OnClick(R.id.photo)
    public void photo() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICTURE_SELECTOR);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Clean up
        menu.clear();

        // Add Menu Options
        inflater.inflate(R.menu.menu_add_game, menu);

        // Set Title
        showGlobalContextActionBar(EnvoyApp.getApp().getResources().getString(R.string.title_fragment_add_game));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save:
                if (!OtherUtilities.isEmpty(gameName, getString(R.string.write_game_name))
                        && !OtherUtilities.isEmpty(consoleName, getString(R.string.write_console_name))) {
                    Game game = new Game();
                    game.setGameName(OtherUtilities.getText(gameName));
                    game.setConsoleName(OtherUtilities.getText(consoleName));
                    game.setIsGameFinished(false);
                    game.setStarsNumber(0);
                    if (image != null) {
                        game.setGameImage(ImgUtilities.getByteArray(image));
                    }
                    DbUtilities.saveGame(game);
                    EventBus.getDefault().postSticky(new Msg(Msg.EventType.UPDATE_GAMES));
                    getActivity().onBackPressed();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onDestroy() {
        super.onDestroy();
        image = null;
        ButterKnife.unbind(this);
    }

    public void onActivityResult(int requestCode, int responseCode, Intent imageReturned) {
        if (requestCode == Activity.RESULT_CANCELED) return;

        switch(requestCode) {
            case PICTURE_SELECTOR:{
                image = ImgUtilities.resizeBitmap(imageReturned, (int) OtherUtilities.pxFromDp(100),
                        (int) OtherUtilities.pxFromDp(100));
            }
                break;
        }
    }
}

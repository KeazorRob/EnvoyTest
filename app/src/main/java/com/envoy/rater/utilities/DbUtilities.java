package com.envoy.rater.utilities;

import com.envoy.rater.controller.EnvoyApp;
import com.envoy.rater.orm.Game;

import java.util.List;

public class DbUtilities {

    public static long saveGame(Game game) {
        return EnvoyApp.getApp().getDAOSession().getGameDao().insert(game);
    }

    public static void updateGame(Game game) {
        EnvoyApp.getApp().getDAOSession().getGameDao().update(game);
    }

    public static List<Game> getGames() {
        return EnvoyApp.getApp().getDAOSession().getGameDao().loadAll();
    }
}

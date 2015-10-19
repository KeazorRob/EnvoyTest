package com.envoy.rater.controller;

import android.app.Application;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.envoy.rater.orm.DaoMaster;
import com.envoy.rater.orm.DaoSession;

public class EnvoyApp extends Application {
    public static final String DATABASE_NAME = "EnvoyRater.db";
    private static String LOG = EnvoyApp.class.getName();
    private static EnvoyApp thiz;
    private SQLiteDatabase database;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    public void onCreate() {
        super.onCreate();
        thiz = this;
        openSQLiteDatabase();
    }

    public Resources getAppResources() {
        return thiz.getResources();
    }

    public static EnvoyApp getApp() {
        return thiz;
    }


    public SQLiteDatabase openSQLiteDatabase() throws SQLiteException {
        if (database == null) {
            database = new DaoMaster.DevOpenHelper(this, DATABASE_NAME,
                    null).getWritableDatabase();
        } else if (!database.isOpen()) {
            database = new DaoMaster.DevOpenHelper(this, DATABASE_NAME,
                    null).getWritableDatabase();
        }
        return database;
    }

    public DaoSession getDAOSession() {
        DaoMaster dm = getDAOMaster();
        if (daoSession == null) {
            daoSession = dm.newSession();
        }
        return daoSession;
    }

    public DaoMaster getDAOMaster() {
        SQLiteDatabase d = openSQLiteDatabase();
        if (daoMaster == null) {
            daoMaster = new DaoMaster(d);
        } else if (daoMaster.getDatabase() != d) {
            daoMaster = new DaoMaster(d);
        }
        return daoMaster;
    }

    public SQLiteDatabase getDb() {
        return openSQLiteDatabase();
    }

    public void closeDb() {
        try {
            database.close();
        } catch (Exception e) {
            Log.i(LOG, "Error :: " + e + ")");
        } finally {
            database = null;
            daoMaster = null;
            daoSession = null;
        }
    }

}

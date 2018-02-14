package com.spy.vksoni.wetalk.db;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

/**
 * Created by Vksoni on 2/13/2018.
 */

public class DBConnection extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Configuration dbConfiguration =
                new Configuration.Builder(this).
                        setDatabaseName("WeTalk.db").setModelClasses(User.class, SmsCode.class
                        , SMSModel.class, ConversationModel.class)
                        .create();

        ActiveAndroid.initialize(dbConfiguration);
    }
    }
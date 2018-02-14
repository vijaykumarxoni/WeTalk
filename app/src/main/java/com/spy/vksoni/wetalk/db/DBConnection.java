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
<<<<<<< HEAD:app/src/main/java/com/spy/vksoni/wetalk/db/DBConncetion.java
                        setDatabaseName("WeTalk.db").setModelClasses(User.class,SmsCode.class,AlertCode.class)
=======
                        setDatabaseName("WeTalk.db").setModelClasses(User.class, SmsCode.class
                        , SMSModel.class, ConversationModel.class)
>>>>>>> origin/master:app/src/main/java/com/spy/vksoni/wetalk/db/DBConnection.java
                        .create();

        ActiveAndroid.initialize(dbConfiguration);
    }
    }
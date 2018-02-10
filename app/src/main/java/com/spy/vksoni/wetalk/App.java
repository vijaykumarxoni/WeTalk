package com.spy.vksoni.wetalk;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.activeandroid.app.Application;
import com.spy.vksoni.wetalk.db.Users;

/**
 * Created by Dell on 2018-02-10.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        Configuration dbConfiguration =
                new Configuration.Builder(this).
                        setDatabaseName("wetalk.db").setModelClasses(Users.class)
                        .create();

        ActiveAndroid.initialize(dbConfiguration);

    }
}

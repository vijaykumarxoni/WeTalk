package com.spy.vksoni.wetalk.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Vksoni on 2/10/2018.
 */


    @Table(name="User")
    public class User extends Model {
    public User() {
   super(); }

    @Column(name="userId",unique = true)
        public int userId;

        @Column(name="userName",notNull = true)
        public String userName;

        @Column(name="userEmail",notNull = true)
        public String userEmail;

        @Column(name="userPassword",notNull = true)
        public String userPassword;

        @Column(name="userContactno",notNull = true)
        public String userContactno;

    }

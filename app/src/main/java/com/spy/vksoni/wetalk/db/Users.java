package com.spy.vksoni.wetalk.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Dell on 2018-02-10.
 */

@Table(name="Users")
public class Users extends Model {

    @Column(name = "userId")
    public int userId;



    @Column (name = "userName")
    public String userName;


    @Column (name = "userEmail")
    public String userEmail;

    @Column (name = "userPassword")
    public String userPassword;

    @Column (name = "userContactno")
    public int userImage;



}


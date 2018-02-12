package com.spy.vksoni.wetalk.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;

/**
 * Created by Vksoni on 2/10/2018.
 */

public class DBHandler {

    static final DBHandler instance = new DBHandler();

    public static DBHandler getInstance() {
        return instance;
    }

    private DBHandler() {
    }

    public boolean acoountAuthentication(Context context, String userEmail,String userPassword){
        User user=new Select().all().from(User.class).
                where("userEmail   = ?  and   userPassword   = ?",userEmail,userPassword) .executeSingle();

        if(user!=null){



            Toast.makeText(context,"Welcome: "+user.userName,Toast.LENGTH_SHORT).show();
            addToSharePrefrences(context,user.userId,user.userName,user.userEmail,user.userPassword);

            return true;
        }




        return false;
    }
    public void addToSharePrefrences(Context context,int user_id,String name, String email, String pass){
        SharedPreferences sharedPreferencespre =context.getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferencespre.edit();

        editor.putString("Name",name);
        editor.putString("Email",email);
        editor.putString("Password",pass);
        editor.putInt("UserId",user_id);
        editor.apply();
        editor.commit();


    }
    public void clearSharePrefrences(Context context){
        SharedPreferences sharedPreferencespre =context.getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferencespre.edit();

        editor.putString("Name","");
        editor.putString("Email","");
        editor.putString("Password","");
        editor.putInt("UserId",-1);
        editor.apply();
        editor.commit();




    }

}

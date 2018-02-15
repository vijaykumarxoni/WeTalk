package com.spy.vksoni.wetalk.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.spy.vksoni.wetalk.MessageActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    Date d=new Date();
    String  date=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat format=new SimpleDateFormat("h:mm a");
    String time=format.format(calendar.getTime());

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


    public void addMsg(String msg_body,String msg_type,String phone_no){


        List<SMSModel> msgTempList=new Select().all().from(SMSModel.class).execute();

        SMSModel smsModel = new SMSModel();
        smsModel.msg_id =msgTempList.size();
        smsModel.msg_body= msg_body;
        smsModel.msg_type =msg_type;
        smsModel.phone_no=phone_no;
        smsModel.msg_rec_date=date;
        smsModel.msg_rec_time=time;
        smsModel.save();

    }
    public void addToConversation(String sender_name_num,int con_id){

        ConversationModel conversationModel=new ConversationModel();
        conversationModel.conver_id=con_id;
        conversationModel.sender_name_number=sender_name_num;
        conversationModel.msg_rec_date=date;
        conversationModel.msg_rec_time=time;

        conversationModel.save();

    }
    public void addMessage(String msgBody,String number,String msgType,int conv_id){
        SMSModel smsModel=new SMSModel();
        smsModel.msg_type=msgType;
        smsModel.msg_id=new Select().all().from(SMSModel.class).execute().size();
        smsModel.phone_no=number;
        smsModel.msg_body=msgBody;
        smsModel.msg_rec_date=date;
        smsModel.msg_rec_time=time;
        smsModel.conver_id=conv_id;
        smsModel.save();

//        MessageActivity messageActivity=MessageActivity.newIntenence();
//        messageActivity.refreshList(smsModel);


    }
    public List<ConversationModel> getConversation(){

        return  new Select().all().from(ConversationModel.class).execute();}

    public List<SMSModel> getSMS(String phone_no){
        return new Select().all().from(SMSModel.class).where("phone_no=?",phone_no).execute();
    }



}

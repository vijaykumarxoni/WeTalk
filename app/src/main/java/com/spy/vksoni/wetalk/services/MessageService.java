package com.spy.vksoni.wetalk.services;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.spy.vksoni.wetalk.MessageActivity;
import com.spy.vksoni.wetalk.db.SMSModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MessageService extends Service {
    Date d=new Date();
    String  date=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat format=new SimpleDateFormat("h:mm a");
    String time=format.format(calendar.getTime());
    List<SMSModel> smsList;
    String tempMsg;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {


            @Override
            public void run() {

                while (true) {

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
finally {


                    Uri uriSMSURI = Uri.parse("content://sms/");

                    smsList=new Select().all().from(SMSModel.class).execute();

                    Cursor cur = getContentResolver().query(uriSMSURI, null, null, null, null);
                    cur.moveToFirst();

                    if(cur.getInt(cur.getColumnIndex("type")) == 2){


                        String address = cur.getString(cur.getColumnIndex("address"));
                        String body = cur.getString(cur.getColumnIndexOrThrow("body"));
if(!(body.equalsIgnoreCase(tempMsg))) {
    if(!(smsList.get(smsList.size()-1).msg_body.equals(body)&&
            smsList.get(smsList.size()-1).phone_no.equals(address))){
    SMSModel smsModel = new SMSModel();
    smsModel.msg_body = body;
    smsModel.msg_rec_time = time;
    smsModel.msg_rec_date = date;
    smsModel.msg_id = new Select().all().from(SMSModel.class).execute().size();
    smsModel.phone_no = address;
    //smsModel.conver_id=getConversationId(address);
    smsModel.msg_type = "Sended";

    smsModel.save();



    tempMsg=body;
}}
                    }

                }}
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onCreate() {


        super.onCreate();


    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }}


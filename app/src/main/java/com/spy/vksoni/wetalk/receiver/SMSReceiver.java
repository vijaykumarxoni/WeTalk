package com.spy.vksoni.wetalk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.spy.vksoni.wetalk.db.ConversationModel;
import com.spy.vksoni.wetalk.db.DBHandler;
import com.spy.vksoni.wetalk.db.SMSModel;
import com.spy.vksoni.wetalk.db.SmsCode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.Context.AUDIO_SERVICE;

/**
 * Created by Vksoni on 2/10/2018.
 */

public class SMSReceiver extends BroadcastReceiver {

    String name="";
    int  con_id;
    boolean con=false;
    List<ConversationModel> conversationList;

    @Override
    public void onReceive(Context context, Intent intent) {
        // pdus protcol data unit
        Bundle bundlePDUS = intent.getExtras();
        Object[] pdus = (Object[]) bundlePDUS.get("pdus");
        SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[0]);
        Toast.makeText(context, "SMS RECEIVED ", Toast.LENGTH_SHORT).show();

        // date and time
        Date d = new Date();
        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm a");
        String time = format.format(calendar.getTime());

        SmsCode smsCode = new Select().all().from(SmsCode.class).executeSingle();

        // for silent remove
        if (smsCode != null) {
            if (message.getDisplayMessageBody().contains(smsCode.code)) {
                AudioManager audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);

                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

            }
        }


        List <SMSModel> smsList = new Select().all().from(SMSModel.class).execute();
        conversationList = new Select().all().from(ConversationModel.class).execute();

        for (ConversationModel conversationModel : conversationList) {
            if (conversationModel.sender_name_number.equalsIgnoreCase(message.getDisplayOriginatingAddress())) {
                con = true;
                con_id = conversationModel.conver_id;
                break;
            }

        }
        if (con == true) {
            new Update(ConversationModel.class).set("msg_rec_date = ?," +
                    "msg_rec_time = ?", date, time)
                    .where("conver_id = ?", con_id).execute();


        } else {

            DBHandler.getInstance().addToConversation(message.getDisplayOriginatingAddress(), conversationList.size());
            DBHandler.getInstance().
                    addMessage(message.getMessageBody(), message.getDisplayOriginatingAddress(), "Received", con_id);


        }
    }}










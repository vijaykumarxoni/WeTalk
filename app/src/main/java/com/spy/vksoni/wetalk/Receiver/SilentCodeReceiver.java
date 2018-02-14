package com.spy.vksoni.wetalk.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.spy.vksoni.wetalk.R;
import com.spy.vksoni.wetalk.db.AlertCode;
import com.spy.vksoni.wetalk.db.SmsCode;

import java.util.List;

import static android.content.Context.AUDIO_SERVICE;

/**
 * Created by Vksoni on 2/10/2018.
 */

public class SilentCodeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundlePDUS = intent.getExtras();
        Object[] pdus = (Object[]) bundlePDUS.get("pdus");
        SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[0]);

        SmsCode smsCode=new Select().all().from(SmsCode.class).executeSingle();
        AlertCode   alert= new Select().all().from(AlertCode.class).executeSingle();

if(smsCode!=null){
    Toast.makeText(context, "Silent wala receiver", Toast.LENGTH_SHORT).show();

    if(message.getDisplayMessageBody().contains(smsCode.code)){
            AudioManager audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);

            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            Toast.makeText(context, "Silent sy hatja", Toast.LENGTH_SHORT).show();

        }

    else if(message.getDisplayMessageBody().contains(alert.code)){
        final MediaPlayer songplyer = MediaPlayer.create(context, R.raw.alert);
        songplyer.start();

    }
    }}
}

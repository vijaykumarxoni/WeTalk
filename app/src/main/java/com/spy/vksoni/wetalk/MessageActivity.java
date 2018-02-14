package com.spy.vksoni.wetalk;

import android.Manifest;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.spy.vksoni.wetalk.adapter.ListViewAdapterMsg;
import com.spy.vksoni.wetalk.db.DBHandler;
import com.spy.vksoni.wetalk.db.SMSModel;

import java.util.List;

public class MessageActivity extends AppCompatActivity {

    ListView listViewMsg;
    ListViewAdapterMsg listViewAdapterMsg;
    List<SMSModel> msgList;
    EditText editTextMessageWrite;
    FloatingActionButton floatingActionButtonSend;
    RippleView rippleView;
    String phone_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        listViewMsg=(ListView)findViewById(R.id.listViewMsg);
        Intent i=getIntent();
        phone_no=i.getStringExtra("phone_no");
        editTextMessageWrite=(EditText)findViewById(R.id.editTextMessageWrite);
        msgList= DBHandler.getInstance().getSMS(phone_no);
        listViewAdapterMsg=new ListViewAdapterMsg(getApplicationContext(),msgList);
        floatingActionButtonSend=(FloatingActionButton)findViewById(R.id.floatingBtnSend);
        listViewMsg.setAdapter(listViewAdapterMsg);
        rippleView=(RippleView)findViewById(R.id.rippleView);
//
//
//        rippleView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               SmsManager smsManager = SmsManager.getDefault();
//                   smsManager.sendTextMessage(phone_no,
//                           null, editTextMessageWrite.getText().toString(), null, null);
//                   Toast.makeText(getApplicationContext(),"Sended to:"+phone_no,Toast.LENGTH_SHORT).show();
//
//
//                editTextMessageWrite.setText("");
//            }
//
//        });

        floatingActionButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phone_no,
                        null, editTextMessageWrite.getText().toString(), null, null);
                Toast.makeText(getApplicationContext(),"Sended to:"+phone_no,Toast.LENGTH_SHORT).show();

                editTextMessageWrite.setText("");
            }

        });

    }
}

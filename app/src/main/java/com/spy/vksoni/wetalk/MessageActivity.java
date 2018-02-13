package com.spy.vksoni.wetalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.spy.vksoni.wetalk.adapter.ListViewAdapterMsg;
import com.spy.vksoni.wetalk.db.DBHandler;
import com.spy.vksoni.wetalk.db.SMSModel;

import java.util.List;

public class MessageActivity extends AppCompatActivity {

    ListView listViewMsg;
    ListViewAdapterMsg listViewAdapterMsg;
    List<SMSModel> msgList;
    String phone_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        listViewMsg=(ListView)findViewById(R.id.listViewMsg);
        Intent i=getIntent();
        phone_no=i.getStringExtra("phone_no");
        msgList= DBHandler.getInstance().getSMS(phone_no);
        listViewAdapterMsg=new ListViewAdapterMsg(getApplicationContext(),msgList);

        listViewMsg.setAdapter(listViewAdapterMsg);

    }
}

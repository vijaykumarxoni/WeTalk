package com.spy.vksoni.wetalk;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.andexert.library.RippleView;
import com.spy.vksoni.wetalk.adapter.ListViewAdapterMsg;
import com.spy.vksoni.wetalk.db.DBHandler;
import com.spy.vksoni.wetalk.db.SMSModel;

import java.util.List;

public class MessageActivity extends AppCompatActivity {

    ListView listViewMsg;
    public static ListViewAdapterMsg listViewAdapterMsg;
    public static List<SMSModel> msgList;
    EditText editTextMsg;
    FloatingActionButton floatingActionButtonSend;
    RippleView rippleView;
    String phone_no;
    String msgBody;

    public static MessageActivity instance=new MessageActivity();;
    public static MessageActivity newIntenence(){
        return  instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        listViewMsg=(ListView)findViewById(R.id.listViewMsg);
        Intent i=getIntent();
        phone_no=i.getStringExtra("phone_no");
        editTextMsg=(EditText)findViewById(R.id.editTextMsg);
        msgList= DBHandler.getInstance().getSMS(phone_no);
        listViewAdapterMsg=new ListViewAdapterMsg(getApplicationContext(),msgList);
        floatingActionButtonSend=(FloatingActionButton)findViewById(R.id.floatingBtnSend);
        listViewMsg.setAdapter(listViewAdapterMsg);
        rippleView=(RippleView)findViewById(R.id.rippleView);


//        rippleView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               try{ SmsManager smsob=SmsManager.getDefault();
//                smsob.sendTextMessage(phone_no, null, editTextMessageWrite.getText().toString(), null, null);
//                Toast.makeText(getApplicationContext(),"Sended to:"+phone_no,Toast.LENGTH_SHORT).show();
//
//               }catch (Exception e){}
//                editTextMessageWrite.setText("");
//            }
//
//        });


        floatingActionButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{ SmsManager smsob=SmsManager.getDefault();
                    msgBody=  editTextMsg.getText().toString();

                  DBHandler.getInstance().addSendMessage(editTextMsg.getText().toString(),
                          phone_no,"Sended");

                    smsob.sendTextMessage(phone_no, null,msgBody , null, null);
                    Toast.makeText(getApplicationContext(),"Sended to:"+phone_no,Toast.LENGTH_SHORT).show();
                }catch (Exception e){}
                editTextMsg.setText("");
            }

        });

    }


    public static void refreshList(SMSModel smsModel){
        listViewAdapterMsg.refreshAdapter(smsModel);
    }


}

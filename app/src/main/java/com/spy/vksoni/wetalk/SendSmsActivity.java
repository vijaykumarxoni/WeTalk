package com.spy.vksoni.wetalk;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendSmsActivity extends AppCompatActivity {
    private static final String TAG ="Message to:" ;
    private EditText to,msg;
    private Button send;

    private static final int REQUEST_CODE = 1;
    String name1,number1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        to=findViewById(R.id.sendsmsto);
        msg=findViewById(R.id.msg);
        send=findViewById(R.id.sendsms);

        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://contacts");
                Intent intent = new Intent(Intent.ACTION_PICK, uri);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg1=msg.getText().toString();
                try {
                    SmsManager smsob=SmsManager.getDefault();
                    smsob.sendTextMessage(number1, null, msg1, null, null);
                    Toast.makeText(getApplicationContext(),"Message sent to:"+name1, Toast.LENGTH_SHORT).show();

                }
                catch(Exception e){

                    Toast.makeText(getApplicationContext(),"Exception"+e.getMessage(),Toast.LENGTH_SHORT).show();

                }

            }
        });



    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Uri uri = intent.getData();
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};

                Cursor cursor = getContentResolver().query(uri, projection,
                        null, null, null);
                cursor.moveToFirst();

                int numberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberColumnIndex);

                int nameColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                String name = cursor.getString(nameColumnIndex);

                Log.d(TAG, "ZZZ number : " + number + " , name : " + name);
                to.setText(""+name+":("+number+")");
                number1=number;
                name1=name;
            }
        }
    }
}

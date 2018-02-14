package com.spy.vksoni.wetalk;

import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.spy.vksoni.wetalk.db.SmsCode;

public class SmsSilenceModeRemoveActivity extends AppCompatActivity {
Button btnSetCode,btnUpdatecode;
EditText editTextCode;
    SmsCode smsCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_silence_mode_remove);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},123);

//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//            }
//        });
        btnSetCode=(Button)findViewById(R.id.btnSetCode);
        btnUpdatecode=(Button)findViewById(R.id.btnSetUpdateCode);
        editTextCode=(EditText)findViewById(R.id.editTextCode);
        try{
         smsCode=new Select().all().from(SmsCode.class).executeSingle();
        if(smsCode.code!=null){
            editTextCode.setText(""+smsCode.code);
            editTextCode.setEnabled(false);
            btnSetCode.setEnabled(false);
        }}
        catch (Exception e){}
        btnSetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextCode.getText().toString().contains("SLM#-")){
                SmsCode smsCode=new SmsCode();
                smsCode.code=editTextCode.getText().toString();
                smsCode.save();
                btnSetCode.setEnabled(false);
                editTextCode.setEnabled(false);
              }
                else{

                    Snackbar.make(view, "Incorrect code format.Please write in format: SLM#-example  ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }


        });
    btnUpdatecode.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                new Update(SmsCode.class).set( "code =?",editTextCode.getText().toString())
                        .execute();
                btnUpdatecode.setVisibility(View.INVISIBLE);
            editTextCode.setEnabled(false);


            Snackbar.make(view, "Sucessfully code updated ", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    });
    }


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.sms_silence_code_menu,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getTitle().toString().equalsIgnoreCase("Update Code")){
            editTextCode.setEnabled(true);
            btnSetCode.setEnabled(false);
            btnUpdatecode.setVisibility(View.VISIBLE);

        }

        return true; }

}



package com.spy.vksoni.wetalk;

import android.Manifest;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.spy.vksoni.wetalk.db.AlertCode;
import com.spy.vksoni.wetalk.db.SmsCode;

public class AlertToneSetActivity extends AppCompatActivity {
    Button btnSetCode,btnUpdatecode;
    EditText editTextCode;
    AlertCode alertCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_tone_set);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 123);
        btnSetCode = (Button) findViewById(R.id.btnSetAlertCode);
        btnUpdatecode = (Button) findViewById(R.id.btnSetUpdateAlertCode);
        editTextCode = (EditText) findViewById(R.id.editTextAlertCode);
        try{
            alertCode =new Select().all().from(AlertCode.class).executeSingle();

            if(alertCode.code!=null){
                editTextCode.setText(""+alertCode.code);
                editTextCode.setEnabled(false);
                btnSetCode.setEnabled(false);
            }}
        catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        btnSetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextCode.getText().toString().contains("ALERT#-")) {
                    AlertCode alertcode = new AlertCode();

                    alertcode.code = editTextCode.getText().toString();
                    alertcode.save();
                    btnSetCode.setEnabled(false);
                    editTextCode.setEnabled(false);


                } else {

                    Snackbar.make(v, "Incorrect code format.Please write in format: SLM#-example  ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });


        btnUpdatecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    new Update(AlertCode.class).set("code =?", editTextCode.getText().toString())
                            .execute();
                    btnUpdatecode.setVisibility(View.INVISIBLE);
                    editTextCode.setEnabled(false);
                }
                catch (Exception e){

                    Toast.makeText(AlertToneSetActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                Snackbar.make(view, "Sucessfully code updated ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sms_silence_code_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().toString().equalsIgnoreCase("Update Code")) {
            editTextCode.setEnabled(true);
            btnSetCode.setEnabled(false);
            btnUpdatecode.setVisibility(View.VISIBLE);

        }

        return true;
    }


}





package com.spy.vksoni.wetalk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
    private EditText userName,userPass,userEmail,userContact;
    private Button create;
    public static int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userName= findViewById(R.id.user_name);
        userPass=findViewById(R.id.user_password);
        userEmail=findViewById(R.id.user_email);
        userContact= findViewById(R.id.user_number);
        create=findViewById(R.id.create);



    }
}

package com.spy.vksoni.wetalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.spy.vksoni.wetalk.db.User;

public class SignUpActivity extends AppCompatActivity {
    private EditText userName,userPass,userEmail,userContact;
    private Button btnCreate;
    final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userName= findViewById(R.id.user_name);
        userPass=findViewById(R.id.user_password);
        userEmail=findViewById(R.id.user_email);
        userContact= findViewById(R.id.user_number);
        btnCreate=findViewById(R.id.create);

        btnCreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(userEmail.getText().toString().equals("") && userName.getText().toString().equals("")
                        && userPass.getText().toString().equals("") && userContact.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Please provide complete information! ",Toast.LENGTH_SHORT).show();}

                // if valid email
                else{ if((userEmail.getText().toString().matches(emailPattern))){


                    User user=new User();
                    user.userId=new Select().all().from(User.class).execute().size();
                    user.userEmail=userEmail.getText().toString();
                    user.userName=userName.getText().toString();
                    user.userPassword=userPass.getText().toString();
                    user.userContactno=userContact.getText().toString();
                    user.save();

                    // move back to login activity
                    startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                    finish();

                    Toast.makeText(getApplicationContext(),"Successfully account created",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getApplicationContext(),"Incorrect email format ",Toast.LENGTH_SHORT).show();

                }

                }



            }
        });



    }
}

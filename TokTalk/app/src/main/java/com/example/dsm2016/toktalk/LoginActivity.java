package com.example.dsm2016.toktalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private Button login_btn;
    private EditText idfield,pwdfield;
    private TextView signup_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_btn=(Button)findViewById(R.id.login_btn);
        idfield=(EditText)findViewById(R.id.login_id_et);
        pwdfield=(EditText)findViewById(R.id.login_pwd_et);
        signup_btn=(TextView)findViewById(R.id.signup_btn);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SignupAcitivity.class);
                startActivity(intent);
            }
        });
    }
}

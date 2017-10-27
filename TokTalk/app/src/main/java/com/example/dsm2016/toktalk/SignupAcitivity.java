package com.example.dsm2016.toktalk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupAcitivity extends AppCompatActivity {
    private EditText namefield,idfield,pwdfield,confirm_pwdfield;
    private TextView signinbtn;
    private Button signupbtn;
    private String name,id,pwd,confirm_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        namefield=(EditText)findViewById(R.id.signup_name_et);
        idfield=(EditText)findViewById(R.id.signup_id_et);
        pwdfield=(EditText)findViewById(R.id.login_pwd_et);
        confirm_pwdfield=(EditText)findViewById(R.id.signup_cofirm_pwd_et);
        signinbtn=(TextView)findViewById(R.id.signin);
        signupbtn=(Button)findViewById(R.id.signup);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=namefield.getText().toString();
                id=idfield.getText().toString();
                pwd=pwdfield.getText().toString();
                confirm_pwd=confirm_pwdfield.getText().toString();
                if(name.length()==0||id.length()==0||pwd.length()==0||confirm_pwd.length()==0){
                    Toast.makeText(SignupAcitivity.this,"빈칸이 있으면 안됩니다.",Toast.LENGTH_LONG).show();
                }
                else{
                    if(pwd.equals(confirm_pwd)){
                        Toast.makeText(SignupAcitivity.this,"비밀번호가 일치하지 않습니다.",Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(SignupAcitivity.this,"회원가입이 완료되었습니다.",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }
        });
    }
}

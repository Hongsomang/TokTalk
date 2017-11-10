package com.example.dsm2016.toktalk;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupAcitivity extends AppCompatActivity {
    private EditText namefield,emailfield,pwdfield,confirm_pwdfield;
    private TextView signInbtn;
    private Button signupbtn;
    private String name,email,pwd,confirm_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signInbtn=(TextView)findViewById(R.id.signin);
        signupbtn=(Button)findViewById(R.id.signup);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namefield=(EditText)findViewById(R.id.signup_name_et);
                emailfield=(EditText)findViewById(R.id.signup_email_et);
                pwdfield=(EditText)findViewById(R.id.signup_pwd_et);
                confirm_pwdfield=(EditText)findViewById(R.id.signup_cofirm_pwd_et);

                name=namefield.getText().toString();
                email=emailfield.getText().toString();
                pwd=pwdfield.getText().toString();
                confirm_pwd=confirm_pwdfield.getText().toString();
                Log.d("pwd",pwd);
                Log.d("confirm_pwd",confirm_pwd);
                if(name.length()==0||email.length()==0||pwd.length()==0||confirm_pwd.length()==0){
                    Toast.makeText(SignupAcitivity.this,"빈칸이 있으면 안됩니다.",Toast.LENGTH_LONG).show();
                }
                else{
                    if(pwd.equals(confirm_pwd)){
                        FirebaseAuth.getInstance()
                                .createUserWithEmailAndPassword(email,pwd)
                                .addOnCompleteListener(SignupAcitivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Log.d("dfd","df");
                                    }
                                });


                        Toast.makeText(SignupAcitivity.this,"회원가입이 완료되었습니다.",Toast.LENGTH_LONG).show();
                        finish();

                    }
                    else{
                        Toast.makeText(SignupAcitivity.this,"비밀번호가 일치하지 않습니다.",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}

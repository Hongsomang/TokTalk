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

import com.example.dsm2016.toktalk.Model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupAcitivity extends AppCompatActivity {
    private EditText namefield, emailfield, pwdfield, confirm_pwdfield;
    private TextView signInbtn;
    private Button signupbtn;
    private String name, email, pwd, confirm_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signInbtn = (TextView) findViewById(R.id.signin);
        signupbtn = (Button) findViewById(R.id.signup);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(getApplication().getClass().getName(), "start1");

                namefield = (EditText) findViewById(R.id.signup_name_et);
                emailfield = (EditText) findViewById(R.id.signup_email_et);
                pwdfield = (EditText) findViewById(R.id.signup_pwd_et);
                confirm_pwdfield = (EditText) findViewById(R.id.signup_cofirm_pwd_et);
                Log.d(getApplication().getClass().getName(), "finish2");

                name = namefield.getText().toString();
                email = emailfield.getText().toString();
                pwd = pwdfield.getText().toString();
                confirm_pwd = confirm_pwdfield.getText().toString();
                Log.d(getApplication().getClass().getName(), "start");
                Log.d("pwd", pwd);
                Log.d("confirm_pwd", confirm_pwd);
                Log.d("name", name);
                Log.d("email", email);
                Log.d(getApplication().getClass().getName(), "finish");
                if (name.length() == 0 || email.length() == 0 || pwd.length() == 0 || confirm_pwd.length() == 0) {
                    Toast.makeText(SignupAcitivity.this, "빈칸이 있으면 안됩니다.", Toast.LENGTH_LONG).show();
                } else {
                    if (pwd.equals(confirm_pwd)) {
                      if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ||pwd.length()<=6){
                          Toast.makeText(SignupAcitivity.this, "이메일 형식이 올바른지 확인해 주시고,\n 비밀번호는 6자리이상 입력해주세요.", Toast.LENGTH_LONG).show();

                      }else {
                          FirebaseAuth.getInstance()
                                  .createUserWithEmailAndPassword(email, pwd)
                                  .addOnCompleteListener(SignupAcitivity.this, new OnCompleteListener<AuthResult>() {
                                      @Override
                                      public void onComplete(@NonNull Task<AuthResult> task) {
                                          UserModel userModel = new UserModel();
                                          userModel.userName = name;
                                          String uid = task.getResult().getUser().getUid();
                                          Toast.makeText(SignupAcitivity.this, "데이터베이스 저장.", Toast.LENGTH_LONG).show();
                                          FirebaseDatabase.getInstance().getReference().child("users").child(uid).setValue(userModel);
                                          Toast.makeText(SignupAcitivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_LONG).show();
                                          Toast.makeText(SignupAcitivity.this, name, Toast.LENGTH_LONG).show();
                                          Toast.makeText(SignupAcitivity.this, email, Toast.LENGTH_LONG).show();
                                          Toast.makeText(SignupAcitivity.this, pwd, Toast.LENGTH_LONG).show();
                                          Toast.makeText(SignupAcitivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_LONG).show();
                                          finish();
                                          Log.d("dfd", "df");
                                      }
                                  });

                      }

                    } else {
                        Toast.makeText(SignupAcitivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}

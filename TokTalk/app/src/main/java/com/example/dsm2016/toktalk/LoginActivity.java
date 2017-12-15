package com.example.dsm2016.toktalk;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class LoginActivity extends AppCompatActivity {
    private Button login_btn;
    private EditText idfield,pwdfield;
    private TextView signup_btn;
    private FirebaseRemoteConfig mfirebaseRemoteConfig;
    private FirebaseAuth firebaseAuth;
    private  FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_btn=(Button)findViewById(R.id.login_btn);
        idfield=(EditText)findViewById(R.id.login_id_et);
        pwdfield=(EditText)findViewById(R.id.login_pwd_et);
        signup_btn=(TextView)findViewById(R.id.signup_btn);
        //로그인버튼
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginEvent();
            }
        });
        //회원가입 버튼
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SignupAcitivity.class);
                startActivity(intent);
            }
        });
        mfirebaseRemoteConfig=FirebaseRemoteConfig.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        //로그인 인터페이스 리스너
        authStateListener=new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                    //로그인
                 Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    //로그아웃
                }
            }
        };
    }
    void LoginEvent(){


        firebaseAuth.signInWithEmailAndPassword(idfield.getText().toString(),pwdfield.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    //로그인 실패부분
                    Toast.makeText(LoginActivity.this,"로그인 실패",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }
}

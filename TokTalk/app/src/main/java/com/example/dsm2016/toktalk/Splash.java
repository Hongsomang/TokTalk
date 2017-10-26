package com.example.dsm2016.toktalk;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.appcompat.*;
import android.support.v7.appcompat.BuildConfig;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class Splash extends AppCompatActivity {
    LinearLayout linearLayout;
    private FirebaseRemoteConfig mfirebaseRemoteConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        linearLayout=(LinearLayout)findViewById(R.id.splash_layout);
        //원격 구정 개체 인스턴스를 가져오고 캐시를 비번하게 새로고칠 수 있도록 개발자 모들르 사용 설정
        mfirebaseRemoteConfig=FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings=new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mfirebaseRemoteConfig.setConfigSettings(configSettings);
        mfirebaseRemoteConfig.setDefaults(R.xml.default_config);

        mfirebaseRemoteConfig.fetch()
                .addOnCompleteListener(this, new OnCompleteListener<Void>(){

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Splash.this,"Fetch Succeeded",Toast.LENGTH_LONG).show();
                            mfirebaseRemoteConfig.activateFetched();
                        }else {
                            Toast.makeText(Splash.this,"Fetch Failed",Toast.LENGTH_LONG).show();

                        }
                        displayMessage();

                    }
                });
    }
    void displayMessage(){
        String splash_background=mfirebaseRemoteConfig.getString("splash_background");
        boolean caps=mfirebaseRemoteConfig.getBoolean("splash_message_caps");
        String splash_message=mfirebaseRemoteConfig.getString("splash_message");
        linearLayout.setBackgroundColor(Color.parseColor(splash_background));
        if(caps){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage(splash_message).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            builder.create().show();
        }
        else{

            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                //Do Something
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    Intent i = new Intent(Splash.this, LoginActivity.class); // xxx가 현재 activity,
                    //yyy가 이동할 activity
                    startActivity(i);
                    finish();
                }
            }, 2000);



        }
    }

}

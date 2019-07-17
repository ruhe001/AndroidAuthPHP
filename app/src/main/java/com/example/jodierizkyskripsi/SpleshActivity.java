package com.example.jodierizkyskripsi;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SpleshActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splesh_activity);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent intenUtama = new Intent(SpleshActivity.this,PembobotanActivity.class);
                startActivity(intenUtama);
                finish();
            }
        }, 3000);
    }
}

package com.example.jodierizkyskripsi;

import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jodierizkyskripsi.Common.Common;
import com.example.jodierizkyskripsi.Model.APIResponse;
import com.example.jodierizkyskripsi.Remote.IMyAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    Button btn_login,btn_bobot;
    private Location mLastLocation;

    IMyAPI mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mService= Common.getAPI();
        

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(MainActivity.this,PembobotanActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 3000);
    }


}

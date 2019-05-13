package com.example.androidauthphp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class HasilTopsisActivity extends AppCompatActivity {


    private TextView txt_hasil_pasar,txt_hasil_pendapatan,txt_hasil_infrastruktur,txt_hasil_transportasi
            ,txt_hasil_lokasi,KNTL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_topsis);

        txt_hasil_pasar = (TextView)findViewById(R.id.text_pasar);
        txt_hasil_pendapatan = (TextView)findViewById(R.id.text_pendapatan);
        txt_hasil_infrastruktur = (TextView)findViewById(R.id.text_infrastruktur);
        txt_hasil_transportasi = (TextView)findViewById(R.id.text_transportasi);
        txt_hasil_lokasi = (TextView)findViewById(R.id.text_lokasi);


        Intent intent = getIntent();

        Integer bobot_pasar = intent.getIntExtra("bobot_pasar",0);
        Integer bobot_pendapatan = intent.getIntExtra("bobot_pendapatan",0);
        Integer bobot_infrastruktur = intent.getIntExtra("bobot_infrastruktur",0);
        Integer bobot_transportasi = intent.getIntExtra("bobot_transportasi",0);
        String txt_lokasi = intent.getStringExtra("txt_lokasi");

        txt_hasil_pasar.setText(bobot_pasar);
        txt_hasil_pendapatan.setText(bobot_pendapatan);
        txt_hasil_infrastruktur.setText(bobot_infrastruktur);
        txt_hasil_transportasi.setText(bobot_transportasi);
        txt_hasil_lokasi.setText(txt_lokasi);

    }
}

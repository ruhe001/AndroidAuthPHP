package com.example.androidauthphp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailHasilActivity extends AppCompatActivity {

    ImageView img_detail_tempat, img_detail_lokasi;
    TextView txt_detail_nama, txt_detail_harga, txt_detail_kadaluarsa;

    String detail_nama, detail_harga, detail_kadaluarsa, detail_lat, detail_long;

    Uri gmmIntentUri;
    Intent mapIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hasil);

        img_detail_tempat = (ImageView)findViewById(R.id.image_detail_tempat);
        img_detail_lokasi = (ImageView)findViewById(R.id.image_detail_lokasi);

        txt_detail_nama = (TextView)findViewById(R.id.text_detail_nama);
        txt_detail_harga = (TextView)findViewById(R.id.text_detail_harga);
        txt_detail_kadaluarsa = (TextView)findViewById(R.id.text_detail_kadaluarsa);

        Intent intent = getIntent();

        detail_nama = intent.getStringExtra("hasil_nama");
        detail_harga = intent.getStringExtra("hasil_harga");
        detail_kadaluarsa = intent.getStringExtra("hasil_kadaluarsa");
        detail_lat = intent.getStringExtra("hasil_latitude");
        detail_long= intent.getStringExtra("hasil_longitude");

        //if (detail_nama != null){
            txt_detail_nama.setText(detail_nama);
            txt_detail_harga.setText(detail_harga);
            txt_detail_kadaluarsa.setText(detail_kadaluarsa);
        //} else{
         //   txt_detail_nama.setText("null");
         //   txt_detail_harga.setText("null");
          //  txt_detail_kadaluarsa.setText("null");
        //}



        img_detail_tempat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gmmIntentUri = Uri.parse("google.navigation:q=-7.94065,112.623967");
                gmmIntentUri = Uri.parse("google.navigation:q="+detail_lat+","+detail_long);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
    }
}

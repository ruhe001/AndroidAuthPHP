package com.example.jodierizkyskripsi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailHasilActivity extends AppCompatActivity {

    ImageView img_detail_tempat, img_detail_lokasi;
    TextView txt_detail_nama, txt_detail_harga, txt_detail_deskripsi;

    String detail_nama, detail_harga, detail_deskripsi, detail_lat, detail_long,gambar;

    Uri gmmIntentUri;
    Intent mapIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hasil);

        img_detail_tempat = (ImageView)findViewById(R.id.image_detail_tempat);
//        img_detail_lokasi = (ImageView)findViewById(R.id.image_detail_lokasi);


        txt_detail_nama = (TextView)findViewById(R.id.text_detail_nama);
        txt_detail_harga = (TextView)findViewById(R.id.text_detail_harga);
        txt_detail_deskripsi = (TextView)findViewById(R.id.text_detail_deskripsi);

        Intent intent = getIntent();

        detail_nama = intent.getStringExtra("hasil_nama");
        detail_harga = intent.getStringExtra("hasil_harga");
        detail_deskripsi = intent.getStringExtra("hasil_deskripsi");
        detail_lat = intent.getStringExtra("hasil_latitude");
        detail_long= intent.getStringExtra("hasil_longitude");
        gambar = intent.getStringExtra("hasil_gambar");

//        Picasso.with(this).load(gambar).into(img_detail_lokasi);
        //if (detail_nama != null){
            txt_detail_nama.setText(detail_nama);
            txt_detail_harga.setText(detail_harga);
            txt_detail_deskripsi.setText(detail_deskripsi);
        //} else{
         //   txt_detail_nama.setText("null");
         //   txt_detail_harga.setText("null");
          //  txt_detail_kadaluarsa.setText("null");
        //}



        img_detail_tempat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+detail_lat+","+detail_long));

                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    startActivity(intent);
                
            }
        });
    }
}

package com.example.jodierizkyskripsi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailHasilActivity extends AppCompatActivity {

    ImageView gambarTempat;
    TextView tombolPeta, txt_rinci_nama, txt_rinci_harga, txt_rinci_desk;

    String detail_nama, detail_harga, detail_deskripsi, detail_lat, detail_long,gambar,current_lat,current_long;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hasil_rinci_activity);
        tombolPeta = (TextView)findViewById(R.id.tombol_peta);
        gambarTempat = (ImageView)findViewById(R.id.image_detail_lokasi);


        txt_rinci_nama = (TextView)findViewById(R.id.tulisan_rinci_nama);
        txt_rinci_harga = (TextView)findViewById(R.id.tulisan_rinci_harga);
        txt_rinci_desk = (TextView)findViewById(R.id.tulisan_rinci_deskripsi);

        Intent TOPSISintent = getIntent();

        detail_nama = TOPSISintent.getStringExtra("hasil_nama");
        detail_harga = TOPSISintent.getStringExtra("hasil_harga");
        detail_deskripsi = TOPSISintent.getStringExtra("hasil_deskripsi");
        detail_lat = TOPSISintent.getStringExtra("hasil_latitude");
        detail_long= TOPSISintent.getStringExtra("hasil_longitude");
        gambar = TOPSISintent.getStringExtra("hasil_gambar");
        current_lat = TOPSISintent.getStringExtra("current_lat");
        current_long = TOPSISintent.getStringExtra("current_long");

       Picasso.get().load(gambar).into(gambarTempat);
        if (detail_nama != null){
            txt_rinci_nama.setText(detail_nama);
            txt_rinci_harga.setText("Tiket Masuk: Rp"+detail_harga);
            txt_rinci_desk.setText(detail_deskripsi);

        } else{
            txt_rinci_nama.setText("Tidak dapat menemukan informasi.\n Pastikan koneksi internet aktif lalu restart aplikasi!");
            txt_rinci_harga.setText("");
            txt_rinci_desk.setText("");
        }



        tombolPeta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (detail_nama != null) {
                    Intent PETAinten = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?saddr=+" +current_lat +"," +current_long +"&daddr=" + detail_lat + "," + detail_long));

                    PETAinten.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    startActivity(PETAinten);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Tidak dapat menemukan informasi. Pastikan informasi ditemukan dan restart aplikasi!",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}

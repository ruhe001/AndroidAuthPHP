package com.example.jodierizkyskripsi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HasilTopsisActivity extends AppCompatActivity {


    private TextView txt_hasil_nama1,txt_hasil_harga1,
            txt_hasil_nama2,txt_hasil_harga2,
            txt_hasil_nama3,txt_hasil_harga3,
            txt_hasil_nama4,txt_hasil_harga4,
            txt_hasil_nama5,txt_hasil_harga5,txt_hasil_CI1;

    private ImageView gambar1,gambar2,gambar3,gambar4,gambar5;
    String bobot_harga, bobot_jarak, bobot_akses, bobot_fasilitas, bobot_edukasi, txt_latitude, txt_longitude;
    private LinearLayout linear1, linear2, linear3,linear4, linear5;

    String namaArray[] = new String[15];
    String hargaArray[] = new String[15];
    String latitudeArray[] = new String[15];
    String longitudeArray[] = new String[15];
    String gambarArray[] = new String[15];
    String deskripsiArray[]= new String[15];
    String ciArray[]= new String[15];


    private RequestQueue permintaanVolley; //Volley Queue

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hasil_topsis_activity);

        txt_hasil_nama1 = findViewById(R.id.text_nama1);
        txt_hasil_harga1 = findViewById(R.id.text_harga1);
        gambar1 = findViewById(R.id.gambar1);
        txt_hasil_nama2 = findViewById(R.id.text_nama2);
        txt_hasil_harga2 = findViewById(R.id.text_harga2);
        gambar2 =  findViewById(R.id.gambar2);
        txt_hasil_nama3 = findViewById(R.id.text_nama3);
        txt_hasil_harga3 = findViewById(R.id.text_harga3);
        gambar3 =  findViewById(R.id.gambar3);
        txt_hasil_nama4 = findViewById(R.id.text_nama4);
        txt_hasil_harga4 = findViewById(R.id.text_harga4);
        gambar4 =  findViewById(R.id.gambar4);
        txt_hasil_nama5 = findViewById(R.id.text_nama5);
        txt_hasil_harga5 = findViewById(R.id.text_harga5);
        gambar5 = findViewById(R.id.gambar5);

        //txt_hasil_CI1 = findViewById(R.id.text_CI1);

        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);
        linear3 = findViewById(R.id.linear3);
        linear4 = findViewById(R.id.linear4);
        linear5 = findViewById(R.id.linear5);
        Intent intenAmbil = getIntent();

        bobot_harga = intenAmbil.getStringExtra("bobot_harga");
        bobot_jarak = intenAmbil.getStringExtra("bobot_jarak");
        bobot_akses = intenAmbil.getStringExtra("bobot_akses");
        bobot_fasilitas = intenAmbil.getStringExtra("bobot_fasilitas");
        bobot_edukasi = intenAmbil.getStringExtra("bobot_edukasi");
        txt_latitude = intenAmbil.getStringExtra("txt_latitude");
        txt_longitude= intenAmbil.getStringExtra("txt_longitude");

        permintaanVolley = Volley.newRequestQueue(this);
        jsonParse(txt_latitude, txt_longitude, bobot_harga, bobot_jarak, bobot_akses, bobot_fasilitas, bobot_edukasi);

        linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intentToDetail = new Intent(view.getContext(), DetailHasilActivity.class);

                //Intent Bobot
                intentToDetail.putExtra("hasil_nama", namaArray[0]);
                intentToDetail.putExtra("hasil_harga", hargaArray[0]);
                intentToDetail.putExtra("hasil_latitude", latitudeArray[0]);
                intentToDetail.putExtra("hasil_longitude", longitudeArray[0]);
                intentToDetail.putExtra("hasil_gambar", gambarArray[0]);
                intentToDetail.putExtra("hasil_deskripsi",deskripsiArray[0]);
                intentToDetail.putExtra("current_lat",txt_latitude);
                intentToDetail.putExtra("current_long",txt_longitude);
                startActivity(intentToDetail);

            }
        });
        linear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentToDetail = new Intent(view.getContext(), DetailHasilActivity.class);

                //Intent Bobot
                intentToDetail.putExtra("hasil_nama", namaArray[1]);
                intentToDetail.putExtra("hasil_harga", hargaArray[1]);
                intentToDetail.putExtra("hasil_latitude", latitudeArray[1]);
                intentToDetail.putExtra("hasil_longitude", longitudeArray[1]);
                intentToDetail.putExtra("hasil_gambar", gambarArray[1]);
                intentToDetail.putExtra("hasil_deskripsi",deskripsiArray[1]);
                intentToDetail.putExtra("current_lat",txt_latitude);
                intentToDetail.putExtra("current_long",txt_longitude);
                startActivity(intentToDetail);

            }
        });
        linear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intentToDetail = new Intent(view.getContext(), DetailHasilActivity.class);

                //Intent Bobot
                intentToDetail.putExtra("hasil_nama", namaArray[2]);
                intentToDetail.putExtra("hasil_harga", hargaArray[2]);
                intentToDetail.putExtra("hasil_latitude", latitudeArray[2]);
                intentToDetail.putExtra("hasil_longitude", longitudeArray[2]);
                intentToDetail.putExtra("hasil_gambar", gambarArray[2]);
                intentToDetail.putExtra("hasil_deskripsi",deskripsiArray[2]);
                intentToDetail.putExtra("current_lat",txt_latitude);
                intentToDetail.putExtra("current_long",txt_longitude);
                startActivity(intentToDetail);

            }
        });
        linear4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intentToDetail = new Intent(view.getContext(), DetailHasilActivity.class);

                //Intent Bobot
                intentToDetail.putExtra("hasil_nama", namaArray[3]);
                intentToDetail.putExtra("hasil_harga", hargaArray[3]);
                intentToDetail.putExtra("hasil_latitude", latitudeArray[3]);
                intentToDetail.putExtra("hasil_longitude", longitudeArray[3]);
                intentToDetail.putExtra("hasil_gambar", gambarArray[3]);
                intentToDetail.putExtra("hasil_deskripsi",deskripsiArray[3]);
                intentToDetail.putExtra("current_lat",txt_latitude);
                intentToDetail.putExtra("current_long",txt_longitude);
                startActivity(intentToDetail);

            }
        });
        linear5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentToDetail = new Intent(view.getContext(), DetailHasilActivity.class);

                //Intent Bobot
                intentToDetail.putExtra("hasil_nama", namaArray[4]);
                intentToDetail.putExtra("hasil_harga", hargaArray[4]);
                intentToDetail.putExtra("hasil_latitude", latitudeArray[4]);
                intentToDetail.putExtra("hasil_longitude", longitudeArray[4]);
                intentToDetail.putExtra("hasil_gambar", gambarArray[4]);
                intentToDetail.putExtra("hasil_deskripsi",deskripsiArray[4]);
                intentToDetail.putExtra("current_lat",txt_latitude);
                intentToDetail.putExtra("current_long",txt_longitude);
                startActivity(intentToDetail);

            }
        });
    }

    private void jsonParse(String txtLatitude, String txtLongitude,
                           String bobot_harga, String bobot_jarak,
                           String bobot_akses, String bobot_fasilitas, String bobot_edukasi){

        String url = "https://topsisfhj.xyz/TOPSIS_WISATA_BATU/topsis_wisata.php?"
                +"lat="+txtLatitude
                +"&long="+txtLongitude
                +"&harga="+bobot_harga
                +"&jarak="+bobot_jarak
                +"&akses="+bobot_akses
                +"&fasilitas="+bobot_fasilitas
                +"&edukasi="+bobot_edukasi
                +"&jum_rekomendasi=5";

         JsonArrayRequest rikues = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for (int jd = 0; jd < response.length(); jd++) {

                                JSONObject jsonTest = response.getJSONObject(jd);

                                namaArray[jd] = jsonTest.getString("nama_tempat");
                                hargaArray[jd] = jsonTest.getString("harga");
                                latitudeArray[jd] = jsonTest.getString("latitude");
                                longitudeArray[jd] = jsonTest.getString("longitude");
                                gambarArray[jd] = jsonTest.getString("url");
                                deskripsiArray[jd]=jsonTest.getString("deskripsi");
                                ciArray[jd]=jsonTest.getString("CI");

                                txt_hasil_nama1.setText(namaArray[0]);
                                txt_hasil_harga1.setText("Harga Tiket Masuk: Rp"+hargaArray[0]);
                                gambar1.setImageResource(R.drawable.nomor1);
                                //txt_hasil_CI1.setText("Skor CI: "+ciArray[0]);

                                txt_hasil_nama2.setText(namaArray[1]);
                                txt_hasil_harga2.setText("Harga Tiket Masuk: Rp"+hargaArray[1]);
                                gambar2.setImageResource(R.drawable.nomor2);

                                txt_hasil_nama3.setText(namaArray[2]);
                                txt_hasil_harga3.setText("Harga Tiket Masuk: Rp"+hargaArray[2]);
                                gambar3.setImageResource(R.drawable.nomor3);

                                txt_hasil_nama4.setText(namaArray[3]);
                                txt_hasil_harga4.setText("Harga Tiket Masuk: Rp"+hargaArray[3]);
                                gambar4.setImageResource(R.drawable.nomor4);

                                txt_hasil_nama5.setText(namaArray[4]);
                                txt_hasil_harga5.setText("Harga Tiket Masuk: Rp"+hargaArray[4]);
                                gambar5.setImageResource(R.drawable.nomor5);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

            }
        });

        permintaanVolley.add(rikues);
    }

}

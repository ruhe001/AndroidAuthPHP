package com.example.androidauthphp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
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


    private TextView txt_hasil_nama,txt_hasil_harga,txt_hasil_akses,txt_hasil_fasilitas,txt_hasil_edukasi;

    String bobot_harga, bobot_jarak, bobot_akses, bobot_fasilitas, bobot_edukasi, txt_latitude, txt_longitude;

    String namaArray[] = new String[3];
    String hargaArray[] = new String[3];
    String aksesArray[] = new String[3];
    String fasilitasArray[] = new String[3];
    String edukasiArray[] = new String[3];
    String latitudeArray[] = new String[3];
    String longitudeArray[] = new String[3];
    String gambarArray[]= new String[3];

    Button btn_next_hasil;

    private RequestQueue mQueue; //Volley Queue

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_topsis);

        txt_hasil_nama = (TextView)findViewById(R.id.text_nama);
        txt_hasil_harga = (TextView)findViewById(R.id.text_harga);
        txt_hasil_akses = (TextView)findViewById(R.id.text_akses);
        txt_hasil_fasilitas = (TextView)findViewById(R.id.text_fasilitas);
        txt_hasil_edukasi = (TextView)findViewById(R.id.text_edukasi);

        btn_next_hasil = (Button)findViewById(R.id.button_next_hasil);

        Intent intent = getIntent();

        bobot_harga = intent.getStringExtra("bobot_harga");
        bobot_jarak = intent.getStringExtra("bobot_jarak");
        bobot_akses = intent.getStringExtra("bobot_akses");
        bobot_fasilitas = intent.getStringExtra("bobot_fasilitas");
        bobot_edukasi = intent.getStringExtra("bobot_edukasi");
        txt_latitude = intent.getStringExtra("txt_latitude");
        txt_longitude= intent.getStringExtra("txt_longitude");

        mQueue = Volley.newRequestQueue(this);
        jsonParse(txt_latitude, txt_longitude, bobot_harga, bobot_jarak, bobot_akses, bobot_fasilitas, bobot_edukasi);

        txt_hasil_nama.setText(namaArray[1]);
        txt_hasil_harga.setText(hargaArray[1]);
        txt_hasil_akses.setText(aksesArray[1]);
        txt_hasil_fasilitas.setText(fasilitasArray[1]);
        txt_hasil_edukasi.setText(edukasiArray[1]);

        btn_next_hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intentToDetail = new Intent(view.getContext(), DetailHasilActivity.class);



                //Intent Bobot
                intentToDetail.putExtra("hasil_nama", namaArray[1]);
                intentToDetail.putExtra("hasil_harga", hargaArray[1]);
                intentToDetail.putExtra("hasil_akses", aksesArray[1]);
                intentToDetail.putExtra("hasil_fasilitas", fasilitasArray[1]);
                intentToDetail.putExtra("hasil_edukasi", edukasiArray[1]);
                intentToDetail.putExtra("hasil_latitude", latitudeArray[1]);
                intentToDetail.putExtra("hasil_longitude", longitudeArray[1]);

                startActivity(intentToDetail);

            }
        });

    }

    private void jsonParse(String txtLatitude, String txtLongitude,
                           String bobot_harga, String bobot_jarak,
                           String bobot_akses, String bobot_fasilitas, String bobot_edukasi){

        String url = "http://topsisfhj.xyz/TOPSIS_WISATA_BATU/topsis_wisata.php?"
                +"lat="+txtLatitude
                +"&long="+txtLongitude
                +"&harga="+bobot_harga
                +"&jarak="+bobot_jarak
                +"&akses="+bobot_akses
                +"&fasilitas="+bobot_fasilitas
                +"&edukasi="+bobot_edukasi
                +"&jum_rekomendasi=3";

         JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonTest = response.getJSONObject(i);

                                namaArray[i] = jsonTest.getString("nama_tempat");
                                hargaArray[i] = jsonTest.getString("harga");
                                aksesArray[i] = jsonTest.getString("akses");
                                fasilitasArray[i] = jsonTest.getString("fasilitas");
                                edukasiArray[i] = jsonTest.getString("edukasi");
                                latitudeArray[i] = jsonTest.getString("latitude");
                                longitudeArray[i] = jsonTest.getString("longitude");
                                /*gambarArray[i] = jsonTest.getString("gambar");*/
                                /*
                                namaArray[i] = name;
                                hargaArray[i] = harga;
                                kadaluarsaArray[i] = kadaluarsa;
                                latitudeArray[i] = latitude;
                                longitudeArray[i] = longitude;



                                txt_hasil_nama.setText(namaArray[i]);
                                txt_hasil_harga.setText(hargaArray[i]);
                                txt_hasil_kadaluarsa.setText(kadaluarsaArray[i]);

                                */

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

        mQueue.add(request);
    }

//    @Override
//    public void finish(){
//        super.finish();
//        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
//
//    }
}

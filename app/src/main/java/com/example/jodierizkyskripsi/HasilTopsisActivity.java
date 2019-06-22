package com.example.jodierizkyskripsi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
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


    private TextView txt_hasil_nama1,txt_hasil_harga1,txt_hasil_akses1,txt_hasil_fasilitas1,txt_hasil_edukasi1,txt_hasil_url_gambar,
            txt_hasil_nama2,txt_hasil_harga2,txt_hasil_akses2,txt_hasil_fasilitas2,txt_hasil_edukasi2,
    txt_hasil_nama3,txt_hasil_harga3,txt_hasil_akses3,txt_hasil_fasilitas3,txt_hasil_edukasi3;

    String bobot_harga, bobot_jarak, bobot_akses, bobot_fasilitas, bobot_edukasi, txt_latitude, txt_longitude;
    private LinearLayout linear1, linear2, linear3;

    String namaArray[] = new String[3];
    String hargaArray[] = new String[3];
    String aksesArray[] = new String[3];
    String fasilitasArray[] = new String[3];
    String edukasiArray[] = new String[3];
    String latitudeArray[] = new String[3];
    String longitudeArray[] = new String[3];
    String gambarArray[] = new String[3];
    String deskripsiArray[]= new String[3];

    Button btn_next_hasil;

    private RequestQueue mQueue; //Volley Queue

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_topsis);

        txt_hasil_nama1 = (TextView)findViewById(R.id.text_nama1);
        txt_hasil_harga1 = (TextView)findViewById(R.id.text_harga1);
        /*txt_hasil_akses1 = (TextView)findViewById(R.id.text_akses1);
        txt_hasil_fasilitas1 = (TextView)findViewById(R.id.text_fasilitas1);
        txt_hasil_edukasi1 = (TextView)findViewById(R.id.text_edukasi1);*/

        txt_hasil_nama2 = (TextView)findViewById(R.id.text_nama2);
        txt_hasil_harga2 = (TextView)findViewById(R.id.text_harga2);
        /*txt_hasil_akses2 = (TextView)findViewById(R.id.text_akses2);
        txt_hasil_fasilitas2 = (TextView)findViewById(R.id.text_fasilitas2);
        txt_hasil_edukasi2 = (TextView)findViewById(R.id.text_edukasi2);*/

        txt_hasil_nama3 = (TextView)findViewById(R.id.text_nama3);
        txt_hasil_harga3 = (TextView)findViewById(R.id.text_harga3);
        /*txt_hasil_akses3 = (TextView)findViewById(R.id.text_akses3);
        txt_hasil_fasilitas3 = (TextView)findViewById(R.id.text_fasilitas3);
        txt_hasil_edukasi3 = (TextView)findViewById(R.id.text_edukasi3);*/
//        txt_hasil_url_gambar = (TextView)findViewById(R.id.text_url_gambar);

        linear1 = (LinearLayout)findViewById(R.id.linear1);
        linear2 = (LinearLayout)findViewById(R.id.linear2);
        linear3 = (LinearLayout)findViewById(R.id.linear3);
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



        linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intentToDetail = new Intent(view.getContext(), DetailHasilActivity.class);

                //Intent Bobot
                intentToDetail.putExtra("hasil_nama", namaArray[0]);
                intentToDetail.putExtra("hasil_harga", hargaArray[0]);
                intentToDetail.putExtra("hasil_akses", aksesArray[0]);
                intentToDetail.putExtra("hasil_fasilitas", fasilitasArray[0]);
                intentToDetail.putExtra("hasil_edukasi", edukasiArray[0]);
                intentToDetail.putExtra("hasil_latitude", latitudeArray[0]);
                intentToDetail.putExtra("hasil_longitude", longitudeArray[0]);
                intentToDetail.putExtra("hasil_gambar", gambarArray[0]);
                intentToDetail.putExtra("hasil_deskripsi",deskripsiArray[0]);
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
                intentToDetail.putExtra("hasil_akses", aksesArray[1]);
                intentToDetail.putExtra("hasil_fasilitas", fasilitasArray[1]);
                intentToDetail.putExtra("hasil_edukasi", edukasiArray[1]);
                intentToDetail.putExtra("hasil_latitude", latitudeArray[1]);
                intentToDetail.putExtra("hasil_longitude", longitudeArray[1]);
                intentToDetail.putExtra("hasil_gambar", gambarArray[1]);
                intentToDetail.putExtra("hasil_deskripsi",deskripsiArray[1]);
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
                intentToDetail.putExtra("hasil_akses", aksesArray[2]);
                intentToDetail.putExtra("hasil_fasilitas", fasilitasArray[2]);
                intentToDetail.putExtra("hasil_edukasi", edukasiArray[2]);
                intentToDetail.putExtra("hasil_latitude", latitudeArray[2]);
                intentToDetail.putExtra("hasil_longitude", longitudeArray[2]);
                intentToDetail.putExtra("hasil_gambar", gambarArray[2]);
                intentToDetail.putExtra("hasil_deskripsi",deskripsiArray[2]);
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
                                gambarArray[i] = jsonTest.getString("url");
                                deskripsiArray[i]=jsonTest.getString("deskripsi");


                                txt_hasil_nama1.setText(namaArray[0]);
                                txt_hasil_harga1.setText("Harga Tiket Masuk: Rp"+hargaArray[0]);
/*                                txt_hasil_akses1.setText(aksesArray[0]);
                                txt_hasil_fasilitas1.setText(fasilitasArray[0]);
                                txt_hasil_edukasi1.setText(edukasiArray[0]);
//                                txt_hasil_url_gambar.setText(gambarArray[1]);*/

                                txt_hasil_nama2.setText(namaArray[1]);
                                txt_hasil_harga2.setText("Harga Tiket Masuk: Rp"+hargaArray[1]);
                                /*txt_hasil_akses2.setText(aksesArray[1]);
                                txt_hasil_fasilitas2.setText(fasilitasArray[1]);
                                txt_hasil_edukasi2.setText(edukasiArray[1]);*/

                                txt_hasil_nama3.setText(namaArray[2]);
                                txt_hasil_harga3.setText("Harga Tiket Masuk: Rp"+hargaArray[2]);
                                /*txt_hasil_akses3.setText(aksesArray[2]);
                                txt_hasil_fasilitas3.setText(fasilitasArray[2]);
                                txt_hasil_edukasi3.setText(edukasiArray[2]);*/


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

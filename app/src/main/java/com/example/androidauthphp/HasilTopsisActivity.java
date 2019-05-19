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


    private TextView txt_hasil_nama,txt_hasil_harga,txt_hasil_kadaluarsa;

    String bobot_harga, bobot_jarak, bobot_kadaluarsa, txt_latitude, txt_longitude;
    String namaArray[] = new String[3];
    String hargaArray[] = new String[3];
    String kadaluarsaArray[] = new String[3];
    String latitudeArray[] = new String[3];
    String longitudeArray[] = new String[3];

    Button btn_next_hasil;

    private RequestQueue mQueue; //Volley Queue

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_topsis);

        txt_hasil_nama = (TextView)findViewById(R.id.text_nama);
        txt_hasil_harga = (TextView)findViewById(R.id.text_harga);
        txt_hasil_kadaluarsa = (TextView)findViewById(R.id.text_kadaluarsa);

        btn_next_hasil = (Button)findViewById(R.id.button_next_hasil);

        Intent intent = getIntent();

        bobot_harga = intent.getStringExtra("bobot_harga");
        bobot_jarak = intent.getStringExtra("bobot_jarak");
        bobot_kadaluarsa = intent.getStringExtra("bobot_kadaluarsa");
        txt_latitude = intent.getStringExtra("txt_latitude");
        txt_longitude= intent.getStringExtra("txt_longitude");

        mQueue = Volley.newRequestQueue(this);
        jsonParse(txt_latitude, txt_longitude, bobot_harga, bobot_jarak, bobot_kadaluarsa);

        btn_next_hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HasilTopsisActivity.this,DetailHasilActivity.class));

                Intent intentToDetail = new Intent(view.getContext(), DetailHasilActivity.class);

                txt_hasil_nama.setText(namaArray[0]);
                txt_hasil_harga.setText(hargaArray[0]);
                txt_hasil_kadaluarsa.setText(kadaluarsaArray[0]);

                //Intent Bobot
                intentToDetail.putExtra("hasil_nama", namaArray[1]);
                intentToDetail.putExtra("hasil_harga", hargaArray[1]);
                intentToDetail.putExtra("hasil_kadaluarsa", kadaluarsaArray[1]);
                intentToDetail.putExtra("hasil_latitude", latitudeArray[1]);
                intentToDetail.putExtra("hasil_longitude", longitudeArray[1]);

                startActivity(intentToDetail);

            }
        });

    }

    private void jsonParse(String txt_latitude, String txt_longitude, String bobot_harga, String bobot_jarak, String bobot_kadaluarsa){

        String url = "http://topsisfhj.xyz/TOPSIS_OLEH_OLEH/test_json_real_device.php?lat="+txt_latitude+"&long="+txt_longitude+"&harga="+bobot_harga+"&jarak="+bobot_jarak+"&kadaluarsa="+bobot_kadaluarsa;

         JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonTest = response.getJSONObject(i);

                                namaArray[i] = jsonTest.getString("name");
                                hargaArray[i] = jsonTest.getString("harga");
                                kadaluarsaArray[i] = jsonTest.getString("kadaluarsa");
                                latitudeArray[i] = jsonTest.getString("latitude");
                                longitudeArray[i] = jsonTest.getString("longitude");

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

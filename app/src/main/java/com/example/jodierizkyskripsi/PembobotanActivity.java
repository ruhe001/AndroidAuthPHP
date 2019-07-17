package com.example.jodierizkyskripsi;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class PembobotanActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final int KODE_RIKUES_PERMISION = 7171;
    private static final int KODE_GPLAY_RESOLUSI = 7172;

    private TextView txt_lokasi;
    EditText edt_harga, edt_jarak, edt_akses , edt_fasilitas, edt_edukasi;
    Button  btn_bobot_next;
    private boolean permintaanUpdateLokasi = false;

    private LocationRequest memintaLokasi;
    private GoogleApiClient mGoogleApiClient;
    private Location lokasiTerakhir;

    private static int interval_pembaruan = 5000; //SEC
    private static int interval_tercepat = 3000; //SEC
    private static int perpindahan = 10; //METERS

    String latd, longd;
    boolean connected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembobotan);

       LocationManager lokasimenejer = (LocationManager) getSystemService(LOCATION_SERVICE );

        if (lokasimenejer.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(this, "GPS aktif", Toast.LENGTH_SHORT).show();
        }else{
            notifikasiGPSbelumAKTIF();
        }

        ConnectivityManager cekKoneksi = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cekKoneksi.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                cekKoneksi.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        else {
            connected = false;
        }

        edt_harga = (EditText)findViewById(R.id.edit_harga);
        edt_jarak = (EditText)findViewById(R.id.edit_jarak);
        edt_akses = (EditText)findViewById(R.id.edit_akses);
        edt_fasilitas = (EditText)findViewById(R.id.edit_fasilitas);
        edt_edukasi = (EditText)findViewById(R.id.edit_edukasi);

        btn_bobot_next = (Button)findViewById(R.id.button_next_pembobotan);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //CEK PERMISSION
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, KODE_RIKUES_PERMISION);
        } else {
            if (cekLayananGplay()) {
                buatKlienGoogleAPI();
                buatPermintaanLokasi();
          }
        }
//        if (cekLayananGplay()) {
//                buatKlienGoogleAPI();
//                buatPermintaanLokasi();
//        }

        temukanLokasi();

        btn_bobot_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( TextUtils.isEmpty(edt_harga.getText())){

                    edt_harga.setError( "Tidak boleh kosong" );
                }
                else if( TextUtils.isEmpty(edt_jarak.getText())){

                    edt_jarak.setError( "Tidak boleh kosong" );
                }
                else if( TextUtils.isEmpty(edt_akses.getText())){

                    edt_akses.setError( "Tidak boleh kosong" );
                }
                else if( TextUtils.isEmpty(edt_fasilitas.getText())){

                    edt_fasilitas.setError( "Tidak boleh kosong" );
                }
                else if( TextUtils.isEmpty(edt_edukasi.getText())){

                    edt_edukasi.setError( "Tidak boleh kosong" );
                }
                else if(connected==false){

                    Toast.makeText(getApplicationContext(),"Tidak ada koneksi internet! Pastikan koneksi internet aktif dan lakukan restart aplikasi",Toast.LENGTH_SHORT).show();
                }
                else{
                    String text_edt_harga= edt_harga.getText().toString();
                    String text_edt_jarak = edt_jarak.getText().toString();
                    String text_edt_edukasi= edt_edukasi.getText().toString();
                    String text_edt_akses= edt_akses.getText().toString();
                    String text_edt_fasilitas= edt_fasilitas.getText().toString();

                    double harga1= Double.parseDouble(text_edt_harga);
                    double jarak1= Double.parseDouble(text_edt_jarak);
                    double akses1= Double.parseDouble(text_edt_akses);
                    double fasilitas1= Double.parseDouble(text_edt_fasilitas);
                    double edukasi1= Double.parseDouble(text_edt_edukasi);

                    double hitung_harga1=harga1/(harga1+jarak1+akses1+fasilitas1+edukasi1);
                    double hitung_jarak1=jarak1/(harga1+jarak1+akses1+fasilitas1+edukasi1);
                    double hitung_akses1=akses1/(harga1+jarak1+akses1+fasilitas1+edukasi1);
                    double hitung_fasilitas1=fasilitas1/(harga1+jarak1+akses1+fasilitas1+edukasi1);
                    double hitung_edukasi1=edukasi1/(harga1+jarak1+akses1+fasilitas1+edukasi1);

                    final String bobotHargaFinal = String.valueOf(hitung_harga1);
                    final String bobotJarakFinal = String.valueOf(hitung_jarak1);
                    final String bobotAksesFinal = String.valueOf(hitung_akses1);
                    final String bobotFasilitasFinal = String.valueOf(hitung_fasilitas1);
                    final String bobotEdukasiFinal = String.valueOf(hitung_edukasi1);
                    buatPermintaanLokasi();
                    temukanLokasi();

                    Intent intentToHasilTopsis = new Intent(view.getContext(), HasilTopsisActivity.class);


                    intentToHasilTopsis.putExtra("bobot_harga", bobotHargaFinal);
                    intentToHasilTopsis.putExtra("bobot_jarak", bobotJarakFinal);
                    intentToHasilTopsis.putExtra("bobot_akses", bobotAksesFinal);
                    intentToHasilTopsis.putExtra("bobot_fasilitas", bobotFasilitasFinal);
                    intentToHasilTopsis.putExtra("bobot_edukasi", bobotEdukasiFinal);
                    intentToHasilTopsis.putExtra("txt_latitude", latd);
                    intentToHasilTopsis.putExtra("txt_longitude", longd);
                    startActivity(intentToHasilTopsis);
                }


            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }
    @Override
    protected void onStop(){
        //LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this);
        //if(mGoogleApiClient != null)
         //   mGoogleApiClient.disconnect();
        super.onStop();
    }

    private void temukanLokasi() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        lokasiTerakhir = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (lokasiTerakhir != null) {

            double bujur = lokasiTerakhir.getLatitude();
            double lintang = lokasiTerakhir.getLongitude();
            //txt_lokasi.setText(latitude + " / " + longitude);
            latd = String.valueOf(bujur);
            longd = String.valueOf(lintang);
        } else {
            //Toast.makeText(getApplicationContext(),"Everything Seems Fine",Toast.LENGTH_LONG).show();// Set your own toast  message
        }
    }

    private void mulaiUpdateLokasi() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, memintaLokasi, this);
    }

    private void buatKlienGoogleAPI(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }
    private boolean cekLayananGplay(){
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS){
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)){
                GooglePlayServicesUtil.getErrorDialog(resultCode,this, KODE_GPLAY_RESOLUSI).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Perangkat tidak didukung", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }
    private void buatPermintaanLokasi(){
        memintaLokasi = new LocationRequest();
        memintaLokasi.setInterval(interval_pembaruan);
        memintaLokasi.setFastestInterval(interval_tercepat);
        memintaLokasi.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        memintaLokasi.setSmallestDisplacement(perpindahan);
    }


    @Override
    public void onLocationChanged(Location location) {
        lokasiTerakhir = location;
        temukanLokasi();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        temukanLokasi();
        if(permintaanUpdateLokasi)
            mulaiUpdateLokasi();
    }


    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void notifikasiGPSbelumAKTIF(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS sedang tidak aktif. Aktifkan GPS?")
                .setCancelable(false)
                .setPositiveButton("Lihat di Settings untuk mengaktifkan GPS",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

}


package com.example.androidauthphp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private static final int MY_PERMISSION_REQUEST_CODE = 7171;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 7172;
    private TextView txt_lokasi;
    EditText edt_pasar, edt_pendapatan, edt_infrastruktur, edt_transportasi;
    Button  btn_bobot_next;
    private boolean mRequestingLocationUpdates = false;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;

    private static int UPDATE_INTERVAL = 5000; //SEC
    private static int FASTEST_INTERVAL = 3000; //SEC
    private static int DISPLACEMENT = 10; //METERS




    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (checkPlayServices())
                        buildGoogleApiClient();
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembobotan);

        edt_pasar = (EditText)findViewById(R.id.edit_pasar);
        edt_pendapatan = (EditText)findViewById(R.id.edit_pendapatan);
        edt_infrastruktur = (EditText)findViewById(R.id.edit_infrastruktur);
        edt_transportasi = (EditText)findViewById(R.id.edit_transportasi);

        txt_lokasi = (TextView)findViewById(R.id.text_lokasi_pembobotan) ;

        btn_bobot_next = (Button)findViewById(R.id.button_next_pembobotan);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //RUNTIME REQUEST PERMISSION
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, MY_PERMISSION_REQUEST_CODE);
        } else {
            if (checkPlayServices()) {
                buildGoogleApiClient();
                createLocationRequest();
            }
        }

        displayLocation();

        btn_bobot_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PembobotanActivity.this,HasilTopsisActivity.class));

                final String text_edt_pasar = edt_pasar.getText().toString();
                final String text_edt_pendapatan = edt_pendapatan.getText().toString();
                final String text_edt_infrastruktur = edt_infrastruktur.getText().toString();
                final String text_edt_transportasi = edt_transportasi.getText().toString();
                final String txt_hasil_lokasi = txt_lokasi.getText().toString();

                final int int_bobot_pasar = Integer.parseInt(text_edt_pasar);
                final int int_bobot_pendapatan = Integer.parseInt(text_edt_pendapatan);
                final int int_bobot_infrastruktur = Integer.parseInt(text_edt_infrastruktur);
                final int int_bobot_transportasi = Integer.parseInt(text_edt_transportasi);

                Intent intentToHasilTopsis = new Intent(view.getContext(), HasilTopsisActivity.class);

                intentToHasilTopsis.putExtra("bobot_pasar", int_bobot_pasar);
                intentToHasilTopsis.putExtra("bobot_pendapatan", int_bobot_pendapatan);
                intentToHasilTopsis.putExtra("bobot_infrastruktur", int_bobot_infrastruktur);
                intentToHasilTopsis.putExtra("bobot_transportasi", int_bobot_transportasi);
                intentToHasilTopsis.putExtra("txt_lokasi", txt_hasil_lokasi);
                startActivity(intentToHasilTopsis);

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
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this);
        if(mGoogleApiClient != null)
            mGoogleApiClient.disconnect();
        super.onStop();
    }

    private void displayLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {

            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();
            txt_lokasi.setText(latitude + " / " + longitude);
        } else
            txt_lokasi.setText("Tidak dapat menemukan lokasi. Pastikan pencarian lokasi diaktifkan pada perangkat");
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }
    private void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }
    private void buildGoogleApiClient(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }
    private boolean checkPlayServices(){
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS){
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)){
                GooglePlayServicesUtil.getErrorDialog(resultCode,this,PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Perangkat tidak didukung", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }
    private void createLocationRequest(){
        mLocationRequest= new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
    }


    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        displayLocation();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        displayLocation();
        if(mRequestingLocationUpdates)
            startLocationUpdates();
    }


    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}

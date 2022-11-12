package com.example.domesticnews;

import static android.content.Context.LOCATION_SERVICE;

import static com.example.domesticnews.R.id.locationTextByManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class TempActivity extends AppCompatActivity {

    TextView locationText;
    TextView locationTextByManager;


    protected LocationManager locationManager;
    protected LocationListener locationListener;

    protected Context context;

    private FusedLocationProviderClient flpclient;

    protected boolean gps_enabled, network_enabled;



    public static final int MY_PERMISSIONS_REQUEST_LOCATION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        flpclient = LocationServices.getFusedLocationProviderClient(this);

        locationText = (TextView) findViewById(R.id.locationText);
        locationTextByManager = (TextView) findViewById(R.id.locationTextByManager);





    }

    public Location getLastLocation(){


        Location location = null;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(TempActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            if (locationManager == null) {
                return null;
            }
            List<String> providers = locationManager.getProviders(true);
            for (String provider : providers) {
                Location l = locationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (location == null || l.getAccuracy() < location.getAccuracy()) {
                    // Found best last known location: %s", l);
                    location = l;
                }
            }


        }
        return location;


    }




    public void getLastLocation(View view) {

        if (ContextCompat.checkSelfPermission(TempActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(TempActivity.this, "Get city", Toast.LENGTH_SHORT).show();
            flpclient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                Geocoder geocoder = new Geocoder(TempActivity.this, Locale.getDefault());
                                List<Address> addresses = null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),
                                            1);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                locationText.setText(addresses.get(0).getLocality() + addresses.get(0).getAddressLine(0));

                            }
                        }
                    });

        } else {
            requestLocationPermission();
        }
    }



    public void LocationMethod(View view) {

        if (ContextCompat.checkSelfPermission(TempActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(TempActivity.this, "You have already granted this permission", Toast.LENGTH_SHORT).show();

        } else {
            requestLocationPermission();
        }

    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {

            new AlertDialog.Builder(this).setTitle("Permission needed")
                    .setMessage("Need Permission")
                    .setPositiveButton(R.string.ok, (dialogInterface, i) -> ActivityCompat.requestPermissions(TempActivity.this, new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    }, MY_PERMISSIONS_REQUEST_LOCATION_CODE))
                    .setNegativeButton("cancel", (dialogInterface, i) -> dialogInterface.dismiss())

                    .create()
                    .show();

        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, MY_PERMISSIONS_REQUEST_LOCATION_CODE);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
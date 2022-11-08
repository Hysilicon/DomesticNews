package com.example.domesticnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TempActivity extends AppCompatActivity {

    private TextView locationText;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

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
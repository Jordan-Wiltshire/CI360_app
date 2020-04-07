package com.example.ci360;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.api.LogDescriptor;
import com.google.firebase.firestore.GeoPoint;

public class Map_Activity extends AppCompatActivity implements OnMapReadyCallback {
    private int LOCATION_PERMISSION_REQUEST_CODE;
    private GoogleMap mMap;
    private LatLng LatLng;
    private FusedLocationProviderClient mFusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            // Permission to access the location is missing. Show rationale and request permission
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        }

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                                    .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        getUserLocation();
    }

    private void getUserLocation() {
        mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Log.d("user location", "onComplete: did run but did the rest?");
                if (task.isSuccessful()) {
                    Location location = task.getResult();
                    GeoPoint geoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
                    Log.d("location", "onComplete: latitude" + geoPoint.getLatitude());
                    Log.d("location", "onComplete: longitude" + geoPoint.getLongitude());
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map){
        map.addMarker(new MarkerOptions()
            .position(new LatLng(0, 0))
            .title("Marker"));
    }


}

package com.example.dreamtravelv1;

import androidx.fragment.app.FragmentActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.location.Address;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;



import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.dreamtravelv1.databinding.ActivityMapsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.location.Geocoder;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private String locationName = "colombo";
    private double latitude;
    private double longitude;


    private List<Address> listGeoCoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        try {
            listGeoCoder = new Geocoder(this).getFromLocationName(locationName, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (listGeoCoder != null && !listGeoCoder.isEmpty()) {
            latitude = listGeoCoder.get(0).getLatitude();
            longitude = listGeoCoder.get(0).getLongitude();
            String countryName = listGeoCoder.get(0).getCountryName();
            Log.i("google_map_tag", "Address has longitude " + longitude + ", latitude " + latitude + ", country name " + countryName);
        }

        // Set up the bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.location);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.location:
                        startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),search.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.compass:
                        startActivity(new Intent(getApplicationContext(),compass.class));
                        overridePendingTransition(0,0);
                        return  true;
                    default:
                        return false;
                }
            }
        });







    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker and move the camera to the location stored as member variables
        LatLng location = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(location).title(locationName));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
    }

    public void onClickhandler(View view) {
        EditText editText = findViewById(R.id.text_input_edit_text);
        String newLocationName = editText.getText().toString();

        try {
            listGeoCoder = new Geocoder(this).getFromLocationName(newLocationName, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (listGeoCoder != null && !listGeoCoder.isEmpty()) {
            latitude = listGeoCoder.get(0).getLatitude();
            longitude = listGeoCoder.get(0).getLongitude();
            locationName = newLocationName;

            LatLng newLocation = new LatLng(latitude, longitude);
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(newLocation).title(locationName));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 12));
        }
    }



}
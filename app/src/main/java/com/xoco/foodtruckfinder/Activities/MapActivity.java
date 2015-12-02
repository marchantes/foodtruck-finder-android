package com.xoco.foodtruckfinder.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.xoco.foodtruckfinder.Models.Foodtruck;
import com.xoco.foodtruckfinder.R;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private ArrayList<Foodtruck> mFoodtrucks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMyLocationEnabled(true);

        LatLng mexico  = new LatLng(19.434372, -99.1397591);
        LatLng mexico2 = new LatLng(19.4326018, -99.135399);
        LatLng mexico3 = new LatLng(19.435353, -99.1379671);
        LatLng mexico4 = new LatLng(19.438489, -99.1349741);

        mMap.addMarker(new MarkerOptions()
                .position(mexico)
                .title("Food Truck 1")
                .snippet("Mexican")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fast_food_24)));

        mMap.addMarker(new MarkerOptions()
                .position(mexico2)
                .title("Food Truck 2")
                .snippet("Mexican")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fast_food_24)));

        mMap.addMarker(new MarkerOptions()
                .position(mexico3)
                .title("Food Truck 3")
                .snippet("Chinese")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fast_food_24)));

        mMap.addMarker(new MarkerOptions()
                .position(mexico4)
                .title("Food Truck 4")
                .snippet("Italian")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fast_food_24)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mexico, 15));
    }

}

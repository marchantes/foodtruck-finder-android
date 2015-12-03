package com.xoco.foodtruckfinder.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.xoco.foodtruckfinder.R;
import com.xoco.foodtruckfinder.models.FoodTruck;
import com.xoco.foodtruckfinder.restful.ApiClient;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private ArrayList<FoodTruck> mFoodTrucks;

    private MapActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //Get food trucks list
        ApiClient.getService().getAllFoodTrucks(new Callback<ArrayList<FoodTruck>>() {
            @Override
            public void success(ArrayList<FoodTruck> foodTrucks, Response response) {
                Log.d("Custom:MapActivity", "Request was successful");
                mFoodTrucks = foodTrucks;

                //Get map support and call in async way
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                mapFragment.getMapAsync(self);

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Custom:MapActivity", "Retrofit Error");
                Log.d("Custom:MapActivity", error.toString());
            }
        });

    }

    public void onMapReady(GoogleMap googleMap) {


        //Get map and sets location service
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);

        //TODO Hard coded map center, it should be changed to User's position
        LatLng myCenter  = new LatLng(19.434372, -99.1397591);

        //Add food truck to map
        for (FoodTruck foodTruck : mFoodTrucks) {

            Log.d("Custom:MapActivity:", foodTruck.name);
            Log.d("Custom:MapActivity", foodTruck.location.toString());

            mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(foodTruck.location.lat, foodTruck.location.lng))
                            .title(foodTruck.name)
                            .snippet(foodTruck.foodType)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.fast_food_24))
            );

        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myCenter, 15));

    }

}

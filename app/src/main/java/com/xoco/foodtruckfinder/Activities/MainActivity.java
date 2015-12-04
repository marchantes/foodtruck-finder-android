package com.xoco.foodtruckfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.xoco.foodtruckfinder.R;
import com.xoco.foodtruckfinder.models.FoodTruck;
import com.xoco.foodtruckfinder.restful.ApiClient;
import com.xoco.foodtruckfinder.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, NavigationView.OnNavigationItemSelectedListener {


    //Saves food trucks from server
    private ArrayList<FoodTruck> mFoodTrucks = new ArrayList<FoodTruck>();

    //Maps Food trucks with Map Pins
    private HashMap<String, FoodTruck> mHashMap = new HashMap<String, FoodTruck>();

    //To save current object in case this word is used inside nested functions or callbacks
    private MainActivity self = this;

    private DrawerLayout drawerLayout;

    private View content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get food trucks list
        ApiClient.getService().getAllFoodTrucks(new Callback<ArrayList<FoodTruck>>() {
            @Override
            public void success(ArrayList<FoodTruck> foodTrucks, Response response) {
                Log.d("Custom:MainActivity", "Request was successful");
                mFoodTrucks = foodTrucks;

                //Get map support and call in async way
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                mapFragment.getMapAsync(self);

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Custom:MainActivity", "Retrofit Error");
                Log.d("Custom:MainActivity", error.toString());
            }
        });


        //Navigation Drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        content = findViewById(R.id.content);

        NavigationView view = (NavigationView) findViewById(R.id.navigation_view);
        view.setNavigationItemSelectedListener(self);

    }

    public void onMapReady(GoogleMap googleMap) {


        String currentMarkerId;

        //Get map and sets location service
        googleMap.setMyLocationEnabled(true);

        //Set on information windows listener
        googleMap.setOnInfoWindowClickListener(this);

        //TODO Hard coded map center, it should be changed to User's position
        LatLng myCenter  = new LatLng(19.434372, -99.1397591);

        //Add food truck to map and saves in map table
        for (FoodTruck foodTruck : mFoodTrucks) {

            Log.d("Custom:MainActivity", foodTruck.name);
            Log.d("Custom:MainActivity", foodTruck.location.toString());

            currentMarkerId = googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(foodTruck.location.lat, foodTruck.location.lng))
                            .title(foodTruck.name)
                            .snippet(foodTruck.foodType)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.fast_food_24))
            ).getId();

            Log.d("Custom:MainActivity",currentMarkerId);
            Log.d("Custom:MainActivity",foodTruck.name);

            mHashMap.put(currentMarkerId, foodTruck);

        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myCenter, 15));

    }

    public void onInfoWindowClick(Marker marker){

        FoodTruck selectedFoodTruck = mHashMap.get(marker.getId());

        Intent toDetailsIntent = new Intent(this, FoodtruckDetailsActivity.class);
        Bundle foodTruckInfo = new Bundle();

        foodTruckInfo.putInt(Constants.ID, selectedFoodTruck.id);
        foodTruckInfo.putString(Constants.NAME, selectedFoodTruck.name);
        foodTruckInfo.putString(Constants.TYPE, selectedFoodTruck.foodType);
        foodTruckInfo.putFloat(Constants.RATING, selectedFoodTruck.rating);

        toDetailsIntent.putExtra(Constants.FOOD_TRUCK_INFO, foodTruckInfo);

        startActivity(toDetailsIntent);

    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Snackbar.make(content, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
        menuItem.setChecked(true);
        drawerLayout.closeDrawers();
        return true;
    }

}

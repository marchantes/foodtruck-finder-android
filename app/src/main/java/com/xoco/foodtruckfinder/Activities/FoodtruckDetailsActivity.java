package com.xoco.foodtruckfinder.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.TextView;

import com.xoco.foodtruckfinder.R;
import com.xoco.foodtruckfinder.models.FoodTruck;

import de.greenrobot.event.EventBus;

public class FoodtruckDetailsActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvType;
    private RatingBar rbRating;

    private FoodTruck foodTruck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_foodtruck_details);


        tvName = (TextView)findViewById(R.id.foodtruck_details_tv_name);
        tvType = (TextView)findViewById(R.id.foodtruck_details_tv_type);
        rbRating = (RatingBar) findViewById(R.id.foodtruck_details_rb);


    }

    @Override
    protected void onStart() {
        super.onStart();
        //Subscribe to EventBus, registerSticky uses the Android main ghread (UI thread) to call the event handling method,
        //i.e. onEventMainThread method. UI changes must be done in the UI thread.
        EventBus.getDefault().registerSticky(this);

    }


    @Override
    protected void onStop() {
        //Unsuscribe from EventBus
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    //This codes runs when a food truck is received. UI changes must be done in the UI thread
    public void onEventMainThread(FoodTruck foodTruckEvent) {
        foodTruck = foodTruckEvent;

        tvName.setText(foodTruck.name);
        tvType.setText(foodTruck.foodType);
        rbRating.setRating(foodTruck.rating);

        //TODO Implement server request here
    }

}

package com.xoco.foodtruckfinder.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.xoco.foodtruckfinder.R;
import com.xoco.foodtruckfinder.utils.Constants;

public class FoodtruckDetailsActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvType;
    private RatingBar rbRating;
    private Bundle mapActivityBundle;

    private Integer foodTruckID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodtruck_details);

        mapActivityBundle = getIntent().getBundleExtra(Constants.FOOD_TRUCK_INFO);

        tvName = (TextView)findViewById(R.id.foodtruck_details_tv_name);
        tvType = (TextView)findViewById(R.id.foodtruck_details_tv_type);
        rbRating = (RatingBar) findViewById(R.id.foodtruck_details_rb);

        tvName.setText(mapActivityBundle.getString(Constants.NAME));
        tvType.setText(mapActivityBundle.getString(Constants.TYPE));
        rbRating.setRating(mapActivityBundle.getFloat(Constants.RATING));

        //Get ID to make future requests
        foodTruckID = mapActivityBundle.getInt(Constants.ID);

    }
}

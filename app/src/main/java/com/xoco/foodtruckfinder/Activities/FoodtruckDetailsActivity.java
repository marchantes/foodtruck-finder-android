package com.xoco.foodtruckfinder.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.xoco.foodtruckfinder.R;
import com.xoco.foodtruckfinder.adapters.CommentAdapter;
import com.xoco.foodtruckfinder.fragments.CommentDialogFragment;
import com.xoco.foodtruckfinder.models.Comment;
import com.xoco.foodtruckfinder.models.FoodTruck;
import com.xoco.foodtruckfinder.restful.ApiClient;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FoodtruckDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    //To handlet this Activity
    private FoodtruckDetailsActivity self = this;
    private FoodTruck currentFoodTruck;

    private TextView tvName;
    private TextView tvType;
    private RatingBar rbRating;
    private Button postCommentBtn;
    private ImageView ivCover;

    //To display comments
    private RecyclerView recyclerView;


    //Floating button
    private FloatingActionButton commentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_foodtruck_details);

        //TODO Put header image
//        ivCover = (ImageView) findViewById(R.id.foodtruck_details_image);

        tvName = (TextView)findViewById(R.id.foodtruck_details_name_tv);
        tvType = (TextView)findViewById(R.id.foodtruck_details_type_tv);
        rbRating = (RatingBar) findViewById(R.id.foodtruck_details_rating);

        commentBtn = (FloatingActionButton) findViewById(R.id.comment_fab);
        commentBtn.setOnClickListener(this);

        setUpRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Subscribe to EventBus, registerSticky uses the Android main thread (UI thread) to call the event handling method,
        //i.e. onEventMainThread method. UI changes must be done in the UI thread.
        EventBus.getDefault().registerSticky(this);

    }


    @Override
    protected void onStop() {
        //Unregister from EventBus
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    //This codes runs when a food truck is received. UI changes must be done in the UI thread
    public void onEventMainThread(FoodTruck foodTruck) {

        self.currentFoodTruck = foodTruck;

        //Just to check which food truck was selected
        Log.d("DetailActivity", "Food truck ID: "+ String.valueOf(foodTruck.id));

        //Set header
        tvName.setText(foodTruck.name);
        tvType.setText(foodTruck.foodType);
        rbRating.setRating(foodTruck.rating);

        //Get info from server
        getComments(foodTruck.id);

    }

    private void setUpRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.foodtruck_details_comments);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


    }

    private void getComments(int foodTruckId){

        //Get comments from server
        ApiClient.getService().getComments(foodTruckId, new Callback<ArrayList<Comment>>() {
            @Override
            public void success(ArrayList<Comment> comments, Response response) {
                CommentAdapter commentAdapter = new CommentAdapter(self, comments);
                recyclerView.setAdapter(commentAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("DetailsActivity", "Retrofit Error");
                Log.d("DetailsActivity", error.toString());
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.comment_fab:
                CommentDialogFragment commentDialogFragment = new CommentDialogFragment();
                commentDialogFragment.show(getSupportFragmentManager(), "Post Comment");
                Log.d("DetailsActivity", "Inside post button");
                break;
        }

    }

    public FoodTruck getCurrentFoodTruck() {
        return currentFoodTruck;
    }


}

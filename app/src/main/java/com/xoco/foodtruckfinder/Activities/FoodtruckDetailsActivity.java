package com.xoco.foodtruckfinder.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.xoco.foodtruckfinder.R;
import com.xoco.foodtruckfinder.adapters.CommentAdapter;
import com.xoco.foodtruckfinder.models.Comment;
import com.xoco.foodtruckfinder.models.Foodtruck;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

public class FoodtruckDetailsActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvType;
    private RatingBar rbRating;

    //Holds current food truck passed from MainActivity by EventBus
    private Foodtruck foodTruck;

    //To display comments
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_foodtruck_details);

        //TODO Put header info
//        tvName = (TextView)findViewById(R.id.foodtruck_details_tv_name);
//        tvType = (TextView)findViewById(R.id.foodtruck_details_tv_type);
//        rbRating = (RatingBar) findViewById(R.id.foodtruck_details_rb);

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
    public void onEventMainThread(Foodtruck foodTruckEvent) {
        foodTruck = foodTruckEvent;

        tvName.setText(foodTruck.name);
        tvType.setText(foodTruck.foodType);
        rbRating.setRating(foodTruck.rating);

        //TODO Implement server request here
    }

    private void setUpRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.foodtruck_details_comments);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        CommentAdapter commentAdapter = new CommentAdapter(this, getComments());
        recyclerView.setAdapter(commentAdapter);

    }

    private ArrayList<Comment> getComments(){

        ArrayList<Comment> comments = new ArrayList<>();

        String comment1 = "Súper a gusto, la comida y frappes ricos y a muy buen precio";
        String comment2 = "Excelente ambientación; una terraza por encima de las copas de los árboles; lindo café para crepas y baguette. Excelente opción de verdad a un precio muy muy accesible";
        String comment3 = "Muy recomendable y economico, lo chistoso es la mezla y variedad de hipsters y mirreyes en un lugar ";
        String comment4 = "Pésimo servicio, pésimo el desayuno y además carísimo, el pan de dulce insípido y no hay variedad. Para mi gusto no lo recomiendo y no regresare.";
        String date = "2-Dec-2015";
        int likes = 10;
        float rating = 1;

        for(int i=0; i<15; i++) {
            comments.add(new Comment("Juan Perez", comment1, date, likes, rating, R.drawable.bill_gates));
            comments.add(new Comment("Emma Perez", comment2, date, likes, rating, R.drawable.emma_watson));
            comments.add(new Comment("Juan Perez", comment3, date, likes, rating, R.drawable.bill_gates));
            comments.add(new Comment("Emma Perez", comment4, date, likes, rating, R.drawable.emma_watson));
            likes++;
            rating += 0.1;
        }

        return comments;

    }


}

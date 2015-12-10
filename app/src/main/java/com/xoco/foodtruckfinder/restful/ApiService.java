package com.xoco.foodtruckfinder.restful;

import com.xoco.foodtruckfinder.models.Favorite;
import com.xoco.foodtruckfinder.models.Foodtruck;
import com.xoco.foodtruckfinder.utils.Constants;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by uliseschino on 12/2/15.
 */
public interface ApiService {

    @GET(Constants.API_ALL_FOODTRUCKS)
    void getAllFoodTrucks(Callback<ArrayList<Foodtruck>> response);

    @GET(Constants.API_USER_FAVORITES)
    void getUserFavorites(@Path("pk") int id, Callback<ArrayList<Favorite>> response);

    @GET(Constants.API_FOOD_TRUCK)
    void getFoodTruck(@Path("pk") int id, Callback<Foodtruck> response);

//    @GET(Constants.API_USER_FAVORITES)
//    FoodTruck getFoodTruck(@Path("pk") int id);

}

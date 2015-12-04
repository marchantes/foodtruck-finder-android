package com.xoco.foodtruckfinder.restful;

import com.xoco.foodtruckfinder.models.FoodTruck;
import com.xoco.foodtruckfinder.utils.Constants;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by uliseschino on 12/2/15.
 */
public interface ApiService {

    @GET(Constants.API_ALL_FOODTRUCKS)
    void getAllFoodTrucks(Callback<ArrayList<FoodTruck>> response);

}

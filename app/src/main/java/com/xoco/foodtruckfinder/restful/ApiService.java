package com.xoco.foodtruckfinder.restful;

import com.xoco.foodtruckfinder.models.Comment;
import com.xoco.foodtruckfinder.models.Favorite;
import com.xoco.foodtruckfinder.models.FoodTruck;
import com.xoco.foodtruckfinder.utils.Constants;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by uliseschino on 12/2/15.
 */
public interface ApiService {

    @GET(Constants.API_ALL_FOODTRUCKS)
    void getAllFoodTrucks(Callback<ArrayList<FoodTruck>> response);

    @GET(Constants.API_USER_FAVORITES)
    void getUserFavorites(@Path("pk") int id, Callback<ArrayList<Favorite>> response);

    @GET(Constants.API_FOOD_TRUCK_COMMENTS)
    void getComments(@Path("pk") int id, Callback<ArrayList<Comment>> response);

    @FormUrlEncoded
    @POST(Constants.API_FOOD_TRUCK_COMMENTS)
    void postComment(@Path("pk") int id, @Field("comment")String comment, @Header("Authorization") String authToken, Callback<Comment> response);


}

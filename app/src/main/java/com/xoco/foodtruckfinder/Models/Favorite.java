package com.xoco.foodtruckfinder.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by uliseschino on 12/9/15.
 */
public class Favorite {

    //Only food truck id is needed for now
    @SerializedName("foodtruck")
    public int foodTruckId;
}

package com.xoco.foodtruckfinder.models;
import com.google.gson.annotations.SerializedName;

/**
 * Created by uliseschino on 12/8/15.
 *
 */
public class Comment {

    public int id;

    @SerializedName("user")
    public String userName;

    @SerializedName("foodtruck")
    public int foodTruckId;

    public String comment;

    @SerializedName("date_added")
    public String date;

    public int likes;

    public Comment(String userName, String comment, String date, int likes, float rating, int userImage) {
        this.userName = userName;
        this.comment = comment;
        this.date = date;
        this.likes = likes;
        this.rating = rating;
        this.userImage = userImage;
    }

    //TODO not in API yet
    public int userImage;

    public float rating;

}

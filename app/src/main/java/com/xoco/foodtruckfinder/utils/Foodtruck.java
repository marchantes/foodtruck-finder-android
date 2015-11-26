package com.xoco.foodtruckfinder.utils;

/**
 * Created by chino on 11/26/15.
 */
public class Foodtruck {
    private String mName;
    private String mFoodType;
    private Integer mRating;
    private Double mLat;
    private Double mLng;

    public Foodtruck(String mName, String mFoodType, Double mLat, Integer mRating, Double mLng) {
        this.mName = mName;
        this.mFoodType = mFoodType;
        this.mLat = mLat;
        this.mRating = mRating;
        this.mLng = mLng;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmFoodType() {
        return mFoodType;
    }

    public void setmFoodType(String mFoodType) {
        this.mFoodType = mFoodType;
    }

    public Integer getmRating() {
        return mRating;
    }

    public void setmRating(Integer mRating) {
        this.mRating = mRating;
    }

    public Double getmLat() {
        return mLat;
    }

    public void setmLat(Double mLat) {
        this.mLat = mLat;
    }

    public Double getmLng() {
        return mLng;
    }

    public void setmLng(Double mLng) {
        this.mLng = mLng;
    }
}

package com.xoco.foodtruckfinder.restful;

import com.xoco.foodtruckfinder.utils.Constants;

import retrofit.RestAdapter;

/**
 * Created by uliseschino on 12/2/15.
 */
public class ApiClient {

    private static final String BASE_URL = Constants.API_BASE_URL;

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();

    private static final ApiService API_SERVICE = REST_ADAPTER.create(ApiService.class);

    public static ApiService getService() {
        return API_SERVICE;
    }

}

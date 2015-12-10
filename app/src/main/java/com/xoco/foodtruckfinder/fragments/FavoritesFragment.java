package com.xoco.foodtruckfinder.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xoco.foodtruckfinder.R;
import com.xoco.foodtruckfinder.adapters.FoodTruckAdapter;
//import com.xoco.foodtruckfinder.Models.Favorite;
//import com.xoco.foodtruckfinder.Models.Foodtruck;
import com.xoco.foodtruckfinder.models.Favorite;
import com.xoco.foodtruckfinder.models.Foodtruck;
import com.xoco.foodtruckfinder.restful.ApiClient;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class FavoritesFragment extends android.support.v4.app.Fragment {


    //To display comments
    private RecyclerView recyclerView;

    //Holds Favorite
    private final ArrayList<com.xoco.foodtruckfinder.models.Foodtruck> foodTruckFavorites = new ArrayList<com.xoco.foodtruckfinder.models.Foodtruck>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initRecyclerView() {

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.favorites_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        FoodTruckAdapter foodTruckAdapter = new FoodTruckAdapter(getActivity(), getFavorites());
        recyclerView.setAdapter(foodTruckAdapter);

    }

    private ArrayList<com.xoco.foodtruckfinder.models.Foodtruck> getFavorites(){




        ArrayList<Foodtruck> favorites = new ArrayList<>();

        for(int i=0; i<12; i++){

            favorites.add(new Foodtruck("Mex "+ String.valueOf(i), "Mexican", R.drawable.bus_24));
            favorites.add(new Foodtruck("Italian "+ String.valueOf(i), "Italian", R.drawable.bus_24));
            favorites.add(new Foodtruck("French "+ String.valueOf(i), "French", R.drawable.bus_24));

        }

        return favorites;




        /*

        ApiClient.getService().getUserFavorites(1, new Callback<ArrayList<Favorite>>() {

            @Override
            public void success(ArrayList<Favorite> favorites, Response response) {
                for (final Favorite favorite : favorites) {


                    //Sync
//                    foodTruckFavorites.add(ApiClient.getService().getFoodTruck(favorite.foodTruckId));

                    //Async
                    ApiClient.getService().getFoodTruck(favorite.foodTruckId, new Callback<Foodtruck>() {
                        @Override
                        public void success(Foodtruck foodTruck, Response response) {
                            foodTruckFavorites.add(foodTruck);
                        }
                        @Override
                        public void failure(RetrofitError error) {
                            Log.d("FavoritesFragment", "Retrofit Error Inside Second Loop");
                        }
                    });



                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("FavoritesFragment", "Retrofit Error");
                Log.d("FavoritesFragment", error.toString());
            }
        });

        return foodTruckFavorites;

        */
    }


}

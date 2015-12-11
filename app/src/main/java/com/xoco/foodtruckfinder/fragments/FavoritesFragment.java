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
import com.xoco.foodtruckfinder.models.FoodTruck;
import com.xoco.foodtruckfinder.restful.ApiClient;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class FavoritesFragment extends android.support.v4.app.Fragment {

    //To display comments
    private RecyclerView recyclerView;

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
//        getFavorites(1);
        getAllFoodTrucks();
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

    }

    void getAllFoodTrucks(){

        ApiClient.getService().getAllFoodTrucks( new Callback<ArrayList<FoodTruck>>() {

            @Override
            public void success(ArrayList<FoodTruck> foodTrucks, Response response) {

                FoodTruckAdapter foodTruckAdapter = new FoodTruckAdapter(getActivity(), foodTrucks);
                recyclerView.setAdapter(foodTruckAdapter);

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("FavoritesFragment", "Retrofit Error");
                Log.d("FavoritesFragment", error.toString());
            }
        });

    }


}

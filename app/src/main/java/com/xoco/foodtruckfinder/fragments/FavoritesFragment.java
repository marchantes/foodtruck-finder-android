package com.xoco.foodtruckfinder.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xoco.foodtruckfinder.R;
import com.xoco.foodtruckfinder.adapters.FoodTruckAdapter;
import com.xoco.foodtruckfinder.models.FoodTruck;

import java.util.ArrayList;


public class FavoritesFragment extends android.support.v4.app.Fragment {


    //To display comments
    private RecyclerView recyclerView;

    //Holds Favorites
    private ArrayList<FoodTruck> foodTrucks = new ArrayList<FoodTruck>();

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

    private ArrayList<FoodTruck> getFavorites(){
        ArrayList<FoodTruck> favorites = new ArrayList<>();

        for(int i=0; i<12; i++){

            favorites.add(new FoodTruck("Mex "+ String.valueOf(i), "Mexican", R.drawable.bus_24));
            favorites.add(new FoodTruck("Italian "+ String.valueOf(i), "Italian", R.drawable.bus_24));
            favorites.add(new FoodTruck("French "+ String.valueOf(i), "French", R.drawable.bus_24));

        }

        return favorites;
    }

}

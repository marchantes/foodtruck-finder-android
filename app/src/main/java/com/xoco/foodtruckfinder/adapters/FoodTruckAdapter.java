package com.xoco.foodtruckfinder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xoco.foodtruckfinder.R;
import com.xoco.foodtruckfinder.models.FoodTruck;

import java.util.ArrayList;

/**
 * Created by uliseschino on 12/8/15.
 */
public class FoodTruckAdapter extends RecyclerView.Adapter<FoodTruckAdapter.FoodTruckViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<FoodTruck> favorites;

    //Adapter constructor receives context and the list
    public FoodTruckAdapter(Context context, ArrayList<FoodTruck> foodTrucks) {
        layoutInflater = LayoutInflater.from(context);
        this.favorites = foodTrucks;

    }

    //Creates ViewHolder
    @Override
    public FoodTruckAdapter.FoodTruckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.list_item_foodtruck, parent, false);

        FoodTruckViewHolder viewHolder = new FoodTruckViewHolder(
                view,
                R.id.li_food_truck_name,
                R.id.li_food_truck_type,
                R.id.li_food_truck_image);

        return viewHolder;
    }

    public class FoodTruckViewHolder extends RecyclerView.ViewHolder  {


        private TextView mName;
        private TextView mType;
        private ImageView mImage;


        public FoodTruckViewHolder(View itemView, int name, int type, int image) {
            super(itemView);
            this.mName = (TextView) itemView.findViewById(name);
            this.mType = (TextView) itemView.findViewById(type);
            this.mImage = (ImageView) itemView.findViewById(image);
        }

        public void setName(String name) {
            this.mName.setText(name);
        }

        public void setType(String type) {
            this.mType.setText(type);
        }

        public void setImage(int image) {
            this.mImage.setImageResource(image);
        }

    }


    @Override
    public void onBindViewHolder(FoodTruckAdapter.FoodTruckViewHolder holder, int position) {

        FoodTruck foodTruck = favorites.get(position);

        holder.setName(foodTruck.name);
        holder.setType(foodTruck.foodType);
        holder.setImage(foodTruck.image);

    }

    @Override
    public int getItemCount() {
        return favorites.size();

    }



}

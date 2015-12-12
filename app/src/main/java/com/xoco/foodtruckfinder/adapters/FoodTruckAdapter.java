package com.xoco.foodtruckfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xoco.foodtruckfinder.R;
import com.xoco.foodtruckfinder.activities.FoodtruckDetailsActivity;
import com.xoco.foodtruckfinder.models.FoodTruck;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by uliseschino on 12/8/15.
 */
public class FoodTruckAdapter extends RecyclerView.Adapter<FoodTruckAdapter.FoodTruckViewHolder> {


    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<FoodTruck> favorites;

    //Adapter constructor receives context and the list
    public FoodTruckAdapter(Context context, ArrayList<FoodTruck> foodTrucks) {
        layoutInflater = LayoutInflater.from(context);
        this.favorites = foodTrucks;
        this.context = context;

    }

    //Creates ViewHolder
    @Override
    public FoodTruckAdapter.FoodTruckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.list_item_foodtruck, parent, false);

        FoodTruckViewHolder viewHolder = new FoodTruckViewHolder(
                view,
                R.id.li_food_truck_name,
                R.id.li_food_truck_type
//                ,R.id.li_food_truck_image
        );

        return viewHolder;
    }


    //The view holder implements on Click Listener
    public class FoodTruckViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mName;
        private TextView mType;
//        private ImageView mImage;
        private FoodTruck mFoodTruck;

        public FoodTruckViewHolder(View itemView, int name, int type
//                , int image
        ) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.mName = (TextView) itemView.findViewById(name);
            this.mType = (TextView) itemView.findViewById(type);
//            this.mImage = (ImageView) itemView.findViewById(image);
        }


        public void setFodTruck(FoodTruck foodTruck) { this.mFoodTruck = foodTruck; }

        public void setName(String name) {
            this.mName.setText(name);
        }

        public void setType(String type) {
            this.mType.setText(type);
        }

//        public void setImage(int image) {
//           this.mImage.setImageResource(image);
//        }

        @Override
        public void onClick(View view){

            //Post food truck object to EventBus
            EventBus.getDefault().postSticky(mFoodTruck);

            Log.d("InsideViewHolder", String.valueOf(mFoodTruck.id));
//            //Go to details
            Intent toDetailsIntent = new Intent(context, FoodtruckDetailsActivity.class);
            context.startActivity(toDetailsIntent);
        }

    }


    @Override
    public void onBindViewHolder(FoodTruckAdapter.FoodTruckViewHolder holder, int position) {

        FoodTruck foodTruck = favorites.get(position);

        holder.setFodTruck(foodTruck);
        holder.setName(foodTruck.name);
        holder.setType(foodTruck.foodType);
//        holder.setImage(foodTruck.image);

    }

    @Override
    public int getItemCount() {
        return favorites.size();

    }



}

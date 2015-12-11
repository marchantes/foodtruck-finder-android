package com.xoco.foodtruckfinder.fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xoco.foodtruckfinder.R;
import com.xoco.foodtruckfinder.models.Comment;
import com.xoco.foodtruckfinder.models.FoodTruck;
import com.xoco.foodtruckfinder.restful.ApiClient;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class CommentDialogFragment extends DialogFragment {

    private View content;
    private EditText editText;
    private FoodTruck currentFoodTruck;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Context context = getContext();

        LayoutInflater inflater = getActivity().getLayoutInflater();
        content = inflater.inflate(R.layout.fragment_comment_dialog, null);
        editText = (EditText) content.findViewById(R.id.fragment_comment_dialog_comment);



        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(content)
                .setMessage("Post a comment")
                .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        ApiClient.getService().postComment(currentFoodTruck.id, editText.getText().toString(), "Basic c29tZUBtYWlsLmNvbToxMjM0", new Callback<Comment>() {
                            @Override
                            public void success(Comment comment, Response response) {
                                Toast.makeText(context , "Comment posted successfully", Toast.LENGTH_SHORT).show();
                                Log.d("DialogComment", "Success!");
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(context, "There was an error during posting, please try later", Toast.LENGTH_SHORT).show();
                                Log.d("DialogComment", "Retrofit Error");
                                Log.d("DialogComment", error.toString());
                            }
                        });
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onStart(){
        super.onStart();
        EventBus.getDefault().registerSticky(this);
    }

    @Override
    public void onStop(){
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    //This codes runs when a food truck is received. UI changes must be done in the UI thread
    public void onEventMainThread(FoodTruck foodTruck) {

        currentFoodTruck = foodTruck;

        //Just to check which food truck was selected
        Log.d("CommentFragment", "Food truck ID: "+ String.valueOf(foodTruck.id));

    }


}

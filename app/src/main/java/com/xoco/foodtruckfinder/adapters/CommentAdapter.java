package com.xoco.foodtruckfinder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.xoco.foodtruckfinder.R;
import com.xoco.foodtruckfinder.models.Comment;

import java.util.ArrayList;

/**
 * Created by uliseschino on 12/8/15.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<Comment> foodTruckComments;

    //Adapter constructor receives context and the list
    public CommentAdapter(Context context, ArrayList<Comment> comments) {
        layoutInflater = LayoutInflater.from(context);
        this.foodTruckComments = comments;

    }

    //Creates ViewHolder
    @Override
    public CommentAdapter.CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.list_item_comment, parent, false);

        CommentViewHolder viewHolder = new CommentViewHolder(
                view,
                R.id.li_comment_username_tv,
                R.id.li_comment_date_tv,
                R.id.li_comment_comment_tv,
                R.id.li_comment_image_iv,
                R.id.li_comment_likes_tv,
                R.id.li_comment_rb );

        return viewHolder;
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder  {


        private TextView mUserName;
        private TextView mDate;
        private TextView mComment;
        private ImageView mUserImage;
        private TextView mLikes;
        private RatingBar mRating;

        public CommentViewHolder(View itemView, int userName, int date, int comment, int userImage, int likes, int rating) {
            super(itemView);
            this.mUserName = (TextView) itemView.findViewById(userName);
            this.mDate = (TextView) itemView.findViewById(date);
            this.mComment = (TextView) itemView.findViewById(comment);
            this.mUserImage = (ImageView) itemView.findViewById(userImage);
            this.mLikes = (TextView) itemView.findViewById(likes);
            this.mRating = (RatingBar) itemView.findViewById(rating);
        }

        public void setUserName(String userName) {
            this.mUserName.setText(userName);
        }

        public void setDate(String date) {
            this.mDate.setText(date);
        }

        public void setComment(String comment) {
            this.mComment.setText(comment);
        }

        public void setUserImage(int userImage) {
            this.mUserImage.setImageResource(userImage);
        }

        public void setLikes(int likes) {
            this.mLikes.setText(String.valueOf(likes));
        }

        public void setRating(float rating) {
            this.mRating.setRating(rating);
        }
    }


    @Override
    public void onBindViewHolder(CommentAdapter.CommentViewHolder holder, int position) {


        Comment truckComment = foodTruckComments.get(position);

        holder.setComment(truckComment.comment);
        holder.setDate(truckComment.date);
        holder.setLikes(truckComment.likes);
        holder.setUserName(truckComment.userName);
        holder.setUserImage(truckComment.userImage);
        holder.setRating(truckComment.rating);

    }

    @Override
    public int getItemCount() {
        return foodTruckComments.size();

    }



}

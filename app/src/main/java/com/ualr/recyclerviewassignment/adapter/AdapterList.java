package com.ualr.recyclerviewassignment.adapter;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ualr.recyclerviewassignment.R;
import com.ualr.recyclerviewassignment.Utils.DataGenerator;
import com.ualr.recyclerviewassignment.model.Inbox;

import java.util.List;


// TODO 05. Create a new Adapter class and the corresponding ViewHolder class in a different file. The adapter will be used to populate
//  the recyclerView and manage the interaction with the items in the list


public class AdapterList extends RecyclerView.Adapter {

    private List<Inbox> mItems;
    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(View view, Inbox obj, int position);
    }

    public AdapterList(List<Inbox> items) {
        this.mItems = items;
    }

    public void removeItem(int position){
        if (position >= mItems.size()){
            return;
        }
        mItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }


    public void setOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.mListener = itemClickListener;
    }

    public void addItem(int position, Inbox item){

        mItems.add(position, item);
        notifyItemInserted(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.items_inbox, parent, false);
        RecyclerView.ViewHolder vh = new ItemViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        Inbox i = mItems.get(index);

        itemViewHolder.email.setText(i.getEmail());
        itemViewHolder.date.setText(i.getDate());
        itemViewHolder.name.setText(i.getFrom());

        itemViewHolder.imgLetter.setText(Character.toString(i.getFrom().charAt(0)));;

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView imgLetter,email, name, date;
        public RelativeLayout layoutImage;
        public View layoutParent;
        public ImageView ImageV;




        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutImage = itemView.findViewById(R.id.layoutImage);

            layoutParent = itemView.findViewById(R.id.layoutParent);

            imgLetter = itemView.findViewById(R.id.imgLetter);

            name = itemView.findViewById(R.id.name);

            date = itemView.findViewById(R.id.date);

            email = itemView.findViewById(R.id.email);

            ImageV = itemView.findViewById(R.id.image_V);

            layoutParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mListener.onItemClick(v, mItems.get(getLayoutPosition()), getLayoutPosition());

                }
            });
            layoutImage.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View v) {
                    ImageV.setImageResource(R.drawable.ic_delete_24px);
                    imgLetter.setText("");

                }
            });
            ImageV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(getLayoutPosition());
                    ImageV.setImageResource(R.drawable.shape_circle);

                }
            });
        }
    }

}
package com.example.roomdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> users;

    public RecyclerViewAdapter(ArrayList<String> tUsers) {
        // get ArrayList from MainActivity class
        this.users = tUsers;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // bind recycle_view_item.xml with View object
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // set text to specific postion of recycler list
        holder.firstName.setText(users.get(position));
    }

    @Override
    public int getItemCount() {
        // return the size of array
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView firstName;

        public ViewHolder(@NonNull View itemView) {  // This itemView refer to recycle_view_item.xml, passed from line 24 - binded to recycle_view_item.xml
            super(itemView);
            firstName = itemView.findViewById(R.id.first_name);  // Get Textview reference from xml file (recycle_view_item.xml)
        }
    }
}

package com.example.foodtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.ViewHolder> {

    ArrayList<FoodItem> items;
    Context context;
    DashboardActivity dashboardActivity;

    public FoodItemAdapter(ArrayList<FoodItem> items, Context context) {
        this.items = items;
        this.context = context;
        this.dashboardActivity = (DashboardActivity) context;
    }

    @NonNull
    @Override
    public FoodItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemAdapter.ViewHolder holder, int position) {
        FoodItem item = items.get(position);

        holder.name.setText(item.getName());
        holder.expiry.setText("Expiry: " + item.getExpiryDate());
        holder.daysLeft.setText(item.getDaysLeft() + " day(s) left");

        holder.deleteIcon.setOnClickListener(v -> {
            dashboardActivity.deleteFoodItem(position);
            Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
        });

        holder.editIcon.setOnClickListener(v -> {
            // Here, you can implement update functionality
            Toast.makeText(context, "Edit Item - Not Implemented Yet", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, expiry, daysLeft;
        ImageView deleteIcon, editIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.foodName);
            expiry = itemView.findViewById(R.id.foodExpiry);
            daysLeft = itemView.findViewById(R.id.foodDays);
            deleteIcon = itemView.findViewById(R.id.deleteIcon);
            editIcon = itemView.findViewById(R.id.editIcon);
        }
    }
}

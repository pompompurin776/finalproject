package com.example.foodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    RecyclerView foodRecycler;
    ArrayList<FoodItem> foodItems = new ArrayList<>();
    DatabaseHelper dbHelper;
    FoodItemAdapter adapter;
    Button addItemBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        foodRecycler = findViewById(R.id.foodRecycler);
        addItemBtn = findViewById(R.id.addItemBtn);
        dbHelper = new DatabaseHelper(this);

        loadFoodItems();

        addItemBtn.setOnClickListener(v -> startActivity(new Intent(DashboardActivity.this, AddItemActivity.class)));
    }

    private void loadFoodItems() {
        foodItems = dbHelper.getAllFoodItems();
        adapter = new FoodItemAdapter(foodItems, this);
        foodRecycler.setLayoutManager(new LinearLayoutManager(this));
        foodRecycler.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFoodItems();
    }

    public void deleteFoodItem(int position) {
        FoodItem foodItem = foodItems.get(position);
        boolean isDeleted = dbHelper.deleteFoodItem(foodItem.getId());
        if (isDeleted) {
            Toast.makeText(this, "Food Item Deleted", Toast.LENGTH_SHORT).show();
            loadFoodItems();
        } else {
            Toast.makeText(this, "Failed to Delete Food Item", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateFoodItem(FoodItem updatedItem) {
        boolean isUpdated = dbHelper.updateFoodItem(updatedItem);
        if (isUpdated) {
            Toast.makeText(this, "Food Item Updated", Toast.LENGTH_SHORT).show();
            loadFoodItems();
        } else {
            Toast.makeText(this, "Failed to Update Food Item", Toast.LENGTH_SHORT).show();
        }
    }
}

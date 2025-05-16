package com.example.foodtracker;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddItemActivity extends AppCompatActivity {

    EditText foodNameEditText, categoryEditText;
    TextView expiryDateTextView;
    Button saveButton;
    DatabaseHelper dbHelper;
    Calendar calendar = Calendar.getInstance();
    String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        foodNameEditText = findViewById(R.id.editTextFoodName);
        categoryEditText = findViewById(R.id.editTextCategory);
        expiryDateTextView = findViewById(R.id.textViewExpiryDate);
        saveButton = findViewById(R.id.buttonSave);

        dbHelper = new DatabaseHelper(this);

        expiryDateTextView.setOnClickListener(view -> showDatePicker());

        saveButton.setOnClickListener(v -> saveFoodItem());
    }

    private void showDatePicker() {
        new DatePickerDialog(this, (view, year, month, day) -> {
            calendar.set(year, month, day);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            selectedDate = sdf.format(calendar.getTime());
            expiryDateTextView.setText(selectedDate);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void saveFoodItem() {
        String name = foodNameEditText.getText().toString().trim();
        String category = categoryEditText.getText().toString().trim();
        String expiryDate = selectedDate;

        if (name.isEmpty() || expiryDate.isEmpty() || category.isEmpty()) {
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
        } else {
            FoodItem foodItem = new FoodItem(name, expiryDate, 0, category);
            boolean isInserted = dbHelper.addFoodItem(foodItem);

            if (isInserted) {
                Toast.makeText(this, "Food Item Added", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to Add Food Item", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

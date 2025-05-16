package com.example.foodtracker;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView detail = findViewById(R.id.detailText);
        String name = getIntent().getStringExtra("name");
        detail.setText("Detail for " + name);
    }
}

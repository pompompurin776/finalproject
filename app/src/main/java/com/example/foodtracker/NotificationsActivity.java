package com.example.foodtracker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotificationsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<NotificationItem> notifications;
    NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        recyclerView = findViewById(R.id.notificationRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notifications = new ArrayList<>();
        notifications.add(new NotificationItem("Milk expires in 2 days", "10:00 AM", "Expiry Alert"));
        notifications.add(new NotificationItem("Bread expires today", "8:30 AM", "Urgent"));
        notifications.add(new NotificationItem("Eggs expire in 5 days", "9:15 AM", "Reminder"));

        adapter = new NotificationAdapter(notifications);
        recyclerView.setAdapter(adapter);
    }
}

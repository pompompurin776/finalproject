package com.example.foodtracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    ArrayList<NotificationItem> notifications;

    public NotificationAdapter(ArrayList<NotificationItem> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotificationItem item = notifications.get(position);
        holder.message.setText(item.message);
        holder.time.setText(item.time);
        holder.type.setText(item.type);

        holder.dismissBtn.setOnClickListener(v -> {
            notifications.remove(position);
            notifyItemRemoved(position);
        });

        holder.snoozeBtn.setOnClickListener(v -> {
            // You can add snooze logic here (e.g., reschedule)
            holder.message.setText(item.message + " (Snoozed)");
        });
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView message, time, type;
        Button dismissBtn, snoozeBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.notificationMessage);
            time = itemView.findViewById(R.id.notificationTime);
            type = itemView.findViewById(R.id.notificationType);
            dismissBtn = itemView.findViewById(R.id.dismissBtn);
            snoozeBtn = itemView.findViewById(R.id.snoozeBtn);
        }
    }
}

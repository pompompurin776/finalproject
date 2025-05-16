package com.example.foodtracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    EditText expiryThresholdEditText;
    Switch darkModeSwitch, syncSwitch;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize views
        expiryThresholdEditText = findViewById(R.id.expiryThresholdEditText);
        darkModeSwitch = findViewById(R.id.darkModeSwitch);
        syncSwitch = findViewById(R.id.syncSwitch);
        saveButton = findViewById(R.id.saveButton);

        // Load existing settings
        loadSettings();

        // Set up Save button to store preferences
        saveButton.setOnClickListener(v -> saveSettings());
    }

    private void loadSettings() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Load expiry threshold setting
        int expiryThreshold = sharedPreferences.getInt("expiryThreshold", 3);
        expiryThresholdEditText.setText(String.valueOf(expiryThreshold));

        // Load dark mode setting
        boolean isDarkMode = sharedPreferences.getBoolean("darkMode", false);
        darkModeSwitch.setChecked(isDarkMode);

        // Load sync setting
        boolean isSyncEnabled = sharedPreferences.getBoolean("sync", false);
        syncSwitch.setChecked(isSyncEnabled);
    }

    private void saveSettings() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Save expiry threshold
        int expiryThreshold = Integer.parseInt(expiryThresholdEditText.getText().toString());
        editor.putInt("expiryThreshold", expiryThreshold);

        // Save dark mode setting
        boolean isDarkMode = darkModeSwitch.isChecked();
        editor.putBoolean("darkMode", isDarkMode);

        // Save sync setting
        boolean isSyncEnabled = syncSwitch.isChecked();
        editor.putBoolean("sync", isSyncEnabled);

        // Commit changes
        editor.apply();

        // Show a confirmation
        Toast.makeText(this, "Settings saved!", Toast.LENGTH_SHORT).show();
    }
}

package com.example.foodtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FoodTracker.db";
    private static final int DATABASE_VERSION = 2;

    // User Table
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_PASSWORD = "password";

    // Food Item Table
    private static final String TABLE_FOOD_ITEMS = "food_items";
    private static final String COLUMN_FOOD_ID = "id";
    private static final String COLUMN_FOOD_NAME = "name";
    private static final String COLUMN_FOOD_EXPIRY = "expiry_date";
    private static final String COLUMN_FOOD_DAYS_LEFT = "days_left";
    private static final String COLUMN_FOOD_CATEGORY = "category";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " ("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_EMAIL + " TEXT UNIQUE, "
                + COLUMN_USER_PASSWORD + " TEXT)";

        String CREATE_FOOD_ITEMS_TABLE = "CREATE TABLE " + TABLE_FOOD_ITEMS + " ("
                + COLUMN_FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_FOOD_NAME + " TEXT, "
                + COLUMN_FOOD_EXPIRY + " TEXT, "
                + COLUMN_FOOD_DAYS_LEFT + " INTEGER, "
                + COLUMN_FOOD_CATEGORY + " TEXT)";

        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_FOOD_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD_ITEMS);
        onCreate(db);
    }

    // --- USER AUTHENTICATION ---
    public boolean registerUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    public boolean authenticateUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null,
                COLUMN_USER_EMAIL + "=? AND " + COLUMN_USER_PASSWORD + "=?",
                new String[]{email, password}, null, null, null);

        boolean isAuthenticated = cursor.getCount() > 0;
        cursor.close();
        return isAuthenticated;
    }

    // --- FOOD ITEM MANAGEMENT ---
    public boolean addFoodItem(FoodItem foodItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOOD_NAME, foodItem.getName());
        values.put(COLUMN_FOOD_EXPIRY, foodItem.getExpiryDate());
        values.put(COLUMN_FOOD_DAYS_LEFT, foodItem.getDaysLeft());
        values.put(COLUMN_FOOD_CATEGORY, foodItem.getCategory());

        long result = db.insert(TABLE_FOOD_ITEMS, null, values);
        return result != -1;
    }

    public ArrayList<FoodItem> getAllFoodItems() {
        ArrayList<FoodItem> foodList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FOOD_ITEMS, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FOOD_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FOOD_NAME));
                String expiryDate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FOOD_EXPIRY));
                int daysLeft = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FOOD_DAYS_LEFT));
                String category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FOOD_CATEGORY));

                foodList.add(new FoodItem(id, name, expiryDate, daysLeft, category));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return foodList;
    }

    public boolean updateFoodItem(FoodItem foodItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOOD_NAME, foodItem.getName());
        values.put(COLUMN_FOOD_EXPIRY, foodItem.getExpiryDate());
        values.put(COLUMN_FOOD_DAYS_LEFT, foodItem.getDaysLeft());
        values.put(COLUMN_FOOD_CATEGORY, foodItem.getCategory());

        int rowsUpdated = db.update(TABLE_FOOD_ITEMS, values, COLUMN_FOOD_ID + "=?",
                new String[]{String.valueOf(foodItem.getId())});
        return rowsUpdated > 0;
    }

    public boolean deleteFoodItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_FOOD_ITEMS, COLUMN_FOOD_ID + "=?",
                new String[]{String.valueOf(id)});
        return rowsDeleted > 0;
    }
}

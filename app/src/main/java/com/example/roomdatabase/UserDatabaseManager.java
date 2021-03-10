package com.example.roomdatabase;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class UserDatabaseManager {

    // Create database instance
    // RoomDatabase instance has very high costs and rarely revoke multiple instances in single thread,
    // thus, RoomDatabase should be a singleton class
    private static AppDatabase database;

    // Define database name
    private static String DATABASE_NAME = "UserInfo.db";

    public synchronized static AppDatabase getInstance(Context context) {
        // Check condition
        if (database == null) {
            // Setup a Room database
            database = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()  // allow query execute in main UI thread
                    .fallbackToDestructiveMigration()
                    .build();  // Creates the databases and initializes it
        }

        // Return database
        return database;
    }

    // Insert
    public static synchronized void addUser(Context context, User user) {
        getInstance(context).userDao().insert(user);
    }

    // Get User
    public static synchronized User getUser(Context context, String firstName) {
        return getInstance(context).userDao().getUser(firstName);
    }

    // Delete
    public static synchronized void deleteUser(Context context, User user) {
        getInstance(context).userDao().delete(user);
    }

    // Update
    public static synchronized void updateUser(Context context, User user) {
        getInstance(context).userDao().update(user);
    }

    // Get list of users
    public static synchronized List<User> getUsers(Context context) {
        return getInstance(context).userDao().getAll();
    }
}

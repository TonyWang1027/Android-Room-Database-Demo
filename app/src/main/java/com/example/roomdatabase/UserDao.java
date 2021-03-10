package com.example.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    // Insert query
    @Insert
    void insert(User... user);

    // Insert query
    @Insert
    void insert(User user);

    // Delete query
    @Delete
    void delete(User user);

    // Delete all query
    @Delete
    void reset(List<User> users);

    // Update query
    @Update
    void update(User user);

    // Get User
    @Query("SELECT * FROM User WHERE first_name = :firstName")
    User getUser(String firstName);

    // Get list of user
    @Query("SELECT * FROM user")
    List<User> getAll();

}

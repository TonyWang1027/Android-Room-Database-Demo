package com.example.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "userinfo")
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;  // the type of primary key in Room DB is long

    @ColumnInfo(name = "first_name")
    private String mFirstName;

    @ColumnInfo(name = "last_name")
    private String mLastName;

    @ColumnInfo(name = "email")
    private String mEmail;

    public User(String firstName, String lastName, String email) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mEmail = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}

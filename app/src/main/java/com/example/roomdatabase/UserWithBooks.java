package com.example.roomdatabase;

import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

import java.util.List;

public class UserWithBooks {

    @Embedded
    public User user;  // parent class/entity

    @Relation(parentColumn = "book_id", entityColumn = "user_fkId")
    public List<Book> books;
}

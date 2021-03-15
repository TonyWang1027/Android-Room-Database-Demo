package com.example.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "bookInfo")
public class Book implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long bookId;  // primary key for book table

    @ColumnInfo(name = "user_fkId")
    private long userFkId;  // foreign key for book table - link to user table

    @ColumnInfo(name = "book_name")
    private String mBookName;

    @ColumnInfo(name = "year_of_book")
    private String mYearOfBook;

    public Book(long userFkId, String bookName, String yearOfBook) {
        this.userFkId = userFkId;
        mBookName = bookName;
        mYearOfBook = yearOfBook;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getUserFkId() {
        return userFkId;
    }

    public void setUserFkId(long userFkId) {
        this.userFkId = userFkId;
    }

    public String getBookName() {
        return mBookName;
    }

    public void setBookName(String bookName) {
        mBookName = bookName;
    }

    public String getYearOfBook() {
        return mYearOfBook;
    }

    public void setYearOfBook(String yearOfBook) {
        mYearOfBook = yearOfBook;
    }
}

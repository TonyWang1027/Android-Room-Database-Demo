package com.example.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    String TABLE_NAME = "userinfo";

    // Insert User query
    @Insert
    long insertUser(User user);

    @Insert
    long insertBook(Book book);

    // Delete query
    @Delete
    void delete(User user);

    // Delete all query
    @Delete
    void reset(List<User> users);

    // Delete all rows in a table
    @Query("DELETE FROM " + TABLE_NAME)
    void deleteAllUserRows();

    // Delete all rows in a table
    @Query("DELETE FROM bookInfo")
    void deleteAllBookRows();

    // Update query
    @Update
    void update(User user);

    // Get list of books
    @Transaction
    @Query("SELECT * FROM " + TABLE_NAME + " WHERE first_name = :firstName")
    UserWithBooks getUserWithBooksByName(String firstName);

    String queryString = "SELECT bookInfo.bookId, bookInfo.user_fkId, bookInfo.book_name, bookInfo.year_of_book " +
            "FROM userinfo " +
            "INNER JOIN bookInfo ON userInfo.book_id=bookInfo.user_fkId " +
            "WHERE first_name = :firstName " +
            "ORDER BY bookInfo.year_of_book";

    @Query(queryString)
    List<Book> getAllBooksForAllUsers(String firstName);

    // Get User
    @Query("SELECT * FROM userinfo WHERE first_name = :firstName")
    User getUser(String firstName);

    // Get list of user
    @Query("SELECT * FROM userinfo")
    List<User> getAll();

}

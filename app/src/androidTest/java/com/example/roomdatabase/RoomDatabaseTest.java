package com.example.roomdatabase;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RoomDatabaseTest {

    private Context appContext;

    public void testInit() {
        // Context of the app under test.
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    public void clearUpDatabase() {
        testInit();
        UserDatabaseManager.deleteAllRows(appContext);
    }

    @Test
    public void addNewUserTest() {
        // Test purpose: Add one entity into database by using "UserDatabaseManager.addUser(context, VALUABLE_NAME)" method
        //               then, using "UserDatabaseManager.getUser(context, VALUABLE_NAME)" method to get user entity from DB
        clearUpDatabase();

        User user = new User("Tony", "Wang", "634599701@qq.com", 1);
        long id = UserDatabaseManager.addUser(appContext, user);
        user.setUserId(id);

        User user_back = UserDatabaseManager.getUser(appContext, "Tony");

        assertEquals("Tony", user_back.getFirstName());
    }

    @Test
    public void deleteUserTest() {
        clearUpDatabase();

        User user = new User("Jack", "Zhang", "634599711@qq.com",1);
        long id = UserDatabaseManager.addUser(appContext, user);

        /*
         * when inserting new User entity, room will auto-generate a PrimaryKey(id) for a row
         * insert function will return that PrimaryKey, type is "long"
         * But, attribute "id" in "user" object is not set, we need to set "id" manually, otherwise, delete function does not know which row we want to delete,
         * because Room uses the PrimaryKey to match passed entity instances to rows in the database.
         */
        user.setUserId(id);
        UserDatabaseManager.deleteUser(appContext, user);

        List<User> users = UserDatabaseManager.getUsers(appContext);

        assertEquals(0, users.size());

    }

    @Test
    public void updateDatabase() {
        clearUpDatabase();

        User user = new User("Andrew", "Li", "andrew.li@gmail.com", 1);
        // insert data
        long id = UserDatabaseManager.addUser(appContext, user);
        user.setUserId(id);

        // update data
        user.setEmail("andrew.li.2021@gmail.com");
        user.setLastName("Huang");
        UserDatabaseManager.updateUser(appContext, user);

        User user_back = UserDatabaseManager.getUser(appContext, "Andrew");

        assertEquals("andrew.li.2021@gmail.com", user_back.getEmail());
        assertEquals("Huang", user_back.getLastName());
    }

    @Test
    public void deleteAllUsers() {
        clearUpDatabase();

        List<User> users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            users.add(new User("FirstName" + i, "LastName" + i, "email" + 1 + "@gmail.com", 1));
            long id = UserDatabaseManager.addUser(appContext, users.get(i));
            users.get(i).setUserId(id);
        }

        UserDatabaseManager.getInstance(appContext).userDao().reset(users);

        List<User> users_back = UserDatabaseManager.getUsers(appContext);

        assertEquals(0, users_back.size());

        UserDatabaseManager.deleteAllRows(appContext);
    }

    @Test
    public void insertUserWithBooks() {
        clearUpDatabase();

        User user = new User("Jack", "Huang", "131456478@gmail.com", 1);
        long userId = UserDatabaseManager.addUser(appContext, user);
        user.setUserId(userId);

        Book book1 = new Book(1, "WithMe", "2007");
        long bookId1 = UserDatabaseManager.addBook(appContext, book1);
        book1.setBookId(bookId1);

        Book book2 = new Book(1, "HeIsGood", "2009");
        long bookId2 = UserDatabaseManager.addBook(appContext, book2);
        book2.setBookId(bookId2);

        UserWithBooks userWithBooks = UserDatabaseManager.getUserWithBooks(appContext, "Jack");

        assertEquals("HeIsGood", userWithBooks.books.get(1).getBookName());
    }
}

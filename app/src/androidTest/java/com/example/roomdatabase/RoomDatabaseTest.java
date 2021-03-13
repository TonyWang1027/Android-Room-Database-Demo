package com.example.roomdatabase;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

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
        clearUpDatabase();

        User user = new User("Tony", "Wang", "634599701@qq.com");
        long id = UserDatabaseManager.addUser(appContext, user);
        user.setId(id);

        User user_back = UserDatabaseManager.getUser(appContext, "Tony");

        assertEquals("Tony", user_back.getFirstName());
    }

    @Test
    public void deleteUserTest() {
        clearUpDatabase();

        User user = new User("Zhang", "Jack", "634599711@qq.com");
        long id = UserDatabaseManager.addUser(appContext, user);

        /*
         * when inserting new User entity, room will auto-generate a PrimaryKey(id) for a row
         * insert function will return that PrimaryKey, type is "long"
         * But, attribute "id" in "user" object is not set, we need to set "id" manually, otherwise, delete function does not know which row we want to delete,
         * because Room uses the PrimaryKey to match passed entity instances to rows in the database.
         */
        user.setId(id);
        UserDatabaseManager.deleteUser(appContext, user);

        List<User> users = UserDatabaseManager.getUsers(appContext);

        assertEquals(0, users.size());

    }
}

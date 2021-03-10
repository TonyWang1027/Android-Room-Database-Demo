package com.example.roomdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class AddInfoActivity extends AppCompatActivity {

    private static final String TAG = "AddInfoActivity";

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_info_main);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        button = findViewById(R.id.button);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production").allowMainThreadQueries().build();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstNameString = firstName.getText().toString();
                String lastNameString = lastName.getText().toString();
                String emailString = email.getText().toString();

                Log.d(TAG, "Message: " + firstNameString + " " + lastNameString + " " + emailString);

                User user = new User(firstNameString, lastNameString, emailString);
                db.userDao().insertAll(user);

                startActivity(new Intent(AddInfoActivity.this, MainActivity.class));
            }
        });
    }

}

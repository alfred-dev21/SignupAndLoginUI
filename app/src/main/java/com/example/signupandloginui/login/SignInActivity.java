package com.example.signupandloginui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.signupandloginui.MainActivity;
import com.example.signupandloginui.R;
import com.example.signupandloginui.database.DatabaseHandler;
import com.example.signupandloginui.gallery.GalleryActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private String email, password;
    DatabaseHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        database = new DatabaseHandler(this, "app");
    }

    public void retrieveData(View view){
        EditText inputEmail = findViewById(R.id.inputEmail);
        EditText inputPassword = findViewById(R.id.inputPassword);

        email = inputEmail.getText().toString();
        password = inputPassword.getText().toString();

        if(validateDetails()){
            toGallery();
        }
    }

    public void toGallery(){
        Intent intent = new Intent(SignInActivity.this, GalleryActivity.class);
        startActivity(intent);
    }

    public boolean allFilled(){
        return !email.isEmpty() && !password.isEmpty();
    }

    public boolean isValidEmail(){
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidUser(){
        Cursor cursor = database.doQuery(
                String.format(
                        "SELECT email, password FROM userDetails WHERE email='%s' AND password='%s'", email, password
                )
        );

        return cursor.moveToNext();
    }

    public boolean validateDetails(){
        if (allFilled()){
            if (!isValidEmail()){
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!isValidUser()){
                Toast.makeText(this, "Either your password or email is incorrect", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
        Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
        return false;
    }
}
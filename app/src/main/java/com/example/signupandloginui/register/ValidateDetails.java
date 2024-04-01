package com.example.signupandloginui.register;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.signupandloginui.database.DatabaseHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateDetails {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private final String name, email, password, password2;
    DatabaseHandler database;

    Context context;

    public ValidateDetails(
            DatabaseHandler database,
            Context context,
            String name,
            String email,
            String p1,
            String p2)
    {
        this.name = name;
        this.email = email;
        this.password = p1;
        this.password2 = p2;
        this.context = context;
        this.database = database;
    }

    public boolean testAllFilled(){
        return  !name.isEmpty() &&
                !email.isEmpty() &&
                !password.isEmpty() &&
                !password2.isEmpty();
    }

    public boolean isPasswordCorrect(){
        return password.equals(password2);
    }

    public boolean isValidEmail(){
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isNewEmail(){
        Cursor cursor = database.doQuery(String.format("SELECT * FROM userDetails WHERE email='%s'", email));
        return !cursor.moveToNext();
    }

    public boolean validateHandler(){
        if (testAllFilled()){
            if (!isPasswordCorrect()){
                Toast.makeText(context, "Passwords are not the same", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!isValidEmail()){
                Toast.makeText(context, "Please Enter a valid email", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!isNewEmail()){
                Toast.makeText(context, "There already a user with that email", Toast.LENGTH_SHORT).show();
                return false;
            }
            Toast.makeText(context, "Your account has been successfully created", Toast.LENGTH_SHORT).show();
            return true;
        }
        Toast.makeText(context, "Please fill all the required fields", Toast.LENGTH_SHORT).show();
        return false;
    }

}

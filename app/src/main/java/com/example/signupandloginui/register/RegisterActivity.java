package com.example.signupandloginui.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.signupandloginui.R;
import com.example.signupandloginui.database.DatabaseHandler;

import android.database.Cursor;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {
    private String username, email, password, password2;

    DatabaseHandler myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        myDB=new DatabaseHandler(this, "app");

    }


    public void retrieveData(View view){
        EditText nameInput = findViewById(R.id.nameInput);
        EditText emailInput = findViewById(R.id.emailInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        EditText passwordInput2 = findViewById(R.id.passwordInput2);

        username = nameInput.getText().toString();
        email = emailInput.getText().toString();
        password = passwordInput.getText().toString();
        password2 = passwordInput2.getText().toString();

        validateDetails();
    }

    public boolean testAllFilled(){
        return  !username.isEmpty() &&
                !email.isEmpty() &&
                !password.isEmpty() &&
                !password2.isEmpty();
    }

    public void validateDetails(){
        if (testAllFilled()){
            if (password.equals(password2)){
                doInsert(username, email, password);
                Toast.makeText(this, "Your account was successfully created", Toast.LENGTH_SHORT).show();
                doQuery();
                return;
            }
            Toast.makeText(this, "passwords are not the same", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Fill all the required fields", Toast.LENGTH_SHORT).show();

    }

    public void doInsert(String name, String email, String password){
        String[] vals = {name,email, password};
        myDB.doUpdate("Insert into userDetails(name, email, password) values (?,?,?);", vals);
    }

    public void clearData(){
        myDB.doUpdate("DELETE FROM userDetails");
    }

    public void doQuery(){
//        clearData();
        Cursor c = myDB.doQuery("SELECT name, email from userDetails");
        while (c.moveToNext()){
            int nameIndex = c.getColumnIndex("name");
            int emailIndex = c.getColumnIndex("email");

            System.out.println(c.getString(nameIndex) + "," + c.getString(emailIndex));
            System.out.println("alfred");
        }
        c.close();
    }
}
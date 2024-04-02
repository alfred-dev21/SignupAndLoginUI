package com.example.signupandloginui.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.signupandloginui.R;
import com.example.signupandloginui.database.DatabaseHandler;
import com.example.signupandloginui.login.SignInActivity;

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
        nameInput.setText(""); emailInput.setText("");
        passwordInput.setText(""); passwordInput2.setText("");
    }

    public void validateDetails(){
        ValidateDetails validate = new ValidateDetails(
                myDB,
                this,
                username,
                email,
                password,
                password2
        );

        if (validate.validateHandler()){
            doInsert(username, email, password);
        }
    }

    public void doInsert(String name, String email, String password){
        String[] values = {name,email, password};
        myDB.doUpdate("Insert into userDetails(name, email, password) values (?,?,?);", values);
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

    public void toSignInPage(View view) {
        Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
        startActivity(intent);
    }
}
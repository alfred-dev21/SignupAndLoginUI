package com.example.signupandloginui.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.signupandloginui.R;
import com.example.signupandloginui.database.DatabaseHandler;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class RegisterActivity extends Activity {

    DatabaseHandler myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        myDB=new DatabaseHandler(this, "app");
    }

//    public void doList(View v){
//        LinearLayout l = (LinearLayout)findViewById(R.id.mainView);
//        TextView t = new TextView(this);
//        t.setText("Some text");
//        l.addView(t);
//    }

//    public void doInsert(View v){
//        String[] vals = {"Steve","23"};
//        myDB.doUpdate("Insert into students(name, age) values (?,?);", vals);
//    }

    public void doQuery(View v){
        Cursor c = myDB.doQuery("SELECT name, email from userDetails");
        while (c.moveToNext()){
            System.out.println(c.getString(c.getColumnIndex("name"))+","+c.getString(c.getColumnIndex("email")));
        }
        c.close();
    }
}
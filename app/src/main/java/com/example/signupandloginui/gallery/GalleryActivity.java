package com.example.signupandloginui.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.signupandloginui.R;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        RecyclerView celebrityView = findViewById(R.id.celebrityView);
        ArrayList<Celebrity> celebrities = new ArrayList<>();

        celebrities.add(new Celebrity("Megan Fox", "Singer/Actor", "megan"));
        celebrities.add(new Celebrity("Kanye West", "Singer/Designer", "kanye"));
        celebrities.add(new Celebrity("Rihanna Jenkins", "Singer", "rihanna"));
        celebrities.add(new Celebrity("Morgan Freeman", "Actor", "morgan"));
        celebrities.add(new Celebrity("Cardi B", "Singer", "cardi"));
        celebrities.add(new Celebrity("Quincy Rowland", "Model/Actor", "quincy"));

        CelebAdapter celebAdapter = new CelebAdapter(this);
        celebAdapter.setCelebrities(celebrities);
        celebrityView.setAdapter(celebAdapter);

        celebrityView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}
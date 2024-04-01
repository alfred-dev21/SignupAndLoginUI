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

        celebrities.add(new Celebrity("Megan Freedom", "Singer/Actor", "https://www.byrdie.com/thmb/RJcVfukdYM7aPMtVhje050P4MYw=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/GettyImages-911199570-c7347dab092841efac74925488146989.jpg"));
        celebrities.add(new Celebrity("Kanye West", "Singer/Designer", "https://www.format.com/wp-content/uploads/kawai-matthews-kanye-west-1.jpg"));
        celebrities.add(new Celebrity("Rihanna Jenkins", "Singer", "https://hips.hearstapps.com/hmg-prod/images/766/shutterstock-350127209-1515591195.jpg"));
        celebrities.add(new Celebrity("Walter Freeman", "Actor", "https://img.buzzfeed.com/buzzfeed-static/static/2020-11/12/21/asset/b537877860a1/sub-buzz-2914-1605214930-12.jpg"));
        celebrities.add(new Celebrity("Cardi B", "Singer", "https://footwearnews.com/wp-content/uploads/2019/10/cardi-b-two-tone-suit.jpg"));
        celebrities.add(new Celebrity("Quincy Rowland", "Model/Actor", "https://netstorage-briefly.akamaized.net/images/050b41a1dfe25c98.jpg"));

        CelebAdapter celebAdapter = new CelebAdapter(this);
        celebAdapter.setCelebrities(celebrities);
        celebrityView.setAdapter(celebAdapter);

        celebrityView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}
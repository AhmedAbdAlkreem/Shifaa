package com.example.shifaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeManger extends AppCompatActivity {

    ImageView teethData ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_manger);

        teethData = (ImageView) findViewById(R.id.img_teeth1);
        teethData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeManger.this , teethDatbase.class);
                startActivity(intent);
            }
        });
    }
}
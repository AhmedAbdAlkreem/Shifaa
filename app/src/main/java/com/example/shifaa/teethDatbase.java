package com.example.shifaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class teethDatbase extends AppCompatActivity {

    Button btn_add ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teeth_datbase);

        btn_add = (Button) findViewById(R.id.Add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(teethDatbase.this, AddCutomViw.class);
                startActivity(intent);
            }
        });
    }
}
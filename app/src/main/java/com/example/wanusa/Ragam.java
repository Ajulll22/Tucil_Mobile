package com.example.wanusa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ragam extends AppCompatActivity {
    private Button btnesy ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ragam);
        btnesy = (Button) findViewById(R.id.btn_easy_rgm);


        btnesy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Ragam.this,Soal.class);
                startActivity(i);
            }
        });
    }
}
package com.example.wanusa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start extends AppCompatActivity {
    private Button btnsjr;
    private Button btnrgm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnsjr = (Button) findViewById(R.id.btn_Sjr);
        btnrgm = (Button) findViewById(R.id.btn_Rgm);

        btnsjr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Start.this,Sejarah.class);
                startActivity(i);
            }
        });
        btnrgm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Start.this,Ragam.class);
                startActivity(i);
            }
        });

    }
}
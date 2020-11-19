package com.example.wanusa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Hasil_Kuis extends AppCompatActivity {
    private Button btnlnjt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil__kuis);
        btnlnjt = (Button) findViewById(R.id.btn_lnjt);


        btnlnjt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Hasil_Kuis.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
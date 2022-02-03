package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class aggiungi_appuntamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_appuntamento);

        final Button button_plus = findViewById(R.id.button2);
        button_plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(aggiungi_appuntamento.this, cerca_aggiungi_cliente.class));
            }
        });


    }
}
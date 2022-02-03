package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class proposte_slot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposte_slot);

        final Button button_plus = findViewById(R.id.button3);
        button_plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(proposte_slot.this, MainActivity.class));
            }
        });
    }
}
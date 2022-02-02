package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class cerca_aggiungi_cliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerca_aggiungi_cliente);

        final Button button_plus = findViewById(R.id.button5);
        button_plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(cerca_aggiungi_cliente.this, proposte_slot.class));
            }
        });

        /*MongoClientURI uri = new MongoClientURI("mongodb://username:password@www.example.com:12345/db-name" );
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase(uri.getDatabase());*/
    }
}
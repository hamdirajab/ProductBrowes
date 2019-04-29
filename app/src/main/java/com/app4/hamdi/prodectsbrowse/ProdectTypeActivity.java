package com.app4.hamdi.prodectsbrowse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProdectTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodect_type);

        Button pcBtn = findViewById(R.id.button2);
        Button mobileBtn = findViewById(R.id.button);
        Button bcBtn = findViewById(R.id.button3);
        Button caBtn = findViewById(R.id.button6);
        Button maBtn = findViewById(R.id.button5);
        Button hpBtn = findViewById(R.id.button4);
        
        final Intent intent = new Intent(getApplicationContext() , AdminShowAllActivity.class);

        pcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("type" , "Pc and Labtop");
                startActivity(intent);
            }
        });
        mobileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("type" , "Mobiles");
                startActivity(intent);
            }
        });
        bcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("type" , "Batteries and Chargers");
                startActivity(intent);
            }
        });
        caBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("type" , "Cameras");
                startActivity(intent);
            }
        });
        maBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("type" , "Moblies Accessories");
                startActivity(intent);
            }
        });
        hpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("type" , "Headphones");
                startActivity(intent);
            }
        });
    }
}

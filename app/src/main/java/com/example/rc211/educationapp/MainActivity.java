package com.example.rc211.educationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleEasy();
        handleMedium();
        handleHard();
    }

    public void handleEasy(){
        Button easy = (Button) findViewById(R.id.easybtn);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EasyGameActivity.class);
                startActivity(intent);
            }
        });
    }

    public void handleMedium(){
        Button easy = (Button) findViewById(R.id.mediumbtn);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MediumGameActivity.class);
                startActivity(intent);
            }
        });
    }

    public void handleHard(){
        Button easy = (Button) findViewById(R.id.hardbtn);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HardGameActivity.class);
                startActivity(intent);
            }
        });
    }
}

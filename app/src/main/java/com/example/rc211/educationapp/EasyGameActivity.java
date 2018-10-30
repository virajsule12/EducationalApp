package com.example.rc211.educationapp;

import android.animation.ObjectAnimator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EasyGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_game);
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.easylayout);
        TextView z = new TextView(this);
        z.setText("Hello World");
        z.setX((float) 50);
        z.setY((float) 500);
        layout.addView(z);

        ObjectAnimator anim = ObjectAnimator.ofFloat(z, "translationY",  900f);
        anim.setDuration(2000);
        anim.start();
        System.out.println(z.getY());


    }
}

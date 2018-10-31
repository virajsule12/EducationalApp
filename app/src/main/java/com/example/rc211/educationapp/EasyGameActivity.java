package com.example.rc211.educationapp;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EasyGameActivity extends AppCompatActivity {

    private int[] answers =new int[3];
    private boolean ans1Colliding = false;
    private boolean ans2Colliding = false;
    private boolean ans3Colliding = false;

    private boolean ans1Correct = false;
    private boolean ans2Correct = false;
    private boolean ans3Correct = false;

    private int score = 0;
    private int lives = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_game);

        handleButtons();
        answers[0] = 0;
        answers[1] = 0;
        answers[2] = 0;


//        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.easylayout);
//        TextView z = new TextView(this);
//        z.setText("30");
//        z.setX((float) 150);
//        z.setY((float) 500);
//        z.setTextColor(getResources().getColor(R.color.black));
//        z.setTextSize(30f);
//        layout.addView(z);
//
//        ObjectAnimator anim = ObjectAnimator.ofFloat(z, "translationY",  1275f);
//        anim.setDuration(2000);
//        anim.start();

        moveAnswers();


    }

    private void moveAnswers() {
        final Handler handler = new Handler();

        final ImageView circle1 = (ImageView) findViewById(R.id.circlem1);
        final ImageView circle2 = (ImageView) findViewById(R.id.circlem2);
        final ImageView circle3 = (ImageView) findViewById(R.id.circlem3);

        final Runnable r = new Runnable() {
            public void run() {
                if (lives > 0) {
                    final TextView equation = (TextView) findViewById(R.id.mEquation);
                    final TextView ans1 = (TextView) findViewById(R.id.easyans1);
                    final TextView ans2 = (TextView) findViewById(R.id.easyans2);
                    final TextView ans3 = (TextView) findViewById(R.id.easyans3);

                    int aOrs = (int) (Math.random() * 2);
                    int num1 = (int) ((Math.random() * 20) + 1);
                    int num2 = (int) ((Math.random() * 20) + 1);
                    int correctAns;

                    ans1Correct = false;
                    ans2Correct = false;
                    ans3Correct = false;
                    if (aOrs == 0) {//0 is addition
                        correctAns = num1 + num2;
                        equation.setText(num1 + " + " + num2 + " = ?");
                    } else {//1 is subtraction
                        correctAns = num1 - num2;
                        equation.setText(num1 + " - " + num2 + " = ?");
                    }

                    int temp = (int) (Math.random() * 3);

                    for (int i = 0; i < 3; i++) {
                        answers[i] = (int) ((Math.random() * 51) - 10);
                    }
                    answers[temp] = correctAns;
//
                for(int i=0;i<3;i++){
                    if(answers[i] == correctAns && i!= temp){
                        while(answers[i] == correctAns){
                            answers[i] = (int) ((Math.random()*51)-10);
                            System.out.println("changing from correct");
                        }
                    }

                }

                    if (temp == 0) {
                        ans1Correct = true;
                    } else if (temp == 1) {
                        ans2Correct = true;
                    } else {
                        ans3Correct = true;
                    }


                    ans1.setVisibility(View.VISIBLE);
                    ans1.setText(answers[0] + "");
                    ans1.clearAnimation();
                    ans1.setX(150);
                    ans1.setY(400);

                    ans2.setVisibility(View.VISIBLE);
                    ans2.setText(answers[1] + "");
                    ans2.clearAnimation();
                    ans2.setX(485);
                    ans2.setY(400);

                    ans3.setVisibility(View.VISIBLE);
                    ans3.setText(answers[2] + "");
                    ans3.clearAnimation();
                    ans3.setX(835);
                    ans3.setY(400);
                    final ObjectAnimator anim1 = ObjectAnimator.ofFloat(ans1, "translationY", 1550f);
                    anim1.setDuration(7000);
                    anim1.start();

                    anim1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            if (ans1.getX() + ans1.getWidth() > circle1.getX() && ans1.getX() < circle1.getX() + circle1.getWidth() && ans1.getY() + ans1.getHeight() > circle1.getY() && ans1.getY() < circle1.getY() + circle1.getHeight()) {
                                ans1Colliding = true;
                            } else {
                                ans1Colliding = false;
                            }
                        }
                    });

                    final ObjectAnimator anim2 = ObjectAnimator.ofFloat(ans2, "translationY", 1550f);
                    anim2.setDuration(7000);
                    anim2.start();

                    anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            if (ans2.getX() + ans2.getWidth() > circle2.getX() && ans2.getX() < circle2.getX() + circle2.getWidth() && ans2.getY() + ans2.getHeight() > circle2.getY() && ans2.getY() < circle2.getY() + circle2.getHeight()) {
                                ans2Colliding = true;
                            } else {
                                ans2Colliding = false;
                            }
                        }
                    });

                    final ObjectAnimator anim3 = ObjectAnimator.ofFloat(ans3, "translationY", 1550f);
                    anim3.setDuration(7000);
                    anim3.start();

                    anim3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            if (ans3.getX() + ans3.getWidth() > circle3.getX() && ans3.getX() < circle3.getX() + circle3.getWidth() && ans3.getY() + ans3.getHeight() > circle3.getY() && ans3.getY() < circle3.getY() + circle3.getHeight()) {
                                ans3Colliding = true;
                            } else {
                                ans3Colliding = false;
                            }
                        }
                    });


                    handler.postDelayed(this, 4750);
                }
                else{
                    final TextView equation = (TextView) findViewById(R.id.mEquation);
                    equation.setText("GAME OVER");
                }
            }

        };

        handler.postDelayed(r, 3000);

    }






    private void handleButtons(){
        Button btn1 = (Button) findViewById(R.id.mediumbtn1);
        Button btn2 = (Button) findViewById(R.id.mediumbtn2);
        Button btn3 = (Button) findViewById(R.id.mediumbtn3);
        final TextView scoretxt = (TextView) findViewById(R.id.mediumScore);
        final TextView livestxt = (TextView) findViewById(R.id.mediumLives);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans1Colliding){
                    if(ans1Correct){
                        System.out.println("colliding and correct");
                        score++;
                    }
                    else{
                        System.out.println("colliding and wrong");
                        lives--;
                    }
                }
                else{
                    System.out.println("not colliding");
                    lives--;
                }
                scoretxt.setText("Score: " + score);
                livestxt.setText("Lives: " + lives);
                if (lives==0){
                    final TextView equation = (TextView) findViewById(R.id.mEquation);
                    equation.setText("GAME OVER");
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans2Colliding){
                    if(ans2Correct){
                        System.out.println("colliding and correct");
                        score++;
                    }
                    else{
                        System.out.println("colliding and wrong");
                        lives--;
                    }
                }
                else{
                    System.out.println("not colliding");
                    lives--;
                }
                scoretxt.setText("Score: " + score);
                livestxt.setText("Lives: " + lives);
                if (lives==0){
                    final TextView equation = (TextView) findViewById(R.id.mEquation);
                    equation.setText("GAME OVER");
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans3Colliding){
                    if(ans3Correct){
                        System.out.println("colliding and correct");
                        score++;
                    }
                    else{
                        System.out.println("colliding and wrong");
                        lives--;
                    }
                }
                else{
                    System.out.println("not colliding");
                    lives--;
                }
                scoretxt.setText("Score: " + score);
                livestxt.setText("Lives: " + lives);
                if (lives==0){
                    final TextView equation = (TextView) findViewById(R.id.mEquation);
                    equation.setText("GAME OVER");
                }
            }
        });
    }

}

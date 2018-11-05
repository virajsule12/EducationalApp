package com.example.rc211.educationapp;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HardGameActivity extends AppCompatActivity {

    private int[] answers =new int[3];
    private boolean ans1Colliding = false;
    private boolean ans2Colliding = false;
    private boolean ans3Colliding = false;

    private boolean ans1Correct = false;
    private boolean ans2Correct = false;
    private boolean ans3Correct = false;

    private boolean ans1Clicked = false;
    private boolean ans2Clicked = false;
    private boolean ans3Clicked = false;

    private boolean gameOver = false;

    private ArrayList<Integer> num1wrongArr = new ArrayList<>();
    private ArrayList<Integer> num2wrongArr = new ArrayList<>();
    private ArrayList<String> operatorArr = new ArrayList<>();
    private ArrayList<Integer> incorrectansArr = new ArrayList<>();
    private ArrayList<Integer> correctansArr = new ArrayList<>();

    private int score = 0;
    private int lives = 3;

    private int potans1 = 0;
    private int potans2 = 0;
    private int potans3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_game);

        handleButtons();
        handleMenu();
        handleRestart();

        answers[0] = 0;
        answers[1] = 0;
        answers[2] = 0;

        moveAnswers();
    }

    private void moveAnswers() {
        final Handler handler = new Handler();

        final ImageView circle1 = (ImageView) findViewById(R.id.circleh1);
        final ImageView circle2 = (ImageView) findViewById(R.id.circleh2);
        final ImageView circle3 = (ImageView) findViewById(R.id.circleh3);

        final Button btn1 = (Button) findViewById(R.id.hardbtn1);
        final Button btn2 = (Button) findViewById(R.id.hardbtn2);
        final Button btn3 = (Button) findViewById(R.id.hardbtn3);



        final Runnable r = new Runnable() {
            public void run() {
                if (lives > 0) {
                    btn1.setEnabled(true);
                    btn2.setEnabled(true);
                    btn3.setEnabled(true);

                    circle1.setImageResource(R.drawable.circle);
                    circle2.setImageResource(R.drawable.circle);
                    circle3.setImageResource(R.drawable.circle);
                    final TextView equation = (TextView) findViewById(R.id.hEquation);
                    final TextView ans1 = (TextView) findViewById(R.id.hardans1);
                    final TextView ans2 = (TextView) findViewById(R.id.hardans2);
                    final TextView ans3 = (TextView) findViewById(R.id.hardans3);

                    int aOrs = (int) (Math.random() * 4);


                    int num1=0;
                    int num2=0;

                    int correctAns=0;

                    if (aOrs ==0){
                        num1 = (int) ((Math.random() * 10) + 1);
                        num2 = (int) ((Math.random() * 10) + 1);
                        correctAns = num1 + num2;
                        equation.setText(num1 + " + " + num2 + " = ?");
                    }
                    else if(aOrs == 1){
                        num1 = (int) ((Math.random() * 10) + 1);
                        num2 = (int) ((Math.random() * 10) + 1);
                        correctAns = num1 - num2;
                        equation.setText(num1 + " - " + num2 + " = ?");
                    }
                    else if(aOrs == 2){
                        num1 = (int) ((Math.random() * 10) + 1);
                        num2 = (int) ((Math.random() * 10) + 1);
                        correctAns = num1 * num2;
                        equation.setText(num1 + " * " + num2 + " = ?");
                    }
                    else if (aOrs == 3){
                        num2 = (int) ((Math.random() * 10) + 1);
                        correctAns = (int) ((Math.random() * 10) + 1);
                        num1 = num2 * correctAns;
                        equation.setText(num1 + " / " + num2 + " = ?");
                    }

                    ans1Clicked = false;
                    ans2Clicked = false;
                    ans3Clicked = false;

                    ans1Correct = false;
                    ans2Correct = false;
                    ans3Correct = false;

                    int temp = (int) (Math.random() * 3);

                    for (int i = 0; i < 3; i++) {
                        answers[i] = (int) ((Math.random() * 101) - 10);
                    }
                    answers[temp] = correctAns;

                    for(int i=0;i<3;i++){
                        if(answers[i] == correctAns && i!= temp){
                            while(answers[i] == correctAns){
                                answers[i] = (int) ((Math.random()*101)-20);
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
                    potans1 = Integer.parseInt(ans1.getText().toString());


                    ans2.setVisibility(View.VISIBLE);
                    ans2.setText(answers[1] + "");
                    ans2.clearAnimation();
                    ans2.setX(485);
                    ans2.setY(400);
                    potans2 = Integer.parseInt(ans2.getText().toString());

                    ans3.setVisibility(View.VISIBLE);
                    ans3.setText(answers[2] + "");
                    ans3.clearAnimation();
                    ans3.setX(835);
                    ans3.setY(400);
                    potans3 = Integer.parseInt(ans3.getText().toString());

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
                            if(ans1Clicked || ans2Clicked || ans3Clicked){
                                if (gameOver){
                                    ans1.setVisibility(View.INVISIBLE);
                                    ans2.setVisibility(View.INVISIBLE);
                                    ans3.setVisibility(View.INVISIBLE);
                                }
                                anim1.cancel();
                            }
                            if (gameOver){
                                ans1.setVisibility(View.INVISIBLE);
                                ans2.setVisibility(View.INVISIBLE);
                                ans3.setVisibility(View.INVISIBLE);
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
                            if(ans1Clicked || ans2Clicked || ans3Clicked){
                                if (gameOver){
                                    ans1.setVisibility(View.INVISIBLE);
                                    ans2.setVisibility(View.INVISIBLE);
                                    ans3.setVisibility(View.INVISIBLE);
                                }
                                anim2.cancel();
                            }
                            if (gameOver){
                                ans1.setVisibility(View.INVISIBLE);
                                ans2.setVisibility(View.INVISIBLE);
                                ans3.setVisibility(View.INVISIBLE);
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
                            if(ans1Clicked || ans2Clicked || ans3Clicked){
                                if (gameOver){
                                    ans1.setVisibility(View.INVISIBLE);
                                    ans2.setVisibility(View.INVISIBLE);
                                    ans3.setVisibility(View.INVISIBLE);
                                }
                                anim3.cancel();
                            }
                            if (gameOver){
                                ans1.setVisibility(View.INVISIBLE);
                                ans2.setVisibility(View.INVISIBLE);
                                ans3.setVisibility(View.INVISIBLE);
                            }
                        }
                    });


                    handler.postDelayed(this, 4750);
                }
            }

        };

        handler.postDelayed(r, 3000);

    }

    private void handleButtons(){
        final Button btn1 = (Button) findViewById(R.id.hardbtn1);
        final Button btn2 = (Button) findViewById(R.id.hardbtn2);
        final Button btn3 = (Button) findViewById(R.id.hardbtn3);

        final ImageView circle1 = (ImageView) findViewById(R.id.circleh1);
        final ImageView circle2 = (ImageView) findViewById(R.id.circleh2);
        final ImageView circle3 = (ImageView) findViewById(R.id.circleh3);

        final TextView scoretxt = (TextView) findViewById(R.id.hardScore);
        final TextView livestxt = (TextView) findViewById(R.id.hardLives);
        final TextView equation = (TextView) findViewById(R.id.hEquation);

        final TextView gameovertxt = (TextView) findViewById(R.id.hardgo);
        final TextView endScoretxt = (TextView) findViewById(R.id.hardend);
        final Button restartbtn = (Button) findViewById(R.id.hardrestart);
        final Button menuBtn = (Button) findViewById(R.id.hardmenu);

        final TextView incorrecttxt = (TextView) findViewById(R.id.hardwrong);
        final TextView correcttxt = (TextView) findViewById(R.id.hardcorrect);





        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans1Colliding){
                    if(ans1Correct){
                        System.out.println("colliding and correct");
                        score++;
                        circle1.setImageResource(R.drawable.correct);
                    }
                    else{
                        System.out.println("colliding and wrong");
                        lives--;
                        circle1.setImageResource(R.drawable.wrong);
                        String[] equationArr = equation.getText().toString().split("");

                        int counter = 0;
                        for (String y:equationArr){
                            if (y.equals("+") || y.equals("-") || y.equals("*") || y.equals("/")){
                                operatorArr.add(y);
                                break;
                            }
                            counter++;
                        }

                        int counter2 = 0;
                        for (String z:equationArr){
                            if(z.equals("=")){
                                break;
                            }
                            counter2++;
                        }


                        num1wrongArr.add(Integer.parseInt(equation.getText().toString().substring(0,(counter-2))));
                        num2wrongArr.add(Integer.parseInt(equation.getText().toString().substring(counter+1,counter2-2)));
                        incorrectansArr.add(potans1);

                        if (operatorArr.get(operatorArr.size()-1).equals("+")){
                            correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) + num2wrongArr.get(num2wrongArr.size()-1));
                        }
                        else if (operatorArr.get(operatorArr.size()-1).equals("-")){
                            correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) - num2wrongArr.get(num2wrongArr.size()-1));
                        }
                        else if (operatorArr.get(operatorArr.size()-1).equals("*")){
                            correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) * num2wrongArr.get(num2wrongArr.size()-1));
                        }
                        else if (operatorArr.get(operatorArr.size()-1).equals("/")){
                            correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) / num2wrongArr.get(num2wrongArr.size()-1));
                        }

                    }
                    if(ans2Correct){
                        circle2.setImageResource(R.drawable.correct);
                    }
                    if (ans3Correct) {
                        circle3.setImageResource(R.drawable.correct);
                    }
                }
                else{
                    System.out.println("not colliding");
                    lives--;
                    circle1.setImageResource(R.drawable.wrong);

                    String[] equationArr = equation.getText().toString().split("");

                    int counter = 0;
                    for (String y:equationArr){
                        if (y.equals("+") || y.equals("-") || y.equals("*") || y.equals("/")){
                            operatorArr.add(y);
                            break;
                        }
                        counter++;
                    }

                    int counter2 = 0;
                    for (String z:equationArr){
                        if(z.equals("=")){
                            break;
                        }
                        counter2++;
                    }


                    num1wrongArr.add(Integer.parseInt(equation.getText().toString().substring(0,(counter-2))));
                    num2wrongArr.add(Integer.parseInt(equation.getText().toString().substring(counter+1,counter2-2)));
                    incorrectansArr.add(potans1);

                    if (operatorArr.get(operatorArr.size()-1).equals("+")){
                        correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) + num2wrongArr.get(num2wrongArr.size()-1));
                    }
                    else if (operatorArr.get(operatorArr.size()-1).equals("-")){
                        correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) - num2wrongArr.get(num2wrongArr.size()-1));
                    }
                    else if (operatorArr.get(operatorArr.size()-1).equals("*")){
                        correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) * num2wrongArr.get(num2wrongArr.size()-1));
                    }
                    else if (operatorArr.get(operatorArr.size()-1).equals("/")){
                        correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) / num2wrongArr.get(num2wrongArr.size()-1));
                    }


                }
                scoretxt.setText("Score: " + score);
                livestxt.setText("Lives: " + lives);
                if (lives==0){
                    gameOver = true;

                    circle1.setVisibility(View.INVISIBLE);
                    circle2.setVisibility(View.INVISIBLE);
                    circle3.setVisibility(View.INVISIBLE);

                    scoretxt.setVisibility(View.INVISIBLE);
                    livestxt.setVisibility(View.INVISIBLE);
                    equation.setVisibility(View.INVISIBLE);

                    endScoretxt.setVisibility(View.VISIBLE);
                    gameovertxt.setVisibility(View.VISIBLE);
                    restartbtn.setVisibility(View.VISIBLE);
                    menuBtn.setVisibility(View.VISIBLE);

                    incorrecttxt.setVisibility(View.VISIBLE);
                    correcttxt.setVisibility(View.VISIBLE);


                    endScoretxt.setText("Score: " + score);

                    String missed = "Missed Questions:  \n" ;
                    String correct = "Corrections:  \n";
                    for (int i=0;i<3;i++){
                        missed += (num1wrongArr.get(i).toString() + " " + operatorArr.get(i) +  " " + num2wrongArr.get(i).toString() + " = " + incorrectansArr.get(i).toString() + "\n");
                        correct += (num1wrongArr.get(i).toString() + " " + operatorArr.get(i) +  " " + num2wrongArr.get(i).toString() + " = " + correctansArr.get(i).toString() + "\n");
                    }
                    incorrecttxt.setText(missed);
                    correcttxt.setText(correct);
                }
                ans1Clicked = true;
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans2Colliding){
                    if(ans2Correct){
                        System.out.println("colliding and correct");
                        score++;
                        circle2.setImageResource(R.drawable.correct);

                    }
                    else{
                        System.out.println("colliding and wrong");
                        lives--;
                        circle2.setImageResource(R.drawable.wrong);

                        String[] equationArr = equation.getText().toString().split("");

                        int counter = 0;
                        for (String y:equationArr){
                            if (y.equals("+") || y.equals("-") || y.equals("*") || y.equals("/")){
                                operatorArr.add(y);
                                break;
                            }
                            counter++;
                        }

                        int counter2 = 0;
                        for (String z:equationArr){
                            if(z.equals("=")){
                                break;
                            }
                            counter2++;
                        }


                        num1wrongArr.add(Integer.parseInt(equation.getText().toString().substring(0,(counter-2))));
                        num2wrongArr.add(Integer.parseInt(equation.getText().toString().substring(counter+1,counter2-2)));
                        incorrectansArr.add(potans2);

                        if (operatorArr.get(operatorArr.size()-1).equals("+")){
                            correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) + num2wrongArr.get(num2wrongArr.size()-1));
                        }
                        else if (operatorArr.get(operatorArr.size()-1).equals("-")){
                            correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) - num2wrongArr.get(num2wrongArr.size()-1));
                        }
                        else if (operatorArr.get(operatorArr.size()-1).equals("*")){
                            correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) * num2wrongArr.get(num2wrongArr.size()-1));
                        }
                        else if (operatorArr.get(operatorArr.size()-1).equals("/")){
                            correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) / num2wrongArr.get(num2wrongArr.size()-1));
                        }
                    }
                    if(ans1Correct){
                        circle1.setImageResource(R.drawable.correct);
                    }
                    if (ans3Correct) {
                        circle3.setImageResource(R.drawable.correct);
                    }
                }
                else{
                    System.out.println("not colliding");
                    lives--;
                    circle2.setImageResource(R.drawable.wrong);

                    String[] equationArr = equation.getText().toString().split("");

                    int counter = 0;
                    for (String y:equationArr){
                        if (y.equals("+") || y.equals("-") || y.equals("*") || y.equals("/")){
                            operatorArr.add(y);
                            break;
                        }
                        counter++;
                    }

                    int counter2 = 0;
                    for (String z:equationArr){
                        if(z.equals("=")){
                            break;
                        }
                        counter2++;
                    }


                    num1wrongArr.add(Integer.parseInt(equation.getText().toString().substring(0,(counter-2))));
                    num2wrongArr.add(Integer.parseInt(equation.getText().toString().substring(counter+1,counter2-2)));
                    incorrectansArr.add(potans2);

                    if (operatorArr.get(operatorArr.size()-1).equals("+")){
                        correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) + num2wrongArr.get(num2wrongArr.size()-1));
                    }
                    else if (operatorArr.get(operatorArr.size()-1).equals("-")){
                        correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) - num2wrongArr.get(num2wrongArr.size()-1));
                    }
                    else if (operatorArr.get(operatorArr.size()-1).equals("*")){
                        correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) * num2wrongArr.get(num2wrongArr.size()-1));
                    }
                    else if (operatorArr.get(operatorArr.size()-1).equals("/")){
                        correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) / num2wrongArr.get(num2wrongArr.size()-1));
                    }
                }
                scoretxt.setText("Score: " + score);
                livestxt.setText("Lives: " + lives);
                if (lives==0){
                    gameOver = true;

                    circle1.setVisibility(View.INVISIBLE);
                    circle2.setVisibility(View.INVISIBLE);
                    circle3.setVisibility(View.INVISIBLE);

                    scoretxt.setVisibility(View.INVISIBLE);
                    livestxt.setVisibility(View.INVISIBLE);
                    equation.setVisibility(View.INVISIBLE);

                    endScoretxt.setVisibility(View.VISIBLE);
                    gameovertxt.setVisibility(View.VISIBLE);
                    restartbtn.setVisibility(View.VISIBLE);
                    menuBtn.setVisibility(View.VISIBLE);

                    incorrecttxt.setVisibility(View.VISIBLE);
                    correcttxt.setVisibility(View.VISIBLE);


                    endScoretxt.setText("Score: " + score);

                    String missed = "Missed Questions:  \n" ;
                    String correct = "Corrections:  \n";
                    for (int i=0;i<3;i++){
                        missed += (num1wrongArr.get(i).toString() + " " + operatorArr.get(i) +  " " + num2wrongArr.get(i).toString() + " = " + incorrectansArr.get(i).toString() + "\n");
                        correct += (num1wrongArr.get(i).toString() + " " + operatorArr.get(i) +  " " + num2wrongArr.get(i).toString() + " = " + correctansArr.get(i).toString() + "\n");
                    }
                    incorrecttxt.setText(missed);
                    correcttxt.setText(correct);
                }
                ans2Clicked = true;
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans3Colliding){
                    if(ans3Correct){
                        System.out.println("colliding and correct");
                        score++;
                        circle3.setImageResource(R.drawable.correct);
                    }
                    else{
                        System.out.println("colliding and wrong");
                        lives--;
                        circle3.setImageResource(R.drawable.wrong);

                        String[] equationArr = equation.getText().toString().split("");

                        int counter = 0;
                        for (String y:equationArr){
                            if (y.equals("+") || y.equals("-") || y.equals("*") || y.equals("/")){
                                operatorArr.add(y);
                                break;
                            }
                            counter++;
                        }

                        int counter2 = 0;
                        for (String z:equationArr){
                            if(z.equals("=")){
                                break;
                            }
                            counter2++;
                        }


                        num1wrongArr.add(Integer.parseInt(equation.getText().toString().substring(0,(counter-2))));
                        num2wrongArr.add(Integer.parseInt(equation.getText().toString().substring(counter+1,counter2-2)));
                        incorrectansArr.add(potans3);

                        if (operatorArr.get(operatorArr.size()-1).equals("+")){
                            correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) + num2wrongArr.get(num2wrongArr.size()-1));
                        }
                        else if (operatorArr.get(operatorArr.size()-1).equals("-")){
                            correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) - num2wrongArr.get(num2wrongArr.size()-1));
                        }
                        else if (operatorArr.get(operatorArr.size()-1).equals("*")){
                            correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) * num2wrongArr.get(num2wrongArr.size()-1));
                        }
                        else if (operatorArr.get(operatorArr.size()-1).equals("/")){
                            correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) / num2wrongArr.get(num2wrongArr.size()-1));
                        }
                    }
                    if(ans1Correct){
                        circle1.setImageResource(R.drawable.correct);
                    }
                    if (ans2Correct) {
                        circle2.setImageResource(R.drawable.correct);
                    }
                }
                else{
                    System.out.println("not colliding");
                    lives--;
                    circle3.setImageResource(R.drawable.wrong);

                    String[] equationArr = equation.getText().toString().split("");

                    int counter = 0;
                    for (String y:equationArr){
                        if (y.equals("+") || y.equals("-") || y.equals("*") || y.equals("/")){
                            operatorArr.add(y);
                            break;
                        }
                        counter++;
                    }

                    int counter2 = 0;
                    for (String z:equationArr){
                        if(z.equals("=")){
                            break;
                        }
                        counter2++;
                    }


                    num1wrongArr.add(Integer.parseInt(equation.getText().toString().substring(0,(counter-2))));
                    num2wrongArr.add(Integer.parseInt(equation.getText().toString().substring(counter+1,counter2-2)));
                    incorrectansArr.add(potans3);

                    if (operatorArr.get(operatorArr.size()-1).equals("+")){
                        correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) + num2wrongArr.get(num2wrongArr.size()-1));
                    }
                    else if (operatorArr.get(operatorArr.size()-1).equals("-")){
                        correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) - num2wrongArr.get(num2wrongArr.size()-1));
                    }
                    else if (operatorArr.get(operatorArr.size()-1).equals("*")){
                        correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) * num2wrongArr.get(num2wrongArr.size()-1));
                    }
                    else if (operatorArr.get(operatorArr.size()-1).equals("/")){
                        correctansArr.add(num1wrongArr.get(num1wrongArr.size()-1) / num2wrongArr.get(num2wrongArr.size()-1));
                    }
                }
                scoretxt.setText("Score: " + score);
                livestxt.setText("Lives: " + lives);
                if (lives==0){
                    gameOver = true;

                    circle1.setVisibility(View.INVISIBLE);
                    circle2.setVisibility(View.INVISIBLE);
                    circle3.setVisibility(View.INVISIBLE);

                    scoretxt.setVisibility(View.INVISIBLE);
                    livestxt.setVisibility(View.INVISIBLE);
                    equation.setVisibility(View.INVISIBLE);

                    endScoretxt.setVisibility(View.VISIBLE);
                    gameovertxt.setVisibility(View.VISIBLE);
                    restartbtn.setVisibility(View.VISIBLE);
                    menuBtn.setVisibility(View.VISIBLE);

                    incorrecttxt.setVisibility(View.VISIBLE);
                    correcttxt.setVisibility(View.VISIBLE);

                    endScoretxt.setText("Score: " + score);

                    String missed = "Missed Questions:  \n";
                    String correct = "Corrections:  \n";
                    for (int i=0;i<3;i++){
                        missed += (num1wrongArr.get(i).toString() + " " + operatorArr.get(i) +  " " + num2wrongArr.get(i).toString() + " = " + incorrectansArr.get(i).toString() + "\n");
                        correct += (num1wrongArr.get(i).toString() + " " + operatorArr.get(i) +  " " + num2wrongArr.get(i).toString() + " = " + correctansArr.get(i).toString() + "\n");
                    }
                    incorrecttxt.setText(missed);
                    correcttxt.setText(correct);
                }
                ans3Clicked = true;
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
            }
        });
    }

    private void handleRestart(){
        final Button btn1 = (Button) findViewById(R.id.hardbtn1);
        final Button btn2 = (Button) findViewById(R.id.hardbtn2);
        final Button btn3 = (Button) findViewById(R.id.hardbtn3);

        final ImageView circle1 = (ImageView) findViewById(R.id.circleh1);
        final ImageView circle2 = (ImageView) findViewById(R.id.circleh2);
        final ImageView circle3 = (ImageView) findViewById(R.id.circleh3);

        final TextView scoretxt = (TextView) findViewById(R.id.hardScore);
        final TextView livestxt = (TextView) findViewById(R.id.hardLives);
        final TextView equation = (TextView) findViewById(R.id.hEquation);

        final TextView gameovertxt = (TextView) findViewById(R.id.hardgo);
        final TextView endScoretxt = (TextView) findViewById(R.id.hardend);
        final Button restartbtn = (Button) findViewById(R.id.hardrestart);
        final Button menuBtn = (Button) findViewById(R.id.hardmenu);

        final TextView incorrecttxt = (TextView) findViewById(R.id.hardwrong);
        final TextView correcttxt = (TextView) findViewById(R.id.hardcorrect);


        restartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circle1.setVisibility(View.VISIBLE);
                circle2.setVisibility(View.VISIBLE);
                circle3.setVisibility(View.VISIBLE);

                scoretxt.setVisibility(View.VISIBLE);
                livestxt.setVisibility(View.VISIBLE);
                equation.setVisibility(View.VISIBLE);

                gameovertxt.setVisibility(View.INVISIBLE);
                endScoretxt.setVisibility(View.INVISIBLE);
                menuBtn.setVisibility(View.INVISIBLE);
                restartbtn.setVisibility(View.INVISIBLE);

                incorrecttxt.setVisibility(View.INVISIBLE);
                correcttxt.setVisibility(View.INVISIBLE);

                gameOver = false;

                score = 0;
                lives = 3;

                num1wrongArr.clear();
                num2wrongArr.clear();
                incorrectansArr.clear();
                correctansArr.clear();
                operatorArr.clear();

                scoretxt.setText("Score: " + score);
                livestxt.setText("Lives: " + lives);
                equation.setText("");

                circle1.setImageResource(R.drawable.circle);
                circle2.setImageResource(R.drawable.circle);
                circle3.setImageResource(R.drawable.circle);

                moveAnswers();
            }
        });
    }

    private void handleMenu(){
        Button menuBtn = (Button) findViewById(R.id.hardmenu);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HardGameActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

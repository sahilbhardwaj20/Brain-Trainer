package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton,button1,button2,button3,button4, playAgainButton;
    TextView sumTextView, resultTextView, scoreTextView, timerTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer, score = 0, totalAnswers = 0;
    GridLayout gridLayout;
    CountDownTimer timer;

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        timer = new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000 + "s"));
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done");
                gridLayout.setVisibility(View.INVISIBLE);
                sumTextView.setVisibility(View.INVISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
                score=0; totalAnswers = 0;
                timer.cancel();
                timerTextView.setText("30s");
            }
        }.start();

        newQuestion();
    }

    public void checkAnswer(View view){

        if(String.valueOf(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct");
            score++;
        } else {
            resultTextView.setText("Wrong :(");
        }

        totalAnswers++;
        scoreTextView.setText(score+ "/" +totalAnswers);
        newQuestion();
    }

    public void playAgain(View view){
        start(findViewById(R.id.goButton));
        playAgainButton.setVisibility(View.INVISIBLE);
        scoreTextView.setText("0/0");
        resultTextView.setText("");
    }

    public void newQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(String.valueOf(a)+" + "+String.valueOf(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();
        for(int i=0; i<4; i++){

            if(locationOfCorrectAnswer==i) {
                answers.add(a+b);
            } else {
                int check = rand.nextInt(41);

                while (check == a + b) {
                    check = rand.nextInt(41);

                }
                answers.add(check);
            }
        }
        button1.setText(String.valueOf(answers.get(0)));
        button2.setText(String.valueOf(answers.get(1)));
        button3.setText(String.valueOf(answers.get(2)));
        button4.setText(String.valueOf(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton=(Button)findViewById(R.id.goButton);
        sumTextView=(TextView)findViewById(R.id.sumTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
    }
}
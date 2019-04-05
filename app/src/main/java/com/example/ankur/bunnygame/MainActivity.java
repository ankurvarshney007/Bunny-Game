package com.example.ankur.bunnygame;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView bunny1, bunny2, bunny3;
    TextView tv_left, tv_score;
    Button btn;

    Random r;
    int score = 0, fps = 1000, left = 5, timeleft = 0;
    int which = 0; int  whichsave = 0;
    AnimationDrawable an;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r = new Random();
        btn = findViewById(R.id.button);
        tv_left = findViewById(R.id.tv_left);
        tv_score = findViewById(R.id.tv_score);
        bunny1 = findViewById(R.id.bunny1);
        bunny2 = findViewById(R.id.bunny2);
        bunny3 = findViewById(R.id.bunny3);



        bunny1.setVisibility(View.INVISIBLE);
        bunny2.setVisibility(View.INVISIBLE);
        bunny3.setVisibility(View.INVISIBLE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                left = 5;
                tv_left.setText("LEFT: " + left);
                score = 0;
                tv_score.setText("LEFT: " + score);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        theGameActions();
                    }
                }, 1000);
                btn.setEnabled(false);

            }
        });


        bunny1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeleft = 1;
                bunny1.setImageResource(R.drawable.bunnyhit);
                score = score + 1;
                tv_score.setText("SCORE: " + score);
                bunny1.setEnabled(false);
            }
        });

        bunny2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeleft = 1;
                bunny2.setImageResource(R.drawable.bunnyhit);
                score = score + 1;
                tv_score.setText("SCORE: " + score);
                bunny2.setEnabled(false);
            }

        });

        bunny3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timeleft = 1;
                bunny3.setImageResource(R.drawable.bunnyhit);
                score = score + 1;
                tv_score.setText("SCORE: " + score);
                bunny3.setEnabled(false);
            }
        });


    }



private void theGameActions() {
    if (score < 10) {
        fps = 1000;

    } else if (score >= 10 && score < 15) {
        fps = 950;
    } else if (score >= 15 && score < 20) {
        fps = 900;
    } else if (score >= 20 && score < 25) {
        fps = 850;
    } else if (score >= 25 && score < 30) {
        fps = 800;
    } else if (score >= 30 && score < 35) {
        fps = 750;
    } else if (score >= 35 && score < 40) {
        fps = 700;
    } else if (score >= 40 && score < 45) {
        fps = 650;
    } else if (score >= 45 && score < 50) {
        fps = 600;
    } else if (score >= 50 && score < 55) {
        fps = 550;
    } else if (score >= 55 && score < 60) {
        fps = 500;
    } else if (score >= 60 && score < 65) {
        fps = 450;
    } else if (score >= 65 && score < 70) {
        fps = 400;
    } else if (score >= 70) {
        fps = 350;
    }
    an = (AnimationDrawable) ContextCompat.getDrawable(this, R.drawable.anim);
    do {
        which = r.nextInt(3) + 1;
    } while (whichsave == which);

    if (which == 1) {
        bunny1.setImageDrawable(an);
        bunny1.setVisibility(View.VISIBLE);
        bunny1.setEnabled(true);
    } else if (which == 2) {
        bunny2.setImageDrawable(an);
        bunny2.setVisibility(View.VISIBLE);
        bunny2.setEnabled(true);
    } else if (which == 3) {
        bunny3.setImageDrawable(an);
        bunny3.setVisibility(View.VISIBLE);
        bunny3.setEnabled(true);
    }

    an.start();

    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            bunny1.setVisibility(View.INVISIBLE);
            bunny2.setVisibility(View.INVISIBLE);
            bunny3.setVisibility(View.INVISIBLE);

            bunny1.setEnabled(false);
            bunny2.setEnabled(false);
            bunny3.setEnabled(false);

            if (timeleft == 0) {
                left = left - 1;
                tv_left.setText("LEFT: " + left);
            } else if (timeleft == 1) {
                timeleft = 0;
            }

            if (left == 0) {
                Toast.makeText(MainActivity.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                btn.setEnabled(true);

            } else if (left > 0) {
                theGameActions();
            }
        }
    }, fps);
}
}
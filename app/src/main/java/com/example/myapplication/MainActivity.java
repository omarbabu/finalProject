package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.Random;


    public class MainActivity extends AppCompatActivity {

        private int rando = 0;
        private String display = "";
        private String taskString = "";
        private View background;
        private String gameType;
        private final String[] pg = new String[10];
        private final String[] bad = new String[10];
        private static int last = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button roll = findViewById(R.id.roll);
        final TextView result = findViewById(R.id.result);
        final SeekBar skBar = findViewById(R.id.seekBar);
        final Button reroll = findViewById(R.id.reRoll);
        gameType = getIntent().getStringExtra("gameType");
        if (gameType.equals("goodBoy")) {
            result.setTextSize(84);
            result.setText("PG Version: \n Roll to Start!");
        } else {
            result.setTextSize(84);
            result.setText("Bad Biddie Version: \n Roll to Start!");

        }

        reroll.setVisibility(View.GONE);
        background = findViewById(R.id.mainBack);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                rando = random.nextInt(10);
                if (rando == 0) {
                    rando++;
                }
                display = rando + "";
                result.setTextSize(144);
                result.setText(display);
                display = "Click here for task";
                roll.setText(display);
                reroll.setVisibility(View.VISIBLE);


                roll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), GameActivity.class);
                        intent.putExtra("taskNumber", rando);
                        writeTask();
                        intent.putExtra("taskName", taskString);
                        intent.putExtra("gameType", gameType);

                        // change the background
                        background.setBackgroundResource(R.drawable.bgorange);
                        background.animate().scaleX(4).scaleY(4).setDuration(1000).start();

                       // timer for change the holderbg
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                background.setScaleX(3);
                                background.setScaleY(3);
                                background.setBackgroundResource(R.drawable.bgorange);
                                background.setScaleX(0);

                                background.setScaleY(0);
                            }
                        }, 850);

                        startActivity(intent);
                        //finish();

                    }
                });

                reroll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Random random = new Random();
                        rando = random.nextInt(10);
                        if (rando == 0) {
                            rando++;
                        }
                        display = rando + "";
                        result.setText(display);
                        display = "Click here for task";
                    }
                });



            }
        });

    }

    private void writeTask() {
        pg[0] = "Name one of your hobbies";
        pg[1] = "Play duck, duck, goose for one round";
        pg[2] = "10 Jumping jacks";
        pg[3] = "Hold your breath for 20 seconds";
        pg[4] = "Hug the person to your left";
        pg[5] = "Make up your own handshake with the person to your left";
        pg[6] = "Have a staring contest with the person in front of you";
        pg[7] = "Run to the nearest room and bring back something and tell a story about it.";
        pg[8] = "Draw something and have the rest try to guess what it is";
        pg[9] = "The person in front of you decides your task!";


        bad[0] = "Remove one piece of clothing";
        bad[1] = "Kiss the person of your choice (you choose where)";
        bad[2] = "Use a pick up line on the person to your left";
        bad[3] = "Let the person to your right go through your photos";
        bad[4] = "Person to your left decides your task";
        bad[5] = "Answer ONE question the person to your right asks";
        bad[6] = "Tell an embarrassing story";
        bad[7] = "Call your ex and ask her/him out on a date (if no ex, call crush)";
        bad[8] = "Who would you bang, marry, kill from 3 other players of your choice.";
        bad[9] = "Give the person in front of you a lap dance.";

        if (gameType.equals("goodBoy")) {
            taskString = pg[rando - 1];

        } else {
            taskString = bad[rando - 1];
        }

    }
}

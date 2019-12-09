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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button roll = findViewById(R.id.roll);
        final TextView result = findViewById(R.id.result);
        final SeekBar skBar = findViewById(R.id.seekBar);
        final Button reroll = findViewById(R.id.reRoll);
        result.setText("Roll to Start!");
        reroll.setVisibility(View.GONE);
        background = findViewById(R.id.mainBack);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                rando = random.nextInt(skBar.getProgress());
                if (rando == 0) {
                    rando++;
                }
                display = rando + "";
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

                    }
                });

                reroll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Random random = new Random();
                        rando = random.nextInt(skBar.getProgress());
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

    }
}

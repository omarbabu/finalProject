package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Vibrator;





import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;



public class GameActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private TextView mTextField;
    private Button rules;
    private Button playAgain;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        playAgain = findViewById(R.id.reTry);
        playAgain.setVisibility(View.GONE);
        rules = findViewById(R.id.rules);
        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), RulesActivity.class));
            }
        });

        TextView task = findViewById(R.id.task);
        String taskString = "";
        mTextField = findViewById(R.id.timer);

        task.setText(taskString);
        int seconds = 60 * getIntent().getIntExtra("taskNumber", 1);

        new CountDownTimer(seconds * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextField.setText("Seconds Remaining: \n" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mTextField.setText("Time Up!");
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                playAgain.setVisibility(View.VISIBLE);
                playAgain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(v.getContext(), MainActivity.class));
                    }
                });
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v.vibrate(500);
                }

            }
        }.start();




    }
}

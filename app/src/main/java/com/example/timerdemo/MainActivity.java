package com.example.timerdemo;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView tv;
    Boolean counterIsActive = false;
    Button goButton;
    CountDownTimer countDownTimer;
    public void resetTimer(){
        tv.setText("0:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("Go!");
        counterIsActive = false;
    }
    public void Buttonn(View view) {
        if (counterIsActive) {
            resetTimer();
        } else {
            counterIsActive = true;
            seekBar.setEnabled(false);
            goButton.setText("STOP");
                                                         //10 sec                            1 sec
            countDownTimer = new CountDownTimer(seekBar.getProgress()*1000+100, 1000) {
                @Override
                                  // l=milisecond
                public void onTick(long l) {
                    UpdateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ht);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }
    public void UpdateTimer(int i){
        int minutes = i/60;
        int seconds = i- (minutes * 60);
        String secondString = Integer.toString(seconds);
        String minutesString = Integer.toString(minutes);
        if(minutes<=9){
            minutesString = "0" + minutesString;
        }
        if(seconds<=9){
            secondString ="0" + secondString;
        }

        tv.setText(minutesString+":" + secondString );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar2);
         tv = findViewById(R.id.textView2);
         goButton= findViewById(R.id.button);

        seekBar.setMax((600));//10min
        seekBar.setProgress(30);//30seconds

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
            UpdateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        }
    }
 /*new CountDownTimer(10000, 1000){
            public void onTick(long millisecondsUntilDone){
                Log.i( "seconds Left! ", String.valueOf(millisecondsUntilDone/1000));
            }
            public  void  onFinish(){
                Log.i("we re done ", " NO more countdown");
            }
        }.start();*/
       /* final Handler  handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Log.i("Hey it's me ", "A second Passed by");
                handler.postDelayed(this,1000);
            }
        };
         handler.post(run);*/
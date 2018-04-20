package com.example.v_wif.timetask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    //declare timer and timer task instance variables
    Timer timer;
    TimerTask timerTask;

    //declare a Handler to run the TimerTask
    final Handler handler = new Handler();

    //declare widget
    Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get reference to widget
        cancelButton = findViewById(R.id.cancelButton);

        //handle the buttons onClick event
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if timer is still running cancel it.
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
            }
        });
    }//end onCreate()

    @Override
    protected void onResume() {
        super.onResume();

        //start timer when the app comes back from the background
        startTimer();
    }//end onResume()

    private void startTimer() {
        timer = new Timer();

        initializeTimerTask();

        //schedule the timer - after 5000ms delay, run it every 5000ms
        timer.schedule(timerTask, 5000, 5000);
    }//end startTimer()

    //initialize the TimerTask with what it will do
    private void initializeTimerTask() {

        timerTask = new TimerTask() {
            @Override
            public void run() {

                //use a handler to display the toast
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //get current TimeStamp
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:mm:yyyy, HH:mm:ss a");
                        final String strDate = simpleDateFormat.format(calendar.getTime());

                        //display the toast
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(getApplicationContext(), strDate, duration);
                        toast.show();
                    }//end run()
                });

            }//end initializeTimerTask()
        };
    }

}
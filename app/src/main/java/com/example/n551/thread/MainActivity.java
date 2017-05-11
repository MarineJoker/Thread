package com.example.n551.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar bar = null;
    Button sB = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar=(ProgressBar)findViewById(R.id.bar);
        sB=(Button)findViewById(R.id.startButton);
        sB.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                bar.setVisibility(View.VISIBLE);
                updataB.post(updateThread);
            }
        }
        );
    }
    Handler updataB=new Handler()
    {
        public void handleMessage(Message msg)
        {
          bar.setProgress(msg.arg1);
            updataB.post(updateThread);
        }
    };
    Runnable updateThread = new Runnable()
    {
        int i=0;
        public void run()
        {
            i=i+10;
            Message msg=updataB.obtainMessage();
            msg.arg1=i;
            try{
                Thread.sleep(1000);
                Log.i("123", "123");
            }catch (InterruptedException e)
            {
                e.printStackTrace();

            }
            updataB.sendMessage(msg);
            if(i==100)
            {
                updataB.removeCallbacks(updateThread);
            }
        }
    };
}

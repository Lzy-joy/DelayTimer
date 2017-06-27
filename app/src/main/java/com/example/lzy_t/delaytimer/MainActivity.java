package com.example.lzy_t.delaytimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private DelayTimer delayTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        delayTimer = new DelayTimer();

        initViews();
    }

    private void initViews() {
        textView = (TextView) findViewById(R.id.tv_text);
        delayTimer.setTimerFinished(new DelayTimer.TimerFinished() {
            @Override
            public void onFinished() {
                textView.setText("延时执行结束");
            }
        });
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delayTimer.startDelayTimer(3000);
                textView.setText("延时执行中...");
            }
        });

        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delayTimer.stopDelayTimer();
                textView.setText("延时执行取消");
            }
        });

        findViewById(R.id.btn_status).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "状态：" + delayTimer.isRunning(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

package com.example.lzy_t.delaytimer;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.Timer;

/**
 * 类描述：延时器
 * Created by lzy on 2017/6/26.
 * email:lzy_tinyjoy@163.com
 */

public class DelayTimer {
    private static final int DELAY_INFO_FLAG = 1;
    private boolean isRunning;
    private Timer timer;
    private java.util.TimerTask timerTask;
    private TimerFinished timerFinished;

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == DELAY_INFO_FLAG) {
                stopDelayTimer();
                if (timerFinished != null) {
                    timerFinished.onFinished();
                }
            }
        }
    };

    public DelayTimer() {
        isRunning = false;
    }

    /**
     * 方法描述：开始延时
     *
     * @param delayTime 延时时间（单位ms）
     */
    public void startDelayTimer(long delayTime) {
        if (timer == null) {
            timer = new Timer();
        }
        if (timerTask == null) {
            timerTask = new java.util.TimerTask() {
                @Override
                public void run() {
                    handler.sendMessage(handler.obtainMessage(DELAY_INFO_FLAG));
                }
            };
        }
        if (timer != null) {
            isRunning = true;
            timer.schedule(timerTask, delayTime);
        }
    }

    /**
     * 方法描述：停止延时器（如果此时延时器正在执行则取消延时器）
     */
    public void stopDelayTimer() {
        isRunning = false;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    /**
     * 方法描述：延时器是否正在执行
     *
     * @return 延时器状态
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * 方法描述：注入延时回调接口
     */
    public void setTimerFinished(TimerFinished timerFinished) {
        this.timerFinished = timerFinished;
    }

    /**
     * 类描述：延时结束回调接口
     */
    public interface TimerFinished {
        void onFinished();
    }
}

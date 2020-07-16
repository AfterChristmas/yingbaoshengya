package com.yingbao.career.utils;

import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2016/4/23.
 */
public class TimerTools extends Thread {

    private int remainTime = 0;
    private boolean isRun = true;
    private Handler handler;

    public static final int SHOW_COUNTDOWN = 0;

    public TimerTools() {
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isRun() {
        return isRun;
    }

    public void setIsRun(boolean isRun) {
        this.isRun = isRun;
    }

    public int getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }

    @Override
    public void run() {
        super.run();
        while (isRun) {
            try {
                Thread.sleep(1000);
                remainTime++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String timeStr = String.valueOf(remainTime);
            Message msg = Message.obtain();
            msg.what = SHOW_COUNTDOWN;
            msg.obj = timeStr;
            if (handler != null) {
                handler.sendMessage(msg);
            }
        }
    }
}

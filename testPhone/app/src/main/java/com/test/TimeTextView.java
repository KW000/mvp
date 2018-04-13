package com.test;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;


/**
 * desc
 * Author:shimao
 * Date:2017.11.13 13:48
 */
public class TimeTextView extends TextView {

    public TimeTextView(Context context) {
        super(context);
    }

    public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 在控件被销毁时移除消息
        handler.removeMessages(0);
    }

    long Time;
//    private Handler handler = new Handler(Looper.getMainLooper()) {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 0:
//                    if (Time >= 0) {
//                        updateTextView(Time);
//                        Time = Time-1000;
//                        handler.sendEmptyMessageDelayed(0, 1000);
//                    }else {
//                        TimeTextView.this.setVisibility(View.GONE);
//                    }
//                    break;
//            }
//        }
//    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    if (Time >= 0) {
                        updateTextView(Time);
                        Time = Time - 1000;
                        handler.sendEmptyMessageDelayed(0, 1000);
                    } else {
                        TimeTextView.this.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    };

    public void setTimes(long time) {
        Time = time;
        if (Time >= 0) {
            handler.sendEmptyMessage(0);
        } else {
            TimeTextView.this.setVisibility(View.GONE);
        }
    }

    /**
     * 刷新倒计时控件
     */
    public void updateTextView(long time) {
        if (time <= 0) {
            setText("00:00:00");
            return;
        }
        //秒钟
        long time_second = (time / 1000) % 60;
        String str_second;
        if (time_second < 10) {
            str_second = "0" + time_second;
        } else {
            str_second = "" + time_second;
        }

        long time_temp = ((time / 1000) - time_second) / 60;
        //分钟
        long time_minute = time_temp % 60;
        String str_minute;
        if (time_minute < 10) {
            str_minute = "0" + time_minute;
        } else {
            str_minute = "" + time_minute;
        }

        time_temp = (time_temp - time_minute) / 60;
        //小时
        long time_hour = time_temp;
        String str_hour;
        if (time_hour < 10) {
            str_hour = "0" + time_hour;
        } else {
            str_hour = "" + time_hour;
        }
        setText(str_hour + "小时" + str_minute + "分" + str_second + "秒");
    }

}

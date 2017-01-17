package demo.android.boundservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import org.junit.rules.Stopwatch;


/**
 * Created by ln-149 on 17/1/17.
 */

public class StopwatchService extends Service {
    private static final String TAG = "StopwatchService";
    public Stopwatch m_stopwatch;
    long starttime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedtime = 0L;
    int t = 1;
    int secs = 0;
    int mins = 0;
    int milliseconds = 0;
    Handler handler = new Handler();

    public Runnable updateTimer = new Runnable() {
        @Override
        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - starttime;

            updatedtime = timeSwapBuff + timeInMilliseconds;

            secs = (int) (updatedtime / 1000);
            mins = secs / 60;
            secs = secs % 60;
            milliseconds = (int) (updatedtime % 1000);
            MainActivity.tv_sw.setText("" + mins + ":" + String.format("%02d", secs) + ":"+ String.format("%03d", milliseconds));
            handler.postDelayed(this, 0);
        }
    };

    public void start()
    {
        if (t==1)
        {
            starttime = SystemClock.uptimeMillis();
            handler.postDelayed(updateTimer,0);
            t=0;
            //MainActivity.tv_sw.setText(String.valueOf(mins)+":"+String.valueOf(secs)+":"+String.valueOf(milliseconds));
        }
    }

    public void pause()
    {
        timeSwapBuff += timeInMilliseconds;
        handler.removeCallbacks(updateTimer);
        t=1;
        //MainActivity.tv_sw.setText(String.valueOf(timeSwapBuff));

    }

    public void reset()
    {
        starttime = 0L;
        timeInMilliseconds = 0L;
        timeSwapBuff = 0L;
        updatedtime = 0L;
        t = 1;
        secs = 0;
        mins = 0;
        milliseconds = 0;
        handler.removeCallbacks(updateTimer);
        MainActivity.tv_sw.setText("00:00:00");
    }

    private LocalBinder m_binder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "bound");

        return m_binder;
    }

    public class LocalBinder extends Binder {

        Class<StopwatchService> getService() {
            return StopwatchService.class;
        }
    }


}
package org.yamu.tea_core.util.timer;

import java.util.TimerTask;

/**
 * Created by 马杰涛 on 2018/10/2
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null)
            mITimerListener.onTimer();
    }
}

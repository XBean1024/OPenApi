package com.binny.openapi.mvp.ui.activity;

import android.app.Service;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.binny.openapi.R;
import com.binny.openapi.mvp.ui.base.BaseActivity;
import com.binny.openapi.util.UtilsLog;

public class LockScreenActivity extends BaseActivity implements PinLockListener {


    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;

    //创建震动服务对象
    private Vibrator mVibrator;


    @Override
    protected void handleIntent() {

    }

    @Override
    protected void initView() {

        //获取手机震动服务
        mVibrator=(Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE);
        mPinLockView = findViewById(R.id.pin_lock_view);
        mPinLockView.setPinLockListener(this);

        mIndicatorDots = findViewById(R.id.indicator_dots);

        mPinLockView.attachIndicatorDots(mIndicatorDots);
        //mPinLockView.setCustomKeySet(new int[]{2, 3, 1, 5, 9, 6, 7, 0, 8, 4});
        //mPinLockView.enableLayoutShuffling();

        mPinLockView.setPinLength(4);
        mPinLockView.setTextColor(ContextCompat.getColor(this, R.color.white));

        mIndicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lock_screen;
    }

    @Override
    public void onComplete(String pin) {

        UtilsLog.i(pin);
        if ("1234".equals(pin)) {
            intoActivityWithAnimotion(MainActivity.class);
        }else {
            //设置震动周期，数组表示时间：等待+执行，单位是毫秒，下面操作代表:等待100，执行100，等待100，执行1000，
            //后面的数字如果为-1代表不重复，之执行一次，其他代表会重复，0代表从数组的第0个位置开始
            mVibrator.vibrate(new long[]{100,100,100,1000},-1);
            Toast.makeText(this, "密码为 1234", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onEmpty() {
        UtilsLog.i("........");

    }

    @Override
    public void onPinChange(int pinLength, String intermediatePin) {
        UtilsLog.i(intermediatePin);
    }
}

package com.binny.openapi.mvp.view.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import com.binny.openapi.R;
import com.binny.openapi.mvp.view.base.AbsBaseActivity;

import java.util.Set;

public class BluetoochActivity extends AbsBaseActivity {
    BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void handleIntent() {

    }

    @Override
    protected void initView() {
        mImmersionBar.titleBar(R.id.header_ble).barColorInt(Color.BLUE).execute();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_blue_tooch;
    }

    public void openBLE(View view) {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(mActivity, "不支持蓝牙设备", Toast.LENGTH_SHORT).show();
            return;
        }
        //判断蓝牙设备是否处于关闭状态，如果是则打开蓝牙
        if (!mBluetoothAdapter.isEnabled()) {
            if (mBluetoothAdapter.enable()) {//打开蓝牙设备
                //开启蓝牙后，需设置蓝牙为可发现状态，这样其它的蓝牙设备才能搜索到。
                Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                //最后的参数设置可见的时间，最长为300s,设为0表示一直可见
                discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);
                startActivity(discoverableIntent);
                //成功打开蓝牙后开始搜索附近的蓝牙设备
                mBluetoothAdapter.startDiscovery();
                //停止搜索：mBluetoothAdapter.cancelDiscovery();

            }
        }

    }

    public void closeBLE(View view) {
        if (mBluetoothAdapter == null) {
            Toast.makeText(mActivity, "不支持蓝牙设备", Toast.LENGTH_SHORT).show();
            return;
        }
        mBluetoothAdapter.disable();
    }

    public void getBLEList(View view) {
        if (mBluetoothAdapter == null) {
            Toast.makeText(mActivity, "不支持蓝牙设备", Toast.LENGTH_SHORT).show();
            return;
        }
        Set<BluetoothDevice> Bondedlist =mBluetoothAdapter.getBondedDevices();
    }

}

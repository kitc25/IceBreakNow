package com.purplecorn.icebreaknow.service;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class IceBreakNowBLESevice extends Service implements BluetoothAdapter.LeScanCallback{

    private final BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter;
    private Handler handler;
    private boolean isScanning = false;
    private final int SCAN_PERIOD = 180000;

    public IceBreakNowBLESevice() {
        bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
        handler = new Handler();

        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            startBLECentralService();
            startBLEPeripheralService();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void startBLECentralService(){


            // Stops scanning after a pre-defined scan period.
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isScanning = false;
                    bluetoothAdapter.stopLeScan(IceBreakNowBLESevice.this);
                }
            }, SCAN_PERIOD);

            isScanning = true;
            bluetoothAdapter.startLeScan(this);

    }

    private void startBLEPeripheralService(){

    }

    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(isScanning){

        }
    }
}

package com.example.kotlindemo.aidl;

import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

public class AidlDemo implements IMyAidlInterface {
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
    }

    @Override
    public IBinder asBinder() {
        List<String> data = new ArrayList<>();
        data.add(2, "2");
        try {
            new Default().basicTypes(1, 1, true, 0, 0, "");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}

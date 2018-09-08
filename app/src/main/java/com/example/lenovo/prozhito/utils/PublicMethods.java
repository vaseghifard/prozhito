package com.example.lenovo.prozhito.utils;

import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

public class PublicMethods {

    public static void toast(int msg) {
        Toast.makeText(MyApplication.appInstance, msg, Toast.LENGTH_SHORT).show();
    }

    public static void saveValue(String key, String value) {
        Hawk.put(key, value);
    }

    public static String getValue(String key, String defValue) {
        return Hawk.get(key, defValue);
    }


}

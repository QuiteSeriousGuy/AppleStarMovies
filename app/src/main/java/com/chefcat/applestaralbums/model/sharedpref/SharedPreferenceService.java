package com.chefcat.applestaralbums.model.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

import com.chefcat.applestaralbums.util.Constant;

import java.util.Date;

public class SharedPreferenceService {
    public static void saveLastIn(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constant.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(Constant.SHARED_PREF_LAST_IN, (new Date()).toString());
        editor.apply();
    }

    public static String getLastIn(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constant.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return prefs.getString(Constant.SHARED_PREF_LAST_IN, "");
    }


    public static void saveLastScreen(Context context, int screen) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constant.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(Constant.SHARED_PREF_LAST_SCREEN, screen);
        editor.apply();
    }

    public static int getLastScreen(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constant.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(Constant.SHARED_PREF_LAST_SCREEN, 0);
    }


    public static void saveLastInfo(Context context, long info) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constant.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
        editor.putLong(Constant.SHARED_PREF_LAST_INFO, info);
        editor.apply();
    }

    public static long getLastInfo(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constant.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return prefs.getLong(Constant.SHARED_PREF_LAST_INFO, 0);
    }

}

package com.chefcat.applestaralbums.util;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TimeHelper {

    public static String milliToTime(String milliString) {
        Long milli = new Long(milliString);
        int seconds = (int) (milli / 1000) % 60 ;
        int minutes = (int) ((milli / (1000*60)) % 60);
        int hours   = (int) ((milli / (1000*60*60)) % 24);

        return hours + "h " + minutes + "m " + seconds + "s ";
    }

    public static String timeStampStringToShortDate(String timeStamp) {
        Log.e("Time Stamp", timeStamp);
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        DateFormat df2 = new SimpleDateFormat("MMM yyyy", Locale.getDefault());

        String result = " - ";
        try {
            result = df2.format(df1.parse(timeStamp));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String longDateStringToTime(String longDate) {
        Log.e("Date", longDate);
        DateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault());
        DateFormat df2 = new SimpleDateFormat("yyyy MMM dd hh:mm:ss", Locale.getDefault());

        String result = " - ";
        try {
            result = df2.format(df1.parse(longDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

}

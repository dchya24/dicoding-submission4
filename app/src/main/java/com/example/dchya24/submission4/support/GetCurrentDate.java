package com.example.dchya24.submission4.support;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GetCurrentDate {
    public static String getDate(){
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        return df.format(c);
    }

    public static boolean isDateInvalid(String string, String format){
        try{
            DateFormat df = new SimpleDateFormat(format, Locale.getDefault());
            df.setLenient(false);
            df.parse(string);
            return false;
        }catch (ParseException e){
            return true;
        }
    }
}

package com.purplecorn.icebreaknow.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Kit on 5/16/2016.
 */
public class IceBreakNowUtil {

    private static final SimpleDateFormat serverDOBFormat = new SimpleDateFormat(IceBreakNowContant.SERVER_DOB_FORMAT);

    public static int calculateAgeFromDOB(String dob){
        int age = 0;
        try {
            Date birthday = serverDOBFormat.parse(dob);
            Calendar bday = Calendar.getInstance();
            bday.setTime(birthday);
            Calendar curDay = Calendar.getInstance();//(Calendar) bday.clone();

            age = curDay.get(Calendar.YEAR) - bday.get(Calendar.YEAR);
            if(bday.get(Calendar.MONTH) > curDay.get(Calendar.MONTH) || (bday.get(Calendar.MONTH) > curDay.get(Calendar.MONTH) && bday.get(Calendar.DAY_OF_MONTH) > curDay.get(Calendar.DAY_OF_MONTH))){
                age--;
            }
        }
        catch(ParseException e){
            e.printStackTrace();
        }
        return age;
    }
}
